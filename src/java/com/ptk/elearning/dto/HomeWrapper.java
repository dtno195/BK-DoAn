/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dto;

import java.util.List;

public class HomeWrapper {

    public String subjectName;
    public Integer subjectId;
    public List<TblTopicDTO> lstTopic;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public List<TblTopicDTO> getLstTopic() {
        return lstTopic;
    }

    public void setLstTopic(List<TblTopicDTO> lstTopic) {
        this.lstTopic = lstTopic;
    }

}
