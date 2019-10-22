/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dao;

import com.google.common.base.Strings;
import com.ptk.elearning.bo.EventLogs;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.EventLogsDTO;
import com.ptk.elearning.util.DAOUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("eventLogsDAO")
public class EventLogsDAO extends DAOUtil<EventLogs> {

    @Transactional
    public List<EventLogsDTO> getAll(EventLogsDTO eventLogs, Integer offset, Integer limit) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT e.event_id eventId,e.event_date eventDate, e.user_id userId, e.action action,"
                    + " e.status status, e.ip ip, e.user_name userName, e.description description,"
                    + " u.full_name fullname FROM event_logs e ";
            sql += " left join tbl_users u on e.user_id = u.user_id";
            sql += " WHERE 1 = 1";
            if (!Strings.isNullOrEmpty(eventLogs.getFromDate())) {
                sql += "    AND e.event_date >= :fromDate";
            }
            if (!Strings.isNullOrEmpty(eventLogs.getToDate())) {
                sql += "    AND e.event_date <= :toDate";
            }
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("eventId", LongType.INSTANCE)
                    .addScalar("eventDate", DateType.INSTANCE)
                    .addScalar("userId", LongType.INSTANCE)
                    .addScalar("fullname", StringType.INSTANCE)
                    .addScalar("action", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("ip", StringType.INSTANCE)
                    .addScalar("userName", StringType.INSTANCE)
                    .addScalar("fullname", StringType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(EventLogsDTO.class));
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            if (!Strings.isNullOrEmpty(eventLogs.getFromDate())) {
                try {
                    query.setParameter("fromDate", formatter.parse(eventLogs.getFromDate() + " 00:00:00"));
                } catch (ParseException ex) {
                    Logger.getLogger(EventLogsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (!Strings.isNullOrEmpty(eventLogs.getToDate())) {
                try {
                    query.setParameter("toDate", formatter.parse(eventLogs.getToDate() + " 23:59:59"));
                } catch (ParseException ex) {
                    Logger.getLogger(EventLogsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            List<EventLogsDTO> divisionDTOs = query.list();
            getSession().getTransaction().commit();
            return divisionDTOs;
        } catch (Exception ex) {
            Logger.getLogger(EventLogsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public Integer getTotalRow(EventLogsDTO eventLogs) {
        try {
            getSession().beginTransaction();
            String sql = "select count(*) from event_logs e WHERE 1 = 1 ";
            if (!Strings.isNullOrEmpty(eventLogs.getFromDate())) {
                sql += "    AND e.event_date >= :fromDate";
            }
            if (!Strings.isNullOrEmpty(eventLogs.getToDate())) {
                sql += "    AND e.event_date <= :toDate";
            }
            Query query = getSession().createSQLQuery(sql);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            if (!Strings.isNullOrEmpty(eventLogs.getFromDate())) {
                try {
                    query.setParameter("fromDate", formatter.parse(eventLogs.getFromDate() + " 00:00:00"));
                } catch (ParseException ex) {
                    Logger.getLogger(EventLogsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (!Strings.isNullOrEmpty(eventLogs.getToDate())) {
                try {
                    query.setParameter("toDate", formatter.parse(eventLogs.getToDate() + " 23:59:59"));
                } catch (ParseException ex) {
                    Logger.getLogger(EventLogsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            int total = ((Number) query.uniqueResult()).intValue();
            getSession().getTransaction().commit();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(EventLogsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    public ErrorResult delete(Long eventId) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String hql = "DELETE FROM EventLogs WHERE eventId = :eventId";
            Query query = getSession().createQuery(hql);
            query.setParameter("eventId", eventId);
            query.executeUpdate();
            result.setHasError(false);
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            result.setHasError(true);
            result.setError(ex.getMessage());
            result.setErrorType(JDBCConnectionException.class.getSimpleName());
        } finally {
            getSession().close();
        }
        return result;
    }
}
