/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dao;

import com.google.common.base.Strings;
import com.ptk.elearning.bo.TblTopic;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblTopicDTO;
import com.ptk.elearning.util.DAOUtil;
import com.ptk.elearning.util.DivisionConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;


@Repository("tblTopicDAO")
public class TblTopicDAO extends DAOUtil<TblTopic> {

    public List<TblTopicDTO> getAllTopicBySubjectId(Integer subjectId, String keyWord, Integer limit, Integer offset) {
        try {
            getSession().beginTransaction();
            List<TblTopicDTO> lst = new ArrayList<>();
            String sql = "SELECT e.id id,e.subject_id subjectId, e.name name, u.description subjectName "
                    + " FROM tbl_topic e ";
            sql += " left join tbl_division u on e.subject_id = u.dvs_value and u.dvs_name = :subject";
            sql += " WHERE 1 = 1";
            if (subjectId > 0) {
                sql += " AND e.subject_id = :subjectId";
            }
            if (!Strings.isNullOrEmpty(keyWord)) {
                sql += " AND e.name like :name";
            }
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("id", LongType.INSTANCE)
                    .addScalar("subjectId", LongType.INSTANCE)
                    .addScalar("name", StringType.INSTANCE)
                    .addScalar("subjectName", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblTopicDTO.class));
            if (!Strings.isNullOrEmpty(keyWord)) {
                query.setParameter("name", "%" + keyWord + "%");
            }
            query.setParameter("subject", DivisionConstant.SUBJECT);
            if (subjectId > 0) {
                query.setParameter("subjectId", subjectId);
            }
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            lst = query.list();
            getSession().getTransaction().commit();
            return lst;
        } catch (Exception e) {
            return null;
        } finally {
            getSession().close();
        }
    }

    public Integer getTotalTopicBySubjectId(Integer subjectId, String keyWord) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT count(*) "
                    + " FROM tbl_topic e ";
            sql += " left join tbl_division u on e.subject_id = u.dvs_name and u.dvs_group = :subject";
            sql += " WHERE 1 = 1";
            if (subjectId > 0) {
                sql += " AND e.subject_id = :subjectId";
            }
            if (!Strings.isNullOrEmpty(keyWord)) {
                sql += " AND e.name like :name";
            }
            Query query = getSession().createSQLQuery(sql);

            query.setParameter("subject", DivisionConstant.SUBJECT);
            if (subjectId > 0) {
                query.setParameter("subjectId", subjectId);
            }
            if (!Strings.isNullOrEmpty(keyWord)) {
                query.setParameter("name", "%" + keyWord + "%");
            }
            int total = ((Number) query.uniqueResult()).intValue();
            getSession().getTransaction().commit();
            return total;
        } catch (Exception e) {
            return 0;
        } finally {
            getSession().close();
        }
    }

    public TblTopic findById(Long topicId) {
        try {
            getSession().beginTransaction();
            Criteria cr = getSession().createCriteria(TblTopic.class);
            cr.add(Restrictions.eq("id", topicId));
            TblTopic tblTopic = (TblTopic) cr.uniqueResult();
            getSession().getTransaction().commit();
            return tblTopic;
        } catch (Exception ex) {
            Logger.getLogger(TblQuestionsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }
    public ErrorResult delete(Long id) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String hql = "DELETE FROM TblTopic WHERE id = :id";
            Query query = getSession().createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            result.setHasError(false);
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(TblQuestionsDAO.class.getName()).log(Level.SEVERE, null, ex);
            result.setHasError(true);
            result.setError(ex.getMessage());
            result.setErrorType(JDBCConnectionException.class.getSimpleName());
        } finally {
            getSession().close();
        }
        return result;
    }

}
