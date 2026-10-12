package kz.lab;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
    private static final String[] OPS = {"+", "-", "*", "/", "^", "%"};

    private void form(PrintWriter out, String a, String op, String b) {
        out.println("<form method='post' action='calc'>");
        out.println("<input name='a' size='5' value='" + Util.esc(a) + "'> ");
        out.println("<select name='op'>");
        for (String o : OPS) {
            out.println("<option" + (o.equals(op) ? " selected" : "") + ">" + o + "</option>");
        }
        out.println("</select> ");
        out.println("<input name='b' size='5' value='" + Util.esc(b) + "'> <button>=</button></form>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        form(resp.getWriter(), "", "+", "");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String as = req.getParameter("a"), bs = req.getParameter("b"), op = req.getParameter("op");
        form(out, as, op, bs); // форма жоғалмайды
        try {
            double a = Double.parseDouble(as.trim());
            double b = Double.parseDouble(bs.trim());
            double r;
            switch (op) {
                case "+": r = a + b; break;
                case "-": r = a - b; break;
                case "*": r = a * b; break;
                case "/":
                    if (b == 0) { out.println("<p>Қате: нөлге бөлуге болмайды</p>"); return; }
                    r = a / b; break;
                case "^": r = Math.pow(a, b); break;
                case "%":
                    if (b == 0) { out.println("<p>Қате: нөлге бөлуге болмайды</p>"); return; }
                    r = a % b; break;
                default: out.println("<p>Белгісіз амал</p>"); return;
            }
            out.println("<h2>Нәтиже: " + r + "</h2>");
        } catch (NumberFormatException | NullPointerException e) {
            out.println("<p>Қате: сан енгізіңіз</p>");
        }
    }
}
