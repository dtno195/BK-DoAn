/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.bo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "tbl_division")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDivision.findAll", query = "SELECT t FROM TblDivision t"),
    @NamedQuery(name = "TblDivision.findByDvsId", query = "SELECT t FROM TblDivision t WHERE t.dvsId = :dvsId"),
    @NamedQuery(name = "TblDivision.findByDvsName", query = "SELECT t FROM TblDivision t WHERE t.dvsName = :dvsName"),
    @NamedQuery(name = "TblDivision.findByDvsGroup", query = "SELECT t FROM TblDivision t WHERE t.dvsGroup = :dvsGroup"),
    @NamedQuery(name = "TblDivision.findByDvsOrder", query = "SELECT t FROM TblDivision t WHERE t.dvsOrder = :dvsOrder"),
    @NamedQuery(name = "TblDivision.findByDvsValue", query = "SELECT t FROM TblDivision t WHERE t.dvsValue = :dvsValue"),
    @NamedQuery(name = "TblDivision.findByDescription", query = "SELECT t FROM TblDivision t WHERE t.description = :description"),
    @NamedQuery(name = "TblDivision.findByUserCreated", query = "SELECT t FROM TblDivision t WHERE t.userCreated = :userCreated")})
public class TblDivision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dvs_id")
    private Long dvsId;
    @Column(name = "dvs_name")
    private String dvsName;
    @Column(name = "dvs_group")
    private String dvsGroup;
    @Column(name = "dvs_order")
    private Integer dvsOrder;
    @Column(name = "dvs_value")
    private Integer dvsValue;
    @Column(name = "description")
    private String description;
    @Column(name = "user_created")
    private Long userCreated;
    @Column(name = "dvs_parent")
    private Integer dvsParent;

    public TblDivision() {
    }

    public TblDivision(Long dvsId) {
        this.dvsId = dvsId;
    }

    public Long getDvsId() {
        return dvsId;
    }

    public void setDvsId(Long dvsId) {
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

}
