package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.Industry;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IndustryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Industry record);

    int insertSelective(Industry record);

    Industry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Industry record);

    int updateByPrimaryKey(Industry record);
}