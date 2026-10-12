package kz.lab;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/status")
public class StatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter("code");
        if ("404".equals(code)) { resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Бет табылмады"); return; }
        if ("500".equals(code)) { resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Сервер қатесі"); return; }
        if ("403".equals(code)) { resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Рұқсат жоқ"); return; }
        if ("400".equals(code)) { resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Қате сұраныс"); return; }
        String type = req.getParameter("type");
        if ("json".equals(type)) {
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().println("{\"status\":\"ok\",\"message\":\"Сәлем\"}");
        } else if ("html".equals(type)) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<h2>HTML жауап</h2><p>Бұл <b>text/html</b> түрі.</p>");
        } else {
            resp.setContentType("text/plain;charset=UTF-8");
            resp.getWriter().println("Қарапайым мәтін");
        }
    }
}
