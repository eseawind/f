<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/mcommons.jsp" %>
<title>${def.gname }|${def.cgname }</title>
</head>
<body class="container">
<%@ include file="../commons/mhead.jsp" %>
<div class="text-center">
	<div id="carousel"></div>
</div>
<ul class="list-group">
	<li class="list-group-item" style="color:red"><h4>￥&nbsp;${dyn.price }</h4></li>
	<li class="list-group-item">${def.gname }</li>
</ul>
<div class="panel panel-default">
	<div class="panel-heading">商品规格</div>
	<div class="panel-body form-inline">
		<c:forEach items="${cgs}" var="cg">
			<c:choose>
				<c:when test="${cg.cgid == def.cgid }">
					<div class="form-group"><a href="${dynUrl }/goods/detail/${cg.cgid}.htm" class="btn btn-default btn-danger">${cg.cgname }</a></div>
				</c:when>
				<c:otherwise>
					<div class="form-group"><a href="${dynUrl }/goods/detail/${cg.cgid}.htm" class="btn btn-default">${cg.cgname }</a></div>
				</c:otherwise>
			</c:choose>
			
		</c:forEach>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">
		商品详情
	</div>
	<div class="panel-body">
		${def.descript }
	</div>
</div>
<script type="text/javascript">
$(function(){
	f.setTitle("商品详情页");
})
</script>
</body>
</html>