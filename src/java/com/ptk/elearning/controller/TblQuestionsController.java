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
import com.ptk.elearning.bo.TblQuestions;
import com.ptk.elearning.business.TblAnswerBusiness;
import com.ptk.elearning.business.TblDivisionBusiness;
import com.ptk.elearning.business.TblQuestionsBusiness;
import com.ptk.elearning.common.CommonConstant;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.common.ImportErrorMessage;
import com.ptk.elearning.common.ValidationResult;
import com.ptk.elearning.dto.TblAnswerDTO;
import com.ptk.elearning.dto.TblQuestionsDTO;
import com.ptk.elearning.util.Converter;
import com.ptk.elearning.util.DivisionConstant;
import com.ptk.elearning.util.ImportQuestionUtil;
import com.ptk.elearning.util.JsonDataGrid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/TblQuestions")
public class TblQuestionsController extends BaseController {

    @Autowired
    private TblDivisionBusiness tblDivisionBusiness;
    @Autowired
    private TblQuestionsBusiness tblQuestionsBusiness;
    @Autowired
    private TblAnswerBusiness tblAnswerBusiness;

    @RequestMapping(value = "/Index.html", method = RequestMethod.GET)
    public ModelAndView Index(Long userId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("tbl_questions");
        model.addObject("subject", tblDivisionBusiness.getChildById(DivisionConstant.SUBJECT));
        model.addObject("level", tblDivisionBusiness.getChildById(DivisionConstant.LEVEL_ID));
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
        TblQuestionsDTO questionsDTO = new TblQuestionsDTO();
        //<editor-fold defaultstate="collapsed" desc="Các điều kiện tìm kiếm đầu vào">
        String content = URLDecoder.decode(request.getParameter("content"));
        Integer subjectId = Converter.converToInt(request.getParameter("subjectId"));
        Integer levelId = Converter.converToInt(request.getParameter("levelId"));
        Long examId = Converter.converToLong(request.getParameter("examId"));
        questionsDTO.setSubjectId(subjectId);
        questionsDTO.setExamId(examId);
        questionsDTO.setContent(content);
        questionsDTO.setLevelId(levelId);
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

    @RequestMapping(value = "/deleteQuestion.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String deleteQuestion(HttpServletRequest request) {
        try {
            Long questionId = Converter.converToLong(request.getParameter("questionId"));
            if (questionId == null || questionId == 0) {
                return null;
            }
            ErrorResult result = tblQuestionsBusiness.delete(questionId);
            addEventLog("DELETE", "DELETE_TBLQUESTION", result.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
            return new Gson().toJson(result);
        } catch (Exception ex) {
            return null;
        }
    }
    
    @RequestMapping(value = "/deleteQuestions.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String deleteQuestions(@RequestParam("ids") List<Long> ids) {
        try {
            if(!ids.isEmpty()) {
                ErrorResult result = tblQuestionsBusiness.deleteQuestions(ids);
                return new Gson().toJson(result);
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/findById.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String findById(HttpServletRequest request) {
        try {
            Long questionId = Converter.converToLong(request.getParameter("questionId"));
            if (questionId == null || questionId == 0) {
                return null;
            }
            TblQuestionsDTO questions = tblQuestionsBusiness.getDTOById(questionId);
            if (questions != null) {
                questions.setLstAnswer(tblAnswerBusiness.getAll(questionId));
            }
            return new Gson().toJson(questions);
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = {"/saveQuestion.html"}, method = RequestMethod.POST,
            produces = "text/html;charset=utf-8")
    public @ResponseBody
    String saveQuestion(@ModelAttribute("tblQuestionForm") TblQuestionsDTO questionsDTO, HttpServletRequest request) throws JSONException {
        try {
            ErrorResult errorResult = new ErrorResult();
            if (questionsDTO.getQuestionId() == null || questionsDTO.getQuestionId() == 0) {
                questionsDTO.setDateCreated(new Date());
                errorResult = tblQuestionsBusiness.insert(questionsDTO.toModel());
                if (questionsDTO.getLstAnswer() != null) {
                    List<TblAnswer> lstAnswer = new ArrayList<>();
                    for (TblAnswerDTO item : questionsDTO.getLstAnswer()) {
                        if (!Strings.isNullOrEmpty(item.getContent())) {
                            if (item.getIsTrue() == null) {
                                item.setIsTrue(false);
                            }
                            item.setQuestionId(errorResult.getId());
                            lstAnswer.add(item.toModel());
                        }
                    }
                    tblAnswerBusiness.insertList(lstAnswer);
                }
                addEventLog("INSERT", "INSERT_TBLQUESTION", errorResult.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
            } else {
                TblQuestions questions = tblQuestionsBusiness.findById(questionsDTO.getQuestionId());
                if (questions == null) {
                    errorResult.setHasError(true);
                    errorResult.setError("Không tìm thấy đề thi cần cập nhật");
                    return new Gson().toJson(errorResult);
                }
                questionsDTO.setDateCreated(questions.getDateCreated());
                errorResult = tblQuestionsBusiness.update(questionsDTO.toModel());
                if (questionsDTO.getLstAnswer() != null) {
                    List<TblAnswer> lstAnswer = new ArrayList<>();
                    for (TblAnswerDTO item : questionsDTO.getLstAnswer()) {
                        if (!Strings.isNullOrEmpty(item.getContent())) {
                            if (item.getIsTrue() == null) {
                                item.setIsTrue(false);
                            }
                            item.setAnswerId(item.getAnswerId());
                            item.setQuestionId(questionsDTO.getQuestionId());
                            lstAnswer.add(item.toModel());
                        }
                    }
                    tblAnswerBusiness.updateList(lstAnswer);
                }
                addEventLog("UPDATE", "INSERT_TBLQUESTION", errorResult.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
            }
            return new Gson().toJson(errorResult);
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/deleteAnswer.json", method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String deleteAnswer(HttpServletRequest request) {
        try {
            Long answerId = Converter.converToLong(request.getParameter("answerId"));
            if (answerId == null || answerId == 0) {
                return null;
            }
            ErrorResult result = tblAnswerBusiness.delete(answerId);
            addEventLog("DELETE", "DELETE_TBLANSWER", result.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
            return new Gson().toJson(result);
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/downloadTemplate.html", method = RequestMethod.GET)
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) {
        String dataDirectory = request.getRealPath("/WEB-INF/template/");
        String fileName = "";
        if (!Strings.isNullOrEmpty(request.getParameter("type"))) {
            Integer type = Converter.converToInt(request.getParameter("type"));
            if (type != null) {
                //<editor-fold defaultstate="collapsed" desc="Set template">
                switch (type) {
                    case 1:
                        //Toa nha xls
                        fileName = "TblQuestion.xls";
                        break;
                    case 2:
                        //Toa nha xlsx
                        fileName = "TblQuestion.xlsx";
                        break;
                    default:
                        fileName = "Unknow.xlsx";
                        break;
                }
//</editor-fold>
                response.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                response.setHeader("Content-Transfer-Encoding", "binary");
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                Path file = Paths.get(dataDirectory, fileName);
                if (Files.exists(file)) {
                    response.setContentType("application/vnd.ms-excel");
                    response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                    try {
                        Files.copy(file, response.getOutputStream());
                        response.getOutputStream().flush();
                    } catch (IOException ex) {
                        logger.error(ex);
                    }
                }
            }
        }
    }

    @RequestMapping(value = {"/importExcel.html"}, method = RequestMethod.POST)
    @ResponseBody
    public String importExcel(HttpServletRequest request, @RequestParam("files") MultipartFile file) throws IOException {
        String path = request.getRealPath("/WEB-INF/template/") + "OMML2MML.XML";
        Integer subjectId = Converter.converToInt(request.getParameter("subjectId"));
        int totalSuccess = 0;
        int totalFail = 0;
        List<ImportErrorMessage> lstImportErrorMessages = new ArrayList<>();
        if (file != null && !Strings.isNullOrEmpty(file.getOriginalFilename()) && (file.getOriginalFilename().toLowerCase().endsWith(".doc")
                || file.getOriginalFilename().toLowerCase().endsWith(".docx"))
                && file.getSize() <= 20480000) {
            File newFiles = new File(request.getRealPath("/WEB-INF/import/"), file.getOriginalFilename());
            if (!newFiles.exists()) {
                newFiles.createNewFile();
                file.transferTo(newFiles);
            }
            List<TblQuestionsDTO> data = ImportQuestionUtil.importQuestion(newFiles.getPath(), "aaaaaaaaa", path);
            for (TblQuestionsDTO tblQuestionsDTO : data) {
                ImportErrorMessage errorMessage = new ImportErrorMessage();
                HashMap<String, String> hmError = new HashMap<>();
                List<ValidationResult> error = validateForm(tblQuestionsDTO, hmError);
                tblQuestionsDTO.setContent(tblQuestionsDTO.getContent().replace("$$", ""));
                tblQuestionsDTO.setSubjectId(subjectId);
                tblQuestionsDTO.setDateCreated(new Date());
                tblQuestionsDTO.setLevelId(1);
                ErrorResult serviceResult = tblQuestionsBusiness.insert(tblQuestionsDTO.toModel());
                if (!serviceResult.isHasError()) {
                    totalSuccess++;
                    tblQuestionsDTO.getLstAnswer().stream().forEach((item) -> {
                        item.setQuestionId(serviceResult.getId());
                    });
                    List<TblAnswer> lstAnswers = tblQuestionsDTO.getLstAnswer().stream().map(r -> {
                        return r.toModel();
                    }).collect(Collectors.toList());
                    tblAnswerBusiness.insertList(lstAnswers);
                    addEventLog("ADD_TBL_QUESTION", "QUESTION name=" + tblQuestionsDTO.getContent(), serviceResult.isHasError() ? CommonConstant.EVENT_FAIL : CommonConstant.EVENT_SUCCESS, request);
                } else {
                    totalFail++;
                    ValidationResult validationResult = new ValidationResult();
                    validationResult.setError((serviceResult.getError()));
                    error.add(validationResult);
                    errorMessage.setLstError(error);
                    lstImportErrorMessages.add(errorMessage);
                }
            }
            newFiles.delete();

            JsonObject jsObj = new JsonObject();
            jsObj.addProperty("totalSuccess", String.valueOf(totalSuccess));
            jsObj.addProperty("totalFail", String.valueOf(totalFail));
            jsObj.addProperty("rdList", new Gson().toJson(lstImportErrorMessages));

            return jsObj.toString();
        }
        return null;
    }

    private String readWaterPointsFromExcelFile(String pathFile, boolean isXls, HttpServletRequest request) {
        int totalSuccess = 0;
        int totalFail = 0;
        List<ImportErrorMessage> lstImportErrorMessages = new ArrayList<>();
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
                                }
                                break;
                            default:
                                //Cac truong hop con lai
                                break;
                        }
//</editor-fold>
                    }
//                    tblQuestionsDTO.setRow(rowIndex + 1);

                }
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
        JsonObject jsObj = new JsonObject();
        jsObj.addProperty("totalSuccess", String.valueOf(totalSuccess));
        jsObj.addProperty("totalFail", String.valueOf(totalFail));
        jsObj.addProperty("rdList", new Gson().toJson(lstImportErrorMessages));

        return jsObj.toString();
    }

    private List<ValidationResult> validateForm(TblQuestionsDTO questionsDTO, HashMap<String, String> hmError) {
        return null;
    }
}
