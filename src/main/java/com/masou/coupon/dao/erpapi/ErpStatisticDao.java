package com.masou.coupon.dao.erpapi;

import com.masou.coupon.data.filter.BaseFilter;
import com.masou.coupon.data.filter.StatisticFilter;
import com.masou.coupon.data.mappers.*;
import com.masou.coupon.data.models.LogUserVisit;
import com.masou.coupon.data.models.UserManager;
import com.masou.coupon.data.models.UserShop;
import com.masou.coupon.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by jason on 2017/6/25.
 */
@Repository
public class ErpStatisticDao {

    @Autowired
    private UserShopMapper userShopMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LogUserVisitMapper logUserVisitMapper;

    @Autowired
    private UserTicketMapper userTicketMapper;

    @Autowired
    private UserManagerMapper userManagerMapper;

    public Integer memberCount(BaseFilter baseFilter){
        Integer count = userMapper.memberCount(baseFilter);
        if (count != null)
            return count;
        return 0;
    }

    public Integer shopCount(BaseFilter baseFilter){
        Integer count = shopMapper.shopCount(baseFilter);
        if(count != null)
            return count;
        return 0;
    }

    public Integer ticketCount(StatisticFilter statisticFilter){
//        StatisticFilter statisticFilter = (StatisticFilter) baseFilter;
        Integer count = ticketMapper.ticketCount(statisticFilter);
        if(count != null && count > 0)
            return count;
        return 0;
    }

    public Integer ticketTaken(){
        Integer count = userTicketMapper.ticketTaken();
        if(count != null)
            return count;
        return 0;
    }

    public Integer webStatisticPV(BaseFilter baseFilter){
        Integer count = logUserVisitMapper.webStatisticPV(baseFilter);
        if (count != null)
            return count;
        return 0;
    }

    public Integer webStatisticUV(BaseFilter baseFilter){
        Integer count = logUserVisitMapper.webStatisticUV(baseFilter);
        if (count != null)
            return count;
        return 0;
    }

    public List<UserManager> memberList(BaseFilter baseFilter){
        return userManagerMapper.memberList(baseFilter);
    };

}
