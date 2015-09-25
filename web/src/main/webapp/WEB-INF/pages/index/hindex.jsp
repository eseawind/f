<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/mcommons.jsp" %>
<script src="${staUrl }/js/f.dg.js" type="text/javascript"></script>
</head>
<body>
<div id="dg" class="container"></div>
<script type="text/javascript">
$(function(){
	$("#dg").f_dg({
		url:"${staUrl}/mid_autumn.json",
		columns:[{
			field:"type",
			title:"类型"
		},{
			field:"name",
			title:"名称"
		},{
			field:"ids",
			title:"序号"
		}],
		check:true,
		pagination:true
	});
})
</script>
</body>
</html>