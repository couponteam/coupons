package com.masou.coupon.dao;

import com.masou.coupon.data.mappers.UserProfileMapper;
import com.masou.coupon.data.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Paul on 2017/5/3.
 */
@Repository
public class UserProfileDao {

    @Autowired
    private UserProfileMapper userProfileMapper;


    public int deleteByPrimaryKey(Integer id){
        return userProfileMapper.deleteByPrimaryKey(id);
    }


    public int insertSelective(UserProfile record){
        return userProfileMapper.insertSelective(record);
    }

    public  UserProfile selectByPrimaryKey(Integer id){
        return userProfileMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(UserProfile record){
        return userProfileMapper.updateByPrimaryKeySelective(record);
    }


    public UserProfile selectByUid(String uid){
        return userProfileMapper.selectByUid(uid);
    }

    public UserProfile selectByPhone(String phone){
        return userProfileMapper.selectByPhone(phone);
    }
}
