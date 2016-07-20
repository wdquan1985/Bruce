package com.bruce.daoInterface;

import java.util.List;
import java.util.Map;
import com.bruce.model.Log;

public interface LogManager extends BaseManager<Log, Long>{
	
	public void addLog(Log log);
	public Map<String, Object> findLog(String user, long start, long end, int startPage, int max, String type);
}
