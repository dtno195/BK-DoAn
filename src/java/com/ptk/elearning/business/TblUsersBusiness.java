/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblUsers;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dao.TblUserDAO;
import com.ptk.elearning.dto.TblUsersDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tblUsersBusiness")
public class TblUsersBusiness implements ITblUsersBusiness {

    @Autowired
    TblUserDAO tblUserDAO;

    @Override
    public TblUsers findById(Long userId) {
        return tblUserDAO.getOneAnswerByGid(userId);
    }

    @Override
    public List<TblUsersDTO> getAll(TblUsersDTO usersDTO, Integer offset, Integer limit) {
        return tblUserDAO.getAll(usersDTO, offset, limit);
    }

    @Override
    public ErrorResult insert(TblUsers tblUsers) {
        return tblUserDAO.save(tblUsers);
    }

    @Override
    public ErrorResult update(TblUsers tblUsers) {
        return tblUserDAO.update(tblUsers);
    }

    @Override
    public ErrorResult delete(long id) {
        return tblUserDAO.delete(id);
    }

    @Override
    public TblUsers findByUsername(String userName) {
        return tblUserDAO.findByUsername(userName);
    }

    @Override
    public Integer getTotalRow(TblUsersDTO usersDTO) {
        return tblUserDAO.getTotalRow(usersDTO);
    }

}
