/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblExamQuestion;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dao.TblExamQuestionDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tblExamQuestionBusiness")
public class TblExamQuestionBusinessImpl implements TblExamQuestionBusiness {

    @Autowired
    private TblExamQuestionDAO tblExamQuestionDAO;

    @Override
    public ErrorResult insert(TblExamQuestion examQuestion) {
        return tblExamQuestionDAO.save(examQuestion);
    }

    @Override
    public ErrorResult update(TblExamQuestion examQuestion) {
        return tblExamQuestionDAO.update(examQuestion);
    }

    @Override
    public ErrorResult delete(long id) {
        return tblExamQuestionDAO.delete(id);
    }

    @Override
    public ErrorResult insertList(List<TblExamQuestion> lstExamQuestion) {
        return tblExamQuestionDAO.saveList(lstExamQuestion);
    }

    @Override
    public ErrorResult updateList(List<TblExamQuestion> lstExamQuestion) {
        return tblExamQuestionDAO.updateList(lstExamQuestion);
    }

    @Override
    public ErrorResult delete(List<Long> ids) {
        return tblExamQuestionDAO.deleteList(ids);
    }

}
