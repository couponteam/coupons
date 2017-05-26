package com.masou.coupon.data.models;

import java.util.Date;

public class Ticket {
    private Long id;

    private String ticketId;

    private Long shopId;

    private String ticketName;

    private String content;

    private Byte typeId;

    private Date periodOfValidityStarttime;

    private Date periodOfValidityEndtime;

    private String comment;

    private Byte status;

    private Byte collectingMethod;

    private Byte isRetaken;

    private Byte isReUse;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId == null ? null : ticketId.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName == null ? null : ticketName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getTypeId() {
        return typeId;
    }

    public void setTypeId(Byte typeId) {
        this.typeId = typeId;
    }

    public Date getPeriodOfValidityStarttime() {
        return periodOfValidityStarttime;
    }

    public void setPeriodOfValidityStarttime(Date periodOfValidityStarttime) {
        this.periodOfValidityStarttime = periodOfValidityStarttime;
    }

    public Date getPeriodOfValidityEndtime() {
        return periodOfValidityEndtime;
    }

    public void setPeriodOfValidityEndtime(Date periodOfValidityEndtime) {
        this.periodOfValidityEndtime = periodOfValidityEndtime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getCollectingMethod() {
        return collectingMethod;
    }

    public void setCollectingMethod(Byte collectingMethod) {
        this.collectingMethod = collectingMethod;
    }

    public Byte getIsRetaken() {
        return isRetaken;
    }

    public void setIsRetaken(Byte isRetaken) {
        this.isRetaken = isRetaken;
    }

    public Byte getIsReUse() {
        return isReUse;
    }

    public void setIsReUse(Byte isReUse) {
        this.isReUse = isReUse;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}