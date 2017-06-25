package com.masou.coupon.exception;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.common.enums.ViewResultCodeEnum;
import com.masou.coupon.common.exception.BaseException;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ExceptionsHandler {

    @Autowired
    IPUtil ipUtil;
    Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest req, Exception ex) {

        logger.info("IP:{},URL:{},参数:{}", ipUtil.getIpAddress(req), req.getRequestURI(), JSON.toJSONString(req.getParameterMap()));

        Result result = new Result();
        if (ex instanceof DevException) {
            BaseException e = (BaseException) ex;
            result.setCode(e.getStatus());
            result.setUserMessage(e.getMsg());
        } else if (ex instanceof UserException) {
            BaseException e = (BaseException) ex;
            result.setCode(e.getStatus());
            result.setUserMessage(e.getMsg());

        } else {
            result.setCode(ViewResultCodeEnum.UNKNOWN_EXCEPTION.getCode());
            result.setUserMessage(ex.toString());
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append(ex.toString()).append("\n");
        for (int i = 0; i < ex.getStackTrace().length; i++) {
            buffer.append(ex.getStackTrace()[i].toString()).append("\n");

        }

        logger.error(buffer.toString());
        ex.printStackTrace();
        return result;
    }
}
