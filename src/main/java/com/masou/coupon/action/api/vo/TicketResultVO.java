package com.masou.coupon.action.api.vo;

import java.util.List;

/**
 * Created by jason on 2017/6/9.
 */
public class TicketResultVO {

    private List<TicketVO> ticketVO;

    private int total;

    public List<TicketVO> getTicketVO() {
        return ticketVO;
    }

    public void setTicketVO(List<TicketVO> ticketVO) {
        this.ticketVO = ticketVO;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
