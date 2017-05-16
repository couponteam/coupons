package com.masou.coupon.utils;

/**
 * Created by Paul on 2017/5/4.
 */
public class ModelConvertUtil {

    public static <T> T convert(Class<T> type, Object o) {

        String json = JsonUtil.getJsonFromObject(o);

        return JsonUtil.getObjectFromJson(json, type);

    }
}
