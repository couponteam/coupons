package com.masou.coupon.data.mappers;

import com.masou.coupon.data.models.TicketType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TicketTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TicketType record);

    int insertSelective(TicketType record);

    TicketType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TicketType record);

    int updateByPrimaryKey(TicketType record);

    List<TicketType> selectList();

}