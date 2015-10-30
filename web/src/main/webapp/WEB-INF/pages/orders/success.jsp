<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/mcommons.jsp" %>
</head>
<body class="container">
<%@include file="../commons/mhead.jsp" %>
<div class="panel panel-default">
	<div class="panel-heading text-center">提交订单成功</div>
	<div class="panel-body form text-center">
		<div class="form-group">
			<label>订单号：</label>
			<p>${orderNum }</p>
		</div>
		<div class="form-group">
			<label>应付金额：</label>
			<p style="color:red">￥&nbsp;${payPrice }</p>
		</div>
	</div>
	<div class="panel-footer text-center">
		<c:if test="${payPrice gt 0.00}">
			<a href="#" class="btn btn-success">立即支付</a><span style="color:red">未支付订单24小时后系统将自动取消</span>
		</c:if>
		<c:if test="${payPrice eq 0.00}">
			<a href="#" class="btn btn-success">查看订单详情</a><a href="${staUrl }/page/index/mindex.htm">去购物</a>
		</c:if>
	</div>
</div>
</body>
</html>