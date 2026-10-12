package kz.lab;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) req;
        long start = System.currentTimeMillis();
        chain.doFilter(req, resp);
        long ms = System.currentTimeMillis() - start;
        System.out.println(r.getMethod() + " " + r.getRequestURI() + " - " + ms + " ms");
    }
}
