package com.masou.coupon.action.erpapi;

import com.masou.coupon.action.param.PageParam;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.service.api.ShopService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 系统审核
 * Created by Paul on 2017/5/20.
 */
@RestController
@RequestMapping("/management/api/approve")
public class ErpApproveController {

    @Autowired
    private ShopService shopService;

    @ApiOperation("请求店铺列表")

    @ApiImplicitParam(name = "verifyType",value = "商铺是否通过审核：0-待审核；1-已审核；2-未通过；3-已通过",paramType = "query",dataType = "Integer")
    @GetMapping("/shopList")
    public Result shopList(@RequestParam("verifyType")Integer verifyType,
                           @RequestParam("page")Integer page,
                           @RequestParam("pageSize")Integer pageSize){
        PageParam pageParam = new PageParam();
        pageParam.setPage(page);
        pageParam.setPageSize(pageSize);
        return shopService.selectShopList(pageParam,verifyType);
    }

    @ApiOperation("审核店铺")
    @PostMapping("/approve")
    @ApiImplicitParam(name = "verifyType",value = "2-未通过；3-已通过",dataType = "String")
    public Result approve(@RequestParam("shopId") Long shopId,
                          @RequestParam("verifyType")Integer verifyType){

        return shopService.approve(shopId,verifyType);
    }


}
