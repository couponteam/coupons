package com.masou.coupon.action.erpapi;

import com.masou.coupon.common.struct.Result;
import com.masou.coupon.service.api.UserDenyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Paul on 2017/5/11.
 */

@RestController
@RequestMapping("/management/api/userDeny")
public class ErpUserDenyController {

    @Autowired
    private UserDenyService userDenyService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list() {

        return userDenyService.selectAllList();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestParam("id") Integer id) {

        return userDenyService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insert(@RequestParam("phone") String phone,
                         @RequestParam("description") String description) {
        return userDenyService.insertSelective(phone, description);
    }
}
