package com.masou.coupon.action.api;

import com.masou.coupon.common.enums.RoleEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.service.UserLogService;
import com.masou.coupon.service.api.UserService;
import com.masou.coupon.service.api.UserTokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jason on 2017/5/16.
 */
@RestController
@RequestMapping("/api/user")
public class ApiUserController {


    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserLogService userLogService;

    /**
     * 创建token
     *
     * @param token
     * @return
     */
    @ApiOperation("创建token")
    @RequestMapping(value = "/token/create", method = RequestMethod.POST)
    public Result tokenCreate(@RequestParam(value = "token", required = false) String token) {
        return ResultHelper.genResultWithSuccess(userTokenService.create(token));
    }


    @ApiOperation("账号密码登陆")
    @RequestMapping(value = "/loginByPassword", method = RequestMethod.POST)
    public Result loginByPassword(@RequestParam("token") String token,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("password") String password) {

        return userService.loginByPassword(token, phone, password);
    }

    @ApiOperation("注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestParam("phone") String phone,
                           @RequestParam("verify") String verify,
                           @RequestParam("password") String password,
                           @RequestParam("fromKey") String fromKey,
                           @RequestParam(value = "beInviteCode", required = false) String beInviteCode) {

        return userService.register(phone, null, verify, password, fromKey, beInviteCode, false, RoleEnum.USER.getRole());
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

        return userService.forgetPassword(phone, verify, password);
    }

    @ApiOperation("退出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result logout(@RequestParam("token") String token) {
        userService.logout(token);

        return ResultHelper.genResultWithSuccess();
    }


    @ApiOperation("用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result info(@RequestParam("token") String token) {
        return userService.info(token);
    }


    @ApiOperation("更新个人信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestParam(value = "token") String token,
                         @RequestParam(value = "nickname", required = false) String nickname,
                         @RequestParam(value = "gender", required = false) String gender,
                         @RequestParam(value = "avatar", required = false) String avatar) {

        Long uid = userTokenService.getUid(token);
        return userService.update(uid, nickname, gender, avatar);

    }


}
