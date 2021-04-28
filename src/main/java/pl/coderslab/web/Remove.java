package pl.coderslab.web;

import pl.coderslab.dao.RecipieDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Remove", value = "/app/recipe/remove")
public class Remove extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String IdString = request.getParameter("id");
       int id = Integer.parseInt(IdString);

        RecipieDao recipieDao = new RecipieDao();
        recipieDao.delete(id);

        getServletContext().getRequestDispatcher("/app/recipe/list").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
