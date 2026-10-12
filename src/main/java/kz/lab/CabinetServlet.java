package kz.lab;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/cabinet")
public class CabinetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        if ("logout".equals(req.getParameter("action"))) {
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h2>Жеке кабинет</h2><p>Қош келдіңіз, "
                + Util.esc(String.valueOf(session.getAttribute("user")))
                + "!</p><a href='cabinet?action=logout'>Шығу</a>");
    }
}
