package com.masou.coupon.data.mappers;


import com.masou.coupon.data.models.LogErpOperate;

public interface LogErpOperateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LogErpOperate record);

    int insertSelective(LogErpOperate record);

    LogErpOperate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LogErpOperate record);

    int updateByPrimaryKeyWithBLOBs(LogErpOperate record);

    int updateByPrimaryKey(LogErpOperate record);
}