package com.systemk.thefactor2.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    public static String camelToSnake(String str)
    {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        str = str.replaceAll(regex, replacement).toLowerCase();
        return str;
    }

    public static String dateFormat(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return format.format(date);
    }

    public static String dateFormat(String form, Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat(form);
        return format.format(date);
    }

    public static String dateFormatYMD(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }




}
