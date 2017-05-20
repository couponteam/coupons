package com.masou.coupon.common.enums;

/**
 * Created by Paul on 2017/5/20.
 */
public enum ShopOwnerTypeEnum {


    CHIEF_MANAGER((byte)0, "最高管理员"),
    NORMAL_MANAGER((byte)1, "普通管理员"),;


    private byte role;

    private String msg;

    ShopOwnerTypeEnum(byte role, String msg) {
        this.role = role;
        this.msg = msg;
    }

    public byte getRole() {
        return role;
    }

    public String getMsg() {
        return msg;
    }
}
