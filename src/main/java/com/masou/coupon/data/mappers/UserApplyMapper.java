package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.UserApply;

import java.util.List;

public interface UserApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserApply record);

    int insertSelective(UserApply record);

    UserApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserApply record);

    int updateByPrimaryKey(UserApply record);

    List<UserApply> applylist(Byte status);
}