<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/mcommons.jsp" %>
</head>
<body class="container">
<%@include file="../commons/mhead.jsp" %>
<div id="container">
	
</div>
<script type="text/tmpl" id="tmpl">
	<div class="panel panel-default">
		<div class="panel-body form">
			<div class="form-group">
				<label>订单号：</label>
				<span>{{= order.orderNum}}</span>
			</div>
			<div class="form-group">
				<label>订单状态：</label>
				<span>
				{{if order.isPaid==1}}
					未付款
				{{else order.status == 1}}
					未确认
				{{else order.status == 2}}
					确认
				{{else order.status == 3}}
					处理中
				{{else order.status == 4}}
					已发货
				{{else order.status == 5}}
					已完成
				{{else order.status == 6}}
					退货中
				{{else order.status == 7}}
					已完成	
				{{/if}}
				</span>
			</div>
			<div class="form-group">
				<label>订单金额：</label>
				<span>{{= order.orderPrice}}</span>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">收货人信息</div>
		<div class="panel-body form">
			<div class="form-group">
				{{= order.provinceName}}&nbsp;&nbsp;{{= order.cityName}}&nbsp;&nbsp;{{= order.areaName}}
			</div>
			<div class="form-group">
				收货人：{{= order.consignee}}&nbsp;&nbsp;电话：{{= order.mobile}}
			</div>
			<div class="form-group">
				地址：{{= order.remark}}
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">商品列表</div>
		<div class="panel-body">
			<ul class="list-group">
			{{each ods}}
				<li class="list-group-item form-inline">
					<img src="{{= imgUrl}}{{= $value.photo}}" style="width:80px;height:80px">&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="{{= dynUrl}}/goods/detail/{{= $value.cgoodsId}}.htm">{{= $value.gname}}-{{= $value.cgname}}</a>&nbsp;x&nbsp;{{= $value.number}}
				</li>
			{{/each}}
			</ul>
		</div>
	</div>
</script>
<script type="text/javascript">
$(function(){
	f.setTitle("订单详情页");
	var orderId = f.parseUrlParam(window.location.href)["orderId"];
	$.getJSON(f.dynUrl+"/orders/mdetail.htm",{orderId:orderId},function(d){
		if(d.success){
			var obj = d.result;
			obj.dynUrl = f.dynUrl;
			obj.imgUrl = f.imgUrl();
			$("#container").append($("#tmpl").tmpl(obj));
		}else{
			f.dialogAlert(d.errMsg);
		}
	});
})
</script>
</body>
</html>