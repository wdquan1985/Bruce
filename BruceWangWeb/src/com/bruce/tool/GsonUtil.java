package com.bruce.tool;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.crip.po.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {

	private final static Gson gson = new Gson();
	public static <T> T getJSON(String jsonString, Type type) {
        T t = null;
        try {
//            Gson gson = new Gson();
            t = gson.fromJson(jsonString, type);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return t;
    }

	public static <T> List<T> getJsonList(String jsonString, Type type) {
        List<T> list = new ArrayList<T>();
        try {
//            Gson gson = new Gson();
            list = gson.fromJson(jsonString, type);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return list;
    }
	
	public static List<Map<String, Object>> listKeyMaps(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
//            Gson gson = new Gson();
            list = gson.fromJson(jsonString, new TypeToken<List<Map<String, Object>>>() {
                    }.getType());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }
	
	public static <T> String toJSON(T t) {
//		Gson gson = new Gson();
		return gson.toJson(t);
	}
	
//	public static void main(String[] args) {
//		Gson gson = new Gson();
//		User user1 = new User();
//		user1.setUsername("test1");
//		user1.setPassword("111");
//		User user2 = new User();
//		user2.setUsername("test2");
//		user2.setPassword("222");
//		User user3 = new User();
//		user3.setUsername("test3");
//		user3.setPassword("333");
//		
//		List<User> list = new ArrayList<User>();  
//        list.add(user1);  
//        list.add(user2);  
//        list.add(user3);  
//        
//        System.out.println("----------带泛型的List之间的转�?------------");  
//        // 带泛型的list转化为json  
//        String s2 = gson.toJson(list);  
//        System.out.println("带泛型的list转化为json==" + s2);  
//  
//        // json转为带泛型的list  
//        List<User> retList = gson.fromJson(s2,  
//                new TypeToken<List<User>>() {  
//                }.getType());  
//        for (User stu : retList) {  
//            System.out.println(stu.getUsername());  
//        }
//        List<User> fooList = getJsonList(s2, new TypeToken<List<User>>() {  
//        }.getType());
//        for (User stu : fooList) {  
//            System.out.println(stu.getPassword());  
//        }
//        
//        String s1 = gson.toJson(user1);
//        User fooString = getJSON(s1, User.class);
//        System.out.println(fooString.getUsername());
//        
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("result", "OK");
//        s1 = gson.toJson(map);
//        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
//        mapList.add(map);
//        String s3 = gson.toJson(mapList);
//        mapList = listKeyMaps(s3);
//        Map<String, Object> map1 = getJSON(s1,  new TypeToken<Map<String, Object>>() {  
//        }.getType());
//        System.out.println(map1.get("result"));
//        
//        
//        
//	}
}
