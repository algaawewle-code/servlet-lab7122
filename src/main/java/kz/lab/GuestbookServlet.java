package kz.lab;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {
    /** true: тек логиннен кірген пайдаланушы жазба қалдыра алады (12-жұмыс). false: бәрі жаза алады. */
    private static final boolean REQUIRE_LOGIN = true;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private final List<String> messages = Collections.synchronizedList(new ArrayList<>());

    private boolean loggedIn(HttpServletRequest req) {
        HttpSession s = req.getSession(false);
        return s != null && s.getAttribute("user") != null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Пікір кітабы</h2>");
        if (REQUIRE_LOGIN && !loggedIn(req)) {
            out.println("<p>Жазба қалдыру үшін <a href='login'>кіріңіз</a>.</p>");
        } else {
            out.println("<form method='post' action='guestbook'>");
            out.println("Аты: <input name='author'><br>");
            out.println("<textarea name='text' rows='3' cols='30'></textarea><br>");
            out.println("<button>Жіберу</button></form>");
        }
        out.println("<hr>");
        synchronized (messages) {
            // ең жаңасы жоғарыда
            for (int i = messages.size() - 1; i >= 0; i--) out.println("<p>" + messages.get(i) + "</p>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        if (REQUIRE_LOGIN && !loggedIn(req)) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        String author = Util.esc(req.getParameter("author"));
        String text = Util.esc(req.getParameter("text"));
        if (!author.isBlank() && !text.isBlank()) {
            String time = LocalDateTime.now().format(FMT);
            messages.add("<small>" + time + "</small> <b>" + author + "</b>: " + text);
        }
        resp.sendRedirect("guestbook"); // Post/Redirect/Get
    }
}
