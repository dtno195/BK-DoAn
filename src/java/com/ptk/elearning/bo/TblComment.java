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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "tbl_comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblComment.findAll", query = "SELECT t FROM TblComment t"),
    @NamedQuery(name = "TblComment.findByCommentId", query = "SELECT t FROM TblComment t WHERE t.commentId = :commentId"),
    @NamedQuery(name = "TblComment.findByContent", query = "SELECT t FROM TblComment t WHERE t.content = :content"),
    @NamedQuery(name = "TblComment.findByUserComment", query = "SELECT t FROM TblComment t WHERE t.userComment = :userComment"),
    @NamedQuery(name = "TblComment.findByDateComment", query = "SELECT t FROM TblComment t WHERE t.dateComment = :dateComment"),
    @NamedQuery(name = "TblComment.findByNewId", query = "SELECT t FROM TblComment t WHERE t.newId = :newId"),
    @NamedQuery(name = "TblComment.findByExamId", query = "SELECT t FROM TblComment t WHERE t.examId = :examId")})
public class TblComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "content")
    private String content;
    @Column(name = "user_comment")
    private Long userComment;
    @Column(name = "date_comment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateComment;
    @Column(name = "new_id")
    private Long newId;
    @Column(name = "exam_id")
    private Long examId;

    public TblComment() {
    }

    public TblComment(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserComment() {
        return userComment;
    }

    public void setUserComment(Long userComment) {
        this.userComment = userComment;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public Long getNewId() {
        return newId;
    }

    public void setNewId(Long newId) {
        this.newId = newId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentId != null ? commentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblComment)) {
            return false;
        }
        TblComment other = (TblComment) object;
        if ((this.commentId == null && other.commentId != null) || (this.commentId != null && !this.commentId.equals(other.commentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ptk.elearning.bo.TblComment[ commentId=" + commentId + " ]";
    }
    
}
