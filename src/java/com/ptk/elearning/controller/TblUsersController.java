/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.controller;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ptk.elearning.bo.TblUsers;
import com.ptk.elearning.business.ITblUsersBusiness;
import com.ptk.elearning.business.TblDivisionBusiness;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblUsersDTO;
import com.ptk.elearning.dto.UserSession;
import com.ptk.elearning.util.Converter;
import com.ptk.elearning.util.DivisionConstant;
import com.ptk.elearning.util.JsonDataGrid;
import com.ptk.elearning.util.PasswordUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@RequestMapping(value = "/TblUser")
public class TblUsersController extends BaseController {

    @Autowired
    ITblUsersBusiness iTblUsersBusiness;
    @Autowired
    private TblDivisionBusiness tblDivisionBusiness;

    @RequestMapping(value = "/Index.html", method = RequestMethod.GET)
    public ModelAndView Index(Long userId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("tbl_user");
        model.addObject("role", tblDivisionBusiness.getDataByDvsGroup(DivisionConstant.ROLE_ID));
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
        TblUsersDTO usersDTO = new TblUsersDTO();
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        Integer role = Converter.converToIntOrNull(request.getParameter("role"));
        usersDTO.setUserName(username);
        usersDTO.setFullName(fullname);
        usersDTO.setRole(role);
        Integer offset = (currentPage - 1) * limit;
        Integer totalPage = iTblUsersBusiness.getTotalRow(usersDTO);
        if (totalPage > 0) {
            dataGrid.setData(iTblUsersBusiness.getAll(usersDTO, offset, limit));
        } else {
            dataGrid.setData(new ArrayList<>());
        }
        dataGrid.setTotalRecords(totalPage);
        dataGrid.setCurPage(getCurrentPage(request));
        return new Gson().toJson(dataGrid);
    }

    @RequestMapping(value = {"/addUser.html"}, method = RequestMethod.POST,
            produces = "text/html;charset=utf-8")
    public @ResponseBody
    String addUser(@ModelAttribute("tblUsersForm") TblUsers tblUsers, HttpServletRequest request) throws JSONException {
        String birthDay = request.getParameter("birthdate");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (!Strings.isNullOrEmpty(birthDay)) {
            try {
                tblUsers.setDateOfBirth(sdf.parse(birthDay));
            } catch (ParseException ex) {
            }
        }
        UserSession userSession = getCurrentSession(request);
        tblUsers.setDateCreated(new Date());
        String salt = PasswordUtil.getSalt(6);
        tblUsers.setSalt(salt);
        tblUsers.setPassword(PasswordUtil.encrypt(tblUsers.getPassword(), salt));
        ErrorResult errorResult = iTblUsersBusiness.insert(tblUsers);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(errorResult);
    }

    @RequestMapping(value = {"/updateUser.html"}, method = RequestMethod.POST,
            produces = "text/html;charset=utf-8")
    public @ResponseBody
    String updateUser(@ModelAttribute("tblUsersForm") TblUsers tblUsers, HttpServletRequest request) throws JSONException {
        String birthDay = request.getParameter("birthdate");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (!Strings.isNullOrEmpty(birthDay)) {
            try {
                tblUsers.setDateOfBirth(sdf.parse(birthDay));
            } catch (ParseException ex) {

            }
        }
        ErrorResult errorResult = new ErrorResult();
        TblUsers users = iTblUsersBusiness.findById(tblUsers.getUserId());
        if (users == null) {
            errorResult.setError("Không tìm thấy người dùng cần cập nhật");
            errorResult.setHasError(true);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(errorResult);
        }
        tblUsers.setDateCreated(users.getDateCreated());
        tblUsers.setSalt(users.getSalt());
        tblUsers.setPassword(users.getPassword());
        errorResult = iTblUsersBusiness.update(tblUsers);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(errorResult);
    }

    @RequestMapping(value = {"/removeUser.html"}, method = RequestMethod.POST,
            produces = "text/html;charset=utf-8")
    public @ResponseBody
    String removeUser(HttpServletRequest request) throws JSONException {
        ErrorResult errorResult = new ErrorResult();
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            errorResult = iTblUsersBusiness.delete(userId);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(errorResult);
        } catch (Exception e) {
            errorResult.setHasError(true);
            errorResult.setError("Không tìm thấy người dùng cần xóa");
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(errorResult);
    }

    @RequestMapping(value = "/findById.json", method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String findById(HttpServletRequest request) {
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            TblUsers users = iTblUsersBusiness.findById(userId);
            if (users != null) {
                users.setPassword("");
                users.setSalt("");
            }
            return new Gson().toJson(users);
        } catch (Exception ex) {
            return null;
        }

    }
}
