package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AdminDao adminDao = new AdminDao();
        boolean logIN = adminDao.verification(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("login", "");
        if(logIN){
            Admin admin = adminDao.readByEmail(email);
            session.setAttribute("login", true);
            session.setAttribute("id",admin.getId());
            session.setAttribute("firstName",admin.getFirstName());
            session.setAttribute("lastName",admin.getLastName());
            session.setAttribute("email",admin.getEmail());
            response.sendRedirect("/");
//            getServletContext().getRequestDispatcher("/").forward(request, response);//nie wiem na razie tak jest
        }
        if(!logIN) {
            PrintWriter out = response.getWriter();
//            out.println("<meta http-equiv='refresh' content='3;URL=login.jsp'>");//redirects after 3 seconds
//            out.println("<p style='color:red;'>User or password incorrect!</p>");

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Email or password incorrect, try again');");
            out.println("location='login.jsp';");
            out.println("</script>");
//            response.sendRedirect("/login.jsp");
        }
    }
}
