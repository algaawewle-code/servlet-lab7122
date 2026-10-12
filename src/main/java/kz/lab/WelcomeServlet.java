package kz.lab;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    private String cookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) if (c.getName().equals(name)) return c.getValue();
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Cookie өшіру
        if ("clear".equals(req.getParameter("action"))) {
            Cookie c = new Cookie("user", "");
            c.setMaxAge(0);
            resp.addCookie(c);
            out.println("<p>Cookie өшірілді.</p><a href='welcome'>Қайту</a>");
            return;
        }

        // Тілді cookie-де сақтау
        String langParam = req.getParameter("lang");
        if (langParam != null && langParam.matches("kk|ru|en")) {
            Cookie lc = new Cookie("lang", langParam);
            lc.setMaxAge(60 * 60 * 24 * 30);
            resp.addCookie(lc);
        }
        String lang = langParam != null && langParam.matches("kk|ru|en") ? langParam : cookie(req, "lang");
        if (lang == null) lang = "kk";
        String hello, again, intro;
        switch (lang) {
            case "ru": hello = "Привет"; again = "С возвращением, "; intro = "Давайте познакомимся: /welcome?name=Имя"; break;
            case "en": hello = "Hello"; again = "Welcome back, "; intro = "Let's meet: /welcome?name=Name"; break;
            default:   hello = "Сәлем"; again = "Қайта келдіңіз, "; intro = "Танысайық: /welcome?name=Аты";
        }

        out.println("<p>" + hello + "! <a href='welcome?lang=kk'>kk</a> | <a href='welcome?lang=ru'>ru</a> | <a href='welcome?lang=en'>en</a></p>");

        String user = cookie(req, "user");
        String nameParam = req.getParameter("name");
        if (nameParam != null && !nameParam.isBlank()) {
            // кириллица/қазақ әріптері cookie-де кодталуы керек
            Cookie c = new Cookie("user", URLEncoder.encode(nameParam.trim(), "UTF-8"));
            c.setMaxAge(60 * 60 * 24); // 1 тәулік
            // HttpOnly: JavaScript (document.cookie) cookie-ді оқи алмайды, XSS-тен қорғайды
            c.setHttpOnly(true);
            resp.addCookie(c);
            out.println("<p>Cookie сақталды: " + Util.esc(nameParam) + "</p>");
        } else if (user != null && !user.isEmpty()) {
            out.println("<h2>" + again + Util.esc(URLDecoder.decode(user, "UTF-8")) + "!</h2>");
            out.println("<a href='welcome?action=clear'>Cookie өшіру</a>");
        } else {
            out.println("<p>" + intro + "</p>");
        }
    }
}
