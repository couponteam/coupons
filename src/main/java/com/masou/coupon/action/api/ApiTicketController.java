package com.masou.coupon.action.api;

import com.masou.coupon.action.api.vo.ShopResultVO;
import com.masou.coupon.action.api.vo.ShopTicketVO;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.api.TicketService;
import com.masou.coupon.service.api.UserTokenService;
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
@RequestMapping("/api/ticket")
public class ApiTicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserTokenService userTokenService;

    private Logger logger = LoggerFactory.getLogger(ApiTicketController.class);

    private static final double DISTANCE_RADIUS = 500;

    @ApiOperation("券筛选，根据行业类型，券类型筛选")
    @RequestMapping(value = "/sel_by_type", method = RequestMethod.GET)
    public Result selectByType(@RequestParam("industry") Integer industry,
                               @RequestParam("type") Integer type,
                               @RequestParam("page") Integer page,
                               @RequestParam("pageSize") Integer pageSize){
        try {
            List<ShopTicketVO> list = ticketService.selectByType(industry,type,page,pageSize);
            if(list != null && list.size() > 0) {
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
                                       @RequestParam("token") String token){
        if(sid != null &&sid > 0){
            Long uid = userTokenService.getUid(token);
            if(uid == null || uid <= 0){
                return ResultHelper.genResultWithSuccess(ticketService.selectTicketByShopId(sid,uid));
            }
            return ResultHelper.genResultWithSuccess(ticketService.selectTicketByShopId(sid));
        }
        return ResultHelper.genResult(ErrorCodeEnum.NULL_VALUE_ERROR.getCode(), "传入店铺id"+ErrorCodeEnum.NULL_VALUE_ERROR.getMsg());
    }

    @ApiOperation("用户领取券，读取券，使用券，放弃券接口")
    @RequestMapping(value = "/colet_ticket", method = RequestMethod.GET)
    public Result userCollectTicket(@RequestParam("token") String token,
                                    @RequestParam("tid") Long tid,
                                    @RequestParam("status") Integer status){

        Long uid = userTokenService.getUid(token);
        if(uid == null || uid <= 0){
            return ResultHelper.genResult(ErrorCodeEnum.TOKEN_INVALID);
        }

        if(ticketService.userCollectTicket(uid,tid,status) > 0){
            return ResultHelper.genResultWithSuccess();
        }else{
            String msg = "";

            switch (status){
                case(1):
                    msg = "查看券错误";
                case(2):
                    msg = "领取券失败";
                case(3):
                    msg = "使用券失败";
                case(4):
                    msg = "放弃券失败";
            }
            return ResultHelper.genResult(ErrorCodeEnum.USER_TICKET_OPEATE_FAILED.getCode(),msg);
        }
    }

    /**
     * 根据用户当前的地理位置坐标，获取距离最近的地方
     * @param longitude
     * @param latitude
     * @return
     */
    @ApiOperation("登录系统展示附近券")
    @RequestMapping(value = "/selectlist", method = RequestMethod.GET)
    public Result selectList(@RequestParam("longitude") float longitude,
                             @RequestParam("latitude") float latitude,
                             @RequestParam("token") String token){

        Long uid = userTokenService.getUid(token);
        if(uid == null || uid <= 0){
            ;
        }

        ShopResultVO shopResultVO = null;
        try {
            if(Math.abs(longitude) > 0 && Math.abs(latitude) > 0){
                shopResultVO = ticketService.selectList();
            }else{
                shopResultVO = ticketService.selectList(longitude, latitude,DISTANCE_RADIUS);
            }
            if(shopResultVO != null){
                return ResultHelper.genResultWithSuccess(shopResultVO);
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
