package ru.javawebinar.topjava.repository.jdbc.date;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

import static ru.javawebinar.topjava.util.DateTimeUtil.LdtToDate;

/**
 * Created by Сергей on 25.09.2018.
 */
@Component
@Profile("hsqldb")
public class DateMeal implements DateTimeMeal<Date> {
    @Override
    public Date getDate(LocalDateTime ld) {
        return LdtToDate(ld);
    }
}
