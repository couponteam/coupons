package com.masou.coupon.data.mappers;

import com.masou.coupon.data.filter.AddressFilter;
import com.masou.coupon.data.models.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper {
    int insert(District record);

    int insertSelective(District record);

    List<District> selectList(AddressFilter ads);
}