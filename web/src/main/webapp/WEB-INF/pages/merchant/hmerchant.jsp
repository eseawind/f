<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/hcommons.jsp" %>
</head>
<body>
<div class="container">
<%@ include file="../commons/hhead.jsp" %>
<div class="form-inline">
	<div class="form-group">
		<label>商家编号：</label>
		<input class="form-control" id="merchantId" f-type="number"/>
	</div>
	<div class="form-group">
		<label>商家名称：</label>
		<input class="form-control" id="merchantName"/>
	</div>
</div>
<hr/>
<div class="panel panel-default"> 
	<div class="panel-heading">
		<div class="btn-group">
			<button class="btn btn-default" onclick="addMerchantFun()"><i class="icon-plus"></i>&nbsp;创建商家</button>
		</div>
	</div>
	<div class="panel-body">
		<div id="dg"></div>
	</div>
</div>
</div>

<div id="add_win">
<form id="add_form" class="form">
	<div class="form-group">
		<label>用户名：</label>
		<input class="form-control" name="username" f-type="text" f-options="required:true,maxLength:20,minLength:3,errMsg:'3到20位长度',errDir:'bottom'"/>
	</div>
	<div class="form-group">
		<label>密码：</label>
		<input class="form-control" type="password" name="password" f-type="text" f-options="required:true,maxlength:20,minLength:6,errMsg:'6到20位长度',errDir:'bottom'"/>
	</div>
	<div class="form-group">
		<label>商家名称：</label>
		<input class="form-control" name="name" f-type="text" f-options="required:true,maxLength:254,minLength:3,errMsg:'3到254位长度',errDir:'bottom'"/>
	</div>
	<div class="form-group">
		<label>电话：</label>
		<input class="form-control" name="mobile" f-type="text" f-options="required:true,hint:'电话',errMsg:'请输入正确手机号',errDir:'bottom',regex:'^1[3|4|5|6|7|8|9]\\d{9}$'"/>
	</div>
	<div class="form-group">
		<label>QQ：</label>
		<input class="form-control" name="qq" f-type="text" f-options="hint:'QQ',errMsg:'请输入正确QQ号',errDir:'bottom',regex:'\\d*'"/>
	</div>
	<div class="form-group">
		<label>地址：</label>
		<input class="form-control" name="address" f-type="text" f-options="required:true,hint:'商家地址'"/>
	</div>
	<div class="form-group">
		<label>支付宝账户：</label>
		<input class="form-control" name="alipay" f-type="text" f-options="hint:'支付宝账户'"/>
	</div>
</form>
<button id="add_submit" class="btn btn-block btn-primary">保存</button>
</div>

<div id="upd_win">
<form id="upd_form" class="form">
	<input type="hidden" name="id"/>
	<div class="form-group">
		<label>商家名称：</label>
		<input class="form-control" name="name" f-type="text" f-options="required:true,maxLength:254,minLength:3,errMsg:'3到254位长度',errDir:'bottom'"/>
	</div>
	<div class="form-group">
		<label>电话：</label>
		<input class="form-control" name="mobile" f-type="text" f-options="required:true,hint:'电话',errMsg:'请输入正确手机号',errDir:'bottom',regex:'^1[3|4|5|6|7|8|9]\\d{9}$'"/>
	</div>
	<div class="form-group">
		<label>QQ：</label>
		<input class="form-control" name="qq" f-type="text" f-options="hint:'QQ',errMsg:'请输入正确QQ号',errDir:'bottom',regex:'\\d*'"/>
	</div>
	<div class="form-group">
		<label>地址：</label>
		<input class="form-control" name="address" f-type="text" f-options="required:true,hint:'商家地址'"/>
	</div>
	<div class="form-group">
		<label>支付宝账户：</label>
		<input class="form-control" name="alipay" f-type="text" f-options="hint:'支付宝账户'"/>
	</div>
</form>
<button id="upd_submit" class="btn btn-block btn-primary">保存</button>
</div>
<script type="text/javascript">
$(function(){
	var addWin = $("#add_win");
	var addForm = $("#add_form");
	var updWin = $("#upd_win");
	var updForm = $("#upd_form");
	addWin.f_modal({title:'新增商家'});
	updWin.f_modal({title:'修改商家信息'});
	addMerchantFun = function(){
		addForm.f_formReset();
		addWin.f_modal('show');
	}
	$("#add_submit").click(function(){
		if(addForm.f_isValid()){
			var param = addForm.f_serialized();
			addWin.startMask();
			$.post(f.dynUrl+'/merchant/addOrUpd.htm',param,function(d){
				addWin.closeMask();
				if(d.success){
					addWin.f_modal('hide');
					dg.f_dg("reload");
				}else{
					f.alertError(d.errMsg);
				}
			},'json');
		}
	});
	$("#upd_submit").click(function(){
		if(updForm.f_isValid()){
			var param = updForm.f_serialized();
			updWin.startMask();
			$.post(f.dynUrl+'/merchant/addOrUpd.htm',param,function(d){
				updWin.closeMask();
				if(d.success){
					updWin.f_modal('hide');
					dg.f_dg("reload");
				}else{
					f.alertError(d.errMsg);
				}
			},'json');
		}
	});
	var dg = $("#dg").f_dg({
		url:f.dynUrl+'/merchant/list.htm',
		method:'post',
		pagination:true,
		dblclick:function(){
			var data = dg.f_dg("getSelected");
			updForm.f_formReset();
			updForm.f_formLoad(data);
			updWin.f_modal('show');
		},
		before:function(param){
			param.id = $("#merchantId").f_input_number("getValue");
			param.name = $("#merchantName").val(); 
		},
		filter:function(data){
			if(data.success){
				return {rows:data.result.entry,count:data.result.total};
			}
			return {rows:[],count:0};
		},
		columns:[{
			field:'id',title:'商家编号'
		},{
			field:'username',title:'用户名'
		},{
			field:'name',title:'商家名称'
		},{
			field:'mobile',title:'联系电话'
		},{
			field:'qq',title:'QQ'
		},{
			field:'address',title:'地址'
		},{
			field:'alipay',title:'阿里账号'
		},{
			field:'createtime',title:'注册时间'
		}]
	});
})
</script>
</body>
</html>