/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dto;

import com.ptk.elearning.bo.TblAnswer;

public class TblAnswerDTO {

    private Long answerId;
    private String content;
    private Long questionId;
    private Integer priority;
    private Boolean isTrue;
    private String stt;

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

   
    public Boolean getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Boolean isTrue) {
        this.isTrue = isTrue;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public TblAnswer toModel() {
        TblAnswer answer = new TblAnswer();
        answer.setAnswerId(this.answerId);
        answer.setContent(content);
        answer.setIsTrue(isTrue);
        answer.setPriority(priority);
        answer.setQuestionId(questionId);
        return answer;
    }

    
}
