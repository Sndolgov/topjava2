package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DataBase.MealDataBase;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Сергей on 01.04.2018.
 */
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("get all meals");
        MealRepositoryImpl repository = new MealRepositoryImpl();
        String action = request.getParameter("action");
        if (action != null) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            switch (action) {
                case "delete":
                    repository.delete(id);
                    break;
            }
        }
        request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(MealDataBase.getMeals(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

}
