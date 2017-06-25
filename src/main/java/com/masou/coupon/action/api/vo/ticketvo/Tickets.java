package com.masou.coupon.action.api.vo.ticketvo;

import java.util.List;

/**
 * Created by jason on 2017/6/16.
 */
public class Tickets {

    private List<TicketVO> tickes;

    private Integer total;

    public List<TicketVO> getTickes() {
        return tickes;
    }

    public void setTickes(List<TicketVO> tickes) {
        this.tickes = tickes;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
