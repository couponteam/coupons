package com.masou.coupon.data.filter;

import lombok.Data;

/**
 * Created by Paul on 2017/5/3.
 */
@Data
public class UserFilter extends BaseFilter {

    private String fromKey;

    private String username;
}
