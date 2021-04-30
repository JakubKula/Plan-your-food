package pl.coderslab.web;

import pl.coderslab.dao.RecipieDao;
import pl.coderslab.model.Recipie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "EditRecipe", value = "/app/recipe/edit")
public class EditRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);

        RecipieDao recipieDao = new RecipieDao();
        Recipie editDetal = recipieDao.read(id);

        response.getWriter().println(recipieDao.read(id));
        HttpSession session = request.getSession();
        session.setAttribute("editDetali", editDetal);

        if((boolean) session.getAttribute("superadmin")){
            boolean isSuperAdmin = (boolean) session.getAttribute("superadmin");
            request.setAttribute("superAdmin", isSuperAdmin);
        }

        getServletContext().getRequestDispatcher("/app/recipe/EditRecipe.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String currentDate = (formatter.format(date));

        String name = request.getParameter("nameEdit");
        String description = request.getParameter("disc");
        String prepTime = request.getParameter("prepTime");
        int prepTimeInt = Integer.parseInt(prepTime);
        String preparation = request.getParameter("prep");
        String igredients = request.getParameter("igred");
        String editID = request.getParameter("idofEdit");
        int editIdInt = Integer.parseInt(editID);

        HttpSession session2 = request.getSession();

        Object adminInt = session2.getAttribute("id");
        String adminId =   adminInt.toString();
        int admin = Integer.parseInt(adminId);

        Recipie recipie = new Recipie(name,igredients,description, currentDate, currentDate,prepTimeInt,preparation, admin);

        RecipieDao recipieDao = new RecipieDao();
        recipieDao.update(recipie, editIdInt);

        response.sendRedirect("/app/recipe/list");
    }
}
