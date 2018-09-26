package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Override
    Meal save(Meal meal);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=?1 AND m.user.id=?2")
    int delete(int id, int userId);

    @Modifying
    @Query("SELECT m FROM Meal m WHERE m.id=?1 AND m.user.id=?2")
    List<Meal> get(int id, int userId);

    @Override
    Optional<Meal> findById(Integer integer);

    @Modifying
    @Query("SELECT m FROM Meal m WHERE m.user.id=?1 ORDER BY m.dateTime DESC")
    List<Meal> getAll(int userId);

    @Modifying
    @Query("SELECT m FROM Meal m " +
            "WHERE m.user.id=?3 AND m.dateTime BETWEEN ?1 AND ?2 ORDER BY m.dateTime DESC")
    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    @Modifying
    @Query("SELECT m FROM Meal m left join fetch m.user WHERE m.id=?1 ")
    List<Meal> withUser(int id);

}
