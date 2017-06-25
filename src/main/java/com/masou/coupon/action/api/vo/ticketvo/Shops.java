package com.masou.coupon.action.api.vo.ticketvo;

import java.util.List;

/**
 * 系统最终返回的对象
 * Created by jason on 2017/6/16.
 */
public class Shops {

    private List<ShopTicket> shops;

    private int total;

    private double distance;

    public List<ShopTicket> getShops() {
        return shops;
    }

    public void setShops(List<ShopTicket> shops) {
        this.shops = shops;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
