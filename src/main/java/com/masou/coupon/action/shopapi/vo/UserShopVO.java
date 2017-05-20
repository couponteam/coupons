package com.masou.coupon.action.shopapi.vo;

import com.masou.coupon.data.models.User;
import com.masou.coupon.data.models.UserProfile;
import lombok.Data;

import java.util.Date;

/**
 * Created by Paul on 2017/5/20.
 */
@Data
public class UserShopVO {

    private Long uid;

    private String username;

    private String phone;

    private boolean isApplyShop = false;


    ShopVO shopProfile;


}
