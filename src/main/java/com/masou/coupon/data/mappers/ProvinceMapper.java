package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.Province;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);
}