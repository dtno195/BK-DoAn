/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dao;

import com.ptk.elearning.bo.TblExamQuestion;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.util.DAOUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.stereotype.Repository;


@Repository("tblExamQuestionDAO")
public class TblExamQuestionDAO extends DAOUtil<TblExamQuestion> {

    public ErrorResult delete(Long id) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String hql = "DELETE FROM TblExamQuestion WHERE id = :id";
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

    public ErrorResult deleteList(List<Long> ids) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String sql = "DELETE FROM tbl_exam_question WHERE id in :ids";
            Query query = getSession().createSQLQuery(sql);
            query.setParameterList("ids", ids);
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
