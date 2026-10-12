package kz.lab;

import java.io.IOException;
import javax.servlet.http.*;

/** @WebServlet жоқ: web.xml арқылы /about мекенжайына тіркелген. */
public class AboutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h2>About</h2><p>Бұл Servlet web.xml арқылы тіркелген.</p>"
                + "<a href='hello'>Hello бетіне қайту</a>");
    }
}
