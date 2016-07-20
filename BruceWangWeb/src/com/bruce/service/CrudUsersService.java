package com.bruce.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bruce.daoInterface.CrudUsersDao;
import com.bruce.model.CrudUsers;
import com.bruce.model.PageBean;
import com.bruce.model.Pagination;
import com.bruce.serviceInterface.CrudUsersServiceInter;


@Service("crudUsersService")
public class CrudUsersService implements CrudUsersServiceInter{
	@Autowired
	public CrudUsersDao crudUsersDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void addUser(CrudUsers user){
		System.out.println("22222222222222222: invoke the add crudusers method");
		crudUsersDao.save(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteUser(CrudUsers user){
		crudUsersDao.delete(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteIds(Long[] id){
		crudUsersDao.delete(id);
	}
	

	public void deleteId(Long id){
		crudUsersDao.delete(id);
	}
	
	public void updateUser(CrudUsers user){
		System.out.println("22222222222222222: invoke the updateUser method");
		crudUsersDao.update(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updatecrudUser(CrudUsers user){
		System.out.println("22222222222222222: invoke the updatecrudUser method");
		crudUsersDao.updateCuser(user);
	}
	
	public CrudUsers searchOne(Long id,String firstName){
		return crudUsersDao.searchUni(id, firstName);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<CrudUsers>  getAll(){
//		return crudUsersDao.getAll();
		return crudUsersDao.getAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public CrudUsers getByID(Long ID){
		return crudUsersDao.get(ID);
	}
	public long getTotalCount(){
		return crudUsersDao.getTotalCount();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Pagination retrieve(PageBean pageBean) {
		Pagination pagination = new Pagination();
		pagination.setRows(crudUsersDao.retrieve(pageBean));
		pagination.setTotal(crudUsersDao.count()+"");
		return pagination;
	}
	
	@Override
	public CrudUsers GetByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
