package com.masou.coupon.data.filter;

import java.util.Date;

/**
 * Created by jason on 2017/6/13.
 */
public class StatisticFilter extends BaseFilter implements Cloneable{

    private Date fromData;

    private Date toData;

    private Long sid;

    private Date today;

    private String tid;

    public Date getFromData() {
        return fromData;
    }

    public void setFromData(Date fromData) {
        this.fromData = fromData;
    }

    public Date getToData() {
        return toData;
    }

    public void setToData(Date toData) {
        this.toData = toData;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        StatisticFilter statisticFilter = null;
        try {
            statisticFilter = (StatisticFilter)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return statisticFilter;
    }

}
