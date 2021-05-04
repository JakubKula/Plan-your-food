package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipieDao;
import pl.coderslab.model.PlanDetails;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Desktop", value = "/desktop")
public class Desktop extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("firstName");
        request.setAttribute("firstName", name);
        int adminId = (int) session.getAttribute("id");
        if((boolean) session.getAttribute("superadmin")){
            boolean isSuperAdmin = (boolean) session.getAttribute("superadmin");
            request.setAttribute("superAdmin", isSuperAdmin);
        }
        RecipieDao recipieDao = new RecipieDao();
        int recipieNr = recipieDao.numberOfRecipie(adminId);
        request.setAttribute("recipieNr", recipieNr);
        PlanDao planDao = new PlanDao();
        int plansNr = planDao.plansNumber(adminId);
        request.setAttribute("plansNr", plansNr);
        List<PlanDetails> planDetails = planDao.recentPlan(adminId);
        List<String> daysOfWeek = new ArrayList<>();
        for (PlanDetails plandetails: planDetails) {
            String dayOfweek = plandetails.getDayName();
            daysOfWeek.add(dayOfweek);
        }
        for (int i = 0; i < daysOfWeek.size()-1; i++) {
            if(daysOfWeek.get(i).equals(daysOfWeek.get(i+1))){
                daysOfWeek.remove(i+1);
            }
        }
        request.setAttribute("daysOfWeek", daysOfWeek);
        request.setAttribute("list", planDetails);
        String recentPlanName = planDao.recentPlanName(adminId);
        request.setAttribute("planName", recentPlanName);
        response.getWriter().println(planDetails);
        getServletContext().getRequestDispatcher("/app/desktop/desktop.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
