/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.bo;

import java.io.Serializable;
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
@Table(name = "tbl_questions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblQuestions.findAll", query = "SELECT t FROM TblQuestions t"),
    @NamedQuery(name = "TblQuestions.findByQuestionId", query = "SELECT t FROM TblQuestions t WHERE t.questionId = :questionId"),
    @NamedQuery(name = "TblQuestions.findByContent", query = "SELECT t FROM TblQuestions t WHERE t.content = :content"),
    @NamedQuery(name = "TblQuestions.findBySubjectId", query = "SELECT t FROM TblQuestions t WHERE t.subjectId = :subjectId"),
    @NamedQuery(name = "TblQuestions.findByTopicId", query = "SELECT t FROM TblQuestions t WHERE t.topicId = :topicId"),
    @NamedQuery(name = "TblQuestions.findByExamId", query = "SELECT t FROM TblQuestions t WHERE t.examId = :examId"),
    @NamedQuery(name = "TblQuestions.findByLevelId", query = "SELECT t FROM TblQuestions t WHERE t.levelId = :levelId"),
    @NamedQuery(name = "TblQuestions.findByDateCreated", query = "SELECT t FROM TblQuestions t WHERE t.dateCreated = :dateCreated")})
public class TblQuestions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "question_id")
    private Long questionId;
    @Column(name = "content")
    private String content;
    @Column(name = "subject_id")
    private Integer subjectId;
    @Column(name = "topic_id")
    private Integer topicId;
    @Column(name = "exam_id")
    private Long examId;
    @Column(name = "level_id")
    private Integer levelId;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public TblQuestions() {
    }

    public TblQuestions(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblQuestions)) {
            return false;
        }
        TblQuestions other = (TblQuestions) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ptk.elearning.bo.TblQuestions[ questionId=" + questionId + " ]";
    }
    
}
