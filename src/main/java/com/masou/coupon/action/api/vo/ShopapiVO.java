package com.masou.coupon.action.api.vo;

import com.masou.coupon.action.api.vo.ticketvo.TicketVO;
import com.masou.coupon.data.models.Shop;

import java.util.List;

/**
 * Created by jason on 2017/6/10.
 */
public class ShopapiVO implements Comparable<ShopapiVO>{

    @Override
    public int compareTo(ShopapiVO o) {

        if((this.distance - o.distance) > 0 ){
            return 1;
        }
        return 0;
    }

    private Shop shop;

    private List<TicketVO> ticketVO;

    private double distance;

    private Integer newMsgCount;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<TicketVO> getTicketVO() {
        return ticketVO;
    }

    public void setTicketVO(List<TicketVO> ticketVO) {
        this.ticketVO = ticketVO;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Integer getNewMsgCount() {
        return newMsgCount;
    }

    public void setNewMsgCount(Integer newMsgCount) {
        this.newMsgCount = newMsgCount;
    }
}
