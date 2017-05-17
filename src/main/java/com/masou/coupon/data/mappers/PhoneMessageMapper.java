package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.PhoneMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PhoneMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhoneMessage record);

    int insertSelective(PhoneMessage record);

    PhoneMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhoneMessage record);

    int updateByPrimaryKey(PhoneMessage record);


    int selectTodayMessageCount(String phone);


    PhoneMessage selectLastByType(@Param("phone") String phone,
                                  @Param("type") Integer type);
}