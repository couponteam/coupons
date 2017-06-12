package com.masou.coupon.data.mappers;


import com.masou.coupon.data.models.LogUserVisit;

public interface LogUserVisitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LogUserVisit record);

    int insertSelective(LogUserVisit record);

    LogUserVisit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LogUserVisit record);

    int updateByPrimaryKey(LogUserVisit record);
}