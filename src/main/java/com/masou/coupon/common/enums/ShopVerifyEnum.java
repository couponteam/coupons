package com.masou.coupon.common.enums;

/**
 * Created by Paul on 2017/5/17.
 */
public enum ShopVerifyEnum {

    PRE_VERIFY(0, "待审核"),
    VERIFIED(1, "已通过"),
    UN_PASSED(2, "未通过");
//    PASSED(3, "已通过"),;


    private int code;

    private String msg;

    ShopVerifyEnum(int role, String msg) {
        this.code = role;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
