/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.controller;

import com.google.gson.Gson;
import com.ptk.elearning.bo.TblComment;
import com.ptk.elearning.bo.TblNews;
import com.ptk.elearning.bo.TblTopic;
import com.ptk.elearning.business.TblCommentBusiness;
import com.ptk.elearning.business.TblDivisionBusiness;
import com.ptk.elearning.business.TblNewsBusiness;
import com.ptk.elearning.business.TblTopicBusiness;
import com.ptk.elearning.common.CommonConstant;
import com.ptk.elearning.common.CommonUtil;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.ListNewsDTO;
import com.ptk.elearning.dto.TblCommentDTO;
import com.ptk.elearning.dto.TblDivisionDTO;
import com.ptk.elearning.dto.TblNewsDTO;
import com.ptk.elearning.dto.TblTopicDTO;
import com.ptk.elearning.dto.UserSession;
import com.ptk.elearning.util.Converter;
import com.ptk.elearning.util.DivisionConstant;
import com.ptk.elearning.util.JsonDataGrid;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/TblNews")
public class TblNewsController extends BaseController {
    
    @Autowired
    private TblNewsBusiness tblNewsBusiness;
    @Autowired
    private TblDivisionBusiness tblDivisionBusiness;
    @Autowired
    private TblCommentBusiness tblCommentBusiness;
    @Autowired
    private TblTopicBusiness tblTopicBusiness;
    
    @RequestMapping(value = "/Index.html", method = RequestMethod.GET)
    public ModelAndView Index(Long userId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("tbl_news");
        return model;
    }
    
    @RequestMapping(value = "/getAll.json", method = RequestMethod.GET,
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
        TblNewsDTO tblNewsDTO = new TblNewsDTO();
        //<editor-fold defaultstate="collapsed" desc="Các điều kiện tìm kiếm đầu vào">
        String title = URLDecoder.decode(request.getParameter("title"));
        Long topicId = Converter.converToLong(request.getParameter("topicId"));
        String fullname = URLDecoder.decode(request.getParameter("fullname"));
        String fromDate = URLDecoder.decode(request.getParameter("fromDate"));
        String toDate = URLDecoder.decode(request.getParameter("toDate"));
        tblNewsDTO.setTitle(title);
        tblNewsDTO.setTopicId(topicId);
        tblNewsDTO.setFromDate(fromDate);
        tblNewsDTO.setToDate(toDate);
        tblNewsDTO.setFullname(fullname);
        //</editor-fold>
        Integer totalPage = tblNewsBusiness.getTotalRow(tblNewsDTO);
        if (totalPage > 0) {
            dataGrid.setData(tblNewsBusiness.getAll(tblNewsDTO, offset, limit));
        } else {
            dataGrid.setData(new ArrayList<TblDivisionDTO>());
        }
        dataGrid.setTotalRecords(totalPage);
        dataGrid.setCurPage(getCurrentPage(request));
        return new Gson().toJson(dataGrid);
    }
    
