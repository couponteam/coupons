package com.masou.coupon.action.api.vo;

import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.TicketWithBLOBs;

import java.util.Date;
import java.util.List;

/**
 * Created by jason on 2017/6/1.
 */
public class ShopTicketVO {

    private TicketWithBLOBs ticket;

    private Shop shop;

        public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public TicketWithBLOBs getTicket() {
        return ticket;
    }

    public void setTicket(TicketWithBLOBs ticket) {
        this.ticket = ticket;
    }
}
