/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dao;

import com.ptk.elearning.bo.ExamResult;
import com.ptk.elearning.dto.ExamResultDTO;
import com.ptk.elearning.util.DAOUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;


@Repository("examResultDAO")
public class ExamResultDAO extends DAOUtil<ExamResult> {

    public Double avgScore(int year, int month, int subject, int userId) {
        try {
            getSession().beginTransaction();
            String sql = ""
                    + " SELECT AVG(A.score) AS tb"
                    + " FROM exam_result A"
                    + "     INNER JOIN tbl_exam B ON A.exam_id = B.exam_id"
                    + "     INNER JOIN tbl_division C ON C.dvs_value = B.subject_id"
                    + " WHERE C.dvs_group = '002'"
                    + "	    AND B.subject_id = :subject"
                    + "     AND A.exam_year = :year";
            if (month > 0) {
                sql += "     AND A.exam_month = :month";
            }
            if (userId > 0) {
                sql += "     AND A.user_id = :userId";
            }
            sql += " GROUP BY A.exam_month, A.exam_year, C.dvs_name;";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("year", year);
            query.setParameter("subject", subject);
            if (month > 0) {
                query.setParameter("month", month);
            }
            if (userId > 0) {
                query.setParameter("userId", userId);
            }
            Double avg = 0.0;
            if (query.uniqueResult() != null) {
                avg = (Double) query.uniqueResult();
            }
            return avg;
        } catch (Exception ex) {
            Logger.getLogger(ExamResultDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0.0;
        } finally {
            getSession().close();
        }
    }

    public List<ExamResultDTO> getAllResultOfUser(Long userId, Integer limit, Integer offset) {
        try {
            getSession().beginTransaction();
            List<ExamResultDTO> lstExamResultDTOs = new ArrayList<>();
            String sql = "  SELECT B.name examName, C.dvs_name subjectName"
                    + ", A.score score"
                    + ", A.date_of_test dateOfTestString "
                    + ", D.full_name fullName"
                    + " FROM exam_result A"
                    + " INNER JOIN tbl_exam B ON A.exam_id = B.exam_id"
                    + " INNER JOIN tbl_division C ON C.dvs_value = B.subject_id"
                    + " INNER JOIN tbl_users D ON D.user_id = :userId"
                    + " WHERE C.dvs_group = '002'";

            Query query = getSession().createSQLQuery(sql)
                    .addScalar("examName", StringType.INSTANCE)
                    .addScalar("subjectName", StringType.INSTANCE)
                    .addScalar("score", DoubleType.INSTANCE)
                    .addScalar("dateOfTestString", StringType.INSTANCE)
                    .addScalar("fullName", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(ExamResultDTO.class));
            query.setParameter("userId", userId);
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            lstExamResultDTOs = query.list();
            return lstExamResultDTOs;
        } catch (Exception ex) {
            Logger.getLogger(ExamResultDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    public Integer totalResultOfUser(Long userId) {
        try {
            getSession().beginTransaction();
            String sql = "  SELECT count(*)"
                    + " FROM exam_result A"
                    + " INNER JOIN tbl_exam B ON A.exam_id = B.exam_id"
                    + " INNER JOIN tbl_division C ON C.dvs_value = B.subject_id"
                    + " INNER JOIN tbl_users D ON D.user_id = :userId"
                    + " WHERE C.dvs_group = '002'";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("userId", userId);
            int total = ((Number) query.uniqueResult()).intValue();
            getSession().getTransaction().commit();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(ExamResultDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }
}
