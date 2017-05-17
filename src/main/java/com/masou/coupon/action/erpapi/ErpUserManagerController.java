package com.masou.coupon.action.erpapi;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.service.api.UserManageService;
import com.masou.coupon.service.api.UserTokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Paul on 2017/5/11.
 */
@RestController
@RequestMapping("/management/api/manager")
public class ErpUserManagerController {

    @Autowired
    private UserManageService userManageService;

    @Autowired
    private UserTokenService userTokenService;


    @ApiOperation("管理员登录")

    @RequestMapping(value = "/login", method = RequestMethod.POST)

    public Result login(@RequestParam("token") String token,
                        @RequestParam("phone") String phone,
                        @RequestParam("password") String password) {

        return userManageService.login(token, phone, password);
    }


    @ApiOperation("管理员退出")

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result logout(@RequestParam("token") String token) {


        userTokenService.updateByLogout(token);
        return ResultHelper.genResultWithSuccess();
    }

    @ApiOperation("管理员列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list() {
        return userManageService.selectAllList();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insert(@RequestParam("phone") String phone,
                         @RequestParam("name") String name) {


        return userManageService.insertSelective(phone, name);
    }


    @ApiOperation("删除管理员")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestParam("id") Integer id) {

        return userManageService.deleteByPrimaryKey(id);
    }

}
