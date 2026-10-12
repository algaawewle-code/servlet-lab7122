package kz.lab;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/info")
public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<p>Әдіс: " + req.getMethod() + "</p>");
        out.println("<p>URI: " + Util.esc(req.getRequestURI()) + "</p>");
        out.println("<p>IP: " + req.getRemoteAddr() + "</p>");
        out.println("<p>Тіл: " + detectLang(req.getHeader("Accept-Language")) + "</p>");
        out.println("<p>Браузер: " + detectBrowser(req.getHeader("User-Agent")) + "</p>");
        out.println("<table border='1' cellpadding='4'>");
        Enumeration<String> names = req.getHeaderNames();
        while (names.hasMoreElements()) {
            String n = names.nextElement();
            out.println("<tr><td>" + Util.esc(n) + "</td><td>" + Util.esc(req.getHeader(n)) + "</td></tr>");
        }
        out.println("</table>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }

    private String detectLang(String al) {
        if (al == null) return "белгісіз";
        String l = al.toLowerCase();
        if (l.startsWith("kk")) return "қазақша";
        if (l.startsWith("ru")) return "орысша";
        if (l.startsWith("en")) return "ағылшынша";
        return "басқа (" + Util.esc(al) + ")";
    }

    private String detectBrowser(String ua) {
        if (ua == null) return "белгісіз";
        if (ua.contains("Edg")) return "Edge";      // Edge құрамында "Chrome" да бар, сондықтан бірінші
        if (ua.contains("OPR") || ua.contains("Opera")) return "Opera";
        if (ua.contains("Firefox")) return "Firefox";
        if (ua.contains("Chrome")) return "Chrome";
        if (ua.contains("Safari")) return "Safari";
        return "басқа";
    }
}
