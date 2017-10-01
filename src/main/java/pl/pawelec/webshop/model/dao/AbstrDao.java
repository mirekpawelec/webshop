/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author mirek
 */
public abstract class AbstrDao<T extends Object> implements Dao<T>{
    
    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> domainClass;
    
    protected EntityManager getEntityManager(){
        return this.entityManager;
    }
    private Class<T> getDomainClass(){
        if(domainClass==null){
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            domainClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        }
        return domainClass;
    }
    private String getDomainName(){
        return getDomainClass().getName();
    }
    private T find(Serializable id){
        return getEntityManager().find(getDomainClass(), id);
    }
    public void create(T entity){
        getEntityManager().persist(entity);
    }
    public void update(T entity){
        getEntityManager().merge(entity);
    }
    public void delete(T entity){
        getEntityManager().remove(entity);
    }
    public void deleteById(Serializable id){
        T deleteItem = find(id);
        if(deleteItem!=null) getEntityManager().remove(deleteItem);
    }
    public void deleteAll(){
        getEntityManager().createQuery("DELETE FROM " + getDomainName()).executeUpdate();
    }
    public T getOneById(Serializable id){
        //return (T) getEntityManager().createQuery("from " + getDomainName() + " where id = :id").setParameter("id", Id).getSingleResult();    
        return find(id);
    }
    public List<T> getAll(){
        return getEntityManager().createQuery("FROM " + getDomainName()).getResultList();
    }
    public Long count(){
        return (Long) getEntityManager().createQuery("SELECT count(*) FROM " + getDomainName()).getSingleResult();
    }
    public boolean exists(Serializable id){
        return (find(id) != null);
    }
}
