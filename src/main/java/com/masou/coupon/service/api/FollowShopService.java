package com.masou.coupon.service.api;

import com.masou.coupon.data.filter.ShopFilter;
import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.mappers.TicketMapper;
import com.masou.coupon.data.mappers.UserShopMapper;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.UserShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 2017/7/5.
 */
@Service
public class FollowShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private UserShopMapper userShopMapper;

    /**
     * 用户关注店铺
     * @param uid
     * @param sid
     * @return
     */
    public boolean followShop(Long uid, Long sid){
        UserShop userShop = new UserShop();
        userShop.setUserId(uid);
        userShop.setShopId(sid);
        int count = userShopMapper.insertSelective(userShop);
        if(count > 0){
            return true;
        }
        return false;
    }

    /**
     * 检查用户是否关注店铺
     * @param uid
     * @return true：已关注；false：未关注
     */
    public boolean isFollowShop(Long uid, Long sid){
        UserShop userShop = new UserShop();
        userShop.setUserId(uid);
        userShop.setShopId(sid);
        UserShop rs = userShopMapper.selectByUidSid(userShop);
        if(rs != null && rs.getId() > 0){
            System.out.println("Already followed the shop");
            return true;
        }
        return false;
    }
}
