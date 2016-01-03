<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/bcommons.jsp" %>
</head>
<body class="container">
<%@include file="../commons/bhead.jsp" %>
<div id="dgContainer">
<button class="btn btn-success" id="addBtn"><i class="icon-plus"></i>新增活动</button>
<hr/>
<div id="dg"></div>
</div>
<div id="addWin">
</div>
<script type="text/javascript">
$(function(){
	$("#dg").f_dg({
		filter:function(d){
			return {rows:d.result.entry,count:d.result.total};
		},
		url:f.dynUrl+"/dynpage/list.htm",
		pagination:true,
		columns:[{
			field:"id",title:"ID"
		},{
			field:"name",title:"名称"
		},{
			field:"typeName",title:"类型名称"
		},{
			field:"createtime",title:"创建时间"
		},{
			field:"id",title:"操作"
		}]
	});
	var dgCon = $("#dgContainer");
	var addWin = $("#addWin");
	$("#addBtn").click(function(){
		dgCon.slideUp();
		addWin.slideDown();
	});
})
</script>
</body>
</html>