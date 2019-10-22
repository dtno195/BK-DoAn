/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblAnswer;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dao.TblAnswerDAO;
import com.ptk.elearning.dto.TblAnswerDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tblAnswerBusiness")
public class TblAnswerBusinessImpl implements TblAnswerBusiness {

    @Autowired
    private TblAnswerDAO tblAnswerDAO;

    @Override
    public List<TblAnswerDTO> getAll(Long questionId) {
        return tblAnswerDAO.getAll(questionId);
    }

    @Override
    public ErrorResult insert(TblAnswer answer) {
        return tblAnswerDAO.save(answer);
    }

    @Override
    public ErrorResult update(TblAnswer answer) {
        return tblAnswerDAO.update(answer);
    }

    @Override
    public ErrorResult delete(long answerId) {
        return tblAnswerDAO.delete(answerId);
    }

    @Override
    public ErrorResult insertList(List<TblAnswer> lstAnswer) {
        return tblAnswerDAO.saveList(lstAnswer);
    }

    @Override
    public ErrorResult updateList(List<TblAnswer> lstAnswer) {
        return tblAnswerDAO.updateList(lstAnswer);
    }

    @Override
    public TblAnswer getAnswerCorrectOfQuestion(Long questionId) {
        return tblAnswerDAO.getAnswerCorrectOfQuestion(questionId);
    }

    @Override
    public TblAnswer getAnswerById(Long anwserId) {
        return tblAnswerDAO.getAnswerById(anwserId);
    }

    public ErrorResult insertListReturnId(List<TblAnswer> lstAnswer) {
        return tblAnswerDAO.saveListReturnId(lstAnswer);
    }
}
