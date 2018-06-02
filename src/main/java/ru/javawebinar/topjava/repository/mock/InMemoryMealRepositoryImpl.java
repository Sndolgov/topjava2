package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.javawebinar.topjava.util.UsersUtil.*;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static AtomicInteger counter = new AtomicInteger(0);
    public static Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, USER_ID));
        MealsUtil.ADMIN_MEALS.forEach(meal -> save(meal, ADMIN_ID));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals==null? null: meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getAllAsSteam(userId).collect(Collectors.toList());

    }

    @Override
    public List<Meal> getAllBetween(int userId, LocalDate startDate, LocalDate endDate) {
        return getAllAsSteam(userId).
                filter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                .collect(Collectors.toList());
    }

    public Stream<Meal> getAllAsSteam(int userId){
        Map<Integer, Meal> meals = repository.get(userId);
        return meals==null? Stream.empty():meals.values().stream().sorted(Comparator.comparing(Meal::getDateTime).reversed());
    }

/*
    public static void main(String[] args) {
        InMemoryMealRepositoryImpl repository = new InMemoryMealRepositoryImpl();
        repository.save(MealsUtil.updateMeal(), ADMIN_ID);
        System.out.println(repository.getAll(ADMIN_ID));
        System.out.println(repository.get(8, ADMIN_ID));
        System.out.println(repository.getAllBetween(USER_ID, LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 30)));
    }
*/
}

