package com.masou.coupon.service.shopapi;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.dao.ShopApiDao.ShopManagerDao;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.TicketWithBLOBs;
import com.masou.coupon.utils.GenTicketIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by jason on 2017/5/18.
 */
@Service
public class ShopManagerService {

    @Autowired
    private ShopManagerDao shopManagerDao;

    /**
     * 查询店铺列表
     * @param uid
     * @return
     */
    public List<Shop> shopList(Long uid,Integer page, Integer pageSize){
        return shopManagerDao.shopList(uid,page,pageSize);
    }

    /**
     * 查看单个店铺
     * @param sid
     * @return
     */
    public Shop shopBysid(Long sid){
        return shopManagerDao.shopBysid(sid);
    }

    /**
     *
     * @param data 店铺基本信息
     * @return
     */
    public Shop regis(String data){
        try {
            Shop shop = JSON.toJavaObject((JSON)JSON.parse(data), Shop.class);
            shop.setShopMD5(GenTicketIdUtil.genTicketId());
            return shopManagerDao.regis(shop);
        }catch(Exception e){
            e.printStackTrace();
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
