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
<div style="padding-left:100px">
<div style="min-width:150px;width:150px;background-color:white;border:1px solid #ccc;cursor:pointer">
	<div class="btn-toolbar text-center">
		<div class="btn-group">
			<button type="button" class="btn btn-xs"><i class="icon-angle-left"></i></button>
			<button type="button" class="btn btn-xs"><i class="icon-double-angle-left"></i></button>
		</div>
		<div class="btn-group">
			<select style="font-size:10px;padding:0;height:16px;width:72px" class="form-control">
				<option>2013</option>
				<option>2014</option>
				<option>2015</option>
			</select>
		</div>
		<div class="btn-group">
			<button type="button" class="btn btn-xs"><i class="icon-angle-right"></i></button>
			<button type="button" class="btn btn-xs"><i class="icon-double-angle-right"></i></button>
		</div>
	</div>
	<div>
		<table class="table table-condensed table-bordered" style="font-size:10px">
			<thead>
				<tr>
					<td>日</td>
					<td>一</td>
					<td>二</td>
					<td>三</td>
					<td>四</td>
					<td>五</td>
					<td>六</td>
				</tr>
			</thead>
		</table>
	</div>
	<div>
		<div class="row text-center" style="font-size:10px;width:150px;min-width:150px;margin:0;border-top:1px solid #ccc;padding:5px 0 5px 0">
			<div class="col-md-4 col-xs-4 col-sm-4" style="padding:0">
				<span>时</span>
				<input style="width:20px"/>
			</div>
			<div class="col-md-4 col-xs-4 col-sm-4" style="padding:0">
				<span>分</span>
				<input style="width:20px"/>
			</div>
			<div class="col-md-4 col-xs-4 col-sm-4" style="padding:0">
				<span>秒</span>
				<input style="width:20px"/>
			</div>
		</div>
		<div class="row text-center" style="font-size:10px;width:150px;min-width:150px;margin:0;border-top:1px solid #ccc;padding:5px 0 5px 0">
			<div class="col-md-4 col-xs-4 col-sm-4" style="padding:0">
				<button class="btn btn-xs" style="font-size:10px">今天</button>
			</div>
			<div class="col-md-4 col-xs-4 col-sm-4" style="padding:0">
				<button class="btn btn-xs" style="font-size:10px">取消</button>
			</div>
			<div class="col-md-4 col-xs-4 col-sm-4" style="padding:0">
				<button class="btn btn-xs" style="font-size:10px">确定</button>
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
$(function(){
	$('#sku').f_input_combobox({datas:[{k:'hello1',v:1},{k:'hello2',v:2},{k:'nihao',v:1},{k:'wangyang',v:2},{k:'guohui',v:1},{k:'fengming',v:2}]});
	$('#brandId').f_combobox({datas:[{k:'hello1',v:1},{k:'hello2',v:2}]});
})
</script>
</body>
</html>