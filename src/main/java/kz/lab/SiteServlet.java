package kz.lab;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = "/site",
        initParams = @WebInitParam(name = "siteName", value = "Servlet Lab"))
public class SiteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext ctx = getServletContext();
        int visits;
        synchronized (ctx) {
            Integer v = (Integer) ctx.getAttribute("visits");
            visits = (v == null) ? 1 : v + 1;
            ctx.setAttribute("visits", visits);
        }
        // Ағымдағы сессияның кіру саны
        HttpSession session = req.getSession();
        Integer sv = (Integer) session.getAttribute("sessionVisits");
        int sessionVisits = (sv == null) ? 1 : sv + 1;
        session.setAttribute("sessionVisits", sessionVisits);

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h2>" + getInitParameter("siteName") + "</h2>"
                + "<p>Жалпы кіру саны: " + visits + "</p>"
                + "<p>Сіздің сессияңыздағы кіру саны: " + sessionVisits + "</p>");
    }
}
