package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Сергей on 01.04.2018.
 */
public class DateUtil {
    private DateUtil() {}


    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime==null?"":localDateTime.toString().replace('T', ' ');
    }
}
