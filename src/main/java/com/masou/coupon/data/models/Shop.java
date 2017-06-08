package com.masou.coupon.data.models;

import java.util.Date;

public class Shop {
    private Integer id;

    private Long uid;

    private String shopName;

    private String iconId;

    private Integer industryId;

    private String phone;

    private Byte isPhoneVerified;

    private String telephone;

    private String telephoneEx;

    private String briefIntro;

    private String businessLicenseId;

    private float longitude;

    private float dimensionality;

    private Byte isShopVerified;

    private String wechatAccount;

    private String shopAddress;

    private Integer rank;

    private Date createTime;

    private Date lastUpdateTime;

    private Date phoneVerifiedTime;

    private String shopMD5;

    private Ticket ticket;

    private String comment;

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

    public String getBusinessLicenseId() {
        return businessLicenseId;
    }

    public void setBusinessLicenseId(String businessLicenseId) {
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

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getDimensionality() {
        return dimensionality;
    }

    public void setDimensionality(float dimensionality) {
        this.dimensionality = dimensionality;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getShopMD5() {
        return shopMD5;
    }

    public void setShopMD5(String shopMD5) {
        this.shopMD5 = shopMD5;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}