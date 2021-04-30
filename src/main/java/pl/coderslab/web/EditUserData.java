package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditUserData", value = "/edit/user/data")
public class EditUserData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");
        String firstName = (String) session.getAttribute("firstName");
        String lastName = (String) session.getAttribute("lastName");
        String email = (String) session.getAttribute("email");
        boolean login = (boolean) session.getAttribute("login");
        boolean isSuperAdmin = (boolean) session.getAttribute("superadmin");
        request.setAttribute("superAdmin", isSuperAdmin);
        request.setAttribute("firstName",firstName);
        request.setAttribute("lastName",lastName);
        request.setAttribute("email",email);
        getServletContext().getRequestDispatcher("/app/user/editData.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        Admin admin = new Admin(id, firstName, lastName, email);
        AdminDao adminDao = new AdminDao();
        adminDao.update(admin);
        session.setAttribute("id",admin.getId());
        session.setAttribute("firstName",admin.getFirstName());
        session.setAttribute("lastName",admin.getLastName());
        session.setAttribute("email",admin.getEmail());
        getServletContext().getRequestDispatcher("/app/user/edited.jsp").forward(request, response);
    }
}
