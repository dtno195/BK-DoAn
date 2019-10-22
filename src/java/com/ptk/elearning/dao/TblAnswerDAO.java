/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dao;

import com.ptk.elearning.bo.TblAnswer;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblAnswerDTO;
import com.ptk.elearning.util.DAOUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("tblAnswerDAO")
public class TblAnswerDAO extends DAOUtil<TblAnswer> {

    @Transactional
    public List<TblAnswerDTO> getAll(Long questionId) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT A.answer_id answerId, A.content content, A.question_id questionId,"
                    + " A.priority priority, A.is_true isTrue";
            sql += " FROM TBL_ANSWER A WHERE A.question_id = :questionId";

            Query query = getSession().createSQLQuery(sql)
                    .addScalar("answerId", LongType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .addScalar("questionId", LongType.INSTANCE)
                    .addScalar("priority", IntegerType.INSTANCE)
                    .addScalar("isTrue", BooleanType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblAnswerDTO.class));
            query.setParameter("questionId", questionId);
            List<TblAnswerDTO> tblAnswerDTOs = query.list();
            getSession().getTransaction().commit();
            return tblAnswerDTOs;
        } catch (Exception ex) {
            Logger.getLogger(TblAnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    public ErrorResult delete(Long answerId) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String hql = "DELETE FROM TblAnswer WHERE answerId = :answerId";
            Query query = getSession().createQuery(hql);
            query.setParameter("answerId", answerId);
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

    public TblAnswer getAnswerCorrectOfQuestion(Long questionId) {
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT  ");
        sqlCommand.append("     answer.answer_id answerId ");
        sqlCommand.append(",     answer.content as content ");
        sqlCommand.append(",     answer.is_true as isTrue ");
        sqlCommand.append(",     answer.question_id as questionId ");
        sqlCommand.append(",     answer.priority priority ");
        sqlCommand.append(" FROM tbl_answer answer ");
        sqlCommand.append("    WHERE 1=1  ");
        sqlCommand.append("     AND answer.is_true = true ");
        sqlCommand.append("     AND answer.question_id = :questionId ");
        if (!getSession().getTransaction().isActive()) {
            getSession().getTransaction().begin();
        }
        Query query = getSession().createSQLQuery(sqlCommand.toString())
                .addScalar("answerId", LongType.INSTANCE)
                .addScalar("content", StringType.INSTANCE)
                .addScalar("isTrue", BooleanType.INSTANCE)
                .addScalar("priority", IntegerType.INSTANCE)
                .addScalar("questionId", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(TblAnswer.class));
        query.setParameter("questionId", questionId);
        TblAnswer result = (TblAnswer) query.uniqueResult();
        getSession().getTransaction().commit();
        return result != null ? result : null;
    }

    public TblAnswer getAnswerById(Long anwserId) {
        if (anwserId > 0) {
            StringBuilder sqlCommand = new StringBuilder();
            sqlCommand.append(" SELECT  ");
            sqlCommand.append("     answer.answer_id answerId ");
            sqlCommand.append(",     answer.content as content ");
            sqlCommand.append(",     answer.is_true as isTrue ");
            sqlCommand.append(",     answer.question_id as questionId ");
            sqlCommand.append(",     answer.priority priority ");
            sqlCommand.append(" FROM tbl_answer answer ");
            sqlCommand.append("    WHERE 1=1  ");
            sqlCommand.append("     AND answer.answer_id = :anwserId ");
            if (!getSession().getTransaction().isActive()) {
                getSession().getTransaction().begin();
            }
            Query query = getSession().createSQLQuery(sqlCommand.toString())
                    .addScalar("answerId", LongType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .addScalar("isTrue", BooleanType.INSTANCE)
                    .addScalar("priority", IntegerType.INSTANCE)
                    .addScalar("questionId", LongType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblAnswer.class));
            query.setParameter("anwserId", anwserId);
            TblAnswer result = (TblAnswer) query.uniqueResult();
            getSession().getTransaction().commit();
            return result != null ? result : null;
        }
        return null;
    }
    
}
