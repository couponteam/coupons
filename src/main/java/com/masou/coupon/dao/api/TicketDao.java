package com.masou.coupon.dao.api;

import com.masou.coupon.action.api.vo.ShopTicketVO;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.mappers.TicketMapper;
import com.masou.coupon.data.mappers.UserTicketMapper;
import com.masou.coupon.data.models.TicketWithBLOBs;
import com.masou.coupon.data.models.UserTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jason on 2017/5/26.
 */
@Repository
public class TicketDao {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private UserTicketMapper userTicketMapper;

    @Autowired
    private ShopMapper shopMapper;

    /**
     * 根据行业类型，券类型，获取店铺和券信息
     * @return 店铺和券的信息
     */
    public List<ShopTicketVO> selectByType(ShopFilter shopFilter){
        return shopMapper.selectByType(shopFilter);
    }

    public List<TicketWithBLOBs> selectTicketByShopId(Long sid){
        ShopFilter shopFilter = new ShopFilter();
        shopFilter.setSid(sid);
        return ticketMapper.selectByShopId(shopFilter);
    }

    public List<TicketWithBLOBs> selectByShopId(ShopFilter shopFilter){
        return ticketMapper.selectByShopId(shopFilter);
    }

    public int userCollectTicket(UserTicket record){
        return userTicketMapper.insertSelective(record);
    }

}
