package ru.javawebinar.topjava;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;
import static ru.javawebinar.topjava.util.UsersUtil.USER_ID;

public class AuthorizedUser {

    private static int id = USER_ID;

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

    public static int id() {
        return id;
    }

    public static int getCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}