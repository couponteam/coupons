package com.masou.coupon.action.shopapi;

import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.service.api.UserTokenService;
import com.masou.coupon.service.shopapi.ShopManagerService;
import com.masou.coupon.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jason on 2017/5/16.
 */
@RestController
@RequestMapping("/shop/api/shopmag")
public class ApiShopManagerController {

    @Autowired
    private ShopManagerService shopManagerService;

    @Autowired
    private UserTokenService userTokenService;

    private Logger logger = LoggerFactory.getLogger(ApiShopManagerController.class);

    @ApiOperation("查看店铺列表")
    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    public Result shopList(@RequestParam("token") String token,
                           @RequestParam("page") Integer page,
                           @RequestParam("pageSize") Integer pageSize){
        Long uid = userTokenService.getUid(token);

        List<Shop> shopList = shopManagerService.shopList(uid,page,pageSize);
        if(shopList != null && shopList.size() > 0){
            return ResultHelper.genResultWithSuccess(shopList);
        }else{
            logger.info("[无数据]当前用户 " + uid + " 无店铺信息");
            return ResultHelper.genResult(ErrorCodeEnum.FAILED);
        }
    }

    @ApiOperation("查看单个店铺")
    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public Result shopBysid(@RequestParam("sid") Long sid,
                            @RequestParam("token") String token){
//        Long uid = userTokenService.getUid(token);
//        if(uid == null || uid <= 0){
//            return ResultHelper.genResult(ErrorCodeEnum.TOKEN_INVALID);
//        }

        if(sid != null && sid > 0){
            Shop shop = shopManagerService.shopBysid(sid);
            if(shop != null){
                return ResultHelper.genResultWithSuccess(shop);
            }else{
                logger.error("【空数据错误】获取店铺 " + sid + " 数据失败.");
            }
        }else{
            logger.error("【传入空值】uid为空");
        }
        return ResultHelper.genResult(ErrorCodeEnum.FAILED);
    }

    @ApiOperation("更新店铺信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateShop(@RequestParam("data") String data,
                             @RequestParam("sid") Long sid,
                             @RequestParam("token") String token){

        String errorMsg = "";
        if(StringUtil.areNotEmpty(data)){
            if((sid != null && sid > 0 )){
                return ResultHelper.genResultWithSuccess(shopManagerService.update(data, sid));
            }
        }else{
            errorMsg = "数据不能为空";
        }
        return ResultHelper.genResult(ErrorCodeEnum.SHOP_UPDATE_FAILED.getCode(),ErrorCodeEnum.SHOP_UPDATE_FAILED.getMsg());
    }

    @ApiOperation("删除店铺")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result deleteShop(@RequestParam("token") String token,
                             @RequestParam("sid") Long sid){
        Long uid = userTokenService.getUid(token);
        if(uid == null || uid <= 0){
            return ResultHelper.genResult(ErrorCodeEnum.TOKEN_INVALID);
        }

        if(sid != null && sid > 0){
            if(shopManagerService.delete(sid) > 0){
                return ResultHelper.genResultWithSuccess();
            }
            return ResultHelper.genResult(ErrorCodeEnum.SHOP_DELETE_FAILED);
        }else{
            return ResultHelper.genResult(ErrorCodeEnum.NULL_VALUE_ERROR.getCode(), "传入的店铺id"+ErrorCodeEnum.NULL_VALUE_ERROR.getMsg());
        }
    }
}
