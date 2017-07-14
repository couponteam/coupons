package com.masou.coupon.action.api;

import com.masou.coupon.action.api.vo.ShopResultVO;
import com.masou.coupon.action.api.vo.ShopTicketVO;
import com.masou.coupon.common.constant.BeValue;
import com.masou.coupon.common.constant.DicValue;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.filter.LocaltionFilter;
import com.masou.coupon.data.models.LogUserShop;
import com.masou.coupon.data.models.LogUserVisit;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.data.models.UserTicket;
import com.masou.coupon.data.param.PageParam;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.UserLogService;
import com.masou.coupon.service.api.TicketService;
import com.masou.coupon.service.api.UserTokenService;
import com.masou.coupon.utils.IPUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    private UserLogService userLogService;

    @Autowired
    private IPUtil ipUtil;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserTokenService userTokenService;

    private Logger logger = LoggerFactory.getLogger(ApiTicketController.class);

    private static final double DISTANCE_RADIUS = 5000;

    @ApiOperation("券筛选，根据行业类型，券类型筛选")
    @RequestMapping(value = "/sel_by_type", method = RequestMethod.GET)
    public Result selectByType(@RequestParam("industry") Integer industry,
                               @RequestParam("type") Integer type,
                               @RequestParam("page") Integer page,
                               @RequestParam("pageSize") Integer pageSize){
        userLogService.userLogs(request,BeValue.FROM_KEY_APP);
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

    @ApiOperation("用户领取券")
    @RequestMapping(value = "/get_t", method = RequestMethod.GET)
    public Result userCollectTicket(
                                    @RequestParam(value = "token", required = false) String token,
                                    @RequestParam(value = "tid", required = false) String tid,
                                    @RequestParam(value = "fcode", required = false) String fcode,
                                    @RequestParam(value = "sid", required = false) Long sid
                                    ){
        userLogService.userLogs(request, BeValue.FROM_KEY_APP);
        Long uid = userTokenService.getUid(token);
        return ticketService.userCollectTicket(uid,tid, DicValue.TICKET_STATUS_GOT, fcode, sid);
    }

    @ApiOperation("转发领取")
    @RequestMapping(value = "/forward", method = RequestMethod.GET)
    public Result forwardTicket(@RequestParam("sid") Long sid,
                                @RequestParam("token") String token){
        userLogService.userLogs(request,BeValue .FROM_KEY_APP);
        Long uid = userTokenService.getUid(token);
        return ticketService.forwardTicket(uid,sid,ipUtil.getIpAddress(request));
    }

    @ApiOperation("用户读取券")
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public Result userReadTicket(@RequestParam(value = "token", required = false) String token,
                                     @RequestParam("tid") String tid,
                                     @RequestParam(value = "status",required = false) Integer status){
        userLogService.userLogs(request,BeValue.FROM_KEY_APP);
        Long uid = userTokenService.getUid(token);
        ShopTicketVO shopTicketVO = ticketService.userReadTicket(uid,tid,status,ipUtil.getIpAddress(request));
        if(shopTicketVO != null){
            return ResultHelper.genResultWithSuccess(shopTicketVO);
        }
        return ResultHelper.genResult(ErrorCodeEnum.USER_TICKET_READ_FAILED);
    }

    @ApiOperation("人气推荐")
    @RequestMapping(value = "/poplist", method = RequestMethod.GET)
    public Result popShopList(@RequestParam("page") Integer page,
                              @RequestParam(value = "pageSize", required = false) Integer pageSize){
        userLogService.userLogs(request,BeValue.FROM_KEY_APP);
        return ticketService.popShopList(page, pageSize);
    }

    @ApiOperation("我的券列表")
    @RequestMapping(value = "/my_t", method = RequestMethod.GET)
    public Result myTicket(@RequestParam("page") Integer page,
                           @RequestParam(value = "pageSize", required = false) Integer pageSize,
                           @RequestParam("token") String token,
//                           @RequestParam(value = "uid",required = false) Long uid,
                           @RequestParam(value = "status", required = false) Integer status){
        userLogService.userLogs(request,BeValue.FROM_KEY_APP);
        Long uid = userTokenService.getUid(token);
        return ticketService.myTicket(page,pageSize,uid,status);
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
                             @RequestParam("latitude") String latitude,
                             @RequestParam( value = "industry", required = false) Integer industry,
                             @RequestParam(value = "type", required = false) Integer type,
                             @RequestParam("page") Integer page,
//                             @RequestParam("uid") Long uid,
                             @RequestParam(value = "pageSize",required = false) Integer pageSize,
                             @RequestParam(value = "keyword", required = false) String keyword,
                             @RequestParam(value = "token", required = false) String token,
                             @RequestParam(value = "radius", required = false) Double radius,
                             @RequestParam(value = "isFollow", required = false) String isFollow){
        userLogService.userLogs(request,BeValue.FROM_KEY_APP);
        ShopResultVO shopResultVO = null;
        try {
            LocaltionFilter params = new LocaltionFilter();
            params.setType(type);
            params.setIndustry(industry);
            params.setOffset(page);
            params.setLimit(pageSize);
            params.setRadius(radius);
            try {
                Long uid = userTokenService.getUid(token);
                params.setUid(uid);
            }catch(Exception e){
                e.printStackTrace();
                return ResultHelper.genResult(ErrorCodeEnum.SHOP_NECESSERY);
            }

            if (pageSize == null){
                params.setLimit(new PageParam().getPageSize());
            }
            if(keyword != null && keyword.trim().length() > 0){
                params.setKeyword(keyword);
            }
            if(Math.abs(Float.parseFloat(longitude)) <= 0 && Math.abs(Float.parseFloat(latitude)) <= 0){
                logger.info("" + longitude + "-" +  latitude);
                //定位失败
                return ResultHelper.genResult(ErrorCodeEnum.LOCATION_FAILED);
            }else{
                double innerRadius = DISTANCE_RADIUS;
                if(radius != null){
                    innerRadius = radius;
                }
                 shopResultVO = ticketService.selectList(params,
                        Float.parseFloat(longitude),
                        Float.parseFloat(latitude), innerRadius);
                if(shopResultVO == null){
                    return ResultHelper.genResult(ErrorCodeEnum.SHOP_NECESSERY);
                }else{
                    return ResultHelper.genResultWithSuccess(shopResultVO);
                }
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
    public Result bestShop(@RequestParam(value =  "pageSize", required = false) Integer pageSize){
        userLogService.userLogs(request,BeValue.FROM_KEY_APP);
        try {
            List<Shop> list = ticketService.bestShop(pageSize);
            if(list != null && list.size() > 0){
                return ResultHelper.genResultWithSuccess(list);
            }
        }catch(UserException e){
            logger.error("请求精选店铺失败：" + e.getLocalizedMessage());
        }
        return ResultHelper.genResult(ErrorCodeEnum.SHOP_BEST_FAILED);
    }
}
