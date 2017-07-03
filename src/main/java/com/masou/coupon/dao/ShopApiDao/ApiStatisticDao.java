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
    public Integer followersTotal(StatisticFilter statisticFilter){
        Integer count = userShopMapper.followers(statisticFilter);
        if(count != null){
            return count;
        }
        return 0;
    }

    /**
     * 店铺当天新增
     * @param statisticFilter
     */
    public int followersToday(StatisticFilter statisticFilter){
        StatisticFilter sclone = null;
        try {
            sclone = (StatisticFilter)statisticFilter.clone();
            sclone.setToday(dateUtil.todayOnlyDate());
            Integer count = userShopMapper.followers(sclone);
            if(count != null){
                return count;
            }
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 总浏览量
     * @param statisticFilter
     * @return
     */
    public Integer pageViewTotal(StatisticFilter statisticFilter){
        Integer count = logUserShopMapper.pageView(statisticFilter);
        if(count != null ){
            return count;
        }
        return 0;
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
            sclone.setToday(dateUtil.todayOnlyDate());
            Integer count = logUserShopMapper.pageView(sclone);
            if(count != null){
                return count;
            }
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 券领取总数
     * @param statisticFilter
     * @return
     */
    public int ticketTotal(StatisticFilter statisticFilter){
        Integer count = ticketMapper.ticketCount(statisticFilter);
        if(count != null){
            return count;
        }
        return 0;
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
            sclone.setToday(dateUtil.todayOnlyDate());
            Integer count = ticketMapper.ticketCount(sclone);
            if(count != null){
                return count;
            }
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return 0;
    }
}
