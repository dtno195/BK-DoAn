/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblTopic;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dao.TblDivisionDAO;
import com.ptk.elearning.dao.TblTopicDAO;
import com.ptk.elearning.dto.HomeWrapper;
import com.ptk.elearning.dto.TblDivisionDTO;
import com.ptk.elearning.dto.TblTopicDTO;
import com.ptk.elearning.util.DivisionConstant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tblTopicBusinessImpl")
public class TblTopicBusinessImpl implements TblTopicBusiness {

    @Autowired
    TblTopicDAO tblTopicDAO;
    @Autowired
    TblDivisionDAO tblDivisionDAO;

    @Override
    public List<TblTopicDTO> getAllTopicBySubjectId(Integer subjectId, String keyWord, Integer limit, Integer offset) {
        return tblTopicDAO.getAllTopicBySubjectId(subjectId, keyWord, limit, offset);
    }

    @Override
    public Integer getTotalTopicBySubjectId(Integer subjectId, String keyWord) {
        return tblTopicDAO.getTotalTopicBySubjectId(subjectId, keyWord);
    }

    @Override
    public TblTopic findById(Long topicId) {
        return tblTopicDAO.findById(topicId);
    }

    @Override
    public List<HomeWrapper> getAllSubjectAndTopic() {
        List<TblDivisionDTO> lst = tblDivisionDAO.getDataByDvsGroup(DivisionConstant.SUBJECT);
        List<HomeWrapper> lstHomeWrappers = new ArrayList<>();
        for (TblDivisionDTO e : lst) {
            HomeWrapper homeWrapper = new HomeWrapper();
            homeWrapper.setSubjectId(e.getDvsValue());
            homeWrapper.setSubjectName(e.getDvsName());
            List<TblTopicDTO> lstTblTopicDTOs = tblTopicDAO.getAllTopicBySubjectId(e.getDvsValue(), "", 0, 0);
            homeWrapper.setLstTopic(lstTblTopicDTOs);
            lstHomeWrappers.add(homeWrapper);
        }
        return lstHomeWrappers;
    }

    @Override
    public ErrorResult insert(TblTopic topic) {
        return tblTopicDAO.save(topic);
    }

    @Override
    public ErrorResult update(TblTopic topic) {
        return tblTopicDAO.update(topic);
    }

    @Override
    public ErrorResult delete(long id) {
        return tblTopicDAO.delete(id);
    }
}
