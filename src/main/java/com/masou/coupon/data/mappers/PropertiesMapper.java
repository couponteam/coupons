package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.Properties;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PropertiesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Properties record);

    int insertSelective(Properties record);

    Properties selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Properties record);

    int updateByPrimaryKeyWithBLOBs(Properties record);

    int updateByPrimaryKey(Properties record);
}