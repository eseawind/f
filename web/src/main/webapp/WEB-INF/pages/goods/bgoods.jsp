<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/bcommons.jsp" %>
</head>
<body>
<div class="container">
	<%@ include file="../commons/bhead.jsp" %>
<form >
	<div class="form-inline">
		<div class="form-group">
			<label>商品名称</label>
			<input class="form-control" name="gname"/>
		</div>
		<div class="form-group">
			<label>sku</label>
			<input class="form-control" name="sku"/>
		</div>
		<div class="form-group">
			<label>sku</label>
			<input class="form-control" name="sku"/>
		</div>
		<div class="form-group">
			<label>品牌</label>
			<select class="form-control" name="brandId"></select>
		</div>
	</div>
</form>
</div>

</body>
</html>