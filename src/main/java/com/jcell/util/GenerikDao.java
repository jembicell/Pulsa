package com.jcell.util;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public abstract class GenerikDao<T> {
   
	@SuppressWarnings("rawtypes")
	protected Class domainClass;
	
	@Autowired protected SessionFactory sessionFactory;

	@SuppressWarnings("rawtypes")
	public GenerikDao() {
		this.domainClass = (Class) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
    
    public void save(T domain) {
        sessionFactory.getCurrentSession().saveOrUpdate(domain);
    }
    
    @SuppressWarnings("unchecked")
	public T getById(Long id) {
        return (T) sessionFactory.getCurrentSession().get(domainClass, id);
    }
    
    @SuppressWarnings("unchecked")
	public T getById(String id){
        return (T) sessionFactory.getCurrentSession().get(domainClass, id);
    }
    
    public void delete(T domain) {
    	sessionFactory.getCurrentSession().delete(domain);
    }
    
    public Long count() {
        Long x=(Long) sessionFactory.getCurrentSession().createQuery("select count(*) from "+domainClass.getName()).list().get(0);
        return x;
    }
    
    @SuppressWarnings("unchecked")
	public List<T> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from "+ domainClass.getSimpleName()).list();
    }
    
    @SuppressWarnings("unchecked")
	public List<T> getAll(int start, int end) {
    	return  sessionFactory.getCurrentSession().createQuery("from "+ domainClass.getSimpleName()).setFirstResult(start).setMaxResults(end).list();
    }
    
}