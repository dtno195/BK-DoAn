/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.controller;

import com.ptk.elearning.bo.EventLogs;
import com.ptk.elearning.business.EventLogsBusinessImpl;
import com.ptk.elearning.dto.UserSession;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;


public class BaseController {

    @Autowired
    private EventLogsBusinessImpl eventLogsBusiness;

    protected final Logger logger = Logger.getLogger(BaseController.class);
    //Khai bao cac constant
    //so thu tu trang hien tai
    public static final String PG_CURPAGE = "pagenum";
    //Tong so ban ghi tren mot trang
    public static final String PG_TOTAL_RECORD_PER_PAGE = "pagesize";
    //so thu tu ban ghi cuoi trong trang
    public static final String RECORD_END_INDEX = "recordendindex";
    //index ban ghi dau tien trong trang
    public static final String RECORD_START_INDEX = "recordstartindex";
    public static final String AUTO_COMPLETE_PRNAME = "keysearch";
    public static final String TREE_PARAM = "parentValue";
    public ModelAndView errorModel = new ModelAndView("error");
    protected static final String ERROR_PAGE = "error";
    protected static final String CURRENT_USER = "CURRENT_USER";

    protected String getRemoteIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

    protected int getCurrentPage(HttpServletRequest request) {
        try {
            String curPage = request.getParameter(PG_CURPAGE);
            if (curPage != null && !curPage.isEmpty()) {
                return Integer.parseInt(curPage);
            } else {
                return 1;
            }
        } catch (Exception ex) {
            logger.error(ex);
            return 0;
        }
    }

    protected String getParam(HttpServletRequest request, String paramName) {
        try {
            String rs = request.getParameter(paramName);
            return rs != null ? rs : "";
        } catch (Exception ex) {
            logger.error(ex);
            return "";
        }
    }

    protected int getTotalRecordPerPage(HttpServletRequest request) {
        try {
            String totalRecordPerPage = request.getParameter(PG_TOTAL_RECORD_PER_PAGE);
            if (totalRecordPerPage != null && !totalRecordPerPage.isEmpty()) {
                return Integer.parseInt(totalRecordPerPage);
            } else {
                return 10;
            }
        } catch (Exception ex) {
            logger.error(ex);
            return 10;
        }
    }
//
//    public String getAbsoluteUrl(String contextPath) {
//        String folderDir = ((HttpServletRequest) getRequest()).getSession()
//                .getServletContext().getRealPath(contextPath) + "/";
//        return folderDir;
//    }

    protected void addEventLog(EventLogs eventLogs) {
        eventLogsBusiness.insert(eventLogs);
    }

    protected void addEventLog(String action, String description, int status, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserSession userSession = getCurrentSession(request);
        String username = userSession.getUsername();
        EventLogs eventLogs = new EventLogs();
        eventLogs.setAction(action);
        eventLogs.setDescription(description);
        eventLogs.setEventDate(new Date());
        eventLogs.setIp(getRemoteIpAddress(request));
        eventLogs.setUserName(username);
        eventLogs.setUserId(userSession.getUserId());
        eventLogsBusiness.insert(eventLogs);
    }

    protected UserSession getCurrentSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute(CURRENT_USER);
        return userSession;
    }
}
