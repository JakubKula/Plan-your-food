package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditUserPassword", value = "/edit/user/password")
public class EditUserPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");
        String firstName = (String) session.getAttribute("firstName");
        request.setAttribute("firstName",firstName);
        getServletContext().getRequestDispatcher("/app/user/editPass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");
        String email = (String) session.getAttribute("email");
        String activePass = request.getParameter("activePass");
        AdminDao adminDao = new AdminDao();
        boolean verification = adminDao.verification(email,activePass);
        String firstName = (String) session.getAttribute("firstName");
        request.setAttribute("firstName",firstName);
        if(verification){
            String newPassword = request.getParameter("newPass");
            adminDao.updatePassword(id, newPassword);
            getServletContext().getRequestDispatcher("/app/user/edited.jsp").forward(request, response);
        }else {
            getServletContext().getRequestDispatcher("/app/user/wrongPass.jsp").forward(request, response);
        }

    }
}
