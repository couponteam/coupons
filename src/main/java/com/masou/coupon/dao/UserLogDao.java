package com.masou.coupon.dao;

import com.masou.coupon.data.mappers.LogUserVisitMapper;
import com.masou.coupon.data.models.LogUserVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by jason on 2017/6/12.
 */
@Repository
public class UserLogDao {

    @Autowired
    private LogUserVisitMapper  logUserVisitMapper;

    /**
     * 用户访问日志
     * @param logUserVisit
     * @return
     */
    public int insertLog(LogUserVisit logUserVisit){
        System.out.println("user log dao:");
        return 0;
    }
}
