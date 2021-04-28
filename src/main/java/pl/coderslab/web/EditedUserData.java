package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditedUserData", value = "/edited/user/data")
public class EditedUserData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
