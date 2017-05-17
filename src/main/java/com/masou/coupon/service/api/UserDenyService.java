package com.masou.coupon.service.api;


import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.UserDenyDao;
import com.masou.coupon.data.models.User;
import com.masou.coupon.data.models.UserDeny;
import com.masou.coupon.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Paul on 2017/5/11.
 */
@Service
public class UserDenyService {


    @Autowired
    private UserDenyDao userDenyDao;

    @Autowired
    private UserService userService;

    public Result deleteByPrimaryKey(Integer id) {
        if (userDenyDao.deleteByPrimaryKey(id) == 1) {
            return ResultHelper.genResultWithSuccess();
        } else {
            throw new UserException("删除失败");
        }
    }


    @Transactional
    public Result insertSelective(String phone,String description) {

        User user = userService.selectByPhone(phone);
        if (user==null){
            throw new UserException("用户不存在");
        }
        UserDeny deny = userDenyDao.selectByPhone(phone);
        if (deny!=null){
            throw new UserException("黑名单已经存在");
        }

        UserDeny record = new UserDeny();
        record.setPhone(phone);
        record.setDescription(description);
        if (userDenyDao.insertSelective(record) == 1) {
            return ResultHelper.genResultWithSuccess();
        } else {
            throw new UserException("添加失败");
        }
    }

    public UserDeny selectByPrimaryKey(Integer id) {

        return userDenyDao.selectByPrimaryKey(id);
    }

    public UserDeny selectByPhone(String phone) {
        return userDenyDao.selectByPhone(phone);
    }


    public Result selectAllList() {
        return ResultHelper.genResultWithSuccess(userDenyDao.selectAllList());
    }
}
