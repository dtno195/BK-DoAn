/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.util;

import com.ptk.elearning.bo.TblAnswer;
import com.ptk.elearning.bo.TblQuestions;
import com.ptk.elearning.dto.TblAnswerDTO;
import com.ptk.elearning.dto.TblQuestionsDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.w3c.dom.Node;

public class ImportQuestionUtil {

    static String getMathML(CTOMath ctomath, String path) throws Exception {
//        File stylesheet = new File("/WEB-INF/template/OMML2MML.XML");
        File stylesheet = new File(path);
        TransformerFactory tFactory = TransformerFactory.newInstance();
        StreamSource stylesource = new StreamSource(stylesheet);

        Transformer transformer = tFactory.newTransformer(stylesource);

        Node node = ctomath.getDomNode();

        DOMSource source = new DOMSource(node);
        StringWriter stringwriter = new StringWriter();
        StreamResult result = new StreamResult(stringwriter);
        transformer.setOutputProperty("omit-xml-declaration", "yes");
        transformer.transform(source, result);

        String mathML = stringwriter.toString();
        stringwriter.close();
        mathML = mathML.replaceAll("xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\"", "");
        mathML = mathML.replaceAll("xmlns:mml", "xmlns");
        mathML = mathML.replaceAll("mml:", "");

        return mathML;
    }

    public static List<TblQuestionsDTO> importQuestion(String filePath, String subjectId, String path) {
        List<TblQuestionsDTO> lstQuestionses = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));

            List<XWPFParagraph> paragraphList = xdoc.getParagraphs();

            String question = "";
            String answer = "";
            boolean isQuestion = false;
            boolean isAnswer = false;
            TblQuestionsDTO tblQuestions = null;
            TblAnswerDTO tblAnswer = new TblAnswerDTO();
            List<TblAnswerDTO> lsAnsewer = new ArrayList<>();
            for (XWPFParagraph paragraph : paragraphList) {
                // đọc từng dòng
                String line = paragraph.getText().trim();
                if (line.startsWith("$$")) {
                    isQuestion = true;
                    isAnswer = false;
                } else if (line.startsWith("??")) {
                    lsAnsewer = new ArrayList<>();
                    isAnswer = true;
                    isQuestion = false;
                } else if (line.startsWith("##")) {
                    isAnswer = false;
                    isQuestion = false;
                }
                System.out.println("line " + line);

                for (CTOMath ctomath : paragraph.getCTP().getOMathList()) {
                    line += getMathML(ctomath, path);
                }
                for (CTOMathPara ctomathpara : paragraph.getCTP().getOMathParaList()) {
//                    for (CTOMath ctomath : ctomathpara.getOMathList()) {
//                        line += getMathML(ctomath, path);
//                    }
                }

                if (line.startsWith("$$")) {
                    // bắt đầu câu hỏi
                    if (tblQuestions != null) {
                        tblQuestions.setContent(question);
                        tblQuestions.setLstAnswer(lsAnsewer);
                        lstQuestionses.add(tblQuestions);
                        tblQuestions = new TblQuestionsDTO();
                    }
                    tblQuestions = new TblQuestionsDTO();
                    question = line;
                } else if (line.startsWith("??") || isAnswer) {
                    // kết thúc câu hỏi
                    tblQuestions.setContent(question);
                    // bắt đầu trả lời
                    String title = line.trim().substring(0, 2).toUpperCase();
                    switch (title) {
                        case "A.":
                            answer = line;
                            tblAnswer = new TblAnswerDTO();
                            break;
                        case "B.":
                            tblAnswer.setContent(answer);
                            tblAnswer.setStt("A");
                            lsAnsewer.add(tblAnswer);
                            answer = line;
                            tblAnswer = new TblAnswerDTO();
                            break;
                        case "C.":
                            tblAnswer.setContent(answer);
                            tblAnswer.setStt("B");
                            lsAnsewer.add(tblAnswer);
                            answer = line;
                            tblAnswer = new TblAnswerDTO();
                            break;
                        case "D.":
                            tblAnswer.setContent(answer);
                            tblAnswer.setStt("C");
                            lsAnsewer.add(tblAnswer);
                            answer = line;
                            tblAnswer = new TblAnswerDTO();
                            break;
                        default:
                            answer += line;
                            break;
                    }
                } else if (line.startsWith("##")) {
                    // kết thúc câu trả lời
                    tblAnswer.setContent(answer);
                    tblAnswer.setStt("D");
                    lsAnsewer.add(tblAnswer);
                    // bắt đầu đáp án đúng
                    String correct = line.substring(2).trim();
                    for (TblAnswerDTO tblAnswerDTO : lsAnsewer) {
                        if (tblAnswerDTO.getStt().equals(correct)) {
                            tblAnswerDTO.setIsTrue(true);
                        }
                    }
                    tblQuestions.setLstAnswer(lsAnsewer);
                    // add index cau tra loi dung
                } else // noi tiep vao question
                if (isQuestion) {
                    question += " " + line;

                } else if (isAnswer) {
                    answer += line;
                }
            }
            // add cau hoi cuoi vao list
            lstQuestionses.add(tblQuestions);
        } catch (Exception ex) {
        }
        return lstQuestionses;
    }
}
