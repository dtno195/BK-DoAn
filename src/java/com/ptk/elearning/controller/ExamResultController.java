/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.controller;

import com.google.gson.Gson;
import com.ptk.elearning.bo.ExamResult;
import com.ptk.elearning.bo.TblAnswer;
import com.ptk.elearning.business.ExamResultBusiness;
import com.ptk.elearning.business.TblAnswerBusiness;
import com.ptk.elearning.business.TblDivisionBusiness;
import com.ptk.elearning.business.TblExamBusiness;
import com.ptk.elearning.business.TblQuestionsBusiness;
import com.ptk.elearning.chart.GetChartData;
import com.ptk.elearning.common.CommonUtil;
import com.ptk.elearning.common.Mail;
import com.ptk.elearning.dto.ExamResultDTO;
import com.ptk.elearning.dto.ListExamDTO;
import com.ptk.elearning.dto.ResultOfExam;
import com.ptk.elearning.dto.TblDivisionDTO;
import com.ptk.elearning.dto.TblExamDTO;
import com.ptk.elearning.dto.TblQuestionsDTO;
import com.ptk.elearning.dto.UserSession;
import com.ptk.elearning.util.DivisionConstant;
import com.ptk.elearning.util.StringUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ExamResultController extends BaseController {

    @Autowired
    ExamResultBusiness examResultBusiness;
    @Autowired
    TblDivisionBusiness tblDivisionBusiness;
    @Autowired
    TblQuestionsBusiness tblQuestionsBusiness;
    @Autowired
    TblAnswerBusiness tblAnswerBusiness;
    @Autowired
    TblExamBusiness tblExamBusiness;

    @RequestMapping(value = "/examResult.html")
    public ModelAndView examResult(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        UserSession userSession = getCurrentSession(request);
        Long user = userSession.getUserId();
        final int numberPageDisplay = 5;
        final int limitPerPage = 10;
        int currentPage = 1;
        // get list mon hoc
        List<TblDivisionDTO> lstSubject = tblDivisionBusiness.getChildById(DivisionConstant.SUBJECT);
        model.addObject("lstSubject", lstSubject);
        // get name by id

        // ve chart
        int year = 2018;
        int month = 0;
        int subject = 3;
        int userId = Integer.parseInt(user.toString());
        if (request.getParameter("year") != null) {
            year = Integer.parseInt(request.getParameter("year"));
            month = Integer.parseInt(request.getParameter("month"));
            subject = Integer.parseInt(request.getParameter("subject"));
        }
        GetChartData getChartData = new GetChartData();
        String jsonData = null;
        try {
            jsonData = getChartData.getDataChartDetail("chartDetailPTK", year, month, subject, userId, examResultBusiness);
        } catch (JSONException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addObject("jsonData", jsonData);
        model.addObject("year", year);
        model.addObject("month", month);
        model.addObject("subject", subject);
        if (!CommonUtil.isNull(request.getParameter("page"))) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        int offset = CommonUtil.getOffset(limitPerPage, currentPage);
        List<ExamResultDTO> lstExamResultDTOs = examResultBusiness.getAllResultOfUser(user, limitPerPage, offset);
        Integer totalRecords = examResultBusiness.totalResultOfUser(user);
        List<Integer> listPaging = CommonUtil.getListPage(limitPerPage, numberPageDisplay, totalRecords, currentPage);
        ListExamDTO listExamDTO = new ListExamDTO();
        listExamDTO.setTotalPages(CommonUtil.getTotalPage(totalRecords, numberPageDisplay));
        listExamDTO.setCurrentPage(currentPage);
        listExamDTO.setListPaging(listPaging);
        listExamDTO.setLstExamResultDTOs(lstExamResultDTOs);
        model.addObject("lstItem", listExamDTO);
        model.setViewName("examResult");
        return model;
    }

    private List<ResultOfExam> getResultOfTest(String result) {
        List<ResultOfExam> lstResultTestDTOs = new ArrayList<>();
        if (!StringUtil.isEmpty(result)) {
            String[] arrStr = result.split(";");
            for (String item : arrStr) {
                if (item != null && item != "") {
                    String[] temp = item.split(":");
                    ResultOfExam resultTestDTO = new ResultOfExam();
                    resultTestDTO.setQuestionId(Long.parseLong((temp[0] != null && temp[0].trim().length() > 0) ? temp[0] : "0"));
                    resultTestDTO.setAnswerId(temp.length > 1 ? Long.parseLong((temp[1] != null && temp[1].trim().length() > 0) ? temp[1] : "0") : 0);
                    lstResultTestDTOs.add(resultTestDTO);
                }
            }
        }
        return lstResultTestDTOs;
    }

    @RequestMapping(value = {"markTest.html"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public ModelAndView markTest(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Double totalPointTest = (Double) session.getAttribute("totalPointTest");
        Integer totalQuestionCorrect = (Integer) session.getAttribute("totalQuestionCorrect");
        Integer totalQuestion = (Integer) session.getAttribute("totalQuestion");
        String examName = (String) session.getAttribute("examName");
        List<ResultOfExam> lstResultOfExams = new ArrayList<>();
        lstResultOfExams = (List<ResultOfExam>) session.getAttribute("lstResultOfExams");
        model.addAttribute("lstResultOfExams", lstResultOfExams);
        model.addAttribute("totalQuestionCorrect", totalQuestionCorrect);
        model.addAttribute("totalQuestion", totalQuestion);
        model.addAttribute("totalPointTest", totalPointTest);
        model.addAttribute("examName", examName);
        return new ModelAndView("resultTest");
    }

    @RequestMapping(value = {"/completeTest.html"}, method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody
    String completeTest(HttpServletRequest request, @RequestParam("examId") String exam, @RequestParam("result") String result) {
        try {
            Long examId = Long.parseLong(exam);
            TblExamDTO tblExam = tblExamBusiness.findById(examId);
            Integer totalQuestion = tblQuestionsBusiness.totalQuestionOfExam(examId);
            Integer totalQuestionCorrect = 0;
            Double totalPointTest = 0.0;
            Double pointOfQuestion = Double.parseDouble("10") / totalQuestion;
            List<ResultOfExam> lstResultOfExams = getResultOfTest(result);
            for (ResultOfExam item : lstResultOfExams) {
                TblQuestionsDTO questionDTO = tblQuestionsBusiness.getDTOById(item.getQuestionId());
                TblAnswer answer = tblAnswerBusiness.getAnswerCorrectOfQuestion(item.getQuestionId());
                TblAnswer answerDTO = tblAnswerBusiness.getAnswerById(item.getAnswerId());
                if (questionDTO != null) {
                    item.setQuestionContent(!StringUtil.isEmpty(questionDTO.getContent()) ? questionDTO.getContent() : "");
                }
                if (answer != null) {
                    item.setAnswerCorrectContent(!StringUtil.isEmpty(answer.getContent()) ? answer.getContent() : "");
                }
                if (answerDTO != null) {
                    item.setAnswerContent(!StringUtil.isEmpty(answerDTO.getContent()) ? answerDTO.getContent() : "");
                    if (answerDTO.getIsTrue() != null && answerDTO.getIsTrue() == true) {
                        totalPointTest = totalPointTest + pointOfQuestion;
                        totalQuestionCorrect++;
                    }
                }
                if (answer != null && answerDTO != null) {
                    if (Objects.equals(answer.getAnswerId(), answerDTO.getAnswerId())) {
                        item.setFlagIsRight(1);
                    }
                }
            }
            ExamResult examResult = new ExamResult();
            Calendar cal = Calendar.getInstance();
            Date today = cal.getTime(); // Fri Jun 17 14:54:28 PDT 2016
            int month = cal.get(Calendar.MONTH); // 5 int year = cal.get(Calendar.YEAR); // 2016
            int year = cal.get(Calendar.YEAR); // 2016
            examResult.setDateOfTest(today);
            examResult.setExamId(examId);
            examResult.setExamMonth(month);
            examResult.setExamYear(year);
            examResult.setUserId(getCurrentSession(request).getUserId());
            examResult.setScore(totalPointTest);
            examResultBusiness.insert(examResult);
            HttpSession session = request.getSession();
            session.setAttribute("lstResultOfExams", lstResultOfExams);
            session.setAttribute("totalPointTest", totalPointTest);
            session.setAttribute("totalQuestion", totalQuestion);
            session.setAttribute("totalQuestionCorrect", totalQuestionCorrect);
            session.setAttribute("examName", tblExam.getName());
            return new Gson().toJson("success");
        } catch (Exception e) {
            return new Gson().toJson("error");
        }
    }

    @RequestMapping(value = {"/sendMailToUser.html"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public @ResponseBody
    String sendMailToUser(HttpServletRequest request) {
        String result = "";
        String html = "";
        HttpSession session = request.getSession();
        Double totalPointTest = (Double) session.getAttribute("totalPointTest");
        Integer totalQuestionCorrect = (Integer) session.getAttribute("totalQuestionCorrect");
        Integer totalQuestion = (Integer) session.getAttribute("totalQuestion");
        String nameThreads = (String) session.getAttribute("examName");
        List<ResultOfExam> lstResultOfExams = new ArrayList<>();
        lstResultOfExams = (List<ResultOfExam>) session.getAttribute("lstResultOfExams");
        UserSession userSession = (UserSession) session.getAttribute("CURRENT_USER");
        html = " Họ và tên thí sinh: " + userSession.getFullName() + " <br />";
        html += "Tên đề bài :  " + nameThreads + "<br />";
        html += "Số câu đúng: " + totalQuestionCorrect + "/" + totalQuestion + " <br />";
        html += "Tổng điểm  : " + totalPointTest + " <br />";
        html += "<table border='1'>";
        html += " <thead>";
        html += "     <tr style='text-align: center'>";
        html += "         <th style='width: 20px'>";
        html += "             STT";
        html += "         </th>";
        html += "         <th>";
        html += "             Câu hỏi";
        html += "         </th>";
        html += "         <th>";
        html += "             Đáp án đúng ";
        html += "         </th>";
        html += "         <th>";
        html += "             Đáp án của bạn";
        html += "         </th>";
        html += "     </tr>";
        html += " </thead>";
        html += " <tbody>";
        int i = 1;
        for (ResultOfExam item : lstResultOfExams) {
            html += "<tr>";
            html += "<td>";
            html += i;
            html += "</td>";
            html += "<td>";
            html += item.getQuestionContent() != null ? item.getQuestionContent() : "";
            html += "</td>";
            html += "<td>";
            html += item.getAnswerCorrectContent() != null ? item.getAnswerCorrectContent() : "";
            html += "</td>";
            html += "<td>";
            html += item.getAnswerContent() != null ? item.getAnswerContent() : "";
            html += "</td>";
            html += "</tr>";
            i++;
        }
        html += "</tbody>";
        html += "</table>";
        if (!StringUtil.isEmpty(request.getParameter("userMail"))) {
            result = request.getParameter("userMail");
            try {
                Mail.send(result, "KẾT QUẢ KIỂM TRA", html);
            } catch (Exception ex) {
                Logger.getLogger(ExamResultController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "success";
    }
}
