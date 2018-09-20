package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.*;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

/*
    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }
*/

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            User oldUser = em.getReference(User.class, user.getId());
            user.setMeals(oldUser.getMeals());
            return em.merge(user);
        }
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {

/*      User ref = em.getReference(User.class, id);
        em.remove(ref);

        Query query = em.createQuery("DELETE FROM User u WHERE u.id=:id");
        return query.setParameter("id", id).executeUpdate() != 0;
*/
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    public List<Meal> getMeals(int id) {
        /*User user = get(id);
        em.refresh(get(id));
        return user.getMeals();*/

        User user = em.createNamedQuery(User.GET_MEALS, User.class)
                .setParameter("id", id)
                .getSingleResult();

       /* List <User> users = em.createNamedQuery(User.GET_MEALS, User.class)
                .setParameter("id", id)
                .getResultList();
        User user = DataAccessUtils.singleResult(users);
        List <Meal> meals = user.getMeals();*/

        return user.getMeals();
    }
}
