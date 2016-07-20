package com.bruce.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.bruce.daoInterface.BaseManagerImpl;
import com.bruce.daoInterface.LogManager;
import com.bruce.model.Log;
import com.bruce.tool.LogType;

@Repository("logManager")
public class LogManagerImpl extends BaseManagerImpl<Log, Long>
	implements LogManager {
    private HibernateTemplate hibernateTemplate;
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void addLog(Log log) {
		this.hibernateTemplate.save(log);  
	}
	
	public Map<String, Object> findLog(String user, long start, long end, int startPage, int max, String type){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		DetachedCriteria dcList = DetachedCriteria.forClass(Log.class);
		
		Date startTimeDate = new Date(start);
		Date endTimeDate = new Date(end);
		if(user!=null&&!user.equals("")){
			dcList.add(Restrictions.eq("username", user));
		}
		
		if(start!=0&&end!=0){
			dcList.add(Restrictions.between("time", startTimeDate, endTimeDate)).addOrder(Order.desc("time"));
		}else if(start!=0){
			dcList.add(Restrictions.ge("time", startTimeDate)).addOrder(Order.desc("time"));
		}else if(end!=0){
			dcList.add(Restrictions.le("time", endTimeDate)).addOrder(Order.desc("time"));
		}
		
		if(type!=null&&!type.equals("")){
			dcList.add(Restrictions.eq("type", LogType.TYPES.get(type)));
		}
		
		List<Log> logList = (List<Log>) hibernateTemplate.findByCriteria(dcList, startPage, max);
		
		dcList.setProjection(Projections.rowCount());
		List<Integer> countList = (List<Integer>) hibernateTemplate.findByCriteria(dcList);
		if(countList!=null&&countList.size()!=0){
			resultMap.put("count", countList.get(0));
			resultMap.put("logs", logList);
		}
		return resultMap;
	}
	
}
