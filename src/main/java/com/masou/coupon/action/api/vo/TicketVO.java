package com.masou.coupon.action.api.vo;

import com.masou.coupon.data.models.TicketType;
import com.masou.coupon.data.models.TicketWithBLOBs;

/**
 * Created by jason on 2017/6/9.
 */
public class TicketVO {

    private TicketWithBLOBs ticketWithBLOBs;

    private TicketType ticketType;

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public TicketWithBLOBs getTicketWithBLOBs() {
        return ticketWithBLOBs;
    }

    public void setTicketWithBLOBs(TicketWithBLOBs ticketWithBLOBs) {
        this.ticketWithBLOBs = ticketWithBLOBs;
    }
}
