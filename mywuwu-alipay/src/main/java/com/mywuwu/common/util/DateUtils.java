package com.mywuwu.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Package: com.mywuwu.quartz.utils
 * Description： 时间格式化类
 * Author: 梁乐乐
 * Date: Created in 2018/5/20 11:33
 * Company: clinbrain
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 */
public class DateUtils {
    public static final String ENG_DATE_FROMAT = "EEE, d MMM yyyy HH:mm:ss z";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY = "yyyy";
    public static final String MM = "MM";
    public static final String DD = "dd";


    /**
     * Description： 格式化日期对象
     *
     * @param date      日期类型
     * @param formatStr 格式化日期
     *                  Author: 梁乐乐
     *                  Date: Created in 2018/5/20 11:40
     */
    public static Date date2date(Date date, String formatStr) {

        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String str = sdf.format(date);
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
        return date;
    }


    /**
     * Description： 时间对象转换成字符串
     *
     * @param date      日期
     * @param formatStr 格式化
     *                  Author: 梁乐乐
     *                  Date: Created in 2018/5/20 11:39
     */
    public static String date2string(Date date, String formatStr) {

        String strDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        strDate = sdf.format(date);
        return strDate;
    }

    /**
     * Description： sql时间对象转换成字符串
     *
     * @param timestamp sql时间
     * @param formatStr 格式化时间
     *                  Author: 梁乐乐
     *                  Date: Created in 2018/5/20 11:38
     */
    public static String timestamp2string(Timestamp timestamp, String formatStr) {

        String strDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        strDate = sdf.format(timestamp);
        return strDate;
    }


    /**
     * Description：  字符串转换成时间对象
     *
     * @param dateString 日期字符串
     * @param formatStr  格式化字符串
     *                   Author: 梁乐乐
     *                   Date: Created in 2018/5/20 11:37
     */
    public static Date string2date(String dateString, String formatStr) {

        Date formateDate = null;
        DateFormat format = new SimpleDateFormat(formatStr);
        try {
            formateDate = format.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
        return formateDate;
    }


    /**
     * Description：  Date类型转换为Timestamp类型
     *
     * @param date 日期时间
     *             Author: 梁乐乐
     *             Date: Created in 2018/5/20 11:37
     */
    public static Timestamp date2timestamp(Date date) {

        if (date == null)
            return null;
        return new Timestamp(date.getTime());
    }


    /**
     * Description：  获得当前年份
     * Author: 梁乐乐
     * Date: Created in 2018/5/20 11:36
     */
    public static String getNowYear() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY);
        return sdf.format(new Date());
    }


    /**
     * Description： 获得当前月份
     * Author: 梁乐乐
     * Date: Created in 2018/5/20 11:36
     */
    public static String getNowMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat(MM);
        return sdf.format(new Date());
    }


    /**
     * Description： 获得当前日期中的日
     * Author: 梁乐乐
     * Date: Created in 2018/5/20 11:35
     */
    public static String getNowDay() {
        SimpleDateFormat sdf = new SimpleDateFormat(DD);
        return sdf.format(new Date());
    }

    /**
     * Description： 指定时间距离当前时间的中文信息
     *
     * @param time 时间
     *             Author: 梁乐乐
     *             Date: Created in 2018/5/20 11:35
     */

    public static String getLnow(long time) {

        Calendar cal = Calendar.getInstance();
        long timel = cal.getTimeInMillis() - time;
        if (timel / 1000 < 60) {
            return "1分钟以内";
        } else if (timel / 1000 / 60 < 60) {
            return timel / 1000 / 60 + "分钟前";
        } else if (timel / 1000 / 60 / 60 < 24) {
            return timel / 1000 / 60 / 60 + "小时前";
        } else {
            return timel / 1000 / 60 / 60 / 24 + "天前";
        }
    }
}
