/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.ExamResult;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dao.ExamResultDAO;
import com.ptk.elearning.dto.ExamResultDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("examResultBusiness")
public class ExamResultBusinessImpl implements ExamResultBusiness {

    @Autowired
    private ExamResultDAO examResultDAO;
    
    @Override
    public Double avgScore(int year, int month, int subject, int userId) {
        return examResultDAO.avgScore(year, month, subject, userId);
    }

    @Override
    public List<ExamResultDTO> getAllResultOfUser(Long userId, Integer limit, Integer offset) {
        return examResultDAO.getAllResultOfUser(userId, limit, offset);
    }

    @Override
    public Integer totalResultOfUser(Long userId) {
        return examResultDAO.totalResultOfUser(userId);
    }

    @Override
    public ErrorResult insert(ExamResult examResult) {
        return examResultDAO.save(examResult);
    }
    
}
