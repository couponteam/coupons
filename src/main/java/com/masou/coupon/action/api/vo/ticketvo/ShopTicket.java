package com.masou.coupon.action.api.vo.ticketvo;

import com.masou.coupon.data.models.Shop;

/**
 * Created by jason on 2017/6/16.
 */
public class ShopTicket {

    private Shop shop;

    private Tickets tickets;

    private String isFocus;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Tickets getTickets() {
        return tickets;
    }

    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
    }

    public String getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(String isFocus) {
        this.isFocus = isFocus;
    }
}
