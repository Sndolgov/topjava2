package ru.javawebinar.topjava.util.data;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

/**
 * Created by Сергей on 27.10.2018.
 */
public class TimeFormatter implements Formatter<LocalTime> {
    @Override
    public LocalTime parse(String text, Locale locale) throws ParseException {
        if (text != null) {
            return parseLocalTime(text);
        }
        return null;
    }

    @Override
    public String print(LocalTime object, Locale locale) {
        return object.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
