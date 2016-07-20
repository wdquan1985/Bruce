package com.bruce.aop;

import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
public class AuditLoggingAspect {

	@Autowired
	private LogManager logManager;

	@Pointcut("@annotation(LoggingRequired)")
	protected void loggingToDB() {
	}

	static Logger logger = Logger.getLogger(AuditLoggingAspect.class);

//	@Around(value = "loggingToDB()&&" + "@annotation(LoggingRequired)")
	@Around("loggingToDB()")
	public Object logToDB(ProceedingJoinPoint joinPoint) throws Throwable {
		Object resultObject = null;
		String opretor = "";
		Log log = null;
		String userName = null;
		try {
			resultObject = joinPoint.proceed();
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
			// get action info
			String type = getType(joinPoint.getSignature().getName());
			Object[] obs = joinPoint.getArgs();
			String detail = getReadableStr(obs);
			logger.info("----------------" + opretor + " 操作类型: " + type
					+ " 操作对象:  " + detail + "------------");
			log = new Log(type, opretor + type + detail, new Date(start),
					userName);
			logManager.addLog(log);
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

	private String getReadableStr(Object[] obs) {
		String detail = "";
		if (obs[0] instanceof BaseModel) {
			BaseModel bm = (BaseModel) obs[0];
			detail += bm.GetFriendlyName();
		} else {
			// Long or Long[]
			detail += "id = " + Arrays.toString(obs).toString();
		}
		return detail;
	}

	private String getType(String metohdName) {
		if (metohdName.startsWith("save") || metohdName.startsWith("add")) {
			return "新增";
		} else if (metohdName.startsWith("update")) {
			return "更新";
		} else if (metohdName.startsWith("delete")) {
			return "删除";
		} else {
			return "操作";
		}

	}
}
