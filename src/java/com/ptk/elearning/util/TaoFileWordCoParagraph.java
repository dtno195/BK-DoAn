/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.util;

import java.io.FileOutputStream;

import java.io.IOException;

import java.util.logging.Level;

import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.Borders;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import org.apache.poi.xwpf.usermodel.XWPFRun;

public class TaoFileWordCoParagraph {

    public static void main(String[] args) {
        try {

            //Bước 1: Khởi tạo đối tượng để sinh ra file word
            XWPFDocument document = new XWPFDocument();

            //Bước 2: Tạo tiêu đề bài viết
            XWPFParagraph titleGraph = document.createParagraph();

            titleGraph.setAlignment(ParagraphAlignment.CENTER);

            String title = "TẠO BORDER CHO NỘI DUNG HIỂN THỊ";

            XWPFRun titleRun = titleGraph.createRun();

            titleRun.setBold(true);

            titleRun.setText(title);

            //Bước 3: Tạo đoạn văn bản 1 trong tài liệu:
            XWPFParagraph para1 = document.createParagraph();

            para1.setAlignment(ParagraphAlignment.LEFT);

            //Tạo border cho paragraph 1
            para1.setBorderBottom(Borders.BASIC_BLACK_DASHES);

            para1.setBorderLeft(Borders.BASIC_BLACK_DASHES);

            para1.setBorderRight(Borders.BASIC_BLACK_DASHES);

            para1.setBorderTop(Borders.BASIC_BLACK_DASHES);

            XWPFRun para1Run = para1.createRun();

            para1Run.setText("Đoạn văn bản 1");

            para1Run.addBreak();

            para1Run.setText("Nội dung của đoạn văn bản 1");

            //Bước 4: Tạo khoảng cách giữa paragraph 1 và paragraph 2
            XWPFParagraph paraBreak1 = document.createParagraph();

            XWPFRun runBreak1 = paraBreak1.createRun();

            runBreak1.addBreak();

            //Bước 5: Tạo đoạn văn bản 2:
            XWPFParagraph para2 = document.createParagraph();

            para2.setAlignment(ParagraphAlignment.LEFT);

            //Tạo border cho paragraph 2
            para2.setBorderBottom(Borders.BASIC_BLACK_DASHES);

            para2.setBorderLeft(Borders.BASIC_BLACK_DASHES);

            para2.setBorderRight(Borders.BASIC_BLACK_DASHES);

            para2.setBorderTop(Borders.BASIC_BLACK_DASHES);

            XWPFRun para2Run = para2.createRun();

            para2Run.setText("Đoạn văn bản 2");

            para2Run.addBreak();

            para2Run.setText("Nội dung của đoạn văn bản 2");

            //Bước 6: Tạo khoảng cách giữa paragraph 2 và paragraph 3
            XWPFParagraph paraBreak2 = document.createParagraph();

            XWPFRun runBreak2 = paraBreak2.createRun();

            runBreak2.addBreak();

            //Bước 7: Tạo đoạn văn bản 3:
            XWPFParagraph para3 = document.createParagraph();

            para3.setAlignment(ParagraphAlignment.LEFT);

            //Tạo border cho paragraph 3
            para3.setBorderBottom(Borders.BASIC_BLACK_DASHES);

            para3.setBorderLeft(Borders.BASIC_BLACK_DASHES);

            para3.setBorderRight(Borders.BASIC_BLACK_DASHES);

            para3.setBorderTop(Borders.BASIC_BLACK_DASHES);

            XWPFRun para3Run = para3.createRun();

            para3Run.setText("Đoạn văn bản 3");

            para3Run.addBreak();

            para3Run.setText("Nội dung của đoạn văn bản 3");

            //Bước 8: Ghi dữ liệu ra file word
            FileOutputStream out = new FileOutputStream("src/demo/data.docx");

            document.write(out);

            out.close();


        } catch (IOException ex) {

            Logger.getLogger(TaoFileWordCoParagraph.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}
