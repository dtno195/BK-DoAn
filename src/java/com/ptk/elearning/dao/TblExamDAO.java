/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dao;

import com.google.common.base.Strings;
import com.ptk.elearning.bo.TblExam;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblExamDTO;
import com.ptk.elearning.util.DAOUtil;
import com.ptk.elearning.util.DivisionConstant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("tblExamDAO")
public class TblExamDAO extends DAOUtil<TblExam> {

    @Transactional
    public List<TblExamDTO> getAll(TblExamDTO tblExamDTO, Integer offset, Integer limit) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT E.exam_id examId, E.name name, E.content content,  E.date_created dateCreated,";
            sql += "E.time_id timeId, E.subject_id subjectId, E.user_created userCreated";
            sql += " ,D1.dvs_name subjectName, D2.dvs_name timeName, U.full_name fullname ";
            sql += " FROM TBL_EXAM E left join TBL_DIVISION D1 on E.subject_id = D1.dvs_value AND D1.dvs_group = :subject ";
            sql += " left join TBL_DIVISION D2 on E.time_id = D2.dvs_value AND D2.dvs_group = :time ";
            sql += " left join TBL_USERS U on E.user_created = U.user_id WHERE 1 = 1 ";
            if (tblExamDTO.getSubjectId() != null && tblExamDTO.getSubjectId() > 0) {
                sql += " AND E.subject_id = :subjectId";
            }
            if (tblExamDTO.getTimeId() != null && tblExamDTO.getTimeId() > 0) {
                sql += " AND E.time_id = :timeId";
            }
            if (!Strings.isNullOrEmpty(tblExamDTO.getName())) {
                sql += " AND E.name like :name";
            }
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("examId", LongType.INSTANCE)
                    .addScalar("name", StringType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .addScalar("dateCreated", DateType.INSTANCE)
                    .addScalar("timeId", IntegerType.INSTANCE)
                    .addScalar("subjectId", IntegerType.INSTANCE)
                    .addScalar("userCreated", LongType.INSTANCE)
                    .addScalar("subjectName", StringType.INSTANCE)
                    .addScalar("timeName", StringType.INSTANCE)
                    .addScalar("fullname", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblExamDTO.class));
            query.setParameter("time", DivisionConstant.TIME_ID);
            query.setParameter("subject", DivisionConstant.SUBJECT);
            if (tblExamDTO.getSubjectId() != null && tblExamDTO.getSubjectId() > 0) {
                query.setParameter("subjectId", tblExamDTO.getSubjectId());
            }
            if (tblExamDTO.getTimeId() != null && tblExamDTO.getTimeId() > 0) {
                query.setParameter("timeId", tblExamDTO.getTimeId());
            }
            if (!Strings.isNullOrEmpty(tblExamDTO.getName())) {
                query.setParameter("name", "%" + tblExamDTO.getName() + "%");
            }
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            List<TblExamDTO> tblExamDTOs = query.list();
            getSession().getTransaction().commit();
            return tblExamDTOs;
        } catch (Exception ex) {
            Logger.getLogger(TblExamDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public Integer getTotalRow(TblExamDTO tblExamDTO) {
        try {
            getSession().beginTransaction();
            String sql = "select count(*) from TBL_EXAM E WHERE 1 = 1 ";
            if (tblExamDTO.getSubjectId() != null && tblExamDTO.getSubjectId() > 0) {
                sql += " AND E.subject_id = :subjectId";
            }
            if (tblExamDTO.getTimeId() != null && tblExamDTO.getTimeId() > 0) {
                sql += " AND E.time_id = :timeId";
            }
            if (!Strings.isNullOrEmpty(tblExamDTO.getName())) {
                sql += " AND E.name like :name";
            }
            Query query = getSession().createSQLQuery(sql);
            if (tblExamDTO.getSubjectId() != null && tblExamDTO.getSubjectId() > 0) {
                query.setParameter("subjectId", tblExamDTO.getSubjectId());
            }
            if (tblExamDTO.getTimeId() != null && tblExamDTO.getTimeId() > 0) {
                query.setParameter("timeId", tblExamDTO.getTimeId());
            }
            if (!Strings.isNullOrEmpty(tblExamDTO.getName())) {
                query.setParameter("name", "%" + tblExamDTO.getName() + "%");
            }
            int total = ((Number) query.uniqueResult()).intValue();
            getSession().getTransaction().commit();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(TblExamDAO.class.getName()).log(Level.SEVERE, null, ex);

            return 0;
        } finally {
            getSession().close();
        }

    }

    public ErrorResult delete(Long id) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String hql = "DELETE FROM TblExam WHERE exam_id = :examId";
            Query query = getSession().createQuery(hql);
            query.setParameter("examId", id);
            query.executeUpdate();
            result.setHasError(false);
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(TblExamDAO.class.getName()).log(Level.SEVERE, null, ex);
            result.setHasError(true);
            result.setError(ex.getMessage());
            result.setErrorType(JDBCConnectionException.class.getSimpleName());
        } finally {
            getSession().close();
        }
        return result;
    }

    public TblExam findById(Long examId) {
        try {
            getSession().beginTransaction();
            Criteria cr = getSession().createCriteria(TblExam.class);
            cr.add(Restrictions.eq("examId", examId));
            TblExam tblNews = (TblExam) cr.uniqueResult();
            getSession().getTransaction().commit();
            return tblNews;
        } catch (Exception ex) {
            Logger.getLogger(TblExamDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public TblExamDTO getDTOById(Long newId) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT E.exam_id examId, E.name name, E.content content,  E.date_created dateCreated,";
            sql += " E.time_id timeId, E.subject_id subjectId, E.user_created userCreated";
            sql += " ,D1.dvs_name subjectName, D2.dvs_name timeName, U.full_name fullname,D2.dvs_order timeValue";
            sql += " FROM TBL_EXAM E left join TBL_DIVISION D1 on E.subject_id = D1.dvs_value AND D1.dvs_group = :subject ";
            sql += " left join TBL_DIVISION D2 on E.time_id = D2.dvs_value AND D2.dvs_group = :time ";
            sql += " left join TBL_USERS U on E.user_created = U.user_id WHERE E.exam_id = :examId";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("examId", LongType.INSTANCE)
                    .addScalar("name", StringType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .addScalar("dateCreated", DateType.INSTANCE)
                    .addScalar("timeValue", IntegerType.INSTANCE)
                    .addScalar("timeId", IntegerType.INSTANCE)
                    .addScalar("subjectId", IntegerType.INSTANCE)
                    .addScalar("userCreated", LongType.INSTANCE)
                    .addScalar("subjectName", StringType.INSTANCE)
                    .addScalar("timeName", StringType.INSTANCE)
                    .addScalar("fullname", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblExamDTO.class));
            query.setParameter("examId", newId);
            query.setParameter("time", DivisionConstant.TIME_ID);
            query.setParameter("subject", DivisionConstant.SUBJECT);
            List<TblExamDTO> tblExamDTOs = query.list();
            getSession().getTransaction().commit();
            return tblExamDTOs.isEmpty() ? null : tblExamDTOs.get(0);
        } catch (Exception ex) {
            Logger.getLogger(TblExamDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }
}
