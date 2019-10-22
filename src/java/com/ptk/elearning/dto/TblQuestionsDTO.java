/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dto;

import com.ptk.elearning.bo.TblQuestions;
import java.util.Date;
import java.util.List;

public class TblQuestionsDTO {

    private Long questionId;
    private String content;
    private Integer subjectId;
    private Integer topicId;
    private Long examId;
    private Integer levelId;
    private Date dateCreated;

    private String subjectName;
    private String levelName;

    private List<TblAnswerDTO> lstAnswer;

    private Integer stt;
    private Integer row;
    private Long examQuestionId;

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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public TblQuestions toModel() {
        TblQuestions questions = new TblQuestions();
        questions.setContent(this.content);
        questions.setDateCreated(this.dateCreated);
        questions.setExamId(this.examId);
        questions.setLevelId(this.levelId);
        questions.setQuestionId(this.questionId);
        questions.setSubjectId(this.subjectId);
        questions.setTopicId(this.topicId);
        return questions;
    }

    public List<TblAnswerDTO> getLstAnswer() {
        return lstAnswer;
    }

    public void setLstAnswer(List<TblAnswerDTO> lstAnswer) {
        this.lstAnswer = lstAnswer;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Long getExamQuestionId() {
        return examQuestionId;
    }

    public void setExamQuestionId(Long examQuestionId) {
        this.examQuestionId = examQuestionId;
    }
}
