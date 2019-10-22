/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.EventLogs;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.EventLogsDTO;
import java.util.List;


public interface EventLogsBusiness {

    List<EventLogsDTO> getAll(EventLogsDTO eventLogsDTO, Integer offset, Integer limit);

    ErrorResult insert(EventLogs eventLogs);

    Integer getTotalRow(EventLogsDTO eventLogsDTO);

    ErrorResult delete(long eventId);
}
