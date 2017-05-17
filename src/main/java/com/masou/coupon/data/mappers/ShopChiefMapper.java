package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.ShopChief;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopChiefMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopChief record);

    int insertSelective(ShopChief record);

    ShopChief selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopChief record);

    int updateByPrimaryKey(ShopChief record);
}