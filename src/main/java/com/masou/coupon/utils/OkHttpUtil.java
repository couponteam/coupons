package com.masou.coupon.utils;

import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Paul on 2017/3/1.
 */
@Component
public class OkHttpUtil {

    public static final MediaType FORM_CONTENT_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=gbk");

    public <T> T sendGet(String url, Map<String, String> params, Class<T> clazz) throws UnsupportedEncodingException {
        OkHttpClient okHttpClient = new OkHttpClient();
        if (params != null && !params.isEmpty() && params.size() != 0) {
            String param = urlEncode(params);
            url = url + "?" + param;
        }

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();
            T result = new Gson().fromJson(response.body().string(), clazz);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    public <T> T sendPost(String url, Map<String, String> params, Class<T> clazz){

        OkHttpClient okHttpClient = new OkHttpClient();
//        StringBuffer sb = new StringBuffer();
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && !params.isEmpty() && params.size() != 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    builder.addEncoded(entry.getKey(), entry.getValue());
//                    sb.append(entry.getKey() +"="+entry.getValue()+"&");
                }
            }
        }



        RequestBody formBody = builder.build();
//        RequestBody formBody = RequestBody.create(FORM_CONTENT_TYPE,sb.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            T t = new Gson().fromJson(response.body().string(),clazz);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private String urlEncode(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuffer sb2 = new StringBuffer();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                sb2.append(entry.getKey());
                sb2.append("=");
                sb2.append(URLEncoder.encode(entry.getValue(), "utf-8").toString());
                sb2.append("&");
            }
        }
        String s = "";
        if (sb2.length() != 0) {
            s = sb2.substring(0, sb2.length() - 1);
        }
        return s;
    }

}
