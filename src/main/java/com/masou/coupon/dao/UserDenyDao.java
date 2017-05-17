package com.masou.coupon.dao;

import com.masou.coupon.data.mappers.UserDenyMapper;
import com.masou.coupon.data.models.UserDeny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Paul on 2017/5/11.
 */
@Repository
public class UserDenyDao {

    @Autowired
    private UserDenyMapper userDenyMapper;

    public int deleteByPrimaryKey(Integer id) {
        return userDenyMapper.deleteByPrimaryKey(id);
    }


    public int insertSelective(UserDeny record) {
        return userDenyMapper.insertSelective(record);
    }

    public UserDeny selectByPrimaryKey(Integer id) {

        return userDenyMapper.selectByPrimaryKey(id);
    }

    public UserDeny selectByPhone(String phone){
        return userDenyMapper.selectByPhone(phone);
    }


    public List<UserDeny> selectAllList(){
        return userDenyMapper.selectAllList();
    }
}
