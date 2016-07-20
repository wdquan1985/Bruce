package com.bruce.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bruce.daoInterface.LogManager;

@Controller 
public class LogAction {
	
	@Autowired
	private LogManager logManager;
	
	@RequestMapping(value="/log/list")
	public @ResponseBody Map<String, Object> ListUser(HttpServletRequest request){
		String user = request.getParameter("username");
		String type = request.getParameter("type");
		
		Integer max =10;
		if(request.getParameter("max")!=null){
			max = Integer.parseInt(request.getParameter("max"));
		}
			
		Integer startPage = 0;
		if (request.getParameter("startPage")!=null){
			startPage =Integer.parseInt(request.getParameter("startPage"));
		}
		
		Long startTime = 0l;
		if(request.getParameter("startTime")!=null){
			startTime = Long.parseLong(request.getParameter("startTime"));
		}
		
		Long endTime = 0l;
		if(request.getParameter("endTime")!=null){
			endTime = Long.parseLong(request.getParameter("endTime"));
		}
		
		return logManager.findLog(user, startTime, endTime, startPage, max, type);
	}

	public LogManager getLogManager() {
		return logManager;
	}

	public void setLogManager(LogManager logManager) {
		this.logManager = logManager;
	}
	
}
