/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dao;

import com.ptk.elearning.bo.TblPlans;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.FullCalendar;
import com.ptk.elearning.dto.TblNewsDTO;
import com.ptk.elearning.dto.TblPlansDTO;
import com.ptk.elearning.util.DAOUtil;
import com.ptk.elearning.util.DivisionConstant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("tblPlansDAO")
public class TblPlansDAO extends DAOUtil<TblPlans> {

    
    public List<FullCalendar> getListRemind(String startDate, String endDate) {
        try {
            String sql = ""
                    + " SELECT"
                    + "     plan_id id,content description, type icon, user_id, to_date end, from_date start, title title, color color "
                    + " FROM TBL_PLANS"
                    + " WHERE from_date >= :startDate AND from_date >= :endDate";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("title", StringType.INSTANCE)
                    .addScalar("start", TimestampType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("icon", StringType.INSTANCE)
                    .addScalar("color", StringType.INSTANCE)
                    .addScalar("end", TimestampType.INSTANCE)
                    .addScalar("id", LongType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(FullCalendar.class));
            query.setString("startDate", startDate);
            query.setString("endDate", endDate);
            List<FullCalendar> plansDTOs = query.list();
            plansDTOs.stream().forEach((item) -> {
                if (item.getStart().compareTo(item.getEnd()) == 0) {
                    item.setEnd(null);
                }
                item.setClassName(item.getColor() != null ? item.getColor().split(" ") : null);
            });
            return plansDTOs;
        } catch (Exception ex) {
            Logger.getLogger(TblNewsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }
    
    @Transactional
    public List<FullCalendar> getData(TblPlansDTO newsDTO) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT plan_id id,content description, type icon, user_id, to_date end, "
                    + "from_date start, title title, color color "
                    + "FROM TBL_PLANS WHERE user_id = :userId AND YEAR(from_date) = :year AND MONTH(from_date) = :month";

            Query query = getSession().createSQLQuery(sql)
                    .addScalar("title", StringType.INSTANCE)
                    .addScalar("start", TimestampType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("icon", StringType.INSTANCE)
                    .addScalar("color", StringType.INSTANCE)
                    .addScalar("end", TimestampType.INSTANCE)
                    .addScalar("id", LongType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(FullCalendar.class));
            query.setLong("userId", newsDTO.getUserId());
            query.setInteger("year", newsDTO.getYear());
            query.setInteger("month", newsDTO.getMonth());
            List<FullCalendar> plansDTOs = query.list();
            plansDTOs.stream().forEach((item) -> {
                if (item.getStart().compareTo(item.getEnd()) == 0) {
                    item.setEnd(null);
                }
                item.setClassName(item.getColor() != null ? item.getColor().split(" ") : null);
            });
            getSession().getTransaction().commit();
            return plansDTOs;
        } catch (Exception ex) {
            Logger.getLogger(TblNewsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public List<TblPlansDTO> getAll(TblPlansDTO newsDTO, Integer offset, Integer limit) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT A.new_id newId, A.title title, A.content content, A.user_created userCreated"
                    + ", A.date_created dateCreated, A.topic_id topicId, D.dvs_name topicName, U.full_name fullname "
                    + "FROM TBL_NEWS A left join tbl_division D on A.topic_id = D.dvs_value AND D.dvs_group = :dvsGroup "
                    + "left join tbl_users U on A.user_created = U.user_id";

            Query query = getSession().createSQLQuery(sql)
                    .addScalar("newId", LongType.INSTANCE)
                    .addScalar("title", StringType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblNewsDTO.class));
            query.setParameter("dvsGroup", DivisionConstant.SUBJECT);

            query.setFirstResult(offset);
            query.setMaxResults(limit);
            List<TblPlansDTO> plansDTOs = query.list();
            getSession().getTransaction().commit();
            return plansDTOs;
        } catch (Exception ex) {
            Logger.getLogger(TblNewsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public Integer getTotalRow(TblPlansDTO newsDTO) {
        try {
            getSession().beginTransaction();
            String sql = "select count(new_id) from TBL_NEWS  WHERE 1 = 1 ";
            Query query = getSession().createSQLQuery(sql);
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
            String hql = "DELETE FROM TblPlans WHERE planId = :planId";
            Query query = getSession().createQuery(hql);
            query.setParameter("planId", id);
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

    public TblPlans findById(Long newId) {
        try {
            getSession().beginTransaction();
            Criteria cr = getSession().createCriteria(TblPlans.class);
            cr.add(Restrictions.eq("planId", newId));
            TblPlans plans = (TblPlans) cr.uniqueResult();
            getSession().getTransaction().commit();
            return plans;
        } catch (Exception ex) {
            Logger.getLogger(TblNewsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }
}
