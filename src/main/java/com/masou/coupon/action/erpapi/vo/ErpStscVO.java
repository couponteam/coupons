package com.masou.coupon.action.erpapi.vo;


/**
 * Created by jason on 2017/6/24.
 */
public class ErpStscVO {

    private Integer memberTotal;

    private Integer MemberDaily;

    private Integer ticketTotal;

    private Integer ticketDaily;

    private Integer ticketTaken;

    private Integer shopTotal;

    private Integer shopDaily;

    private Integer uv;

    private Integer pv;

    public Integer getMemberTotal() {
        return memberTotal;
    }

    public void setMemberTotal(Integer memberTotal) {
        this.memberTotal = memberTotal;
    }

    public Integer getMemberDaily() {
        return MemberDaily;
    }

    public void setMemberDaily(Integer memberDaily) {
        MemberDaily = memberDaily;
    }

    public Integer getTicketTotal() {
        return ticketTotal;
    }

    public void setTicketTotal(Integer ticketTotal) {
        this.ticketTotal = ticketTotal;
    }

    public Integer getTicketDaily() {
        return ticketDaily;
    }

    public void setTicketDaily(Integer ticketDaily) {
        this.ticketDaily = ticketDaily;
    }

    public Integer getTicketTaken() {
        return ticketTaken;
    }

    public void setTicketTaken(Integer ticketTaken) {
        this.ticketTaken = ticketTaken;
    }

    public Integer getShopTotal() {
        return shopTotal;
    }

    public void setShopTotal(Integer shopTotal) {
        this.shopTotal = shopTotal;
    }

    public Integer getShopDaily() {
        return shopDaily;
    }

    public void setShopDaily(Integer shopDaily) {
        this.shopDaily = shopDaily;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }
}
