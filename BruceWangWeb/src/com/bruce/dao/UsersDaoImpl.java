package com.bruce.dao;

import org.springframework.stereotype.Component;
import com.bruce.daoInterface.BaseManagerImpl;
import com.bruce.daoInterface.UsersDao;
import com.bruce.model.Users;


@Component("usersDaoImpl")
public class UsersDaoImpl extends BaseManagerImpl<Users, Long> implements UsersDao  {
	
//	@Autowired
//	private SessionFactory sessionFactory;
//	
////	@Autowired
////	private HibernateTemplate hibernateTemplate;
//	
//	
//	@SuppressWarnings("unchecked")
//	@Transactional(propagation=Propagation.REQUIRED)
//	public CrudUsers searchUni(Long id,String firstName){
//		Criteria crit = getSession().createCriteria(CrudUsers.class);  
//	    crit.add(Restrictions.eq("id", id)); 
//	    crit.add(Restrictions.eq("firstname", firstName));
//	    CrudUsers conft = (CrudUsers)crit.uniqueResult();  
//	    //System.out.println(conft.getId());  
//		return conft;
//		
//	}
//	
//	//Get a page of data.
//	@SuppressWarnings("unchecked")
//	@Transactional(propagation=Propagation.REQUIRED)
//	public List<CrudUsers> retrieve(PageBean pageBean) {
//		Session s = sessionFactory.openSession();
////		log.debug("finding all ConfEmailTemplate instances");
//		try {
//			String queryString = "from CrudUsers";
//	         Query queryObject = s.createQuery(queryString).setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
//			 return queryObject.list();
//		} catch (RuntimeException re) {
////			log.error("find all failed", re);
//			throw re;
//		} finally{  //关闭session，否则网页刷新的时候会卡死，因为开的session太多了。
//			if(s!=null){
//			s.close();
//			}
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Transactional(propagation=Propagation.REQUIRED)
//	public Long count() {
////		log.debug("finding all ConfEmailTemplate instances");
//		
//		Session s = sessionFactory.openSession();
//		try {
//			String queryString = "select count(*) from CrudUsers";
//	        return (Long)s.createQuery(queryString).uniqueResult();
//	         
//		} catch (RuntimeException re) {
////			log.error("find all failed", re);
//			throw re;
//		}finally{  //关闭session，否则网页刷新的时候会卡死，因为开的session太多了。
//			if(s!=null){
//			s.close();
//			}
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Transactional(propagation=Propagation.REQUIRED)
//	public void updateCuser(CrudUsers cUser) {
////		org.hibernate.Transaction tx = null;
////		try {
////			   tx = this.getSession().beginTransaction();
////			   // 执行修改操作
////			   
////			   this.getSession().update(cUser);
////			   tx.commit();
////			  } catch (Exception e) {
////			   System.out.println("===修改信息出现异常===");
////			   e.printStackTrace();
////			   tx.rollback();
////		} 
//		
//		Session s = sessionFactory.openSession();
//		System.out.println("3333333333:invoke the Dao updateCuser method " + cUser.getFirstname());
//		try {
//			String queryString = "update CrudUsers set firstname='" + cUser.getFirstname() + "',lastname='" 
//									+ cUser.getLastname() +"',phone='" + cUser.getPhone() + "',email='" + cUser.getEmail() + "' where id=" + cUser.getId();
//			s.createQuery(queryString).executeUpdate();
//	        System.out.println("4444444444:invoke the Dao updateCuser method " + cUser.getFirstname());
//		} catch (RuntimeException re) {
////			log.error("find all failed", re);
//			throw re;
//		}finally{  //关闭session，否则网页刷新的时候会卡死，因为开的session太多了。
//			if(s!=null){
//			s.close();
//			}
//		}
//	}
	
}
