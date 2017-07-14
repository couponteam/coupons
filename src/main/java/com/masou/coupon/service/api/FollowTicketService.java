package com.masou.coupon.service.api;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.common.constant.BeValue;
import com.masou.coupon.common.constant.DicValue;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.enums.StatusEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.api.TicketDao;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.filter.StatisticFilter;
import com.masou.coupon.data.mappers.FcodeMapper;
import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.mappers.TicketMapper;
import com.masou.coupon.data.models.*;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.utils.CommonKeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by jason on 2017/7/5.
 */
@Service
public class FollowTicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private FcodeMapper fcodeMapper;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private FollowShopService followShopService;

    @Autowired
    private ShopMapper shopMapper;

    private Logger logger = LoggerFactory.getLogger(FollowTicketService.class);

//    @Autowired
//    private


    /**
     * 用户点击查看一条记录，领取一张券，等等的接口
     * @param uid
     * @param tid
     * @param status
     * @return
     */
    public Result userCollectTicket(Long uid, String tid, Integer status,String fcode, Long sid){
        //1.处理转发领取
        if(fcode != null && fcode.trim().length() > 0){
            Fcode fcoders = checkFcode(fcode);
            if (uid != null){
                if (uid == fcoders.getUid()){
                    logger.info("The same uid : " + uid + "   fcode uid : " + fcoders.getUid());
                    //如果当前url带有fcode，并且fcode对应的uid和现在传进来的uid相同，则表示当前不是转发领取
                    ;
                }
            }else{
                logger.info("Deal with share code :" + JSON.toJSONString(fcoders));
                forwardTicket(fcoders);
                return ResultHelper.genResultWithSuccess();
            }
        }

        //2.判断券号是否为空
        if(tid == null || tid.trim().length() < 0){
            throw new UserException("无券号码");
        }
        if(uid == null || uid < 0){
            throw new UserException("无效的token");
        }

        //2.判断券是否领取完
        if ( !checkTicketAmount(tid)){
            throw new UserException("券已领完");
        }

        //根据uid和tid，获取店铺和券的详细信息
        ShopFilter param = new ShopFilter();
        param.setTid(tid);
        TicketWithBLOBs ticket = ticketMapper.selectByTid(param);
        if (ticket == null || ticket.getId() == null || ticket.getId() <= 0){
            throw new UserException("券不存在");
        }

        //判断当前券是否已经领取完
        if(!checkTicketAmount(tid)){
            throw new UserException("该券已领取完");
        }

        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(uid);
        userTicket.setTicketId(tid);
        //设置券号
        String utId = CommonKeyUtils.genTicketKey(uid);
        userTicket.setUtId(utId);
        userTicket.setCreateTime(new Date());
        userTicket.setStatus(Byte.parseByte(status+""));

        //4.判断用户是否关注过本店，未关注，表示首次关注，送关注领取券和当前领取的券
        if(followShopService.isFollowShop(uid, ticket.getShopId())){
            //4.已关注，判断用户是否领取过本券,领取过，判断是否可重复领取
            //4.1判断是否已领取券
            List<UserTicket> userTickets = ticketDao.findByUidTid(userTicket);
            //已领取
            if(userTickets != null  && userTickets.size() > 0){
                //4.1.1，已领取，判断是否可重复领取，并且判断领取的数量是否上限
                //超过5张，则无法领取
                if(userTickets.size() >= 5){
                    throw new UserException("领取上限");
                }
                //已经存在，检查是否可重复领取
//                int isRetaken = ticketDao.isRetaken(userTicket);
                int isRetaken = ticket.getIsRetaken();
                //4.1.1.1可重复领取，则领取券
                if(isRetaken == DicValue.TICKET_IS_RETAKEN ){
                    //4.1.1.1.1返回领取成功
                    return ResultHelper.genResultWithSuccess(insertTicket(userTicket));
                }else{
                    //4.1.1.2 不可重复领取，返回领取失败
                    throw new UserException("不可重复领取");
                }
                //4.1.2无法继续领取该券
//                return ResultHelper.genResult(ErrorCodeEnum.USER_TICKET_OPEATE_FAILED.getCode(), "已领取该券");
            }else{
                //4.2未领取，直接领取券
                if(insertTicket(userTicket) > 0){
                    //4.1.1.1.1返回领取成功
                    return ResultHelper.genResultWithSuccess();
                }
                //4.1.1.2返回领取失败
                return ResultHelper.genResult(ErrorCodeEnum.USER_TICKET_OPEATE_FAILED);
            }
        }else{
            System.out.println("未关注店铺");
            //4.2未关注店铺，则处理流程：关注店铺->领取券->领取关注领取券
            if(followShopTicket(uid, ticket.getShopId())){
                //关注店铺成功，并且领取[ 关注即领 ] 券，领取该券
                return ResultHelper.genResultWithSuccess(insertTicket(userTicket));
            }
        }
        return ResultHelper.genResult(ErrorCodeEnum.USER_TICKET_OPEATE_FAILED);
    }

    /**
     * 用户关注店铺，同时领取[ 关注领取 ] 券
     * @param uid
     * @param sid
     * @return
     */
    public boolean followShopTicket(Long uid, Long sid){
        //1.先关注店铺
        if(followShopService.followShop(uid, sid)){
            //2.关注成功，获取关注领取的券
            ShopFilter shopFilter = new ShopFilter();
            shopFilter.setSid(sid);
            TicketWithBLOBs ticket = ticketMapper.selectFollowTicket(shopFilter);
            if(ticket != null){
                insertTicket(uid, ticket.getTicketId());
                return true;
            }
            return true;
        }
        return false;
    }


    /**
     * 用户领取券，将券插入到数据库中
     * @param uid
     * @param tid
     * @return
     */
    public int insertTicket(Long uid, String tid){
        //插入券到数据库
        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(uid);
        userTicket.setTicketId(tid);
        userTicket.setCreateTime(new Date());
        userTicket.setStatus(Byte.parseByte(DicValue.TICKET_STATUS_GOT+""));

        String utId = CommonKeyUtils.genTicketKey(uid);
        userTicket.setUtId(utId);
        return ticketDao.userCollectTicket(userTicket);
    }

    /**
     * 用户领取券，将券插入到数据库中
     * @return
     */
    public int insertTicket(UserTicket userTicket){
        return ticketDao.userCollectTicket(userTicket);
    }

    /**
     * 关注券，
     * @param ticketId
     * @param uid
     * @param status
     * @return
     */
    public int followTicket(String ticketId, Long uid, Integer status){

        return 0;
    }

    /**
     * 检查转发的fcode是否可用
     * @param fcode
     * @return true：可用；false：不可用
     */
    public Fcode checkFcode(String fcode){
        Fcode fcodeObj = new Fcode();
        fcodeObj.setFcode(fcode);
        Fcode rs = fcodeMapper.selectByFcode(fcodeObj);
        if(rs != null && rs.getId() > 0){
            //判断是否关注该店铺
            if(followShopService.isFollowShop(rs.getUid(), rs.getSid())){
                //未关注店铺，则先关注该店铺
                followShopTicket(rs.getUid(), rs.getSid());
            }
            return rs;
        }
        return null;
    }

    /**
     * 检查用户是否已领取券
     * @param uid
     * @param ticketId
     * @return true：已领取；false：未领取
     */
    public boolean isFollowedTicket(Long uid, String ticketId){
//        List<UserTicket> userTickets = ticketDao.findByUidTid(userTicket);

        return false;
    }

    /**
     * 检查券是否可重复领取
     * @return
     */
    public boolean isRetaken(){
        //判断转发领取的券是否已经领取


        return false;
    }

    /**
     * 转发领取券
     * @return
     */
    public Result forwardTicket(Fcode fcoders){
//            Fcode fcodeObj = new Fcode();
//            fcodeObj.setFcode(fcode);
            //用fcode获取用户信息
//            Fcode rs = fcodeMapper.selectByFcode(fcodeObj);
        if(fcoders != null){
            //检查券是否已被领取
            ShopFilter shopFilter = new ShopFilter();
            shopFilter.setSid(fcoders.getSid());
            shopFilter.setUid(fcoders.getUid());
            shopFilter.setLimit(1);
            TicketWithBLOBs ticketWithBLOBs = ticketMapper.selectIdBySidUid(shopFilter);
            if (ticketWithBLOBs != null){
                //已领取，返回
                return ResultHelper.genResult(ErrorCodeEnum.USER_TICKET_OPEATE_EXEISTS);
            }
            //未领取，从数据库获取券id

            TicketWithBLOBs followTicket = ticketMapper.ticketByFollow(shopFilter);
            if(followTicket == null){
                return ResultHelper.genResult(ErrorCodeEnum.FAILED);
            }else{
                insertTicket(fcoders.getUid(),followTicket.getTicketId());
                return ResultHelper.genResultWithSuccess();
            }
        }
        return ResultHelper.genResult(ErrorCodeEnum.FAILED);
    }

    /**
     * 查询券数量
     * @param tid
     * @return
     */
    public synchronized boolean checkTicketAmount(String tid){
        StatisticFilter statisticFilter = new StatisticFilter();
        statisticFilter.setTid(tid);
        int token = ticketMapper.ticketCountByTake(statisticFilter);
        int total = ticketMapper.selectCountByTid(statisticFilter);
        if((total - token) <= 0){
            return false;
        }
        return true;
    }


}
