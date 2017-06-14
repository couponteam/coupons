package com.masou.coupon.action.shopapi.vo;

import com.masou.coupon.data.models.Ticket;

/**
 * Created by jason on 2017/6/13.
 */
public class TicketStstcVO {

    private Ticket ticket;

    private Integer totalPer;

    private Integer today;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Integer getTotalPer() {
        return totalPer;
    }

    public void setTotalPer(Integer totalPer) {
        this.totalPer = totalPer;
    }

    public Integer getToday() {
        return today;
    }

    public void setToday(Integer today) {
        this.today = today;
    }
}
