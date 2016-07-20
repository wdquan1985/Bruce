package com.bruce.serviceInterface;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.bruce.aop.LoggingRequired;
import com.bruce.model.PageBean;
import com.bruce.model.Pagination;
import com.bruce.model.Product;

public interface ProductServiceInter {
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	//在新增的时候删除缓存。这里是删除 该缓存对象中的所有缓存
	PageBean pageBean = new PageBean();
//	@CacheEvict(value="BaseCache",allEntries=true)
	@LoggingRequired
	public void addUser(Product user);
	
//	@CacheEvict(value="BaseCache",allEntries=true)
	public void deleteUser(Product user);
	
//	@CacheEvict(value="BaseCache",allEntries=true)
	public void updateUser(Product user);
	
	//测试ehcache成功。
	//@LoggingRequired
//	@Cacheable(value="BaseCache")
	public List<Product>  getAll();
	public Product getByID(Long ID);
	public long getTotalCount();
	public Product GetByName(String userName);
	
	//给分页加缓存,自定义key
//	@Cacheable(value="BaseCache",key="#pageBean.page")
	public Pagination retrieve(PageBean pageBean,String brand);
	
	public void updatecrudUser(Product user);
	public void deleteIds(Long[] id);
	public void deleteId(Long id);
	public Product searchOne(Long id,String firstName);

}
