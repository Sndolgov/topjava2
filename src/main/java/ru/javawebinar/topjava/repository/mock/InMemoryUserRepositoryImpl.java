package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.UsersUtil.ADMIN_ID;
import static ru.javawebinar.topjava.util.UsersUtil.USER_ID;


@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    public static AtomicInteger counter = new AtomicInteger(100000);
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();

    {
        //   UsersUtil.USERS.forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.getAndIncrement());
            repository.put(user.getId(), user);
            return user;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(repository.values()).stream()
                .sorted((o1, o2) -> {
                    int compare = o1.getName().compareTo(o2.getName());
                    if (compare != 0)
                        return compare;
                    return o1.getEmail().compareTo(o2.getEmail());
                })
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return repository.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny().orElse(null);
    }

    public static void main(String[] args) {
        InMemoryUserRepositoryImpl repository = new InMemoryUserRepositoryImpl();
        repository.delete(100002);
        repository.save(UsersUtil.updateUser());
        System.out.println(repository.getAll());
        //  System.out.println(repository.get(ADMIN_ID));
    }
}
