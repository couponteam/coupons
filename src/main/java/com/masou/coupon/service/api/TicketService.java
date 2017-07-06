package com.masou.coupon.service.api;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.action.api.vo.*;
//import com.masou.coupon.dao.ShopDao;
import com.masou.coupon.action.api.vo.ticketvo.ShopList;
import com.masou.coupon.action.api.vo.ticketvo.ShopTicket;
import com.masou.coupon.action.api.vo.ticketvo.TicketVO;
import com.masou.coupon.action.api.vo.ticketvo.Tickets;
import com.masou.coupon.common.constant.DicValue;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.api.ShopDao;
import com.masou.coupon.dao.api.TicketDao;
import com.masou.coupon.data.filter.*;
import com.masou.coupon.data.mappers.*;
import com.masou.coupon.data.models.*;
import com.masou.coupon.data.param.PageParam;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.CommonService;
import com.masou.coupon.service.LocaltionCalcService;
import com.masou.coupon.service.shopapi.ShopManagerService;
import com.masou.coupon.service.shopapi.TicketManagerService;
import com.masou.coupon.utils.CommonKeyUtils;
import com.masou.coupon.utils.DateUtil;
import org.apache.axis2.dataretrieval.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Literal;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by jason on 2017/5/23.
 */
@Service
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ShopService shopService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private TicketManagerService ticketManagerService;

    @Autowired
    private FcodeMapper fcodeMapper;

    @Autowired
    private LogUserShopMapper logUserShopMapper;

    @Autowired
    private UserTicketMapper userTicketMapper;

    @Autowired
    private TicketTypeMapper ticketTypeMapper;

    @Autowired
    private FollowTicketService followTicketService;

    @Autowired
    private ShopManagerService shopManagerService;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private UserShopMapper userShopMapper;

    @Autowired
    public DateUtil dateUtil;

    public static final Object lock = new Object();

    @Autowired
    private LocaltionCalcService localtionCalcService;

    private Logger logger = LoggerFactory.getLogger(TicketService.class);

    public List<ShopTicketVO> selectByType(Integer industry, Integer type, Integer page,Integer pageSize){
        ShopFilter shopFilter = new ShopFilter();
        shopFilter.setLimit(pageSize);
        shopFilter.setOffset(page);
        shopFilter.setIndustry(industry);
        shopFilter.setIndustry(type);
        return ticketDao.selectByType(shopFilter);
    }

    /**
     * 转发领券
     * @param uid
     * @param ip
     * @return
     */
    public Result forwardTicket(Long uid, Long sid, String ip){
        //验证券
        //根据uid和tid，获取店铺和券的详细信息
//        ShopFilter param = new ShopFilter();
//        param.setTid(tid);
//        TicketWithBLOBs ticket = ticketMapper.selectByTid(param);
//
//        if(ticket != null && ticket.getShopId() > 0){
            //生成fcode
            Fcode fcode = new Fcode();
            fcode.setUid(uid);
            fcode.setSid(sid);
            fcode.setFcode(CommonKeyUtils.fcode(ip));
            fcodeMapper.insertSelective(fcode);
            fcode.setUid(null);
            return ResultHelper.genResultWithSuccess(fcode);
//        }
//        return ResultHelper.genResult(ErrorCodeEnum.NULL_VALUE_ERROR);
    }

    /**
     * 处理用户领取券：关注领取，转发领取，立即领取
     * @param uid
     * @param tid
     * @param status
     * @param fcode 转发码
     * @return
     */
    public Result userCollectTicket(Long uid, String tid, Integer status, String fcode, Long sid){
        return followTicketService.userCollectTicket(uid,tid, status,fcode,sid);
    }

    /**
     * 用户券列表
     * @param page
     * @param pageSize
     * @param uid
     * @param status
     */
    public Result myTicket(Integer page, Integer pageSize, Long uid, Integer status){
        ShopFilter shopFilter = new ShopFilter();
        shopFilter.setUid(uid);
        shopFilter.setLimit(page);

        shopFilter.setOffset(pageSize);
        if(pageSize == null || pageSize <= 0){
            shopFilter.setOffset(PageParam.PAGESIZE_DEFAULT);
        }

        ShopList shopLists = new ShopList();

        List<Ticket> tickets = ticketMapper.myTicket(shopFilter);

        return ResultHelper.genResultWithSuccess(tickets);



//        List<Shop> shopList = null;
//        if(status != null && status > 0){
//            shopFilter.setStatus(status);
//            shopList = shopDao.myTicket(shopFilter);
//        }else if(status != null && status == -23){
//            shopList = shopDao.myTicketNoUse(shopFilter);
//        }else{
//            shopList = shopDao.myTicket(shopFilter);
//        }
//
//        List<Shop> newList = new ArrayList<Shop>();
//        if (shopList != null && shopList.size() > 0){
//            for (Shop shop: shopList) {
//                shop.getUserTicket().setStatusStr(changeUtStatus(shop.getUserTicket().getStatus()));
//                //获取券type信息
//                shop.getTicket().setTicketType(
//                        ticketTypeMapper.selectByPrimaryKey(
//                                Integer.parseInt(shop.getTicket().getTypeId() + "")));
//
//                UserTicket userTicket = new UserTicket();
//                userTicket.setUserId(uid);
//                userTicket.setTicketId(shop.getTicket().getTicketId());
//
//                List<UserTicket> userTickets = userTicketMapper.findByUidTid(userTicket);
//                if(userTickets  != null && userTickets.size() > 0){
//                    shop.getTicket().setUserTicket(userTickets.get(0));
//                }
//                newList.add(shop);
//            }
//
//            shopLists.setShops(newList);
//            shopLists.setTotal(shopDao.myTicketCount(shopFilter));
//            return ResultHelper.genResultWithSuccess(shopLists);
//        }
//        return ResultHelper.genResult(ErrorCodeEnum.NULL_VALUE_ERROR);
    }

    /**
     * 人气推荐
     * @param page
     * @param pageSize
     * @return
     */
    public Result popShopList(Integer page, Integer pageSize){
        BaseFilter baseFilter = new BaseFilter();
        baseFilter.setLimit(page);
        baseFilter.setOffset(pageSize);
        if(pageSize == null || pageSize <= 0){
            baseFilter.setOffset(PageParam.PAGESIZE_DEFAULT);
        }

        List<Shop> shopList = ticketDao.popShopList(baseFilter);
        if(shopList != null && shopList.size() > 0){
            for (Shop shop:shopList) {
                shop.getTicket().setTicketType(
                        ticketTypeMapper.selectByPrimaryKey(
                                Integer.parseInt(shop.getTicket().getTypeId() + "")));
                //获取券type信息
                shop.getTicket().setTicketType(
                        ticketTypeMapper.selectByPrimaryKey(
                                Integer.parseInt(shop.getTicket().getTypeId() + "")));
            }
            return ResultHelper.genResultWithSuccess(shopList);
        }
        return ResultHelper.genResult(ErrorCodeEnum.NULL_VALUE_ERROR);
    }


    public String changeUtStatus(Byte status){
        switch (status){
            case 2:
                return "已领取";
            case 3:
                return "已使用";
            case 4:
                return "已放弃";
        }
        return null;
    }

    /**
     * 用户读取券
     * @param uid
     * @param tid
     * @param status
     * @return
     */
    public ShopTicketVO userReadTicket(Long uid, String tid, Integer status){
        ShopTicketVO shopTicketVO = null;
        ShopFilter shopFilter = new ShopFilter();
        shopFilter.setTid(tid);
        shopFilter.setUid(uid);
        Shop shop = ticketDao.userReadTicket(shopFilter);
        if(shop != null){
            UserTicket userTicket = new UserTicket();
            userTicket.setTicketId(tid);
            userTicket.setUserId(uid);
            shop.getTicket().setIsExpired(isOutOfDate(shop.getTicket().getPeriodOfValidityEndtime()));

            //获取券type信息
            shop.getTicket().setTicketType(
                    ticketTypeMapper.selectByPrimaryKey(
                            Integer.parseInt(shop.getTicket().getTypeId() + "")));

            //获取用户领取券的状态
            List<UserTicket> userTickets = userTicketMapper.findByUidTidNotUtid(userTicket);
            if(userTickets != null && userTickets.size() > 0){
                shop.getTicket().setUserTicket(userTickets.get(0));
            }
            shopTicketVO = new ShopTicketVO();
            TicketVO ticketVO = new TicketVO();
            ticketManagerService.fileTicketVO(ticketVO,shop.getTicket());
            shopTicketVO.setTicketVO(ticketVO);

            shopManagerService.changeStatus2Char(shop);
            shopTicketVO.setShop(shop);
        }
        return shopTicketVO;
    }

    /**
     * 判断券是否过期
     * @param ticketDate
     * @return
     */
    public String isOutOfDate(Date ticketDate){
        try{
            if (dateUtil.daysBetweenSeconds(ticketDate, new Date()) > 0){
                return "已过期";
            }
        }catch (ParseException e){
            return null;
        }
        return null;
    }


    /**
     * 点击店铺，进入该店铺获取店铺下所有的券
     * @param sid
     * @return
     */
    public ShopTicket selectTicketByShopId(Long sid, Long uid, String ip, Integer page, Integer pageSize){
        ShopTicket shopTicket = new ShopTicket();
        try {
            shopTicket.setShop(shopService.selectByPrimaryKey(sid));

            TicketResultVO ticketResultVO = ticketManagerService.showTicketList(sid, uid, page,pageSize, null);
            Tickets tickets = new Tickets();
            tickets.setTickes(ticketResultVO.getTicketVO());
            tickets.setTotal(ticketResultVO.getTotal());

            //获取当前店铺是否已关注
            if (uid != null && uid > 0){
                UserShop userShop = new UserShop();
                userShop.setUserId(uid);
                userShop.setShopId(sid);
                UserShop rs = userShopMapper.selectByUidSid(userShop);
                if (rs != null && rs.getId() > 0){
                    if(rs.getStatus() == DicValue.SHOP_USER_FOCUS){
                        shopTicket.setIsFocus("已关注");
                    }
                }
            }
            shopTicket.setTickets(tickets);
            logUserViewShop(sid,uid,ip);
        }catch(Exception e){
            logger.error(e.getLocalizedMessage());
        }
        return shopTicket;
    }

    /**
     * 用户访问店铺的日志记录
     * @param sid
     * @param uid
     * @param ip
     */
    public void logUserViewShop(Long sid, Long uid, String ip){
        LogUserShop logUserShop = new LogUserShop();
        logUserShop.setIp(ip);
        logUserShop.setSid(sid);
        logUserShop.setUid(uid);
        logUserShopMapper.insertSelective(logUserShop);
        logger.info("[User visit shop log] : " + JSON.toJSONString(logUserShop));
    }

    public List<TicketWithBLOBs> selectByShopId(Long sid){
        return ticketDao.selectTicketByShopId(sid);
    }

    /**
     * 根据用户当前的位置，展示附近的券
     * @param longitude
     * @param latitude
     */
    public ShopResultVO selectList(LocaltionFilter params, double longitude, double latitude, double radius){
        LocaltionFilter filter = localtionCalcService.lbsCalc(params, longitude, latitude, radius);
        searchService.insertKeyword(params.getKeyword());
        ShopResultVO shopResultVO = new ShopResultVO();

        //筛选出方形区域内的店铺
        List<Shop> shopList = shopDao.findByLocation(filter);
        if(shopList != null && shopList.size() > 0){
            List<ShopapiVO> shopVOList = new ArrayList<ShopapiVO>();
            for ( Shop shop : shopList ) {
                ShopapiVO shopVO = new ShopapiVO();

                //获取券type信息
                shop.getTicket().setTicketType(
                        ticketTypeMapper.selectByPrimaryKey(
                                Integer.parseInt(shop.getTicket().getTypeId() + "")));

                //计算店铺与用户位置的距离
                double distance=localtionCalcService.getEarthRadius(
                        new LngAndLatParam(shop.getLongitude(),shop.getDimensionality()),
                        new LngAndLatParam(longitude, latitude));
                if(distance < radius){
                    shop.setTicketTypeStr(commonService.changeTicketType(shop.getTicket().getTypeId()));
                    shopVO.setShop(shop);
                    shopVO.setDistance(distance);
                    shopVOList.add(shopVO);
                }
            }

            //对结果进行排序
//            Collections.sort(shopVOList);
            shopResultVO.setShopVOList(shopVOList);
            shopResultVO.setTotal(shopVOList.size());
            shopResultVO.setUnReadMsg(isUnRead(params.getUid()));
            return shopResultVO;
        }
        return null;
    }

    /**
     * 查询数据库中是否有未读消息
     * @param uid
     * @return
     */
    private boolean isUnRead(Long uid){
        if(uid != null && uid > 0){
            BaseFilter baseFilter = new BaseFilter();
            LogUserShop logUserShop = logUserShopMapper.selectByUid(uid);
            if (logUserShop != null) {
                baseFilter.setUid(uid);
                baseFilter.setToday(logUserShop.getCreateTime());
                Integer count = shopService.ticketUnRead(baseFilter);
                if(count != null && count > 0){
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;
    }

    public Integer unReadCount(Long uid){
        if(uid != null && uid > 0){
            BaseFilter baseFilter = new BaseFilter();
            LogUserShop logUserShop = logUserShopMapper.selectByUid(uid);
            if (logUserShop != null) {
                baseFilter.setUid(uid);
                baseFilter.setToday(logUserShop.getCreateTime());
                return shopService.ticketUnRead(baseFilter);
            }
        }
        return 0;
    }

    /**
     * 展示精选店铺
     */
    public List<Shop> bestShop(Integer limit){
        ShopFilter shopFilter = new ShopFilter();
        shopFilter.setLimit(limit);
        if(limit == null){
            shopFilter.setLimit(PageParam.BEST_SHOP_LIMIT);
        }
        return shopDao.bestShop(shopFilter);
    }
}
