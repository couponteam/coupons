package com.masou.coupon.data.mappers;

import com.masou.coupon.data.filter.UserFilter;
import com.masou.coupon.data.models.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);


    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);



    List<User> selectListByFilter(UserFilter filter);

    int selectCountByFilter(UserFilter filter);

    User selectByPhone(String phone);


    User selectByInviteCode(@Param("inviteCode") String inviteCode);
}