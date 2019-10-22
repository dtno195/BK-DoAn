/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.util;

import com.ptk.elearning.dto.TblAnswerDTO;
import com.ptk.elearning.dto.TblExamDTO;
import com.ptk.elearning.dto.TblQuestionsDTO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

public class ExamExport {

    private static Random random = new Random();
    private static HashMap<Integer, String> hmIndex = new HashMap<>();

    public static String ExportWord(List<TblQuestionsDTO> questionsDTOs, TblExamDTO tblExam,
            String contentExport, String template, String output, Boolean isChangeQuestion, Boolean isChangeAnswer) {
        try {
            hmIndex.put(1, "A");
            hmIndex.put(2, "B");
            hmIndex.put(3, "C");
            hmIndex.put(4, "D");
            hmIndex.put(5, "E");
            hmIndex.put(6, "F");
            hmIndex.put(7, "G");
            hmIndex.put(8, "H");
            hmIndex.put(9, "I");
            hmIndex.put(10, "J");
            hmIndex.put(11, "K");
            XWPFDocument document = new XWPFDocument(OPCPackage.open(template));
            XWPFParagraph titleGraph = document.createParagraph();
            titleGraph.setAlignment(ParagraphAlignment.CENTER);
            String title = tblExam.getName();
            XWPFRun titleRun = titleGraph.createRun();
            titleRun.setBold(true);
            titleRun.setFontSize(14);
            titleRun.setFontFamily("Times New Roman");
            titleRun.setText(title);
            titleRun.addBreak();
            int i = 0;
            if (isChangeQuestion) {
                Collections.shuffle(questionsDTOs);
            }
            for (TblQuestionsDTO questions : questionsDTOs) {
                i++;
                initQuestion(questions, document, i, isChangeAnswer);
            }
            setBookmarkText("subjectName", tblExam.getSubjectName(), document);
            setBookmarkText("timeName", tblExam.getTimeName(), document);
            FileOutputStream out = new FileOutputStream(output);
            document.write(out);
            out.close();
            return output;
        } catch (IOException ex) {
            Logger.getLogger(TaoFileWordCoParagraph.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(TaoFileWordCoParagraph.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void initQuestion(TblQuestionsDTO questionsDTO, XWPFDocument document, int stt, Boolean isChangeAns) {
        XWPFParagraph para1 = document.createParagraph();
        para1.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun para1Run = para1.createRun();
        XWPFRun para1Run2 = para1.createRun();
        para1Run.setBold(true);
        para1Run.setText("Câu " + stt + ". " + questionsDTO.getContent());
        int i = 0;
        para1Run.setFontFamily("Times New Roman");
        para1Run.setFontSize(14);
        para1Run2.setFontFamily("Times New Roman");
        para1Run2.setFontSize(14);
        if (isChangeAns) {
            Collections.shuffle(questionsDTO.getLstAnswer());
        }
        for (TblAnswerDTO answer : questionsDTO.getLstAnswer()) {
            i++;
            para1Run2.addBreak();
            para1Run2.setText(hmIndex.get(i) + ". " + answer.getContent());
        }
    }

    private static void setBookmarkText(String bookmarkId, String replaceText, XWPFDocument document) {
        for (XWPFTable tbl : document.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (CTBookmark bookmark : p.getCTP().getBookmarkStartList()) {
                            if (bookmark.getName().equals(bookmarkId)) {
                                CTP ctp = p.getCTP();
                                CTR ctr = ctp.addNewR();
                                CTText text = ctr.addNewT();
                                text.setStringValue(replaceText);
                            }
                        }
                    }
                }
            }
        }
    }

//    private static TblQuestionsDTO getRandomList(List<TblQuestionsDTO> lstQuestions) {
//        int index = random.nextInt(lstQuestions.size());
//        return lstQuestions.get(index);
//    }
}
