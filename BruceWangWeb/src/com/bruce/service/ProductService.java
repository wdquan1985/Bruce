package com.bruce.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bruce.aop.LoggingRequired;
import com.bruce.daoInterface.ProductDao;
import com.bruce.model.PageBean;
import com.bruce.model.Pagination;
import com.bruce.model.Product;
import com.bruce.serviceInterface.ProductServiceInter;


@Service("productService")
public class ProductService implements ProductServiceInter{
	@Autowired
	public ProductDao productDao;
	
//	@LoggingRequired
	@Transactional(propagation=Propagation.REQUIRED)
	public void addUser(Product user){
		System.out.println("22222222222222222: invoke the add crudusers method");
		productDao.save(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteUser(Product user){
		productDao.delete(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteIds(Long[] id){
		productDao.delete(id);
	}
	

	public void deleteId(Long id){
		productDao.delete(id);
	}
	
	public void updateUser(Product product){
		System.out.println("22222222222222222: invoke the updateUser method");
		productDao.update(product);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updatecrudUser(Product user){
		System.out.println("22222222222222222: invoke the updatecrudUser method");
		productDao.updateProduct(user);
	}
	
	public Product searchOne(Long id,String firstName){
		return productDao.searchUni(id, firstName);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Product>  getAll(){
//		return productDao.getAll();
		return productDao.getAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Product getByID(Long ID){
		return productDao.get(ID);
	}
	public long getTotalCount(){
		return productDao.getTotalCount();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Pagination retrieve(PageBean pageBean,String brand) {
		Pagination pagination = new Pagination();
		List<Product> products = productDao.retrieve(pageBean,brand);
		pagination.setRows(products);
		if (brand != null) {
			pagination.setTotal(products.size() + "");
		}else {
			pagination.setTotal(productDao.count()+"");
		}
		return pagination;
	}
	
	@Override
	public Product GetByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
