package com.masou.coupon.service.api;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.action.api.vo.*;
//import com.masou.coupon.dao.ShopDao;
import com.masou.coupon.dao.api.ShopDao;
import com.masou.coupon.dao.api.TicketDao;
import com.masou.coupon.data.filter.LngAndLatParam;
import com.masou.coupon.data.filter.LocaltionFilter;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.mappers.LogUserShopMapper;
import com.masou.coupon.data.models.LogUserShop;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.TicketWithBLOBs;
import com.masou.coupon.data.models.UserTicket;
import com.masou.coupon.service.LocaltionCalcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private LogUserShopMapper logUserShopMapper;

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

    public  List<Shop> selectByIndustry(Integer industry, Integer page,Integer pageSize){


        return null;
    }

    /**
     * 用户点击查看一条记录，领取一张券，等等的接口
     * @param uid
     * @param tid
     * @param status
     * @return
     */
    public int userCollectTicket(Long uid, Long tid, Integer status){
        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(uid);
        userTicket.setTicketId(tid);
        userTicket.setCreateTime(new Date());
        userTicket.setStatus(Byte.parseByte(status+""));
        return ticketDao.userCollectTicket(userTicket);
    }

    /**
     * 点击店铺，进入该店铺获取店铺下所有的券
     * @param sid
     * @return
     */
    public ShopTicketListVO selectTicketByShopId(Long sid, Long uid, String ip){
        ShopTicketListVO shopTicketListVO = new ShopTicketListVO();
        try {
            shopTicketListVO.setShop(shopService.selectByPrimaryKey(sid));
            shopTicketListVO.setTicketWithBLOBsList(ticketDao.selectTicketByShopId(sid));
            logUserViewShop(sid,uid,ip);
        }catch(Exception e){
            logger.error(e.getLocalizedMessage());
        }
        return shopTicketListVO;
    }

    /**
     * 用户访问店铺的日志记录
     * @param sid
     * @param uid
     * @param ip
     */
    private void logUserViewShop(Long sid, Long uid, String ip){
        LogUserShop logUserShop = new LogUserShop();
        logUserShop.setIp(ip);
        logUserShop.setSid(sid);
        logUserShop.setUid(uid);
        logUserShopMapper.insertSelective(logUserShop);
        logger.info("[User visit shop log] : " + JSON.toJSONString(logUserShop));
    }

   public List<ShopTicketListVO> selectTicketByShopId(Long sid, Long uid){

        return null;
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
                //计算店铺与用户位置的距离
                double distance=localtionCalcService.getEarthRadius(
                        new LngAndLatParam(shop.getLongitude(),shop.getDimensionality()),
                        new LngAndLatParam(longitude, latitude));
                if(distance < radius){
                    shopVO.setShop(shop);
                    shopVO.setDistance(distance);
                    shopVOList.add(shopVO);
                }
            }

            //对结果进行排序
            Collections.sort(shopVOList);
            shopResultVO.setShopVOList(shopVOList);
            shopResultVO.setTotal(shopVOList.size());
            return shopResultVO;
        }
        return null;
    }

    /**
     * 展示精选店铺
     */
    public List<Shop> bestShop(Integer limit){
        ShopFilter shopFilter = new ShopFilter();
        shopFilter.setLimit(limit);
        return shopDao.bestShop(shopFilter);
    }
}
