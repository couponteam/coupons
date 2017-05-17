package com.masou.coupon.exception;


import com.masou.coupon.common.exception.BaseException;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.utils.EnumMsg;

public class DevException extends BaseException {

    public DevException() {
        super();
    }

    public DevException(int status) {
        super(status);
    }

    public DevException(int status, String message) {
        super(status, message);
    }

    public DevException(ErrorCodeEnum errorCodeEnum, String msg) {
        super(errorCodeEnum.getCode(), msg);

    }

    public DevException(Throwable cause) {
        super(cause);
    }

    public DevException(int status, String message, Throwable cause) {
        super(status, message, cause);
    }

    public DevException(EnumMsg<Integer> enumMsg) {
        super(enumMsg.getCode(), enumMsg.getMsg());
    }

    @Override
    public int getStatus() {
        return super.getStatus();
    }

    @Override
    public String getMsg() {
        return super.getMsg();
    }

}
