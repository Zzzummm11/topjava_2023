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
    public static final Meal MEAL_ADMIN_1 = new Meal(MEAL_ID, LocalDateTime.of(2023, Month.JANUARY, 29, 10, 0), "Admin breakfast", 500);
    public static final Meal MEAL_ADMIN_2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2023, Month.JANUARY, 30, 13, 0), "Admin lunch", 1000);
    public static final Meal MEAL_ADMIN_3 = new Meal(MEAL_ID + 2, LocalDateTime.of(2023, Month.JANUARY, 30, 20, 0), "Admin dinner", 500);
    public static final Meal MEAL_ADMIN_4 = new Meal(MEAL_ID + 3, LocalDateTime.of(2023, Month.JANUARY, 31, 0, 0), "Border meals", 100);
    public static final Meal MEAL_ADMIN_5 = new Meal(MEAL_ID + 4, LocalDateTime.of(2023, Month.JANUARY, 31, 10, 0), "Admin breakfast", 1000);
    public static final Meal MEAL_ADMIN_6 = new Meal(MEAL_ID + 5, LocalDateTime.of(2023, Month.JANUARY, 31, 13, 0), "Admin lunch", 500);
    public static final Meal MEAL_ADMIN_7 = new Meal(MEAL_ID + 6, LocalDateTime.of(2023, Month.FEBRUARY, 1, 10, 0), "Admin breakfast", 410);

    public static final List<Meal> MEAL_ALL_BETWEEN_ADMIN = Arrays.asList(MEAL_ADMIN_6, MEAL_ADMIN_5, MEAL_ADMIN_4,
            MEAL_ADMIN_3, MEAL_ADMIN_2);

    public static final List<Meal> MEAL_ALL_ADMIN = Arrays.asList(MEAL_ADMIN_7, MEAL_ADMIN_6, MEAL_ADMIN_5,
            MEAL_ADMIN_4, MEAL_ADMIN_3, MEAL_ADMIN_2, MEAL_ADMIN_1);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2023, Month.AUGUST, 3, 12, 0), "Admin new lunch", 10000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL_ADMIN_1.getId(), MEAL_ADMIN_1.getDateTime(), MEAL_ADMIN_1.getDescription(), MEAL_ADMIN_1.getCalories());
        updated.setDateTime(LocalDateTime.of(2023, Month.AUGUST, 3, 12, 0));
        updated.setDescription("Admin new breakfast");
        updated.setCalories(990);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}

