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
        boolean isSuperAdmin = (boolean) session.getAttribute("superadmin");
        request.setAttribute("superAdmin", isSuperAdmin);
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
        String verification = adminDao.verification(email,activePass);
        String firstName = (String) session.getAttribute("firstName");
        request.setAttribute("firstName",firstName);
        String newPassword = request.getParameter("newPass");
        String newPassword2 = request.getParameter("newPass2");
        if(newPassword2.equals(newPassword)){
            if(verification.equals("log")){
                adminDao.updatePassword(id, newPassword);
                getServletContext().getRequestDispatcher("/app/user/edited.jsp").forward(request, response);
            }else {
                getServletContext().getRequestDispatcher("/app/user/wrongPass.jsp").forward(request, response);
            }
        }else {
            getServletContext().getRequestDispatcher("/app/user/editPassNotSame.jsp").forward(request, response);
        }

    }
}
