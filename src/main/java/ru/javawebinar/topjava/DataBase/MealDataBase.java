package ru.javawebinar.topjava.DataBase;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Сергей on 02.04.2018.
 */
public final class MealDataBase {
    private static AtomicInteger id = new AtomicInteger(0);

    private static List<Meal> meals = Arrays.asList(
            new Meal(id.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(id.getAndIncrement(),LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(id.getAndIncrement(),LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(id.getAndIncrement(),LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(id.getAndIncrement(),LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(id.getAndIncrement(),LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    private MealDataBase() {
    }

    public static List<Meal> getMeals() {
        return meals;
    }

    public static void setMeals(List<Meal> meals) {
        MealDataBase.meals = meals;
    }
}
