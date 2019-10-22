/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dto;

import com.ptk.elearning.bo.TblTopic;

public class TblTopicDTO {

    private Long id;
    private String name;
    private Long subjectId;
    private String subjectName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public TblTopic toModel() {
        TblTopic tblTopic = new TblTopic();
        tblTopic.setId(id);
        tblTopic.setName(name);
        tblTopic.setSubjectId(subjectId);
        return tblTopic;
    }
}
