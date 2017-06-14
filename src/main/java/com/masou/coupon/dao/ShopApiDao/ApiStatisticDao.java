package com.masou.coupon.dao.ShopApiDao;

import com.masou.coupon.data.filter.StatisticFilter;
import com.masou.coupon.data.mappers.LogUserShopMapper;
import com.masou.coupon.data.mappers.TicketMapper;
import com.masou.coupon.data.mappers.UserShopMapper;
import com.masou.coupon.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by jason on 2017/6/13.
 */
@Repository
public class ApiStatisticDao {

    @Autowired
    private UserShopMapper userShopMapper;

    @Autowired
    private LogUserShopMapper logUserShopMapper;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private TicketMapper ticketMapper;

    /**
     * 店铺总关注数
     * @param statisticFilter
     */
    public int followersTotal(StatisticFilter statisticFilter){
        return userShopMapper.followers(statisticFilter);
    }

    /**
     * 店铺当天新增
     * @param statisticFilter
     */
    public int followersToday(StatisticFilter statisticFilter){
        StatisticFilter sclone = null;
        try {
            sclone = (StatisticFilter)statisticFilter.clone();
            statisticFilter.setToday(dateUtil.todayOnlyDate());
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return userShopMapper.followers(statisticFilter);
    }

    /**
     * 总浏览量
     * @param statisticFilter
     * @return
     */
    public int pageViewTotal(StatisticFilter statisticFilter){
        return logUserShopMapper.pageView(statisticFilter);
    }

    /**
     * 当天浏览量
     * @param statisticFilter
     * @return
     */
    public int pageViewToday(StatisticFilter statisticFilter){
        StatisticFilter sclone = null;
        try {
            sclone = (StatisticFilter)statisticFilter.clone();
            statisticFilter.setToday(dateUtil.todayOnlyDate());
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return logUserShopMapper.pageView(statisticFilter);
    }

    /**
     * 券领取总数
     * @param statisticFilter
     * @return
     */
    public int ticketTotal(StatisticFilter statisticFilter){
        return logUserShopMapper.pageView(statisticFilter);
    }

    /**
     * 当天领取总数
     * @param statisticFilter
     * @return
     */
    public int ticketToday(StatisticFilter statisticFilter){
        StatisticFilter sclone = null;
        try {
            sclone = (StatisticFilter)statisticFilter.clone();
            statisticFilter.setToday(dateUtil.todayOnlyDate());
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return logUserShopMapper.pageView(statisticFilter);
    }


}
