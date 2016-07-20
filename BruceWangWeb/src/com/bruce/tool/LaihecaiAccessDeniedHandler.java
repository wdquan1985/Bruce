package com.bruce.tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

//自定义 AccessDeniedHandler， by bruce
public class LaihecaiAccessDeniedHandler implements AccessDeniedHandler{
    private String accessDeniedUrl;
    
    public LaihecaiAccessDeniedHandler() {
    }
 
    public LaihecaiAccessDeniedHandler(String accessDeniedUrl) {
        this.accessDeniedUrl = accessDeniedUrl;
    }
 
   
 public void handle(HttpServletRequest request, HttpServletResponse 
		 response, AccessDeniedException accessDeniedException) throws 
		 IOException, ServletException {
        response.sendRedirect(accessDeniedUrl);
        String deniedMessage = accessDeniedException.getMessage();
        String rp = request.getRequestURI();
        request.getSession().setAttribute(Constants.ACCESS_DENIED_MSG, deniedMessage);
    }
 
    public String getAccessDeniedUrl() {
        return accessDeniedUrl;
    }
 
    public void setAccessDeniedUrl(String accessDeniedUrl) {
        this.accessDeniedUrl = accessDeniedUrl;
    }
}
