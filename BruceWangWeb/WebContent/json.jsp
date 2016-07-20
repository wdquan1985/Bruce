<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/JiaZheng_self_def/js/jquery-1.7.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#add").click(function(){
			var userName = $("#name").attr("value");
			var sex =$("#sex").attr("value");
			var age =$("#age").attr("value");
			
			var person = {name:name,sex:sex,age:age};
			
			$.ajax({
				url:"/JiaZheng_self_def/person/data/addPersonJson",
				type:"post",
				data:person,
				success:function(a){
					alert("name--->" + a.name + "sex--->" + a.sex + "age--->" + a.age);
					
				}
			});
			
			
			
		});
	});
</script>
</head>
<body>
	<h>json添加Person</h>
	姓名：<input type="text" id="name" name="name">
	性别：<input type="text" id="sex" name="sex">
	年龄：<input type="text" id="age" name="age">
	<input type="button" id="add" value="添加">
	
</body>
</html>