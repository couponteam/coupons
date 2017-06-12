package com.masou.coupon.action.erpapi;

import com.masou.coupon.service.api.UserTokenService;
import com.masou.coupon.service.erpapi.ErpShopManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

//    @ApiOperation("查看店铺列表")
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public Result shopList(@RequestParam("token") String token,
//                           @RequestParam("page") Integer page,
//                           @RequestParam("pageSize") Integer pageSize){
//        Long uid = userTokenService.getUid(token);
//
//        List<Shop> shopList = shopManagerService.shopList(uid, page,pageSize);
//        if(shopList != null && shopList.size() > 0){
//            return ResultHelper.genResultWithSuccess(shopList);
//        }else{
//            logger.info("[无数据]当前用户 " + uid + " 无店铺信息");
//            return ResultHelper.genResult(ErrorCodeEnum.FAILED);
//        }
//    }
//
//    @ApiOperation("查看单个店铺")
//    @RequestMapping(value = "/shop", method = RequestMethod.GET)
//    public Result shopBysid(@RequestParam("sid") Long sid,
//                            @RequestParam("token") String token){
//        Long uid = userTokenService.getUid(token);
//
//        if(sid != null && sid > 0){
//            Shop shop = shopManagerService.shopBysid(sid);
//            if(shop != null){
//                return ResultHelper.genResultWithSuccess(shop);
//            }else{
//                logger.error("【空数据错误】获取店铺 " + sid + " 数据失败.");
//            }
//        }else{
//            logger.error("【传入空值】uid为空");
//        }
//        return ResultHelper.genResult(ErrorCodeEnum.FAILED);
//    }
//
//    @ApiOperation("更新店铺信息")
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public Result updateShop(@RequestParam("data") String data,
//                             @RequestParam("sid") Long sid,
//                             @RequestParam("token") String token){
//
//        Long uid = userTokenService.getUid(token);
//        if(uid == null || uid <= 0){
//            return ResultHelper.genResult(ErrorCodeEnum.TOKEN_INVALID);
//        }
//
//        String errorMsg = "";
//        if(StringUtil.areNotEmpty(data)){
//            if((sid != null && sid > 0 )){
//                return ResultHelper.genResultWithSuccess(shopManagerService.update(data, sid));
//            }
//        }else{
//            errorMsg = "数据不能为空";
//        }
//        return ResultHelper.genResult(ErrorCodeEnum.SHOP_UPDATE_FAILED.getCode(),ErrorCodeEnum.SHOP_UPDATE_FAILED.getMsg());
//    }

}
