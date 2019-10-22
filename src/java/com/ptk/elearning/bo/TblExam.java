/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.bo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "tbl_exam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblExam.findAll", query = "SELECT t FROM TblExam t"),
    @NamedQuery(name = "TblExam.findByExamId", query = "SELECT t FROM TblExam t WHERE t.examId = :examId"),
    @NamedQuery(name = "TblExam.findByName", query = "SELECT t FROM TblExam t WHERE t.name = :name"),
    @NamedQuery(name = "TblExam.findByContent", query = "SELECT t FROM TblExam t WHERE t.content = :content"),
    @NamedQuery(name = "TblExam.findByDateCreated", query = "SELECT t FROM TblExam t WHERE t.dateCreated = :dateCreated"),
    @NamedQuery(name = "TblExam.findByTimeId", query = "SELECT t FROM TblExam t WHERE t.timeId = :timeId"),
    @NamedQuery(name = "TblExam.findBySubjectId", query = "SELECT t FROM TblExam t WHERE t.subjectId = :subjectId"),
    @NamedQuery(name = "TblExam.findByUserCreated", query = "SELECT t FROM TblExam t WHERE t.userCreated = :userCreated")})
public class TblExam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "exam_id")
    private Long examId;
    @Column(name = "name")
    private String name;
    @Column(name = "content")
    private String content;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "time_id")
    private Integer timeId;
    @Column(name = "subject_id")
    private Integer subjectId;
    @Column(name = "user_created")
    private BigInteger userCreated;

    public TblExam() {
    }

    public TblExam(Long examId) {
        this.examId = examId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public BigInteger getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(BigInteger userCreated) {
        this.userCreated = userCreated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examId != null ? examId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblExam)) {
            return false;
        }
        TblExam other = (TblExam) object;
        if ((this.examId == null && other.examId != null) || (this.examId != null && !this.examId.equals(other.examId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ptk.elearning.bo.TblExam[ examId=" + examId + " ]";
    }
    
}
