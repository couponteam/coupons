package com.masou.coupon.action.api.vo;

/**
 * Created by jason on 2017/7/10.
 */
public class SverifyVO {

    private boolean applyShop;

    private Integer industryId;

    private Byte isShopVerified;

    public boolean isApplyShop() {
        return applyShop;
    }

    public void setApplyShop(boolean applyShop) {
        this.applyShop = applyShop;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Byte getIsShopVerified() {
        return isShopVerified;
    }

    public void setIsShopVerified(Byte isShopVerified) {
        this.isShopVerified = isShopVerified;
    }
}
