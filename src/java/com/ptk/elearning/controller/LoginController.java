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
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.UserSession;
import com.ptk.elearning.util.DivisionConstant;
import com.ptk.elearning.util.PasswordUtil;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController extends BaseController {

    @Autowired
    private ITblUsersBusiness iTblUsersBusiness;

    @RequestMapping(value = "/Login.html", method = RequestMethod.GET)
    public ModelAndView Index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/Logout.html", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        HttpSession session = request.getSession();
        session.invalidate();
        return model;
    }
    @RequestMapping(value = "/register.html", method = RequestMethod.GET)
    public ModelAndView signup(){
        return new ModelAndView("signup");
    }
//    public ModelAndView 
    @RequestMapping(value = "/authenticate.html", method = RequestMethod.POST)
    public ModelAndView authenticate(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "captchaValue", required = false) String captchaValue,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:Login.html");
        HttpSession session = request.getSession();
        UserSession userSession = getCurrentSession(request);
        if (Strings.isNullOrEmpty(username) || password == null || password.isEmpty()) {
            model.addObject("error", "Chưa nhập tên đăng nhập hoặc mật khẩu");
            return model;
        }
        username = username.trim();
        model.addObject("username", username);
        TblUsers usersDTO = iTblUsersBusiness.findByUsername(username);
        if (usersDTO == null || usersDTO.getUserId() == null) {
            model.addObject("error", "Sai tên đăng nhập hoặc mật khẩu");
            return model;
        }
        // Check thoi gian
        if (PasswordUtil.verifyUserPassword(password, usersDTO.getPassword(), usersDTO.getSalt())) {
            if (userSession == null) {
                userSession = new UserSession();
            }
            userSession.setIsLock(usersDTO.getIsLock());
            userSession.setHighSchool(usersDTO.getHighSchool());
            userSession.setAspiration(usersDTO.getAspiration());
            userSession.setUserId(usersDTO.getUserId());
            userSession.setUsername(usersDTO.getUserName());
            userSession.setFullName(usersDTO.getFullName());
            model.addObject("username", null);
            String url = "";
            if (Objects.equals(usersDTO.getRole(), DivisionConstant.ADMIN)) {
                model.setViewName("redirect:TblExam/Index.html");
            }else{
                model.setViewName("redirect:dashboard.html");
            }
            session.invalidate();
            session = request.getSession(true);
            session.setAttribute(CURRENT_USER, userSession);
            return model;
        } else {
            model.addObject("error", "Sai tên đăng nhập hoặc mật khẩu");
        }

        return model;
    }
    @RequestMapping(value = {"/signup.html"}, method = RequestMethod.POST,
            produces = "text/html;charset=utf-8")
    public @ResponseBody
    ModelAndView addUser(TblUsers tblUsers, HttpServletRequest request) throws JSONException {
        String birthDay = request.getParameter("birthdate");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (!Strings.isNullOrEmpty(birthDay)) {
            try {
                tblUsers.setDateOfBirth(sdf.parse(birthDay));
            } catch (ParseException ex) {
            }
        }
        tblUsers.setDateCreated(new Date());
        tblUsers.setRole(2);
        tblUsers.setIsLock(true);
        String salt = PasswordUtil.getSalt(6);
        tblUsers.setSalt(salt);
        tblUsers.setPassword(PasswordUtil.encrypt(tblUsers.getPassword(), salt));
        ErrorResult errorResult = iTblUsersBusiness.insert(tblUsers);
        if (errorResult.isHasError()) {
            return new ModelAndView("signup");
        } else {
            return new ModelAndView("login");
        }
    }
}
