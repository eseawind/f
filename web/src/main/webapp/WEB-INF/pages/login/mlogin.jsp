<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/mcommons.jsp" %>
<script type="text/javascript" src="${staUrl }/js/md5.js"></script>
<title>登陆</title>
</head>
<body class="container">
<%@ include file="../commons/mhead.jsp" %>
<div class="panel panel-default" style="padding-top:10px">
	<div class="panel-body" id="form">
		<div id="alertError"></div>
		<form class="form">
			<div class="form-group">
				<label>用户名：</label>
				<input value="13718563134" type="text" class="form-control" name="username" f-type="text" f-options="required:true,hint:'用户名',maxLength:50,minLength:6,errMsg:'用户名长度6到50位',errDir:'auto bottom'"/>
			</div>
			<div class="form-group">
				<label>密码：</label>
				<input value="123456" type="password" class="form-control" name="password" f-type="text" f-options="required:true,hint:'密码',maxLength:20,minLength:6,errMsg:'密码长度6到20位',errDir:'auto bottom'"/>
			</div>
		</form>
		<button class="btn btn-block btn-success" type="button" id="login_submit">登陆</button>
		<div class="text-right"><a href="${staUrl }/page/login/mregister.htm" class="btn">去注册&gt;&gt;</a></div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	var form = $("#form");
	var alert = $("#alertError");
	$("#login_submit").click(function(){
		if(form.f_isValid()){
			var param = form.f_serialized();
			param.password = hex_md5(param.password);
			form.startMask();
			$.post(f.dynUrl+"/login/mlogin.htm",param,function(d){
				form.closeMask();
				if(d.success){
					if(window.document.referrer){
						window.location.href = window.document.referrer;
					}else{
						window.location.href = f.staUrl + "/page/index/mindex.htm";
					}
				}else{
					alert.empty();
					alert.f_alertError(d.errMsg);
				}
			},"json");
		}
	});
})
</script>
</body>
</html>