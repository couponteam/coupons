package com.masou.coupon.common.enums;

/**
 * Created by Paul on 2017/5/4.
 */
public enum MessageTypeEnum {
    USER_REGISTER(0, "普通用户注册"),
    USER_LOGIN(1, "普通用户登陆"),
    USER_FORGET_PASSWORD(2, "普通用户忘记密码短信"),

    SHOPOWER_REGISTER(3, "店家注册");


    private int code;

    private String msg;

    MessageTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
