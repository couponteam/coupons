package com.masou.coupon.interceptor;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.data.models.LogUserVisit;
import com.masou.coupon.service.UserLogService;
import com.masou.coupon.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by july on 2016/10/27.
 */

public class LogInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(LogInterceptor.class.getName());

    private IPUtil ipUtil;

    @Autowired
    private UserLogService userLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = ipUtil.getIpAddress(request);
        logger.info("IP:{} - URL:{} - Method:{} - 参数:{}",
                ip,
                request.getRequestURI(),
                request.getMethod(),
                JSON.toJSONString(request.getParameterMap()));

        LogUserVisit logUserVisit = new LogUserVisit();
        logUserVisit.setIp(ip);
        logUserVisit.setMethod(request.getMethod());
        logUserVisit.setUrl(request.getRequestURI());
        userLogService.insertLog(logUserVisit);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info(request.getRequestedSessionId());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public LogInterceptor(IPUtil ipUtil) {
        this.ipUtil = ipUtil;
    }
}
