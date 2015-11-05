<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/mcommons.jsp" %>
</head>
<body class="container">
<%@include file="../commons/mhead.jsp" %>
<ul class="nav nav-tabs row text-center" id="tabs">
  <li role="presentation" class="active col-md-4 col-sm-4 col-xs-4"><a href="javascript:void(0)" onclick="orderFun(1)">待付款</a></li>
  <li role="presentation" class="col-md-4 col-sm-4 col-xs-4"><a href="javascript:void(0)" onclick="orderFun(2)">待收货</a></li>
  <li role="presentation" class="col-md-4 col-sm-4 col-xs-4"><a href="javascript:void(0)" onclick="orderFun(3)">全部</a></li>
</ul>
<div id="container">
	
</div>
<button id="mostBtn" class="btn btn-default btn-block">加载更多数据</button>
<script type="text/tmpl" id="tmpl">
	<div class="form panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label>订单号：</label>
				<span>{{= orderNum}}</span>
			</div>
			<div class="form-group">
				<label>订单金额：</label>
				<span>{{= orderPrice}}</span>
			</div>
			<div class="form-group">
				<label>订单状态：</label>
				<span>
				{{if state == 2||state == 3}}
					已取消
				{{else isPaid==1}}
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
				</span>
			</div>
			<div class="form-group">
				<label>收货信息：</label>
				<span>{{= consignee}}&nbsp;{{= mobile}}&nbsp;{{= remark}}</span>
			</div>
			<div class="form-group">
				<div class="text-right">
					{{if isPaid==1}}
					<a href="javascript:void(0)" class="btn btn-danger">去支付</a>&nbsp;&nbsp;
					{{/if}}					
					<a href="{{= dynUrl}}/page/orders/mdetail.htm?orderId={{= id}}">订单明细 &gt;&gt;</a>
				</div>
			</div>
		</div>
	</div>
</script>
<script type="text/javascript">
$(function(){
	f.setTitle("我的订单");
	var container = $("#container");
	var tabs = $("#tabs");
	var page = 1;
	var rows = 10;
	var curType = 1;
	tabs.children("li").each(function(){
		var li = $(this);
		li.click(function(){
			tabs.children("li").removeClass("active")
			li.addClass("active");
		});
	});
	var mostBtn = $("#mostBtn").click(function(){
		page ++;
		loadData();
	});
	orderFun = function(type){
		page = 1;
		mostBtn.show();
		curType = type;
		loadData(true);
	}
	function loadData(clear){
		var param = {}
		param.page = page;
		param.rows = rows;
		switch(curType){
		case 1:param.isPaid = 1;param.state = 1;break;
		case 2:param.isPaid = 2;param.state = 1;break;
		}		
		$.getJSON(f.dynUrl+"/orders/mlist.htm", param, function(d){
			if(d.success){
				if(clear){
					container.empty();
				}
				$.each(d.result.entry, function(i,obj){
					obj.dyn = f.dyn;
					container.append($("#tmpl").tmpl(obj));
				});
				if(page*rows >= d.result.total){
					mostBtn.hide();
				}
			}else{
				f.dialogAlert(d.errMsg);
			}
		});
	}
	orderFun(1);
})
</script>
</body>
</html>