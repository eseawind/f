<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../commons/mcommons.jsp" %>
</head>
<body class="container">
<%@include file="../commons/mhead.jsp" %>
<div id="container">
	
</div>
<script type="text/javascript">
$(function(){
	f.setTitle("我的购物车");
	var container = $("#container");
	container.startMask({message:"数据加载中"});
	$.getJSON(f.dynUrl+"/cart/settle.htm",function(d){
		container.closeMask();
		if(d.success){
			$.each(d.result.settlements,function(i,obj){
				container.append(f.cartBuilder(obj));
			});
		}else{
			f.dialogAlert(d.errMsg);
		}
	});
})
</script>
</body>
</html>