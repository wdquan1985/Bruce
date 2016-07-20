package com.bruce.authority.interceptor;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bruce.authority.AuthorityHelper;
import com.bruce.authority.AuthorityType;
import com.bruce.authority.AuthorityRequired;
import com.bruce.authority.ResultTypeEnum;
import com.bruce.authority.util.SessionHelper;
import com.bruce.model.User;

public class AuthorityAnnotationInterceptor extends HandlerInterceptorAdapter {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handler2=(HandlerMethod) handler;
		AuthorityRequired authorityRequired = handler2.getMethodAnnotation(AuthorityRequired.class);
	
		if(null == authorityRequired){
			//没有声明权限,放行
			return true;
		}
		
		logger.debug("authorityRequired", authorityRequired.toString());
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SessionHelper.UserHandler);
		boolean aflag = false;
		
		for(AuthorityType at:authorityRequired.authorityTypes()){
			if(AuthorityHelper.hasAuthority(at.getIndex(), user.getRightContent())==true){
				aflag = true;
				break;
			}
		}
		
		if(false == aflag){
			
			if (authorityRequired.resultType() == ResultTypeEnum.page) {
		//采用传统页面进行提示
				StringBuilder sb = new StringBuilder();
				sb.append(request.getContextPath());
				sb.append("/mg/userIndex.jsp?oprst=false&opmsg=").append(URLEncoder.encode("没有权限","utf-8"));
				response.sendRedirect(sb.toString());
			} else if (authorityRequired.resultType() == ResultTypeEnum.json) {
		//采用ajax方式的进行提示
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=UTF-8");
				OutputStream out = response.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out,"utf-8"));
				pw.println("{\"result\":false,\"code\":12,\"errorMessage\":\"没有权限\"}");
				pw.flush();
				pw.close();
			}
			
			return false;
			
		}
		
		return true;

	}
	
}
