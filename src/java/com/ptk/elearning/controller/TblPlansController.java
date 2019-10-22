/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.controller;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.ptk.elearning.bo.TblPlans;
import com.ptk.elearning.business.TblPlansBusiness;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblPlansDTO;
import com.ptk.elearning.dto.UserSession;
import com.ptk.elearning.util.Converter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TblPlansController extends BaseController {

    @Autowired
    private TblPlansBusiness tblPlansBusiness;

    @RequestMapping(value = "/Plans.html", method = RequestMethod.GET)
    public ModelAndView Index(Long userId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("plan");

        return model;
    }

    @RequestMapping(value = "/getPlans.json", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String getPlans(HttpServletRequest request) {
        try {
            UserSession userSession = getCurrentSession(request);
            TblPlansDTO plansDTO = new TblPlansDTO();
            plansDTO.setUserId(userSession.getUserId());
            Calendar cal = Calendar.getInstance();
            int month = Strings.isNullOrEmpty(request.getParameter("month")) ? cal.get(Calendar.MONTH) : Converter.converToInt(request.getParameter("month"));
            int year = Strings.isNullOrEmpty(request.getParameter("year")) ? cal.get(Calendar.YEAR) : Converter.converToInt(request.getParameter("year"));
            plansDTO.setMonth(month + 1);
            plansDTO.setYear(year);
            return new Gson().toJson(tblPlansBusiness.getData(plansDTO));
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/savePlans.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String savePlans(HttpServletRequest request) {
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String color = request.getParameter("color");
        Long planId = Converter.converToLong(request.getParameter("planId"));
        ErrorResult result;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date from = Converter.convertFullDate(fromDate, formatter);
        Date to = Converter.convertFullDate(toDate, formatter);
        TblPlans plans = new TblPlans();
        plans.setContent(content);
        plans.setTitle(title);
        plans.setFromDate(from);
        plans.setToDate(to);
        plans.setType(type);
        plans.setColor(color);
        plans.setPlanId(planId);
        plans.setUserId(getCurrentSession(request).getUserId());
        if (planId == null || planId == 0) {
            result = tblPlansBusiness.insert(plans);
            if (result.isHasError()) {
                result.setError("Có lỗi khi thêm mới kế hoạch. Mời thử lại");
            } else {
                result.setId(plans.getPlanId());
                result.setError("Thêm mới kế hoạch thành công");
            }
        } else {
            result = tblPlansBusiness.update(plans);
            if (result.isHasError()) {
                result.setError("Có lỗi khi cập nhật kế hoạch. Mời thử lại");
            } else {
                result.setError("Cập nhật kế hoạch thành công");
            }
        }
        return new Gson().toJson(result);
    }

    @RequestMapping(value = "/deletePlans.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String deletePlans(HttpServletRequest request) {
        try {
            Long planId = Converter.converToLong(request.getParameter("planId"));
            ErrorResult error = tblPlansBusiness.delete(planId);
            if (error.isHasError()) {
                error.setError("Có lỗi khi xóa kế hoạch. Mời thử lại");
            } else {
                error.setError("Xóa kế hoạch thành công");
            }
            return new Gson().toJson(error);
        } catch (Exception ex) {
            return null;
        }
    }
}
