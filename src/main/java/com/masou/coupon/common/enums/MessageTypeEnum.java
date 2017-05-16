package com.masou.coupon.common.enums;

/**
 * Created by Paul on 2017/5/4.
 */
public enum MessageTypeEnum {
    REGISTER(0, "注册"),
    LOGIN(1, "登陆"),
    FORGET_PASSWORD(2, "忘记密码短信"),

    ;


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
