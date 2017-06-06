package com.masou.coupon.interceptor;


import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.data.models.UserToken;
import com.masou.coupon.service.api.UserTokenService;
import com.masou.coupon.utils.ServletResponseUtil;
import com.masou.coupon.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by july on 2017/1/13.
 */
@Order(98)
public class CheckTokenInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(CheckTokenInterceptor.class.getName());

    @Autowired
    private UserTokenService userTokenService;


    /**
     * @param request
     * @param response
     * @param handler
     * @return true 往下传  false 直接处理
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean check = true;
        String tokenParam = request.getParameter("token");

        UserToken userToken = null;

        //是否有token参数
        if (StringUtil.areNotEmpty(tokenParam)) {
            userToken = userTokenService.getUserToken(tokenParam);
        }

        //是否过期
        if (StringUtil.isEmpty(tokenParam) || userToken == null) {
            ServletResponseUtil.sendResponse(response, ErrorCodeEnum.TOKEN_INVALID);
            check = false;
        }

        return check;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
