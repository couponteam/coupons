package com.masou.coupon.constant.enums;

/**
 * Created by Paul on 2017/2/23.
 */
public enum ErrorCodeEnum {
    UNKNOW(-1, "未知错误"),
    OK(0, "成功"),

    REGISTER_FAILED(1, "注册失败"),

    WRONG_VERIFY(2, "验证码错误"),
    LOGIN_FAILED(3, "用户名或密码错误"),
    CAN_NOT_SEND_MESSAGE(4, "短信发送失败"),
    CERTIFICATION_FAILED(5, "实名信息校验失败"),
    NOT_LOGIN(6, "用户未登录"),
    NOT_REGISTER(7, "用户未注册"),
    OLD_PASSWORD_WRONG(8, "原密码错误"),
    PHONE_EXIST(9, "手机号已存在"),
    PHONE_NOT_EXIST(10, "用户不存在"),
    MULT_ORDER_ERROR(11, ""),
    DENY_USER(12, "用户被禁止登陆"),
    WRONG_CAPTCHA(13, "图形验证码错误"),
    MISSING_CAPTCHA(14, "需要图形验证码，但用户未提交"),
    WRONG_SIGN(15, "签名错误"),
    TOKEN_INVALID(16,"Token失效"),







    NOT_MANAGER(2002,"权限不够"),


    SYS_ERROR(10000, "系统错误");



    int code;
    String msg;

    ErrorCodeEnum(int code, String msg) {
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
