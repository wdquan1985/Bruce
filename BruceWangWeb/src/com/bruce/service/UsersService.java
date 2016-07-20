package com.bruce.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bruce.daoInterface.UsersDao;
import com.bruce.model.PageBean;
import com.bruce.model.Pagination;
import com.bruce.model.Users;
import com.bruce.serviceInterface.UsersServiceInter;
import com.bruce.tool.MyEncoder;
import com.bruce.tool.MySpringEncoder;

//用来在增加用户的时候，给密码就行加密
@Service("usersService")
public class UsersService implements UsersServiceInter{
	@Autowired
	public UsersDao usersDao;
	
	@Autowired
	public MyEncoder myEncoder;
	
	@Autowired
	public MySpringEncoder mySpringEncoder;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void addUser(Users user){
		System.out.println("22222222222222222: invoke the add crudusers method");
		String userName = user.getUsername();
		String password = user.getPassword();
//		password = myEncoder.md5(userName, password);
		password = mySpringEncoder.encodePassword(password, userName);
		System.out.println("the encryped password is:" + password);
		user.setPassword(password);
		usersDao.save(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteUser(Users user){
		usersDao.delete(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteIds(Long[] id){
		usersDao.delete(id);
	}
	

	public void deleteId(Long id){
		usersDao.delete(id);
	}
	
	public void updateUser(Users user){
		System.out.println("22222222222222222: invoke the updateUser method");
		usersDao.update(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Users>  getAll(){
//		return usersDao.getAll();
		return usersDao.getAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Users getByID(Long ID){
		return usersDao.get(ID);
	}
	public long getTotalCount(){
		return usersDao.getTotalCount();
	}
	
	@Override
	public Users GetByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination retrieve(PageBean pageBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatecrudUser(Users user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Users searchOne(Long id, String firstName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
