package com.wtxy.familyeducation.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: yiwenhui
 * @Date: 2020/2/29
 * @Describe:
 */
public class DateUtils {

    /**
     *  获取当前时间 格式：2020-02-01 12:30
     * @return
     */
    public static String getCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(new Date());
    }

    public static String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
