package com.masou.coupon.dao;

import com.masou.coupon.data.mappers.UserManagerMapper;
import com.masou.coupon.data.models.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Paul on 2017/5/11.
 */
@Repository
public class UserManageDao {
    @Autowired
    private UserManagerMapper userManagerMapper;


    public int deleteByPrimaryKey(Integer id) {
        return userManagerMapper.deleteByPrimaryKey(id);
    }


    public int insertSelective(UserManager record) {
        return userManagerMapper.insertSelective(record);
    }

    public int updateByPrimaryKeySelective(UserManager record) {
        return userManagerMapper.updateByPrimaryKeySelective(record);
    }

    public List<UserManager> selectAllList() {

        return userManagerMapper.selectAllList();
    }


    public UserManager selectByPhone(String phone){
        return userManagerMapper.selectByPhone(phone);
    }


    public UserManager selectByUid(Long uid){
        return userManagerMapper.selectByUid(uid);
    }

}
