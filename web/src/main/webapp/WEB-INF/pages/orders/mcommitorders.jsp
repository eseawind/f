<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/mcommons.jsp" %>
<script type="text/javascript" src="${staUrl }/js/md5.js"></script>
</head>
<body class="container">
<%@include file="../commons/mhead.jsp" %>
<div class="panel panel-default">
	<div class="panel-heading">收货人信息</div>
	<div class="panel-body">
		<div id="defAddress">
			
		</div>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">支付方式</div>
	<div class="panel-body">
		<div class="form-inline">
			<div class="form-group btn">
				<input type="radio" name="payType" id="ye" value="1" checked="checked"/>
				<label for="ye">余额</label>
			</div>
			<div class="form-group btn">
				<input type="radio" name="payType" id="zfb" value="2"/>
				<label for="zfb">支付宝</label>
			</div>
			<div class="form-group btn">
				<input type="radio" name="payType" id="wx" value="3"/>
				<label for="wx">微信</label>
			</div>
		</div>
		<div class="form-inline">
			<div class="form-group">
				<label>支付密码：</label>
				<input class="form-control" type="password" id="payPass"/>
				<span style="color:red">&nbsp;余额支付必填</span>
			</div>
		</div>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">商品清单</div>
	<div class="panel-body">
		<div id="list">
			
		</div>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-body text-right">
		优惠金额：<span id="discountPrice"></span>&nbsp;&nbsp;
		总金额：<span style="color:red" id="orderPrice"></span>&nbsp;&nbsp;
		<button class="btn btn-danger" id="commitOrders" disabled="disabled">提交订单</button>
	</div>
</div>

<div id="aWin" style="display:none">
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
		<button class="btn btn-default btn-block" id="save">保存</button>
	</div>
</div>
<script type="text/tmpl" id="aTmpl">
	<div class="panel panel-default">
		<div class="panel-body">
			<p><span>收货人：{{= consignee}}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>手机号：{{= mobile}}</span></p>
			<p>{{= remark}}</p>
			<div class="text-right"><i class="icon-edit" style="cursor:pointer">编辑</i></div>
		</div>
	</div>
</script>
<script type="text/tmpl" id="settleTmpl">
	<div class="panel panel-default">
	    <div class="panel-heading">商家：{{= merchantName}}</div>
	    <div class="panel-body">
			<table class="table table-bordered">
				<tr>
					<th colspan="2">商品</th>
					<th class="text-center">价格</th>
				</tr>{{each settleCarts}}{{if $value.checked}}
				<tr>
					<td style="vertical-align: middle;width:100px">{{each settleGoodsList}}<img src="{{= imgUrl}}{{= $value.photo}}" style="width:80px;height:80px"/>{{/each}}</td>
					<td style="vertical-align: middle;">
						<div style="min-height:50px">{{each settleGoodsList}}{{= $value.gname}}-{{= $value.cgname}}<br/>{{/each}}</div>
					</td>
					<td style="vertical-align: middle;width:80px;text-align:center"><b>{{= $value.orderPrice}}</b></td>
				</tr>{{/if}}{{/each}}
				{{if !settle}}<tr><td colspan="3" style="color:red">{{= reason}}</td><tr>{{/if}}
			</table>
		</div>
		<div class="panel-footer">
			<div class="text-right" style="color:red">总金额：￥{{= orderPrice}}</div>
		</div>
	</div>
</script>
<script type="text/javascript">
$(function(){
	var address;
	var aWin = $("#aWin");
	aWin.f_modal({title:"收货人信息"});
	var defAddress = $("#defAddress");
	defAddress.click(function(){
		if(address){
			aWin.f_formClear();
			aWin.f_formLoad(address);
			$.getJSON(f.dynUrl+'/area/combobox.htm?pid='+address.provinceId,function(d){
				if(d.success){
					$('#cityId').f_combobox('loadData',d.result);
					$('#cityId').f_combobox('setValue',address.cityId);
				}
			});
			$.getJSON(f.dynUrl+'/area/combobox.htm?pid='+address.cityId,function(d){
				if(d.success){
					$('#areaId').f_combobox('loadData',d.result);
					$('#areaId').f_combobox('setValue',address.areaId);
				}
			});
			aWin.f_modal("show");
		}else{
			aWin.f_modal("show");
		}
	});
	$("#save").click(function(){
		if(aWin.f_isValid()){
			var param = aWin.f_serialized();
			aWin.startMask();
			param.isDef = 1;
			$.post(f.dynUrl+"/users/maddOrUpdAddress.htm",param,function(d){
				aWin.closeMask();
				if(d.success){
					aWin.f_modal("hide");
					if(!param.id){
						loadAddress();
					}else{
						$.extend(address,param);
						defAddress.empty();
						$("#aTmpl").tmpl(address).appendTo(defAddress);
					}
				}else{
					f.dialogAlert(d.errMsg);
				}
			},"json");
		}
	});
	function loadAddress(){
		$.getJSON(f.dynUrl+"/users/maddress.htm",function(d){
			if(d.success){
				$.each(d.result,function(i,address){
					if(address.isDef == 1){
						address = address
					}
				});
				if(!address&&d.result.length > 0){
					address = d.result[d.result.length-1];
				}
				if(address){
					defAddress.empty();
					$("#aTmpl").tmpl(address).appendTo(defAddress);
				}else{
					$('<div><i class="icon-plus">&nbsp:新增收货地址</i></div>').appendTo(defAddress);
				}
			}else{
				f.dialogAlert(d.errMsg)
			}
		});
	}
	loadAddress();
	var list = $("#list");
	var commitBtn = $("#commitOrders");
	commitBtn.click(function(){
		var param = {};
		param.provinceId = address.provinceId;
		param.cityId = address.cityId;
		param.areaId = address.areaId;
		param.provinceName = f.area_map[param.provinceId];
		param.cityName = f.area_map[param.cityId];
		param.areaName = f.area_map[param.areaId];
		param.remark = address.remark;
		param.mobile = address.mobile;
		param.consignee = address.consignee;
		if($("#ye").prop('checked')){
			param.payType = 1;
		}else if($("#zfb").prop('checked')){
			param.payType = 2;
		}else if($("#wx").prop('checked')){
			param.payType = 3;
		}
		if(param.payType == undefined){
			f.dialogAlert("请选择支付方式");
			return;
		}
		if(param.payType == 1){
			param.password = $.trim($("#payPass").val());
			if(param.password == ''){
				f.dialogAlert("请输入支付密码");
				return;
			}
			param.password = hex_md5(param.password);
		}
		$.post(f.dynUrl+"/orders/commitOrders.htm",param,function(d){
			if(d.success){
				window.location.href = f.staUrl + "/orders/success.htm?orderNum="+d.result.resOrders[0].orderNum+"&payPrice="+d.result.payPrice;
			}else{
				f.dialogAlert(d.errMsg);
			}
			
		},"json");
	});
	$.getJSON(f.dynUrl+"/cart/settle.htm",function(d){
		if(d.success){
			if(!d.result.settlements.settle){
				commitBtn.removeProp('disabled');
			}
			$("#discountPrice").text(d.result.discountPrice);
			$("#orderPrice").text(d.result.orderPrice);
			$.each(d.result.settlements,function(i,obj){
				if(obj.hasChecked){
					list.append($("#settleTmpl").tmpl(obj));
				}
			});
		}else{
			f.dialogAlert(d.errMsg);
		}
	});
});
</script>
</body>
</html>