/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblUsers;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblUsersDTO;
import java.util.List;

public interface ITblUsersBusiness {

    TblUsers findById(Long userId);

    TblUsers findByUsername(String userName);

    List<TblUsersDTO> getAll(TblUsersDTO usersDTO, Integer offset, Integer limit);

    Integer getTotalRow(TblUsersDTO usersDTO);

    ErrorResult insert(TblUsers tblUsers);

    ErrorResult update(TblUsers tblUsers);

    ErrorResult delete(long id);
}
