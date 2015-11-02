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
			<label>手机号：</label>
			<input class="form-control" name="mobile"/>
		</div>
		<div class="form-group">
			<label>订单状态：</label>
			<select class="form-control" name="state">
				<option></option>
				<option value="1">正常</option>
				<option value="2">取消</option>
			</select>
		</div>
		<div class="form-group">
			<label>订单处理状态：</label>
			<select class="form-control" name="status">
				<option></option>
				<option value="1">未确认</option>
				<option value="2">确认</option>
				<option value="3">处理中</option>
				<option value="4">已发货</option>
				<option value="5">确认收货</option>
				<option value="6">退货中</option>
				<option value="7">退货完成</option>
			</select>
		</div>
	</div>
	<div class="form-inline" style="padding-top:10px">
		<div class="form-group">
			<label>付款状态：</label>
			<select class="form-control" name="isPaid">
				<option></option>
				<option value="1">未付款</option>
				<option value="2">已付款</option>
			</select>
		</div>
		<div class="form-group">
			<label>时间范围：</label>
			<input class="form-control" name="sdate" f-type="datepicker"/>-
			<input class="form-control" name="edate" f-type="datepicker"/>
		</div>
		<button class="btn btn-default" id="queryBtn">查询</button>
	</div>
</div>
<hr/>
<div class="btn-group">
	<button class="btn btn-default">全部订单</button>
	<button class="btn btn-default">未付款</button>
	<button class="btn btn-default">待处理</button>
	<button class="btn btn-default">退货订单</button>
	<button class="btn btn-default">退货完成</button>
</div>

<div id="dg"></div>
<script type="text/javascript">
$(function(){
	$("#queryBtn").click(function(){
		dg.f_dg("load");
	});
	var dg = $("#dg").f_dg({
		columns:[{
			field:"orderNum",title:"订单号"
		},{
			field:"state",title:"订单状态",formatter:function(v){
				switch(parseInt(v)){
				case 1:return "正常";
				case 2:return "取消";
				case 3:return "异常";
				}
			}
		},{
			field:"status",title:"订单处理状态",formatter:function(v){
				switch(parseInt(v)){
				case 1:return "未确认";
				case 2:return "确认";
				case 3:return "处理中";
				case 4:return "已发货";
				case 5:return "确认收货";
				case 6:return "退货中";
				case 7:return "退货完成";
				}
			}
		},{
			field:"productPrice",title:"商品金额"
		},{
			field:"orderPrice",title:"订单金额"
		},{
			field:"discountPrice",title:"优惠金额"
		},{
			field:"isPaid",title:"付款状态",formatter:function(v){
				switch(parseInt(v)){
				case 1:return "未付款";
				case 2:return "已付款";
				}
			}
		},{
			field:"createtime",title:"下单时间"
		},{
			field:"consignee",title:"收货人"
		},{
			field:"mobile",title:"收货人手机号"
		},{
			field:"remark",title:"详细地址"
		}],
		rownumber:true,
		check:true,
		pagination:true,
		url:f.dynUrl+"/orders/blist.htm",
		lazy:true,
		filter:function(data){
			if(data.success){
				return {rows:data.result.entry,count:data.result.total};
			}else{
				f.dialogAlert(data.errMsg);
				return {rows:[],count:0};
			}
			
		},
		before:function(param){
			$.extend(param,$("#queryForm").f_serialized(true));
		}
	});
});
</script>
</body>
</html>