package com.masou.coupon.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * 签名工具类
 * Created by paul on 16/12/21.
 */
public abstract class SignatureUtils {

    /**
     * 对Map参数进行签名.
     * <p>
     * <p>
     * 忽略key或value为空的参数。
     * </p>
     *
     * @param paramsMap Map参数
     * @param key       密钥
     * @return 签名字符串
     */
    public static String sign(Map<String, String> paramsMap, String key) {
        if (MapUtils.isEmpty(paramsMap) || StringUtils.isBlank(key)) {
            return "";
        }

        List<String> paramKeyList = new ArrayList<>(paramsMap.keySet());
        Collections.sort(paramKeyList);

        StringBuilder sb = new StringBuilder();
        for (String paramKey : paramKeyList) {
            String paramValue = paramsMap.get(paramKey);
            if (StringUtils.isNotBlank(paramKey) && StringUtils.isNotBlank(paramValue)) {
                sb.append(paramKey).append("=").append(paramValue).append("&");
            }
        }

        sb.append(key);
        return DigestUtils.md5Hex(sb.toString());
    }

}
