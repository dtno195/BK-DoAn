/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblExam;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblExamDTO;
import java.util.List;


public interface TblExamBusiness {

    TblExamDTO findById(Long examId);

    List<TblExamDTO> getAll(TblExamDTO tblExamDTO, Integer offset, Integer limit);

    ErrorResult insert(TblExam tblExam);
    
    Long insertReturnId(TblExam tblExam);

    ErrorResult update(TblExam tblExam);

    ErrorResult delete(long id);

    Integer getTotalRow(TblExamDTO tblExamDTO);
}
