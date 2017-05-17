package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.City;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
}