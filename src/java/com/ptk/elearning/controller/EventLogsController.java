/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.controller;

import com.google.gson.Gson;
import com.ptk.elearning.business.EventLogsBusinessImpl;
import com.ptk.elearning.dto.EventLogsDTO;
import com.ptk.elearning.util.JsonDataGrid;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/EventLogs")
public class EventLogsController extends BaseController {

    @Autowired
    private EventLogsBusinessImpl eventLogsBusiness;

    @RequestMapping(value = "/Index.html", method = RequestMethod.GET)
    public ModelAndView Index(Long userId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("eventlogs");
        return model;
    }

    @RequestMapping(value = "/getEventLogs.json", method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String search1(HttpServletRequest request) {
        JsonDataGrid dataGrid = new JsonDataGrid();
        Integer currentPage = getCurrentPage(request);
        Integer limit = getTotalRecordPerPage(request);
        if (currentPage < 1) {
            currentPage = 1;
        }
        Integer offset = (currentPage - 1) * limit;
        EventLogsDTO eventLogsDTO = new EventLogsDTO();
        eventLogsDTO.setFromDate(request.getParameter("fromDate"));
        eventLogsDTO.setToDate(request.getParameter("toDate"));
        Integer totalPage = eventLogsBusiness.getTotalRow(eventLogsDTO);
        if (totalPage > 0) {
            dataGrid.setData(eventLogsBusiness.getAll(eventLogsDTO, offset, limit));
        } else {
            dataGrid.setData(new ArrayList<EventLogsDTO>());
        }
        dataGrid.setTotalRecords(totalPage);
        dataGrid.setCurPage(getCurrentPage(request));
        return new Gson().toJson(dataGrid);
    }
}
