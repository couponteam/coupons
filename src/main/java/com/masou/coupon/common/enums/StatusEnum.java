package com.masou.coupon.common.enums;

/**
 * Created by jason on 2017/7/5.
 */
public enum StatusEnum {

    //券领取方式相关
    PICKUP_RIGHT_NOW(1,"立即领取"),
    PICKUP_FORWARD(2, "转发领取"),
    PICKUP_FOLLOW(3, "关注领取"),

    //用户领取券状态相关
    TICKET_STATUS_READ(1,"已读"),
    TICKET_STATUS_GOT(2, "已领取"),
    TICKET_STATUS_USED(3, "已使用"),
    TICKET_STATUS_GIVEUP(4, "已放弃"),

    //店铺券状态
    SHOP_TICKET_PUTAWAY(1, "上架"),
    SHOP_TICKET_EXPIRED(2, "过期"),
    SHOP_TICKET_SELDOUT(3, "下架"),

    //店铺相关
    SHOP_USER_FOCUS(1, "已关注"),
    SHOP_USER_UNFOCUS(2, "取消关注"),

    //店铺审核状态
    APPLY_SHOP_DOING(0,"审核中"),
    APPLY_SHOP_PASS(1, "审核通过"),
    APPLY_SHOP_REJECT(2, "审核未通过"),
    ;

    int status;
    String comment;

    StatusEnum(int status, String comment){
        this.status = status;
        this.comment = comment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
