package com.masou.coupon.utils;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by july on 2016/11/27.
 */

@Component
public class DateUtil {
    public final int ISO_DATETIME_FORMAT = 12;
    public final int DB_CHN_DATETIME_FORMAT = 1;

    public static final String ZERO_DATE = " 00:00:00";

    public Date dateIncreaseByDay(Date date, int days) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone
                .getTimeZone("GMT"));
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    public Date toDate(String dateText, int kind) {
        String format = getDateFormat(kind);
        if (dateText == null) {
            return null;
        }
        DateFormat df = null;
        try {
            if (format == null) {
                df = new SimpleDateFormat();
            } else {
                df = new SimpleDateFormat(format);
            }
            df.setLenient(false);
            return df.parse(dateText);
        } catch (ParseException e) {
            return null;
        }
    }

    public String toString(Date date, int kind) {
        if (date == null) {
            return null;
        }
        String format = getDateFormat(kind);
        try {
            SimpleDateFormat sfDate = new SimpleDateFormat(format);
            sfDate.setLenient(false);
            return sfDate.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public String getDate(Date date, int kind) {
        String res = "";
        if (date != null) {
            String format = getDateFormat(kind);
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            res = sdf.format(date);
        }
        return res;
    }

    /**
     * 获取当前时间，格式为2017-06-13 00:00:00
     * @return
     */
    public Date todayOnlyDate(){
        Calendar cal = Calendar.getInstance();
        String today = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE) + ZERO_DATE;
        return toDate(today,1);
    }

    private String getDateFormat(int kind) {
        String[] format = {"yyyy-MM-dd", // 0
                "yyyy-MM-dd HH:mm:ss", // 1
                "yyyy",// 2
                "M",// 3
                "dd", // 4
                "yyyy年M月d日H点m分", // 5
                "yyyy年M月d日", // 6
                "H点m分", // 7
                "yyyy/MM/dd HH:mm", // 8
                "HH",// 9
                "mm",// 10
                "yyyyMMdd", // 11
                "yyyyMMddHHmmss", // 12
                "yyyy-MM-dd 23:59:59", // 13
                "HH:mm:ss", // 14
                "yyyy/MM/dd HH:mm:ss", // 15
                "yyyy/MM/dd HH:mm",//16
                "HHmmss"//17
        };
        return format[kind];
    }


    public int getIntervalDays(Date oldDate, Date newDate) {

        try {
            return daysBetween(oldDate,newDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }


    public boolean isSameDay(Date fDate, Date oDate) {
        if (null == fDate || null == oDate) {

            return false;
        }

        int day = 0;
        try {
            day = daysBetween(fDate, oDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (day == 0) {
            return true;
        } else {
            return false;
        }


    }

    /**
     *
     * @param oldDate 前一天
     * @param newDate 后一天
     * @return
     * @throws ParseException
     */
    public int daysBetween(Date oldDate, Date newDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        oldDate = sdf.parse(sdf.format(oldDate));
        newDate = sdf.parse(sdf.format(newDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(oldDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(newDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     *
     * @param oldDate 前一天
     * @param newDate 后一天
     * @return
     * @throws ParseException
     */
    public int daysBetweenSeconds(Date oldDate, Date newDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        oldDate = sdf.parse(sdf.format(oldDate));
        newDate = sdf.parse(sdf.format(newDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(oldDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(newDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1);
        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 间隔的秒
     * @param oldDate
     * @param newDate
     * @return
     */
    public long secondBetween(Date oldDate,Date newDate){
        long between=(newDate.getTime()-oldDate.getTime())/1000;//除以1000是为了转换成秒


        return between;
    }
}
