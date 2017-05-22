package com.masou.coupon.utils;


import java.util.UUID;

/**
 * Created by jason on 2017/5/21.
 */
public class GenTicketIdUtil {

    public static String genTicketId(){
        StringBuffer id = new StringBuffer();
        try {

            UUID uuid = UUID.randomUUID();

            return uuid.toString().replaceAll("-","");
//            Long date = System.currentTimeMillis();
//            System.out.println(date);

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
