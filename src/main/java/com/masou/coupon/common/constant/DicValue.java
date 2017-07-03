package com.masou.coupon.common.constant;

/**
 * Created by jason on 2017/6/16.
 */
public class DicValue {

    /** 与券相关的 **/
    public static final Integer TICKET_STATUS_READ = 1;
    public static final Integer TICKET_STATUS_GOT = 2;
    public static final Integer TICKET_STATUS_USED = 3;
    public static final Integer TICKET_STATUS_GIVEUP = 4;

    /** 店铺相关**/
    public static final Byte SHOP_USER_FOCUS = 1;
    public static final Byte SHOP_USER_UNFOCUS = 2;


    public static final Integer TICKET_IS_RETAKEN = 1;



    public static final Integer TICKET_PUT_AWAY = 1;
    /** 券过期 */
    public static final Integer TICKET_EXPIRED = 2;
    /** 券下架 */
    public static final Integer TICKET_SOLD_OUT = 3;

    //用户申请店铺的状态
    public static final Integer APPLY_SHOP_DOING = 1;
    public static final Integer APPLY_SHOP_WAITTING = 2;
    public static final Integer APPLY_SHOP_DONE = 3;
    public static final Integer APPLY_SHOP_CANCLE = -1;

}
