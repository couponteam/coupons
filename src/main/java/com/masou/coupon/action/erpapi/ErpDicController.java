package com.masou.coupon.action.erpapi;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.service.shopapi.ErpDicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 2017/5/21.
 */
@RestController
@RequestMapping("/management/api/dic")
public class ErpDicController {

    @Autowired
    private ErpDicService erpDicService;

    /**
     * 获取数据库中的字典表信息
     * @return
     */
    @ApiOperation("券类型ticket type")
    @RequestMapping(value = "/ticket_type", method = RequestMethod.GET)
    public Result ticketType(){

        return ResultHelper.genResultWithSuccess(erpDicService.ticketType());
    }

    @ApiOperation("店铺行业类型")
    @RequestMapping(value = "/industry_type", method = RequestMethod.GET)
    public Result industryType(){

        return ResultHelper.genResultWithSuccess(erpDicService.industryType());
    }

    @ApiOperation("服务开通区域")
    @RequestMapping(value = "/district_type", method = RequestMethod.GET)
    public Result districtType(){
        return ResultHelper.genResultWithSuccess(erpDicService.districtType());
    }
}
