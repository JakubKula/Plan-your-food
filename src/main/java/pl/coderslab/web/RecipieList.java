package pl.coderslab.web;

import pl.coderslab.dao.RecipieDao;
import pl.coderslab.model.Recipie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RecipieList", value = "/app/recipe/list")
public class RecipieList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RecipieDao recipieDao = new RecipieDao();

        List<Recipie> list = recipieDao.showAll();
        response.getWriter().println(list.get(1).getName());
        session.setAttribute("list", list);

        getServletContext().getRequestDispatcher("/app/recipe/RecipieList.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
