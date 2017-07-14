package com.masou.coupon.action.api.vo;

import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.TicketWithBLOBs;

import java.util.List;

/**
 * Created by jason on 2017/6/8.
 */
public class ShopTicketListVO {

    private Shop shop;

    private List<TicketWithBLOBs> ticketWithBLOBsList;

    private Integer total;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<TicketWithBLOBs> getTicketWithBLOBsList() {
        return ticketWithBLOBsList;
    }

    public void setTicketWithBLOBsList(List<TicketWithBLOBs> ticketWithBLOBsList) {
        this.ticketWithBLOBsList = ticketWithBLOBsList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
