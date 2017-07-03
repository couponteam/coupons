package com.masou.coupon.data.filter;

import com.masou.coupon.common.enums.ShopVerifyEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Paul on 2017/5/20.
 */
@Data
public class ShopFilter extends BaseFilter {

    private Long sid;

    private Integer industry;

    private Integer type;

    private String tid;

    private String keyword;

    private Long uid;

    private Integer status;

    private Date createTime;

    private List<Long> ids;

    private Integer verified = ShopVerifyEnum.VERIFIED.getCode();

    private Integer rank;

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public Long getUid() {
        return uid;
    }

    @Override
    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
