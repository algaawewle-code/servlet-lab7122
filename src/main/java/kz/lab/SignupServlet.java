package kz.lab;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        showForm(resp, "", "", "", new ArrayList<>());
    }

    private String val(HttpServletRequest req, String key) {
        String v = req.getParameter(key);
        return v == null ? "" : v.trim();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String name = val(req, "name");
        String email = val(req, "email");
        String phone = val(req, "phone");
        String pass = req.getParameter("pass") == null ? "" : req.getParameter("pass");
        String pass2 = req.getParameter("pass2") == null ? "" : req.getParameter("pass2");

        List<String> errors = new ArrayList<>();
        if (name.isEmpty()) errors.add("Аты бос болмауы керек");
        if (!email.matches("[^@\\s]+@[^@\\s]+\\.[a-z]{2,}")) errors.add("Email пішімі қате");
        if (!phone.matches("\\+7 7\\d{2} \\d{3} \\d{2} \\d{2}")) errors.add("Телефон пішімі қате (+7 7XX XXX XX XX)");
        if (pass.length() < 6) errors.add("Құпиясөз кемінде 6 таңба болуы керек");
        if (!pass.equals(pass2)) errors.add("Құпиясөздер сәйкес емес");

        if (errors.isEmpty()) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<h2>Тіркеу сәтті аяқталды</h2><p>Қош келдіңіз, " + Util.esc(name) + "!</p>");
        } else {
            showForm(resp, name, email, phone, errors);
        }
    }

    private void showForm(HttpServletResponse resp, String name, String email, String phone,
                          List<String> errors) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        for (String e : errors) out.println("<p style='color:red'>" + e + "</p>");
        out.println("<form method='post' action='signup'>");
        out.println("Аты: <input name='name' value='" + Util.esc(name) + "'><br>");
        out.println("Email: <input name='email' value='" + Util.esc(email) + "'><br>");
        out.println("Телефон: <input name='phone' placeholder='+7 7XX XXX XX XX' value='" + Util.esc(phone) + "'><br>");
        out.println("Құпиясөз: <input type='password' name='pass'><br>");
        out.println("Қайталау: <input type='password' name='pass2'><br>");
        out.println("<button>Тіркелу</button></form>");
    }
}
