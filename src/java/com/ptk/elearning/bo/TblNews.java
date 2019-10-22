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
@Table(name = "tbl_news")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNews.findAll", query = "SELECT t FROM TblNews t"),
    @NamedQuery(name = "TblNews.findByNewId", query = "SELECT t FROM TblNews t WHERE t.newId = :newId"),
    @NamedQuery(name = "TblNews.findByTitle", query = "SELECT t FROM TblNews t WHERE t.title = :title"),
    @NamedQuery(name = "TblNews.findByContent", query = "SELECT t FROM TblNews t WHERE t.content = :content"),
    @NamedQuery(name = "TblNews.findByUserCreated", query = "SELECT t FROM TblNews t WHERE t.userCreated = :userCreated"),
    @NamedQuery(name = "TblNews.findByDateCreated", query = "SELECT t FROM TblNews t WHERE t.dateCreated = :dateCreated"),
    @NamedQuery(name = "TblNews.findByTopicId", query = "SELECT t FROM TblNews t WHERE t.topicId = :topicId")})
public class TblNews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "new_id")
    private Long newId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "user_created")
    private Long userCreated;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "topic_id")
    private Long topicId;

    public TblNews() {
    }

    public TblNews(Long newId) {
        this.newId = newId;
    }

    public Long getNewId() {
        return newId;
    }

    public void setNewId(Long newId) {
        this.newId = newId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Long userCreated) {
        this.userCreated = userCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (newId != null ? newId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNews)) {
            return false;
        }
        TblNews other = (TblNews) object;
        if ((this.newId == null && other.newId != null) || (this.newId != null && !this.newId.equals(other.newId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ptk.elearning.bo.TblNews[ newId=" + newId + " ]";
    }
    
}
