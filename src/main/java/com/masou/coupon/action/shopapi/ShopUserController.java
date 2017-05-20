package com.masou.coupon.action.shopapi;

import com.masou.coupon.common.enums.RoleEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.api.ShopService;
import com.masou.coupon.service.api.UserService;
import com.masou.coupon.service.api.UserTokenService;
import com.masou.coupon.utils.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.codehaus.groovy.tools.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jason on 2017/5/16.
 */
@RestController
@RequestMapping("/shop/api")
public class ShopUserController {


    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;


    @Autowired
    private UserTokenService userTokenService;

    @ApiOperation("商家申请")
    @ApiImplicitParam(name = "industryId", value = "店铺所属行业类型",paramType = "query",dataType = "String")
    @PostMapping("/apply")
    public Result apply(@RequestParam("token") String token,
                        @RequestParam("briefIntro") String briefIntro,
                        @RequestParam("phone") String phone,
                        @RequestParam("shopName") String shopName,
                        @RequestParam("shopAddress") String shopAddress,
                        @RequestParam("industryId") Integer industryId) {


        Long uid = userTokenService.getUid(token);
        Shop record = new Shop();

        record.setBriefIntro(briefIntro);
        record.setPhone(phone);
        record.setShopName(shopName);
        record.setShopAddress(shopAddress);
        record.setIndustryId(industryId);


        return shopService.apply(record, uid);


    }


    @ApiOperation("商家注册")
    @PostMapping("/register")
    public Result register(@RequestParam("phone") String phone,
                           @RequestParam("verify") String verify,
                           @RequestParam("password") String password) {

        return userService.register(phone, verify, password, "shop", null, false, RoleEnum.SHOP_OWNER.getRole());

    }


    @ApiOperation("商家登陆")
    @PostMapping("/login")
    public Result login(@RequestParam("token") String token,
                        @RequestParam("phone") String phone,
                        @RequestParam("password") String password) {


        return shopService.login(token, phone, password);

    }

    @PostMapping("/logout")
    public Result logout(@RequestParam("token") String token) {

        return shopService.logout(token);
    }


}
