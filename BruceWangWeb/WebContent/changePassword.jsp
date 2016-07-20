<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function ChangePass(){
		var form = document.forms[0];
		form.action="/JiaZheng_ChangePass/user/changePassword.do";
		form.method="post";
		form.submit();
	}
</script>
</head>

<body>
<h1>Change Password</h1>
<form method="post" action="">

	<label for="oldpassword">Old Password</label>:
	<input id="oldpassword" name="oldpassword" size="20" maxlength="50" type="password"/>
<br />
	<label for="password">New Password</label>:
	<input id="password" name="password" size="20" maxlength="50" type="password"/>
<br />
     <input type="button" value="ChangePassword" onclick="ChangePass()"/> 
     
</form> 
</body>
</html>