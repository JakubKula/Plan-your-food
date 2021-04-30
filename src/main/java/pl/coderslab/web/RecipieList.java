package pl.coderslab.web;

import pl.coderslab.dao.RecipieDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RecipieList", value = "/app/recipe/list")
public class RecipieList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        RecipieDao recipieDao = new RecipieDao();

        HttpSession session2 = request.getSession();

      Object adminInt = session2.getAttribute("id");
      String adminId =   adminInt.toString();
      int admin = Integer.parseInt(adminId);

        if((boolean) session.getAttribute("superadmin")){
            boolean isSuperAdmin = (boolean) session.getAttribute("superadmin");
            request.setAttribute("superAdmin", isSuperAdmin);
        }

        String name = (String)session.getAttribute("firstName");
        session.setAttribute("firstName" , name);

        List<Recipie> list = recipieDao.showAllForAdmin(admin);
        session.setAttribute("list", list);

        getServletContext().getRequestDispatcher("/app/recipe/RecipieList.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
