package com.masou.coupon.dao;

import com.masou.coupon.data.mappers.PhoneMessageMapper;
import com.masou.coupon.data.models.PhoneMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Paul on 2017/5/2.
 */
@Repository
public class PhoneMessageDao {

    @Autowired
    private PhoneMessageMapper phoneMessageMapper;


    public int insertSelective(PhoneMessage record) {
        return phoneMessageMapper.insertSelective(record);
    }

    public PhoneMessage selectByPrimaryKey(Integer id) {
        return phoneMessageMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(PhoneMessage record) {
        return phoneMessageMapper.updateByPrimaryKeySelective(record);
    }

    public int selectTodayMessageCount(String phone) {
        return phoneMessageMapper.selectTodayMessageCount(phone);
    }

    public PhoneMessage selectLastByType(String phone, Integer type){
        return phoneMessageMapper.selectLastByType(phone,type);
    }

}
