package com.masou.coupon.dao.api;

import com.masou.coupon.action.param.PageParam;
import com.masou.coupon.data.mappers.TicketMapper;
import com.masou.coupon.data.models.Ticket;
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


    public List<Ticket> selectByType(Integer type, PageParam param){
        return ticketMapper.selectByType(type, param);
    }
}
