package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Collections;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {
    public static final User ADMIN = new User(1, "ADMIN", "", "", 100, true, Collections.singletonList(Role.ADMIN));
    public static final User USER = new User(2, "USER", "", "", 2000, true, Collections.singletonList(Role.USER));
    private static int userId;

    public static int authUserId() {
        return userId;
    }

    public static void setAuthUserId(final int userId) {
        SecurityUtil.userId = userId;
    }

    public static int authUserCaloriesPerDay() {
        switch (userId) {
            case 1:
                return ADMIN.getCaloriesPerDay();
            case 2:
                return USER.getCaloriesPerDay();
            default:
                return DEFAULT_CALORIES_PER_DAY;
        }
    }
}