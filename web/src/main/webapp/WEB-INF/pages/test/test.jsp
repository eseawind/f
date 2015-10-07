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
			<input name="gname" f-type="text" f-options="required:true,maxLength:14,minLength:4,errMsg:'taichagnl',blur:function(){this.f_input_text('isValid')}"/>
		</div>
		<div class="form-group">
			<label>sku</label>
			<input name="sku" id="sku"/>
		</div>
		<div class="form-group">
			<label>价格</label>
			<input name="price" f-type="number" f-options="precision:3,errMsg:'数字输入错误',blur:function(){this.f_input_number('isValid')}" />
		</div>
		<div class="form-group">
			<label>品牌</label>
			<select name="brandId" id="brandId"></select>
		</div>
		<div class="form-group">
			<label>时间</label>
			<input name="time" id="time" />
		</div>
	</div>
</form>
</div>

<script type="text/javascript">
$(function(){
	$('#sku').f_input_combobox({datas:[{k:'hello1',v:1},{k:'hello2',v:2},{k:'nihao',v:1},{k:'wangyang',v:2},{k:'guohui',v:1},{k:'fengming',v:2}]});
	$('#brandId').f_combobox({datas:[{k:'hello1',v:1},{k:'hello2',v:2}]});
	$('#time').f_input_datepicker({});
})
</script>
</body>
</html>