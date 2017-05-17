package com.masou.coupon.dao;

import com.masou.coupon.data.filter.UserFilter;
import com.masou.coupon.data.mappers.UserMapper;
import com.masou.coupon.data.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Paul on 2017/5/3.
 */
@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }


    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }


    public List<User> selectListByFilter(UserFilter filter) {
        return userMapper.selectListByFilter(filter);
    }

    public int selectCountByFilter(UserFilter filter) {
        return userMapper.selectCountByFilter(filter);
    }

    public User selectByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }


    public User selectByInviteCode(String inviteCode) {
        return userMapper.selectByInviteCode(inviteCode);
    }

}
