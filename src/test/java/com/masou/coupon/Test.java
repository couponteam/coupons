package com.masou.coupon;

import com.masou.coupon.utils.DateUtil;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by jason on 2017/6/24.
 */
public class Test {

    public static DateUtil dateUtil = new DateUtil();

    public static void main(String[] args){

        System.out.println(new Date().toString());

        try {

            Date date = dateUtil.dateIncreaseByDay(new Date(),1);

            System.out.println(dateUtil.daysBetween(date, new Date()));


        }catch (ParseException e){

        }

    }

}
