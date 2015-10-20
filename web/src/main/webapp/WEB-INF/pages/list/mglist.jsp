<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/mcommons.jsp" %>
</head>
<body>
	<%@ include file="../commons/mhead.jsp" %>
	<div class="container">
		<ul class="nav nav-tabs row text-center" id="tabs">
		  <li role="presentation" class="active col-md-4 col-sm-4 col-xs-4"><a href="#">综合</a></li>
		  <li role="presentation" class="col-md-4 col-sm-4 col-xs-4"><a href="#">销量</a></li>
		  <li role="presentation" class="col-md-4 col-sm-4 col-xs-4"><a href="#">价格</a></li>
		</ul>
		<div id="godosContainer">
			
		</div>
	</div>
<script type="text/javascript">
$(function(){
	var tabs = $("#tabs")
	tabs.children("li").each(function(){
		var li = $(this);
		li.click(function(){
			tabs.children("li").removeClass("active")
			li.addClass("active");
		});
	});
})
</script>
</body>
</html>