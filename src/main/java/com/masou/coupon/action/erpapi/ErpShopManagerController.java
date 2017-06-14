package com.masou.coupon.action.erpapi;

import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.service.api.UserTokenService;
import com.masou.coupon.service.erpapi.ErpShopManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 2017/6/11.
 */
@RestController
@RequestMapping("/management/api/shop")
public class ErpShopManagerController {

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private ErpShopManagerService erpShopManagerService;

    @ApiOperation("精选店铺排行")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result bestShopRank(@RequestParam(value = "sid") Long sid,
                               @RequestParam(value = "rank") Integer rank){




        return ResultHelper.genResult(ErrorCodeEnum.RANK_FAILED);
    }


}
