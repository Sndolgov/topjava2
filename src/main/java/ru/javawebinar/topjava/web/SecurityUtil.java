package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.AuthorizedUser;

/**
 * Created by Сергей on 12.10.2018.
 */
public class SecurityUtil {
    public static void setAuthUserId(int userId) {
        AuthorizedUser.setId(userId);
    }

    public static int authUserId() {
        return AuthorizedUser.id();
    }

    public static int authUserCaloriesPerDay() {
        return AuthorizedUser.getCaloriesPerDay();
    }
}
