package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDetails;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/app/plan/add")
public class AddPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/app/plan/addPlan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Date created = new Date(System.currentTimeMillis());
        int adminId = Integer.parseInt(request.getSession().getAttribute("id").toString());

        PlanDao p = new PlanDao();
        p.create(new Plan(name, description, created, adminId));
        getServletContext().getRequestDispatcher("/app/plan/list").forward(request, response);
    }
}
