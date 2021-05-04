package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/list")
public class PlanList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if((boolean) session.getAttribute("superadmin")){
            boolean isSuperAdmin = (boolean) session.getAttribute("superadmin");
            request.setAttribute("superAdmin", isSuperAdmin);
        }

        int adminId = Integer.parseInt(session.getAttribute("id").toString());
        session.setAttribute("firstName" , (String)session.getAttribute("firstName"));

        PlanDao p = new PlanDao();
        List<Plan> plans = p.findAll(adminId);
        session.setAttribute("plans", plans);

        getServletContext().getRequestDispatcher("/app/plan/planList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
