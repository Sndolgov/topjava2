package ru.javawebinar.topjava.web.meal;

import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.List;

/**
 * Created by Сергей on 22.12.2018.
 */
@RestController
@RequestMapping("/ajax/meals")
public class MealAjaxController extends AbstractMealController {

    @Override
    @GetMapping
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    public void save(@RequestBody Meal meal) {
        if (meal.isNew())
            super.create(meal);
        else super.update(meal, meal.getId());
    }
}
