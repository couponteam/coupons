package com.masou.coupon.dao;

import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.models.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Paul on 2017/5/20.
 */
@Repository
public class ShopDao {

    @Autowired
    private ShopMapper shopMapper;


    public int deleteByPrimaryKey(Integer id) {
        return shopMapper.deleteByPrimaryKey(id);
    }


    public int insertSelective(Shop record) {
        return shopMapper.insertSelective(record);
    }

    public Shop selectByPrimaryKey(Integer id) {
        return shopMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Shop record) {
        return shopMapper.updateByPrimaryKeySelective(record);
    }

    public Shop selectByPhone(String phone) {
        return shopMapper.selectByPhone(phone);
    }


    public List<Shop> selectListByFilter(ShopFilter filter) {

        return shopMapper.selectListByFilter(filter);

    }
}
