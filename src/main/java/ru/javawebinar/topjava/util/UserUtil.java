package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Сергей on 10.04.2018.
 */
public class UserUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(null, "User", "user@mail.ru", "password", Role.ROLE_USER),
            new User(null, "Admin", "admin@mail.ru", "password", Role.ROLE_USER, Role.ROLE_ADMIN)    );
}
