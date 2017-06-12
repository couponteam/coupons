package com.masou.coupon.dao.api;

import com.masou.coupon.data.filter.LocaltionFilter;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.models.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jason on 2017/6/8.
 */
@Repository
public class ShopDao {



    @Autowired
    private ShopMapper shopMapper;

    /**
     * 通过店铺id获取店铺详细信息
     * @param sid
     * @return
     */
    public Shop selectShop(Long sid){
        return shopMapper.selectByPrimaryKey(sid);
    }


    /**
     * 获取当前定位的店铺信息
     * @param filter
     * @return
     */
    public List<Shop> findByLocation(LocaltionFilter filter){
        return shopMapper.findByLocation(filter);
    }

    /**
     * 获取精选店铺
     * @param shopFilter
     * @return
     */
    public List<Shop> bestShop(ShopFilter shopFilter){
       return shopMapper.bestShop(shopFilter);
    }
}
