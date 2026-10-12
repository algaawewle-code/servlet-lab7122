package kz.lab;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

/** Сессиясыз /cabinet-ке кіргізбейді, логинге бағыттайды. */
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) req;
        HttpServletResponse p = (HttpServletResponse) resp;
        HttpSession s = r.getSession(false);
        if (s == null || s.getAttribute("user") == null) {
            p.sendRedirect(r.getContextPath() + "/login");
            return;
        }
        chain.doFilter(req, resp);
    }
}
