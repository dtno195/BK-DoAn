/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.base;

import java.util.Date;
import java.util.List;


public interface BaseFWBusiness<TDTO extends BaseFWDTOImpl, TModel extends BaseFWModelImpl> {

    List<TDTO> getAll();

    BaseFWDTOImpl getOneById(Long costCenterId);

    Long save(TDTO costCenterBO);

    Long update(TDTO costCenterBO);

    void delete(TDTO costCenterBO);

    Date getSysDate() throws Exception;

    Long getNextValSequence(String sequense);
}
