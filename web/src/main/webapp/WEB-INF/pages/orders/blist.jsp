<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/bcommons.jsp" %>
</head>
<body class="container">
<%@include file="../commons/bhead.jsp" %>
<div id="queryForm">
	<div class="form-inline">
		<div class="form-group">
			<label>订单号：</label>
			<input class="form-control" name="orderNum"/>
		</div>
		<div class="form-group">
			<label>用户名：</label>
			<input class="form-control" name="username"/>
		</div>
		<div class="form-group">
			<label>时间范围：</label>
			<input class="form-control" name="sdate" f-type="datepicker" id="sdate"/>-
			<input class="form-control" name="edate" f-type="datepicker" id="edate"/>
		</div>
		<button class="btn btn-default" id="queryBtn"><i class="icon-search"></i>查询</button>
	</div>
</div>
<hr/>
<div class="row">
	<div class="btn-group col-md-10" id="btns">
		<button class="btn btn-default btn-primary" onclick="conditionFun()">全部订单</button>
		<button class="btn btn-default" onclick="conditionFun(1,null,1)">未付款</button>
		<button class="btn btn-default" onclick="conditionFun(1,2,2)">待出库</button>
		<button class="btn btn-default" onclick="conditionFun(1,4,null)">已出库</button>
		<button class="btn btn-default" onclick="conditionFun(1,5,null)">买家已收货</button>
		<button class="btn btn-default" onclick="conditionFun(2,null,null)">已取消</button>
		<button class="btn btn-default" onclick="conditionFun(1,6,null)">退换货订单</button>
	</div>
	<div class="btn-group col-md-2">
		<button class="btn btn-default" onclick="excel()"><i class="icon-signout"></i>批量导出</button>
	</div>
</div>
<hr/>
<div class="btn-group">
	<button class="btn btn-default" onclick="outstocks()">批量出库</button>
	<button class="btn btn-default">批量打印快递单</button>
</div>
<table class="table table-bordered table-condensed">
	<thead>
		<tr>
			<th><input type="checkbox" id="allCheck"/>&nbsp;&nbsp;商品信息</th>
			<th>数量</th>
			<th>金额</th>
			<th>优惠</th>
			<th>当前状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody id="container">
		
	</tbody>
</table>
<div class="text-right">
	&nbsp;&nbsp;总订单数：<span id="countNum"></span>&nbsp;&nbsp;
	<button id="preBtn" class="btn btn-default"><i class="icon-arrow-left"></i></button>
	第&nbsp;&nbsp;<span id="curNum"></span>&nbsp;&nbsp;页
	<button id="nextBtn" class="btn btn-default"><i class="icon-arrow-right"></i></button>
	&nbsp;&nbsp;跳转到第&nbsp;&nbsp;<input id="skipPage" style="width:50px" f-type="number"/>&nbsp;&nbsp;页
	&nbsp;&nbsp;<button class="btn btn-default btn-xs" id="skipBtn">跳转</button>
</div>
<script type="text/tmpl" id="tmpl">
<tr class="info">
	<td><input type="checkbox" f-id="{{= id}}"/>&nbsp;&nbsp;订单号：{{= orderNum}}</td>
	<td colspan="5">货款金额：{{= productPrice}}&nbsp;&nbsp;&nbsp;&nbsp;下单时间：{{= createtime}}&nbsp;&nbsp;&nbsp;&nbsp;付款时间：{{= payTime}}</td>
</tr>
{{each(rowIndex,od) ods}}
<tr>
	<td>
		<img style="width:80px;height:80px;float:left" src="{{= imgUrl}}{{= od.photo}}">
		<div>
		&nbsp;&nbsp;名称：<a href="{{= dynUrl}}/goods/detail/{{= od.cgoodsId}}.htm" target="_blank">{{= od.gname}}</a><br/>
		&nbsp;&nbsp;规格：{{= od.cgname}}
		</div>
	</td>
	<td style="width:150px;vertical-align: middle">
		货号：{{= od.sku}}<br/>
		数量：{{= od.number}}
	</td>
	{{if rowIndex == 0}}
	<td style="width:200px;vertical-align: middle" rowspan="{{= ods.length}}">
		订单金额：{{= orderPrice}}<br/>
		付款金额：{{= paidPrice}}<br/>
		支付方式：{{= payname}}
	</td>
	<td style="width:200px;vertical-align: middle" rowspan="{{= ods.length}}">
		无优惠信息
	</td>
	<td style="width:100px;vertical-align: middle" class="text-center" rowspan="{{= ods.length}}">
		{{if isPaid==1}}
			未付款
		{{else status == 1}}
			未确认
		{{else status == 2}}
			确认
		{{else status == 3}}
			处理中
		{{else status == 4}}
			已发货
		{{else status == 5}}
			已完成
		{{else status == 6}}
			退货中
		{{else status == 7}}
			已完成	
		{{/if}}
	</td>
	<td style="width:100px;vertical-align: middle" class="text-center" rowspan="{{= ods.length}}">
		{{if status < 4&&state == 1}}
		<button class="btn btn-xs btn-primary" onclick="outstock({{= id}})">出库</button><br/>
		{{/if}}
		{{if status == 6&&state == 1}}
		<button class="btn btn-xs btn-primary" onclick="refundComplete({{= id}})">退换货</button><br/>
		{{/if}}
	</td>
	{{/if}}
