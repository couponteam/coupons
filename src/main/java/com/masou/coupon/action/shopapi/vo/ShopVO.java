package com.masou.coupon.action.shopapi.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by Paul on 2017/5/20.
 */
@Data
public class ShopVO {

    private Integer id;

    private String shopName;

    private String iconId;

    private Integer industryId;

    private String phone;

    private Byte isPhoneVerified;

    private String telephone;

    private String telephoneEx;

    private String briefIntro;

    private Long businessLicenseId;

    private Byte isShopVerified;

    private String wechatAccount;

    private String shopAddress;

    private Date createTime;

    private Date lastUpdateTime;

    private Date phoneVerifiedTime;

    private Long  uid;

}
