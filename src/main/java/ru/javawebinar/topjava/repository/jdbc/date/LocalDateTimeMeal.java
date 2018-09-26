package ru.javawebinar.topjava.repository.jdbc.date;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Сергей on 25.09.2018.
 */
@Component
@Profile("!hsqldb")
public class LocalDateTimeMeal implements DateTimeMeal<LocalDateTime> {
    @Override
    public LocalDateTime getDate(LocalDateTime ld) {
        return ld;
    }
}
