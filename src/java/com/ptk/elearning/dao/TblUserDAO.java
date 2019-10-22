/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dao;

import com.google.common.base.Strings;
import com.ptk.elearning.bo.TblUsers;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblUsersDTO;
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
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("tblUserDAO")
public class TblUserDAO extends DAOUtil<TblUsers> {

    public TblUsers getOneAnswerByGid(Long userId) {
        getSession().beginTransaction();
        Criteria cr = getSession().createCriteria(TblUsers.class);
        cr.add(Restrictions.eq("userId", userId));
        TblUsers tblUsers = (TblUsers) cr.uniqueResult();
        getSession().getTransaction().commit();
        return tblUsers;
    }

    @Transactional
    public List<TblUsersDTO> getAll(TblUsersDTO usersDTO, Integer offset, Integer limit) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT E.user_id userId, E.user_name userName, E.full_name fullName,  E.password password,";
            sql += " E.salt salt, E.high_school highSchool, E.date_of_birth dateOfBirth,";
            sql += "E.date_created dateCreated,E.aspiration aspiration, E.role role, E.is_login isLogin";
            sql += ", D.dvs_name roleName, E.is_lock isLock";
            sql += " FROM tbl_users E left join TBL_DIVISION D on E.role = D.dvs_value AND D.dvs_group = :dvsGroup ";
            sql += "    WHERE 1 = 1";
            if (!Strings.isNullOrEmpty(usersDTO.getUserName())) {
                sql += "    AND E.user_name like :userName";
            }
            if (!Strings.isNullOrEmpty(usersDTO.getFullName())) {
                sql += "    AND E.full_name like :fullname";
            }
            if (usersDTO.getRole() != null && usersDTO.getRole() > 0) {
                sql += "    AND E.role = :role";
            }
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("userId", LongType.INSTANCE)
                    .addScalar("userName", StringType.INSTANCE)
                    .addScalar("fullName", StringType.INSTANCE)
                    .addScalar("password", StringType.INSTANCE)
                    .addScalar("salt", StringType.INSTANCE)
                    .addScalar("highSchool", StringType.INSTANCE)
                    .addScalar("dateOfBirth", DateType.INSTANCE)
                    .addScalar("dateCreated", DateType.INSTANCE)
                    .addScalar("aspiration", StringType.INSTANCE)
                    .addScalar("roleName", StringType.INSTANCE)
                    .addScalar("role", IntegerType.INSTANCE)
                    .addScalar("isLogin", IntegerType.INSTANCE)
                    .addScalar("isLock", BooleanType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblUsersDTO.class));
            query.setParameter("dvsGroup", DivisionConstant.ROLE_ID);
            if (!Strings.isNullOrEmpty(usersDTO.getUserName())) {
                query.setParameter("userName", "%" + usersDTO.getUserName().trim() + "%");
            }
            if (!Strings.isNullOrEmpty(usersDTO.getFullName())) {
                query.setParameter("fullname", "%" + usersDTO.getFullName().trim() + "%");
            }
            if (usersDTO.getRole() != null && usersDTO.getRole() > 0) {
                query.setParameter("role", usersDTO.getRole());
            }
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            List<TblUsersDTO> tblUserses = query.list();
            getSession().getTransaction().commit();
            return tblUserses;
        } catch (Exception ex) {
            Logger.getLogger(TblUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    public ErrorResult delete(Long id) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String hql = "DELETE FROM TblUsers WHERE userId = :userId";
            Query query = getSession().createQuery(hql);
            query.setParameter("userId", id);
            query.executeUpdate();
            result.setHasError(false);
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            result.setError(ex.getMessage());
            result.setErrorType(JDBCConnectionException.class.getSimpleName());
        }
        return result;
    }

    @Transactional
    public TblUsers findByUsername(String userName) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT E.user_id userId, E.user_name userName, E.full_name fullName,  E.password password,";
            sql += " E.salt salt, E.high_school highSchool, E.date_of_birth dateOfBirth,";
            sql += "E.date_created dateCreated,E.aspiration aspiration, E.role role, E.is_login isLogin, E.is_lock isLock";
            sql += " FROM tbl_users E WHERE E.user_name = :userName";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("userId", LongType.INSTANCE)
                    .addScalar("userName", StringType.INSTANCE)
                    .addScalar("fullName", StringType.INSTANCE)
                    .addScalar("password", StringType.INSTANCE)
                    .addScalar("salt", StringType.INSTANCE)
                    .addScalar("highSchool", StringType.INSTANCE)
                    .addScalar("dateOfBirth", DateType.INSTANCE)
                    .addScalar("dateCreated", DateType.INSTANCE)
                    .addScalar("aspiration", StringType.INSTANCE)
                    .addScalar("role", IntegerType.INSTANCE)
                    .addScalar("isLock", BooleanType.INSTANCE)
                    .addScalar("isLogin", IntegerType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblUsers.class));
            query.setParameter("userName", userName);

            List<TblUsers> tblUserses = query.list();
            getSession().getTransaction().commit();
            return tblUserses.isEmpty() ? null : tblUserses.get(0);
        } catch (Exception ex) {
            Logger.getLogger(TblExamDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public Integer getTotalRow(TblUsersDTO usersDTO) {
        try {
            getSession().beginTransaction();
            String sql = "select count(*) from TBL_USERS E WHERE 1 = 1 ";
            if (!Strings.isNullOrEmpty(usersDTO.getUserName())) {
                sql += "    AND E.user_name like :userName";
            }
            if (!Strings.isNullOrEmpty(usersDTO.getFullName())) {
                sql += "    AND E.full_name like :fullname";
            }
            if (usersDTO.getRole() != null && usersDTO.getRole() > 0) {
                sql += "    AND E.role = :role";
            }
            Query query = getSession().createSQLQuery(sql);
            if (!Strings.isNullOrEmpty(usersDTO.getUserName())) {
                query.setParameter("userName", "%" + usersDTO.getUserName().trim() + "%");
            }
            if (!Strings.isNullOrEmpty(usersDTO.getFullName())) {
                query.setParameter("fullname", "%" + usersDTO.getFullName().trim() + "%");
            }
            if (usersDTO.getRole() != null && usersDTO.getRole() > 0) {
                query.setParameter("role", usersDTO.getRole());
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
}
