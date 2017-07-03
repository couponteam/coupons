package com.masou.coupon.dao.erpapi;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.mappers.UserApplyMapper;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.UserApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jason on 2017/6/28.
 */
@Repository
public class ErpShopDao {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private UserApplyMapper userApplyMapper;

    public int bestShopRank(Shop shop){
        return shopMapper.updateByPrimaryKeySelective(shop);
    }

    public List<UserApply> applyForShopList(ShopFilter shopFilter){
        return userApplyMapper.applylist(shopFilter);
    };

    public List<Shop> bestShopList(){
        return shopMapper.bestShopList();
    };
}
