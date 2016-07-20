package com.bruce.daoInterface;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dao接口 - Dao基接�?
 */

public interface BaseManager<T, PK extends Serializable> {

	/**
	 * 根据ID获取实体对象.
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T get(PK id);

	/**
	 * 根据ID获取实体对象.
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T load(PK id);

	/**
	 * 根据ID数组获取实体对象集合.
	 * 
	 * @param ids
	 *            ID对象数组
	 * @return 实体对象集合
	 */
	public List<T> get(PK[] ids);

	/**
	 * 根据属�?名和属�?值获取实体对�?
	 * 
	 * @param propertyName
	 *            属�?名称
	 * @param value
	 *            属�?�?
	 * @return 实体对象
	 */
	public T get(String propertyName, Object value);

	/**
	 * 根据属�?名和属�?值获取实体对象集�?
	 * 
	 * @param propertyName
	 *            属�?名称
	 * @param value
	 *            属�?�?
	 * @return 实体对象集合
	 */
	public List<T> getList(String propertyName, Object value);

	/**
	 * 获取�?��实体对象集合.
	 * 
	 * @return 实体对象集合
	 */
	public List<T> getAll();

	/**
	 * 获取�?��实体对象总数.
	 * 
	 * @return 实体对象总数
	 */
	public Long getTotalCount();
	
	/**
	 * 获取�?��实体对象总数.
	 * 
	 * @return 实体对象总数
	 */
	public Long getTotalCountBySql(String sql);

	/**
	 * 根据属�?名�?修改前后属�?值判断在数据库中是否唯一(若新修改的�?与原来�?相等则直接返回true).
	 * 
	 * @param propertyName
	 *            属�?名称
	 * @param oldValue
	 *            修改前的属�?�?
	 * @param oldValue
	 *            修改后的属�?�?
	 * @return boolean
	 */
	public boolean isUnique(String propertyName, Object oldValue, Object newValue);

	/**
	 * 根据属�?名判断数据是否已存在.
	 * 
	 * @param propertyName
	 *            属�?名称
	 * @param value
	 *            �?
	 * @return boolean
	 */
	public boolean isExist(String propertyName, Object value);

	/**
	 * 保存实体对象.
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */

	public PK save(T entity);

	/**
	 * 更新实体对象.
	 * 
	 * @param entity
	 *            对象
	 */
	
	public void update(T entity);

	/**
	 * 删除实体对象.
	 * 
	 * @param entity
	 *            对象
	 * @return
	 */

	public void delete(T entity);

	/**
	 * 根据ID删除实体对象.
	 * 
	 * @param id
	 *            记录ID
	 */
	
	public void delete(PK id);

	/**
	 * 根据ID数组删除实体对象.
	 * 
	 * @param ids
	 *            ID数组
	 */
	
	public void delete(PK[] ids);

	/**
	 * 刷新session.
	 */
	public void flush();

	/**
	 * 清除Session.
	 */
	public void clear();

	/**
	 * 清除某一对象.
	 * 
	 * @param object
	 *            �?��清除的对�?
	 */
	public void evict(Object object);


}