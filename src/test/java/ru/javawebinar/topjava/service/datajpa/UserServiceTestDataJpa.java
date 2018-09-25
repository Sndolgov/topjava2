package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractServiceTest;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

/**
 * Created by Сергей on 25.09.2018.
 */
@ActiveProfiles(Profiles.DATAJPA)
public class UserServiceTestDataJpa extends AbstractUserServiceTest {
}
