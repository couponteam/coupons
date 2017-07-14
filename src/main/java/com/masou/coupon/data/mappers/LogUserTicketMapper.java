package com.masou.coupon.data.mappers;

import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.models.LogUserTicket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogUserTicketMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LogUserTicket record);

    int insertSelective(LogUserTicket record);

    LogUserTicket selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LogUserTicket record);

    int updateByPrimaryKey(LogUserTicket record);

    int checkUnreadTicket(ShopFilter shopFilter);
}