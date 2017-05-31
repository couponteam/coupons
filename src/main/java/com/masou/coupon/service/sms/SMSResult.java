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
}
