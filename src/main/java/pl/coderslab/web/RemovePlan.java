package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipieDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemovePlan", value = "/app/plan/remove")
public class RemovePlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String IdString = request.getParameter("PlanIdentity");
        int identity = Integer.parseInt(IdString);

        PlanDao planDao = new PlanDao();
        System.out.println(identity);
        planDao.delete(identity);

        response.sendRedirect("/app/plan/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
