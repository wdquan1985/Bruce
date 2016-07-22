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
	
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/jquery.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.4.1/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.4.1/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/product.css" />
    <style type="text/css">
    	body {background-color: white}
    </style>
    <script type="text/javascript" charset="utf-8">
	    $(document).ready(function(){  
	    	closeEditor();
	    	loadGrid();
	    	//loadTree();
		});  

	    //分页显示
		function loadGrid()  {
			$('#dg').datagrid({
				url:'productAction/getall',
				//title: '功能菜单',
				//nowrap:false,
				loadMsg:'加载中，请稍候...',
				fitColumns:true,
				autoRowHeight:true,
				pagination : true,//分页工具栏
				pageNumber : 1,//初始页码
				pageSize : 15,
				pageList : [15,30,45,60],
				toolbar:"#toolbar",
				detailFormatter:function(index,row){
					return '<div style="padding:5px"><table id="ddv-' + index + '"></table></div>';
				},
				columns: [[
				   {field:'id',align: 'center',checkbox:true},
		           {field: 'brand', title: '品牌', width: 140,align:'left'},
		           {field: 'model', title: '型号', width: 100,align:'left'},
		           {field: 'dmd', title: 'DMD', width: 70,align:'left'},
		           {field: 'manufacturers', title: '光机厂商', width: 100,align:'center',sortable:true},
		           {field: 'lumen', title: '流明', width: 70,align:'center'},
		           {field: 'realLumen', title: '真实流明', width: 100,align:'left'},
		           {field: 'mainboard', title: '主板方案', width: 140,align:'center'},
		           {field: 'ram', title: '运行内存', width: 80,align:'center'},
		           {field: 'memory', title: '存储内存', width: 80,align:'center'},
		           {field: 'price', title: '售价', width: 60,align:'center'},
		           {field: 'costing', title: '大约成本', width: 60,align:'center'},
		           {field: 'salesAmount', title: '销售量(台/月)', width: 60,align:'center'},
		           {field: 'money', title: '金额(万/月)', width: 60,align:'center'},
		           {field: 'trapezoidal', title: '梯形校正', width: 60,align:'center'},
		           {field: 'lightSource', title: '光源', width: 60,align:'center'},
		       ]],
			   // onClickRow:function(rowIndex){
			   //     if (lastIndex != rowIndex){
			  //      $('#dg').datagrid('endEdit', lastIndex);
			   //     $('#dg').datagrid('beginEdit', rowIndex);
			   //     setEditing(rowIndex);
			   //     }
			   //     lastIndex = rowIndex;
			   // },
			    onRefresh:function(pageNumber, pageSize){
			    	$('#dg').datagrid('load',{
			    		page:pageNumber,
			    		rows:pageSize
			    	});
			    },		
			});
			
			var p = $('#dg').datagrid('getPager'); 
			//单独设置分页
		    $(p).pagination({ 
		        beforePageText: '第',//页数文本框前显示的汉字 
		        afterPageText: '页    共 {pages} 页', 
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		    });
		}
	</script>
    
 </head>
  
 <body class="easyui-layout"> 
 	<!-- 主界面 -->
 	<div class="easyui-panel" title="产品列表" style="width:auto;padding:10px;"> 	
		<table id="dg" border-color="red"></table>
		
		<!-- 主界面中的button -->
		<div id="toolbar">
			<div id="leftButton">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" onclick="addProduct()">添加</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" onclick="editProduct()">修改</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true" onclick="delProduct()">删除</a>
			</div>
			
			<div id="rightButton">
				<label class="slabel">品牌:</label>
				<input id="pinPaiId" name="pinPai" style="line-height:20px;border:1px solid #ccc"/>			    
				<button class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()"><label class="slabel">Search</label></button>
			</div>
		</div>
	</div>

	<!--  添加用户信息对话框 -->
	<div id="dlg" class="easyui-dialog"
		style="width:50%;height:80%;padding:0px 0px; background:#ADA; color:black;" 
		data-options="closed:true,maximizable:true,buttons:'#dlg-buttons',resizable:true,modal:true">		
			<form id="ff" method="get" style="width:100%; height:100%;">
					<table cellpadding="5">			 
						<tr>
							<tr>
							<td>品牌:</td>
							<td>
								<input name="brand">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							
							<tr>
							<td>型号:</td>
							<td>
								<input name="model">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							
							<tr>
							<td>DMD:</td>
							<td>
								<input name="dmd">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							
							<tr>
							<td>光机厂商:</td>
							<td>
								<input name="manufacturers">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							
							<tr>
							<td>流明:</td>
							<td>
								<input name="lumen">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							
							<tr>
							<td>实际流明:</td>
							<td>
								<input name="realLumen">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							
							<tr>
							<td>主板方案:</td>
							<td>
								<input name="mainboard">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							
							<tr>
							<td>运行内存:</td>
							<td>
								<input name="ram">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							
							<tr>
							<td>存储内存:</td>
							<td>
								<input name="memory">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							
							<tr>
							<td>售价:</td>
							<td>
								<input name="price">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							<tr>
							<td>大约成本:</td>
							<td>
								<input name="costing">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							<tr>
							<td>销售数量(台/月):</td>
							<td>
								<input name="salesAmount">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							<tr>
							<td>金额/月:</td>
							<td>
								<input name="money">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							<tr>
							<td>梯形校正:</td>
							<td>
								<input name="trapezoidal">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							<tr>
							<td>光源:</td>
							<td>
								<input name="lightSource">
								<label id="lblTemplateName">添加</label>
							</td></tr>
							
						</tr>
					</table>					
			</form>		
			<!-- 修改用户信息对框框中buttons -->	
			<div id="dlg-buttons">
					<button id="add" class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="add()">add</button>
					<button id="closehref" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="closeEditor()">cancel</button> 
			</div>
			
 	</div>
 	
 	<!--  修改用户信息对话框-->
 	<div id="dlgupdate" class="easyui-dialog"
		style="width:50%;height:80%; padding:0px 0px; color:black;" closed="true"
		maximizable="true" resizable="true"
		buttons="#dlgupdate-buttons">	
		  <div id="update1">	
			<form id="ffupdate" method="get">

					<table cellpadding="5" id="update">
						 
						<tr>
							<td>
								<input type="hidden" name="id">
							</td>
							
							<tr class="updateLabel">												
							<td align="center">品牌:</td>
							<td>
								<input name="brand" id="first">
								<label  for="first">update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">型号:</td>
							<td>
								<input name="model">
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">DMD:</td>
							<td>
								<input name="dmd">
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">光机厂商:</td>
							<td>
								<input name="manufacturers">
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">流明:</td>
							<td>
								<input name="lumen"/>
								<label>update</label>
							</td></tr>
							<tr class="updateLabel">
							<td align="center">真实流明:</td>
							<td>
								<input name="realLumen"/>
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">主板方案:</td>
							<td>
								<input name="mainboard"/>
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">运行内存:</td>
							<td>
								<input name="ram"/>
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">存储内存:</td>
							<td>
								<input name="memory"/>
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">售价:</td>
							<td>
								<input name="price"/>
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">大约成本:</td>
							<td>
								<input name="costing"/>
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">销售数量(台/月):</td>
							<td>
								<input name="salesAmount"/>
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">金额 /月:</td>
							<td>
								<input name="money"/>
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">梯形校正:</td>
							<td>
								<input name="trapezoidal"/>
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">光源:</td>
							<td>
								<input name="lightSource"/>
								<label>update</label>
							</td></tr>
							
							
						</tr>
					</table>
			</form>
		  </div>
			
		<!-- 更新用户信息对话框中的buttons -->
		<div id="dlgupdate-buttons">
				<a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon-ok" 
					onclick="edit()" style="padding:5px;">修改</a>
				<a id="closehref" href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="closeEditor()">关闭</a>
		</div>
			
 	</div>

    <script type="text/javascript">
    //EasyUI 格式化列
		function formatPrice(val,row){
		    if (val < 20){
		    return '<span style="color:red;">'+val+'</span>';
		    } else {
		    return val;
		    }
		}
	    
	    //加载增加用户对话框
		function addProduct() {
			$('#ff').form('reset');
			$('#dlg').show();
			$('#dlg').dialog('open').dialog('setTitle', '添加产品信息');
		}

		//加载更新用户对话框，并且将该用户信息显示。
		function editProduct(){
			$('#add').css('display','none');
			$('#edit').css('display','inline');
			var row = $('#dg').datagrid('getSelections');
			var num  = row.length;
			if(num > 1 || num == 0){
				$.messager.alert('提示',"请选中一条记录进行修改");
				return;
			}
			
			$('#ffupdate').form('reset');
			$('#dlgupdate').show();
			$('#dlgupdate').dialog('open').dialog('setTitle', 'update the Data');
			
			$("#ffupdate").form("load", row[0]);
			
			//getIpAddress();//显示图片
		
		}
		
	    //增加用户对话框中的添加 button。
		function add() {	
			$('#ff').form('submit', {
				url:'<%=basePath%>productAction/save',
				method:'post',
				success:function(data){
					alert(data);
					$.messager.alert('提示','信息保存成功');
					closeEditor();
				},
				error:function(info,er){
		     		$.messager.alert('提示',info,'error');
   	     		}
			});
		
		}
		
		//按条删除用户信息
		function delProduct(){										
				var ids = "";			
				var rows = $('#dg').datagrid('getSelections');
				var num=rows.length;//获取要删除信息的个数
				if(num > 0){
					for(var i=0; i<rows.length; i++){//组成一个字符串，ID主键之间用逗号隔开
							ids=ids + rows[i].id + ",";
					}
					
					$.messager.confirm('Confirm','确定要删除吗?',function (r) {
					//	alert(ids);					
						if(r){	
							 var dataIds = {ids:ids};
							 $.ajax({  
						          url:'<%=basePath%>productAction/dele',
						          type:"POST",
						          data: dataIds,  
						          success:function(msg){  
						            // alert(msg);  
						             loadGrid();
						          }  
						       });
						}
						});
				}else{
					$.messager.alert('错误','请选中要删除的模板！');
				}

		}
		
		//更新用户按钮所调用的操作。
		function edit() {
			//$('#picPath00').value("");
			$('#ffupdate').form('submit', {
				url:'<%=basePath%>productAction/update',
				method:'POST',
				success:function(data){
					closeEditor();
					$.messager.alert('update成功',data);
				}
			});	
		}	
		
		function doSearch() {
			var brand = $("#pinPaiId").val();
			$('#dg').datagrid('load',{
				brand:brand
			});
		} 
		
		function clearForm() {
			ue.execCommand('cleardoc');
		}
		
		//关闭所有的对话框，add，update，delete，upload
		function closeEditor() {
		    $('#dlg').dialog('close');
		    $('#dlgupdate').dialog('close');
		    $('#dlgupload').dialog('close');
		    $('#dlguploadBetter').dialog('close');
			loadGrid();
		}
		
		function reduceContent(value) {
			value = delHtmlTag(value);
			if (value.indexOf("。") !== -1) {
				return value.split("。")[0] +"...";
			}
			else if (value.indexOf(".") !== -1) {
				return value.split(".")[0] +"...";
			}
			return value;
		}
		
		 function delHtmlTag(str){
  			return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
 		}
        
     </script>
     
  </body>
</html>
