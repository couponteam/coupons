package com.masou.coupon.action.erpapi;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.data.models.User;
import com.masou.coupon.service.api.UserManageService;
import com.masou.coupon.service.api.UserTokenService;
import com.masou.coupon.utils.MD5Util;
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
    private MD5Util md5Util;

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

    @ApiOperation("新建/更新管理员")
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public Result insert(@RequestParam("phone") String phone,
                         @RequestParam("name") String name,
                         @RequestParam(value = "id", required = false) Integer id) {
        return userManageService.insertSelective(phone, name, id);
    }

    @ApiOperation("删除管理员")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result delete(@RequestParam("id") Integer id) {
        return userManageService.deleteByPrimaryKey(id);
    }

    @ApiOperation("更新管理员")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestParam(value = "password", required = false) String password,
                         @RequestParam(value = "update", required = false) String update,
                         @RequestParam(value = "nickname", required = false) String nickname,
                         @RequestParam(value = "gender", required = false) String gender,
                         @RequestParam(value = "avatar", required = false) String avatar,
                         @RequestParam("token") String token) {
        Long uid = userTokenService.getUid(token);
        User user = new User();

        if(password != null && password.length() > 0){
            user.setPassword(md5Util.MD5(password));
        }
        user.setId(uid);
//        user.set



        return null;
    }
}
