package com.masou.coupon.service;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.dao.UserLogDao;
import com.masou.coupon.data.models.LogUserVisit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 2017/6/12.
 */
@Service
public class UserLogService {

//    @Autowired
//    private UserLogDao userLogDao;

    private Logger logger = LoggerFactory.getLogger(UserLogService.class);

    public int insertLog(LogUserVisit logUserVisit){
//        logger.info("写入用户日志：" + JSON.toJSONString(logUserVisit));
//        userLogDao.insertLog(logUserVisit)
        return 0;
    }
}
