package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.Ticket;
import com.masou.coupon.data.models.TicketWithBLOBs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TicketMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TicketWithBLOBs record);

    int insertSelective(TicketWithBLOBs record);

    TicketWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TicketWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TicketWithBLOBs record);

    int updateByPrimaryKey(Ticket record);

    //根据店铺的id，获取
//    List<TicketWithBLOBs> selectByLimit(sid);
}