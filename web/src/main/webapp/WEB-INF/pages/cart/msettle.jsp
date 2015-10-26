<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../commons/mcommons.jsp" %>
</head>
<body class="container">
<%@include file="../commons/mhead.jsp" %>
<div id="container">	
</div>
<script type="text/tmpl" id="tmpl">
	<div class="panel panel-default">
	    <div class="panel-heading">商家：{{= merchantName}}</div>
	    <div class="panel-body">
			<table class="table table-bordered">
				<tr>
					<th><input type="checkbox" f-id="{{each settleCarts}}{{= $value.settleCartStr}},{{/each}}" {{each settleCarts}}{{if $value.checked}} {{/if}}{{/each}}/></th>
					<th colspan="2">商品</th>
					<th class="text-center">价格</th>
					<th>删除</th>
				</tr>{{each settleCarts}}
				<tr style="">
					<td style="vertical-align: middle;width:30px"><input f-id="{{= $value.settleCartStr}}" type="checkbox" {{if $value.checked}} checked="checked" {{/if}}/></td>
					<td style="vertical-align: middle;width:100px">{{each settleGoodsList}}<img src="{{= imgUrl}}{{= $value.photo}}" style="width:80px;height:80px"/>{{/each}}</td>
					<td style="vertical-align: middle;">
						<div style="min-height:50px">{{each settleGoodsList}}{{= $value.gname}}-{{= $value.cgname}}<br/>{{/each}}</div>
						<div>
							<button class="btn btn-default icon-minus" style="float:left;height:30px;max-height:30px" f-id="{{= $value.settleCartStr}}"></button>
							<input class="form-control" type="text" value="{{= $value.number}}" style="float:left;width:41px;height:30px;max-height:30px" f-id="{{= $value.settleCartStr}}"/>
							<button class="btn btn-default icon-plus" f-id="{{= $value.settleCartStr}}" style="float:left;height:30px;max-height:30px"></button>
						</div>
					</td>
					<td style="vertical-align: middle;width:80px;text-align:center"><b>{{= $value.orderPrice}}</b></td>
					<td style="vertical-align: middle;width:45px"><span class="icon-trash icon-2x" f-id="{{= $value.settleCartStr}}" style="cursor:pointer"></span></td>
				</tr>{{/each}}
			</table>
		</div>
		<div class="panel-footer">
			<div class="text-right" style="color:red">总金额：￥{{= orderPrice}}</div>
		</div>
	</div>
</script>
<script type="text/tmpl" id="settleTmpl">
	<div class="text-right"><span>优惠：￥&nbsp;{{= discountPrice}}</span>&nbsp;&nbsp;<span style="color:red">总价：￥&nbsp;{{= orderPrice}}</span>&nbsp;&nbsp;<button onclick="settle()" {{if settle}} disabled="disabled" {{/if}} class="btn btn-danger">结算</button></div>
</script>
<script type="text/javascript">
$(function(){
	f.setTitle("我的购物车");
	var container = $("#container");
	function loadCart(){
		container.startMask({message:"数据加载中"});
		$.getJSON(f.dynUrl+"/cart/settle.htm",function(d){
			container.closeMask();
			if(d.success){
				container.empty();
				if(d.result.settlements.length == 0){
					var btn = $('<div class="text-center">购物车还是空的&nbsp;&nbsp;<a class="btn btn-success" href="'+f.staUrl+'/page/index/mindex.htm'+'">去购物</a></div>');
					container.append(btn);
					return;
				}
				$.each(d.result.settlements,function(i,obj){
					container.append(cartBuilder(obj));
				});
				container.append($("#settleTmpl").tmpl(d.result));
			}else{
				f.dialogAlert(d.errMsg);
			}
		});
	}
	loadCart();
	settle = function(){
		window.localtion.href = f.staUrl + '/page/orders/collate.htm';
	}
	function cartBuilder(obj){
		obj.imgUrl = f.imgUrl();
		var cart = $("#tmpl").tmpl(obj);
		cart.find(".icon-minus").each(function(){
			$(this).click(function(){
				var num = parseInt($(this).next("input").val()||1);
				if(num == 1){
					return;
				}
				f.updCart($(this).attr("f-id"), num - 1, function(d){
					loadCart();
				});
			});
		});
		cart.find(".icon-plus").each(function(){
			$(this).click(function(){
				var num = parseInt($(this).prev("input").val()||1);
				if(num == 99){
					return;
				}
				f.updCart($(this).attr("f-id"), num + 1, function(d){
					loadCart();
				});
			});
		});
		cart.find("input[type=text]").each(function(){
			$(this).change(function(){
				var input = $(this);
				var num = parseInt(input.val());
				if(num > 99){
					num = 99;
					input.val(num);
				}else if(num < 1){
					num = 1;
					input.val(num);
				}
				f.updCart($(this).attr("f-id"), num, function(d){
					loadCart();
				});
			}).keypress(function(e){
				if(e.which == 8)return;
				if(e.which<48||e.which>57){
					e.preventDefault();
				}
			});
		});
		cart.find(".icon-trash").each(function(){
			$(this).click(function(){
				f.delCart($(this).attr("f-id"),function(){
					loadCart();
				})
			})
		});
		var unCheckedNum = 0;
		cart.find("input[type=checkbox]").each(function(){
			if(!$(this).prop("checked")){
				unCheckedNum++;
			}
			$(this).change(function(){
				f.checkCart($(this).attr("f-id"),$(this).prop("checked"),function(){
					loadCart();
				});
			});
		});
		if(unCheckedNum < 2){
			cart.find("input[type=checkbox]:first").prop("checked","checked");
		}
		return cart;
	}
})
</script>
</body>
</html>