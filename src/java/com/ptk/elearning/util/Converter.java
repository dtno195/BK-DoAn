/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.jsoup.Jsoup;

public class Converter {

    public static Integer converToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static Long converToLong(String input) {
        try {
            return Long.parseLong(input);
        } catch (Exception ex) {
            return 0L;
        }
    }

    public static String getDataFromCell(DataFormatter formatter, Cell cell) {
        String data = formatter.formatCellValue(cell).trim();
        return data.length() == 0 ? null : data;
    }

    public static Integer converToIntOrNull(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date convertFullDate(String date, SimpleDateFormat formatter) {
        try {
            return formatter.parse(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date convertDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String stripHtml(String value) {
        return Jsoup.parse(value).text();
    }

}
