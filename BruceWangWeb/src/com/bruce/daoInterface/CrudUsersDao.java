package com.bruce.daoInterface;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.bruce.model.CrudUsers;
import com.bruce.model.PageBean;



public interface CrudUsersDao extends BaseManager<CrudUsers, Long>{
	//@Cacheable(value="BaseCache")
	public List<CrudUsers> retrieve(PageBean pageBean);
	public Long count();
	public void updateCuser(CrudUsers cUser);
	public CrudUsers searchUni(Long id,String firstName);

}
