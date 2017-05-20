package com.masou.coupon.action.shopapi;

import com.masou.coupon.common.struct.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 2017/5/16.
 */
@RestController
@RequestMapping("/management/api/shop")
public class ShopManagerController {


    @ApiOperation("注册店铺")
    @RequestMapping(value = "/regis", method = RequestMethod.POST)
    public Result regisShop(String data){


        return null;
    }

//    @ApiOperation("登陆")
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public Result login(){
//
//
//        return null;
//    }

    @ApiOperation("更新店铺信息")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Result updateShop(String data){


        return null;
    }

    @ApiOperation("新增券")
    @RequestMapping(value = "/regis", method = RequestMethod.POST)
    public Result deleteShop(String sid){


        return null;
    }
}
