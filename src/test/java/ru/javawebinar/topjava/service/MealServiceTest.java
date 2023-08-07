package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal created = service.create(getNew(), UserTestData.ADMIN_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, UserTestData.ADMIN_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(MealTestData.getDateTime(), "Admin new breakfast", 514), UserTestData.ADMIN_ID));
    }

    @Test
    public void get() {
        Meal meal = service.get(MealTestData.MEAL_ID, UserTestData.ADMIN_ID);
        assertMatch(meal, adminMeal1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(MealTestData.NOT_FOUND, UserTestData.ADMIN_ID));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(MealTestData.MEAL_ID, UserTestData.USER_ID));
    }

    @Test
    public void delete() {
        service.delete(MealTestData.MEAL_ID, UserTestData.ADMIN_ID);
        assertThrows(NotFoundException.class, () -> service.get(MealTestData.MEAL_ID, UserTestData.ADMIN_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(MealTestData.NOT_FOUND, UserTestData.ADMIN_ID));
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(MealTestData.MEAL_ID, UserTestData.USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> allBetweenInclusive = service.getBetweenInclusive(MealTestData.START_DATE, MealTestData.END_DATE,
                UserTestData.ADMIN_ID);
        assertMatch(allBetweenInclusive, MealTestData.adminAllBetweenInclusiveMeals);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(UserTestData.ADMIN_ID);
        assertMatch(all, MealTestData.adminAllMeals);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, UserTestData.ADMIN_ID);
        assertMatch(service.get(MealTestData.MEAL_ID, UserTestData.ADMIN_ID), getUpdated());
    }

    @Test
    public void updateNotFound() {
        Meal updated = getUpdated();
        updated.setId(MealTestData.NOT_FOUND);
        assertThrows(NotFoundException.class, () -> service.update(updated, UserTestData.ADMIN_ID));
    }

    @Test
    public void updateNotOwn() {
        Meal updated = getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(updated, UserTestData.USER_ID));
    }
}