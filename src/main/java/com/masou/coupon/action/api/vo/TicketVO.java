package com.masou.coupon.action.api.vo;

import com.masou.coupon.data.models.TicketType;
import com.masou.coupon.data.models.TicketWithBLOBs;

/**
 * Created by jason on 2017/6/9.
 */
public class TicketVO {

    private TicketWithBLOBs ticket;

    private String typeId;

    private String status;

    private String isRetaken;

    private String isReUse;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsRetaken() {
        return isRetaken;
    }

    public void setIsRetaken(String isRetaken) {
        this.isRetaken = isRetaken;
    }

    public String getIsReUse() {
        return isReUse;
    }

    public void setIsReUse(String isReUse) {
        this.isReUse = isReUse;
    }

    public TicketWithBLOBs getTicket() {
        return ticket;
    }

    public void setTicket(TicketWithBLOBs ticket) {
        this.ticket = ticket;
    }
}
