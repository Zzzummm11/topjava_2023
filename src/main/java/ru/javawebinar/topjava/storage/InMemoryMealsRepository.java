package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealsRepository implements MealsRepository {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<Integer, Meal> mealsRepository = new ConcurrentHashMap<>();

    public InMemoryMealsRepository() {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2023, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2023, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2023, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2023, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2023, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2023, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2023, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
        for (Meal meal : meals) {
            save(meal);
        }
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.getId() == null) {
            createId(meal);
            mealsRepository.put(meal.getId(), meal);
            return meal;
        }
        return mealsRepository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    private void createId(Meal meal) {
        meal.setId(counter.incrementAndGet());
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
