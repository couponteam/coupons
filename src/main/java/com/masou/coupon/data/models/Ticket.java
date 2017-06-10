package com.masou.coupon.data.models;

import java.util.Date;

public class Ticket {
    private Long id;

    private String ticketId;

    private Long shopId;

    private String ticketName;

    private String content;

    private Byte _typeId;

    private Date periodOfValidityStarttime;

    private Date periodOfValidityEndtime;

    private String comment;

    private Byte _status;

    private Byte collectingMethod;

    private Byte _isRetaken;

    private Byte _isReUse;

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

    public Byte get_status() {

        return _status;
    }

    public void set_status(Byte _status) {
        this._status = _status;
    }

    public Byte getCollectingMethod() {
        return collectingMethod;
    }

    public void setCollectingMethod(Byte collectingMethod) {
        this.collectingMethod = collectingMethod;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte get_typeId() {
        return _typeId;
    }

    public void set_typeId(Byte _typeId) {
        this._typeId = _typeId;
    }

    public Byte get_isRetaken() {
        return _isRetaken;
    }

    public void set_isRetaken(Byte _isRetaken) {
        this._isRetaken = _isRetaken;
    }

    public Byte get_isReUse() {
        return _isReUse;
    }

    public void set_isReUse(Byte _isReUse) {
        this._isReUse = _isReUse;
    }
}