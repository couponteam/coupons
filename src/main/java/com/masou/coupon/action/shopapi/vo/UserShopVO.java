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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isApplyShop() {
        return isApplyShop;
    }

    public void setApplyShop(boolean applyShop) {
        isApplyShop = applyShop;
    }

    public ShopVO getShopProfile() {
        return shopProfile;
    }

    public void setShopProfile(ShopVO shopProfile) {
        this.shopProfile = shopProfile;
    }
}
