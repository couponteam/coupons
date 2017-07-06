package com.masou.coupon.data.mappers;


import com.masou.coupon.data.models.Fcode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FcodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Fcode record);

    int insertSelective(Fcode record);

    Fcode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Fcode record);

    int updateByPrimaryKey(Fcode record);
}