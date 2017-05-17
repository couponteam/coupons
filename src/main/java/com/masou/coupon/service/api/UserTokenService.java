package com.masou.coupon.service.api;

import com.masou.coupon.common.constant.BeValue;
import com.masou.coupon.data.models.UserToken;
import com.masou.coupon.service.RedisService;
import com.masou.coupon.utils.JsonUtil;
import com.masou.coupon.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Paul on 2017/5/3.
 */
@Service
public class UserTokenService {

    private static int TOKEN_EXPIRE_TIME = 60 * 60 * 24 * 7;
    @Autowired
    RedisService redisService;

    /**
     * 新建token
     *
     * @return
     */
    public String create(String token) {
        if (StringUtil.isEmpty(token)) {//创建token

            String newToken = UUID.randomUUID().toString().replaceAll("-", "");
            redisService.set(BeValue.TOKEN_PREFIX + newToken, buildUserTokenStr(0L, 0, System.currentTimeMillis() / 1000), TOKEN_EXPIRE_TIME);
            return newToken;
        } else {//刷新token

            String tokenStr = redisService.get(BeValue.TOKEN_PREFIX + token);
            if (StringUtil.areNotEmpty(tokenStr)) {
                refreshToken(token);
            } else {
                String newToken = UUID.randomUUID().toString().replaceAll("-", "");
                redisService.set(BeValue.TOKEN_PREFIX + newToken, buildUserTokenStr(0L, 0, System.currentTimeMillis() / 1000), TOKEN_EXPIRE_TIME);
                return newToken;
            }
            return token;
        }
    }


    public UserToken getUserToken(String token) {
        String tokenStr = redisService.get(BeValue.TOKEN_PREFIX + token);
        UserToken userToken = JsonUtil.getObjectFromJson(tokenStr, UserToken.class);
        return userToken;
    }

    public Long getUid(String token) {
        String tokenStr = redisService.get(BeValue.TOKEN_PREFIX + token);
        UserToken userToken = JsonUtil.getObjectFromJson(tokenStr, UserToken.class);
        if (StringUtil.isEmpty(tokenStr) || userToken == null) {
            return null;
        }
        return userToken.getUid();
    }

    /**
     * 登陆时更新token
     *
     * @param token
     * @param uid
     */
    public void updateByLogin(String token, Long uid) {
        String tokenStr = redisService.get(BeValue.TOKEN_PREFIX + token);
        UserToken userToken = JsonUtil.getObjectFromJson(tokenStr, UserToken.class);
        userToken.setUid(uid);
        redisService.set(BeValue.TOKEN_PREFIX + token, JsonUtil.getJsonFromObject(userToken), TOKEN_EXPIRE_TIME);
    }


    public void refreshToken(String token) {
        String tokenStr = redisService.get(BeValue.TOKEN_PREFIX + token);
        UserToken userToken = JsonUtil.getObjectFromJson(tokenStr, UserToken.class);
        redisService.set(BeValue.TOKEN_PREFIX + token, JsonUtil.getJsonFromObject(userToken), TOKEN_EXPIRE_TIME);

    }

    /**
     * 登出时更新token
     *
     * @param token 令牌
     */
    public void updateByLogout(String token) {
        String tokenStr = redisService.get(BeValue.TOKEN_PREFIX + token);
        UserToken userToken = JsonUtil.getObjectFromJson(tokenStr, UserToken.class);
        userToken.setIsVerified(0);
        userToken.setUid(0L);
        redisService.set(BeValue.TOKEN_PREFIX + token, JsonUtil.getJsonFromObject(userToken), TOKEN_EXPIRE_TIME);
    }

    /**
     * 构建token字符串
     *
     * @param uid        用户id
     * @param isVerified 是否认证
     * @param time       使用时间
     * @return
     */
    public String buildUserTokenStr(Long uid, Integer isVerified, Long time) {
        UserToken userToken = new UserToken();
        userToken.setUid(uid);
        userToken.setIsVerified(isVerified);
        userToken.setTime(time);
        return JsonUtil.getJsonFromObject(userToken);
    }

}
