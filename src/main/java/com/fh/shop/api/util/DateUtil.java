package com.fh.shop.api.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String FULL_TIME = "yyyy-MM-dd HH:mm:ss";

    public static String date2str(Date date,String pattern){
        if(date==null){
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static Date str2Date(String date, String pattern){
        if(StringUtils.isEmpty(date)){
            return new Date();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date2 = null;
        try {
            date2 = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date2;
    }
}
