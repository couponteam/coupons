package com.masou.coupon.data.filter;

import com.masou.coupon.common.enums.ShopVerifyEnum;
import lombok.Data;

/**
 * Created by Paul on 2017/5/20.
 */
@Data
public class ShopFilter extends BaseFilter {

    private Long sid;

    private Integer industry;

    private Integer type;

    private Integer verified = ShopVerifyEnum.VERIFIED.getCode();

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
