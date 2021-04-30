package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BlockUser", value = "/block/user")
public class BlockUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String block = request.getParameter("block");
        HttpSession session = request.getSession();
        session.setAttribute("firstNameToBlock", firstName);
        session.setAttribute("lastNameToBlock", lastName);
        session.setAttribute("idToBlock", id);
        if((boolean) session.getAttribute("superadmin")){
            boolean isSuperAdmin = (boolean) session.getAttribute("superadmin");
            request.setAttribute("superAdmin", isSuperAdmin);
        }
        if(block.equals("yes")){
            getServletContext().getRequestDispatcher("/app/user/blockuser.jsp").forward(request, response);
        }
        if(block.equals("no")){
            getServletContext().getRequestDispatcher("/app/user/unblockuser.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("idToBlock");
        String block = request.getParameter("block");
        AdminDao adminDao = new AdminDao();
        if(block.equals("yes")){
            adminDao.block(id);
            response.sendRedirect("/super/admin");
        }
        if(block.equals("no")){
            adminDao.unblock(id);
            response.sendRedirect("/super/admin");
        }

//        getServletContext().getRequestDispatcher("/super/admin").forward(request, response);
    }
}
