/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.dao;

import com.google.common.base.Strings;
import com.ptk.elearning.bo.TblDivision;
import com.ptk.elearning.common.ErrorResult;
import com.ptk.elearning.dto.TblDivisionDTO;
import com.ptk.elearning.util.DAOUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("tblDivisionDAO")
public class TblDivisionDAO extends DAOUtil<TblDivision> {

    public TblDivision findById(Long dvsId) {
        getSession().beginTransaction();
        Criteria cr = getSession().createCriteria(TblDivision.class);
        cr.add(Restrictions.eq("dvsId", dvsId));
        TblDivision tblUsers = (TblDivision) cr.uniqueResult();
        getSession().getTransaction().commit();
        return tblUsers;
    }

    @Transactional
    public List<TblDivisionDTO> getAll(TblDivisionDTO divisionDTO, Integer offset, Integer limit) {
        getSession().beginTransaction();
//        String sql = "SELECT DISTINCT A.description  description,A.dvs_group dvsGroup ,B.dvs_name dvsParentName FROM TBL_DIVISION A left join TBL_DIVISION B on A.dvs_parent = B.dvs_id GROUP BY A.description ,A.dvs_group ,B.dvs_name";
        String sql = "SELECT DISTINCT A.description  description,A.dvs_group dvsGroup  FROM TBL_DIVISION A ";
        sql += "WHERE LOWER(dvs_group) like :dvsGroupCd AND LOWER(description) like :description GROUP BY A.description ,A.dvs_group";
        Query query = getSession().createSQLQuery(sql)
                //                .addScalar("dvsId", IntegerType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("dvsGroup", StringType.INSTANCE)
                //                .addScalar("dvsParentName", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(TblDivisionDTO.class));
        if (!Strings.isNullOrEmpty(divisionDTO.getDvsGroup())) {
            query.setParameter("dvsGroupCd", "%" + divisionDTO.getDvsGroup().toLowerCase() + "%");
        } else {
            query.setParameter("dvsGroupCd", "%%");
        }
        if (!Strings.isNullOrEmpty(divisionDTO.getDescription())) {
            query.setParameter("description", "%" + divisionDTO.getDescription().toLowerCase() + "%");
        } else {
            query.setParameter("description", "%%");
        }
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        List<TblDivisionDTO> divisionDTOs = query.list();
        getSession().getTransaction().commit();
        return divisionDTOs;
    }

    @Transactional
    public Integer getTotalRow(TblDivisionDTO divisionDTO) {
        getSession().beginTransaction();
        String sql = "select count(DISTINCT dvs_group) from tbl_division  WHERE LOWER(dvs_group) like :dvsGroupCd AND LOWER(description) like :description ";
        Query query = getSession().createSQLQuery(sql);
        if (!Strings.isNullOrEmpty(divisionDTO.getDvsGroup())) {
            query.setParameter("dvsGroupCd", "%" + divisionDTO.getDvsGroup().toLowerCase() + "%");
        } else {
            query.setParameter("dvsGroupCd", "%%");
        }
        if (!Strings.isNullOrEmpty(divisionDTO.getDescription())) {
            query.setParameter("description", "%" + divisionDTO.getDescription().toLowerCase() + "%");
        } else {
            query.setParameter("description", "%%");
        }
        int total = ((Number) query.uniqueResult()).intValue();
        getSession().getTransaction().commit();
        return total;
    }

