package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.UserTicket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTicketMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserTicket record);

    int insertSelective(UserTicket record);

    UserTicket selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserTicket record);

    int updateByPrimaryKey(UserTicket record);
}