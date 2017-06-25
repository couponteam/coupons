package com.masou.coupon.dao.ShopApiDao;

import com.alibaba.fastjson.JSON;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.data.filter.BaseFilter;
import com.masou.coupon.data.mappers.LogUserVisitMapper;
import com.masou.coupon.data.mappers.ShopMapper;
import com.masou.coupon.data.mappers.TicketMapper;
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

    @Autowired
    private TicketMapper ticketMapper;

    /**
     * 查询店铺列表
     * @return
     */
    public List<Shop> shopList(BaseFilter baseFilter){
        return shopMapper.shopList(baseFilter);
    }

    /**
     * 查询店铺列表总数
     * @param baseFilter
     * @return
     */
    public int shopListCount(BaseFilter baseFilter){
        return shopMapper.shopListCount(baseFilter);
    }


    /**
     * 查看单个店铺
     * @param sid
     * @return
     */
    public Shop shopBysid(Long sid){
        return shopMapper.selectByPrimaryKey(sid);
    }

    public Integer findStatusByUid(String tid){
        return shopMapper.findStatusByUid(tid);
    }

    /**
     *
     * @return
     */
//    public Shop regis(Shop shop){
//        int rows = shopMapper.insert(shop);
//        if(rows > 0){
//            return shopMapper.shopMD5(shop.getShopMD5());
//        }
//        return null;
//    }

    /**
     * 更新店铺信息
     * @param data 更新的数据
     * @return 返回修改后的数据
     */
    public int update(String data){
        Shop shop = JSON.toJavaObject((JSON)JSON.parse(data), Shop.class);
        return shopMapper.updateByPrimaryKey(shop);
    }

    public int delete(Long sid){
        return shopMapper.deleteByPrimaryKey(sid);
    }
}
