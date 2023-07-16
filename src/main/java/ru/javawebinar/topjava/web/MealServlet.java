package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.InMemoryMealsRepository;
import ru.javawebinar.topjava.storage.MealsRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.convertedByStream;

public class MealServlet extends HttpServlet {
    public static final int CALORIES_PER_DAY = 2000;
    private static final Logger log = getLogger(MealServlet.class);

    private MealsRepository repository;

    @Override
    public void init() {
        repository = new InMemoryMealsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "" : action) {
            case "add":
                Meal newMeal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 0);
                request.setAttribute("meal", newMeal);
                log.debug("action add: forward to update");
                request.getRequestDispatcher("/mealsUpdate.jsp").forward(request, response);
                break;
            case "update":
                int idUpdate = Integer.parseInt(request.getParameter("id"));
                Meal meal = repository.get(idUpdate);
                request.setAttribute("meal", meal);
                log.debug("action update: forward to update");
                request.getRequestDispatcher("/mealsUpdate.jsp").forward(request, response);
                break;
            case "delete":
                int idDelete = Integer.parseInt(request.getParameter("id"));
                repository.delete(idDelete);
                log.debug("action delete: redirect to meals");
                response.sendRedirect("meals");
                break;
            case "":
            default:
                List<MealTo> mealToList = convertedByStream(repository.getAll(), CALORIES_PER_DAY);
                request.setAttribute("mealToList", mealToList);
                log.debug("any action: forward to meals");
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal meal = new Meal(id == null ? null : Integer.valueOf(id), dateTime, description, calories);
        repository.save(meal);
        log.debug("doPost: redirect to meals");
        response.sendRedirect("meals");
    }
}
