package com.bruce.dao;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bruce.daoInterface.BaseManagerImpl;
import com.bruce.daoInterface.ProductDao;
import com.bruce.model.PageBean;
import com.bruce.model.Product;


@Component("productDaoImpl")
public class ProductDaoImpl extends BaseManagerImpl<Product, Long> implements ProductDao  {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public Product searchUni(Long id,String firstName){
		Criteria crit = getSession().createCriteria(Product.class);  
	    crit.add(Restrictions.eq("id", id)); 
	    crit.add(Restrictions.eq("firstname", firstName));
	    Product conft = (Product)crit.uniqueResult();  
	    //System.out.println(conft.getId());  
		return conft;
		
	}
	
	//Get a page of data.
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Product> retrieve(PageBean pageBean,String brand) {
		Session s = sessionFactory.openSession();
		try {
			String queryString = "";
			if (brand != null) {
				queryString = "from Product where brand like " + "'%" + brand.trim() + "%'";
			}else {
				queryString = "from Product";				
			}
	        Query queryObject = s.createQuery(queryString).setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
			return queryObject.list();
		} catch (RuntimeException re) {
//			log.error("find all failed", re);
			throw re;
		} finally{  //关闭session，否则网页刷新的时候会卡死，因为开的session太多了。
			if(s!=null){
			s.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public Long count() {
//		log.debug("finding all ConfEmailTemplate instances");
		
		Session s = sessionFactory.openSession();
		try {
			String queryString = "select count(*) from Product";
	        return (Long)s.createQuery(queryString).uniqueResult();
	         
		} catch (RuntimeException re) {
//			log.error("find all failed", re);
			throw re;
		}finally{  //关闭session，否则网页刷新的时候会卡死，因为开的session太多了。
			if(s!=null){
			s.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateProduct(Product product) {
		update(product);
		
//		Session s = sessionFactory.openSession();
//		try {
//			String queryString = "update CrudUsers set firstname='" + product.getFirstname() + "',lastname='" 
//									+ product.getLastname() +"',phone='" + product.getPhone() + "',email='" + product.getEmail() + "' where id=" + cUser.getId();
//			s.createQuery(queryString).executeUpdate();
//	        System.out.println("4444444444:invoke the Dao updateCuser method " + product.getFirstname());
//		} catch (RuntimeException re) {
//			throw re;
//		}finally{  //关闭session，否则网页刷新的时候会卡死，因为开的session太多了。
//			if(s!=null){
//			s.close();
//			}
//		}
	}
	
}
