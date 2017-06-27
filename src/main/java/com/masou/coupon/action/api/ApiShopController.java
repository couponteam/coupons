package com.masou.coupon.action.api;

import com.masou.coupon.action.api.vo.ShopResultVO;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.service.UserLogService;
import com.masou.coupon.service.api.BannerService;
import com.masou.coupon.service.api.ShopService;
import com.masou.coupon.service.api.TicketService;
import com.masou.coupon.service.api.UserTokenService;
import com.masou.coupon.utils.IPUtil;
import com.masou.coupon.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/shop")
public class ApiShopController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IPUtil ipUtil;

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserTokenService userTokenService;

    @ApiOperation("根据shop id查询券")
    @RequestMapping(value = "/sel_by_sid", method = RequestMethod.GET)
    public Result selectTicketByShopId(@RequestParam("sid") Long sid,
                                       @RequestParam(value = "token", required = false) String token,
//                                       @RequestParam("uid") Long uid,
                                       @RequestParam("page") Integer page,
                                       @RequestParam(value = "pageSize", required = false) Integer pageSize){
        if(sid != null &&sid > 0){
            Long uid = userTokenService.getUid(token);
            return ResultHelper.genResultWithSuccess(
                    ticketService.selectTicketByShopId(sid,uid,ipUtil.getIpAddress(request),page,pageSize));
        }
        return ResultHelper.genResult(ErrorCodeEnum.NULL_VALUE_ERROR.getCode(), "传入店铺id"+ErrorCodeEnum.NULL_VALUE_ERROR.getMsg());
    }

    @ApiOperation("用户关注店铺")
    @RequestMapping(value = "/follow", method = RequestMethod.GET)
    public Result section(
            @RequestParam("token") String token,
//              @RequestParam("uid") Long uid,
                          @RequestParam("sid") Long sid,
                          @RequestParam("status") Integer status) {

        Long uid = userTokenService.getUid(token);
        return shopService.follow(uid, sid, status) ;
    }

    @ApiOperation("用户关注店铺列表")
    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    public Result section(@RequestParam("token") String token,
                          @RequestParam("page") Integer page,
//                          @RequestParam("uid") Long uid,
                          @RequestParam(value = "pageSize", required = false) Integer pageSize,
                          @RequestParam(value = "keyword", required = false) String keyword) {

        Long uid = userTokenService.getUid(token);
        ShopResultVO shopResultVOs = shopService.list(uid, page, pageSize, keyword);
        if(shopResultVOs != null){
            return ResultHelper.genResultWithSuccess(shopResultVOs);
        }
        return ResultHelper.genResult(ErrorCodeEnum.FOLLOW_SHOP_NO);
    }
}
