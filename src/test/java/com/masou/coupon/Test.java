package com.masou.coupon;

import com.masou.coupon.utils.CommonKeyUtils;
import com.masou.coupon.utils.DateUtil;
import com.masou.coupon.utils.MD5Util;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by jason on 2017/6/24.
 */
public class Test {

    public static DateUtil dateUtil = new DateUtil();

    private static MD5Util md5Util = new MD5Util();

    public static void main(String[] args){

        System.out.println(md5Util.MD5("111"));
        System.out.println(CommonKeyUtils.genUniqueKey());

    }

}