    public ErrorResult delete(Long id) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String hql = "DELETE FROM TblDivision WHERE dvsId = :dvsId";
            Query query = getSession().createQuery(hql);
            query.setParameter("dvsId", id);
            query.executeUpdate();
            result.setHasError(false);
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            result.setHasError(true);
            result.setError(ex.getMessage());
            result.setErrorType(JDBCConnectionException.class.getSimpleName());
        }
        return result;
    }

    @Transactional
    public List<TblDivisionDTO> getAllParent() {
        getSession().beginTransaction();
        String sql = "SELECT dvs_id dvsId,"
                + "dvs_name dvsName,"
                + "dvs_group dvsGroup,"
                + "dvs_order dvsOrder,"
                + "dvs_value dvsValue,"
                + "description description,"
                + "user_created userCreated,"
                + "dvs_parent dvsParent FROM TBL_DIVISION ORDER BY dvs_name";
        Query query = getSession().createSQLQuery(sql)
                .addScalar("dvsId", IntegerType.INSTANCE)
                .addScalar("dvsName", StringType.INSTANCE)
                .addScalar("dvsGroup", StringType.INSTANCE)
                .addScalar("dvsOrder", IntegerType.INSTANCE)
                .addScalar("dvsValue", IntegerType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("userCreated", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(TblDivisionDTO.class));
        List<TblDivisionDTO> divisionDTOs = query.list();
        getSession().getTransaction().commit();
        return divisionDTOs;
    }

    @Transactional
    public List<TblDivisionDTO> getChildById(String dvsGroup) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT dvs_id dvsId,"
                    + "dvs_name dvsName,"
                    + "dvs_group dvsGroup,"
                    + "dvs_order dvsOrder,"
                    + "dvs_value dvsValue,"
                    + "description description,"
                    + "user_created userCreated,"
                    + "dvs_parent dvsParent FROM TBL_DIVISION WHERE dvs_group = :dvsId ORDER BY dvs_name";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("dvsId", IntegerType.INSTANCE)
                    .addScalar("dvsName", StringType.INSTANCE)
                    .addScalar("dvsGroup", StringType.INSTANCE)
                    .addScalar("dvsOrder", IntegerType.INSTANCE)
                    .addScalar("dvsValue", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("userCreated", LongType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblDivisionDTO.class));
            query.setParameter("dvsId", dvsGroup);
            List<TblDivisionDTO> divisionDTOs = query.list();
            getSession().getTransaction().commit();
            return divisionDTOs;
        } catch (Exception ex) {
            Logger.getLogger(TblDivisionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public List<TblDivisionDTO> getChildById(Integer dvsId) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT dvs_id dvsId,"
                    + "dvs_name dvsName,"
                    + "dvs_group dvsGroup,"
                    + "dvs_order dvsOrder,"
                    + "dvs_value dvsValue,"
                    + "description description,"
                    + "user_created userCreated,"
                    + "dvs_parent dvsParent FROM TBL_DIVISION WHERE dvs_parent = :dvsId ORDER BY dvs_name";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("dvsId", IntegerType.INSTANCE)
                    .addScalar("dvsName", StringType.INSTANCE)
                    .addScalar("dvsGroup", StringType.INSTANCE)
                    .addScalar("dvsOrder", IntegerType.INSTANCE)
                    .addScalar("dvsValue", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("userCreated", LongType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblDivisionDTO.class));
            query.setParameter("dvsId", dvsId);
            List<TblDivisionDTO> divisionDTOs = query.list();
            getSession().getTransaction().commit();
            return divisionDTOs;
        } catch (Exception ex) {
            Logger.getLogger(TblDivisionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    public ErrorResult deleteChild(Integer id) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String hql = "DELETE FROM TblDivision WHERE dvsId = :dvsId";
            Query query = getSession().createQuery(hql);
            query.setParameter("dvsId", id);
            query.executeUpdate();
            result.setHasError(false);
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            result.setHasError(true);
            result.setError(ex.getMessage());
            result.setErrorType(JDBCConnectionException.class.getSimpleName());
        } finally {
            getSession().close();
        }
        return result;
    }

    public TblDivision findByDvsGroup(String dvsGroup) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT dvs_id dvsId,"
                    + "dvs_name dvsName,"
                    + "dvs_group dvsGroup,"
                    + "dvs_order dvsOrder,"
                    + "dvs_value dvsValue,"
                    + "description description,"
                    + "user_created userCreated,"
                    + "dvs_parent dvsParent FROM TBL_DIVISION WHERE dvs_group = :dvsGroup ORDER BY dvs_name";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("dvsId", LongType.INSTANCE)
                    .addScalar("dvsName", StringType.INSTANCE)
                    .addScalar("dvsGroup", StringType.INSTANCE)
                    .addScalar("dvsOrder", IntegerType.INSTANCE)
                    .addScalar("dvsValue", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("userCreated", LongType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblDivision.class));
            query.setParameter("dvsGroup", dvsGroup);
            List<TblDivision> divisionDTOs = query.list();
            getSession().getTransaction().commit();
            return divisionDTOs.isEmpty() ? null : divisionDTOs.get(0);
        } catch (Exception ex) {
            Logger.getLogger(TblDivisionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    public ErrorResult delete(String dvsGroup) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            String hql = "DELETE FROM TblDivision WHERE dvsGroup = :dvsGroup";
            Query query = getSession().createQuery(hql);
            query.setParameter("dvsGroup", dvsGroup);
            query.executeUpdate();
            result.setHasError(false);
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            result.setHasError(true);
            result.setError(ex.getMessage());
            result.setErrorType(JDBCConnectionException.class.getSimpleName());
        } finally {
            getSession().close();
        }
        return result;
    }

    @Transactional
    public List<TblDivisionDTO> getDataByDvsGroup(String dvsGroup) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT dvs_id dvsId,"
                    + "dvs_name dvsName,"
                    + "dvs_group dvsGroup,"
                    + "dvs_order dvsOrder,"
                    + "dvs_value dvsValue,"
                    + "description description,"
                    + "user_created userCreated,"
                    + "dvs_parent dvsParent FROM TBL_DIVISION WHERE dvs_group = :dvsId ORDER BY dvs_order";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("dvsId", IntegerType.INSTANCE)
                    .addScalar("dvsName", StringType.INSTANCE)
                    .addScalar("dvsGroup", StringType.INSTANCE)
                    .addScalar("dvsOrder", IntegerType.INSTANCE)
                    .addScalar("dvsValue", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("userCreated", LongType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblDivisionDTO.class));
            query.setParameter("dvsId", dvsGroup);
            List<TblDivisionDTO> divisionDTOs = query.list();
            getSession().getTransaction().commit();
            return divisionDTOs;
        } catch (Exception ex) {
            Logger.getLogger(TblDivisionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public TblDivision findByName(String dvsName, String dvsGroup) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT dvs_id dvsId,"
                    + "dvs_name dvsName,"
                    + "dvs_group dvsGroup,"
                    + "dvs_order dvsOrder,"
                    + "dvs_value dvsValue,"
                    + "description description,"
                    + "user_created userCreated,"
                    + "dvs_parent dvsParent FROM TBL_DIVISION WHERE dvs_name =:dvsName AND dvs_group = :dvsGroup ORDER BY dvs_name";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("dvsId", IntegerType.INSTANCE)
                    .addScalar("dvsName", StringType.INSTANCE)
                    .addScalar("dvsGroup", StringType.INSTANCE)
                    .addScalar("dvsOrder", IntegerType.INSTANCE)
                    .addScalar("dvsValue", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("userCreated", LongType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblDivision.class));
            query.setParameter("dvsName", dvsName);
            query.setParameter("dvsGroup", dvsGroup);
            List<TblDivision> divisionDTOs = query.list();
            getSession().getTransaction().commit();
            return divisionDTOs.isEmpty() ? null : divisionDTOs.get(0);
        } catch (Exception ex) {
            Logger.getLogger(TblDivisionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public TblDivision findByValue(Integer dvsValue, String dvsGroup) {
        try {
            getSession().beginTransaction();
            String sql = "SELECT dvs_id dvsId,"
                    + "dvs_name dvsName,"
                    + "dvs_group dvsGroup,"
                    + "dvs_order dvsOrder,"
                    + "dvs_value dvsValue,"
                    + "description description,"
                    + "user_created userCreated,"
                    + "dvs_parent dvsParent FROM TBL_DIVISION WHERE dvs_value =:dvsValue AND dvs_group = :dvsGroup ORDER BY dvs_name";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("dvsId", LongType.INSTANCE)
                    .addScalar("dvsName", StringType.INSTANCE)
                    .addScalar("dvsGroup", StringType.INSTANCE)
                    .addScalar("dvsOrder", IntegerType.INSTANCE)
                    .addScalar("dvsValue", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("userCreated", LongType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(TblDivision.class));
            query.setParameter("dvsValue", dvsValue);
            query.setParameter("dvsGroup", dvsGroup);
            List<TblDivision> divisionDTOs = query.list();
            getSession().getTransaction().commit();
            return divisionDTOs.isEmpty() ? null : divisionDTOs.get(0);
        } catch (Exception ex) {
            Logger.getLogger(TblDivisionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            getSession().close();
        }
    }
}
