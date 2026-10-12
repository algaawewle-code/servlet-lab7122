package kz.lab;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/life")
public class LifeServlet extends HttpServlet {
    private int count = 0;
    private LocalDateTime startTime;

    @Override
    public void init() {
        startTime = LocalDateTime.now();
        System.out.println("init() шақырылды: " + startTime);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        count++;
        System.out.println("service() №" + count);
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h2>Бет " + count + " рет ашылды</h2>"
                + "<p>Servlet іске қосылған уақыт: " + startTime + "</p>");
    }

    @Override
    public void destroy() {
        System.out.println("destroy() шақырылды");
    }
}
