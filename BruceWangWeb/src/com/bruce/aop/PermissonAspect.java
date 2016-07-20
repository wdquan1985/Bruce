package com.bruce.aop;

import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bruce.daoInterface.LogManager;
import com.bruce.model.BaseModel;
import com.bruce.model.Log;
import com.bruce.tool.LogType;

@Aspect
@Order(1)
public class PermissonAspect {

	@Autowired
	private LogManager logManager;

	@Pointcut("@annotation(PermissonRequired)")
	protected void permissionCheck() {
	}

	static Logger logger = Logger.getLogger(PermissonAspect.class);

//	@Around(value = "loggingToDB()&&" + "@annotation(LoggingRequired)")
//	@Before("permissionCheck()")
	@Before(value="permissionCheck()&&" + "@annotation(PermissonRequired)",argNames="PermissonRequired")
	public Object Check(PermissonRequired permissonRequired) throws Throwable {
		IsLogin isLogin = userAccessAnnotation.isLogin();
		Object resultObject = null;
		String opretor = "";
		String userName = null;
		try {
			// get user info
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			if (auth != null) {
				userName = SecurityContextHolder.getContext()
						.getAuthentication().getName();
				String authorities = SecurityContextHolder.getContext()
						.getAuthentication().getAuthorities().toString();
				opretor = "操作人 = " + userName + ", 权限 = " + authorities;
			} else {
				opretor = "操作人 = JUnit, 权限 = JUnit";
				userName = "JUnit";
			}

		} finally {
			// get start time
			long start = System.currentTimeMillis();

		}
		return resultObject;
	}

	@AfterThrowing(pointcut="loggingToDB()",throwing="ex")
	public void doRecoveryActions(Exception ex) {
		// get user info
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		String authorities = SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities().toString();
		String opretor = "操作人 = " + userName + ", 权限 = " + authorities;
		long start = System.currentTimeMillis();
		Log log = new Log(LogType.EXCEPTION, opretor, new Date(start),
				userName);
		logManager.addLog(log);

	}
}
