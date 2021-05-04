package pl.coderslab.web;

import pl.coderslab.dao.MealInPlanDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/plan/deleteMealFromPlan")
public class DeleteMealFromPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int planId = Integer.parseInt(request.getParameter("planId"));

        MealInPlanDao m = new MealInPlanDao();
        m.delete(id);

        response.sendRedirect("/app/plan/details?planId="+planId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
