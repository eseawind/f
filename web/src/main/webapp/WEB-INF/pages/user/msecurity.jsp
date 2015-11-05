<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/mcommons.jsp" %>
</head>
<body class="container">
<%@include file="../commons/mhead.jsp" %>
<script type="text/javascript" src="${staUrl }/js/md5.js"></script>
<ul class="nav nav-tabs rows text-center" style="cursor:pointer" id="tabs">
	<li class="active col-md-6 col-xs-6 col-sm-6" f-id="1"><a href="javascript:void(0)">登录密码维护</a></li>
	<li class=" col-md-6 col-xs-6 col-sm-6" f-id="2"><a href="javascript:void(0)">支付密码维护</a></li>
</ul>
<hr/>
<div id="alert"></div>
<div id="pdiv" class="panel panel-default">
	<div class="panel-body form">
		<div class="form-group">
			<label>原密码：</label>
			<input f-type="text" name="oldpass" class="form-control" f-options="required:true"/>
		</div>
		<div class="form-group">
			<label>新密码：</label>
			<input f-type="text" name="newpass" class="form-control" f-options="required:true,minLength:6,maxLength:16,errMsg:'密码长度6到16位',errDir:'bottom'"/>
		</div>
		<div class="form-group">
			<label>再次输入新密码：</label>
			<input f-type="text" name="newpass2" class="form-control" f-options="required:true,minLength:6,maxLength:16,errMsg:'密码长度6到16位',errDir:'bottom'"/>
		</div>
	</div>
	<button class="btn btn-block btn-primary" id="psubmit">提交</button>
</div>
<div id="ppdiv" class="panel panel-default" style="display:none">
	<div class="panel-body form">
		<div class="form-group">
			<label>获取手机验证码：</label>
			<div class="input-group">
				<input f-type="text" name="verifycode" class="form-control" f-options="required:true"/>
				<span class="input-group-addon" style="cursor:pointer" id="scBtn">获取手机验证码</span>
			</div>
		</div>
		<div class="form-group">
			<label>新支付密码：</label>
			<input f-type="text" name="newpass" class="form-control" f-options="required:true,minLength:6,maxLength:16,errMsg:'密码长度6到16位',errDir:'bottom'"/>
		</div>
		<div class="form-group">
			<label>再次输入新支付密码：</label>
			<input f-type="text" name="newpass2" class="form-control" f-options="required:true,minLength:6,maxLength:16,errMsg:'密码长度6到16位',errDir:'bottom'"/>
		</div>
	</div>
	<button class="btn btn-block btn-primary" id="ppsubmit">提交</button>
</div>
<script type="text/javascript">
$(function(){
	var pdiv = $("#pdiv");
	var ppdiv = $("#ppdiv");
	var scBtn = $("#scBtn");
	var alert = $("#alert");
	$("#tabs").children("li").click(function(){
		$("#tabs").children("li").removeClass("active");
		$(this).addClass("active");
		if($(this).attr("f-id") == '1'){
			ppdiv.hide();
			pdiv.show();
		}else if($(this).attr("f-id") == '2'){
			pdiv.hide();
			ppdiv.show();
		}
	});
	var scFlag = true;
	scBtn.click(function(){
		if(scFlag){
			scFlag = false;
			var text = scBtn.text();
			var second = 60;
			scBtn.text(second+"秒后请耐心等待");
			var timer = setInterval(function(){
				scBtn.text(second--+"秒后请耐心等待");
				if(second == 0){
					clearInterval(timer);
					scBtn.text(text);
					scFlag = true;
				}
			}, 1000);
		}
	});
	$("#psubmit").click(function(){
		if(pdiv.f_isValid()){
			var param = pdiv.f_serialized();
			if(param.newpass != param.newpass2 ){
				alert.f_alertError("两次密码输入不一致");
				return;
			}
			pdiv.startMask();
			$.post(f.dynUrl+"/users/updpass.htm",{oldpass:hex_md5(param.oldpass),password:hex_md5(param.newpass)},function(d){
				pdiv.closeMask();
				if(d.success){
					f.dialogAlert("密码修改成功");
				}else{
					alert.f_alertError(d.errMsg);
				}
			},"json");
		}
	});
	$("#ppsubmit").click(function(){
		if(ppdiv.f_isValid()){
			var param = ppdiv.f_serialized();
			if(param.newpass != param.newpass2 ){
				alert.f_alertError("两次密码输入不一致");
				return;
			}
			ppdiv.startMask();
			$.post(f.dynUrl+"/users/updppass.htm",{verifycode:param.verifycode,password:hex_md5(param.newpass)},function(d){
				ppdiv.closeMask();
				if(d.success){
					f.dialogAlert("密码修改成功");
				}else{
					alert.f_alertError(d.errMsg);
				}
			},"json");
		}
	});
})
</script>
</body>
</html>