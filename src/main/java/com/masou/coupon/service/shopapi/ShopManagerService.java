package com.masou.coupon.service.shopapi;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.TicketWithBLOBs;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by jason on 2017/5/18.
 */
@Service
public class ShopManagerService {

    /**
     * 查询店铺列表
     * @param uid
     * @return
     */
    public List<Shop> shopList(Long uid){

        return null;
    }

    /**
     * 查看单个店铺
     * @param sid
     * @return
     */
    public Shop shopBysid(Long sid){


        return null;
    }

    /**
     *
     * @param data 店铺基本信息
     * @param uid 用户id
     * @return
     */
    public Shop regis(String data){


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
