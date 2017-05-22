package com.masou.coupon.common.enums;

/**
 * Created by Paul on 2017/5/4.
 */
public enum MessageTypeEnum {
    USER_REGISTER(0, "用户注册"),
    USER_LOGIN(1, "用户登陆"),
    USER_FORGET_PASSWORD(2, "用户忘记密码短信"),



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
