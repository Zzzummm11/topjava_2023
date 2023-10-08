package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=?1 AND m.user.id=?2")
    int delete(int id, int userId);

    @Query("SELECT m FROM Meal m WHERE m.id=?1 AND m.user.id=?2")
    Meal getByIdAndUserId(int id, int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=?1")
    List<Meal> findAll(int userId, Sort sortParam);

    @Query("SELECT m FROM Meal m WHERE m.user.id=?1 AND m.dateTime >= ?2 AND m.dateTime < ?3")
    List<Meal> findAll(int userId, LocalDateTime startDateTime, LocalDateTime endDateTime, Sort sortParam);

    @Query("SELECT m FROM Meal m JOIN FETCH m.user WHERE m.id=?1 AND m.user.id=?2")
    Meal getWithUser(int id, int userId);
}
