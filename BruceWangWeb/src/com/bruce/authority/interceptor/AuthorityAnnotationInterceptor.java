package com.bruce.authority.interceptor;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bruce.authority.AuthorityHelper;
import com.bruce.authority.AuthorityType;
import com.bruce.authority.AuthorityRequired;
import com.bruce.authority.ResultTypeEnum;
import com.bruce.authority.util.SessionHelper;
import com.bruce.model.Users;

public class AuthorityAnnotationInterceptor extends HandlerInterceptorAdapter {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//bruce, 为什么加这个判断？ 因为我们这里只需要拦截方法，静态资源等的请求url不要拦截
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handler2=(HandlerMethod) handler;
		AuthorityRequired authorityRequired = handler2.getMethodAnnotation(AuthorityRequired.class);
		if(null == authorityRequired){
			//没有声明权限,放行
			return true;
		}
		
		logger.debug("authorityRequired", authorityRequired.toString());

		Users userDetails = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		HttpSession session = request.getSession();
//		Users user = (Users)session.getAttribute(SessionHelper.UserHandler);
//		Users user = (Users)session.get("LOGINED_USER");
		
		boolean aflag = false;
		
		for(AuthorityType at:authorityRequired.authorityTypes()){
			if(AuthorityHelper.hasAuthority(at.getIndex(), userDetails.getContent())==true){
				aflag = true;
				break;
			}
		}
		
		if(false == aflag){
			
			if (authorityRequired.resultType() == ResultTypeEnum.page) {
				//采用传统页面进行提示
				StringBuilder sb = new StringBuilder();
				sb.append(request.getContextPath());
//				sb.append("/mg/userIndex.jsp?oprst=false&opmsg=").append(URLEncoder.encode("没有权限","utf-8"));
				sb.append("/auth/login");
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

	//后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
    @Override  
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {  
        System.out.println("===========HandlerInterceptor1 postHandle");  
    }  
    @Override  
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {  
        System.out.println("===========HandlerInterceptor1 afterCompletion");  
    }
}
