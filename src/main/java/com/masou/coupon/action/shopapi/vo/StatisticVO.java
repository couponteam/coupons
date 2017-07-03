package com.masou.coupon.action.shopapi.vo;

/**
 * Created by jason on 2017/6/13.
 */
public class StatisticVO {

    private int followersTotal;

    private int followersToday;

    private int pageViewTotal;

    private int pageViewToday;

    private int ticketTotal;

    private int ticketToday;

    public Integer getFollowersTotal() {
        return followersTotal;
    }

    public void setFollowersTotal(Integer followersTotal) {
        this.followersTotal = followersTotal;
    }

    public Integer getFollowersToday() {
        return followersToday;
    }

    public void setFollowersToday(Integer followersToday) {
        this.followersToday = followersToday;
    }

    public Integer getPageViewTotal() {
        return pageViewTotal;
    }

    public void setPageViewTotal(Integer pageViewTotal) {
        this.pageViewTotal = pageViewTotal;
    }

    public Integer getPageViewToday() {
        return pageViewToday;
    }

    public void setPageViewToday(Integer pageViewToday) {
        this.pageViewToday = pageViewToday;
    }

    public Integer getTicketTotal() {
        return ticketTotal;
    }

    public void setTicketTotal(Integer ticketTotal) {
        this.ticketTotal = ticketTotal;
    }

    public Integer getTicketToday() {
        return ticketToday;
    }

    public void setTicketToday(Integer ticketToday) {
        this.ticketToday = ticketToday;
    }
}
