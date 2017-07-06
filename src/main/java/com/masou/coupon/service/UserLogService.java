package com.masou.coupon.service;

import com.masou.coupon.dao.UserLogDao;
import com.masou.coupon.data.models.LogUserVisit;
import com.masou.coupon.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jason on 2017/6/12.
 */
@Service
public class UserLogService {

    @Autowired
    private UserLogDao userLogDao;

    @Autowired
    private IPUtil ipUtil;

    public int userLogs(HttpServletRequest request){
        LogUserVisit logUserVisit = new LogUserVisit();
        logUserVisit.setIp(ipUtil.getIpAddress(request));
        logUserVisit.setMethod(request.getMethod());
        logUserVisit.setUrl(request.getRequestURI());
        return userLogDao.insertLog(logUserVisit);
    }
}
