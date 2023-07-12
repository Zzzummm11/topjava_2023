package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.MealsInMemoryRepository;
import ru.javawebinar.topjava.storage.MealsRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.*;
import static ru.javawebinar.topjava.util.TimeUtil.FORMATTER;

public class MealServlet extends HttpServlet {
    public static final int CALORIES_PER_DAY = 2000;
    private static final Logger log = getLogger(MealServlet.class);
    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private MealsRepository repository;

    @Override
    public void init() {
        repository = new MealsInMemoryRepository();
        List<Meal> meals = Arrays.asList(
                new Meal(COUNTER.incrementAndGet(), LocalDateTime.of(2023, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(COUNTER.incrementAndGet(), LocalDateTime.of(2023, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(COUNTER.incrementAndGet(), LocalDateTime.of(2023, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(COUNTER.incrementAndGet(), LocalDateTime.of(2023, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(COUNTER.incrementAndGet(), LocalDateTime.of(2023, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(COUNTER.incrementAndGet(), LocalDateTime.of(2023, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(COUNTER.incrementAndGet(), LocalDateTime.of(2023, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
        for (Meal meal : meals) {
            repository.save(meal);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        List<MealTo> mealToList = convertedByStream(repository.getAll(), CALORIES_PER_DAY);

        if (action == null) {
            request.setAttribute("mealToList", mealToList);
            log.debug("action == null: forward to meals");
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "add":
                Meal newMeal = new Meal(COUNTER.incrementAndGet());
                request.setAttribute("meal", newMeal);
                request.setAttribute("currentAction", "Add");
                log.debug("action add: forward to update");
                request.getRequestDispatcher("/update.jsp").forward(request, response);
                break;
            case "update":
                int idUpdate = Integer.parseInt(request.getParameter("id"));
                Meal meal = repository.get(idUpdate);
                request.setAttribute("meal", meal);
                request.setAttribute("currentAction", "Edit");
                log.debug("action update: forward to update");
                request.getRequestDispatcher("/update.jsp")
                        .forward(request, response);
                break;
            case "delete":
                int idDelete = Integer.parseInt(request.getParameter("id"));
                repository.delete(idDelete);
                log.debug("action delete: redirect to meals");
                response.sendRedirect("meals");
                break;
            default:
                request.setAttribute("mealToList", mealToList);
                log.debug("any action: forward to meals");
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), FORMATTER);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal meal = new Meal(id, dateTime, description, calories);
        repository.save(meal);
        log.debug("doPost: redirect to meals");
        response.sendRedirect("meals");
    }
}
