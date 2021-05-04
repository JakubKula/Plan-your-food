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
        getServletContext().getRequestDispatcher("/app/main/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AdminDao adminDao = new AdminDao();
        String logIN = adminDao.verification(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("login", "");
        if(logIN.equals("block")){
            getServletContext().getRequestDispatcher("/app/user/userblocked.jsp").forward(request, response);
        }
        if(logIN.equals("log")){
            Admin admin = adminDao.readByEmail(email);
            session.setAttribute("login", true);
            session.setAttribute("id",admin.getId());
            session.setAttribute("firstName",admin.getFirstName());
            session.setAttribute("lastName",admin.getLastName());
            session.setAttribute("email",admin.getEmail());
            boolean isSuperAdmin = adminDao.superAdmin(email);
            session.setAttribute("superadmin", "");
            if(isSuperAdmin){
                session.setAttribute("superadmin", true);
            }else {
                session.setAttribute("superadmin", false);
            }
            response.sendRedirect("/");
//            getServletContext().getRequestDispatcher("/").forward(request, response);//nie wiem na razie tak jest
        }
        if(logIN.equals("bad")) {
            PrintWriter out = response.getWriter();
//            out.println("<meta http-equiv='refresh' content='3;URL=login.jsp'>");//redirects after 3 seconds
//            out.println("<p style='color:red;'>User or password incorrect!</p>");

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Email or password incorrect, try again');");
            out.println("location='login';");
            out.println("</script>");
//            response.sendRedirect("/login.jsp");
        }
    }
}
