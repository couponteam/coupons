package com.masou.coupon.action.api.vo;

import java.util.List;

/**
 * Created by jason on 2017/6/10.
 */
public class ShopResultVO {

    private List<ShopapiVO> shopVOList;

    private int total;

    public List<ShopapiVO> getShopVOList() {
        return shopVOList;
    }

    public void setShopVOList(List<ShopapiVO> shopVOList) {
        this.shopVOList = shopVOList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
