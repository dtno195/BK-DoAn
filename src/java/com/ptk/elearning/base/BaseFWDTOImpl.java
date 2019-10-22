/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.base;

import java.io.Serializable;

public abstract class BaseFWDTOImpl<TBO extends BaseFWModelImpl> implements BaseFWDTO<TBO>, Serializable {

    protected String defaultSortField = "name";

    public String getDefaultSortField() {
        return defaultSortField;
    }

    public void setDefaultSortField(String defaultSortField) {
        this.defaultSortField = defaultSortField;
    }

    @Override
    public int compareTo(BaseFWDTO o) {
        return catchName().compareTo(o.catchName());
    }

}
