package com.masou.coupon.interceptor;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by july on 2017/1/13.
 */
@Order(90)
public class CheckSignInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(CheckSignInterceptor.class.getName());

    private MD5Util md5Util;

    private final String PROTOCOL_VERSION_KEY = "protocol_version";
    private final String SIGN_KEY = "sign";
    private final String secretKey = "ZVmAgXmUfdsa3JWL9sfnklfw17J";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String signValue = request.getParameter(PROTOCOL_VERSION_KEY);
        Boolean result;
        if (signValue == null) {
            signValue = "";
        }
        switch (signValue) {
            case "1.0.0":
                result = check_v1(request);
                break;

            case "1.0.1":
                result = check_v2(request);
                break;
            default:
                result = check_v1(request);
                break;
        }

        if (!result) {
            Result responseBag = new Result(ErrorCodeEnum.WRONG_SIGN.getCode());
            responseBag.setMessage(ErrorCodeEnum.WRONG_SIGN.getMsg());
            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            try {
                response.getWriter().write(JSON.toJSONString(responseBag));
            } catch (IOException e) {
                //
            }
        }

        return result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private Boolean check_v1(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();

        String signValue = request.getParameter(SIGN_KEY);
        if (signValue == null) {
            return false;
        }

        List<String> paramKeys = new ArrayList<>();
        paramKeys.addAll(parameterMap.keySet());
        Collections.sort(paramKeys);
        StringBuilder sb = new StringBuilder();

        for (String key : paramKeys) {
            if (key.equals(SIGN_KEY)) {
                continue;
            }
            String value = request.getParameter(key);
            sb.append(key).append("=").append(value).append("&");
        }
        sb.append(secretKey);

        String resultSignValue = sb.toString();
        String md5Value = md5Util.MD5(resultSignValue);
        log.info(String.format("should: %s, get: %s", md5Value, signValue));
        return signValue.equalsIgnoreCase(md5Value);
    }


    private Boolean check_v2(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();

        String signValue = request.getParameter(SIGN_KEY);
        if (signValue == null) {
            return false;
        }

        List<String> paramKeys = new ArrayList<>();
        paramKeys.addAll(parameterMap.keySet());
        Collections.sort(paramKeys);
        StringBuilder sb = new StringBuilder();

        for (String key : paramKeys) {
            if (key.equals(SIGN_KEY)) {
                continue;
            }
            String value = request.getParameter(key);
            sb.append(key).append("=").append(value).append("&");
        }
        sb.append(secretKey);

        String resultSignValue = sb.toString();
        String md5Value = md5Util.MD5(resultSignValue.getBytes());
        log.info(String.format("should: %s, get: %s", md5Value, signValue));
        return signValue.equalsIgnoreCase(md5Value);
    }

    public CheckSignInterceptor(MD5Util md5Util) {
        this.md5Util = md5Util;
    }
}
