package com.masou.coupon.action;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.data.models.Shop;
import org.junit.Test;

/**
 * Created by jason on 2017/5/22.
 */
public class TestTicket {

    @Test
    public void testInsert(){

        Shop s = new Shop();
        s.setId(1);
        s.setShopName("wahaha");


        System.out.println(JSON.toJSONString(s));

    }

//    public void test


}
