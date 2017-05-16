package com.masou.coupon.exception;


import com.masou.coupon.common.exception.BaseException;
import com.masou.coupon.constant.enums.ErrorCodeEnum;
import com.masou.coupon.utils.EnumMsg;

/**
 * 显示给用户的异常
 */
public class UserException extends BaseException {
    private ErrorCodeEnum errorCodeEnum;

    public UserException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getCode(), errorCodeEnum.getMsg());

    }

    public UserException(ErrorCodeEnum errorCodeEnum, String msg) {
        super(errorCodeEnum.getCode(), msg);

    }

    public UserException(String msg) {
        super(ErrorCodeEnum.SYS_ERROR.getCode(), msg);
    }

    public UserException() {
        super();
    }

    public UserException(int status) {
        super(status);
    }

    public UserException(int status, String message) {
        super(status, message);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException(int status, String message, Throwable cause) {
        super(status, message, cause);
    }

    public UserException(EnumMsg<Integer> enumMsg) {
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
