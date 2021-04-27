package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AdminDao adminDao = new AdminDao();
        boolean logIN = adminDao.verification(email, password);
        System.out.println(logIN);
        if(logIN){
            response.sendRedirect("/home.jsp");
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
