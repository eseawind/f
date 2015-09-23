<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/mcommons.jsp" %>
<script type="text/javascript" src="${staUrl }/js/f.datagrid.js"></script>
</head>
<body>
	<div id="hello"></div>
<script type="text/javascript">
$(function(){
	$("#hello").f_dg({
		columns:[{
			name:"hello1",
			field:"ok1"
		},{
			name:"hello2",
			field:"ok2"
		},{
			name:"hello3",
			field:"ok3"
		},{
			name:"hello4",
			field:"ok4"
		}],
		pagination:true
	});
})
</script>
</body>
</html>