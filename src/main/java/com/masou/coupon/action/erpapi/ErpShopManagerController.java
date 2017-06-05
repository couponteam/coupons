package com.masou.coupon.action.erpapi;

import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.service.shopapi.ShopManagerService;
import com.masou.coupon.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 2017/5/16.
 */
@RestController
@RequestMapping("/shop/api/shopmag")
public class ErpShopManagerController {

    @Autowired
    private ShopManagerService shopManagerService;

    @ApiOperation("查看店铺列表")
    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    public Result shopList(@RequestParam("uid") Long uid){



        return null;
    }

    @ApiOperation("查看单个店铺")
    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public Result shopBysid(@RequestParam("sid") Long sid){


        return null;
    }


    @ApiOperation("注册店铺")
    @RequestMapping(value = "/regis", method = RequestMethod.POST)
    public Result regisShop(@RequestParam("data") String data){
        String errorMsg = ErrorCodeEnum.SHOP_UPDATE_FAILED.getMsg();
//        if(uid != null && uid > 0){
//            Shop shop = shopManagerService.regis(data);
//            if(shop != null && shop.getId() > 0){
//                return ResultHelper.genResultWithSuccess(shop);
//            }
//        }
        return ResultHelper.genResult(ErrorCodeEnum.SHOP_REGIS_FAILED.getCode(), ErrorCodeEnum.SHOP_REGIS_FAILED.getMsg());
    }

    @ApiOperation("更新店铺信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateShop(@RequestParam("data") String data, @RequestParam("sid") Long sid){
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
    public Result deleteShop(String sid){


        return null;
    }
}
