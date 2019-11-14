package com.project.miu.commons.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 字符串转Date
     * @param time
     * @return
     */
    public static Date string2Date(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }
    /**
     * 将String转成DateTime
     */
    public static Timestamp str2dateTime(String source){
        if (source.isEmpty())
            return null;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(source);
            Timestamp timeStamp = new Timestamp(date.getTime());
            return timeStamp;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 数据库中的Datetime转成java中的Date
     * @param source
     * @return
     */
    public static Date dateTime2Date(Timestamp source){
        Date date = new Date(source.getTime());
        return date;
    }
    /**
     * 将Date转换成String
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        if (date == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
