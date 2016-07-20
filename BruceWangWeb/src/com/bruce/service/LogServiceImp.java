package com.bruce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.daoInterface.LogManager;

@Service("logService")
public class LogServiceImp {
	
	@Autowired
	LogManager logManager;

	public LogManager getLogManager() {
		return logManager;
	}

	public void setLogManager(LogManager logManager) {
		this.logManager = logManager;
	}
	
}
