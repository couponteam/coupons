package com.masou.coupon.data.mappers;

import com.masou.coupon.action.erpapi.vo.ShopTicketVO;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.models.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    Shop selectByPhone(@Param("phone")String phone);

    List<Shop> selectListByFilter(ShopFilter filter);

    List<ShopTicketVO> selectByType(Integer industry, Integer type, Integer page, Integer pageSize);

}