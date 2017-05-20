package com.masou.coupon.data.models;

import java.util.Date;

public class Shop {
    private Integer id;

    private String shopName;

    private String iconId;

    private Integer industryId;

    private String phone;

    private Byte isPhoneVerified;

    private String password;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Byte getIsPhoneVerified() {
        return isPhoneVerified;
    }

    public void setIsPhoneVerified(Byte isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getTelephoneEx() {
        return telephoneEx;
    }

    public void setTelephoneEx(String telephoneEx) {
        this.telephoneEx = telephoneEx == null ? null : telephoneEx.trim();
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro == null ? null : briefIntro.trim();
    }

    public Long getBusinessLicenseId() {
        return businessLicenseId;
    }

    public void setBusinessLicenseId(Long businessLicenseId) {
        this.businessLicenseId = businessLicenseId;
    }

    public Byte getIsShopVerified() {
        return isShopVerified;
    }

    public void setIsShopVerified(Byte isShopVerified) {
        this.isShopVerified = isShopVerified;
    }

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount == null ? null : wechatAccount.trim();
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getPhoneVerifiedTime() {
        return phoneVerifiedTime;
    }

    public void setPhoneVerifiedTime(Date phoneVerifiedTime) {
        this.phoneVerifiedTime = phoneVerifiedTime;
    }
}