package com.masou.coupon.service.api;


import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.common.enums.RoleEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.dao.UserManageDao;
import com.masou.coupon.data.models.User;
import com.masou.coupon.data.models.UserManager;
import com.masou.coupon.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Paul on 2017/5/11.
 */
@Service
public class UserManageService {
    @Autowired
    private UserManageDao userManageDao;


    @Autowired
    private UserService userService;


    @Autowired
    private UserTokenService userTokenService;

    public Result deleteByPrimaryKey(Integer id) {
        if (userManageDao.deleteByPrimaryKey(id) > 0) {
            return ResultHelper.genResultWithSuccess();
        } else {
            throw new UserException("删除失败");
        }
    }


    @Transactional
    public Result insertSelective(String phone, String name,Integer id) {
        UserManager record = new UserManager();
        record.setRealName(name);
        record.setPhone(phone);

        if(id == null || id <= 0){
            UserManager manager = userManageDao.selectByPhone(phone);
            //插入新管理员
            if (manager != null) {
                throw new UserException("管理员已经存在");
            }

            User user = userService.selectByPhone(phone);
            if (user == null) {
                userService.register(phone,null, null, "123456", "manager", null, true, RoleEnum.MANAGER.getRole());
                user = userService.selectByPhone(phone);
            }
            record.setEnable(1);
            record.setUid(user.getId());

            int i = userManageDao.insertSelective(record);

            if (i == 1) {
                return ResultHelper.genResultWithSuccess();
            } else {
                throw new UserException("添加失败");
            }
        }else{
            record.setId(id);
            if (updateByPrimaryKeySelective(record) > 0){
                return ResultHelper.genResultWithSuccess(userManageDao.selectByPhone(phone));
            }
            return ResultHelper.genResult(ErrorCodeEnum.FAILED);
        }
    }

    public int updateByPrimaryKeySelective(UserManager record) {
        return userManageDao.updateByPrimaryKeySelective(record);
    }

    public Result selectAllList() {

        return ResultHelper.genResultWithSuccess(userManageDao.selectAllList());
    }


    public boolean isManager(String token) {
        Long uid = userTokenService.getUid(token);
        if (uid != null) {
            UserManager manager = userManageDao.selectByUid(uid);
            if (manager != null) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }


    public Result login(String token, String phone, String password) {

        User user = userService.selectByPhoneAndPassword(phone, password);
        if (user == null) {
            throw new UserException(ErrorCodeEnum.LOGIN_FAILED);
        }
        UserManager manager = userManageDao.selectByPhone(phone);
        if (manager == null) {
            throw new UserException(ErrorCodeEnum.MANAGER_NOT_EXIST);
        }
        userTokenService.updateByLogin(token, user.getId());

//        ManagerLoginVO vo = new ManagerLoginVO();
//        vo.setToken(token);
//        vo.setUserManager(manager);

        return ResultHelper.genResultWithSuccess(manager);
    }
}
