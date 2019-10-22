/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.util;

import com.ptk.elearning.common.ErrorResult;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DAOUtil<T> {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        if (session == null || session.equals(null)) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public Session session;

    @Transactional
    public String delete(T obj) {
        try {
            getSession().beginTransaction();
            getSession().delete(obj);
            getSession().getTransaction().commit();
            return "SUCCESS";
        } catch (HibernateException he) {
            Logger.getLogger(DAOUtil.class.getName()).log(Level.SEVERE, null, he);
            return he.getMessage();
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public ErrorResult save(T obj) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            Long id = (Long) getSession().save(obj);
            getSession().getTransaction().commit();
            result.setHasError(false);
            result.setId(id);
            return result;
        } catch (HibernateException he) {
            result.setError(he.getMessage());
            result.setHasError(true);
            Logger.getLogger(DAOUtil.class.getName()).log(Level.SEVERE, null, he);
            return result;
        } finally {
            getSession().close();
        }
    }
    @Transactional
    public Long saveReturnId(T obj) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            Long id = (Long) getSession().save(obj);
            getSession().getTransaction().commit();
            result.setHasError(false);
            result.setId(id);
            return id;
        } catch (HibernateException he) {
            result.setError(he.getMessage());
            result.setHasError(true);
            Logger.getLogger(DAOUtil.class.getName()).log(Level.SEVERE, null, he);
            return 0L;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public ErrorResult saveList(List<T> obj) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            session = getSession();
            for (T item : obj) {
                session.save(item);
            }
            getSession().getTransaction().commit();
            result.setHasError(false);
            return result;
        } catch (HibernateException he) {
            result.setError(he.getMessage());
            result.setHasError(true);
            Logger.getLogger(DAOUtil.class.getName()).log(Level.SEVERE, null, he);
            return result;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public ErrorResult saveListReturnId(List<T> obj) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            session = getSession();
            List<Long> lstId = new ArrayList<Long>();
            for (T item : obj) {
                Long id = (Long) getSession().save(item);
                lstId.add(id);
            }
            result.setLstId(lstId);
            getSession().getTransaction().commit();
            result.setHasError(false);
            return result;
        } catch (HibernateException he) {
            result.setError(he.getMessage());
            result.setHasError(true);
            Logger.getLogger(DAOUtil.class.getName()).log(Level.SEVERE, null, he);
            return result;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public ErrorResult updateList(List<T> obj) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            session = getSession();
            for (T item : obj) {
                session.update(item);
            }
            getSession().getTransaction().commit();
            result.setHasError(false);
            return result;
        } catch (HibernateException he) {
            result.setError(he.getMessage());
            result.setHasError(true);
            Logger.getLogger(DAOUtil.class.getName()).log(Level.SEVERE, null, he);
            return result;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public ErrorResult update(T obj) {
        ErrorResult result = new ErrorResult();
        try {
            getSession().beginTransaction();
            getSession().update(obj);
            getSession().getTransaction().commit();
            result.setHasError(false);
            return result;
        } catch (HibernateException he) {
            result.setError(he.getMessage());
            result.setHasError(true);
            Logger.getLogger(DAOUtil.class.getName()).log(Level.SEVERE, null, he);
            return result;
        } finally {
            getSession().close();
        }
    }

    @Transactional
    public String updateMerge(T obj) {
        try {
            getSession().beginTransaction();
            session.merge(obj);
            getSession().getTransaction().commit();
            return "SUCCESS";
        } catch (HibernateException he) {
            Logger.getLogger(DAOUtil.class.getName()).log(Level.SEVERE, null, he);
            return he.getMessage();
        } finally {
            getSession().close();
        }
    }

}
