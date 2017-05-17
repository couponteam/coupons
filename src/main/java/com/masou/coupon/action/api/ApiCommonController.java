package com.masou.coupon.action.api;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.common.constant.BeValue;
import com.masou.coupon.service.api.PhoneMessageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Paul on 2017/5/17.
 */
@RestController
@RequestMapping("/api/common")
public class ApiCommonController {


    @RequestMapping(value = "/getQiniuBaseUrl", method = RequestMethod.GET)
    public Result getQiniuBaseUrl() {

        return ResultHelper.genResultWithSuccess(BeValue.QINIU_BASE_URL);
    }


    @Autowired
    private PhoneMessageService phoneMessageService;


    @ApiOperation("发送短信")
    @ApiImplicitParam(name = "type", value = "短信类型 0普通用户注册，1普通用户登陆，2普通用户忘记密码  3店家注册")
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public Result sendPhoneMessage(@RequestParam("phone") String phone,
                                   @RequestParam(value = "type", defaultValue = "0") Integer type) {


//        String verify = phoneMessageService.generateVerify();
        String verify = "111111";
        String tpl = "您的验证码是%s。";
        String message = String.format(tpl, verify);


        return phoneMessageService.sendMessage(phone, verify, message, type);
    }

}
