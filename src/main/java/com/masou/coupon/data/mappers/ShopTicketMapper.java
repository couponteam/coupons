package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.ShopTicket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopTicketMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopTicket record);

    int insertSelective(ShopTicket record);

    ShopTicket selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopTicket record);

    int updateByPrimaryKey(ShopTicket record);
}