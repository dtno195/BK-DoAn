/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.filter;

import com.ptk.elearning.bo.TblNews;
import com.ptk.elearning.dto.HomeWrapper;
import com.ptk.elearning.dto.TblDivisionDTO;
import com.ptk.elearning.dto.TblNewsDTO;
import com.ptk.elearning.dto.UserSession;
import java.io.IOException;
import java.util.List;
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

public class AuthFilter implements Filter {

    private FilterConfig filterConfig;
    private static final Logger logger = Logger.getLogger(AuthFilter.class);
    private static final String[] permitterUrls = {
        "/authenticate.html",
        "/Elearning/register.html",
        "/signup.html"};
    private static final String[] outsiteUrl = {
        "/Elearning/dashboard.html",
        "/TblExam/examList.html",
        "/TblNews/news.html"
        //Khai báo hết các đường dẫn bên ngoài ra.
    };

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
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
        UserSession userSession = (UserSession) session.getAttribute("CURRENT_USER");
        String requestURI = req.getRequestURI();
        String path = req.getRequestURI().substring(req.getContextPath().length() + 1);
        if (requestURI.endsWith("/Login.html")) {
            if (userSession == null) {
                userSession = new UserSession();
                session.setAttribute("userSession", userSession);
            }
        } else if (!isPermittedUrl(requestURI.toLowerCase())) {
            if (userSession == null || userSession.getUserId() == null) {
                if (isAjaxRequest(req)) {
                    session.setAttribute("error", "Phiên làm việc đã hết hạn");
                    res.sendRedirect("/Elearning/Login.html");
                } else {
                    if (req.getRequestedSessionId() != null
                            && !req.isRequestedSessionIdValid()) {
                        session.setAttribute("error", "Phiên làm việc đã hết hạn");
                    }
                    res.sendRedirect("/Elearning/Login.html");
                }
                return;
            }
            if (!userSession.getIsLock()) {
                session.setAttribute("error", "Tài khoản đang bị khóa");
                res.sendRedirect("/Elearning/Login.html");
                return;
            }
        }
        if ((session.getAttribute("leftSide") == null || session.getAttribute("rightSide") == null )&& isOutsiteUrl(path.toLowerCase())) {
            res.sendRedirect("/Elearning/SetLeftSide.html?page=" + requestURI);
        } else if (session.getAttribute("leftSide") != null) {
            List<HomeWrapper> divisionDTOs = (List<HomeWrapper>) session.getAttribute("leftSide");
            request.setAttribute("leftSide", divisionDTOs); 
            List<TblNewsDTO> lstNewsDTOs = (List<TblNewsDTO>) session.getAttribute("rightSide");
            request.setAttribute("rightSide", lstNewsDTOs);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private boolean isPermittedUrl(String url) {
        if (url == null || "".equals(url)) {
            return false;
        }

        for (int i = 0; i < permitterUrls.length; i++) {
            if (url.endsWith(permitterUrls[i].toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    private boolean isOutsiteUrl(String url) {
        if (url == null || "".equals(url)) {
            return false;
        }

        for (int i = 0; i < outsiteUrl.length; i++) {
            if(outsiteUrl[i].toLowerCase().contains(url)){
                return true;
            }
        }

        return false;
    }

    private String getRemoteIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        return isAjax;
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
}
