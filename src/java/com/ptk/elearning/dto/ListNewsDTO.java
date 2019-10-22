/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dto;

import java.util.List;

public class ListNewsDTO {

    private int totalPages;
    private int currentPage;
    private List<Integer> listPaging;
    private List<TblNewsDTO> lstNewsDTOs;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Integer> getListPaging() {
        return listPaging;
    }

    public void setListPaging(List<Integer> listPaging) {
        this.listPaging = listPaging;
    }

    public List<TblNewsDTO> getLstNewsDTOs() {
        return lstNewsDTOs;
    }

    public void setLstNewsDTOs(List<TblNewsDTO> lstNewsDTOs) {
        this.lstNewsDTOs = lstNewsDTOs;
    }

}