    @RequestMapping(value = "/deleteNews.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String deleteNews(HttpServletRequest request) {
        try {
            Long newId = Converter.converToLong(request.getParameter("newId"));
            if (newId == 0) {
                return null;
            }
            ErrorResult result = tblNewsBusiness.delete(newId);
            addEventLog("DELETE", "DELETE_TBLNEW", result.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
            return new Gson().toJson(result);
        } catch (Exception ex) {
            return null;
        }
    }
    
    @RequestMapping(value = "/findById.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String findById(HttpServletRequest request) {
        try {
            Long newId = Converter.converToLong(request.getParameter("newId"));
            if (newId == 0) {
                return null;
            }
            return new Gson().toJson(tblNewsBusiness.findById(newId));
        } catch (Exception ex) {
            return null;
        }
    }
    
    @RequestMapping(value = {"/saveNews.html"}, method = RequestMethod.POST,
            produces = "text/html;charset=utf-8")
    public @ResponseBody
    String saveNews(@ModelAttribute("tblNewsForm") TblNews tblNews, HttpServletRequest request) throws JSONException {
        try {
            ErrorResult errorResult = new ErrorResult();
            if (tblNews.getNewId() == null || tblNews.getNewId() == 0) {
                tblNews.setDateCreated(new Date());
                errorResult = tblNewsBusiness.insert(tblNews);
            } else {
                TblNewsDTO newsDTO = tblNewsBusiness.findById(tblNews.getNewId());
                if (newsDTO == null) {
                    errorResult.setHasError(true);
                    errorResult.setError("Không tìm thấy tin tức cần cập nhật");
                    return new Gson().toJson(errorResult);
                }
                tblNews.setDateCreated(newsDTO.getDateCreated());
                errorResult = tblNewsBusiness.update(tblNews);
            }
            return new Gson().toJson(errorResult);
        } catch (Exception ex) {
            return null;
        }
    }
    
    @RequestMapping(value = "/getDataByDvsGroup.json", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String getDataByDvsGroup(HttpServletRequest request) {
        try {
            List<TblTopicDTO> lstTblTopicDTOs = tblTopicBusiness.getAllTopicBySubjectId(0, "", 0, 0);
            return new Gson().toJson(lstTblTopicDTOs);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String removeHtml(String html) {
        return Jsoup.parse(html).text();
    }

    @RequestMapping(value = "/news.html", method = RequestMethod.GET)
    public ModelAndView news(Long userId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        TblNewsDTO tblNewsDTO = new TblNewsDTO();
        Long topicId = 0L;
        if (!CommonUtil.isNull(request.getParameter("topicId"))) {
            topicId = Long.parseLong(request.getParameter("topicId"));
        }
        tblNewsDTO.setTopicId(topicId);
   
        String keyword = request.getParameter("keyword");
        model.addObject("keyword", keyword);
        String toDate = request.getParameter("toDate");
        model.addObject("toDate", toDate);
        String fromDate = request.getParameter("fromDate");
        model.addObject("fromDate", fromDate);
        tblNewsDTO.setFromDate(fromDate);
        tblNewsDTO.setToDate(toDate);
        tblNewsDTO.setTitle(keyword);
        final int numberPageDisplay = 5;
        final int limitPerPage = 10;
        int currentPage = 1;
        if (!CommonUtil.isNull(request.getParameter("page"))) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        int offset = CommonUtil.getOffset(limitPerPage, currentPage);
        List<TblNewsDTO> lstDTOs = tblNewsBusiness.getAll(tblNewsDTO, offset, limitPerPage);
        Integer totalRecords = tblNewsBusiness.getTotalRow(tblNewsDTO);
        List<Integer> listPaging = CommonUtil.getListPage(limitPerPage, numberPageDisplay, totalRecords, currentPage);
        ListNewsDTO listNewsDTO = new ListNewsDTO();
        listNewsDTO.setTotalPages(CommonUtil.getTotalPage(totalRecords, numberPageDisplay));
        listNewsDTO.setCurrentPage(currentPage);
        listNewsDTO.setListPaging(listPaging);
        listNewsDTO.setLstNewsDTOs(lstDTOs);
        lstDTOs.stream().forEach((item) -> {
            item.setContent(removeHtml(item.getContent()));
        });
        model.addObject("lstItem", listNewsDTO);
        model.setViewName("news");
        return model;
    }
    
    @RequestMapping(value = "/newsdetails.html", method = RequestMethod.GET)
    public ModelAndView news(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        Long newId = 0l;
        if (!CommonUtil.isNull(request.getParameter("id"))) {
            newId = Long.parseLong(request.getParameter("id"));
        }
        model.addObject("newId", newId);
        TblNewsDTO tblNewsDTO = tblNewsBusiness.findById(newId);
        model.addObject("tblNewsDTO", tblNewsDTO);
        
        List<TblCommentDTO> listComment = tblCommentBusiness.getAllCommentByNewId(newId);
        model.addObject("listComment", listComment);
        model.setViewName("newsdetail");
        return model;
    }
    
    @RequestMapping(value = "/newsdetails.html", method = RequestMethod.POST)
    public ModelAndView comment(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        Long newId = Long.parseLong(request.getParameter("id"));
        model.addObject("newId", newId);
        String content = request.getParameter("content");
        UserSession session = getCurrentSession(request);
        TblComment tblComment = new TblComment();
        tblComment.setContent(content);
        tblComment.setNewId(newId);
        tblComment.setDateComment(Calendar.getInstance().getTime());
        tblComment.setUserComment(session.getUserId());
        tblComment.setCommentId(0l);
        tblCommentBusiness.insert(tblComment);
        TblNewsDTO tblNewsDTO = tblNewsBusiness.findById(newId);
        model.addObject("tblNewsDTO", tblNewsDTO);
        
        List<TblCommentDTO> listComment = tblCommentBusiness.getAllCommentByNewId(newId);
        model.addObject("listComment", listComment);
        model.setViewName("newsdetail");
        return model;
    }
    
}
