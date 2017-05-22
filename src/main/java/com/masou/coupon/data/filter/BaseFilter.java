package com.masou.coupon.data.filter;

import lombok.Data;

/**
 * Created by Paul on 2017/2/21.
 */
@Data
public class BaseFilter {
    private Integer limit;
    private Integer offset;
    private Long uid;

    private String timeBegin;
    private String timeEnd;

    private Integer enable;

    private Integer placeholder=1;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(Integer placeholder) {
        this.placeholder = placeholder;
    }
}
