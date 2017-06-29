package com.masou.coupon.dao.erpapi;

import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.models.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by jason on 2017/6/28.
 */
@Repository
public class ErpShopDao {

    @Autowired
    private ShopMapper shopMapper;

    public int bestShopRank(Shop shop){
        return shopMapper.updateByPrimaryKey(shop);
    }
}
