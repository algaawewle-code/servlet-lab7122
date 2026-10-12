package kz.lab;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/target")
public class TargetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Object msg = req.getAttribute("msg");
        Object student = req.getAttribute("student");
        String html = "<h2>Мақсатты бет</h2><p>" + (msg != null ? msg : "Redirect арқылы келдіңіз") + "</p>";
        if (student != null) html += "<p>Студент: " + student + "</p>";
        resp.getWriter().println(html);
    }
}
