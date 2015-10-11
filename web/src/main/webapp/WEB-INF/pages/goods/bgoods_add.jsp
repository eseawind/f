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
<div>
	<div class="form">
		<div class="form-group">
			<label>商品名称：</label>
			<input class="form-control" name="gname" f-type="text" f-options="required:true,maxLength:254"/>
		</div>
	</div>
	<div class="form-inline">
		<div class="form-group" style="display:inline;float:left">
			<label>sku:</label>
			<input class="form-control" style="width:216px" name="sku" f-type="text" f-options="required:false,maxLength:32"/>
		</div> 
		<div class="form-group" style="display:inline;float:left">
			<label>描述:</label>
			<input class="form-control" style="width:516px" name="remark" f-type="text" f-options="required:false,maxLength:254"/>
		</div>
		<div class="form-group">
			<label>品牌:</label>
			<select class="form-control" style="width:216px" name="brandId" f-type="combobox" f-options="required:false"></select>
		</div>
	</div>
</div>
</div>
</body>
</html>