package com.masou.coupon.action.api.vo;

import lombok.Data;


/**
 * Created by Paul on 2017/5/4.
 */
@Data
public class UserProfileVO {

    private String nickname;

    private String phone;

    private String inviteCode;

    private String beInviteCode;

    private String gender;

    private String fromKey;

    private String avatarUrl;

    private String avatar;

}
