<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/bcommons.jsp" %>
<script type="text/javascript" src="${staUrl }/js/md5.js"></script>
</head>
<body>
<div class="container" style="width:500px;margin-top:100px">
	<div class="panel panel-default">
		<div class="panel-heading">
			商家登陆
		</div>
		<div class="panel-body" id="form">
			<form class="form">
				<div class="form-group">
					<label>用户名</label>
					<input name="username" f-type="text" class="form-control" f-options="required:true,minLength:3,maxLength:20"/>
				</div>
				<div class="form-group">
					<label>密码</label>
					<input name="password" f-type="text" class="form-control" type="password" f-options="required:true,minLength:3,maxLength:20"/>
				</div>
			</form>
			<div id="error"></div>
			<button class="btn btn-primary btn-block" id="submit">登陆</button>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$("#submit").click(function(){
		var form = $("#form");
		if(form.f_isValid()){
			var param = form.f_serialized();
			param.password = hex_md5(param.password);
			form.startMask();
			$.post(f.dynUrl+"/login/blogin.htm",param,function(d){
				form.closeMask();
				if(d.success){
					if(window.document.referrer){
						window.location.href = window.document.referrer;
					}else{
						window.location.href = f.staUrl + "/page/goods/bgoods.htm";
					}
				}else{
					$("#error").f_alertError(d.errMsg);
				}
			},'json');
		}
	})
})
</script>
</body>
</html>