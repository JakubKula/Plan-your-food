package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app/main/register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AdminDao adminDao =new AdminDao();
        Admin adminUnique = adminDao.readByEmail(email);
        if(adminUnique.getEmail() != null){
            getServletContext().getRequestDispatcher("/app/user/uniqueLogin.jsp").forward(request, response);
        }else{
            Admin admin = new Admin(name,surname,email,password);
            adminDao.create(admin);
            getServletContext().getRequestDispatcher("/login").forward(request, response);
        }

    }
}
