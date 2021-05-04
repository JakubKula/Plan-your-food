package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditPlan", value = "/app/plan/edit")
public class EditPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if((boolean) session.getAttribute("superadmin")){
            boolean isSuperAdmin = (boolean) session.getAttribute("superadmin");
            request.setAttribute("superAdmin", isSuperAdmin);
        }
        int planId = Integer.parseInt(request.getParameter("planId"));
        session.setAttribute("firstName" , (String)session.getAttribute("firstName"));

        PlanDao p = new PlanDao();
        Plan plan = p.read(planId);

        request.setAttribute("plan", plan);
        getServletContext().getRequestDispatcher("/app/plan/editPlan.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        PlanDao p = new PlanDao();
        int planId = Integer.parseInt(request.getParameter("planId"));
        Plan plan = p.read(planId);
        plan.setName(request.getParameter("planName"));
        plan.setDescription(request.getParameter("planDescription"));
        p.update(plan);

        response.sendRedirect("/app/plan/list");

    }
}
