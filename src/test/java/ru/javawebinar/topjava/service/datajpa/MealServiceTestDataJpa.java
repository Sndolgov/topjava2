package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.UserTestData.*;

/**
 * Created by Сергей on 25.09.2018.
 */
@ActiveProfiles(Profiles.DATAJPA)
public class MealServiceTestDataJpa extends AbstractMealServiceTest {

    @Test
    public void getUser(){
        assertMatch(service.getUser(MEAL1_ID), USER);
    }
}
