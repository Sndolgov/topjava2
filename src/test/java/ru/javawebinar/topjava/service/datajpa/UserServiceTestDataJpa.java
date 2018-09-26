package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractServiceTest;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Сергей on 25.09.2018.
 */

@ActiveProfiles(Profiles.DATAJPA)
public class UserServiceTestDataJpa extends AbstractUserServiceTest {

    @Test
    public void getMeals(){
        assertMatch(service.getMeals(USER_ID), MEALS);
    }

    @Test
    public void userWithOutMeals(){
        User newUser = new User(null, "New", "new@gmail.com", "newPass", 1555, false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        assertMatch(service.getMeals(created.getId()), new ArrayList<>());
    }
}
