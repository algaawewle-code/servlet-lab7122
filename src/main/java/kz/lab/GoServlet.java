package kz.lab;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/go")
public class GoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if ("redirect".equals(req.getParameter("type"))) {
            resp.sendRedirect(req.getContextPath() + "/target");
        } else {
            req.setAttribute("msg", "Forward арқылы келдіңіз");
            req.setAttribute("student", "Аты-жөні (ӨЗ АТЫҢЫЗДЫ ЖАЗЫҢЫЗ)");
            req.getRequestDispatcher("/target").forward(req, resp);
        }
    }
}
