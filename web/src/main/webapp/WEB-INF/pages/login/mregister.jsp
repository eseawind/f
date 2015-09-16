<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/mcommons.jsp" %>
<title>注册</title>
</head>
<body>
	<%@ include file="../commons/mhead.jsp" %>
	<ul class="nav nav-tabs">
	  <li role="presentation" class="active f-width50 text-center" id="mobile_register_tab"><a href="#">手机注册</a></li>
	  <li role="presentation" id="email_register_tab" class="f-width50 text-center"><a href="#">邮箱注册</a></li>
	</ul>
	<div id="alertError"></div>
	<div class="container" id="mobile_container" style="padding-top:10px">
		<form class="form" id="mobile_form">
			<div class="form-group">
				<label>手机号</label>
				<input type="text" class="form-control" name="mobile"/>
			</div>
			<div class="form-group">
				<label>密码</label>
				<input type="password" class="form-control" name="password"/>
			</div>
		</form>
		<button class="btn btn-block btn-success" id="mobile_submit">注册</button>
	</div>
	<div class="container" id="email_container" style="display:none;padding-top:10px">
		<form class="form" id="email_form">
			<div class="form-group">
				<label>邮箱</label>
				<input type="text" class="form-control" name="email"/>
			</div>
			<div class="form-group">
				<label>密码</label>
				<input type="password" class="form-control" name="password"/>
			</div>
		</form>
		<button class="btn btn-block btn-success" type="button" id="email_submit">注册</button>
	</div>
<script type="text/javascript">
$(function(){
	var alertError = $("#alertError");
	var mrt = $("#mobile_register_tab");
	var mc = $("#mobile_container");
	mrt.click(function(){
		mrt.addClass("active");
		ert.removeClass("active");
		ec.hide();
		mc.show();
	});
	var ert = $("#email_register_tab"); 
	var ec = $("#email_container");
	ert.click(function(){
		ert.addClass("active");
		mrt.removeClass("active");
		mc.hide();
		ec.show();
	});
	var mform = $("#mobile_form");
	var eform = $("#email_form")
	$("#mobile_submit").click(function(){
		if(mform.isValid()){
			var param = mform.serializeObj();
			mc.showLoading();
			$.post(f.dynUrl+"",param,function(d){
				mc.hideLoading();
			},"json");
		}
	});
	$("#email_submit").click(function(){
		
	});
})
</script>
</body>
</html>