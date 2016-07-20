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
    <style type="text/css">
    	body {background-color: red}
    	.HangTr th {font-size:30pt;}
    </style>
    <script type="text/javascript" charset="utf-8">
	    $(document).ready(function(){  
	    	closeEditor();
	    	loadGrid();
	    	loadTree();
		});  

	 
		function loadGrid()  {
			$('#dg').datagrid({
				title: '功能菜单',
				nowrap:false,
				loadMsg:'加载中，请稍候...',
				fitColumns:true,
				width: function () { return document.body.clientWidth * 0.9 },
				pagination : true,//分页工具栏
				pageNumber : 1,//初始页码
				pageSize : 15,
				pageList : [15,30,45,60],
				detailFormatter:function(index,row){
					return '<div style="padding:5px"><table id="ddv-' + index + '"></table></div>';
				},
				rowStyler:function(index,row){ //EasyUI 行格式化。
					if (row.id<20){
						return 'background-color:pink;color:blue;font-weight:bold;';
					}
				},
				
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

	</script>
    
 </head>
  
 <body class="easyui-layout">
 <!--    <div region="south" title="South Title" split="true" style="height:100px;">
    	<a href="###" id="box" title="这是内容提示框">Hover me</a>
    </div>   -->
    
    <div region="east" iconCls="icon-reload" title="East" split="true" style="width:100px;">
	    <h2>Basic Calendar</h2>
		<p>Click to select date.</p>
		<div style="margin:20px 0"></div>
		<div class="easyui-calendar" style="width:200px;height:200px;"></div>
		
		<ul class="ulist focuslistnews">
                                        <li class="bold-item">
                        <a href="http://news.baidu.com/z/2015lianghui/zhuanti.html" mon="ct=1&amp;a=2&amp;c=top&pn=1" target="_blank">专题：新疆自治区主席解析近年暴恐事件频发原因</a></li>
							    			                                                                <li>
                        <a href="http://china.cankaoxiaoxi.com/roll10/2015/0314/704271.shtml" mon="ct=1&amp;a=2&amp;c=top&pn=2" target="_blank">民政部长建议“兑现捐赠承诺”入慈善法</a></li>
							    			                                                                <li>
                        <a href="http://news.china.com.cn/2015-03/14/content_35050840.htm" mon="ct=1&amp;a=2&amp;c=top&pn=3" target="_blank">国际油价继续下跌 一周以来暴跌近10%</a></li>
							    			                                                                <li>
                        <a href="http://china.cankaoxiaoxi.com/roll10/2015/0314/704279.shtml" mon="ct=1&amp;a=2&amp;c=top&pn=4" target="_blank">中斐互免签证今日生效 持护照入境可停留30天</a></li>
							    			                                                                <li>
                        <a href="http://china.cankaoxiaoxi.com/roll10/2015/0314/704506.shtml" mon="ct=1&amp;a=2&amp;c=top&pn=5" target="_blank">受缅甸航路影响 东航取消多个昆明至缅甸航班</a></li>
							    			                                                                <li>
                        <a href="http://huaxi.media.baidu.com/article/16308061323067447965" mon="ct=1&amp;a=2&amp;c=top&pn=6" target="_blank">多名人大代表建议阻断“缺陷婴儿”出生</a></li>
							    					</ul>
                    <ul class="ulist focuslistnews" >
				    			                                                                <li class="bold-item">
                        <a href="http://baijia.baidu.com/?tn=topic&topicid=x19f3caE" mon="ct=1&amp;a=2&amp;c=top&pn=7" target="_blank">媒体称滴滴将推大众专车 互联网倒逼出租车行业改革</a></li>
							    			                                                                <li>
                        <a href="http://wanghaitao.baijia.baidu.com/article/49437" mon="ct=1&amp;a=2&amp;c=top&pn=8" target="_blank">交通部长：永不允许私家车进专车</a>&nbsp;<a href="http://liushan.baijia.baidu.com/article/49389" mon="ct=1&amp;a=2&amp;c=top&pn=8" target="_blank">交通部为什么任性</a></li>
							    			                                                                <li>
                        <a href="http://news.cnr.cn/native/gd/20150314/t20150314_518000022.shtml" mon="ct=1&amp;a=2&amp;c=top&pn=9" target="_blank">媒体：某市花数千万元装修豪华会议室后不敢使用</a></li>
							    			                                                                <li>
                        <a href="http://huaxi.media.baidu.com/article/16571783572167104291" mon="ct=1&amp;a=2&amp;c=top&pn=10" target="_blank">山西：国企高管年薪必须“一年一晒”</a></li>
							    			                                                                <li>
                        <a href="http://world.huanqiu.com/exclusive/2015-03/5905075.html" mon="ct=1&amp;a=2&amp;c=top&pn=11" target="_blank">日本官房长官：中国阅兵是一味强调过去</a></li>
							    			                                                                <li>
                        <a href="http://world.cankaoxiaoxi.com/bd/20150314/704082.shtml" mon="ct=1&amp;a=2&amp;c=top&pn=12" target="_blank">英国回应美国不满：加入亚投行符合英国家利益</a></li>
							    					</ul>
                    <ul class="ulist focuslistnews" >
				    			                                                                <li class="bold-item">
                        <a href="http://politics.gmw.cn/2015-03/14/content_15098221.htm" mon="ct=1&amp;a=2&amp;c=top&pn=13" target="_blank">第二批中央公车下周拍卖 包括林肯等30多辆豪车</a></li>
							    			                                                                <li>
                        <a href="http://world.cankaoxiaoxi.com/roll10/2015/0314/704547.shtml" mon="ct=1&amp;a=2&amp;c=top&pn=14" target="_blank">缅甸一艘渡轮倾覆近100名乘客死亡 仍有人失踪</a></li>
							    			                                                                <li>
                        <a href="http://world.people.com.cn/n/2015/0314/c1002-26693169.html" mon="ct=1&amp;a=2&amp;c=top&pn=15" target="_blank">泰国景点厕所贴禁止洗脚告示 一小时后中国游客被罚</a></li>
							    			                                                                <li>
                        <a href="http://world.huanqiu.com/article/2015-03/5910064.html" mon="ct=1&amp;a=2&amp;c=top&pn=16" target="_blank">美国警告越南：勿让俄罗斯使用金兰湾空军基地</a></li>
							    			                                                                <li>
                        <a href="http://china.huanqiu.com/article/2015-03/5909792.html" mon="ct=1&amp;a=2&amp;c=top&pn=17" target="_blank">安徽原副省长倪发科悔过书：愧对六安700万人民</a></li>
							    			                                                                <li>
                        <a href="http://huaxi.media.baidu.com/article/16539062544209560400" mon="ct=1&amp;a=2&amp;c=top&pn=18" target="_blank">郑州拆除22个快速公交站台引质疑 单个造价百万元</a></li>
							    					</ul>
                    <ul class="ulist focuslistnews" >
				    			                                                                <li class="bold-item">
                        <a href="http://cnews.chinadaily.com.cn/2015-03/14/content_19810453.htm" mon="ct=1&amp;a=2&amp;c=top&pn=19" target="_blank">收藏家2.8亿拍回明朝唐卡 曾2.2亿买鸡缸杯(图)</a></li>
							    			                                                                <li>
                        <a href="http://world.huanqiu.com/exclusive/2015-03/5909278.html" mon="ct=1&amp;a=2&amp;c=top&pn=20" target="_blank">美国发布新海洋战略：保持威慑力 避免对华冲突</a></li>
							    			                                                                <li>
                        <a href="http://cnews.chinadaily.com.cn/2015-03/14/content_19809731.htm" mon="ct=1&amp;a=2&amp;c=top&pn=21" target="_blank">张震前秘书：张震上将101岁了 身体很好</a></li>
							    			                                                                <li>
                        <a href="http://huaxi.media.baidu.com/article/16610705416227360880" mon="ct=1&amp;a=2&amp;c=top&pn=22" target="_blank">上海再发袭警案 一交警深夜查酒驾被殴打致脑震荡</a></li>
							    			                                                                <li>
                        <a href="http://news.xinhuanet.com/politics/2015lh/2015-03/14/c_1114639550.htm" mon="ct=1&amp;a=2&amp;c=top&pn=23" target="_blank">山东政法委书记：聂树斌案复查结果两会后见分晓</a></li>
							    			                                                                <li>
                        <a href="http://society.cankaoxiaoxi.com/roll10/2015/0314/704429.shtml" mon="ct=1&amp;a=2&amp;c=top&pn=24" target="_blank">辽宁一大学公寓大门被焊 数千学生翻墙搬家(图)</a></li>
							    					</ul>
                    <ul class="ulist focuslistnews" >
				    			                                                                <li class="bold-item">
                        <a href="http://huaxi.media.baidu.com/article/16322154674123261979" mon="ct=1&amp;a=2&amp;c=top&pn=25" target="_blank">四川2千岁&quot;树王&quot;再度病危 已被抢救三次(图)</a></li>
							    			                                                                <li>
                        <a href="http://news.xinhuanet.com/local/2015-03/14/c_1114640357.htm" mon="ct=1&amp;a=2&amp;c=top&pn=26" target="_blank">南京清代古宅遭房企故意破坏 司法部门介入调查</a></li>
							    			                                                                <li>
                        <a href="http://society.cankaoxiaoxi.com/roll10/2015/0314/704277.shtml" mon="ct=1&amp;a=2&amp;c=top&pn=27" target="_blank">陕西村民在堆放垃圾大坑内发现明朝古墓葬</a></li>
							    			                                                                <li>
                        <a href="http://news.youth.cn/yl/201503/t20150314_6525090.htm" mon="ct=1&amp;a=2&amp;c=top&pn=28" target="_blank">成龙经纪人回应吴绮莉藏毒虐女事件：什么都不知道</a></li>
							    			                                                                <li>
                        <a href="http://news.cnr.cn/native/gd/20150314/t20150314_518000037.shtml" mon="ct=1&amp;a=2&amp;c=top&pn=29" target="_blank">99岁老人每月喝20多斤烧酒 称100岁再讨个老婆</a></li>
							    			                                                                <li>
                        <a href="http://news.youth.cn/sh/201503/t20150314_6524858.htm" mon="ct=1&amp;a=2&amp;c=top&pn=30" target="_blank">内蒙煤老板被债主拘禁山沟53天 因近视未逃脱</a></li>
							    			                                    <!--MONITOR INDEX_FOCUS OK 30 HOTAREA-->
            </ul>
    </div> 
     
    <div region="west" split="true" title="West" style="width:100px;">
    	    <ul class="easyui-tree">
	        <li>
	            <span>Folder</span>
	            <ul>
	                <li>
	                    <span>Sub Folder 1</span>
	                    <ul>
	                        <li><span>File 11</span></li>
	                        <li><span>File 12</span></li>
	                        <li><span>File 13</span></li>
	                    </ul>
	                </li>
	                <li><span>File 2</span></li>
	                <li><span>File 3</span></li>
	            </ul>
	        </li>
	        
	        <li>
	        	<span>File21</span>
	        </li>
	    </ul>
    </div> 
     
    <div region="center" title="center title" style="padding:5px;background:#eee;"> <!-- ########### -->  
  	<!-- 主界面 -->	
		<table id="dg" border="1" title="BruceYY" style="width:80%;height:80%"
			data-options="striped:true,rownumbers:true,singleSelect:false,url:'crudUsers/getall',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar'">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<!-- 设置sortable为true,可以点击该列表头进行排序 -->
					<th data-options="field:'id',width:$(this).width() * 0,nowrap:'false',align:'left',sortable:'true'" formatter="formatPrice">模板Id</th>
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
				<div style="margin-bottom:5px">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-add" plain="true" onclick="addTemplateInfo()">添加</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-edit" plain="true" onclick="editTemplateInfo()">修改</a> 
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-remove" plain="true" onclick="delTemplateInfo()">删除</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" 
						iconCls="icon-add" plain="true" onclick="uploadPictureInfo()">上传图片</a>	
				</div>
				<div>
					<span>  Item ID:</span>
					<input id="itemid" name="itemName" style="line-height:26px;border:1px solid #ccc">   
					<span>firstName:</span>
					<input id="firstNameid" name="firstName" style="line-height:26px;border:1px solid #ccc">			    
					<button class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch()">Search</button>
				</div>
			</div>
		
	
	
	<!--  添加用户信息对话框 -->
	<div id="dlg" class="easyui-dialog"
		style="width:30%;height:40%;padding:0px 0px; background:#ADA; color:black;" closed="true"
		maximizable="true" resizable="true"
		modal="true"
		buttons="#dlg-buttons">		
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
		style="width:30%;height:40%; padding:0px 0px; background:#ADA; color:black;" closed="true"
		maximizable="true" resizable="true"
		buttons="#dlgupdate-buttons">		
			<form id="ffupdate" method="get" style="width:100%; height:100%;">

					<table cellpadding="5">
						 
						<tr>
							<td>
								<input type="hidden" name="id">
							</td>
							
							<tr>												
							<td align="center">firstname:</td>
							<td>
								<input name="firstname" id="first">
								<label id="lblTemplateName" for="first">update</label>
							</td></tr>
							
							<tr>
							<td align="center">lastname:</td>
							<td>
								<input name="lastname">
								<label id="lblTemplateName">update</label>
							</td></tr>
							
							<tr>
							<td align="center">phone:</td>
							<td>
								<input name="phone">
								<label id="lblTemplateName">update</label>
							</td></tr>
							
							<tr>
							<td align="center">email:</td>
							<td>
								<input name="email">
								<label id="lblTemplateName">update</label>
							</td></tr>
							
							<tr>
							<td align="center">record:</td>
							<td>
								<input id="picPath00" name="picPath"/> 
								<label id="lblTemplateName">update</label>
							</td></tr>
							
							
						</tr>
					</table>
			</form>
			
			<tr>
			<td>picture:</td>
			<td>
				<a href="<%=basePath%>picture/noBigPic.jpg" target="_blank" id="bigPicture"><img id="picture" src="E:\photo4\1.jpg" border=0 align="middle"></a></td>
			</td></tr>
			
			<form id="PicAddress" method="post">
				<input id="idnum" name="idnum"/>
			</form>
			
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
						$.messager.alert('用户信息保存成功',data);
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
			//$('#add').css('display','none');
			//$('#edit').css('display','inline');
			var row = $('#dg').datagrid('getSelections');
			var num  = row.length;
			var array   =   new   Array();
			//保证客户只选一条记录
			if(num > 1 || num == 0){
				$.messager.alert('提示',"请选中一条记录进行修改");
				return;
			}
		    
			//给表单中的idinfo赋值
			document.getElementById("idnum").value = row[0].id;
			
			//提交表单，将idinfo(string)与文件一起提交，看看后台是否能够接收到这两个信息。
			$('#PicAddress').form('submit', {
				url:'<%=basePath%>crudUsers/address',
				method:'POST', 
				success:function(array){
					var basePath='<%=basePath%>';
					var arr0 = array.replace("[","");
					var arr1 = arr0.replace("]","");
					var arr2 = arr1.replace(/\"/g,""); //正则表达式，去掉字符串中的双引号

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
					//$.messager.alert('获取Address成功',array);
				}
			});
		}
		
		
		
		//加载上传图片对话框
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
			var num  = row.length;
		    
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
