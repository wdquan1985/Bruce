<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>登录首页</title> 
<style type="text/css">
	.NOPRINT{background:url(/BruceWangWeb/Images/login_5.gif);background-repeat:no-repeat;width:60px;height:27px;align:center;}
</style>
</head>
  
<body>
  <table width="681" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:120px">
    <tr>
	    <td width="353" height="259" align="center" valign="bottom" background="/BruceWangWeb/Images/login_1.gif">
		<table width="90%" border="0" cellspacing="3" cellpadding="0">
	      <tr>
	        <td align="right" valign="bottom" style="color:#05B8E4"><font size=2 color=ccjjkk>Power by Bruce Copyright 2009</font></td>
	      </tr>

	    </table>
	    </td>
	    
	    <td width="195" height="259" background="/BruceWangWeb/Images/login_2.gif">
	    <table width="190" height="106" border="0" align="center" cellpadding="2" cellspacing="0">
		    <form action="../j_spring_security_check" method="post">
		        <tr>
              		<td height="50" colspan="2" align="left">&nbsp;</td>
            	</tr>
		  	  
		        <tr> 
		            <td width="60" colspan="1" height="20" align="left"><font size=2 color=ccjjkk>登陆名称</font></td> 
		            <td><input id="j_username" name="j_username" type="text" size="10" ></td> 
		        </tr>  
		  
		        <tr>
		            <td height="20" align="left"><font size=2 color=ccjjkk>登陆密码</font></td> 
		            <td><input id="j_password" name="j_password" type="password" size="10" ></td>  
		        </tr> 
	
			    <tr>
					 <td width="20" height="20" align="right"><label for="_spring_security_remember_me"><font size=2 color=ccjjkk>记住密码?</font></label></td>        
					 <td><input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/></td> 
				</tr>
				<tr>
					<td width="30" colspan="2" align="left">
						<ul>
							<li><a href="/BruceWangWeb/changePassword"><font size=2 color=ccjjee>Change Password</font></a></li>
						</ul>
					</td>
				</tr>
				
				<tr>
		        	<td width="10" align="center">
		        	<input type="submit" value="Login" class="NOPRINT"></td>  
		  		</tr>
		  		<tr>
		  		<td height="3" colspan="1"></td>
		  		</tr>
		  			  		
		    </form>  
	  	</table></td>
	  	<td width="133" background="/BruceWangWeb/Images/login_3.gif">&nbsp;</td>
	</tr>
	
	<tr>
    <td height="161" colspan="3" background="/BruceWangWeb/Images/login_4.gif"></td>
  	</tr>
  </table>
</body>  
</html>