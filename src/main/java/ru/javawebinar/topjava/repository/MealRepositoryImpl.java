package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.DataBase.MealDataBase;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Сергей on 02.04.2018.
 */
public class MealRepositoryImpl implements MealRepository {
    @Override
    public void delete(int id) {
        MealDataBase.setMeals(MealDataBase.getMeals().stream()
                .filter(meal -> meal.getId()!=id)
                .collect(Collectors.toList()));
    }
}