</tr>
{{/each}}
</script>
<script type="text/javascript">
$(function(){
	var container = $("#container"); 
	var curNum = $("#curNum");
	var countNum = $("#countNum");
	var preBtn = $("#preBtn");
	var nextBtn = $("#nextBtn");
	var skipBtn = $("#skipBtn");
	var skipPage = $("#skipPage");
	skipBtn.click(function(){
		if(skipPage.val()){
			page = parseInt(skipPage.val());
			if(page < 1){
				skipPage.val(1);
				page = 1;
			}
			loadData();
		}
	});
	window.conditionFun = function(_state,_status,_isPaid){
		page = 1;
		state = _state;
		status = _status;
		isPaid = _isPaid;
		loadData();
	}
	window.outstock = function(orderId){
		container.startMask();
		$.post(f.dynUrl+"/orders/boutstock.htm",{orderId:orderId},function(d){
			container.closeMask();
			if(d.success){
				reLoadData();
			}else{
				f.alertError(d.errMsg);
			}
		},"json");
	}
	window.outstocks = function(){
		if(status >= 4)return;
		var orderIds;
		container.find("input[type=checkbox][f-id]").each(function(){
			var obj = $(this);
			if(obj.prop("checked")){
				orderIds = obj.attr("f-id") + ",";
			}
		});
		if(orderIds){
			container.startMask();
			$.post(f.dynUrl+"/orders/boutstocks.htm",{orderIds:orderIds},function(d){
				container.closeMask();
				if(d.success){
					reLoadData();
				}else{
					f.alertError(d.errMsg);
				}
			},"json");
		}else{
			f.alertError("请勾选要出库的订单");			
		}
	}
	var allCheck = $("#allCheck").change(function(){
		if($(this).prop("checked")){
			container.find("input[type=checkbox][f-id]").each(function(){
				$(this).prop("checked","checked");
			});
		}else{
			container.find("input[type=checkbox][f-id]").each(function(){
				$(this).removeProp("checked");
			});
		}
	});
	container.delegate("input[type=checkbox][f-id]","change",function(){
		if(!$(this).prop("checked")){
			allCheck.removeProp("checked");
		}
	});
	function reLoadData(){
		page = page - 1;
		loadData();
	}
	var page = 1;
	var rows = 10;
	var state;
	var status;
	var isPaid;
	function loadData(){
		var param = {page:page++,rows:rows};
		$.extend(param,f.filterEmpty($("#queryForm").f_serialized()));
		if(state){
			param.state = state;
		}
		if(status){
			param.status = status;
		}
		if(isPaid){
			param.isPaid = isPaid;
		}
		$.getJSON(f.dynUrl+"/orders/blist.htm",param,function(d){
			if(d.success){
				container.empty();
				$.each(d.result.entry,function(i,obj){
					obj.imgUrl = f.imgUrl();
					obj.dynUrl = f.dynUrl;
					obj.staUrl = f.staUrl;
					container.append($("#tmpl").tmpl(obj));
				});
				curNum.text(page - 1);
				countNum.text(d.result.total);
				if(page - 1 <= 1){
					preBtn.prop("disabled","disabled");
				}else{
					preBtn.removeProp("disabled");
				}
				if((page - 1)*rows >= d.result.total){
					nextBtn.prop("disabled","disabled");
				}else{
					nextBtn.removeProp("disabled");
				}
			}else{
				f.alertError(d.errMsg);
			}
		});
	}
	$("#queryBtn").click(function(){
		page = 1;
		loadData();
	});
	loadData();
	$("#btns").children("button").click(function(){
		$("#btns").children("button").removeClass("btn-primary");
		$(this).addClass("btn-primary");
	});
	window.excel = function(){
		var qs = "";
		if(state){
			qs = qs + "state=" + state;
		}
		if(status){
			qs = qs + "status=" + status;
		}
		if(isPaid){
			qs = qs + "isPaid=" + isPaid;
		}
		var sdate = $("#sdate").f_input_datepicker("getValue");
		var edate = $("#edate").f_input_datepicker("getValue");
		if(sdate == ''||edate == ''){
			f.alertError("订单导出一次最多导出31天数据请正确选择时间范围");
			return;
		}
		var range = $("#edate").f_input_datepicker("getDate").getTime() - $("#sdate").f_input_datepicker("getDate").getTime();
		if(range > 100*60*60*24*31||range <= 0){
			f.alertError("订单导出一次最多导出31天数据请正确选择时间范围");
			return;
		}
		qs = qs + "sdate=" + sdate + "&edate=" + edate;
		window.open(encodeURI(f.dynUrl+"/orders/bexcel.htm?"+qs));
	};
	window.refundComplete = function(orderId){
		container.startMask();
		$.getJSON(f.dynUrl+"/orders/brefundComplete.htm",{orderId:orderId},function(d){
			container.closeMask();
			if(d.success){
				reLoadData();
			}else{
				f.alertError(d.errMsg);
			}
		});
	}
});
</script>
</body>
</html>