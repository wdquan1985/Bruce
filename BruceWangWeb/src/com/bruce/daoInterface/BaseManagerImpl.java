package com.bruce.daoInterface;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.bruce.aop.LoggingRequired;
import com.googlecode.ehcache.annotations.TriggersRemove;

/**
 * Dao实现类 - Dao实现类基类
 */

@Repository
public class BaseManagerImpl<T, PK extends Serializable> implements BaseManager<T, PK> {

	private Class<T> entityClass;
	@Autowired
	protected SessionFactory sessionFactory;
	
//	private SessionFactory sessionFactory;
//	
//
//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//	@Resource("sessionFactory")
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	@SuppressWarnings("unchecked")
	public BaseManagerImpl() {
		this.entityClass = null;
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	@Transactional(propagation=Propagation.REQUIRED)
	protected Session getSession() {
		//return sessionFactory.getCurrentSession();	
		//add by bruce
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;

	}

	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.SUPPORTS)
	public T get(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().get(entityClass, id);
	}

	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.SUPPORTS)
	public T load(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Cacheable(value="BaseCache")
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<T> get(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "from " + entityClass.getName() + " as model where model.id in(:ids)";
		return getSession().createQuery(hql).setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.SUPPORTS)
	public T get(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return (T) getSession().createQuery(hql).setParameter(0, value).uniqueResult();
	}

	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<T> getList(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return getSession().createQuery(hql).setParameter(0, value).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<T> getAll() {
		String hql = "from " + entityClass.getName();
		return getSession().createQuery(hql).list();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName();
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(newValue, "newValue is required");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		if (newValue instanceof String) {
			if (oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
				return true;
			}
		}
		T object = get(propertyName, newValue);
		return (object == null);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public boolean isExist(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		T object = get(propertyName, value);
		return (object != null);
	}
	
//	@LoggingRequired
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	@Secured("ROLE_ADMIN")
	public PK save(T entity) {
		Assert.notNull(entity, "entity is required");
		Object obj = getSession().merge(entity);
		return (PK) getSession().save(obj);
	}
	
	@LoggingRequired
	@TriggersRemove(cacheName="BaseCache",removeAll=true) 
	@Transactional(propagation=Propagation.REQUIRED)
	@Secured("ROLE_ADMIN")
	public void update(T entity) {
		Assert.notNull(entity, "entity is required");
		Object obj = getSession().merge(entity);
		getSession().update(obj);
	}
	
	@LoggingRequired
	@TriggersRemove(cacheName="BaseCache",removeAll=true)  
	@Transactional(propagation=Propagation.REQUIRED)
	@Secured("ROLE_ADMIN")
	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
		flush();
	}
	
	@LoggingRequired
	@TriggersRemove(cacheName="BaseCache",removeAll=true)  
	@Transactional(propagation=Propagation.REQUIRED)
	@Secured("ROLE_ADMIN")
	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		T entity = load(id);
		getSession().delete(entity);
	}
	
	@LoggingRequired
	@TriggersRemove(cacheName="BaseCache",removeAll=true)
	@Transactional(propagation=Propagation.REQUIRED)
	@Secured("ROLE_ADMIN")
	public void delete(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = load(id);
			getSession().delete(entity);
		}
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getSession().evict(object);
	}

	@Override
	public Long getTotalCountBySql(String sql) {
		// TODO Auto-generated method stub	
			return Long.valueOf( getSession().createSQLQuery(sql).uniqueResult().toString()).longValue();
	}

}