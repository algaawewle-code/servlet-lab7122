package kz.lab;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/survey")
public class SurveyServlet extends HttpServlet {
    private void list(PrintWriter out, String title, String[] items) {
        out.println("<p>" + title + ":</p>");
        if (items == null || items.length == 0) {
            out.println("<p><i>таңдау жоқ</i></p>");
            return;
        }
        out.println("<ol>");
        for (String s : items) out.println("<li>" + Util.esc(s) + "</li>");
        out.println("</ol>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Сауалнама нәтижесі</h2>");
        list(out, "Пәндер", req.getParameterValues("subjects"));
        out.println("<p>Курс: " + Util.orDefault(req.getParameter("course"), "көрсетілмеген") + "</p>");
        out.println("<p>Қала: " + Util.orDefault(req.getParameter("city"), "көрсетілмеген") + "</p>");
        list(out, "Бағдарламалау тілдері", req.getParameterValues("langs"));
    }
}
