package com.masou.coupon.action.erpapi.vo;

import com.masou.coupon.data.models.UserManager;
import lombok.Data;

/**
 * Created by Paul on 2017/3/25.
 */
@Data
public class ManagerLoginVO {
    private String token;
    private UserManager userManager;
}

