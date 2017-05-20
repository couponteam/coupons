package com.masou.coupon.data.filter;

import com.masou.coupon.common.enums.ShopVerifyEnum;
import lombok.Data;

/**
 * Created by Paul on 2017/5/20.
 */
@Data
public class ShopFilter extends BaseFilter {

    private Integer verified = ShopVerifyEnum.VERIFIED.getCode();
}
