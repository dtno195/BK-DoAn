/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dto;

import com.ptk.elearning.common.ImportErrorMessage;
import java.util.List;

public class ImportResult {

    private List<Long> listIds;
    private List<ImportErrorMessage> rdList;
    private int totalSuccess;
    private int totalFail;

    public List<Long> getListIds() {
        return listIds;
    }

    public void setListIds(List<Long> listIds) {
        this.listIds = listIds;
    }

    public List<ImportErrorMessage> getRdList() {
        return rdList;
    }

    public void setRdList(List<ImportErrorMessage> rdList) {
        this.rdList = rdList;
    }

    public int getTotalSuccess() {
        return totalSuccess;
    }

    public void setTotalSuccess(int totalSuccess) {
        this.totalSuccess = totalSuccess;
    }

    public int getTotalFail() {
        return totalFail;
    }

    public void setTotalFail(int totalFail) {
        this.totalFail = totalFail;
    }

}
