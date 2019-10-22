/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblComment;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblCommentDTO;
import java.util.List;


public interface TblCommentBusiness {
    
    List<TblCommentDTO> getAllCommentByNewId(long newId);
    
    ErrorResult insert(TblComment tblComment);
}
