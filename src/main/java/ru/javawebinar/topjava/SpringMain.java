package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;

import static ru.javawebinar.topjava.util.UsersUtil.ADMIN;
import static ru.javawebinar.topjava.util.UsersUtil.ADMIN_ID;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            User newUser = adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));
            System.out.println("New user: "+newUser);
//            adminUserController.update(new User(null, "userUpdated", "email", "password", Role.ROLE_ADMIN), 100003);
//            System.out.println("Updated user: "+adminUserController.get(100002));
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            mealRestController.update(MealsUtil.updateMeal(), MealsUtil.updateMeal().getId(), ADMIN_ID);
            System.out.println("Admin meal: "+ mealRestController.getAll(ADMIN_ID));
            mealRestController.delete(8, ADMIN_ID);
            System.out.println("Admin meal: "+ mealRestController.getAll(ADMIN_ID));
        }
    }
}
