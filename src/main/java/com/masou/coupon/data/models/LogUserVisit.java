package com.masou.coupon.data.models;

import java.util.Date;

public class LogUserVisit {
    private Long id;

    private String ip;

    private String url;

    private String method;

    private Date createTime;

    private String fromkey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFromkey() {
        return fromkey;
    }

    public void setFromkey(String fromkey) {
        this.fromkey = fromkey;
    }
}