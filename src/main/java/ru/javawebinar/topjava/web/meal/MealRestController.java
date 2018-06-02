package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal create(Meal meal, int userId){
        log.info("create meal{} user {}", meal, userId);
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void update(Meal meal, int mealId, int userId){
        log.info("update meal{} user {}", meal, userId);
        assureIdConsistent(meal, mealId);
        service.update(meal, userId);
    }

    public void delete(int id, int userId){
        log.info("delete meal{} user {}", id, userId);
        service.delete(id, userId);
    }

    public Meal get(int id, int userId) {
        log.info("get meal{} user {}", id, userId);
        return service.get(id, userId);
    }

    public List<Meal> getAll(int userId){
        log.info("getAll meals of user {}", userId);
        return service.getAll(userId);
    }

}