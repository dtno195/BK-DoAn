/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblQuestions;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.ResultOfExam;
import com.ptk.elearning.dto.TblQuestionsDTO;
import java.util.List;


public interface TblQuestionsBusiness {

    TblQuestions findById(Long questionId);

    TblQuestionsDTO getDTOById(Long questionId);

    List<TblQuestionsDTO> getAll(TblQuestionsDTO questionsDTO, Integer offset, Integer limit);

    ErrorResult insert(TblQuestions questions);

    ErrorResult update(TblQuestions questions);

    ErrorResult delete(long questionId);

    ErrorResult deleteQuestions(List<Long> questionIds);

    Integer getTotalRow(TblQuestionsDTO questionsDTO);

    ErrorResult insertList(List<TblQuestions> lstQuestion);

    List<TblQuestionsDTO> getSelectedQuestion(TblQuestionsDTO questionsDTO, Integer offset, Integer limit);

    List<TblQuestionsDTO> getDataByExamId(Long examId);

    Integer getTotalRowSelected(TblQuestionsDTO questionsDTO);

    List<ResultOfExam> getAllAnswerOfQuestionByExamId(Long examId);

    Integer totalQuestionOfExam(Long examId);

}
