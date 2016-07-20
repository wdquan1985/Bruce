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
	
	<!--  <script type="text/javascript" src="<%=basePath%>js/self.js"></script> -->
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/jquery.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.4.1/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.4.1/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/crud_users.css" />
    <style type="text/css">
    	body {background-color: white}
    	.HangTr th {font-size:30pt;}
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
				url:'crudUsers/getall',
				//title: '功能菜单',
				nowrap:false,
				loadMsg:'加载中，请稍候...',
				fitColumns:true,
				width: function () { return document.body.clientWidth * 0.9;},
				pagination : true,//分页工具栏
				pageNumber : 1,//初始页码
				pageSize : 15,
				pageList : [15,30,45,60],
				detailFormatter:function(index,row){
					return '<div style="padding:5px"><table id="ddv-' + index + '"></table></div>';
				},
				//EasyUI 行格式化。
				//rowStyler:function(index,row){ 
				//	if (row.id<20){
				//		return 'background-color:pink;color:blue;font-weight:bold;';
				//	}
				//},
				
			    onClickRow:function(rowIndex){
			        if (lastIndex != rowIndex){
			        $('#tt').datagrid('endEdit', lastIndex);
			        $('#tt').datagrid('beginEdit', rowIndex);
			        setEditing(rowIndex);
			        }
			        lastIndex = rowIndex;
			    },
		
			});
		}
		
		//轮流显示图片
		var count = 0;
	    var arryImage = ['<%=basePath%>imageFile/1.jpg','<%=basePath%>imageFile/2.jpg','<%=basePath%>imageFile/3.jpg','<%=basePath%>imageFile/4.jpg','<%=basePath%>imageFile/5.jpg','<%=basePath%>imageFile/6.jpg','<%=basePath%>imageFile/7.jpg'];
	    function show() {
	        document.all.imagesId.src = arryImage[count];
	        document.getElementById("imagesId").style.display = "block";
	        count++;
	        if (count >= arryImage.length) {
	            count = 0;
	        }
	        setTimeout("show()",2500);
	    }

	</script>
    
 </head>
  
 <body class="easyui-layout" onLoad="show()">
 <!--    <div region="south" title="South Title" split="true" style="height:100px;">
    	<a href="###" id="box" title="这是内容提示框">Hover me</a>
    </div>   -->
     
    <div region="west" split="true" style="width:20%; background-color: white;">
    	<p id="introduction">&nbsp;&nbsp;SpringMVC 框架搭建&nbsp;&nbsp;</p>
	    <div>
			<img src="<%=basePath%>imageFile/1.jpg" id="imagesId"/>
	    </div>
    </div> 
     
    <div region="center" style="width:70%; padding:5px;"> <!-- ########### -->  
  	<!-- 主界面 -->	
		<table id="dg" border-color="red" style="width:80%; height:70%; background-color: red;"
			data-options="striped:true,rownumbers:true,singleSelect:false,method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar'">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<!-- 设置sortable为true,可以点击该列表头进行排序 -->
					<th data-options="field:'id',width:$(this).width() * 0,nowrap:'false',align:'left',sortable:'true'" formatter="formatPrice">Id</th>
					<th data-options="field:'firstname',width:$(this).width() * 0.2,nowrap:'false',align:'left'">firstname</th>
					<th data-options="field:'lastname',width:$(this).width() * 0.2,nowrap:'false',align:'left'">lastname</th>
					<th data-options="field:'phone',width:$(this).width() * 0.2,nowrap:'false',align:'left'">phone</th>
					<th data-options="field:'email',width:$(this).width() * 0.2,nowrap:'false',align:'left'">email</th>
					<!--<th data-options="field:'picPath',width:$(this).width() * 0.2,nowrap:'false',align:'left'">record</th>	  -->			
				</tr>
			</thead>
		</table>
	</div>
	
	    <!-- 主界面中的button -->
	    
			<div id="toolbar">
				<div id="leftButton">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-add" plain="true" onclick="addTemplateInfo()">添加</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-edit" plain="true" onclick="editTemplateInfo()">修改</a> 
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-remove" plain="true" onclick="delTemplateInfo()">删除</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" 
						iconCls="icon-add" plain="true" onclick="uploadPictureInfo()">上传图片</a>
				</div>
				<div id="rightButton">
					<label class="slabel">  Item ID:</label>
					<input id="itemid" name="itemName" style="line-height:26px;border:1px solid #ccc"/>   
					<label class="slabel">firstName:</label>
					<input id="firstNameid" name="firstName" style="line-height:26px;border:1px solid #ccc"/>			    
					<button class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()"><label class="slabel">Search</label></button>
				</div>
			</div>
		
	
	
	<!--  添加用户信息对话框 -->
	<div id="dlg" class="easyui-dialog"
		style="width:30%;height:40%;padding:0px 0px; background:#ADA; color:black;" 
		data-options="closed:true,maximizable:true,buttons:'#dlg-buttons',resizable:true,modal:true"
		>		
			<form id="ff" method="get" style="width:100%; height:100%;">

					<table cellpadding="5">
						 
						<tr>
							<td>firstname:</td>
							<td>
								<input name="firstname">
								<label id="lblTemplateName">添加</label>
							</td>
							
							<tr>
							<td>lastname:</td>
							<td>
								<input name="lastname">
								<label id="lblTemplateName">添加</label>
							</td>
							
							<tr>
							<td>phone:</td>
							<td>
								<input name="phone">
								<label id="lblTemplateName">添加</label>
							</td>
							
							<tr>
							<td>email:</td>
							<td>
								<input name="email">
								<label id="lblTemplateName">添加</label>
							</td>
							
							<tr>
						</tr>
					</table>					
			</form>		
			<!-- 修改用户信息对框框中buttons -->				
			<div id="dlg-buttons">
					<button id="add" class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="add()">add</button>
					<!-- -->
					<button id="closehref" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="closeEditor()">cancel</button> 
			</div>
			
 	</div>
 	
 	<!--  修改用户信息对话框-->
 	<div id="dlgupdate" class="easyui-dialog"
		style="width:40%;height:75%; padding:0px 0px; color:black;" closed="true"
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
							<td align="center">firstname:</td>
							<td>
								<input name="firstname" id="first">
								<label  for="first">update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">lastname:</td>
							<td>
								<input name="lastname">
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">phone:</td>
							<td>
								<input name="phone">
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">email:</td>
							<td>
								<input name="email">
								<label>update</label>
							</td></tr>
							
							<tr class="updateLabel">
							<td align="center">record:</td>
							<td>
								<input id="picPath00" name="picPath"/> 
								<label>update</label>
							</td></tr>
							
							
						</tr>
					</table>
			</form>
		  </div>  	
		  <div id="update2">
			<label align="center">picture:</label>
			<a href="<%=basePath%>picture/noBigPic.jpg" target="_blank" id="bigPicture"><img id="picture" src="E:\photo4\1.jpg" border=0 align="middle"></a></td>
		  </div>
			
			<!-- 更新用户信息对话框中的buttons -->
			<div id="dlgupdate-buttons">
					<a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon-ok" 
						onclick="edit()" style="padding:5px;">修改</a>
					<a id="closehref" href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="closeEditor()">关闭</a>
			</div>
			
 	</div>
 	
 	<!-- 上传图片对话框 -->
 	<div id="dlgupload" class="easyui-dialog"
		style="width:400px;height:400px;padding:20px 30px" closed="true"
		maximizable="true" resizable="true"
		left="300" top="200"
		buttons="#dlgupload-buttons">
						
			<form id="ffupload" method="post" enctype="multipart/form-data">
				<input id="idinfo" name="idinfo"/>
				<input type="file" name="imgFile" />
			</form> 
			<!-- 上传图片对话框中的buttons -->
			<div id="dlgupload-buttons">
					<a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon-ok" 
						onclick="upload()" style="padding:5px;">upload</a>
					<a id="closehref" href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="closeEditor()">关闭</a>
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
		
	    $(function(){
	    	// 获取邮件模板类型
	    	$.ajax({
	    		url: 'dict/r/email_type',
	    		method:'get',
	    		async:false,
	    		success:function(data) {
	    			templateType = data;
	    		}
	    	});
	    	
	    	// 获取用户类型
	    	$.ajax({
	    		url: 'dict/r/user_type',
	    		method:'get',
	    		async:false,
	    		success:function(data) {
	    			userType = data;
	    		}
	    	});
	    });
	    
	    //加载增加用户对话框
		function addTemplateInfo() {
			$('#ff').form('reset');
			$('#dlg').show();
			$('#dlg').dialog('open').dialog('setTitle', '添加人员信息');

		}
		
	    //增加用户对话框中的添加 button。
		function add() {		
			$('#ff').form('submit', {
				url:'<%=basePath%>crudUsers/save',
				method:'get',
				success:function(data){
						$.messager.alert('提示','用户信息保存成功');
						closeEditor();
					}
			});
		
		}
		
		function getTemplateType(value,row,index) {
			for (var i=0; i<templateType.length; i++) {
        		if (value == templateType[i].code) {
        			return templateType[i].name;
        		}
        	}
		}
		
		function convertUserType(value) {
			var retValue = "";
			if (value!=null) {
				var checkedUserType = value.split(",");
				for (var i=0; i<userType.length;i++) {
					for (var j=0; j<checkedUserType.length; j++) {
						if (checkedUserType[j]==userType[i].code) {
							retValue += userType[i].name+",";
							break;
						}
					}
				}
			}
			return retValue.substring(0,retValue.length-1);
		}
		
		//按条删除用户信息
		function delTemplateInfo(){										
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
						          url:'<%=basePath%>crudUsers/dele',
						          type:"GET",
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
		
		//加载更新用户对话框，并且将该用户信息显示。
		function editTemplateInfo(){
			//window.location.reload(true); //让页面刷新，以便获取最新的后台数据。如果是你要刷新某一个iframe就把window给换成frame的名字或ID号
			//window.location.reload();
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
			
			getIpAddress();//显示图片
		
		}
		
		//更新用户按钮所调用的操作。
		function edit() {
			//$('#picPath00').value("");
			$('#ffupdate').form('submit', {
				url:'<%=basePath%>crudUsers/update',
				method:'POST',
				success:function(data){
					//	closeEditor();
					//	$.messager.alert('update成功',data);
					}
			});	
			
		}
		
		//update的时候，得到图片并且将其显示出来
		function getIpAddress() {
			var row = $('#dg').datagrid('getSelections');
			var num  = row.length;
			
			//保证客户只选一条记录
			if(num > 1 || num == 0){
				$.messager.alert('提示',"请选中一条记录进行修改");
				return;
			}
						
			var idNum = row[0].id;
			var idData= {idnum:idNum};	
			//var array = new Array();
			var basePath="<%=basePath%>";
			$.ajax({
				url:'<%=basePath%>crudUsers/address',
				type:"GET",
				data:idData,
				success:function(array){
					$.messager.alert("接收",array);
					if (array[0] == "noPic"){
						//var basePath="<%=basePath%>"; 
						$('#picture').attr("src",basePath + "picture" + "/" + "no.png");
					}
					else {
						//var basePath="<%=basePath%>"; 
						$('#picture').attr("src",basePath + array[0]);
					}
					
					if (array[1] == "noPic"){
						$('#bigPicture').attr("href",basePath + "picture" + "/" + "no.png");
					}
					else
					{
						$('#bigPicture').attr("href",basePath + array[1]);
				    }
				}
			});
		}
		
		
		
		//上传图片对话框
		function uploadPictureInfo(){
			$('#ffupload').form('reset');
			$('#dlgupload').show();
			
			var row = $('#dg').datagrid('getSelections');
			var num  = row.length;
			
			//保证客户只选一条记录
			if(num > 1 || num == 0){
				$.messager.alert('提示',"请选中一条记录进行上传");
				return;
			}
			$('#dlgupload').dialog('open').dialog('setTitle', 'upload a file!');
		}
		
		//上传图片按钮所执行的操作
		function upload() {
			$('#add').css('display','none');
			$('#edit').css('display','inline');
			var row = $('#dg').datagrid('getSelections');
			//var num  = row.length;
		    
			//给表单中的idinfo赋值
			document.getElementById("idinfo").value = row[0].id;
			
			//提交表单，将idinfo(string)与文件一起提交，看看后台是否能够接收到这两个信息。
			$('#ffupload').form('submit', {
				url:'<%=basePath%>crudUsers/upload01',
				method:'POST', 
				success:function(data){
//					var basePath='<%=basePath%>';
//					$('#picture').attr("src",basePath + data);
						closeEditor();
						$.messager.alert('upload成功',"恭喜恭喜！");
					}
			});
		}
		
		
		function doSearch() {  
				var id = $("#itemid").val();
				//var id = document.getElementById("itemid"); 
				var firstName = $("#firstNameid").val();
				//var firstName = document.getElementById("firstNameid"); 
				//alert('firstName',firstName);
				var user = {id:id,firstName:firstName};
				
				$.ajax({
					url:"<%=basePath%>crudUsers/search",
					type:"post",
					data:user,
					success:function(data){
						$('#dlgupdate').show();
						$('#dlgupdate').dialog('open').dialog('setTitle', 'search one item');
						
						$("#ffupdate").form("load", data);
						
						document.getElementById("idnum").value = id;
						
						//提交表单，将idinfo(string)与文件一起提交，看看后台是否能够接收到这两个信息。
						$('#PicAddress').form('submit', {
							url:'<%=basePath%>crudUsers/address',
							method:'POST', 
							success:function(array){
								var basePath='<%=basePath%>';
								var arr0 = array.replace("[","");
								var arr1 = arr0.replace("]","");
								var arr2 = arr1.replace(/\"/g,""); //正则表达式，去掉字符串中的双引号

//								var arr = arr2.split(",");
//								$('#picture').attr("src",basePath + arr[0]);
//								if (arr[1] !== null) {
//									$('#bigPicture').attr("href",basePath + arr[1]);
//								}
								
								
								var arr = arr2.split(",");
								
								if (arr[0] == "noPic"){
									$('#picture').attr("src",basePath + "imageFile" + "/" + "1.png");
								}
								else {
									$('#picture').attr("src",basePath + arr[0]);
								}
								
								if (arr[1] == "noPic"){
									$('#bigPicture').attr("href",basePath + "picturePath" + "/" + "1.png");
								}
								else
								{
									$('#bigPicture').attr("href",basePath + arr[1]);
							    }
									//closeEditor();
								$.messager.alert('查询成功',array);
							}
						});
						//$.messager.alert('picture path!',data.picPath);
						
					}
				});
			
		} 
		
		function clearForm() {
			ue.execCommand('cleardoc');
		}
		
		//关闭所有的对话框，add，update，delete，upload
		function closeEditor() {
		//	$('#dlg').hide();
		//	$('#dlgupdate').hide();
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
		 
		 //tree
		function expandAll(treeName) {
            var node = $('#' + treeName).tree('getSelected');
            if (node) {
                $('#' + treeName).tree('expandAll', node.target);
            }
            else {
                $('#' + treeName).tree('expandAll');
            }
        }
		 
        function collapseAll(treeName) {
            var node = $('#' + treeName).tree('getSelected');
            if (node) {
                $('#' + treeName).tree('collapseAll', node.target);
            }
            else {
                $('#' + treeName).tree('collapseAll');
            }
        }
        
        
     </script>
     
  </body>
</html>
