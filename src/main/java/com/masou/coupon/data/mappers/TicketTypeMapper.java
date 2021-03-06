package com.masou.coupon.data.mappers;

import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.models.TicketType;
import com.masou.coupon.data.models.TicketWithBLOBs;
import com.masou.coupon.data.models.UserTicket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TicketTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TicketType record);

    int insertSelective(TicketType record);

    TicketType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TicketType record);

    int updateByPrimaryKey(TicketType record);

    List<TicketType> selectList();

    List<TicketWithBLOBs> selectShopTicketType(ShopFilter filter);

}