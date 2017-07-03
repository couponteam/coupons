package com.masou.coupon.data.mappers;

import com.masou.coupon.data.filter.BaseFilter;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.models.UserTicket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserTicketMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserTicket record);

    int insertSelective(UserTicket record);

    List<UserTicket> findByUidTid(UserTicket record);

    List<UserTicket> findByUidTidNotUtid(UserTicket record);

    UserTicket selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserTicket record);

    int updateByPrimaryKey(UserTicket record);

    int updateByUtId(UserTicket userTicket);

    Integer ticketTaken();

    Integer ticketUnRead(BaseFilter baseFilter);

    List<UserTicket> ticketBeenTakenAndUsed(ShopFilter shopFilter);
}