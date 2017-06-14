package com.masou.coupon.data.mappers;

import com.masou.coupon.action.api.vo.ShopTicketVO;
import com.masou.coupon.data.filter.BaseFilter;
import com.masou.coupon.data.filter.LocaltionFilter;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.filter.StatisticFilter;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.Ticket;
import com.masou.coupon.data.models.TicketWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    Shop selectByPhone(@Param("phone")String phone);

    List<Shop> selectListByFilter(ShopFilter filter);

    List<ShopTicketVO> selectByType(ShopFilter shopFilter);

    List<Shop> shopList(BaseFilter baseFilter);

    List<Shop> bestShop(ShopFilter shopFilter);

    List<Shop> findByLocation(LocaltionFilter filter);

    Shop shopMD5(String shopMD5);

    List<Shop> findByUid(ShopFilter shopFilter);

    int ticketCount(StatisticFilter statisticFilter);

}