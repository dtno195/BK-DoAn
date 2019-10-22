/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblDivision;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblDivisionDTO;
import java.util.List;


public interface TblDivisionBusiness {

    TblDivision findById(Long dvsId);

    TblDivision findByDvsGroup(String dvsGroup);

    List<TblDivisionDTO> getAll(TblDivisionDTO divisionDTO, Integer offset, Integer limit);

    List<TblDivisionDTO> getAllParent();

    List<TblDivisionDTO> getChildById(String dvsGroup);

    List<TblDivisionDTO> getDataByDvsGroup(String dvsGroup);

    List<TblDivisionDTO> getChildById(Integer dvsId);

    ErrorResult insert(TblDivision tblDivision);

    ErrorResult update(TblDivision tblDivision);

    ErrorResult delete(long id);

    ErrorResult delete(String dvsGroup);

    Integer getTotalRow(TblDivisionDTO divisionDTO);

    ErrorResult deleteChild(Integer dvsId);

    TblDivision findByName(String dvsName, String dvsGroup);

    TblDivision findByValue(Integer dvsValue, String dvsGroup);
}
