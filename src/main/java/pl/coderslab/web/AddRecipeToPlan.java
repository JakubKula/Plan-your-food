package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.dao.RecipieDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.model.Recipie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddRecipeToPlan", value = "/app/recipe/plan/add")
public class AddRecipeToPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session3 = request.getSession();
        String name = (String) session3.getAttribute("firstName");
        session3.setAttribute("firstName", name);

        if((boolean) session3.getAttribute("superadmin")){
            boolean isSuperAdmin = (boolean) session3.getAttribute("superadmin");
            request.setAttribute("superAdmin", isSuperAdmin);
        }

        Object adminInt = session3.getAttribute("id");
        String adminId = adminInt.toString();
        int admin = Integer.parseInt(adminId);

        PlanDao planDao = new PlanDao();
        List<Plan> listOfPlans = planDao.findAll(admin);
        session3.setAttribute("listOfPlans", listOfPlans);

        RecipieDao recipieDao = new RecipieDao();
        recipieDao.showAllForAdmin(admin);
        List<Recipie> RecipeForAdminList = recipieDao.showAllForAdmin(admin);
        session3.setAttribute("RecipeForAdminList", RecipeForAdminList);

        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> DayNamesList = dayNameDao.showAll();
        session3.setAttribute("DayNamesList", DayNamesList);

        getServletContext().getRequestDispatcher("/app/recipe/AddRecipeToPlan.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String choosePlan = request.getParameter("choosePlan");
        int planId = Integer.parseInt(choosePlan);
        String mealName = request.getParameter("mealName");
        String mealNumber = request.getParameter("mealNumber");
        int displayOrder = Integer.parseInt(mealNumber);
        String recipeId = request.getParameter("recipeId");
        int recipeIdInt = Integer.parseInt(recipeId);
        String dayNameId = request.getParameter("dayNameId");
        int dayNameIdInt = Integer.parseInt(dayNameId);

        RecipePlan recipePlan = new RecipePlan(recipeIdInt,mealName,displayOrder,dayNameIdInt,planId);

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.create(recipePlan);


        response.sendRedirect("/app/recipe/plan/add");


    }
}
