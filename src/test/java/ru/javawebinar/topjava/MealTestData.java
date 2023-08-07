package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int NOT_FOUND = 10;
    public static final int MEAL_ID = START_SEQ + 3;
    public static final LocalDate START_DATE = LocalDate.of(2023, Month.JANUARY, 30);
    public static final LocalDate END_DATE = LocalDate.of(2023, Month.JANUARY, 31);
    public static final Meal adminMeal1 = new Meal(MEAL_ID, LocalDateTime.of(2023, Month.JANUARY, 29, 10, 0), "Admin breakfast", 500);
    public static final Meal adminMeal2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2023, Month.JANUARY, 30, 13, 0), "Admin lunch", 1000);
    public static final Meal adminMeal3 = new Meal(MEAL_ID + 2, LocalDateTime.of(2023, Month.JANUARY, 30, 20, 0), "Admin dinner", 500);
    public static final Meal adminMeal4 = new Meal(MEAL_ID + 3, LocalDateTime.of(2023, Month.JANUARY, 31, 0, 0), "Border meals", 100);
    public static final Meal adminMeal5 = new Meal(MEAL_ID + 4, LocalDateTime.of(2023, Month.JANUARY, 31, 10, 0), "Admin breakfast", 1000);
    public static final Meal adminMeal6 = new Meal(MEAL_ID + 5, LocalDateTime.of(2023, Month.JANUARY, 31, 13, 0), "Admin lunch", 500);
    public static final Meal adminMeal7 = new Meal(MEAL_ID + 6, LocalDateTime.of(2023, Month.FEBRUARY, 1, 10, 0), "Admin breakfast", 410);

    public static final List<Meal> adminAllBetweenInclusiveMeals = Arrays.asList(adminMeal6, adminMeal5, adminMeal4,
            adminMeal3, adminMeal2);

    public static final List<Meal> adminAllMeals = Arrays.asList(adminMeal7, adminMeal6, adminMeal5,
            adminMeal4, adminMeal3, adminMeal2, adminMeal1);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2023, Month.AUGUST, 3, 12, 0), "Admin new lunch", 10000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(adminMeal1.getId(), adminMeal1.getDateTime(), adminMeal1.getDescription(), adminMeal1.getCalories());
        updated.setDateTime(LocalDateTime.of(2023, Month.AUGUST, 3, 12, 0));
        updated.setDescription("Admin new breakfast");
        updated.setCalories(990);
        return updated;
    }

    public static LocalDateTime getDateTime() {
        return adminMeal1.getDateTime();
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}

