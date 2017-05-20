package com.masou.coupon.common.struct;


import com.masou.coupon.common.dto.BaseDTO;
import com.masou.coupon.common.enums.ErrorCodeEnum;

/**
 * Created by Paul on 2016/11/2.
 */

public class Result<T> extends BaseDTO {
    private static final long serialVersionUID = -4159843077341007128L;
    public int code =0;
    public T data=null;
    public String message="";

    public String userMessage="";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
        this.code = ErrorCodeEnum.OK.getCode();
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, T obj) {
        this.code = code;

        if (code == ErrorCodeEnum.OK.getCode()) {
            this.data = obj;
        } else {
            if (obj != null) {
                this.message = obj.toString();
            } else {
                this.message = "";
            }
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
