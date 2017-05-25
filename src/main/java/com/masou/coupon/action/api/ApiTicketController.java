package com.masou.coupon.action.api;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.service.api.BannerService;
import com.masou.coupon.service.api.TicketService;
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
@RequestMapping("/api/ticket")
public class ApiTicketController {

    @Autowired
    private TicketService ticketService;


    @ApiOperation("根据shop id查询券")
    @RequestMapping(value = "/sel_by_sid", method = RequestMethod.GET)
    public Result selectTicketByShopId(@RequestParam("sid") Long sid,
                                       @RequestParam("type")Integer type){



        return null;
    }

    @ApiOperation("登录系统展示附近券")
    @RequestMapping(value = "/selectlist", method = RequestMethod.GET)
    public Result selectList(@RequestParam("longitude") String longitude,
                             @RequestParam("latitude") String latitude){

        if(longitude == null || longitude.length() < 1){

        }
        ticketService.selectList(longitude, latitude);

        return null;

    }

    @ApiOperation("精选店铺")
    @RequestMapping(value = "/bestshop", method = RequestMethod.GET)
    public Result bestShop(){



        return null;
    }
}
