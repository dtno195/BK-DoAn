/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.TblTopic;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.HomeWrapper;
import com.ptk.elearning.dto.TblTopicDTO;
import java.util.List;


public interface TblTopicBusiness {

    List<TblTopicDTO> getAllTopicBySubjectId(Integer subjectId, String keyWord, Integer limit, Integer offset);

    List<HomeWrapper> getAllSubjectAndTopic();

    Integer getTotalTopicBySubjectId(Integer subjectId, String keyWord);

    TblTopic findById(Long questionId);

    ErrorResult insert(TblTopic questions);

    ErrorResult update(TblTopic questions);

    ErrorResult delete(long id);
}
