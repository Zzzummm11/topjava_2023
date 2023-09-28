package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=?1 AND m.user.id=?2")
    int delete(int id, int userId);

    Optional<Meal> findByIdAndUserId(int id, int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=?1")
    List<Meal> findAll(int userId, Sort sortDateTimeDesc);

    @Query("SELECT m FROM Meal m WHERE m.user.id=?1 AND m.dateTime >= ?2 AND m.dateTime < ?3")
    List<Meal> findAll(int userId, LocalDateTime startDateTime, LocalDateTime endDateTime, Sort sortDateTimeDesc);

}
