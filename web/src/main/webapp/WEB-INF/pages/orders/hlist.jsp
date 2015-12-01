<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/hcommons.jsp" %>
</head>
<body class="container">
<%@include file="../commons/hhead.jsp" %>
<div class="panel panel-default" id="query">
	<div class="panel-body form-inline">
		<div class="form-group">
			<label>商家</label>
			<input class="form-control" name="merchantId" f-type="input_combobox" f-options="url:'${dynUrl }/merchant/hcombobox.htm',textField:'name',valueField:'id',filter:function(d){return d.result}"/>
		</div>
		<div class="form-group">
			<label>订单号</label>
			<input class="form-control" name="orderNum"/>
		</div>
		<div class="form-group">
			<label>时间范围：</label>
			<input class="form-control" name="sdate" f-type="datepicker"/>-
			<input class="form-control" name="edate" f-type="datepicker"/>
		</div>
		<button class="btn btn-default" id="queryBtn"><i class="icon-search"></i>查询</button>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading"></div>
	<div class="panel-body">
		<div id="dg"></div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	var dg = $("#dg").f_dg({
		columns:[{
			field:"merchantName",title:'商家',showTip:true
		},{
			field:"orderNum",title:'订单号',showTip:true
		},{
			field:"username",title:'用户名',showTip:true
		},{
			field:"state",title:'订单状态',showTip:true,formatter:function(v){
				switch(parseInt(v)){
				case 1:return "正常";
				case 2:return "取消";
				case 3:return "异常";
				}
			}
		},{
			field:"status",title:'订单处理状态',showTip:true,formatter:function(v){
				switch(parseInt(v)){
				case 1:return "未确认";
				case 2:return "已确认";
				case 3:return "处理中";
				case 4:return "已发货";
				case 5:return "确认收货";
				case 6:return "退货中";
				case 7:return "已退货";
				}
			}
		},{
			field:"orderPrice",title:'订单金额',showTip:true
		},{
			field:"paidPrice",title:'已付金额',showTip:true
		},{
			field:"payPrice",title:'应付金额',showTip:true
		},{
			field:"isPaid",title:'付款',showTip:true,formatter:function(v){
				switch(parseInt(v)){
				case 1:return "未付款";
				case 2:return "已付款";
				}
			}
		},{
			field:"createtime",title:'下单时间',showTip:true
		},{
			field:"payTime",title:'付款时间',showTip:true
		}],
		rownumber:true,
		filter:function(d){
			if(d.success){
				return {rows:d.result.entry,count:d.result.total};
			}else{
				f.alertError(d.errMsg);
				return {};
			}
		},
		before:function(param){
			$.extend(param,$("#query").f_serialized());
		},
		pagination:true,
		url:f.dynUrl+"/orders/hlist.htm"
	});
	$("#queryBtn").click(function(){
		dg.f_dg("load");
	});
})
</script>
</body>
</html>