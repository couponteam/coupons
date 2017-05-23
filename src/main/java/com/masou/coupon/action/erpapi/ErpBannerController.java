package com.masou.coupon.action.erpapi;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.models.Banner;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.api.BannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Paul on 2017/5/3.
 */
@RestController
@RequestMapping("/management/api/banner")
public class ErpBannerController {


    @Autowired
    private BannerService bannerService;

    @ApiOperation("插入广告位")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insert(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "weight") int weight,
            @RequestParam(value = "image") String image,
            @RequestParam(value = "action") String action,
            @RequestParam(value = "section") String section
    ) {
        Banner banner = new Banner();
        banner.setWeight(weight);
        banner.setImage(image);
        banner.setAction(action);
        banner.setSection(section);

        if (id != null) {
            banner.setId(id);
        }

        int i = bannerService.insertSelective(banner);

        if (i == 1) {
            return ResultHelper.genResultWithSuccess();
        } else {
            throw new UserException("操作失败");
        }
    }


    @ApiOperation("删除广告位")
    @PostMapping("/delete")
    public Result delete(@RequestParam("id") Integer id) {
        bannerService.deleteByPrimaryKey(id);
        return ResultHelper.genResultWithSuccess();
    }

    @ApiOperation("广告位列表")
    @GetMapping("/list")
    public Result list(@RequestParam("section") String section) {
        return ResultHelper.genResultWithSuccess(bannerService.selectListBySection(section));
    }

}
