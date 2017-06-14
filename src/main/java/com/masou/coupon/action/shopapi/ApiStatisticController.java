package com.masou.coupon.action.shopapi;

import com.masou.coupon.action.shopapi.vo.StatisticVO;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.service.api.UserTokenService;
import com.masou.coupon.service.shopapi.ApiStatisticService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 数据统计接口
 * Created by jason on 2017/6/5.
 */
@RestController
@RequestMapping("/shop/api/statistic")
public class ApiStatisticController {

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private ApiStatisticService apiStatisticService;

    @ApiOperation("商铺数据统计")
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Result statistic(
//            @RequestParam("token") String token,
            @RequestParam("uid") Long uid,
                            @RequestParam(value = "fromData", required = false) Date fromData,
                            @RequestParam(value = "toData", required = false) Date toData,
                            @RequestParam(value = "sid",required = false) Long sid){
//        Long uid = userTokenService.getUid(token);
        StatisticVO vo = apiStatisticService.statistic(uid, fromData, toData, sid);
        if(vo != null){
            return ResultHelper.genResultWithSuccess(vo);
        }
        return ResultHelper.genResult(ErrorCodeEnum.EMPTY_DATA_ERROR);
    }

}
