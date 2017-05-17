package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.UserShop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserShopMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserShop record);

    int insertSelective(UserShop record);

    UserShop selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserShop record);

    int updateByPrimaryKey(UserShop record);
}