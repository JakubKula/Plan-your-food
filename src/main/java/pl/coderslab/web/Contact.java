package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Contact", value = "/contact")
public class Contact extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("login") != null){
            if(session.getAttribute("login").equals(true)){
                int id = (int) session.getAttribute("id");
                String name = (String) session.getAttribute("firstName");
                boolean login = (boolean) session.getAttribute("login");
                request.setAttribute("name",name);
            }
        }
        getServletContext().getRequestDispatcher("/contact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
