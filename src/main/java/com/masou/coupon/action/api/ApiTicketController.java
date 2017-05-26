package com.masou.coupon.action.api;

import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.api.BannerService;
import com.masou.coupon.service.api.TicketService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017/5/16.
 */
@RestController
@RequestMapping("/api/ticket")
public class ApiTicketController {

    @Autowired
    private TicketService ticketService;

    private Logger logger = LoggerFactory.getLogger(ApiTicketController.class);


    @ApiOperation("券筛选，根据行业类型，券类型筛选")
    @RequestMapping(value = "/sel_by_type", method = RequestMethod.GET)
    public Result selectByType(@RequestParam("industry") Integer industry,
                               @RequestParam("type") Integer type,
                               @RequestParam("page") Integer page){
        try {
            List<Shop> list = ticketService.selectByType(industry,type,page);
            if(list != null && list.size() > 0){
                return ResultHelper.genResultWithSuccess(list);
            }
        }catch(UserException e){
            logger.error("筛选券失败：" + e.getLocalizedMessage());
        }
        return ResultHelper.genResult(ErrorCodeEnum.SHOP_SEL_BY_TYPE_FAILED);
    }

    @ApiOperation("根据shop id查询券")
    @RequestMapping(value = "/sel_by_sid", method = RequestMethod.GET)
    public Result selectTicketByShopId(@RequestParam("sid") Long sid,
                                       @RequestParam("type")Integer type){


        return null;
    }

    /**
     * 根据用户当前的地理位置坐标，获取距离最近的地方
     * @param longitude
     * @param latitude
     * @return
     */
    @ApiOperation("登录系统展示附近券")
    @RequestMapping(value = "/selectlist", method = RequestMethod.GET)
    public Result selectList(@RequestParam("longitude") String longitude,
                             @RequestParam("latitude") String latitude){
        List<Shop> shopList = null;
        try {
            if(longitude == null || longitude.length() < 1){
                shopList = ticketService.selectList();
            }else{
                shopList = ticketService.selectList(longitude, latitude);
            }
            if(shopList != null && shopList.size() > 0){
                return ResultHelper.genResultWithSuccess(shopList);
            }
        }catch (UserException e){
            logger.error("今日必领列表加载失败："+e.getLocalizedMessage());
        }
        return ResultHelper.genResult(ErrorCodeEnum.SHOP_NECESSERY);
    }

    /**
     *
     * @return
     */
    @ApiOperation("精选店铺")
    @RequestMapping(value = "/bestshop", method = RequestMethod.GET)
    public Result bestShop(){
        try {
            List<Shop> list = ticketService.bestShop();
            if(list != null && list.size() > 0){
                return ResultHelper.genResultWithSuccess(list);
            }
        }catch(UserException e){
            logger.error("请求精选店铺失败：" + e.getLocalizedMessage());
        }
        return ResultHelper.genResult(ErrorCodeEnum.SHOP_BEST_FAILED);
    }
}
