package com.bruce.tool;

import java.util.HashMap;
import java.util.Map;

public class LogType {
	
	public static final String LOGIN="登录";
	public static final String LOGOUT="退出";
	public static final String FTP="文件传输";
	public static final String EXCEPTION="异常";
	public static final String DATA_PROCESS="数据处理";
	public static final String BULK_OPERATION="批量操作";
	public static final Map<String,String> TYPES = new HashMap<String,String>();
	
	static{
		TYPES.put("LOGIN", LOGIN);
		TYPES.put("LOGOUT", LOGOUT);
		TYPES.put("FTP", FTP);
		TYPES.put("EXCEPTION", EXCEPTION);
		TYPES.put("DATA_PROCESS", DATA_PROCESS);
		TYPES.put("BULK_OPERATION", BULK_OPERATION);
	}

}
