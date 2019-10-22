/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.business;

import com.ptk.elearning.bo.EventLogs;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dao.EventLogsDAO;
import com.ptk.elearning.dto.EventLogsDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("eventLogsBusiness")
public class EventLogsBusinessImpl implements EventLogsBusiness {

    @Autowired
    private EventLogsDAO eventLogsDAO;

    @Override

    public List<EventLogsDTO> getAll(EventLogsDTO eventLogsDTO, Integer offset, Integer limit) {
        return eventLogsDAO.getAll(eventLogsDTO, offset, limit);
    }

    @Override
    public ErrorResult insert(EventLogs eventLogs) {
        return eventLogsDAO.save(eventLogs);
    }

    @Override
    public Integer getTotalRow(EventLogsDTO eventLogsDTO) {
        return eventLogsDAO.getTotalRow(eventLogsDTO);
    }

    @Override
    public ErrorResult delete(long eventId) {
        return eventLogsDAO.delete(eventId);
    }

}
