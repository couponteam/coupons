package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    /** 根据省份id查询城市id */
    List<City> selectByProvinceKey(Integer provinceId);
}