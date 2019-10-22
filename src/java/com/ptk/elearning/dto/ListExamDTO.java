/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dto;

import java.util.List;

public class ListExamDTO {

    private int totalPages;
    private int currentPage;
    private List<Integer> listPaging;
    private List<TblExamDTO> lstExamDTOs;
    private List<ExamResultDTO> lstExamResultDTOs;

    public List<ExamResultDTO> getLstExamResultDTOs() {
        return lstExamResultDTOs;
    }

    public void setLstExamResultDTOs(List<ExamResultDTO> lstExamResultDTOs) {
        this.lstExamResultDTOs = lstExamResultDTOs;
    }

    public List<Integer> getListPaging() {
        return listPaging;
    }

    public void setListPaging(List<Integer> listPaging) {
        this.listPaging = listPaging;
    }

    public List<TblExamDTO> getLstExamDTOs() {
        return lstExamDTOs;
    }

    public void setLstExamDTOs(List<TblExamDTO> lstExamDTOs) {
        this.lstExamDTOs = lstExamDTOs;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
