/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dto;

public class ResultOfExam {

    private Long answerId;
    private Long questionId;
    private String questionContent;
    private String answerContent;
    private String answerCorrectContent;
    private int flagIsRight;

    public String getAnswerCorrectContent() {
        return answerCorrectContent;
    }

    public void setAnswerCorrectContent(String answerCorrectContent) {
        this.answerCorrectContent = answerCorrectContent;
    }

    public int getFlagIsRight() {
        return flagIsRight;
    }

    public void setFlagIsRight(int flagIsRight) {
        this.flagIsRight = flagIsRight;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

}
