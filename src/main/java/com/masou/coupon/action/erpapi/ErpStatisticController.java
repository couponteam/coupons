package com.masou.coupon.action.erpapi;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.models.UserManager;
import com.masou.coupon.service.erpapi.ErpStatisticService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jason on 2017/6/24.
 */
@RestController
@RequestMapping("/management/api/statistic")
public class ErpStatisticController {

    @Autowired
    private ErpStatisticService erpStatisticService;

    @ApiOperation("商铺数据统计")
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Result statistic(@RequestParam("token") String token){
        return ResultHelper.genResultWithSuccess(erpStatisticService.statistic());
    }

}
