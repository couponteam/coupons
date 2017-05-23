package com.masou.coupon.action.erpapi.vo;

import com.masou.coupon.action.param.PageParam;
import lombok.Data;

/**
 * Created by jason on 2017/5/23.
 */
@Data
public class TicketPageParam extends PageParam {

    protected Long shop_id;

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }
}
