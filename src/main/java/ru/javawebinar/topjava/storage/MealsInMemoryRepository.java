package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MealsInMemoryRepository implements MealsRepository {

    private final Map<Integer, Meal> mealsRepository = new ConcurrentHashMap<>();

    @Override
    public Meal save(final Meal meal) {
        mealsRepository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public Meal get(final int id) {
        return mealsRepository.get(id);
    }

    @Override
    public void delete(final int id) {
        mealsRepository.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealsRepository.values());
    }

}
