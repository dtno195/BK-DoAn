/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.common;

import java.util.ArrayList;
import java.util.List;


public class CommonUtil {

    public static boolean isNull(String val) {
        if (val == null || "".equals(val)) {
            return true;
        }
        return false;
    }
    
    public static List<Integer> getListPage(int limitPerPage, int numberPageDisplay, int totalRecords, int currentPage) {
        List<Integer> result = new ArrayList<Integer>();
        // get total page
        int totalPages = getTotalPage(totalRecords, limitPerPage);
        if (totalRecords == 0 || totalPages == 1) {
            return result;
        }
        if (currentPage > totalPages) {
            currentPage = 1;
        }
        // get first -> current page
        if (currentPage < numberPageDisplay) {
            for (int i = 1; i <= currentPage; i++) {
                result.add(i);
            }
        } else {
            result.add(1);
            result.add(0);
            result.add(currentPage - 2);
            result.add(currentPage - 1);
            result.add(currentPage);
        }
        // get current -> last page
        if (currentPage + 4 > totalPages) {
            for (int i = currentPage + 1; i <= totalPages; i++) {
                result.add(i);
            }   
        } else {
            result.add(currentPage + 1);
            result.add(currentPage + 2);
            result.add(0);
            result.add(totalPages);
        }
        return result;
    }

    public static int getTotalPage(int totalRecords, int limitPerPage) {
        int numberPage = totalRecords / limitPerPage;
        if (totalRecords % limitPerPage != 0) {
            numberPage++;
        }
        return numberPage;
    }

    public static int getOffset(int limitPerPage, int currentPage) {
        int offset = 0;
        if (currentPage > 0) {
            offset = (currentPage - 1) * limitPerPage;
        }
        return offset;
    }

}
