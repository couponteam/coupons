package com.masou.coupon.data.filter;

import lombok.Data;

/**
 * Created by Paul on 2017/5/3.
 */
@Data
public class UserFilter extends BaseFilter {

    private String fromKey;

    private String username;

    private String timeBegin;

    private String timeEnd;

    public String getFromKey() {
        return fromKey;
    }

    public void setFromKey(String fromKey) {
        this.fromKey = fromKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
