/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblExam;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dao.TblExamDAO;
import com.ptk.elearning.dto.TblExamDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tblExamBusiness")
public class TblExamBusinessImpl implements TblExamBusiness {

    @Autowired
    private TblExamDAO tblExamDAO;

    @Override
    public TblExamDTO findById(Long examId) {
        return tblExamDAO.getDTOById(examId);
    }

    @Override
    public List<TblExamDTO> getAll(TblExamDTO tblExamDTO, Integer offset, Integer limit) {
        return tblExamDAO.getAll(tblExamDTO, offset, limit);
    }

    @Override
    public ErrorResult insert(TblExam tblExam) {
        return tblExamDAO.save(tblExam);
    }

    @Override
    public ErrorResult update(TblExam tblExam) {
        return tblExamDAO.update(tblExam);
    }

    @Override
    public ErrorResult delete(long id) {
        return tblExamDAO.delete(id);
    }

    @Override
    public Integer getTotalRow(TblExamDTO tblExamDTO) {
        return tblExamDAO.getTotalRow(tblExamDTO);
    }

    @Override
    public Long insertReturnId(TblExam tblExam) {
        return tblExamDAO.saveReturnId(tblExam);
    }

}
