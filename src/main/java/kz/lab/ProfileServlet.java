package kz.lab;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Қазақ әріптері GET-те дұрыс оқылуы үшін (Tomcat 9: URIEncoding әдепкіде UTF-8)
        req.setCharacterEncoding("UTF-8");
        String name = Util.orDefault(req.getParameter("name"), "аты көрсетілмеген");
        String city = Util.orDefault(req.getParameter("city"), "көрсетілмеген");
        String ageStr = req.getParameter("age");
        String ageText = "белгісіз";
        String category = "";
        if (ageStr != null && !ageStr.isBlank()) {
            try {
                int age = Integer.parseInt(ageStr.trim());
                ageText = String.valueOf(age);
                category = age < 18 ? "Кәмелетке толмаған" : "Ересек";
            } catch (NumberFormatException e) {
                ageText = "қате мән";
            }
        }
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Профиль</h2>");
        out.println("<p>Аты: " + name + "</p>");
        out.println("<p>Жасы: " + ageText + "</p>");
        out.println("<p>Қала: " + city + "</p>");
        if (!category.isEmpty()) out.println("<p>Санат: " + category + "</p>");
    }
}
