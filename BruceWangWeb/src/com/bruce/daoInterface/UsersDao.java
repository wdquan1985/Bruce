package com.bruce.daoInterface;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.bruce.model.PageBean;
import com.bruce.model.Users;



public interface UsersDao extends BaseManager<Users, Long>{
	//@Cacheable(value="BaseCache")
}
