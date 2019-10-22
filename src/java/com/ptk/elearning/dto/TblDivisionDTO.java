/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dto;

import java.util.List;

public class TblDivisionDTO {

    private Integer dvsId;
    private Integer dvsParent;
    private String dvsName;
    private String dvsGroup;
    private Integer dvsOrder;
    private Integer dvsValue;
    private String description;
    private Long userCreated;
    private String dvsParentName;
    private List<TblDivisionDTO> lstChild;

    public Integer getDvsId() {
        return dvsId;
    }

    public void setDvsId(Integer dvsId) {
        this.dvsId = dvsId;
    }

    public String getDvsName() {
        return dvsName;
    }

    public void setDvsName(String dvsName) {
        this.dvsName = dvsName;
    }

    public String getDvsGroup() {
        return dvsGroup;
    }

    public void setDvsGroup(String dvsGroup) {
        this.dvsGroup = dvsGroup;
    }

    public Integer getDvsOrder() {
        return dvsOrder;
    }

    public void setDvsOrder(Integer dvsOrder) {
        this.dvsOrder = dvsOrder;
    }

    public Integer getDvsValue() {
        return dvsValue;
    }

    public void setDvsValue(Integer dvsValue) {
        this.dvsValue = dvsValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Long userCreated) {
        this.userCreated = userCreated;
    }

    public Integer getDvsParent() {
        return dvsParent;
    }

    public void setDvsParent(Integer dvsParent) {
        this.dvsParent = dvsParent;
    }

    public String getDvsParentName() {
        return dvsParentName;
    }

    public void setDvsParentName(String dvsParentName) {
        this.dvsParentName = dvsParentName;
    }

    public List<TblDivisionDTO> getLstChild() {
        return lstChild;
    }

    public void setLstChild(List<TblDivisionDTO> lstChild) {
        this.lstChild = lstChild;
    }

}
