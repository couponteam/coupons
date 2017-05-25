package com.masou.coupon.common.enums;

/**
 * Created by Paul on 2017/2/23.
 */
public enum ErrorCodeEnum {
    UNKNOW(-1, "未知错误"),
    OK(0, "成功"),

    REGISTER_FAILED(10001, "注册失败"),

    WRONG_VERIFY(10002, "验证码错误"),
    LOGIN_FAILED(10003, "用户名或密码错误"),
    CAN_NOT_SEND_MESSAGE(10004, "短信发送失败"),
    CERTIFICATION_FAILED(10005, "实名信息校验失败"),
    NOT_LOGIN(10006, "用户未登录"),
    NOT_REGISTER(10007, "用户未注册"),
    OLD_PASSWORD_WRONG(10008, "原密码错误"),
    PHONE_EXIST(10009, "手机号已存在"),
    PHONE_NOT_EXIST(10010, "用户不存在"),
    MANAGER_NOT_EXIST(10011, "管理员不存在"),
    DENY_USER(10012, "用户被禁止登陆"),
    WRONG_CAPTCHA(10013, "图形验证码错误"),
    MISSING_CAPTCHA(10014, "需要图形验证码，但用户未提交"),
    WRONG_SIGN(10015, "签名错误"),
    TOKEN_INVALID(10016,"Token失效"),



    TICKET_INSERT_FAILED(10030, "新增券失败"),
    TICKET_SELECT_FAILED(10031, "查询券失败"),
    TICKET_UPDATE_FAILED(10032, "更新券失败"),
    TICKET_DELETE_FAILED(10033, "删除券失败"),
    TICKET_INSERT_EMPTY(10034, "输入内容为空"),
    TICKET_INSERT_EMPTY_ID(10035, "店铺ID为空"),

    SHOP_REGIS_FAILED(10050, "注册店铺失败"),
    SHOP_UPDATE_FAILED(10051, "更新店铺失败"),
    SHOP_DELETE_FAILED(10052, "删除店铺失败"),

    SEARCH_FAILED(10080,"查询失败"),
    SEARCH_EMPTY_RESULT(10081, "暂无数据"),
    SEARCH_HOTWORD_FAILED(10082, "暂无数据"),



    DIC_FAIL(10050, "查询字典表出错"),


    NOT_MANAGER(20000,"权限不够"),


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
