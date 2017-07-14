package com.masou.coupon.common.enums;

/**
 * Created by Paul on 2017/5/17.
 */
public enum RoleEnum {

    USER(0, "普通用户"),
    SHOP_OWNER(1, "店铺管理员"),
    MANAGER(2, "系统管理员"),;


    private int role;

    private String msg;

    RoleEnum(int role, String msg) {
        this.role = role;
        this.msg = msg;
    }

    public int getRole() {
        return role;
    }

    public String getMsg() {
        return msg;
    }
}
