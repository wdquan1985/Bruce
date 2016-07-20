<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--  <!DOCTYPE HTML>-->
<html>
  <head>
    <base href="<%=basePath%>">
	
	<script type="text/javascript" src="<%=basePath%>js/self.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/jquery.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.4.1/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.4.1/themes/icon.css" />
    
    <script type="text/javascript" charset="utf-8">
	    $(document).ready(function(){
	    	loadGrid();
	    	loadPanel();
		});  

	 
		function loadGrid()  {
			$('#dg').datagrid({
				title: '功能菜单',
				nowrap:false,
				loadMsg:'加载中，请稍候...',
				fitColumns:true,
				width: function () { return document.body.clientWidth * 0.9 },
				pagination : true,//页码
				pageNumber : 1,//初始页码
				pageSize : 15,
				pageList : [15,30,45,60],
				detailFormatter:function(index,row){
					return '<div style="padding:5px"><table id="ddv-' + index + '"></table></div>';
				},
				pagination:true,
			});
		}

	</script>
    
  </head>
  
 <body class="easyui-layout">
 
 	<div region="north" title="North Title" split="true" style="height:100px;">
 		<div id="p" style="padding:10px;">
 			<p>panel content.</p>
 			<p>panel content.</p>
 		</div>
 	</div>  
 	
    <div region="south" title="South Title" split="true" style="height:100px;">
    	<a href="###" id="box" title="这是内容提示框">Hover me</a>
    </div>  
    
    <div region="east" iconCls="icon-reload" title="East" split="true" style="width:100px;"></div> 
     
    <div region="west" split="true" title="West" style="width:100px;"></div> 
     
    <div region="center" title="center title" style="padding:5px;background:#eee;"> <!-- ########### -->  
		<table id="dg" title="BruceYY" style="width:80%;height:80%"
			data-options="striped:true,rownumbers:true,singleSelect:false,url:'crudUsers/getall',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
			<thead>
				<tr id="tr">
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',width:$(this).width() * 0,nowrap:'false',align:'left'">模板Id</th>
					<th data-options="field:'firstname',width:$(this).width() * 0.2,nowrap:'false',align:'left'">firstname</th>
					<th data-options="field:'lastname',width:$(this).width() * 0.2,nowrap:'false',align:'left'">lastname</th>
					<th data-options="field:'phone',width:$(this).width() * 0.2,nowrap:'false',align:'left'">phone</th>
					<th data-options="field:'email',width:$(this).width() * 0.2,nowrap:'false',align:'left'">email</th>
					<th data-options="field:'picPath',width:$(this).width() * 0.2,nowrap:'false',align:'left'">record</th>
				</tr>
			</thead>
		</table>
		
				    <!-- 主界面中的button -->
	    
			<div id="toolbar">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" onclick="addTemplateInfo()">添加</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" onclick="editTemplateInfo()">修改</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true" onclick="delTemplateInfo()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" 
					iconCls="icon-add" plain="true" onclick="uploadPictureInfo()">上传图片</a>	
			</div>
	</div>
	

	   
    <script type="text/javascript">
		function loadPanel() {
			$('#p').panel({
				id:'pox',
				width:500,
				height:150,
				left:200,
				top:200,
				title:'My Panel'
			});
			
			$('#pox').panel('panel').css('position', 'absolute');

		}
     </script>
     
  </body>
</html>
