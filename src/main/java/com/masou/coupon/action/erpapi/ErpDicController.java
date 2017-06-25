package com.masou.coupon.action.erpapi;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.filter.AddressFilter;
import com.masou.coupon.service.shopapi.ErpDicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @ApiOperation("券类型ticket type")
    @RequestMapping(value = "/t_update", method = RequestMethod.GET)
    public Result ticketTypeUpdate(
            @RequestParam(value = "comment", required = false) String comment,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "icon", required = false) String icon,
            @RequestParam(value = "id", required = false) Integer id){
        return erpDicService.ticketTypeUpdate(id,comment,status,icon);
    }

    @ApiOperation("店铺行业类型查看")
    @RequestMapping(value = "/industry_type", method = RequestMethod.GET)
    public Result industryType(){
        return ResultHelper.genResultWithSuccess(erpDicService.industryType());
    }

    @ApiOperation("店铺行业类型更新")
    @RequestMapping(value = "/i_update", method = RequestMethod.GET)
    public Result industryTypeUpdate(
            @RequestParam(value = "comment", required = false) String comment,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "icon", required = false) String icon,
            @RequestParam(value = "id", required = false) Integer id
    ){
        return erpDicService.update(id,comment,status,icon);
    }

    @ApiOperation("服务开通区域")
    @RequestMapping(value = "/district_type", method = RequestMethod.GET)
    public Result districtType(){
        return ResultHelper.genResultWithSuccess(erpDicService.districtType());
    }

    @ApiOperation("地址筛选")
    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public Result selectAdress(@RequestParam(value = "province", required = false) Integer province,
                               @RequestParam(value = "city", required = false) Integer city
                                ){
        AddressFilter ads = new AddressFilter();
        if(province != null){
            //通过省id，查询城市列表
            ads.setProvince(province);
            return erpDicService.cityList(ads);
        }else if (city != null){
            //通过城市id，获取区列表
            ads.setCity(city);
            return erpDicService.discList(ads);
        }else{
            return erpDicService.provinceList(ads);
        }
    }
}
