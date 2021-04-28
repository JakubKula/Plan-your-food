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
        if(session.getAttribute("login") != null){
            if(session.getAttribute("login").equals(true)){
                int id = (int) session.getAttribute("id");
                String firstName = (String) session.getAttribute("firstName");
                String lastName = (String) session.getAttribute("lastName");
                String email = (String) session.getAttribute("email");
                boolean login = (boolean) session.getAttribute("login");
                request.setAttribute("firstName",firstName);
                request.setAttribute("lastName",lastName);
                request.setAttribute("email",email);
            }
        }
        getServletContext().getRequestDispatcher("/app/user/editData.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
