package com.masou.coupon.utils;


import com.masou.coupon.common.exception.BaseException;
import com.masou.coupon.common.struct.Result;

/**
 * 异常工具类
 * 用于传递远程调用的异常
 * Created by paul on 16/12/21.
 */
public class ExceptionUtils {

    public static BaseException genWithEnum(EnumMsg<Integer> enumMsg) {
        return new BaseException(enumMsg.getCode(), enumMsg.getMsg());
    }

    public static BaseException genWithResult(Result result) {
        if(result != null) {
            return new BaseException(result.getCode(), result.getMessage());
        } else {
            return new BaseException(-1, "invoke hessian error");
        }
    }
}
