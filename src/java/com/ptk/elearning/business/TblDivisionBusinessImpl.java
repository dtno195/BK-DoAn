/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblDivision;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dao.TblDivisionDAO;
import com.ptk.elearning.dto.TblDivisionDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tblDivisionBusiness")
public class TblDivisionBusinessImpl implements TblDivisionBusiness {

    @Autowired
    private TblDivisionDAO tblDivisionDAO;

    @Override
    public TblDivision findById(Long dvsId) {
        return tblDivisionDAO.findById(dvsId);
    }

    @Override
    public List<TblDivisionDTO> getAll(TblDivisionDTO divisionDTO, Integer offset, Integer limit) {
        return tblDivisionDAO.getAll(divisionDTO, offset, limit);
    }

    @Override
    public ErrorResult insert(TblDivision tblDivision) {
        return tblDivisionDAO.save(tblDivision);
    }

    @Override
    public ErrorResult update(TblDivision tblDivision) {
        return tblDivisionDAO.update(tblDivision);
    }

    @Override
    public ErrorResult delete(long id) {
        return tblDivisionDAO.delete(id);
    }

    @Override
    public Integer getTotalRow(TblDivisionDTO divisionDTO) {
        return tblDivisionDAO.getTotalRow(divisionDTO);
    }

    @Override
    public List<TblDivisionDTO> getAllParent() {
        return tblDivisionDAO.getAllParent();
    }

    @Override
    public List<TblDivisionDTO> getChildById(String dvsGroup) {
        return tblDivisionDAO.getChildById(dvsGroup);
    }

    @Override
    public ErrorResult deleteChild(Integer dvsId) {
        return tblDivisionDAO.deleteChild(dvsId);
    }

    @Override
    public List<TblDivisionDTO> getChildById(Integer dvsId) {
        return tblDivisionDAO.getChildById(dvsId);
    }

    @Override
    public TblDivision findByDvsGroup(String dvsGroup) {
        return tblDivisionDAO.findByDvsGroup(dvsGroup);
    }

    @Override
    public ErrorResult delete(String dvsGroup) {
        return tblDivisionDAO.delete(dvsGroup);
    }

    @Override
    public List<TblDivisionDTO> getDataByDvsGroup(String dvsGroup) {
        return tblDivisionDAO.getDataByDvsGroup(dvsGroup);
    }

    @Override
    public TblDivision findByName(String dvsName, String dvsGroup) {
        return tblDivisionDAO.findByName(dvsName, dvsGroup);
    }

    @Override
    public TblDivision findByValue(Integer dvsValue, String dvsGroup) {
        return tblDivisionDAO.findByValue(dvsValue, dvsGroup);
    }

}
