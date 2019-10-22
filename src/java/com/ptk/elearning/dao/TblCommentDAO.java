/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dao;

import com.ptk.elearning.bo.TblComment;
import com.ptk.elearning.dto.TblCommentDTO;
import com.ptk.elearning.util.DAOUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;


@Repository("tblCommentDAO")
public class TblCommentDAO extends DAOUtil<TblComment> {

    public List<TblCommentDTO> getAll(Long newId) {
        try {
            getSession().beginTransaction();
            String sql = ""
                    + " SELECT"
                    + "      A.comment_id AS commentId"
                    + "     ,A.content AS content"
                    + "     ,A.date_comment AS dateComment"
                    + "     ,B.full_name AS fullName"
                    + " FROM tbl_comment A"
                    + "     INNER JOIN tbl_users B ON A.user_comment = B.user_id"
                    + " WHERE A.new_id = :newId"
                    + " ORDER BY A.date_comment DESC";

            Query query = getSession().createSQLQuery(sql)
                    .addScalar("commentId", LongType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .addScalar("dateComment", TimestampType.INSTANCE)
                    .addScalar("fullName", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblCommentDTO.class));
            query.setParameter("newId", newId);
            List<TblCommentDTO> result = query.list();
//            getSession().getTransaction().commit();
            return result;
        } catch (Exception ex) {
            Logger.getLogger(TblCommentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }
}
