package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Logout", value = "/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("login").equals(true)){
            int id = (int) session.getAttribute("id");
            String name = (String) session.getAttribute("firstName");
            boolean login = (boolean) session.getAttribute("login");
            request.setAttribute("name",name);
        }
        getServletContext().getRequestDispatcher("/logout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("login", false);

        response.sendRedirect("/");

    }
}
