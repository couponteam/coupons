/**
 * Copyright (c) 2016, 59store. All rights reserved.
 */
package com.masou.coupon.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 16/1/13
 * @since 1.0
 */
public class ServletRequestUtil {

    public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

//    public static Map<String, List<String>> getQueryParams() {
//        Map<String, List<String>> qp = RequestContext.getCurrentContext().getRequestQueryParams();
//        if (qp == null) {
//            qp = new HashMap<String, List<String>>();
//            RequestContext.getCurrentContext().setRequestQueryParams(qp);
//        }
//
//        return qp;
//    }

}
