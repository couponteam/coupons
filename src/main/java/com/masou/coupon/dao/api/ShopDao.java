package com.masou.coupon.dao.api;

import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.models.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jason on 2017/5/25.
 */
@Repository
public class ShopDao {

    @Autowired
    private ShopMapper shopMapper;

    public List<Shop> selectBySType(Integer industry, Integer page, Integer pageSize){


        return null;
//        return shopMapper.selectByType(industry, limit);
    }

//    public



}
