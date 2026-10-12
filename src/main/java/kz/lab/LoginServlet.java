package kz.lab;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    // тек оқу мақсатында. Нақты жобада құпиясөз хэштеліп, дерекқорда сақталады
    static final Map<String, String> USERS = new HashMap<>();
    static {
        USERS.put("admin", "1234");
        USERS.put("student", "abcd");
        USERS.put("aigerim", "qwerty");
    }

    private void form(HttpServletResponse resp, String error) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        if (error != null) resp.getWriter().println("<p style='color:red'>" + error + "</p>");
        resp.getWriter().println("<form method='post' action='login'>"
                + "Логин: <input name='user'> "
                + "Құпиясөз: <input type='password' name='pass'> <button>Кіру</button></form>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        form(resp, null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String u = req.getParameter("user"), p = req.getParameter("pass");
        if (u != null && p != null && p.equals(USERS.get(u))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", u);
            session.setMaxInactiveInterval(60); // 1 минут (тексеру үшін). Кейін 30*60 етіңіз
            resp.sendRedirect(req.getContextPath() + "/cabinet");
        } else {
            form(resp, "Логин немесе құпиясөз қате");
        }
    }
}
