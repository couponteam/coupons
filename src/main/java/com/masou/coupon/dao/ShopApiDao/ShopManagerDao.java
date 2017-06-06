package com.masou.coupon.dao.ShopApiDao;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.models.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jason on 2017/5/19.
 */
@Repository
public class ShopManagerDao {

    @Autowired
    private ShopMapper shopMapper;

    /**
     * 查询店铺列表
     * @param uid
     * @return
     */
    public List<Shop> shopList(Long uid, Integer page, Integer pageSize){
        return shopMapper.shopList(uid,page,pageSize);
    }

    /**
     * 查看单个店铺
     * @param sid
     * @return
     */
    public Shop shopBysid(Long sid){
        return shopMapper.selectByPrimaryKey(sid);
    }

    /**
     *
     * @return
     */
    public Shop regis(Shop shop){
        int rows = shopMapper.insert(shop);
        if(rows > 0){
            return shopMapper.shopMD5(shop.getShopMD5());
        }
        return null;
    }

    /**
     * 更新店铺信息
     * @param data 更新的数据
     * @return 返回修改后的数据
     */
    public Shop update(String data, Long sid){
        Shop shp = JSON.toJavaObject((JSON)JSON.parse(data), Shop.class);

        return null;
    }


    public String delete(String sid){

        return null;
    }



}
