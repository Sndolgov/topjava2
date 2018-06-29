package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        int userId = AuthorizedUser.id();
        log.info("create meal{} user {}", meal, userId);
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void update(Meal meal, int mealId) {
        int userId = AuthorizedUser.id();
        log.info("update meal{} user {}", meal, userId);
        assureIdConsistent(meal, mealId);
        service.update(meal, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete meal{} user {}", id, userId);
        service.delete(id, userId);
    }

    public Meal get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get meal{} user {}", id, userId);
        return service.get(id, userId);
    }

    public List<MealWithExceed> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll meals of user {}", userId);
        return MealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay());
    }

    public List<MealWithExceed> getAllBetween(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        log.info("getAll meals of user {}", userId);
        LocalDate sd = startDate!=null?startDate:LocalDate.MIN;
        LocalDate ed = endDate!=null?endDate:LocalDate.MAX;
        LocalTime st = startTime!=null?startTime:LocalTime.MIN;
        LocalTime et = endTime!=null?endTime:LocalTime.MAX;

        return MealsUtil.getFilteredWithExceeded(service.getAllBetween(userId, sd, ed), AuthorizedUser.getCaloriesPerDay(), st, et);
    }

}