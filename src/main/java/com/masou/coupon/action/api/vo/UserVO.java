package com.masou.coupon.action.api.vo;

import lombok.Data;

/**
 * Created by Paul on 2017/5/4.
 */
@Data
public class UserVO {
    private Long id;

    private String username;

    private String phone;

    private UserProfileVO profile;
}
