
package com.semeru.model.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface InterfaceDAO<T> {
    
    void save(T entity);
    void update(T entity);
    void revome(T entity);
    void marge(T entity);
    T getEntity(Serializable id);
    T getEntityByDetachedCriteria(DetachedCriteria criteria);
    List<T> getEntites();
    List<T> getListByDetachedCriteria(DetachedCriteria criteria);
    
}
