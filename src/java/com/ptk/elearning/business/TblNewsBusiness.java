/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblNews;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblNewsDTO;
import java.util.List;


public interface TblNewsBusiness {

    TblNewsDTO findById(Long newId);

    List<TblNewsDTO> getAll(TblNewsDTO tblNewsDTO, Integer offset, Integer limit);

    ErrorResult insert(TblNews tblNews);

    ErrorResult update(TblNews tblNews);

    ErrorResult delete(long id);

    Integer getTotalRow(TblNewsDTO tblNewsDTO);

}
