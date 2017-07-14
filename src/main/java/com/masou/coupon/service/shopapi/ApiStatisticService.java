package com.masou.coupon.service.shopapi;

import com.masou.coupon.action.shopapi.vo.StatisticVO;
import com.masou.coupon.action.shopapi.vo.TicketStstcVO;
import com.masou.coupon.dao.ShopApiDao.ApiStatisticDao;
import com.masou.coupon.data.filter.StatisticFilter;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by jason on 2017/6/13.
 */
@Service
public class ApiStatisticService {

    @Autowired
    private ApiStatisticDao apiStatisticDao;

    @Autowired
    private DateUtil dateUtil;

    private Logger logger = LoggerFactory.getLogger(ApiStatisticService.class);

    public StatisticVO statistic(Long uid, String fromData, String toData, Long sid){
        if(uid == null || uid <= 0){
            throw new UserException("无数据");
        }
        StatisticVO statisticVO = new StatisticVO();
        StatisticFilter statisticFilter = new StatisticFilter();
        statisticFilter.setUid(uid);
        statisticFilter.setSid(sid);
        try{
            statisticFilter.setFromData(dateUtil.toDate(fromData,1));
            statisticFilter.setToData(dateUtil.toDate(toData,1));
        }catch (Exception e){
            logger.error("时间格式转换错误："+e.getLocalizedMessage());
        }

        //获取关注数
        statisticVO.setFollowersToday(apiStatisticDao.followersToday(statisticFilter));
        statisticVO.setFollowersTotal(apiStatisticDao.followersTotal(statisticFilter));

        //获取券领取情况
        statisticVO.setTicketToday(apiStatisticDao.ticketToday(statisticFilter));
        statisticVO.setTicketTotal(apiStatisticDao.ticketTotal(statisticFilter));

        //获取店铺浏览量
        statisticVO.setPageViewToday(apiStatisticDao.pageViewToday(statisticFilter));
        statisticVO.setPageViewTotal(apiStatisticDao.pageViewTotal(statisticFilter));
        return statisticVO;
    }


    private TicketStstcVO ticketCount(){

        return null;
    }

}
