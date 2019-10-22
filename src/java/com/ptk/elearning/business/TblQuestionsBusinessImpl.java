/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblQuestions;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dao.TblQuestionsDAO;
import com.ptk.elearning.dto.ResultOfExam;
import com.ptk.elearning.dto.TblQuestionsDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tblQuestionsBusiness")
public class TblQuestionsBusinessImpl implements TblQuestionsBusiness {

    @Autowired
    private TblQuestionsDAO tblQuestionsDAO;

    @Override
    public TblQuestions findById(Long questionId) {
        return tblQuestionsDAO.findById(questionId);
    }

    @Override
    public List<TblQuestionsDTO> getAll(TblQuestionsDTO questionsDTO, Integer offset, Integer limit) {
        return tblQuestionsDAO.getAll(questionsDTO, offset, limit);
    }

    @Override
    public ErrorResult insert(TblQuestions questions) {
        return tblQuestionsDAO.save(questions);
    }

    @Override
    public ErrorResult update(TblQuestions questions) {
        return tblQuestionsDAO.update(questions);
    }

    @Override
    public ErrorResult delete(long questionId) {
        return tblQuestionsDAO.delete(questionId);
    }

    @Override
    public Integer getTotalRow(TblQuestionsDTO questionsDTO) {
        return tblQuestionsDAO.getTotalRow(questionsDTO);
    }

    @Override
    public ErrorResult insertList(List<TblQuestions> lstQuestion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TblQuestionsDTO getDTOById(Long questionId) {
        return tblQuestionsDAO.getDTOById(questionId);
    }

    @Override
    public List<TblQuestionsDTO> getSelectedQuestion(TblQuestionsDTO questionsDTO, Integer offset, Integer limit) {
        return tblQuestionsDAO.getSelectedQuestion(questionsDTO, offset, limit);
    }

    @Override
    public Integer getTotalRowSelected(TblQuestionsDTO questionsDTO) {
        return tblQuestionsDAO.getTotalRowSelected(questionsDTO);
    }

    @Override
    public List<TblQuestionsDTO> getDataByExamId(Long examId) {
        return tblQuestionsDAO.getDataByExamId(examId);
    }

    @Override
    public List<ResultOfExam> getAllAnswerOfQuestionByExamId(Long examId) {
        return tblQuestionsDAO.getAllAnswerOfQuestionByExamId(examId);
    }

    @Override
    public Integer totalQuestionOfExam(Long examId) {
        return tblQuestionsDAO.totalQuestionOfExam(examId);
    }

    @Override
    public ErrorResult deleteQuestions(List<Long> questionIds) {
        return tblQuestionsDAO.deleteList(questionIds);
    }

}
