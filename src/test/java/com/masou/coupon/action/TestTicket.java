package com.masou.coupon.action;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.data.mappers.TicketMapper;
import com.masou.coupon.data.models.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jason on 2017/5/22.
 */
public class TestTicket {

    @Autowired
    private TicketMapper ticketMapper;

    @Test
    public void testInsert(){

        System.out.println(JSON.toJSONString(ticketMapper.selectByPrimaryKey(3L)));

    }

//    public void test


}
