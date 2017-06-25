package com.masou.coupon.service.api;


import com.masou.coupon.action.api.vo.UserVO;
import com.masou.coupon.action.erpapi.vo.UserListVO;
import com.masou.coupon.data.param.PageParam;
import com.masou.coupon.common.enums.MessageTypeEnum;
import com.masou.coupon.common.struct.Result;
import com.masou.coupon.common.utils.ResultHelper;
import com.masou.coupon.common.enums.ErrorCodeEnum;
import com.masou.coupon.dao.UserDao;
import com.masou.coupon.dao.UserProfileDao;
import com.masou.coupon.data.filter.UserFilter;
import com.masou.coupon.data.models.User;
import com.masou.coupon.data.models.UserProfile;
import com.masou.coupon.exception.UserException;
import com.masou.coupon.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Paul on 2017/5/3.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserProfileDao userProfileDao;


    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private MD5Util md5Util;

    @Autowired
    private PhoneUtil phoneUtil;

    @Autowired
    private PhoneMessageService phoneMessageService;

    @Autowired
    private RandomKeyUtil randomKeyUtil;

    /**
     * 登陆
     *
     * @param token
     * @param phone
     * @param password
     * @return
     */
    public Result loginByPassword(String token, String phone, String password) {
        User user = userDao.selectByPhone(phone);
        if (user != null && user.getPassword().equals(this.encrypt(password))) {

            userTokenService.updateByLogin(token, user.getId());

            UserVO vo = ModelConvertUtil.convert(UserVO.class, user);

            return ResultHelper.genResultWithSuccess(vo);


        } else {
            throw new UserException(ErrorCodeEnum.LOGIN_FAILED);
        }

    }

    @Transactional
    public Result register(String phone, String verify, String password, String fromKey, String beInviteCode,boolean ignoreVerify,Integer role) {
        if (!phoneUtil.isPhone(phone)) {
            throw new UserException(ErrorCodeEnum.SYS_ERROR, "手机号不正确");
        }
        User user = userDao.selectByPhone(phone);
        if (user != null) {
            throw new UserException(ErrorCodeEnum.PHONE_EXIST);
        }

        if (!ignoreVerify) {
            if (!phoneMessageService.checkVerify(phone, verify, MessageTypeEnum.USER_REGISTER.getCode())) {
                throw new UserException(ErrorCodeEnum.WRONG_VERIFY);
            }
        }

        Long uid = CommonKeyUtils.genUniqueKey();

        user = new User(uid, phone, this.encrypt(password), new Date(), role, phone);

        UserProfile profile = new UserProfile();
        profile.setBeInviteCode(beInviteCode);
        profile.setFromKey(fromKey);
        profile.setNickname(phone);
        profile.setPhone(phone);

        while (true) {
            String inviteCode = randomKeyUtil.getRandomString(5);
            User existUser = userDao.selectByInviteCode(inviteCode);
            if (existUser == null) {
                profile.setInviteCode(inviteCode);
                break;
            }
        }

        profile.setUid(uid);

        userDao.insertSelective(user);
        userProfileDao.insertSelective(profile);

        return ResultHelper.genResultWithSuccess();

    }


    public Result changePassword(Long uid, String oldPassword, String newPassword) {
        User user = userDao.selectByPrimaryKey(uid);

        if (!user.getPassword().equals(this.encrypt(oldPassword))) {
            throw new UserException(ErrorCodeEnum.OLD_PASSWORD_WRONG);
        }
        user.setPassword(this.encrypt(newPassword));
        userDao.updateByPrimaryKeySelective(user);

        return ResultHelper.genResultWithSuccess();
    }


    public Result forgetPassword(String phone, String verify, String newPassword) {
        if (!phoneUtil.isPhone(phone)) {
            throw new UserException(ErrorCodeEnum.SYS_ERROR, "手机号不正确");
        }
        User user = userDao.selectByPhone(phone);

        if (user == null) {
            throw new UserException(ErrorCodeEnum.PHONE_NOT_EXIST);
        }

        if (!phoneMessageService.checkVerify(user.getPhone(), verify, MessageTypeEnum.USER_FORGET_PASSWORD.getCode())) {
            throw new UserException(ErrorCodeEnum.WRONG_VERIFY);
        }

        user.setPassword(this.encrypt(newPassword));

        userDao.updateByPrimaryKeySelective(user);

        return ResultHelper.genResultWithSuccess();


    }


//    public Result loginByVerify(String token, String phone, String verify,String fromKey) {
//        if (!phoneUtil.isPhone(phone)) {
//            throw new UserException(ErrorCodeEnum.SYS_ERROR, "手机号不正确");
//        }
//
//        if (!phoneMessageService.checkVerify(phone, verify, MessageTypeEnum.LOGIN.getCode())) {
//            throw new UserException(ErrorCodeEnum.WRONG_VERIFY);
//        }
//
//        User user = userDao.selectByPhone(phone);
//
//        if (user == null) {
//
//
//        }
//
//
//    }


    /**
     * 退出
     *
     * @param token
     * @return
     */
    public Result logout(String token) {
        userTokenService.updateByLogout(token);
        return ResultHelper.genResultWithSuccess();
    }


    public Result info(Long uid) {
        User user = userDao.selectByPrimaryKey(uid);

        return ResultHelper.genResultWithSuccess(ModelConvertUtil.convert(UserVO.class, user));

    }

    public Result info(String token) {
        Long uid = userTokenService.getUid(token);
        return info(uid);
    }

    public User selectByPhone(String phone){
        return userDao.selectByPhone(phone);
    }


    public Result userList(UserFilter filter) {

        Integer total = userDao.selectCountByFilter(filter);
        List<User> list = userDao.selectListByFilter(filter);

        UserListVO vo = new UserListVO();
        vo.setTotal(total);
        vo.setList(list);
        return ResultHelper.genResultWithSuccess(vo);
    }

    public User selectByPhoneAndPassword(String phone, String password) {
        User user = userDao.selectByPhone(phone);
        if (user != null && user.getPassword().equals(encrypt(password))) {
            return user;
        } else {
            return null;
        }
    }


    public Result update(Long uid, String nickname, String gender, String avatar) {
        User user = userDao.selectByPrimaryKey(uid);

        UserProfile profile = user.getProfile();
        if (StringUtil.areNotEmpty(nickname)) {
            profile.setNickname(nickname);
        }
        if (StringUtil.areNotEmpty(gender)) {
            profile.setGender(gender);
        }
        if (StringUtil.areNotEmpty(avatar)) {
            profile.setAvatar(avatar);
        }
        userProfileDao.updateByPrimaryKeySelective(profile);

        return ResultHelper.genResultWithSuccess();

    }


    public String encrypt(String password) {
        return md5Util.MD5(password);
    }

}
