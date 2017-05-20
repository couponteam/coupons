package com.masou.coupon.action.erpapi;


import com.masou.coupon.action.param.PageParam;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Paul on 2017/5/4.
 */
@RestController
@RequestMapping("/management/api/user")
public class ErpUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result list(PageParam pageParam,
                       @RequestParam(value = "fromKey", required = false) String fromKey,
                       @RequestParam(value = "phone", required = false) String phone,
                       @RequestParam(value = "timeBegin", required = false) String timeBegin,
                       @RequestParam(value = "timeEnd", required = false) String timeEnd) {


        return userService.userList(pageParam, fromKey, phone, timeBegin, timeEnd);
    }


}
