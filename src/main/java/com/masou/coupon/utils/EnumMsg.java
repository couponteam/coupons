/**
 * Copyright (c) 2016, 59store. All rights reserved.
 */
package com.masou.coupon.utils;

/**
 * 枚举信息.
 *  Created by paul on 16/12/21.
 */
public interface EnumMsg<T> extends EnumCode<T> {

    /**
     * 获取枚举信息.
     * 
     * @return 枚举信息
     */
    public String getMsg();

}
