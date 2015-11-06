<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/mcommons.jsp" %>
</head>
<body class="container">
<%@include file="../commons/mhead.jsp" %>
<div class="panel panel-default">
	<div class="panel-body form-inline">
		 <div class="form-group">
		 	<img class="img-circle" src="${staUrl }/img/111.jpg" style="width:50px;height:50px"/>
		 </div>
		 <div class="form-group" style="padding:20px">
		 	<p>用户名：<span id="userName"></span></p>
		 	<p>账户余额：<span id="balance">0.00</span></p>
		 </div>
	</div>
</div>
<ul class="list-group">
	<li class="list-group-item"><a href="${staUrl }/page/orders/mlist.htm">我的订单</a></li>
	<li class="list-group-item"><a href="#">我的钱包</a></li>
	<li class="list-group-item"><a href="${staUrl }/page/user/maddress.htm">收货地址管理</a></li>
	<li class="list-group-item"><a href="${staUrl }/page/user/mcollect.htm">我的收藏</a></li>
	<li class="list-group-item"><a href="${staUrl }/page/user/msecurity.htm">账户安全</a></li>
	<li class="list-group-item"><a href="#">联系我们</a></li>
</ul>
<button class="btn btn-block btn-danger" onclick="unlogin()">退出登录</button>
<script type="text/javascript">
$(function(){
	f.setTitle("个人中心");
	$.getJSON(f.dynUrl+"/users/minfo.htm",function(d){
		if(d.success){
			$("#userName").text(d.result.username);
			$("#balance").text(d.result.balance);
		}else{
			f.dialogAlert(d.errMsg);
		}
	});
	unlogin = function(){
		$.getJSON(f.dynUrl+"/login/unlogin.htm",function(){
			window.location.href = f.staUrl+"/page/index/mindex.htm";
		});
	}
});
</script>
</body>
</html>