package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.exeption.NotExistStorageException;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MealsInMemoryRepository implements Storage {

    private final List<Meal> list = Collections.synchronizedList(new ArrayList<>());

    private Meal findById(final int id) {
        synchronized (list) {
            for (Meal meal : list) {
                if (meal.getId() == id) {
                    return meal;
                }
            }
            return null;
        }
    }

    @Override
    public void save(final Meal meal) {
        synchronized (list) {
            Meal existingMeal = findById(meal.getId());
            if (existingMeal != null) {
                list.remove(existingMeal);
            }
            list.add(meal);
        }
    }

    @Override
    public Meal get(final int id) {
        synchronized (list) {
            Meal meal = findById(id);
            if (meal == null) {
                throw new NotExistStorageException(id);
            }
            return meal;
        }
    }

    @Override
    public void delete(final int id) {
        synchronized (list) {
            Meal meal = findById(id);
            if (meal == null) {
                throw new NotExistStorageException(id);
            }
            list.remove(meal);
        }
    }

    @Override
    public List<Meal> convertToList() {
        synchronized (list) {
            return list;
        }
    }
}
