package com.masou.coupon.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

/**
 * @author jason.lin@timerchina.com 2014-11-4 下午4:41:07 json和对象之间的转换类
 */
public class JsonUtils {

    private static Gson gson = null;

    private JsonUtils() {
    }

    static {
        ExclusionStrategy myExclusionStrategy = new ExclusionStrategy() {
            /**
             * 去除类属性是"_"开头的属性名
             */
            public boolean shouldSkipField(FieldAttributes fname) {
                return fname.getName().startsWith("_");
            }

            public boolean shouldSkipClass(Class<?> arg0) {
                return false;
            }
        };
        gson = new GsonBuilder().setExclusionStrategies(myExclusionStrategy).create();
    }

    /**
     * 将json转换为对象
     * 
     * @param <T>
     * @param <T>
     * @param json
     * @return
     */
    public static <T> T Json2Object(Class<T> objectClass, String json) {
        T newObject = null;
        if (json != null && json.length() > 1) {
            newObject = gson.fromJson(json, objectClass);
        }
        return newObject;
    }

    /**
     * 将对象转换为字符串
     * 
     * @param object
     * @return
     */
    public static String object2Json(Object object) {
        String json = null;
        if (object != null) {
            json = gson.toJson(object);
        }
        return json;
    }

    /**
     * 将Map转换成Json字符串
     * 
     * @param map
     * @return
     */
    public static <K, V> String map2Json(Map<K, V> map) {
        String json = null;
        if (map != null && map.size() > 0) {
            json = gson.toJson(map);
        }
        return json;
    }
}
