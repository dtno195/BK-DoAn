/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.ExamResult;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.ExamResultDTO;
import java.util.List;


public interface ExamResultBusiness {

    Double avgScore(int year, int month, int subject, int userId);

    List<ExamResultDTO> getAllResultOfUser(Long userId, Integer limit, Integer offset);

    Integer totalResultOfUser(Long userId);
    
    ErrorResult insert(ExamResult examResult);
}
