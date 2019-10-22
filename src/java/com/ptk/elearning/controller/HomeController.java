/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.controller;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ptk.elearning.bo.TblAnswer;
import com.ptk.elearning.bo.TblDivision;
import com.ptk.elearning.bo.TblExam;
import com.ptk.elearning.bo.TblExamQuestion;
import com.ptk.elearning.business.ExamResultBusiness;
import com.ptk.elearning.business.TblAnswerBusiness;
import com.ptk.elearning.business.TblDivisionBusiness;
import com.ptk.elearning.business.TblExamBusiness;
import com.ptk.elearning.business.TblExamQuestionBusiness;
import com.ptk.elearning.business.TblExamQuestionBusinessImpl;
import com.ptk.elearning.business.TblNewsBusiness;
import com.ptk.elearning.business.TblQuestionsBusiness;
import com.ptk.elearning.business.TblTopicBusiness;
import com.ptk.elearning.chart.GetChartData;
import com.ptk.elearning.common.CommonConstant;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.common.ImportErrorMessage;
import com.ptk.elearning.common.ValidationResult;
import com.ptk.elearning.dto.HomeWrapper;
import com.ptk.elearning.dto.ImportResult;
import com.ptk.elearning.dto.TblDivisionDTO;
import com.ptk.elearning.dto.TblExamDTO;
import com.ptk.elearning.dto.TblNewsDTO;
import com.ptk.elearning.dto.TblQuestionsDTO;
import com.ptk.elearning.util.Converter;
import com.ptk.elearning.util.DivisionConstant;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController extends BaseController {

    @Autowired
    private ExamResultBusiness examResultBusiness;
    @Autowired
    private TblDivisionBusiness tblDivisionBusiness;
    @Autowired
    private TblAnswerBusiness tblAnswerBusiness;

    @Autowired
    private TblQuestionsBusiness tblQuestionsBusiness;
    @Autowired
    private TblExamQuestionBusiness tblExamQuestionBusiness;
    @Autowired
    private TblExamBusiness tblExamBusiness;
    @Autowired
    private TblTopicBusiness tblTopicBusiness;
    @Autowired
    private TblNewsBusiness tblNewsBusiness;

    @RequestMapping(value = {"/index.html"}, method = RequestMethod.GET)
    public ModelAndView index(Model model, HttpServletRequest request) {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"/examTest.html"}, method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        String id = request.getParameter("id");
        ModelAndView model = new ModelAndView();
        TblExamDTO examDTO = tblExamBusiness.findById(Converter.converToLong(id));
        if (examDTO == null) {
            model.addObject("subjects", tblDivisionBusiness.getChildById(DivisionConstant.SUBJECT));
            model.addObject("exams", tblExamBusiness.getAll(new TblExamDTO(), 0, 15));
            return model;
        }
        model.addObject("exam", examDTO);
        List<TblQuestionsDTO> questionsDTOs = tblQuestionsBusiness.getDataByExamId(examDTO.getExamId());
        questionsDTOs.stream().forEach((item) -> {
            item.setLstAnswer(tblAnswerBusiness.getAll(item.getQuestionId()));
        });
        model.addObject("questions", questionsDTOs);
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = {"/dashboard.html"}, method = RequestMethod.GET)
    public ModelAndView dashboard(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        model.addObject("subjects", tblDivisionBusiness.getChildById(DivisionConstant.SUBJECT));
        model.addObject("exams", tblExamBusiness.getAll(new TblExamDTO(), 0, 15));
        model.setViewName("dashboard");

        // get list mon hoc
        List<TblDivisionDTO> lstSubject = tblDivisionBusiness.getChildById(DivisionConstant.SUBJECT);
        List<TblDivisionDTO> lstTime = tblDivisionBusiness.getChildById(DivisionConstant.TIME_ID);
        model.addObject("lstSubject", lstSubject);
        model.addObject("lstTime", lstTime);
        // get name by id

        // ve chart
        int year = 2018;
        int month = 0;
        int subject = 3;
        int userId = 0;
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
        return model;
    }

    @RequestMapping(value = {"/exam.html"}, method = RequestMethod.GET)
    public ModelAndView exam(Model model, HttpServletRequest request) {
        return new ModelAndView("exam");
    }

    @RequestMapping(value = {"/SetLeftSide.html"}, method = RequestMethod.GET)
    public void setLeftSide(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        List<HomeWrapper> lstParent = tblTopicBusiness.getAllSubjectAndTopic();
        TblNewsDTO tblNewsDTO = new TblNewsDTO();
        List<TblNewsDTO> lstParent1 = tblNewsBusiness.getAll(tblNewsDTO, 0, 0);
        lstParent1.stream().forEach((item) -> {
            item.setContent(removeHtml(item.getContent()));
        });
        session.setAttribute("rightSide", lstParent1);
        session.setAttribute("leftSide", lstParent);
        response.sendRedirect(request.getParameter("page"));
    }

    public static String removeHtml(String html) {
        return Jsoup.parse(html).text();
    }
    @RequestMapping(value = {"/importExam.html"}, method = RequestMethod.POST)
    @ResponseBody
    public String importExcel(@ModelAttribute("tblExamDTO") TblExam tblExamDTO, MultipartHttpServletRequest request) throws IOException, JSONException {
        MultipartFile file = request.getFile("files");
        if (file != null && !Strings.isNullOrEmpty(file.getOriginalFilename()) && (file.getOriginalFilename().toLowerCase().endsWith(".xls")
                || file.getOriginalFilename().toLowerCase().endsWith(".xlsx"))
                && file.getSize() <= 20480000) {
            Calendar cal = Calendar.getInstance();
            File newFiles = new File(request.getRealPath("/WEB-INF/import/"), file.getOriginalFilename() + cal.getTimeInMillis());
            if (!newFiles.exists()) {
                newFiles.createNewFile();
                file.transferTo(newFiles);
            }
            boolean isXls = file.getOriginalFilename().toLowerCase().endsWith("xls");
            Long examId = tblExamBusiness.insertReturnId(tblExamDTO);
            ImportResult data = readQuestionFromExcelFile(newFiles.getAbsolutePath(), isXls, request);
            List<Long> lstIds = data.getListIds();
            for (Long lstId : lstIds) {
                TblExamQuestion tblExamQuestion = new TblExamQuestion();
                tblExamQuestion.setExamId(examId);
                tblExamQuestion.setId(lstId);
                tblExamQuestionBusiness.insert(tblExamQuestion);
            }
            JsonObject jsObj = new JsonObject();
            jsObj.addProperty("totalSuccess", String.valueOf(data.getTotalSuccess()));
            jsObj.addProperty("totalFail", String.valueOf(data.getTotalFail()));
            jsObj.addProperty("rdList", new Gson().toJson(data.getRdList()));
            newFiles.delete();
            return data.toString();
        }
        return null;
    }
     private ImportResult readQuestionFromExcelFile(String pathFile, boolean isXls, HttpServletRequest request) {
        int totalSuccess = 0;
        int totalFail = 0;
        List<ImportErrorMessage> lstImportErrorMessages = new ArrayList<>();
        List<Long> listIds = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(new File(pathFile));
            Workbook workbook;
            Sheet worksheet;
            if (isXls) {
                //read xls
                workbook = new HSSFWorkbook(inputStream);
                worksheet = workbook.getSheetAt(0);
            } else {
                //read xlsx
                workbook = new XSSFWorkbook(inputStream);
                worksheet = workbook.getSheetAt(0);
            }
            Iterator<Row> iterator = worksheet.iterator();
            DataFormatter formatter = new DataFormatter();
            TblQuestionsDTO tblQuestionsDTO = null;
            List<TblAnswer> lstAnswer = new ArrayList<>();
            Integer dvsValue = null;
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                int rowIndex = nextRow.getRowNum();
                if (rowIndex >= 6) {
                    TblAnswer answer = null;
                    ImportErrorMessage errorMessage = new ImportErrorMessage();
                    HashMap<String, String> hmError = new HashMap<>();
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    TblDivision division;
                    while (cellIterator.hasNext()) {
                        Cell nextCell = cellIterator.next();
                        int columnIndex = nextCell.getColumnIndex();
                        //<editor-fold defaultstate="collapsed" desc="Mapping giá trị của các cột">
                        String valueTemp = Converter.getDataFromCell(formatter, worksheet.getRow(rowIndex).getCell(columnIndex));
                        switch (columnIndex) {
                            case 1:
                                //STT
                                if (tblQuestionsDTO == null) {
                                    tblQuestionsDTO = new TblQuestionsDTO();
                                    tblQuestionsDTO.setStt(Converter.converToInt(valueTemp));
                                }
                            case 2:
                                //câu hỏi
                                if (!Strings.isNullOrEmpty(valueTemp)) {
                                    //<editor-fold defaultstate="collapsed" desc="Thêm câu trả lời + câu hỏi">
                                    if (!lstAnswer.isEmpty()) {
                                        List<ValidationResult> error = validateForm(tblQuestionsDTO, hmError);
                                        if (error == null || error.isEmpty()) {
                                            tblQuestionsDTO.setDateCreated(new Date());
                                            ErrorResult serviceResult = tblQuestionsBusiness.insert(tblQuestionsDTO.toModel());
                                            if (!serviceResult.isHasError()) {
                                                totalSuccess++;
                                                lstAnswer.stream().forEach((item) -> {
                                                    item.setQuestionId(serviceResult.getId());
                                                });
                                                tblAnswerBusiness.insertList(lstAnswer);
                                                addEventLog("ADD_TBL_QUESTION", "QUESTION name=" + tblQuestionsDTO.getContent(), serviceResult.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
                                            } else {
                                                totalFail++;
                                                ValidationResult validationResult = new ValidationResult();
                                                validationResult.setError((serviceResult.getError()));
                                                error.add(validationResult);
                                                errorMessage.setRow(rowIndex + 1);
                                                errorMessage.setLstError(error);
                                                lstImportErrorMessages.add(errorMessage);
                                            }
                                        } else {
                                            totalFail++;
                                            errorMessage.setRow(rowIndex + 1);
                                            errorMessage.setLstError(error);
                                            lstImportErrorMessages.add(errorMessage);
                                        }
                                    }
//</editor-fold>
                                    lstAnswer.clear();
                                    tblQuestionsDTO = new TblQuestionsDTO();
                                    tblQuestionsDTO.setContent(valueTemp);
                                }
                                break;
                            case 3:
                                //Môn học
                                if (!Strings.isNullOrEmpty(valueTemp)) {
                                    dvsValue = Converter.converToIntOrNull(valueTemp);
                                    if (dvsValue == null) {
                                        division = tblDivisionBusiness.findByName(valueTemp, DivisionConstant.SUBJECT);
                                    } else {
                                        division = tblDivisionBusiness.findByValue(dvsValue, DivisionConstant.SUBJECT);
                                    }
                                    if (division == null) {
                                        hmError.put("subjectId", "Môn học " + valueTemp + "không tồn tại");
                                    } else if (tblQuestionsDTO != null) {
                                        tblQuestionsDTO.setSubjectId(division.getDvsValue());
                                    }
                                } else if (lstAnswer.isEmpty()) {
                                    hmError.put("subjectId", "Bạn chưa nhập môn học");
                                }
                                break;
                            case 4:
                                //Độ khó
                                if (!Strings.isNullOrEmpty(valueTemp)) {
                                    dvsValue = Converter.converToIntOrNull(valueTemp);
                                    if (dvsValue == null) {
                                        division = tblDivisionBusiness.findByName(valueTemp, DivisionConstant.LEVEL_ID);
                                    } else {
                                        division = tblDivisionBusiness.findByValue(dvsValue, DivisionConstant.LEVEL_ID);
                                    }
                                    if (division == null) {
                                        hmError.put("levelId", "Độ khó " + valueTemp + "không tồn tại");
                                    } else if (tblQuestionsDTO != null) {
                                        tblQuestionsDTO.setLevelId(division.getDvsValue());
                                    }
                                } else if (lstAnswer.isEmpty()) {
                                    hmError.put("levelId", "Bạn chưa nhập độ khó");
                                }
                                break;
                            case 5:
                                //Câu trả lời
                                if (!Strings.isNullOrEmpty(valueTemp)) {
                                    answer = new TblAnswer();
                                    answer.setContent(valueTemp);
                                }
                                break;
                            case 6:
                                //Đáp án
                                if (!Strings.isNullOrEmpty(valueTemp) && answer != null) {
                                    answer.setIsTrue("x".equalsIgnoreCase(valueTemp));
                                    lstAnswer.add(answer);
                                } else if (answer != null) {
                                    lstAnswer.add(answer);
                                }
                                if (!iterator.hasNext()) {
                                    //<editor-fold defaultstate="collapsed" desc="Thêm câu trả lời + câu hỏi">
                                    if (!lstAnswer.isEmpty()) {
                                        List<ValidationResult> error = validateForm(tblQuestionsDTO, hmError);
                                        if (error == null || error.isEmpty()) {
                                            tblQuestionsDTO.setDateCreated(new Date());
                                            ErrorResult serviceResult = tblQuestionsBusiness.insert(tblQuestionsDTO.toModel());
                                            if (!serviceResult.isHasError()) {
                                                totalSuccess++;
                                                lstAnswer.stream().forEach((item) -> {
                                                    item.setQuestionId(serviceResult.getId());
                                                });
                                                ErrorResult errorResult = tblAnswerBusiness.insertListReturnId(lstAnswer);
                                                listIds = errorResult.getLstId();
                                                addEventLog("ADD_TBL_QUESTION", "QUESTION name=" + tblQuestionsDTO.getContent(), serviceResult.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
                                            } else {
                                                totalFail++;
                                                ValidationResult validationResult = new ValidationResult();
                                                validationResult.setError((serviceResult.getError()));
                                                error.add(validationResult);
                                                errorMessage.setRow(rowIndex + 1);
                                                errorMessage.setLstError(error);
                                                lstImportErrorMessages.add(errorMessage);
                                            }
                                        } else {
                                            totalFail++;
                                            errorMessage.setRow(rowIndex + 1);
                                            errorMessage.setLstError(error);
                                            lstImportErrorMessages.add(errorMessage);
                                        }
                                    }
//</editor-fold>
                                }
                                break;
                            default:
                                //Cac truong hop con lai
                                break;
                        }
//</editor-fold>
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
        ImportResult importResult = new ImportResult();
        importResult.setListIds(listIds);
        importResult.setRdList(lstImportErrorMessages);
        importResult.setTotalFail(totalFail);
        importResult.setTotalSuccess(totalSuccess);
        return importResult;
    }
    private List<ValidationResult> validateForm(TblQuestionsDTO questionsDTO, HashMap<String, String> hmError) {
      return null;
    }
}

    
