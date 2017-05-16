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

    private String accessKey = "LI93y8HnnKqwLA0pz33PyR0_9B0VGVPJXW5QeWBt";

    private String secretKey = "_opb8UhXgKB1-XD-prRqUD9ntZz29T1sqHY6iO8E";

    //要上传的空间
    String bucketname = "linghuiquan";
    //密钥配置
    Auth auth = Auth.create(accessKey, secretKey);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken(){
        return auth.uploadToken(bucketname);
    }

    public boolean upload(byte[] data, String fileKey) throws IOException {
        try {
            //创建上传对象
            UploadManager uploadManager = new UploadManager();
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

    public boolean delete(String fileKey) {
        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth);
        //要测试的空间和key，并且这个key在你空间中存在
        try {
            //调用delete方法移动文件
            bucketManager.delete(bucketname, fileKey);
            return true;
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            System.out.println(r.toString());
            return false;
        }
    }

}
