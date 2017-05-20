package com.masou.coupon.data.models;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Paul on 2017/5/3.
 */
@Data
public class UserToken implements Serializable {

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 是否通过认证 0-不通过 1-通过
     */
    private Integer isVerified;

    /**
     * token最近使用的时间
     */
    private Long time;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
