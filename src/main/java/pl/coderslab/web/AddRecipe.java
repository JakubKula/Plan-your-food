package pl.coderslab.web;

import pl.coderslab.dao.RecipieDao;
import pl.coderslab.model.Recipie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AddRecipe", value = "/app/recipe/add")
public class AddRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/app/recipe/AddRecipe.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String currentDate = (formatter.format(date));

        String name = request.getParameter("name");
        String description = request.getParameter("disc");
        String prepTime = request.getParameter("prepTime");
        int prepTimeInt = Integer.parseInt(prepTime);
        String preparation = request.getParameter("prep");
        String igredients = request.getParameter("igred");

        HttpSession session2 = request.getSession();

        Object adminInt = session2.getAttribute("id");
        String adminId =   adminInt.toString();
        int admin = Integer.parseInt(adminId);

        Recipie recipie = new Recipie(name,igredients,description, currentDate, currentDate,prepTimeInt,preparation, admin);

        RecipieDao recipieDao = new RecipieDao();
        recipieDao.create(recipie);

        response.sendRedirect("/app/recipe/list");

    }
}
