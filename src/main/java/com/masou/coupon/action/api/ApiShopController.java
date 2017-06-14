package com.masou.coupon.action.api;

import com.masou.coupon.action.api.vo.ShopResultVO;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.service.UserLogService;
import com.masou.coupon.service.api.BannerService;
import com.masou.coupon.service.api.ShopService;
import com.masou.coupon.service.api.UserTokenService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/shop")
public class ApiShopController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserTokenService userTokenService;

//    /**
//     * 请求banner列表
//     * @param section
//     * @return
//     */
//    @ApiOperation("广告位列表")
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public Result section(@RequestParam("section") String section) {
//        return ResultHelper.genResultWithSuccess(bannerService.selectListBySection(section));
//    }

    @ApiOperation("用户关注店铺")
    @RequestMapping(value = "/follow", method = RequestMethod.GET)
    public Result section(@RequestParam("token") String token,
                          @RequestParam("sid") Long sid,
                          @RequestParam("status") Integer status) {

        Long uid = userTokenService.getUid(token);
        if(shopService.follow(uid, sid, status) > 0){
            return ResultHelper.genResultWithSuccess();
        }
        return ResultHelper.genResult(ErrorCodeEnum.FOLLOW_SHOP_FAILED);
    }

    @ApiOperation("用户关注店铺列表")
    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    public Result section(
//            @RequestParam("token") String token,
                          @RequestParam("page") Integer page,
                          @RequestParam("pageSize") Integer pageSize,
                          @RequestParam("uid") Long uid) {

//        Long uid = userTokenService.getUid(token);
        ShopResultVO shopResultVOs = shopService.list(uid, page, pageSize);
        if(shopResultVOs != null){
            return ResultHelper.genResultWithSuccess(shopResultVOs);
        }
        return ResultHelper.genResult(ErrorCodeEnum.FAILED);
    }
}
