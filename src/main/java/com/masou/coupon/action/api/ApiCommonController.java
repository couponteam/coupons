package com.masou.coupon.action.api;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.constant.BeValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Paul on 2017/5/17.
 */
@RestController
@RequestMapping("/api/common")
public class ApiCommonController {

    @RequestMapping("/getQiniuBaseUrl")
    public Result getQiniuBaseUrl(){

        return ResultHelper.genResultWithSuccess(BeValue.QINIU_BASE_URL);
    }
}
