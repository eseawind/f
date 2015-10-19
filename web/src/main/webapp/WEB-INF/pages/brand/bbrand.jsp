<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/bcommons.jsp" %>
</head>
<body>
<div class="container">
<%@ include file="../commons/bhead.jsp" %>
<div class="form-inline" style="padding:10px">
	<label>品牌名称</label>
	<input class="form-control" style="width:20%" id="query"/>
	<button class="btn btn-primary" id="queryBtn">查询</button>
</div>
<div class="panel panel-default">
	<div class="panel-heading">
		<button class="btn" id="addBtn"><i class="icon-plus"></i>&nbsp;新增品牌</button>
	</div>
	<div class="panel-body">
		<div id="dg"></div>
	</div>
</div>
<div id="form" class="form">
	<input type="hidden" name="id"/>
	<div class="form-group">
		<label>品牌名称</label>
		<input f-type="text" class="form-control" name="name" f-options="required:true,maxLength:20"/>
	</div>
	<div class="form-group">
		<label>url</label>
		<input f-type="text" class="form-control" name="url" f-options=""/>
	</div>
	<div class="form-group">
		<label>是否启用</label>
		<input type="radio" name="isUse" value="1" checked="checked"/>是
		<input type="radio" name="isUse" value="0"/>否
	</div>
	<button class="btn btn-primary btn-block" id="save">保存</button>
</div>
</div>
<script type="text/javascript">
$(function(){
	var form = $("#form");
	form.f_modal({});
	$("#queryBtn").click(function(){
		dg.f_dg("load");
	});
	$("#addBtn").click(function(){
		form.f_formClear();
		form.f_modal('setTitle','新增品牌信息');
		form.f_modal('show');
	});
	$("#save").click(function(){
		if(form.f_isValid()){
			var param = form.f_serialized();
			console.log(param);
			form.startMask();
			if(param.id){
				$.post(f.dynUrl+"/brand/upd.htm",param,function(d){
					form.closeMask();
					if(d.success){
						form.f_modal('hide');
						dg.f_dg('reload');
					}else{
						f.alertError(d.errMsg)
					}
				},'json');
			}else{
				$.post(f.dynUrl+"/brand/add.htm",param,function(d){
					form.closeMask();
					if(d.success){
						form.f_modal('hide');
						dg.f_dg('reload');
					}else{
						f.alertError(d.errMsg)
					}
				},'json');
			}
		}
	});
	var dg = $("#dg").f_dg({
		url:f.dynUrl+"/brand/list.htm",
		method:"post",
		before:function(param){param.name = $.trim($("#query").val())},
		pagation:true,
		filter:function(data){
			if(data.success){
				return {rows:data.result.entry,count:data.result.total};
			}else{
				f.alertError(data.errMsg);
			}
		},
		columns:[{
			field:"id",title:"品牌id"
		},{
			field:"name",title:"名称"
		},{
			field:"url",title:"url"
		},{
			field:"isUse",title:"是否启用",formatter:function(v){return parseInt(v) == 1?"是":"否"}
		},{
			field:"createtime",title:"创建时间"
		}],
		dblclick:function(i,d){
			form.f_formClear();
			form.f_formLoad(d);
			form.f_modal('setTitle','品牌信息维护');
			form.f_modal('show');
		}
	});
})
</script>
</body>
</html>