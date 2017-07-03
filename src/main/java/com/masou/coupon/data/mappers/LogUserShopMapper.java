package com.masou.coupon.data.mappers;


import com.masou.coupon.data.filter.StatisticFilter;
import com.masou.coupon.data.models.LogUserShop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogUserShopMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LogUserShop record);

    int insertSelective(LogUserShop record);

    LogUserShop selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LogUserShop record);

    int updateByPrimaryKey(LogUserShop record);

    Integer pageView(StatisticFilter statisticFilter);

    LogUserShop selectByUid(Long uid);
}