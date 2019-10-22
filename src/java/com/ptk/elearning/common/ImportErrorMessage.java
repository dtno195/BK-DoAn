/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.common;

import java.util.List;

public class ImportErrorMessage {

    private int row;
    private List<ValidationResult> lstError;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public List<ValidationResult> getLstError() {
        return lstError;
    }

    public void setLstError(List<ValidationResult> lstError) {
        this.lstError = lstError;
    }

}
