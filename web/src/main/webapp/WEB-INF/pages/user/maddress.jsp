<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/mcommons.jsp" %>
</head>
<body class="container">
<%@ include file="../commons/mhead.jsp" %>
<div class="panel panel-default">
	<div class="panel-heading">我的收货地址：</div>
	<div class="panel-body">
		<div id="container">
			
		</div>
		<script type="text/tmpl" id="tmpl">
			<div class="panel panel-default">
				<div class="panel-body">
					<p><span>收货人：{{= consignee}}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>手机号：{{= mobile}}</span></p>
					<p>{{= remark}}</p>
				</div>
				<div class="panel-footer">
					<button class="btn btn-default btn-block" f-id="{{= index}}"><i class="icon-edit"></i>编辑收货地址</button>
				</div>
			</div>
		</script>
		<button class="btn btn-default btn-block" id="addBtn"><i class="icon-plus-sign">&nbsp;&nbsp;新增收货地址</i></button>
	</div>
</div>
<div id="win" style="display:none">
	<div class="form">
		<input type="hidden" name="id"/>
		<div class="form-group">
			<label>省：</label>
			<select class="form-control" f-type="combobox" name="provinceId" f-options="required:true,url:'${dynUrl }/area/combobox.htm?pid=1',filter:function(d){return d.result;},select:function(v){
				$.getJSON(f.dynUrl+'/area/combobox.htm?pid='+v.v,function(d){
					if(d.success){
						$('#cityId').f_combobox('loadData',d.result);
					}
				});
			}"></select>
		</div>
		<div class="form-group">
			<label>市：</label>
			<select class="form-control" f-type="combobox" name="cityId" id="cityId" f-options="required:true,select:function(v){
				$.getJSON(f.dynUrl+'/area/combobox.htm?pid='+v.v,function(d){
					if(d.success){
						$('#areaId').f_combobox('loadData',d.result);
					}
				});
			}"></select>
		</div>
		<div class="form-group">
			<label>区：</label>
			<select class="form-control" f-type="combobox" name="areaId" id="areaId" f-options="required:true"></select>
		</div>
		<div class="form-group">
			<label>收货人：</label>
			<input class="form-control" f-type="text" name="consignee" f-options="required:true,maxLength:20"/>
		</div>
		<div class="form-group">
			<label>手机号：</label>
			<input class="form-control" f-type="text" name="mobile" f-options="required:true,regex:'^1[3|4|5|6|7|8|9]\\d{9}',errDir:'bottom',errMsg:'请输入正确手机号'"/>
		</div>
		<div class="form-group">
			<label>详细地址：</label>
			<input class="form-control" f-type="text" name="remark" f-options="required:true,maxLength:254"/>
		</div>
		<div class="form-group">
			&nbsp;&nbsp;&nbsp;&nbsp;<input name="isDef" id="isDef" value="1" type="checkbox"/>
			<label for="isDef">&nbsp;设置为默认收货地址</label>
		</div>
		<button class="btn btn-default btn-block" id="submit">保存</button>
	</div>
</div>
<script type="text/javascript">
$(function(){
	var container = $("#container");
	var win = $("#win");
	win.f_modal({});
	var addBtn = $("#addBtn");
	addBtn.click(function(){
		if(aDatas.length >= 5){
			f.dialogAlert("收货地址数量达到上限");
		}
		win.f_formClear();
		win.f_modal("setTitle","新增收货地址");
		win.f_modal("show");
	});
	$("#submit").click(function(){
		if(win.f_isValid()){
			var param = win.f_serialized();
			win.startMask();
			$.post(f.dynUrl+"/users/maddOrUpdAddress.htm",param,function(d){
				win.closeMask();
				if(d.success){
					win.f_modal("hide");
					loadAddress();
				}else{
					f.dialogAlert(d.errMsg);
				}
			},"json");
		}
	});
	var aDatas = [];
	function loadAddress(){
		$.getJSON(f.dynUrl+"/users/maddress.htm",function(d){
			if(d.success){
				aDatas = d.result;
				container.empty();
				if(aDatas.length >= 5){
					addBtn.hide();
				}else{
					addBtn.show();
				}
				$.each(aDatas,function(i,obj){
					obj.index = i;
					var ad = $("#tmpl").tmpl(obj).appendTo(container);
					ad.find("button[f-id]").eq(0).click(function(){
						var data = aDatas[$(this).attr("f-id")];
						win.f_formClear();
						win.f_formLoad(data);
						$.getJSON(f.dynUrl+'/area/combobox.htm?pid='+data.provinceId,function(d){
							if(d.success){
								$('#cityId').f_combobox('loadData',d.result);
								$('#cityId').f_combobox('setValue',data.cityId);
							}
						});
						$.getJSON(f.dynUrl+'/area/combobox.htm?pid='+data.cityId,function(d){
							if(d.success){
								$('#areaId').f_combobox('loadData',d.result);
								$('#areaId').f_combobox('setValue',data.areaId);
							}
						});
						win.f_modal('setTitle','修改收货地址');
						win.f_modal("show");
					});
				})
			}else{
				f.dialogAlert(d.errMsg)
			}
		});
	}
	loadAddress();
})
</script>
</body>
</html>