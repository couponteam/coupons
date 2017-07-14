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
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Result bestShopRank(@RequestParam("sid") Long sid,
                               @RequestParam(value = "rank", required = false) Integer rank){

        if(erpShopManagerService.bestShopRank(sid, rank) > 0){
            return ResultHelper.genResultWithSuccess();
        }
        return ResultHelper.genResult(ErrorCodeEnum.RANK_FAILED);
    }

    @ApiOperation("我的店铺要加入列表")
    @RequestMapping(value = "/applyList", method = RequestMethod.GET)
    public Result applyForShopList(
                                    @RequestParam(value = "id",required = false) Long id,
                                    @RequestParam("token") String token,
                                   @RequestParam(value = "status", required = false) String status){
//        Long uid = userTokenService.getUid(token);
        return erpShopManagerService.applyForShopList(id, status);
    }

    @ApiOperation("精选店铺列表")
    @RequestMapping(value = "/bestList", method = RequestMethod.GET)
    public Result bestShopList(
            @RequestParam("token") String token){
//        Long uid = userTokenService.getUid(token);
        return erpShopManagerService.bestShopList();
    }


}
