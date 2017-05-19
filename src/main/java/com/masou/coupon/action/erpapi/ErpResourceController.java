package com.masou.coupon.action.erpapi;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.models.ImgResource;
import com.masou.coupon.data.models.Resource;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.QiniuService;
import com.masou.coupon.service.api.ImageResourceService;
import com.masou.coupon.utils.MD5Util;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Paul on 2017/5/9.
 */
@RestController
@RequestMapping("/management/api/resource")
public class ErpResourceController {
    @Autowired
    private MD5Util md5Util;

    @Autowired
    private QiniuService qiniuService;

    @Autowired
    private ImageResourceService imageResourceService;

    @ApiOperation("插入图片")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insert(@RequestParam(value = "name") String name,
                         @RequestParam(value = "shopId", required = false) Integer shopId,
                         @RequestParam(value = "type", required = false) Integer type,
                         @RequestParam(value = "file") MultipartFile file) {

        String md5 = null;
        try {
            md5 = md5Util.MD5File(file.getBytes());
            String fileName = file.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (extension.equals("")) {
                throw new UserException("请先选择文件");
            } else {
                switch (extension.toLowerCase()) {
                    case "jpg":
                    case "jpeg":
                    case "png":
                        break;
                    default:
                        throw new UserException("不支持的文件格式");

                }
            }
            String fileKey = String.format("%s.%s", md5, extension);
            boolean uploadResult = qiniuService.upload(file.getBytes(), fileKey);
            //保存
            ImgResource record = new ImgResource(shopId, (byte) 0, type != null ? type.byteValue() : 0, fileKey);

            return imageResourceService.insertSelective(record);

        } catch (IOException e) {
            throw new UserException("异常错误");
        }
    }


}
