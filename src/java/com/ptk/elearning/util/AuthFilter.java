package com.ptk.elearning.util;

import com.ptk.elearning.dto.UserSession;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class AuthFilter
        implements Filter {

    private FilterConfig filterConfig;
    private static final Logger logger = Logger.getLogger(AuthFilter.class);
    private static final String[] permitterUrls = {
        "/auth.html"
    };

    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req;
        HttpServletResponse res;
        if ((request instanceof HttpServletRequest)) {
            req = (HttpServletRequest) request;
        } else {
            chain.doFilter(request, response);
            return;
        }

        if ((response instanceof HttpServletResponse)) {
            res = (HttpServletResponse) response;
        } else {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);

        if (session == null) {
            session = req.getSession(true);
        }

        UserSession userSession = (UserSession) session.getAttribute("userSession");
        String requestURI = req.getRequestURI();

        if (requestURI.endsWith("/login.html")) {
            if (userSession == null) {
                userSession = new UserSession();
                session.setAttribute("userSession", userSession);
            }
        } else if (!isPermittedUrl(requestURI)) {

            if (userSession == null || userSession.getUserId() == null) {
                if (isAjaxRequest(req)) {
                    session.setAttribute("error", "message.sessionExpired");
                    res.sendRedirect("/sessionTimeout.html");
                } else {
                    if (req.getRequestedSessionId() != null
                            && !req.isRequestedSessionIdValid()) {
                        session.setAttribute("error", "message.sessionExpired");
                    }
                    res.sendRedirect("login.html");
                }
                return;
            }
        } else if (!requestURI.endsWith("/sessionTimeout.html") && userSession == null) {
            session.setAttribute("error", "message.sessionExpired");
            res.sendRedirect("login.html");
            return;
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

    public FilterConfig getFilterConfig() {
        return this.filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    private boolean isPermittedUrl(String url) {
        if (url == null || "".equals(url)) {
            return false;
        }

        for (int i = 0; i < permitterUrls.length; i++) {
            if (url.endsWith(permitterUrls[i])) {
                return true;
            }
        }

        return false;
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        return isAjax;
    }
}
