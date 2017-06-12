package com.masou.coupon.action.shopapi;

import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.service.api.UserTokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据统计接口
 * Created by jason on 2017/6/5.
 */
@RestController
@RequestMapping("/shop/api/statistic")
public class ApiStatisticController {

    @Autowired
    private UserTokenService userTokenService;

    @ApiOperation("查看店铺列表")
    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    public Result shopList(@RequestParam("token") String token){

        Long uid = userTokenService.getUid(token);


        return ResultHelper.genResult(ErrorCodeEnum.STATISTIC_SSELECT_FAILED);
    }





}
