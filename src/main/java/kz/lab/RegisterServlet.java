package kz.lab;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8"); // параметрді оқудан БҰРЫН
        String[][] fields = {
                {"Аты", "name"}, {"Email", "email"}, {"Қала", "city"},
                {"Телефон", "phone"}, {"Туған жылы", "birthYear"}, {"Топ", "group"}
        };
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Сәтті тіркелдіңіз!</h2>");
        out.println("<table border='1' cellpadding='6'>");
        for (String[] f : fields) {
            out.println("<tr><td>" + f[0] + "</td><td>"
                    + Util.orDefault(req.getParameter(f[1]), "көрсетілмеген") + "</td></tr>");
        }
        out.println("</table>");
    }
}
