/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.controller;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.ptk.elearning.bo.TblTopic;
import com.ptk.elearning.business.TblDivisionBusiness;
import com.ptk.elearning.business.TblTopicBusiness;
import com.ptk.elearning.common.CommonConstant;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblTopicDTO;
import com.ptk.elearning.util.Converter;
import com.ptk.elearning.util.DivisionConstant;
import com.ptk.elearning.util.JsonDataGrid;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/Topic")
public class TblTopicController extends BaseController {

    @Autowired
    private TblDivisionBusiness tblDivisionBusiness;
    @Autowired
    private TblTopicBusiness tblTopicBusiness;

    @RequestMapping(value = "/Index.html", method = RequestMethod.GET)
    public ModelAndView Index(Long userId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("tbl_topic");
        model.addObject("subject", tblDivisionBusiness.getChildById(DivisionConstant.SUBJECT));
        return model;
    }

    @RequestMapping(value = "/getAllTopic.json", method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String getAllTopic(HttpServletRequest request) {
        JsonDataGrid dataGrid = new JsonDataGrid();
        Integer currentPage = getCurrentPage(request);
        Integer limit = getTotalRecordPerPage(request);
        if (currentPage < 1) {
            currentPage = 1;
        }
        Integer offset = (currentPage - 1) * limit;
        Integer subjectId = 0;
        String subject = request.getParameter("subjectId");
        if (!Strings.isNullOrEmpty(subject)) {
            subjectId = Integer.parseInt(subject);
        }
        String keyWord = request.getParameter("keyWord");
        Integer total = tblTopicBusiness.getTotalTopicBySubjectId(subjectId, keyWord);
        if (total > 0) {
            dataGrid.setData(tblTopicBusiness.getAllTopicBySubjectId(subjectId, keyWord, limit, offset));
        } else {
            dataGrid.setData(new ArrayList<>());
        }
        dataGrid.setTotalRecords(total);
        dataGrid.setCurPage(getCurrentPage(request));
        return new Gson().toJson(dataGrid);
    }
    
    
    @RequestMapping(value = {"/saveTopic.html"}, method = RequestMethod.POST,
            produces = "text/html;charset=utf-8")
    public @ResponseBody
    String saveQuestion(@ModelAttribute("tblTopicForm") TblTopicDTO tblTopicDTO, HttpServletRequest request) throws JSONException {
        try {
            ErrorResult errorResult = new ErrorResult();
            if (tblTopicDTO.getId()== null || tblTopicDTO.getId() == 0) {
                errorResult = tblTopicBusiness.insert(tblTopicDTO.toModel());
                addEventLog("INSERT", "INSERT_TBLTOPIC", errorResult.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
            } else {
                TblTopic tblTopic = tblTopicBusiness.findById(tblTopicDTO.getId());
                if (tblTopic == null) {
                    errorResult.setHasError(true);
                    errorResult.setError("Không tìm thấy đề thi cần cập nhật");
                    return new Gson().toJson(errorResult);
                }
                errorResult = tblTopicBusiness.update(tblTopicDTO.toModel());
                addEventLog("UPDATE", "INSERT_TBLTOPIC", errorResult.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
            }
            return new Gson().toJson(errorResult);
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/deleteTopic.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String deleteAnswer(HttpServletRequest request) {
        try {
            Long id = Converter.converToLong(request.getParameter("id"));
            if (id == null || id == 0) {
                return null;
            }
            ErrorResult result = tblTopicBusiness.delete(id);
            addEventLog("DELETE", "DELETE_TBLANSWER", result.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
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
            Long id = Converter.converToLong(request.getParameter("id"));
            if (id == null || id == 0) {
                return null;
            }
            TblTopic tblTopic = tblTopicBusiness.findById(id);
            return new Gson().toJson(tblTopic);
        } catch (Exception ex) {
            return null;
        }
    }
}
