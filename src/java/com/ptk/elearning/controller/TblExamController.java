/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.controller;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ptk.elearning.bo.TblExam;
import com.ptk.elearning.bo.TblExamQuestion;
import com.ptk.elearning.business.TblAnswerBusiness;
import com.ptk.elearning.business.TblDivisionBusiness;
import com.ptk.elearning.business.TblExamBusiness;
import com.ptk.elearning.business.TblExamQuestionBusiness;
import com.ptk.elearning.business.TblQuestionsBusiness;
import com.ptk.elearning.common.CommonConstant;
import com.ptk.elearning.common.CommonUtil;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.ListExamDTO;
import com.ptk.elearning.dto.TblDivisionDTO;
import com.ptk.elearning.dto.TblExamDTO;
import com.ptk.elearning.dto.TblQuestionsDTO;
import com.ptk.elearning.util.Converter;
import com.ptk.elearning.util.DivisionConstant;
import com.ptk.elearning.util.ExamExport;
import com.ptk.elearning.util.JsonDataGrid;
import com.ptk.elearning.util.StringUtil;
import com.ptk.elearning.util.ZipDirectoryExample;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping(value = "/TblExam")
public class TblExamController extends BaseController {
    
    @Autowired
    private TblExamBusiness tblExamBusiness;
    @Autowired
    private TblDivisionBusiness tblDivisionBusiness;
    @Autowired
    private TblQuestionsBusiness tblQuestionsBusiness;
    @Autowired
    private TblExamQuestionBusiness tblExamQuestionBusiness;
    
    @Autowired
    private TblAnswerBusiness tblAnswerBusiness;
    
    @RequestMapping(value = "/Index.html", method = RequestMethod.GET)
    public ModelAndView Index(Long userId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("tbl_exam");
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
        TblExamDTO tblExamDTO = new TblExamDTO();
        //<editor-fold defaultstate="collapsed" desc="Các điều kiện tìm kiếm đầu vào">
        String name = URLDecoder.decode(request.getParameter("name"));
        Integer subjectId = Converter.converToInt(request.getParameter("subjectId"));
        Integer timeId = Converter.converToInt(request.getParameter("timeId"));
        tblExamDTO.setSubjectId(subjectId);
        tblExamDTO.setTimeId(timeId);
        tblExamDTO.setName(name);
        //</editor-fold>
        Integer totalPage = tblExamBusiness.getTotalRow(tblExamDTO);
        if (totalPage > 0) {
            dataGrid.setData(tblExamBusiness.getAll(tblExamDTO, offset, limit));
        } else {
            dataGrid.setData(new ArrayList<TblExamDTO>());
        }
        dataGrid.setTotalRecords(totalPage);
        dataGrid.setCurPage(getCurrentPage(request));
        return new Gson().toJson(dataGrid);
    }
    
