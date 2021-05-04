package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.MealInPlanDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.MealInPlan;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/app/plan/details")
public class PlanDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if((boolean) session.getAttribute("superadmin")){
            boolean isSuperAdmin = (boolean) session.getAttribute("superadmin");
            request.setAttribute("superAdmin", isSuperAdmin);
        }
//        int planId = 6;
        int planId = Integer.parseInt(request.getAttribute("planId").toString());
        session.setAttribute("firstName" , (String)session.getAttribute("firstName"));

        PlanDao p = new PlanDao();
        Plan plan = p.read(planId);

        MealInPlanDao m = new MealInPlanDao();
        List<MealInPlan> mealsInPlan = m.findAll(planId);

        DayNameDao d = new DayNameDao();
        List<DayName> dayNames = d.showAll();

        Set<String> dayNamesInCurrentList = new HashSet<>();
        for (MealInPlan mealInPlan : mealsInPlan) {
            dayNamesInCurrentList.add(mealInPlan.getDayName());
        }

        dayNames.removeIf(dayName -> !dayNamesInCurrentList.contains(dayName.getName()));


        session.setAttribute("plan", plan);
        session.setAttribute("dayNames", dayNames);
        session.setAttribute("mealsInPlan", mealsInPlan);

        getServletContext().getRequestDispatcher("/app/plan/planDetails.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
