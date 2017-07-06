package com.masou.coupon.service.api;

import com.masou.coupon.common.constant.DicValue;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.enums.StatusEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.api.TicketDao;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.filter.StatisticFilter;
import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.mappers.TicketMapper;
import com.masou.coupon.data.models.Ticket;
import com.masou.coupon.data.models.TicketWithBLOBs;
import com.masou.coupon.data.models.UserTicket;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.utils.CommonKeyUtils;
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
    private TicketDao ticketDao;

    @Autowired
    private FollowShopService followShopService;

    @Autowired
    private ShopMapper shopMapper;

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
//        //1.判断券号是否为空
//        if(tid == null || tid.trim().length() < 0){
//            throw new UserException("无券号码");
//        }
//
//        //2.判断券是否领取完
//        if ( !checkTicketAmount(tid)){
//            throw new UserException("券已领完");
//        }
//
//        //根据uid和tid，获取店铺和券的详细信息
//        ShopFilter param = new ShopFilter();
//        param.setTid(tid);
//        TicketWithBLOBs ticket = ticketMapper.selectByTid(param);
//        if (ticket == null || ticket.getId() == null || ticket.getId() <= 0){
//            throw new UserException("券不存在");
//        }
//
//        //3.处理转发领取
//        if(fcode != null && fcode.trim().length() > 0){
//            if(checkFcode(fcode)){
//                forwardTicket(fcode, sid);
//                return ResultHelper.genResultWithSuccess();
//            }
//        }
//
//        //4.判断用户是否关注过本店，未关注，表示首次关注，送关注领取券和当前领取的券
//        if(followShopService.isFollowShop(uid, ticket.getShopId())){
//            //4.已关注，判断用户是否领取过本券,领取过，判断是否可重复领取
//            //4.1判断是否已领取券
//            if(isFollowedTicket(uid, tid)){
//                //4.1.1，已领取，判断是否可重复领取，并且判断领取的数量是否上限
//                if(isRetaken()){
//                    //4.1.1.1可重复领取，则领取券
//                    Integer rs = followTicket(tid, uid, status);
//                    if(rs != null && rs > 0){
//                        //4.1.1.1.1返回领取成功
//                        return ResultHelper.genResultWithSuccess();
//                    }
//                    //4.1.1.2返回领取失败
//                    return ResultHelper.genResult(ErrorCodeEnum.USER_TICKET_OPEATE_FAILED);
//                }
//                //4.1.2无法继续领取该券
//                return ResultHelper.genResult(ErrorCodeEnum.USER_TICKET_OPEATE_FAILED.getCode(), "已领取该券");
//            }else{
//                //4.2未领取，领取该券
//                Integer rs = followTicket(tid, uid, status);
//                if(rs != null && rs > 0){
//                    //4.1.1.1.1返回领取成功
//                    return ResultHelper.genResultWithSuccess();
//                }
//                //4.1.1.2返回领取失败
//                return ResultHelper.genResult(ErrorCodeEnum.USER_TICKET_OPEATE_FAILED);
//            }
//        }else{
//            //4.2未关注店铺，则处理流程：关注店铺->领取券->领取关注领取券
//            if (followShopService.followShop(uid, ticket.getShopId(), StatusEnum.SHOP_USER_FOCUS.getStatus())){
//                //关注店铺成功后，领取当前券和关注即领取券
//
//
//            }
//        }


        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(uid);
        userTicket.setTicketId(tid);
        userTicket.setCreateTime(new Date());
        userTicket.setStatus(Byte.parseByte(status+""));

        //检查当前券的数量是否可再领取
        if (checkTicketAmount(tid)){
            //检查数据库中是否已经存在
            List<UserTicket> userTickets = ticketDao.findByUidTid(userTicket);
            if(userTickets != null &&userTickets.size() > 0){
                //已经存在，检查是否可重复领取
                int isRetaken = ticketDao.isRetaken(userTicket);
                //不可重复领取，返回false
                if(isRetaken != DicValue.TICKET_IS_RETAKEN || userTickets.size() >= 2){
                    return ResultHelper.genResult(ErrorCodeEnum.USER_TICKET_OPEATE_FAILED.getCode(), "不可重复领取");
                }
            }

            String utId = CommonKeyUtils.genTicketKey(uid);
            userTicket.setUtId(utId);
            if(ticketDao.userCollectTicket(userTicket) > 0){
                //领取成功后，对券数量减一
                return ResultHelper.genResultWithSuccess(userTicket);
            }
        }else{
            return ResultHelper.genResult(ErrorCodeEnum.USER_TICKET_OPEATE_FAILED.getCode(), "OOPS，已经领完了");
        }
        return ResultHelper.genResult(ErrorCodeEnum.USER_TICKET_OPEATE_FAILED);
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
    public boolean checkFcode(String fcode){

        return false;
    }

    /**
     * 检查用户是否已领取券
     * @param uid
     * @param ticketId
     * @return true：已领取；false：未领取
     */
    public boolean isFollowedTicket(Long uid, String ticketId){


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

    public int forwardTicket(String fcode, Long sid){
        if(fcode != null && fcode.trim().length() > 0){
            //检查券是否已被领取
            ShopFilter shopFilter = new ShopFilter();
            shopFilter.setSid(sid);
//            shopMapper.selectTicketBysid();
        }
        return 0;
    }

    /**
     * 查询券数量
     * @param tid
     * @return
     */
    public synchronized boolean checkTicketAmount(String tid){
        StatisticFilter statisticFilter = new StatisticFilter();
        statisticFilter.setTid(tid);
        int token = ticketMapper.ticketCount(statisticFilter);
        int total = ticketMapper.selectCountByTid(statisticFilter);
        if((total - token) <= 0){
            return false;
        }
        return true;
    }


}
