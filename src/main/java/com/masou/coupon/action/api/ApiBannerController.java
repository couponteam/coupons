package com.masou.coupon.action.api;

import com.masou.coupon.common.constant.BeValue;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.service.UserLogService;
import com.masou.coupon.service.api.BannerService;
import com.masou.coupon.utils.IPUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Paul on 2017/4/29.
 */
@RestController
@RequestMapping("/api/banner")
public class ApiBannerController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 请求banner列表
     *
     * @param section
     * @return
     */
    @ApiOperation("广告位列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result section(@RequestParam("section") String section) {
        userLogService.userLogs(request, BeValue.FROM_KEY_APP);
        return ResultHelper.genResultWithSuccess(bannerService.selectListBySection(section));
    }
}
