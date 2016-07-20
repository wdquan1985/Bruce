$(document).ready(function(){
    getRegInfo();
});
 
function getRegInfo(){
    var id = $.trim($("#id").value());
    var name =  $.trim($("#name").value());
    var rand = Math.random();
    
    var page = {id:id,name:name,rand:rand};
    
    $.ajax( {
        url:"/user/data/pageExport.do",
        type:"post",
        async:false,
        data:page,
//      dataType : 'json',
        //contentType : "application/x-www-form-urlencoded; charset=utf-8",
        success:function(data, textStatus) {
            var json = eval(data);
            changePage(json);
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        alert("服务器请求失败！");
    }
    });
}
 
function changePage(json){
    var title = [];
    title.push( "<th>id</th>");
    title.push( "<th>姓名</th>");
    title.push( "<th>密码</th>");
    $("#tabTitle").html(title.join(""));
    var body = [];
    if (json == undefined || json.length == 0) {
        body.push( "<tr><td colspan='3'>没有数据！</td></tr>");
    } else {
        for(var i = 0, len = json[0].list.length;i < len; i++){
            body.push( "<tr>");
            body.push( "<td>"+(json[0].list[i].id == null ? "" : json[0].list[i].id)+"</td>")
            body.push( "<td>"+(json[0].list[i].name == null ? "" : json[0].list[i].name)+"</td>")
            body.push( "<td>"+(json[0].list[i].psw == null ? "" : json[0].list[i].psw)+"</td>")
            body.push( "</tr>")
             
        }
    }
     $("#tabBody").html(body.join(""));
     $("#currentPage").text(json[0].currentPage); 
     $("#totalCount").text(json[0].totalCount); 
     $("#totalPage").text(json[0].totalPage); 
    // $("#pageSize").html("<option value='"+pageSize+"' selected>"+pageSize+"</option>")
     $(".selector").val(pageSize);
}
//翻页
function goto_page(currentPage) {
    var curp = $("#currentPage").text();
    var totalPage = $("#totalPage").text();
    if(currentPage == "next"){
        curp = curp*1+1*1;
    }
    if(currentPage == "pre"){
        curp = curp*1 - 1*1;
    }
    if(currentPage == "first"){
        curp = 1;
    }
    if(currentPage == "last"){
        curp = totalPage;
    }
    if(currentPage == "changePage"){
        curp = curp;
    }
    if(currentPage == "jump"){
        curp = $("#page").value();
    }
    var pageSize = $("#pageSize").value();//每页显示多少行
    if (pageSize == null || pageSize == "" || pageSize == undefined) {
        pageSize = 10; // 默认每页10条
    }
    var rand = Math.random();
    var id = $.trim($("#id").value());
    var name =  $.trim($("#name").value());
    var page = {id:id,name:name,rand:rand,currentPage:curp,pageSize:pageSize};
    
    $.ajax( {
        url:"/user/data/pageExport.do",
        type:"post",
        async:false,
        data:page,
        success:function(data, textStatus) {
            var json = eval(data);
            changePage(json);
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        alert("服务器请求失败！");
    }
    });
}
function exportExcel(){
    var id = $.trim($("#id").value());
    var name =  $.trim($("#name").value());
    var rand = Math.random();
}