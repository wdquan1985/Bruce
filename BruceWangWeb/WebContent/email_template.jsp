<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<html>
  <head>
    <base href="<%=basePath%>">
 
  	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.4.1/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.4.1/themes/icon.css" />
	
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    
    <script type="text/javascript">
	    $(document).ready(function(){  
	    	closeEditor();
	    	loadGrid();
		});  

	 
		function loadGrid()  {
			$('#dg').datagrid({
				nowrap:false,
				loadMsg:'加载中，请稍候...',
				fitColumns:true,
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
  
  <body>
  	
  	<div style="width:100%; height:100%;" id="tablegrid">
		<table id="dg" title="家政管理系统" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'dataquery/r',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
			<thead>
				<tr id="tr">
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',width:$(this).width() * 0,nowrap:'false',align:'left'">模板Id</th>
					<th data-options="field:'name',width:$(this).width() * 0.2,nowrap:'false',align:'left'">模板名称</th>
					<th data-options="field:'sex',width:$(this).width() * 0.2,nowrap:'false',align:'left'">用户类型</th>
					<th data-options="field:'age',width:$(this).width() * 0.2,nowrap:'false',align:'left'">模板文件名称</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editTemplateInfo()">修改</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="addTemplateInfo()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="delTemplateInfo()">删除</a>
	</div>
    	
    	<script type="text/javascript">
		var templateType;

		
		function closeEditor() {
			$('#dlg').hide();
			loadGrid();
		}
		

		
	</script>
  </body>
</html>
