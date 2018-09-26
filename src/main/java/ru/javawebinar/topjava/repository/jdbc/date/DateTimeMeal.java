package ru.javawebinar.topjava.repository.jdbc.date;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by Сергей on 25.09.2018.
 */

public interface DateTimeMeal<T> {
    T getDate(LocalDateTime ld);
}
