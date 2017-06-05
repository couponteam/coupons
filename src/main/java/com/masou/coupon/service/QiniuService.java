package com.masou.coupon.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by july on 2016/11/18.
 */

@Service
public class QiniuService {

    private String accessKey = "O5J4bt2_maG3AEWUujacIW4ZCMaBcvlPf3-x212n";

    private String secretKey = "IxZ74MAFn4MnYR3mvbhE9WGQUIZr7v249QutpDX-";


    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken(){

        //要上传的空间
        String bucketname = "welin";
        //密钥配置
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucketname);
    }

    public boolean upload(byte[] data, String fileKey) throws IOException {
        try {
            //创建上传对象
            UploadManager uploadManager = new UploadManager();

            System.out.println("token:"+getUpToken());
            //调用put方法上传
            Response res = uploadManager.put(data, fileKey, getUpToken());
            //打印返回的信息
            return res.statusCode == 200;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }

        return false;
    }



}
