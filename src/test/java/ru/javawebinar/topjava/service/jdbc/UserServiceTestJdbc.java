package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

/**
 * Created by Сергей on 25.09.2018.
 */
@ActiveProfiles(Profiles.JDBC)
public class UserServiceTestJdbc extends AbstractUserServiceTest {
}