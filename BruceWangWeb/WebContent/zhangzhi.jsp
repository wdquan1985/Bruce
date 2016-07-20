<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>产品统计系统</title>
	
	<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/jquery.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.4.1/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.4.1/themes/icon.css" />
	
	<style type="text/css">
	body{font-size:14px;}
	ul,li,h2{margin:0;padding:0;}
	ul{list-style:none;}
	#top{width:100%;height:40px;margin:0 auto;background-color:#CCCC00;}
	#top h2{width:150px;height:40px;background-color:#99CC00;float:left;font-size:18px;text-align:center;line-height:40px;}
	#topTags{width:750px;height:40px;margin:0 auto;background-color:#CCCC00;float:left}
	#topTags ul li{float:left;width:100px;height:25px;margin-right:5px;display:block;text-align:center;cursor:pointer;padding-top:15px;}
	
	#main{width:100%;height:88%;margin:0 auto;background-color:#F5F7E6;}
	#leftMenu{width:11.6%;height:100%;background-color:#009900;float:left;}
	#leftMenu ul{margin:10px;}
	#leftMenu ul li{width:130px;height:30px;display:block;background:#99CC00;cursor:pointer;line-height:30px;text-align:center;margin-bottom:5px;}
	#leftMenu ul li a{color:#000000;text-decoration:none;display:block;height:100%;/*将a填满整个li，要不不点击到a无法加载页面*/}
	#content{width:88%;height:100%;float:left}
	.content{width:100%;height:100%;display:none;padding:5px;overflow-y:auto;line-height:30px;}
	.content iframe{width:100%;height:100%}
	
	#footer{width:100%;height:30px;margin:0 auto;background-color:#ccc;line-height:30px;text-align:center;}
	.content1 {width:740px;height:390px;display:block;padding:5px;overflow-y:auto;line-height:30px;}
	</style>
	<script type="text/javascript">
	    window.onload = function () {
	        function $(id) {return document.getElementById(id);}
	        var menu = $("topTags").getElementsByTagName("ul")[0]; //顶部菜单容器
	        var tags = menu.getElementsByTagName("li"); //顶部菜单
	        var ck = $("leftMenu").getElementsByTagName("ul")[0].getElementsByTagName("li"); //左侧菜单
	        var j;
	        //点击左侧菜单增加新标签
	        for (var i = 0; i < ck.length; i++) {
	            ck[i].onclick = function () {
	                $("welcome").style.display = "none";//欢迎内容隐藏
	                //循环取得当前索引
	                for (j = 0; j < 8; j++) {
	                    if (this == ck[j]) {
	                        if ($("p" + j) == null) {
	                            openNew(j, this.innerHTML); //设置标签显示文字
	                        }
	                        clearStyle();
	                        $("p" + j).style.backgroundColor = "yellow";
	                        clearContent();
	                        $("c" + j).style.display = "block";
	                    }
	                }
	               // return false;
	            };
	        }
	        //增加或删除标签
	        function openNew(id, name) {
	            var tagMenu = document.createElement("li");
	            tagMenu.id = "p" + id;
	            tagMenu.innerHTML = name + "   " + "<img src='http://www.tjdadi.com.cn/off.gif' style='vertical-align:middle'/>";
	            //标签点击事件
	            tagMenu.onclick = function (evt) {
	                clearStyle();
	                tagMenu.style.backgroundColor = "yellow";
	                clearContent();
	                $("c" + id).style.display = "block";
	            };
	            //标签内关闭图片点击事件
	            tagMenu.getElementsByTagName("img")[0].onclick = function (evt) {
	                evt = (evt) ? evt : ((window.event) ? window.event : null);
	                if (evt.stopPropagation) { evt.stopPropagation(); } //取消opera和Safari冒泡行为;
	                this.parentNode.parentNode.removeChild(tagMenu); //删除当前标签
	                var color = tagMenu.style.backgroundColor;
	                //设置如果关闭一个标签时，让最后一个标签得到焦点
	                if (color == "#ffff00" || color == "yellow") {//区别浏览器对颜色解释
	                    if (tags.length - 1 >= 0) {
	                        clearStyle();
	                        tags[tags.length - 1].style.backgroundColor = "yellow";
	                        clearContent();
	                        var cc = tags[tags.length - 1].id.split("p");
	                        $("c" + cc[1]).style.display = "block";
	                    }
	                    else {
	                        clearContent();
	                        $("welcome").style.display = "block";
	                    }
	                }
	            };
	            menu.appendChild(tagMenu);
	        }
	        //清除标签样式
	        function clearStyle() {
	            for (var i = 0; i < tags.length; i++) {
	                menu.getElementsByTagName("li")[i].style.backgroundColor = "#FFCC00";
	            }
	        }
	        //清除内容
	        function clearContent() {
	            for (var i = 0; i < 7; i++) {
	                $("c" + i).style.display = "none";
	            }
	        }
	    };
	</script>
	</head>
	<body>
		<div id="top">
			<h2>产品管理</h2>
			<div id="topTags">
				<ul>
				</ul>
			</div>
		</div>
		<div id="main">
			<!-- 左侧菜单 --> 
			<div id="leftMenu">
				<ul>
					<li><a href="product.jsp" target="content1">产品管理</a></li>
					<li><a href="accessDenied.jsp" target="content2">个人资料</a></li>
					<li><a href="Crud_Users.jsp" target="content3">相册管理</a></li>
					<li><a href="c3.html" target="content4">留言管理</a></li>
					<li><a href="c4.html" target="content5">风格管理</a></li>
					<li><a href="c5.html" target="content6">系统管理</a></li>
					<li><a href="c6.html" target="content7">帮助信息</a></li>
				</ul>
			</div>
			<!-- 右侧内容 -->
			<div id="content">
				<div id="welcome" class="content" style="display:block;">
				  <div align="center">
				    <p> </p>
				    <p><strong>欢迎使用用户管理系统！</strong></p>
				    <p> </p>
				   </div>
				</div>
				<div id="c0" class="content"><iframe name="content1" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe></div>
				<div id="c1" class="content"><iframe name="content2" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe></div>
				<div id="c2" class="content"><iframe name="content3" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe></div>
				<div id="c3" class="content"><iframe name="content4" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe></div>
				<div id="c4" class="content"><iframe name="content5" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe></div>
				<div id="c5" class="content"><iframe name="content6" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe></div>
				<div id="c6" class="content"><iframe name="content7" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe></div>
			</div>
		</div>
		<div id="footer">copyright for lalasxc </div>
	</body>
</html>
