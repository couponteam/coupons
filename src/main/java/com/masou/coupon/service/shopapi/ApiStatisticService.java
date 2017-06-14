package com.masou.coupon.service.shopapi;

import com.masou.coupon.action.shopapi.vo.StatisticVO;
import com.masou.coupon.action.shopapi.vo.TicketStstcVO;
import com.masou.coupon.dao.ShopApiDao.ApiStatisticDao;
import com.masou.coupon.data.filter.StatisticFilter;
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

    public StatisticVO statistic(Long uid, Date fromData, Date toData, Long sid){
        StatisticVO statisticVO = new StatisticVO();
        StatisticFilter statisticFilter = new StatisticFilter();
        statisticFilter.setUid(uid);
        statisticFilter.setSid(sid);
        statisticFilter.setFromData(fromData);
        statisticFilter.setToData(toData);

        //获取关注数
        statisticVO.setFollowersToday(apiStatisticDao.followersToday(statisticFilter));
        statisticVO.setFollowersTotal(apiStatisticDao.followersTotal(statisticFilter));

        //获取券领取情况
//        statisticVO.setTicket(ticketCount());
        statisticVO.setTicketToday(apiStatisticDao.ticketToday(statisticFilter));
        statisticVO.setPageViewTotal(apiStatisticDao.ticketTotal(statisticFilter));

        //获取店铺浏览量
        statisticVO.setPageViewToday(apiStatisticDao.pageViewToday(statisticFilter));
        statisticVO.setPageViewTotal(apiStatisticDao.pageViewTotal(statisticFilter));
        return statisticVO;
    }


    private TicketStstcVO ticketCount(){

        return null;
    }

}
