package com.masou.coupon.action.api.vo;

import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.TicketType;

import java.util.List;

/**
 * Created by jason on 2017/6/10.
 */
public class ShopVO {

    private Shop shop;

    private List<TicketVO> ticketVO;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<TicketVO> getTicketVO() {
        return ticketVO;
    }

    public void setTicketVO(List<TicketVO> ticketVO) {
        this.ticketVO = ticketVO;
    }
}
