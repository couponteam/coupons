package com.masou.coupon.utils;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.constant.enums.ErrorCodeEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Paul on 2017/3/25.
 */
public class ServletResponseUtil {

    public static void sendResponse(HttpServletResponse response,ErrorCodeEnum errorCodeEnum) {
        Result result = new Result(errorCodeEnum.getCode());
        result.setUserMessage(errorCodeEnum.getMsg());
//            result.setData(token);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
            //
        }
    }

    public static void sendResponse(HttpServletResponse response,String msg,int code) {
        Result result = new Result(code);
        result.setUserMessage(msg);
//            result.setData(token);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
            //
        }
    }
}
