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
			field:"userName",title:'订单号',showTip:true
		},{
			field:"orderNum",title:'订单号',showTip:true
		},{
			field:"orderNum",title:'订单号',showTip:true
		},{
			field:"orderNum",title:'订单号',showTip:true
		},{
			field:"orderNum",title:'订单号',showTip:true
		},{
			field:"orderNum",title:'订单号',showTip:true
		},{
			field:"orderNum",title:'订单号',showTip:true
		},{
			field:"orderNum",title:'订单号',showTip:true
		},{
			field:"orderNum",title:'订单号',showTip:true
		},{
			field:"orderNum",title:'订单号',showTip:true
		},{
			field:"orderNum",title:'订单号',showTip:true
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
	});
})
</script>
</body>
</html>