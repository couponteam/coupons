package com.masou.coupon.service.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.utils.HttpUtil;
import com.masou.coupon.utils.JsonUtil;
import com.masou.coupon.utils.MD5Util;
import com.masou.coupon.utils.OkHttpUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paul on 2017/5/17.
 */
@Service
public class SmsClientService {

    private Logger logger = LoggerFactory.getLogger(SmsClientService.class);

    private static String baseUrl = "http://202.91.244.252:30001/yqx/v1/sms/single_send";
    private static final String ACCOUNT = "5634";

    private static final String API_KEY = "5b2707d90f8fe7affa203c07497f166f";


    public static String CODE_SUCCESS = "0";


    public SMSResult sendMessage(String phone, String content) {

        String result = send(phone, content);
        if (result != null) {
            SMSResult smsResult = JsonUtil.getObjectFromJson(result, SMSResult.class);
            logger.info("短信请求结果：" + JSON.toJSONString(smsResult));

            if (smsResult != null && smsResult.getCode().equals(CODE_SUCCESS)) {
                return smsResult;
            }

        }
        return null;
    }


    public String send(String phone, String content) {
        /** 测试 */
        String url = baseUrl;
        String account = ACCOUNT;
        String apikey = API_KEY;
        try {
//            String sign = Base64.encodeBase64String(md5Util.MD5(
//                    account + apikey).getBytes());

            String sign = "YjE1Zjk3NzRiYjAxYzg2NTEwNzBlMjQzOWU0MzcwNmI=";

            JSONObject json = new JSONObject();
            json.put("account", account);
            json.put("mobile", phone);
            json.put("text", content);
            json.put("sign", sign);
            json.put("extend", "");

            System.out.println("request:" + json.toJSONString());

            // 设置PostMethod
            PostMethod postMethod = new PostMethod(url);
            postMethod.getParams().setContentCharset("utf-8");
            postMethod.getParams().setHttpElementCharset("utf-8");

            // 设置requestEntity
            RequestEntity requestEntity = new StringRequestEntity(
                    json.toJSONString(), "application/json", "UTF-8");
            postMethod.setRequestEntity(requestEntity);

            // http client 参数设置 连接超时 读取数据超时
            HttpClient httpClient = new HttpClient();
            // httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
            // httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
            try {
                httpClient.executeMethod(postMethod);
                if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
                    InputStream resStream = postMethod
                            .getResponseBodyAsStream();
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(resStream, "UTF-8"));
                    StringBuffer resBuffer = new StringBuffer();
                    String resTemp = "";
                    while ((resTemp = br.readLine()) != null) {
                        resBuffer.append(resTemp);
                    }
                    String result = resBuffer.toString();
                    return result;
//					System.out.println(result);
                }
            } finally {
                postMethod.releaseConnection();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
