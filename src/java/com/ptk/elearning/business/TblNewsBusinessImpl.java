/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblNews;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dao.TblNewsDAO;
import com.ptk.elearning.dto.TblNewsDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tblNewsBusiness")
public class TblNewsBusinessImpl implements TblNewsBusiness {

    @Autowired
    private TblNewsDAO tblNewsDAO;

    @Override
    public TblNewsDTO findById(Long newId) {
        return tblNewsDAO.getDTOById(newId);
    }

    @Override
    public List<TblNewsDTO> getAll(TblNewsDTO tblNewsDTO, Integer offset, Integer limit) {
        return tblNewsDAO.getAll(tblNewsDTO, offset, limit);
    }

    @Override
    public ErrorResult insert(TblNews tblNews) {
        return tblNewsDAO.save(tblNews);
    }

    @Override
    public ErrorResult update(TblNews tblNews) {
        return tblNewsDAO.update(tblNews);
    }

    @Override
    public ErrorResult delete(long id) {
        return tblNewsDAO.delete(id);
    }

    @Override
    public Integer getTotalRow(TblNewsDTO tblNewsDTO) {
        return tblNewsDAO.getTotalRow(tblNewsDTO);
    }

}
