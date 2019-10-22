/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dao;

import com.google.common.base.Strings;
import com.ptk.elearning.bo.TblNews;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblNewsDTO;
import com.ptk.elearning.util.DAOUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("tblNewsDAO")
public class TblNewsDAO extends DAOUtil<TblNews> {

    @Transactional
    public List<TblNewsDTO> getAll(TblNewsDTO newsDTO, Integer offset, Integer limit) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT A.new_id newId, A.title title, A.content content, A.user_created userCreated"
                    + ", A.date_created dateCreated, A.topic_id topicId, D.name topicName, U.full_name fullname "
                    + "FROM TBL_NEWS A inner join tbl_topic D on A.topic_id = D.id "
                    + "left join tbl_users U on A.user_created = U.user_id"
                    + " WHERE 1=1";
            if (!Strings.isNullOrEmpty(newsDTO.getTitle())) {
                sql += " AND A.title like :title";
            }
            if (!Strings.isNullOrEmpty(newsDTO.getFullname())) {
                sql += " AND U.full_name like :fullname";
            }
            if (newsDTO.getTopicId() != null && newsDTO.getTopicId() > 0) {
                sql += " AND A.topic_id = :topicId";
            }
            if (!Strings.isNullOrEmpty(newsDTO.getFromDate())) {
                sql += "    AND A.date_created >= :fromDate";
            }
            if (!Strings.isNullOrEmpty(newsDTO.getToDate())) {
                sql += "    AND A.date_created <= :toDate";
            }
            sql += "    ORDER BY A.date_created DESC";

            Query query = getSession().createSQLQuery(sql)
                    .addScalar("newId", LongType.INSTANCE)
                    .addScalar("title", StringType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .addScalar("userCreated", LongType.INSTANCE)
                    .addScalar("dateCreated", DateType.INSTANCE)
                    .addScalar("topicId", LongType.INSTANCE)
                    .addScalar("topicName", StringType.INSTANCE)
                    .addScalar("fullname", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblNewsDTO.class));
            if (!Strings.isNullOrEmpty(newsDTO.getTitle())) {
                query.setParameter("title", "%" + newsDTO.getTitle() + "%");
            }
            if (!Strings.isNullOrEmpty(newsDTO.getFullname())) {
                query.setParameter("fullname", "%" + newsDTO.getFullname() + "%");
            }
            if (newsDTO.getTopicId() != null && newsDTO.getTopicId() > 0) {
                query.setParameter("topicId", newsDTO.getTopicId());
            }
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            if (!Strings.isNullOrEmpty(newsDTO.getFromDate())) {
                try {
                    query.setParameter("fromDate", formatter.parse(newsDTO.getFromDate() + " 00:00:00"));
                } catch (ParseException ex) {
                    Logger.getLogger(EventLogsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (!Strings.isNullOrEmpty(newsDTO.getToDate())) {
                try {
                    query.setParameter("toDate", formatter.parse(newsDTO.getToDate() + " 23:59:59"));
                } catch (ParseException ex) {
                    Logger.getLogger(EventLogsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            List<TblNewsDTO> tblNewsDTOs = query.list();
            getSession().getTransaction().commit();
            return tblNewsDTOs;
        } catch (Exception ex) {
            Logger.getLogger(TblNewsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public Integer getTotalRow(TblNewsDTO newsDTO) {
        try {
            getSession().beginTransaction();
            String sql = "select count(new_id) from TBL_NEWS  WHERE 1 = 1 ";
            if (!Strings.isNullOrEmpty(newsDTO.getTitle())) {
                sql += " AND A.title like :title";
            }
            if (!Strings.isNullOrEmpty(newsDTO.getFullname())) {
                sql += " AND B.full_name like :fullname";
            }
            if (newsDTO.getTopicId() != null && newsDTO.getTopicId() > 0) {
                sql += " AND A.topic_id = :topicId";
            }
            if (!Strings.isNullOrEmpty(newsDTO.getFromDate())) {
                sql += "    AND e.date_created >= :fromDate";
            }
            if (!Strings.isNullOrEmpty(newsDTO.getToDate())) {
                sql += "    AND e.date_created <= :toDate";
            }
            Query query = getSession().createSQLQuery(sql);
            if (!Strings.isNullOrEmpty(newsDTO.getTitle())) {
                query.setParameter("title", "%" + newsDTO.getTitle() + "%");
            }
            if (!Strings.isNullOrEmpty(newsDTO.getFullname())) {
                query.setParameter("fullname", "%" + newsDTO.getFullname() + "%");
            }
            if (newsDTO.getTopicId() != null && newsDTO.getTopicId() > 0) {
                query.setParameter("topicId", newsDTO.getTopicId());
            }
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            if (!Strings.isNullOrEmpty(newsDTO.getFromDate())) {
                try {
                    query.setParameter("fromDate", formatter.parse(newsDTO.getFromDate() + " 00:00:00"));
                } catch (ParseException ex) {
                    Logger.getLogger(EventLogsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (!Strings.isNullOrEmpty(newsDTO.getToDate())) {
                try {
                    query.setParameter("toDate", formatter.parse(newsDTO.getToDate() + " 23:59:59"));
                } catch (ParseException ex) {
                    Logger.getLogger(EventLogsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            int total = ((Number) query.uniqueResult()).intValue();
            getSession().getTransaction().commit();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(TblNewsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            getSession().close();
        }

    }

    public ErrorResult delete(Long id) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String hql = "DELETE FROM TblNews WHERE newId = :newId";
            Query query = getSession().createQuery(hql);
            query.setParameter("newId", id);
            query.executeUpdate();
            result.setHasError(false);
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(TblNewsDAO.class.getName()).log(Level.SEVERE, null, ex);
            result.setHasError(true);
            result.setError(ex.getMessage());
            result.setErrorType(JDBCConnectionException.class.getSimpleName());
        } finally {
            getSession().close();
        }
        return result;
    }

    public TblNews findById(Long newId) {
        try {
            getSession().beginTransaction();
            Criteria cr = getSession().createCriteria(TblNews.class);
            cr.add(Restrictions.eq("newId", newId));
            TblNews tblNews = (TblNews) cr.uniqueResult();
            getSession().getTransaction().commit();
            return tblNews;
        } catch (Exception ex) {
            Logger.getLogger(TblNewsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public TblNewsDTO getDTOById(Long newId) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT new_id newId,"
                    + "title title,"
                    + "content content,"
                    + "user_created userCreated,"
                    + "date_created dateCreated,"
                    + "topic_id topicId "
                    + "FROM TBL_NEWS WHERE new_id = :newId";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("newId", LongType.INSTANCE)
                    .addScalar("title", StringType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .addScalar("userCreated", LongType.INSTANCE)
                    .addScalar("dateCreated", DateType.INSTANCE)
                    .addScalar("topicId", LongType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblNewsDTO.class));
            query.setParameter("newId", newId);
//            query.setMaxResults(1);
            List<TblNewsDTO> divisionDTOs = query.list();
            getSession().getTransaction().commit();
            return divisionDTOs.isEmpty() ? null : divisionDTOs.get(0);
        } catch (Exception ex) {
            Logger.getLogger(TblNewsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }
}
