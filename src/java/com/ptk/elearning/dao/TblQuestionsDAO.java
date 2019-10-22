/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dao;

import com.google.common.base.Strings;
import com.ptk.elearning.bo.TblQuestions;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.ResultOfExam;
import com.ptk.elearning.dto.TblQuestionsDTO;
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


@Repository("tblQuestionsDAO")
public class TblQuestionsDAO extends DAOUtil<TblQuestions> {

    @Transactional
    public List<TblQuestionsDTO> getAll(TblQuestionsDTO questionsDTO, Integer offset, Integer limit) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT Q.question_id questionId, Q.content content, Q.subject_id subjectId,"
                    + " Q.topic_id topicId, Q.level_id levelId, Q.date_created dateCreated,";
            sql += "    D1.dvs_name subjectName,  D3.dvs_name levelName, T.id examQuestionId";
            sql += " FROM TBL_QUESTIONS Q left join TBL_DIVISION D1 on Q.subject_id = D1.dvs_value AND D1.dvs_group = :subject ";
            sql += " left join TBL_DIVISION D3 on Q.level_id = D3.dvs_value AND D3.dvs_group = :level ";
            sql += "    left join tbl_exam_question T on Q.question_id = T.question_id";
            sql += " WHERE 1 = 1 ";
            if (!Strings.isNullOrEmpty(questionsDTO.getContent())) {
                sql += "    AND Q.content like :content";
            }
            if (questionsDTO.getLevelId() != null && questionsDTO.getLevelId() > 0) {
                sql += "    AND Q.level_id = :levelId";
            }
            if (questionsDTO.getSubjectId() != null && questionsDTO.getSubjectId() > 0) {
                sql += "    AND Q.subject_id = :subjectId";
            }
            if (questionsDTO.getExamId() != null && questionsDTO.getExamId() > 0) {
                sql += "    AND Q.question_id not in (select question_id from tbl_exam_question where exam_id = :examId)";
            }
            sql += "    GROUP BY Q.question_id";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("questionId", LongType.INSTANCE)
                    .addScalar("examQuestionId", LongType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .addScalar("subjectId", IntegerType.INSTANCE)
                    .addScalar("topicId", IntegerType.INSTANCE)
                    .addScalar("dateCreated", DateType.INSTANCE)
                    .addScalar("levelId", IntegerType.INSTANCE)
                    .addScalar("subjectName", StringType.INSTANCE)
                    .addScalar("levelName", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblQuestionsDTO.class));
            query.setParameter("subject", DivisionConstant.SUBJECT);
            query.setParameter("level", DivisionConstant.LEVEL_ID);
            if (!Strings.isNullOrEmpty(questionsDTO.getContent())) {
                query.setParameter("content", "%" + questionsDTO.getContent() + "%");
            }
            if (questionsDTO.getLevelId() != null && questionsDTO.getLevelId() > 0) {
                query.setParameter("levelId", questionsDTO.getLevelId());
            }
            if (questionsDTO.getSubjectId() != null && questionsDTO.getSubjectId() > 0) {
                query.setParameter("subjectId", questionsDTO.getSubjectId());
            }
            if (questionsDTO.getExamId() != null && questionsDTO.getExamId() > 0) {
                query.setParameter("examId", questionsDTO.getExamId());
            }
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            List<TblQuestionsDTO> tblQuestionsDTOs = query.list();
            getSession().getTransaction().commit();
            return tblQuestionsDTOs;
        } catch (Exception ex) {
            Logger.getLogger(TblQuestionsDAO.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public Integer getTotalRow(TblQuestionsDTO questionsDTO) {
        try {
            getSession().beginTransaction();
            String sql = "select count(*) from TBL_QUESTIONS Q WHERE 1 = 1 ";
            if (!Strings.isNullOrEmpty(questionsDTO.getContent())) {
                sql += "    AND Q.content like :content";
            }
            if (questionsDTO.getLevelId() != null && questionsDTO.getLevelId() > 0) {
                sql += "    AND Q.level_id = :levelId";
            }
            if (questionsDTO.getSubjectId() != null && questionsDTO.getSubjectId() > 0) {
                sql += "    AND Q.subject_id = :subjectId";
            }
            if (questionsDTO.getExamId() != null && questionsDTO.getExamId() > 0) {
                sql += "    AND Q.question_id not in (select question_id from tbl_exam_question where exam_id = :examId)";
            }
            Query query = getSession().createSQLQuery(sql);
            if (!Strings.isNullOrEmpty(questionsDTO.getContent())) {
                query.setParameter("content", "%" + questionsDTO.getContent() + "%");
            }
            if (questionsDTO.getLevelId() != null && questionsDTO.getLevelId() > 0) {
                query.setParameter("levelId", questionsDTO.getLevelId());
            }
            if (questionsDTO.getSubjectId() != null && questionsDTO.getSubjectId() > 0) {
                query.setParameter("subjectId", questionsDTO.getSubjectId());
            }
            if (questionsDTO.getExamId() != null && questionsDTO.getExamId() > 0) {
                query.setParameter("examId", questionsDTO.getExamId());
            }
            int total = ((Number) query.uniqueResult()).intValue();
            getSession().getTransaction().commit();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(TblQuestionsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            getSession().close();
        }
    }

    public ErrorResult delete(Long questionId) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String hql = "DELETE FROM TblQuestions WHERE questionId = :questionId";
            Query query = getSession().createQuery(hql);
            query.setParameter("questionId", questionId);
            query.executeUpdate();
            hql = "DELETE FROM TblAnswer WHERE questionId = :questionId";
            query = getSession().createQuery(hql);
            query.setParameter("questionId", questionId);
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

    public TblQuestions findById(Long questionId) {
        try {
            getSession().beginTransaction();
            Criteria cr = getSession().createCriteria(TblQuestions.class);
            cr.add(Restrictions.eq("questionId", questionId));
            TblQuestions tblQuestions = (TblQuestions) cr.uniqueResult();
            getSession().getTransaction().commit();
            return tblQuestions;
        } catch (Exception ex) {
            Logger.getLogger(TblQuestionsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public TblQuestionsDTO getDTOById(Long questionId) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT Q.question_id questionId, Q.content content, Q.subject_id subjectId,"
                    + " Q.topic_id topicId, Q.level_id levelId, Q.date_created dateCreated";
            sql += " FROM TBL_QUESTIONS Q WHERE Q.question_id = :questionId";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("questionId", LongType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .addScalar("subjectId", IntegerType.INSTANCE)
                    .addScalar("topicId", IntegerType.INSTANCE)
                    .addScalar("dateCreated", DateType.INSTANCE)
                    .addScalar("levelId", IntegerType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblQuestionsDTO.class));
            query.setParameter("questionId", questionId);
            List<TblQuestionsDTO> tblExamDTOs = query.list();
            getSession().getTransaction().commit();
            return tblExamDTOs.isEmpty() ? null : tblExamDTOs.get(0);
        } catch (Exception ex) {
            Logger.getLogger(TblQuestionsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public List<TblQuestionsDTO> getSelectedQuestion(TblQuestionsDTO questionsDTO, Integer offset, Integer limit) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT Q.question_id questionId, Q.content content, Q.subject_id subjectId,"
                    + " Q.topic_id topicId, Q.level_id levelId, Q.date_created dateCreated,";
            sql += "    D1.dvs_name subjectName,  D3.dvs_name levelName, T.id examQuestionId";
            sql += " FROM TBL_QUESTIONS Q left join TBL_DIVISION D1 on Q.subject_id = D1.dvs_value AND D1.dvs_group = :subject ";
            sql += " left join TBL_DIVISION D3 on Q.level_id = D3.dvs_value AND D3.dvs_group = :level ";
            sql += "    left join tbl_exam_question T on Q.question_id = T.question_id";
            sql += " WHERE 1 = 1 ";
            if (questionsDTO.getExamId() != null && questionsDTO.getExamId() > 0) {
                sql += "    AND Q.question_id in (select question_id from tbl_exam_question where exam_id = :examId) AND T.exam_id  = :examId";
            }
            if (!Strings.isNullOrEmpty(questionsDTO.getContent())) {
                sql += "    AND Q.content like :content";
            }
            if (questionsDTO.getLevelId() != null && questionsDTO.getLevelId() > 0) {
                sql += "    AND Q.level_id = :levelId";
            }
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("questionId", LongType.INSTANCE)
                    .addScalar("examQuestionId", LongType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .addScalar("subjectId", IntegerType.INSTANCE)
                    .addScalar("topicId", IntegerType.INSTANCE)
                    .addScalar("dateCreated", DateType.INSTANCE)
                    .addScalar("levelId", IntegerType.INSTANCE)
                    .addScalar("subjectName", StringType.INSTANCE)
                    .addScalar("levelName", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblQuestionsDTO.class));
            query.setParameter("subject", DivisionConstant.SUBJECT);
            query.setParameter("level", DivisionConstant.LEVEL_ID);
//            if (questionsDTO.getSubjectId() != null && questionsDTO.getSubjectId() > 0) {
//                query.setParameter("subjectId", questionsDTO.getSubjectId());
//            }
            if (questionsDTO.getExamId() != null && questionsDTO.getExamId() > 0) {
                query.setParameter("examId", questionsDTO.getExamId());
            }
            if (!Strings.isNullOrEmpty(questionsDTO.getContent())) {
                query.setParameter("content", "%" + questionsDTO.getContent() + "%");
            }
            if (questionsDTO.getLevelId() != null && questionsDTO.getLevelId() > 0) {
                query.setParameter("levelId", questionsDTO.getLevelId());
            }
            if (offset > 0 && limit > 0) {
                query.setFirstResult(offset);
                query.setMaxResults(limit);
            }
            List<TblQuestionsDTO> tblQuestionsDTOs = query.list();
            getSession().getTransaction().commit();
            return tblQuestionsDTOs;
        } catch (Exception ex) {
            Logger.getLogger(TblQuestionsDAO.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public Integer getTotalRowSelected(TblQuestionsDTO questionsDTO) {
        try {
            getSession().beginTransaction();
            String sql = "select count(*) from TBL_QUESTIONS Q   left join tbl_exam_question T on Q.question_id = T.question_id WHERE 1 = 1 ";

            if (questionsDTO.getExamId() != null && questionsDTO.getExamId() > 0) {
                sql += "    AND Q.question_id in (select question_id from tbl_exam_question where exam_id = :examId )  ";
            }
            if (!Strings.isNullOrEmpty(questionsDTO.getContent())) {
                sql += "    AND Q.content like :content";
            }
            if (questionsDTO.getLevelId() != null && questionsDTO.getLevelId() > 0) {
                sql += "    AND Q.level_id = :levelId";
            }
            Query query = getSession().createSQLQuery(sql);
            if (questionsDTO.getExamId() != null && questionsDTO.getExamId() > 0) {
                query.setParameter("examId", questionsDTO.getExamId());
            }
            if (!Strings.isNullOrEmpty(questionsDTO.getContent())) {
                query.setParameter("content", "%" + questionsDTO.getContent() + "%");
            }
            if (questionsDTO.getLevelId() != null && questionsDTO.getLevelId() > 0) {
                query.setParameter("levelId", questionsDTO.getLevelId());
            }
            int total = ((Number) query.uniqueResult()).intValue();
            getSession().getTransaction().commit();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(TblQuestionsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public List<TblQuestionsDTO> getDataByExamId(Long examId) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT Q.question_id questionId, Q.content content, Q.subject_id subjectId,"
                    + " Q.topic_id topicId, Q.level_id levelId, Q.date_created dateCreated,Q.exam_id examId";
            sql += " FROM TBL_QUESTIONS Q ";
            sql += " WHERE Q.question_id IN (SELECT B.question_id FROM tbl_exam_question B WHERE B.exam_id = :examId) ";

            Query query = getSession().createSQLQuery(sql)
                    .addScalar("questionId", LongType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .addScalar("subjectId", IntegerType.INSTANCE)
                    .addScalar("topicId", IntegerType.INSTANCE)
                    .addScalar("dateCreated", DateType.INSTANCE)
                    .addScalar("levelId", IntegerType.INSTANCE)
                    .addScalar("examId", LongType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblQuestionsDTO.class));
            query.setParameter("examId", examId);

            List<TblQuestionsDTO> tblQuestionsDTOs = query.list();
            getSession().getTransaction().commit();
            return tblQuestionsDTOs;
        } catch (Exception ex) {
            Logger.getLogger(TblQuestionsDAO.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public List<ResultOfExam> getAllAnswerOfQuestionByExamId(Long examId) {
        try {
            getSession().beginTransaction();
            String sql = "  SELECT A.question_id questionId, C.answer_id answerId, A.content AS questionContent, C.content AS answerContent "
                    + " FROM tbl_questions A "
                    + " INNER JOIN tbl_answer C ON C.question_id = A.question_id and C.is_true = 1"
                    + " WHERE A.question_id IN (SELECT B.question_id FROM tbl_exam_question B WHERE B.exam_id = :examId)";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("answerId", LongType.INSTANCE)
                    .addScalar("questionId", LongType.INSTANCE)
                    .addScalar("questionContent", StringType.INSTANCE)
                    .addScalar("answerContent", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(ResultOfExam.class));
            query.setParameter("examId", examId);
            List<ResultOfExam> lstResultOfExams = query.list();
            getSession().getTransaction().commit();
            return lstResultOfExams;
        } catch (Exception ex) {
            Logger.getLogger(TblQuestionsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }
    
    public Integer totalQuestionOfExam(Long examId) {
        try {
            getSession().beginTransaction();
            String sql = "  SELECT count(*) "
                    + " FROM tbl_questions A "
                    + " INNER JOIN tbl_answer C ON C.question_id = A.question_id and C.is_true = 1"
                    + " WHERE A.question_id IN (SELECT B.question_id FROM tbl_exam_question B WHERE B.exam_id = :examId)";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("examId", examId);
            Integer total = ((Number) query.uniqueResult()).intValue();
            getSession().getTransaction().commit();
            return total;
        } catch (Exception ex) {
            Logger.getLogger(TblQuestionsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }
    
    public ErrorResult deleteList(List<Long> questionId) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String hql = "DELETE FROM TblQuestions WHERE questionId IN (:questionId)";
            Query query = getSession().createQuery(hql);
            query.setParameterList("questionId", questionId);
            query.executeUpdate();
            hql = "DELETE FROM TblAnswer WHERE questionId IN (:questionId)";
            query = getSession().createQuery(hql);
            query.setParameterList("questionId", questionId);
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
