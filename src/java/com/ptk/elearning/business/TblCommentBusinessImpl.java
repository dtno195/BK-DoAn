/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblComment;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dao.TblCommentDAO;
import com.ptk.elearning.dto.TblCommentDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tblCommentBusiness")
public class TblCommentBusinessImpl implements  TblCommentBusiness {
    
    @Autowired
    private TblCommentDAO tblCommentDAO;

    @Override
    public List<TblCommentDTO> getAllCommentByNewId(long newId) {
        return tblCommentDAO.getAll(newId);
    }

    @Override
    public ErrorResult insert(TblComment tblComment) {
        return tblCommentDAO.save(tblComment);
    }
}
