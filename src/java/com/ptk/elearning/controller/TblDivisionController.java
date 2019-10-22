/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.controller;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.ptk.elearning.bo.EventLogs;
import com.ptk.elearning.bo.TblDivision;
import com.ptk.elearning.bo.TblUsers;
import com.ptk.elearning.business.TblDivisionBusiness;
import com.ptk.elearning.common.CommonConstant;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblDivisionDTO;
import com.ptk.elearning.dto.UserSession;
import com.ptk.elearning.util.Converter;
import com.ptk.elearning.util.JsonDataGrid;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/TblDivision")
public class TblDivisionController extends BaseController {

    @Autowired
    private TblDivisionBusiness tblDivisionBusiness;

    @RequestMapping(value = "/Index.html", method = RequestMethod.GET)
    public ModelAndView Index(Long userId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("tbl_divison");
        return model;
    }

    @RequestMapping(value = "/Search1.json", method = RequestMethod.GET,
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
        TblDivisionDTO divisionDTO = new TblDivisionDTO();
        String dvsGroup = URLDecoder.decode(request.getParameter("dvsGroup"));
        String description = URLDecoder.decode(request.getParameter("description"));
        divisionDTO.setDvsGroup(dvsGroup);
        divisionDTO.setDescription(description);
        Integer totalPage = tblDivisionBusiness.getTotalRow(divisionDTO);
        if (totalPage > 0) {
            dataGrid.setData(tblDivisionBusiness.getAll(divisionDTO, offset, limit));
        } else {
            dataGrid.setData(new ArrayList<TblDivisionDTO>());
        }
        dataGrid.setTotalRecords(totalPage);
        dataGrid.setCurPage(getCurrentPage(request));
        return new Gson().toJson(dataGrid);
    }

    @RequestMapping(value = {"/addDivision.html"}, method = RequestMethod.POST,
            produces = "text/html;charset=utf-8")
    public @ResponseBody
    String addDivision(@ModelAttribute("tblDivision") TblUsers tblUsers, HttpServletRequest request) throws JSONException {
        JSONObject result = new JSONObject();
        JSONObject resultError = new JSONObject();
//        Integer dvsParent = Converter.converToInt(request.getParameter("dvsParent"));
        String description = request.getParameter("description");
        String dvsGroup = request.getParameter("dvsGroup");
        String[] lstDvsName = request.getParameterValues("dvsName");
        String[] lstDvsValue = request.getParameterValues("dvsValue");
        String[] lstDvsOrder = request.getParameterValues("dvsOrder");
        String[] dvsId = request.getParameterValues("dvsId");
        int length = dvsId.length;
        String validation = "";
        int arrSize = lstDvsName.length;
        boolean isSaved = false;
        UserSession userSession = getCurrentSession(request);
        for (int i = 0; i < arrSize; i++) {
            try {
                TblDivision tblDivision = new TblDivision();
                if (i < length) {
                    tblDivision.setDvsId(Converter.converToLong(dvsId[i]));
                }
//                tblDivision.setDvsParent(dvsParent);
                tblDivision.setUserCreated(userSession.getUserId());
                tblDivision.setDvsGroup(dvsGroup);
                tblDivision.setDescription(description);
                tblDivision.setDvsName(lstDvsName[i]);
                tblDivision.setDvsValue(Converter.converToInt(lstDvsValue[i]));
                tblDivision.setDvsOrder(Converter.converToInt(lstDvsOrder[i]));
                validation = validate(tblDivision);
                if (Strings.isNullOrEmpty(validation)) {
                    ErrorResult errorResult = new ErrorResult();
                    EventLogs eventLogs = new EventLogs();
                    if (tblDivision.getDvsId() != null && tblDivision.getDvsId() > 0) {
                        errorResult = tblDivisionBusiness.update(tblDivision);
                        eventLogs.setAction("UPDATE");
                        eventLogs.setDescription("UPDATE_TBLDIVISION");
                        eventLogs.setEventDate(new Date());
                        eventLogs.setIp(getRemoteIpAddress(request));
                        eventLogs.setStatus(errorResult.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS);
                        eventLogs.setUserId(0L);
                    } else {
                        errorResult = tblDivisionBusiness.insert(tblDivision);
                        eventLogs.setAction("INSERT");
                        eventLogs.setDescription("INSERT_TBLDIVISION");
                        eventLogs.setEventDate(new Date());
                        eventLogs.setIp(getRemoteIpAddress(request));
                        eventLogs.setStatus(errorResult.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS);
                        eventLogs.setUserId(0L);
                    }
                    addEventLog(eventLogs);
                    if (!errorResult.isHasError()) {
                        isSaved = true;
                    } else {
                        resultError = new JSONObject(errorResult);
                    }
                    if (isSaved) {
                        result = new JSONObject(errorResult);
                    }
                }
            } catch (Exception ex) {
                logger.error(ex);
            }
        }
        if (!Strings.isNullOrEmpty(validation)) {
            return validation;
        }
        if (isSaved) {
            return result.toString();
        } else {
            return resultError.toString();
        }
    }

    @RequestMapping(value = "/getAllParent.json", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String getAllParent(HttpServletRequest request) {
        try {
            return new Gson().toJson(tblDivisionBusiness.getAllParent());
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/getChildById.json", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String getChildById(HttpServletRequest request) {
        try {
            String dvsGroup = request.getParameter("dvsGroup");
            if (Strings.isNullOrEmpty(dvsGroup)) {
                return null;
            }
            return new Gson().toJson(tblDivisionBusiness.getChildById(dvsGroup));
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/getDataByDvsGroup.json", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String getDataByDvsGroup(HttpServletRequest request) {
        try {
            String dvsGroup = request.getParameter("dvsGroup");
            if (Strings.isNullOrEmpty(dvsGroup)) {
                return null;
            }
            return new Gson().toJson(tblDivisionBusiness.getDataByDvsGroup(dvsGroup));
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/deleteChild.json", method = RequestMethod.DELETE,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String deleteChild(HttpServletRequest request) {
        try {
            Integer dvsId = Converter.converToInt(request.getParameter("dvsId"));
            if (dvsId == 0) {
                return null;
            }
            List<TblDivisionDTO> lstChild = tblDivisionBusiness.getChildById(dvsId);
            ErrorResult result = new ErrorResult();
            if (!lstChild.isEmpty()) {
                result.setHasError(true);
                result.setError("Bạn cần xóa các con của danh mục trước");
                return new Gson().toJson(result);
            }
            result = tblDivisionBusiness.delete(dvsId);
            addEventLog("DELETE", "DELETE_TBLDIVISION", result.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
            return new Gson().toJson(result);
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/findByDvsGroup.json", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String findByDvsGroup(HttpServletRequest request) {
        try {
            String dvsGroup = request.getParameter("dvsGroup");
            if (Strings.isNullOrEmpty(dvsGroup)) {
                return null;
            }
            TblDivision division = tblDivisionBusiness.findByDvsGroup(dvsGroup);
            System.out.println(division.toString());
            return new Gson().toJson(division);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/deleteDivision.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String deleteDivision(HttpServletRequest request) {
        try {
            String dvsGroup = request.getParameter("dvsGroup");
            if (Strings.isNullOrEmpty(dvsGroup)) {
                return null;
            }
            ErrorResult result = tblDivisionBusiness.delete(dvsGroup);
            addEventLog("DELETE", "DELETE_TBLDIVISION", result.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
            return new Gson().toJson(result);
        } catch (Exception ex) {
            return null;
        }
    }

    private String validate(TblDivision tblDivision) {
        return null;
    }
}
