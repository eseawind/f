<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/mcommons.jsp" %>
<title>登陆</title>
</head>
<body>
	<%@ include file="../commons/mhead.jsp" %>
	<div class="container" style="padding-top:10px" id="container">
		<div id="alertError"></div>
		<form class="form" id="login_form">
			<div class="form-group">
				<label>用户名：</label>
				<input type="text" class="form-control" name="username" data-validation-help="必填" data-validation="length,required" data-validation-length="6,16"/>
			</div>
			<div class="form-group">
				<label>密码：</label>
				<input type="password" class="form-control" name="password" data-validation-help="必填" data-validation="length,required" data-validation-length="6,16"/>
			</div>
		</form>
		<button class="btn btn-block btn-primary" type="button" id="login_submit">登陆</button>
	</div>
<script type="text/javascript">
$(function(){
	$("#login_submit").click(function(){
		var container = $("#container");
		var form = $("#login_form");
		if(form.isValid()){
			var param = $("#login_form").serializeObj();
			container.showLoading();
			$.post(f.dynUrl+"/login/mlogin.htm",param,function(d){
				container.hideLoading();
				if(d.success){
					window.location.href = f.staUrl+f.result;
				}else{
					$("#alertError").f_alertError(d.errMsg);
				}
			},"json");
		}
	});
	$.validate();
})
</script>
</body>
</html>