package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SuperAdmin", value = "/super/admin")
public class SuperAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDao adminDao = new AdminDao();
        List<Admin> adminList = adminDao.findAll();
        HttpSession session = request.getSession();
        String firstName = (String) session.getAttribute("firstName");
        if((boolean) session.getAttribute("superadmin")){
            boolean isSuperAdmin = (boolean) session.getAttribute("superadmin");
            request.setAttribute("superAdmin", isSuperAdmin);
        }
        int enable = 1;
        request.setAttribute("firstName",firstName);
        request.setAttribute("list", adminList);
        request.setAttribute("enable", enable);
        getServletContext().getRequestDispatcher("/app/user/superadmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
