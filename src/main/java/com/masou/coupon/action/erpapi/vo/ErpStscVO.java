package com.masou.coupon.action.erpapi.vo;


/**
 * Created by jason on 2017/6/24.
 */
public class ErpStscVO {

    private Integer memberTotal;

    private Integer MemberDaily;

    private Integer ticketTotal = 0;

    private Integer ticketDaily;

    private Integer ticketTaken;

    private Integer shopTotal;

    private Integer shopDaily;

    private Integer uvTotal;

    private Integer pvTotal;

    private Integer uvToday;

    private Integer pvToday;

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

    public Integer getUvTotal() {
        return uvTotal;
    }

    public void setUvTotal(Integer uvTotal) {
        this.uvTotal = uvTotal;
    }

    public Integer getPvTotal() {
        return pvTotal;
    }

    public void setPvTotal(Integer pvTotal) {
        this.pvTotal = pvTotal;
    }

    public Integer getUvToday() {
        return uvToday;
    }

    public void setUvToday(Integer uvToday) {
        this.uvToday = uvToday;
    }

    public Integer getPvToday() {
        return pvToday;
    }

    public void setPvToday(Integer pvToday) {
        this.pvToday = pvToday;
    }
}
