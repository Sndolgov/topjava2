package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Сергей on 10.04.2018.
 */
public class UsersUtil {

    public static final Integer USER_ID = 100000;
    public static final Integer ADMIN_ID = 100001;


    public static final User USER = new User(null, "User", "user@mail.ru", "password", Role.ROLE_USER);
   // public static final User USER2 = new User(null, "User", "aser@mail.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(null, "Admin", "admin@mail.ru", "password", Role.ROLE_USER, Role.ROLE_ADMIN);
  //  public static final User USER3 = new User(null, "User", "bser@mail.ru", "password", Role.ROLE_USER);

    public static User updateUser(){
        return new User(ADMIN_ID, "updatedAdmin", "admin@mail.ru", "password", Role.ROLE_USER, Role.ROLE_ADMIN);
    }


    public static final List<User> USERS = Arrays.asList(USER, ADMIN);
}
