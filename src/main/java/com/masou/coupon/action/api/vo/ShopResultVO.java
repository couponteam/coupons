package com.masou.coupon.action.api.vo;

import java.util.List;

/**
 * Created by jason on 2017/6/10.
 */
public class ShopResultVO {

    private List<ShopVO> shopVOList;

    private int total;

    public List<ShopVO> getShopVOList() {
        return shopVOList;
    }

    public void setShopVOList(List<ShopVO> shopVOList) {
        this.shopVOList = shopVOList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
