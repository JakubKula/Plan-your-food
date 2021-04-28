package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Do not change servlet address !!!
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/app/main/home.jsp").forward(request, response);
    }
}
