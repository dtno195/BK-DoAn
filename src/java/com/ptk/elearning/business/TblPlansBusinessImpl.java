/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblPlans;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dao.TblPlansDAO;
import com.ptk.elearning.dto.FullCalendar;
import com.ptk.elearning.dto.TblPlansDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tblPlansBusiness")
public class TblPlansBusinessImpl implements TblPlansBusiness {

    @Autowired
    private TblPlansDAO tblPlansDAO;

    @Override
    public TblPlans findById(Long planId) {
        return tblPlansDAO.findById(planId);
    }

    @Override
    public List<TblPlansDTO> getAll(TblPlansDTO tblPlansDTO, Integer offset, Integer limit) {
        return tblPlansDAO.getAll(tblPlansDTO, offset, limit);
    }

    @Override
    public ErrorResult insert(TblPlans tblNews) {
        return tblPlansDAO.save(tblNews);
    }

    @Override
    public ErrorResult update(TblPlans tblNews) {
        return tblPlansDAO.update(tblNews);
    }

    @Override
    public ErrorResult delete(long id) {
        return tblPlansDAO.delete(id);
    }

    @Override
    public Integer getTotalRow(TblPlansDTO tblNewsDTO) {
        return tblPlansDAO.getTotalRow(tblNewsDTO);
    }

    @Override
    public List<FullCalendar> getData(TblPlansDTO tblPlansDTO) {
    return tblPlansDAO.getData(tblPlansDTO);
    }

//    @Override
    public List<FullCalendar> getListRemind(String startDate, String endDate) {
        TblPlansDAO tblPlansDAO = new TblPlansDAO();
        return tblPlansDAO.getListRemind(startDate, endDate);
    }

}
