package com.masou.coupon.utils;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 对象转字符串工具类.
 * Created by paul on 16/12/21.
 */
public abstract class ObjectToStringUtils {

    /**
     * 通过反射将对象转换成字符串.
     * 
     * @param object
     * @return
     */
    public static String reflectionToString(Object object) {
        return ToStringBuilder.reflectionToString(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * 对象转换成JSON字符串.
     * 
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        return JsonUtil.getJsonFromObject(object);
    }

}
