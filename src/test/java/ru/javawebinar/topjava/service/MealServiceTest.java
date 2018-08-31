package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Сергей on 31.08.2018.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    MealService service;

    @Test
    public void get() throws Exception {
        assertMatch(MEAL_1, service.get(MEAL_1_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void getError() throws Exception {
        assertMatch(MEAL_1, service.get(MEAL_1_ID, ADMIN_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(ADMIN_MEAL_1_ID, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), ADMIN_MEAL_2);
    }

    @Test(expected = NotFoundException.class)
    public void deleteError() throws Exception {
        service.delete(ADMIN_MEAL_1_ID, USER_ID);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(ADMIN_ID), ADMIN_MEAL_2, ADMIN_MEAL_1);
    }

    @Test
    public void update() throws Exception {
        Meal update = MealTestData.getUpdatedMeal();
        service.update(update, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), ADMIN_MEAL_2, update);
    }

    @Test
    public void create() throws Exception {
        Meal create = MealTestData.getNewMeal();
        service.create(create, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), ADMIN_MEAL_2, ADMIN_MEAL_1, create);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        LocalDateTime start = LocalDateTime.of(2015, Month.MAY, 30, 10, 0);
        LocalDateTime end = LocalDateTime.of(2015, Month.MAY, 30, 17, 0);
        assertMatch(service.getBetweenDateTimes(start, end, USER_ID), MEAL_2, MEAL_1);
    }


}