package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.District;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DistrictMapper {
    int insert(District record);

    int insertSelective(District record);
}