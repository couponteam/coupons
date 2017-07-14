package com.masou.coupon.action.api.vo.ticketvo;

import com.masou.coupon.data.models.Shop;

import java.util.List;

/**
 * Created by jason on 2017/6/24.
 */
public class ShopList {



    private List<Shop> shops;

    private Integer total;

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
