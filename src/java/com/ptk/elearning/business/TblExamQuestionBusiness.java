/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblExamQuestion;
import com.ptk.elearning.common.ErrorResult;
import java.util.List;


public interface TblExamQuestionBusiness {

    ErrorResult insert(TblExamQuestion examQuestion);

    ErrorResult update(TblExamQuestion examQuestion);

    ErrorResult delete(long id);

    ErrorResult delete(List<Long> ids);

    ErrorResult insertList(List<TblExamQuestion> lstExamQuestion);

    ErrorResult updateList(List<TblExamQuestion> lstExamQuestion);
}
