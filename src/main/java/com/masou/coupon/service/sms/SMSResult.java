package com.masou.coupon.service.sms;

import lombok.Data;

/**
 * Created by Paul on 2017/5/31.
 */
@Data
public class SMSResult {

    private String code;
    private String msg;
    private String msgid;


    @Override
    public String toString() {
        return "SMSResult{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", msgid='" + msgid + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}
