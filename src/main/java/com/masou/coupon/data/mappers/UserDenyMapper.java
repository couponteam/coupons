package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.UserDeny;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDenyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDeny record);

    int insertSelective(UserDeny record);

    UserDeny selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDeny record);

    int updateByPrimaryKey(UserDeny record);

    UserDeny selectByPhone(@Param("phone") String phone);

    List<UserDeny> selectAllList();
}