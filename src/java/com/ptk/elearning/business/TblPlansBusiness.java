/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblPlans;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.FullCalendar;
import com.ptk.elearning.dto.TblPlansDTO;
import java.util.List;


public interface TblPlansBusiness {
    List<FullCalendar> getListRemind(String startDate, String endDate);

    TblPlans findById(Long planId);

    List<TblPlansDTO> getAll(TblPlansDTO tblPlansDTO, Integer offset, Integer limit);

    List<FullCalendar> getData(TblPlansDTO tblPlansDTO);

    ErrorResult insert(TblPlans tblNews);

    ErrorResult update(TblPlans tblNews);

    ErrorResult delete(long id);

    Integer getTotalRow(TblPlansDTO tblNewsDTO);
}