    @RequestMapping(value = "/deleteExam.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String deleteExam(HttpServletRequest request) {
        try {
            Long examId = Converter.converToLong(request.getParameter("examId"));
            if (examId == null || examId == 0) {
                return null;
            }
            ErrorResult result = tblExamBusiness.delete(examId);
            addEventLog("DELETE", "DELETE_TBLEXAM", result.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
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
            Long examId = Converter.converToLong(request.getParameter("examId"));
            if (examId == null || examId == 0) {
                return null;
            }
            return new Gson().toJson(tblExamBusiness.findById(examId));
        } catch (Exception ex) {
            return null;
        }
    }
    
    @RequestMapping(value = {"/saveExam.html"}, method = RequestMethod.POST,
            produces = "text/html;charset=utf-8")
    public @ResponseBody
    String saveExam(@ModelAttribute("tblExamForm") TblExam tblExam, HttpServletRequest request) throws JSONException {
        try {
            ErrorResult errorResult = new ErrorResult();
            if (tblExam.getExamId() == null || tblExam.getExamId() == 0) {
                tblExam.setDateCreated(new Date());
                errorResult = tblExamBusiness.insert(tblExam);
                addEventLog("INSERT", "INSERT_TBLEXAM", errorResult.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
            } else {
                TblExamDTO tblExamDTO = tblExamBusiness.findById(tblExam.getExamId());
                if (tblExamDTO == null) {
                    errorResult.setHasError(true);
                    errorResult.setError("Không tìm thấy đề thi cần cập nhật");
                    return new Gson().toJson(errorResult);
                }
                tblExam.setDateCreated(tblExamDTO.getDateCreated());
                errorResult = tblExamBusiness.update(tblExam);
                addEventLog("UPDATE", "UPDATE_TBLEXAM", errorResult.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(errorResult);
        } catch (Exception ex) {
            return null;
        }
    }
    
    @RequestMapping(value = "/getSubject.json", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String getSubject(HttpServletRequest request) {
        try {
            return new Gson().toJson(tblDivisionBusiness.getDataByDvsGroup(DivisionConstant.SUBJECT));
        } catch (Exception ex) {
            return null;
        }
    }
    
    @RequestMapping(value = "/getTime.json", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String getTime(HttpServletRequest request) {
        try {
            return new Gson().toJson(tblDivisionBusiness.getDataByDvsGroup(DivisionConstant.TIME_ID));
        } catch (Exception ex) {
            return null;
        }
    }
    
    @RequestMapping(value = "/getLevel.json", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String getLevel(HttpServletRequest request) {
        try {
            return new Gson().toJson(tblDivisionBusiness.getDataByDvsGroup(DivisionConstant.LEVEL_ID));
        } catch (Exception ex) {
            return null;
        }
    }
    
    @RequestMapping(value = "/getQuestion.json", method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String getQuestion(HttpServletRequest request) {
        JsonDataGrid dataGrid = new JsonDataGrid();
        Integer currentPage = getCurrentPage(request);
        Integer limit = getTotalRecordPerPage(request);
        if (currentPage < 1) {
            currentPage = 1;
        }
        Integer offset = (currentPage - 1) * limit;
        TblQuestionsDTO questionsDTO = new TblQuestionsDTO();
        //<editor-fold defaultstate="collapsed" desc="Các điều kiện tìm kiếm đầu vào">
        String content = request.getParameter("content");
        Long examId = Converter.converToLong(request.getParameter("examId"));
        TblExamDTO tblExamDTO = tblExamBusiness.findById(examId);
        if (tblExamDTO == null) {
            dataGrid.setData(new ArrayList<TblExamDTO>());
            dataGrid.setTotalRecords(0);
            dataGrid.setCurPage(getCurrentPage(request));
            return new Gson().toJson(dataGrid);
        }
        Integer levelId = Converter.converToInt(request.getParameter("levelId"));
        questionsDTO.setSubjectId(tblExamDTO.getSubjectId());
        questionsDTO.setContent(content);
        questionsDTO.setLevelId(levelId);
        questionsDTO.setExamId(examId);
        //</editor-fold>
        Integer totalPage = tblQuestionsBusiness.getTotalRow(questionsDTO);
        if (totalPage > 0) {
            dataGrid.setData(tblQuestionsBusiness.getAll(questionsDTO, offset, limit));
        } else {
            dataGrid.setData(new ArrayList<TblQuestionsDTO>());
        }
        dataGrid.setTotalRecords(totalPage);
        dataGrid.setCurPage(getCurrentPage(request));
        return new Gson().toJson(dataGrid);
    }
    //Lấy danh sách các câu hỏi đã được chọn

    @RequestMapping(value = "/getSelectedQuestion.json", method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String getSelectedQuestion(HttpServletRequest request) {
        JsonDataGrid dataGrid = new JsonDataGrid();
        Integer currentPage = getCurrentPage(request);
        Integer limit = getTotalRecordPerPage(request);
        if (currentPage < 1) {
            currentPage = 1;
        }
        Integer levelId = Converter.converToInt(request.getParameter("levelId"));
        String content = request.getParameter("content");
        Integer offset = (currentPage - 1) * limit;
        TblQuestionsDTO questionsDTO = new TblQuestionsDTO();
        //<editor-fold defaultstate="collapsed" desc="Các điều kiện tìm kiếm đầu vào">
        Long examId = Converter.converToLong(request.getParameter("examId"));
        questionsDTO.setExamId(examId);
        questionsDTO.setContent(content);
        questionsDTO.setLevelId(levelId);
        //</editor-fold>
        Integer totalPage = tblQuestionsBusiness.getTotalRowSelected(questionsDTO);
        List<TblQuestionsDTO> questionsDTOs = new ArrayList<>();
        if (totalPage > 0) {
            questionsDTOs = tblQuestionsBusiness.getSelectedQuestion(questionsDTO, offset, limit);
            dataGrid.setData(questionsDTOs);
        } else {
            dataGrid.setData(new ArrayList<TblQuestionsDTO>());
        }
//        for (TblQuestionsDTO item : questionsDTOs) {
//            item.setLstAnswer(tblAnswerBusiness.getAll(item.getQuestionId()));
//        }
//        ExamExport.ExportWord(questionsDTOs, tblExamBusiness.findById(examId));
        dataGrid.setTotalRecords(totalPage);
        dataGrid.setCurPage(getCurrentPage(request));
        return new Gson().toJson(dataGrid);
    }
    
    @RequestMapping(value = "/deleteExamQuestion.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String deleteExamQuestion(HttpServletRequest request) {
        try {
            Long id = Converter.converToLong(request.getParameter("id"));
            if (id == null || id == 0) {
                return null;
            }
            ErrorResult result = tblExamQuestionBusiness.delete(id);
            addEventLog("DELETE_TBLEXAMQUESTION", "EXAM_QUESTION: id=" + id, result.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
            return new Gson().toJson(result);
        } catch (Exception ex) {
            return null;
        }
    }
    
    @RequestMapping(value = {"/addQuestion.html"}, method = RequestMethod.POST,
            produces = "text/html;charset=utf-8")
    public @ResponseBody
    String addQuestion(HttpServletRequest request) throws JSONException {
        try {
            ErrorResult errorResult = new ErrorResult();
            Long examId = Converter.converToLong(request.getParameter("examId"));
            TblExamDTO examDTO = tblExamBusiness.findById(examId);
            if (examDTO == null) {
                errorResult.setError("Không tìm thấy đề thi cần thêm câu hỏi");
                errorResult.setHasError(true);
            } else {
                String questionIds = request.getParameter("questionIds");
                List<TblExamQuestion> lstExamQuestion = new ArrayList<>();
                String[] arrQuestion = questionIds.split(",");
                for (String item : arrQuestion) {
                    if (!Strings.isNullOrEmpty(item)) {
                        Long questionId = Converter.converToLong(item);
                        if (questionId > 0) {
                            TblExamQuestion examQuestion = new TblExamQuestion();
                            examQuestion.setQuestionId(questionId);
                            examQuestion.setExamId(examId);
                            lstExamQuestion.add(examQuestion);
                        }
                        
                    }
                }
                if (lstExamQuestion.isEmpty()) {
                    errorResult.setError("Bạn chưa chọn câu hỏi");
                    errorResult.setHasError(true);
                } else {
                    errorResult = tblExamQuestionBusiness.insertList(lstExamQuestion);
                }
            }
            
            return new Gson().toJson(errorResult);
        } catch (Exception ex) {
            return null;
        }
    }
    
    @RequestMapping(value = {"/deleteQuestion.html"}, method = RequestMethod.POST,
            produces = "text/html;charset=utf-8")
    public @ResponseBody
    String deleteQuestion(HttpServletRequest request) throws JSONException {
        try {
            ErrorResult errorResult = new ErrorResult();
            Long examId = Converter.converToLong(request.getParameter("examId"));
            TblExamDTO examDTO = tblExamBusiness.findById(examId);
            if (examDTO == null) {
                errorResult.setError("Không tìm thấy đề thi cần thêm câu hỏi");
                errorResult.setHasError(true);
            } else {
                String ids = request.getParameter("ids");
                String[] arrIds = ids.split(",");
                List<Long> lstId = new ArrayList<>();
                for (String item : arrIds) {
                    if (!Strings.isNullOrEmpty(item)) {
                        Long id = Converter.converToLong(item);
                        if (id > 0) {
                            lstId.add(id);
                        }
                    }
                }
                if (lstId.isEmpty()) {
                    errorResult.setError("Bạn chưa chọn câu hỏi");
                    errorResult.setHasError(true);
                } else {
                    errorResult = tblExamQuestionBusiness.delete(lstId);
                }
            }
            
            return new Gson().toJson(errorResult);
        } catch (Exception ex) {
            return null;
        }
    }
    
    @RequestMapping(value = {"/exportWord.html"}, method = RequestMethod.GET)
    public void exportWord(HttpServletRequest request, HttpServletResponse response) {
        String dataDirectory = request.getRealPath("/WEB-INF/template/Exam.docx");
        Integer changeQuestion = Converter.converToInt(request.getParameter("changeQuestion"));
        Integer changeAnswer = Converter.converToInt(request.getParameter("changeAnswer"));
        Long examId = Converter.converToLong(request.getParameter("examId"));
        Integer examNumber = Converter.converToInt(request.getParameter("examNumber"));
        String contentExport = request.getParameter("contentExport");
        TblExamDTO examDTO = tblExamBusiness.findById(examId);
        if (examDTO != null) {
            TblQuestionsDTO questionsDTO = new TblQuestionsDTO();
            questionsDTO.setExamId(examId);
            List<TblQuestionsDTO> lstQuestion = tblQuestionsBusiness.getSelectedQuestion(questionsDTO, 0, 0);
            lstQuestion.stream().forEach((item) -> {
                item.setLstAnswer(tblAnswerBusiness.getAll(item.getQuestionId()));
            });
            if (examNumber == null) {
                examNumber = 1;
            }
            String outputFolder = request.getRealPath("/WEB-INF/export/" + System.currentTimeMillis());
            File outFile = new File(outputFolder);
            if (!outFile.exists()) {
                outFile.mkdir();
            }
            for (int i = 0; i < examNumber; i++) {
                String result = ExamExport.ExportWord(lstQuestion, examDTO, contentExport,
                        dataDirectory, outputFolder + "\\" + examDTO.getName() + "_" + (i + 1) + ".docx",
                        changeQuestion == CommonConstant.ACCEPT_CHANGE, changeAnswer == CommonConstant.ACCEPT_CHANGE);
            }
            String fileName = examDTO.getName() + ".rar";
            File outputZipFile = new File(outputFolder + "\\" + examDTO.getName() + ".rar");
            File inputDir = new File(outputFolder);
            ZipDirectoryExample.zipDirectory(inputDir, outputZipFile);
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            Path file = Paths.get(outputFolder, examDTO.getName() + ".rar");
            if (Files.exists(file)) {
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    Files.copy(file, response.getOutputStream());
                    response.getOutputStream().flush();
                    inputDir.deleteOnExit();
                } catch (IOException ex) {
                    logger.error(ex);
                }
            }
        }
        
    }
    
    public static String removeHtml(String html) {
        return Jsoup.parse(html).text();
    }
    
    @RequestMapping(value = "/examList.html", method = RequestMethod.GET)
    public ModelAndView examList(Long userId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        TblExamDTO tblExamDTO = new TblExamDTO();
        List<TblDivisionDTO> lstSubject = tblDivisionBusiness.getChildById(DivisionConstant.SUBJECT);
        model.addObject("lstSubject", lstSubject);
        String keyword = request.getParameter("keyword");
        model.addObject("keyword", keyword);
        final int numberPageDisplay = 5;
        final int limitPerPage = 10;
        int currentPage = 1;
        int subject = 0;
        if (!CommonUtil.isNull(request.getParameter("page"))) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        if (!CommonUtil.isNull(request.getParameter("subject"))) {
            subject = Integer.parseInt(request.getParameter("subject"));
        }
        tblExamDTO.setSubjectId(subject);
        tblExamDTO.setName(keyword);
        int offset = CommonUtil.getOffset(limitPerPage, currentPage);
        List<TblExamDTO> lstExamDTOs = tblExamBusiness.getAll(tblExamDTO, offset, limitPerPage);
        Integer totalRecords = tblExamBusiness.getTotalRow(tblExamDTO);
        List<Integer> listPaging = CommonUtil.getListPage(limitPerPage, numberPageDisplay, totalRecords, currentPage);
        ListExamDTO listExamDTO = new ListExamDTO();
        listExamDTO.setTotalPages(CommonUtil.getTotalPage(totalRecords, numberPageDisplay));
        listExamDTO.setCurrentPage(currentPage);
        listExamDTO.setListPaging(listPaging);
        listExamDTO.setLstExamDTOs(lstExamDTOs);
        lstExamDTOs.stream().forEach((item) -> {
            if (StringUtil.isEmpty(item.getContent())) {
                item.setContent("");
            }
            item.setContent(removeHtml(item.getContent()));
        });
        model.addObject("lstItem", listExamDTO);
        model.setViewName("listExam");
        return model;
    }
}
