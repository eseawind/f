<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/mcommons.jsp" %>
<title>${def.gname }|${def.cgname }</title>
</head>
<body class="container">
<%@ include file="../commons/mhead.jsp" %>
<div class="text-center">
	<input type="hidden" value="${def.cgid }" id="cgid"/>
	<div id="carousel" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<c:if test="${not empty def.photo }">
				<li data-target="#carousel" data-slide-to="0" class="active" style="background-color:black"></li>
			</c:if>
			<c:if test="${not empty def.photo1 }">
				<li data-target="#carousel" data-slide-to="1" style="background-color:black"></li>
			</c:if>
			<c:if test="${not empty def.photo2 }">
				<li data-target="#carousel" data-slide-to="2" style="background-color:black"></li>
			</c:if>
			<c:if test="${not empty def.photo3 }">
				<li data-target="#carousel" data-slide-to="3" style="background-color:black"></li>
			</c:if>
			<c:if test="${not empty def.photo4 }">
				<li data-target="#carousel" data-slide-to="4" style="background-color:black"></li>
			</c:if>
		  </ol>
		  <div class="carousel-inner" role="listbox">
		  	<c:if test="${not empty def.photo }">
				<div class="item active">
			      <div class="text-center"><img src="${staUrl }${def.photo }" style="width:220px;height:220px"></div>
			    </div>
			</c:if>
			<c:if test="${not empty def.photo1 }">
				<div class="item">
			      <div class="text-center"><img src="${staUrl }${def.photo1 }" style="width:220px;height:220px"></div>
			    </div>
			</c:if>
			<c:if test="${not empty def.photo2 }">
				<div class="item">
			      <div class="text-center"><img src="${staUrl }${def.photo2 }" style="width:220px;height:220px"></div>
			    </div>
			</c:if>
			<c:if test="${not empty def.photo3 }">
				<div class="item">
			      <div class="text-center"><img src="${staUrl }${def.photo3 }" style="width:220px;height:220px"></div>
			    </div>
			</c:if>
			<c:if test="${not empty def.photo4 }">
				<div class="item">
			      <div class="text-center"><img src="${staUrl }${def.photo4 }" style="width:220px;height:220px"></div>
			    </div>
			</c:if>
		  </div>
	</div>
</div>
<ul class="list-group">
	<li class="list-group-item" style="color:red"><h4>￥&nbsp;<span id="price"></span></h4></li>
	<li class="list-group-item"><h4>${def.gname }</h4></li>
	<c:if test="${not empty def.remark }">
		<li class="list-group-item" style="color:red">${def.remark }</li>
	</c:if>
	<li class="list-group-item">
		<span style="float:left;padding:10px 10px 10px 0">购买数量：</span>
		<button id="minusBtn" class="btn btn-default" style="float:left;height:35px"><i class="icon-minus"></i></button>
		<input id="buyNum" class="form-control" style="width:50px;float:left;height:35px" value="1"/>
		<button id="plusBtn" class="btn btn-default" style="height:35px"><i class="icon-plus"></i></button>
		<span style="color:red;padding:10px 0 10px 0" id="info"></span>
	</li>
</ul>
<div class="panel panel-default">
	<div class="panel-heading">商品规格</div>
	<div class="panel-body">
		<c:forEach items="${cgs}" var="cg">
			<c:choose>
				<c:when test="${cg.cgid == def.cgid }">
					<div style="float:left;margin:10px"><a href="${dynUrl }/goods/detail/${cg.cgid}.htm" class="btn btn-default btn-danger">${cg.cgname }</a></div>
				</c:when>
				<c:otherwise>
					<div style="float:left;margin:10px"><a href="${dynUrl }/goods/detail/${cg.cgid}.htm" class="btn btn-default">${cg.cgname }</a></div>
				</c:otherwise>
			</c:choose>
			
		</c:forEach>
	</div>
</div>
<div class="panel panel-default" style="margin-bottom:55px">
	<div class="panel-heading">
		商品详情
	</div>
	<div class="panel-body">
		${def.descript }
	</div>
</div>
<div class="btn-group" style="position:fixed;bottom:0;left:0;width:100%">
	<c:choose>
		<c:when test="${isCollect }">
			<button class="btn btn-default btn-lg" style="width:30%" id="collectBtn" f-id="${def.cgid}" disabled="disabled">
				已收藏
			</button>
		</c:when>
		<c:otherwise>
			<button class="btn btn-default btn-lg" style="width:30%" id="collectBtn" f-id="${def.cgid}">
				<i class="icon-heart-empty"></i>&nbsp;&nbsp;收藏
			</button>
		</c:otherwise>
	</c:choose>
	
	<button class="btn btn-default btn-lg" style="width:30%" id="buyBtn" disabled="disabled">
		立刻购买
	</button>
	<button class="btn btn-danger btn-lg" onclick="addCartFun(${def.merchantId},${def.cgid })" style="width:40%" id="addCartBtn" disabled="disabled">加入购物车</button>
</div>
<script type="text/javascript">
$(function(){
	var price = 0;
	var number = 0;
	var buyBtn = $("#buyBtn");
	var addCartBtn = $("#addCartBtn");
	f.setTitle("商品详情页");
	$.getJSON(f.dynUrl+"/goods/dyn/cgids.htm?cgids="+$("#cgid").val(),function(d){
		if(d.success){
			var data = d.result[0];
			price = data.price;
			$("#price").text(data.price);
			number = data.number
			if(number > 0){
				buyBtn.removeProp("disabled");
				addCartBtn.removeProp("disabled");
				if(number < 100){
					$("#info").text("只剩" + number);
				}
			}else{
				$("#info").text("无货");
			}
		}else{
			f.dialogAlert(d.errMsg);
		}
	});
	$("#minusBtn").click(function(){
		var num = parseInt(buyNum.val());
		if(num > 1){
			num--;
			buyNum.val(num);
		}
	});
	$("#plusBtn").click(function(){
		var num = parseInt(buyNum.val());
		if(num >= number){
			return;
		}
		if(num < 100){
			num++;
			buyNum.val(num);
		}
	});
	var buyNum = $("#buyNum").keypress(function(e){
		if(e.which == 8||e.which == 0) return;
		if(e.which<47||e.which>57){
			e.preventDefault();
		}
	});
	buyNum.keyup(function(){
		var num = buyNum.val();
		if(num == ''||parseInt(num) <= 0){
			buyNum.val(1);
		}else if(parseInt(num) > number){
			buyNum.val(number);
		}
	});
	buyNum.focus(function(){
		buyNum.select();
	});
	addCartFun = function(merchantId,cgids){
		f.addCart(merchantId, cgids, buyNum.val(),function(){
			f.transientAlert("成功加入购物车");
		});
	}
	$("#collectBtn").click(function(){
		var obj = $(this);
		f.addCollect(obj.attr("f-id"),function(){
			obj.empty();
			obj.prop("disabled","disabled");
			obj.text("已收藏");
		},function(code,errMsg){
			if(parseInt(code) == 142){
				obj.empty();
				obj.prop("disabled","disabled");
				obj.text("已收藏");
			}else{
				f.dialogAlert(errMsg);
			}
		});
	});
})
</script>
</body>
</html>