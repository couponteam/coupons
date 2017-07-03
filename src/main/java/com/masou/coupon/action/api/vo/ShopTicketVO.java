package com.masou.coupon.action.api.vo;

import com.masou.coupon.action.api.vo.ticketvo.TicketVO;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.TicketWithBLOBs;

import java.util.Date;
import java.util.List;

/**
 * Created by jason on 2017/6/1.
 */
public class ShopTicketVO {

//    private TicketWithBLOBs ticket;
    private TicketVO ticketVO;

    private Shop shop;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public TicketVO getTicketVO() {
        return ticketVO;
    }

    public void setTicketVO(TicketVO ticketVO) {
        this.ticketVO = ticketVO;
    }

    //    public TicketWithBLOBs getTicket() {
//        return ticket;
//    }
//
//    public void setTicket(TicketWithBLOBs ticket) {
//        this.ticket = ticket;
//    }
}
