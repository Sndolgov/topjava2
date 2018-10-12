package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Сергей on 12.10.2018.
 */
@Controller
@RequestMapping(value="/meals")
public class JspMealController extends MealRestController{


    @PostMapping("/filter")
    public String mealsFiltered(Model model,
                                @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endtDate,
                                @RequestParam(value = "startTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
                                @RequestParam(value = "endTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
        model.addAttribute("meals", getBetween(startDate, startTime, endtDate, endTime));
        return "meals";
    }

    @GetMapping("/delete/{id}")
    public String deleteMeal(@PathVariable int id) {
        delete(id);
        return "redirect:/meals";
    }

    @GetMapping("/create")
    public String createMeal(Model model) {
        model.addAttribute("meal", new Meal());
        return "mealForm";
    }

    @GetMapping("/update/{id}")
    public String updateMeal(@PathVariable int id, Model model) {
        model.addAttribute("meal", get(id));
        return "mealForm";
    }

    @PostMapping("")
    public String saveMeal(@Valid @ModelAttribute(name = "meal") Meal meal,
                           BindingResult result, ModelMap model) {
        if (meal.getId() == null)
            create(meal);
        else update(meal, meal.getId());
        return "redirect:/meals";
    }


    @GetMapping("")
    public String meals(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }
}
