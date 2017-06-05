package com.masou.coupon.action.api;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.service.api.BannerService;
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
@RequestMapping("/api/shop")
public class ApiShopController {

    @Autowired
    private BannerService bannerService;

    /**
     * 请求banner列表
     * @param section
     * @return
     */
    @ApiOperation("广告位列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result section(@RequestParam("section") String section) {
        return ResultHelper.genResultWithSuccess(bannerService.selectListBySection(section));
    }
}
