package com.bruce.serviceInterface;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.bruce.aop.LoggingRequired;
import com.bruce.model.CrudUsers;
import com.bruce.model.PageBean;
import com.bruce.model.Pagination;

public interface CrudUsersServiceInter {
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	//在新增的时候删除缓存。这里是删除 该缓存对象中的所有缓存
	PageBean pageBean = new PageBean();
	@CacheEvict(value="BaseCache",allEntries=true)
	public void addUser(CrudUsers user);
	
	public void deleteUser(CrudUsers user);
	public void updateUser(CrudUsers user);
	
	//测试ehcache成功。
	//@LoggingRequired
	@Cacheable(value="BaseCache")
	public List<CrudUsers>  getAll();
	public CrudUsers getByID(Long ID);
	public long getTotalCount();
	public CrudUsers GetByName(String userName);
	
	//给分页加缓存,自定义key
	@Cacheable(value="BaseCache",key="#pageBean.page")
	public Pagination retrieve(PageBean pageBean);
	
	public void updatecrudUser(CrudUsers user);
	public void deleteIds(Long[] id);
	public void deleteId(Long id);
	public CrudUsers searchOne(Long id,String firstName);

}
