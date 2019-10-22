/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblAnswer;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblAnswerDTO;
import java.util.List;


public interface TblAnswerBusiness {

    List<TblAnswerDTO> getAll(Long questionId);

    ErrorResult insert(TblAnswer answer);

    ErrorResult update(TblAnswer answer);

    ErrorResult delete(long questionId);

    ErrorResult insertList(List<TblAnswer> lstAnswer);

    ErrorResult insertListReturnId(List<TblAnswer> lstAnswer);

    ErrorResult updateList(List<TblAnswer> lstAnswer);

    TblAnswer getAnswerCorrectOfQuestion(Long questionId);

    TblAnswer getAnswerById(Long anwserId);
}
