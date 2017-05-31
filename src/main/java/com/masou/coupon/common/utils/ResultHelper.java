package com.masou.coupon.common.utils;


import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.exception.BaseException;
import com.masou.coupon.common.struct.Result;

/**
 * Created by paul on 16/12/21.
 */
public class ResultHelper {

    public static Result genResult(Object obj, int retCode, String msg) {
        Result ret = new Result();
        ret.setData(obj);
        ret.setMessage(msg);
        ret.setCode(retCode);
        return ret;
    }


    public static Result genResult(int retCode, String msg) {
        Result ret = new Result();
        ret.setMessage(msg);
        ret.setCode(retCode);
        return ret;
    }

    public static Result genResult(ErrorCodeEnum errorCodeEnum){
        Result ret = new Result();
        ret.setMessage(errorCodeEnum.getMsg());
        ret.setCode(errorCodeEnum.getCode());
        return ret;
    }

    public static Result genResult(Object obj, int retCode, String msg, String userMessage) {
        Result ret = new Result();
        ret.setData(obj);
        ret.setMessage(msg);
        ret.setCode(retCode);
        ret.setUserMessage(userMessage);
        return ret;
    }

    public static Result genMessageResult(Object obj, int code, String userMessage) {
        Result ret = new Result();
        ret.setData(obj);
        ret.setUserMessage(userMessage);
        ret.setCode(code);
        return ret;
    }

    /**
     * 获取result中的Data属性
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> T getData(Result<T> result) {
        if (result == null) {
            throw new BaseException(-1, "Network is error");
        } else {
            if (result.getCode() != 0) {
                throw new BaseException(-1, String.format("invoke hessian error! status:%s; msg:%s", result.getCode(), result.getMessage()));
            } else {
                return (T) result.getData();
            }
        }
    }


    public static Result genResultWithSuccess(Object obj) {
        return genResult(obj, 0, "");
    }

    public static Result genResultWithSuccess() {
        return genResult(0, "");
    }

    public static Result genResultWithSuccessMessage(Object obj, String message) {

        return genMessageResult(obj, 0, message);
    }

    public static Result genResultWithSuccessMessage(String message) {
        return genMessageResult(null, 0, message);
    }


}
