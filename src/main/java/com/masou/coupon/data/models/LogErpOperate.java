package com.masou.coupon.data.models;

import java.util.Date;

public class LogErpOperate {
    private Long id;

    private Long uid;

    private String operate;

    private String opPath;

    private Date createTime;

    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

    public String getOpPath() {
        return opPath;
    }

    public void setOpPath(String opPath) {
        this.opPath = opPath == null ? null : opPath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }
}