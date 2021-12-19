package com.bng.ddaja.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {
    private static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    private DateUtil() {}

    public static Date addMilliSecond(Date date, long milliSecond) {
        if(date == null) return null;
        return new Date(date.getTime() + milliSecond);
    }
    public static Date addSeconds(Date date, int second) {
        if(date == null) return null;
        return new Date(date.getTime() + (Long.valueOf(second) * 1000));
    }

    public static Date addMinutes(Date date, int minute) {
        return addSeconds(date, minute * 60);
    }

    public static Date addHours(Date date, int hour) {
        return addSeconds(date, hour * 60 * 60);
    }

    public static Date addDays(Date date, int day) {
        return addSeconds(date, day * 60 * 60 * 24);
    }
}
