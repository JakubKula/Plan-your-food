package pl.coderslab.web;

import pl.coderslab.dao.AboutDao;
import pl.coderslab.model.About;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "About", value = "/about")
public class AboutServlet extends HttpServlet {
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
        AboutDao aboutDao = new AboutDao();
        About about = new About();
        about = aboutDao.read(1);
        String topic = about.getTopic();
        String description = about.getDescription();
        request.setAttribute("topic",topic);
        request.setAttribute("description",description);
        getServletContext().getRequestDispatcher("/app/main/about.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
