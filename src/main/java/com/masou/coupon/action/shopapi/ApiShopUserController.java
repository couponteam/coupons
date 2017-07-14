package com.masou.coupon.action.shopapi;

import com.masou.coupon.common.constant.BeValue;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.enums.RoleEnum;
import com.masou.coupon.common.enums.ShopVerifyEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.models.Shop;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.service.UserLogService;
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

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jason on 2017/5/16.
 */
@RestController
@RequestMapping("/shop/api/user")
public class ApiShopUserController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserLogService userLogService;


    @ApiOperation("商家申请")
    @ApiImplicitParam(name = "industryId", value = "店铺所属行业类型", paramType = "query", dataType = "String")
    @PostMapping("/apply")
    public Result apply(
            @RequestParam(value = "shopId", required = false) Long shopId,
            @RequestParam(value = "briefIntro", required = false) String briefIntro,
            @RequestParam("phone") String phone,
            @RequestParam("businessLicenseId") String businessLicenseId,
            @RequestParam("shopName") String shopName,
            @RequestParam("shopAddress") String shopAddress,
            @RequestParam("industryId") Integer industryId,
            @RequestParam("longitude") String longitude,
            @RequestParam("latitude") String latitude,
            @RequestParam("token") String token) {
        userLogService.userLogs(request,BeValue.FROM_KEY_WEB);
        Long uid = userTokenService.getUid(token);
        if (uid == null || uid <= 0) {
            return ResultHelper.genResult(ErrorCodeEnum.TOKEN_INVALID);
        }

        Shop record = new Shop();

        if (shopId != null) {
            record.setId(shopId);
        }
        record.setBusinessLicenseId(businessLicenseId);
        record.setBriefIntro(briefIntro);
        record.setPhone(phone);
        record.setShopName(shopName);
        record.setShopAddress(shopAddress);
        record.setIndustryId(industryId);
        record.setUid(uid);
        //设置店铺默认头像
        record.setIconId(BeValue.SHOP_DEFAULT_IMG);
        if(longitude != null && longitude.trim().length() > 0){
            record.setLongitude(Float.parseFloat(longitude));
            record.setDimensionality(Float.parseFloat(latitude));
        }else{
            return ResultHelper.genResult(ErrorCodeEnum.NULL_VALUE_ERROR);
        }
        return shopService.apply(record);
    }

    @ApiOperation("商家更新店铺信息")
    @PostMapping("/update")
    public Result update(@RequestParam(value = "shopId") Long shopId,
                         @RequestParam(value = "iconId",required = false)String iconId,
                         @RequestParam(value = "briefIntro", required = false) String briefIntro,
                         @RequestParam(value = "profilePicture",required = false) String profilePicture,
                         @RequestParam(value = "shopName",required = false) String shopName,
                         @RequestParam(value = "shopAddress",required = false) String shopAddress
                         ) {
        userLogService.userLogs(request,BeValue.FROM_KEY_WEB);
        Shop shop = shopService.selectByPrimaryKey(shopId);
        if (shop==null){
            throw new UserException("店铺不存在");
        }
        if (shop.getIsShopVerified() == ShopVerifyEnum.UN_PASSED.getCode()){
            throw new UserException("未通过审核");
        }
        if (shop.getIsShopVerified() == ShopVerifyEnum.PRE_VERIFY.getCode()){
            throw new UserException("审核中");
        }

        shop.setIconId(iconId);
        shop.setBriefIntro(briefIntro);
        shop.setProfilePicture(profilePicture);
        shop.setShopName(shopName);
        shop.setShopAddress(shopAddress);
        return shopService.updateByPrimaryKeySelective(shop);
    }

    @ApiOperation("商家注册")
    @PostMapping("/register")
    public Result register(@RequestParam("phone") String phone,
                           @RequestParam(value = "verify", required = false) String verify,
                           @RequestParam(value = "username", required = false) String username,
                           @RequestParam(value = "password", required = false) String password
                           ) {
        userLogService.userLogs(request,BeValue.FROM_KEY_WEB);
        return userService.register(phone,username, verify, password, "shop", null, false, RoleEnum.SHOP_OWNER.getRole());
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public Result changePassword(@RequestParam("token") String token,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword) {
        Long uid = userTokenService.getUid(token);

        return userService.changePassword(uid, oldPassword, newPassword);

    }

    @ApiOperation("忘记密码")
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public Result forgetPassword(@RequestParam("phone") String phone,
                                 @RequestParam("verify") String verify,
                                 @RequestParam("newPassword") String password) {
        userLogService.userLogs(request,BeValue.FROM_KEY_WEB);
        return userService.forgetPassword(phone, verify, password);
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
