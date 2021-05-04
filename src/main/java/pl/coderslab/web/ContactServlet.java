package pl.coderslab.web;

import pl.coderslab.dao.ContactDao;
import pl.coderslab.model.Contact;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "Contact", value = "/contact")
public class ContactServlet extends HttpServlet {
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
        ContactDao contactDao = new ContactDao();
        Contact contact = new Contact();
        contact = contactDao.read(1);
        String topic1 = contact.getTopic1();
        String topic2 = contact.getTopic2();
        String topic3 = contact.getTopic3();
        String description1 = contact.getDescription1();
        String description2list = contact.getDescription2list();
        List<String> list = new ArrayList<>();
        list = Arrays.asList(description2list.split(","));
        request.setAttribute("topic1",topic1);
        request.setAttribute("topic2",topic2);
        request.setAttribute("topic3",topic3);
        request.setAttribute("description1",description1);
        request.setAttribute("list",list);
        getServletContext().getRequestDispatcher("/app/main/contact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
