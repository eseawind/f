<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/mcommons.jsp" %>
</head>
<body class="container">
<input id="code" value="${code }" type="hidden"/>
<input id="merchantId" value="${merchantId }" type="hidden"/>
<%@ include file="../commons/mhead.jsp" %>
<ul class="nav nav-tabs row text-center" id="tabs">
  <li role="presentation" class="active col-md-4 col-sm-4 col-xs-4"><a href="javascript:void(0)" onclick="sortFun(1,this)">综合</a></li>
  <li role="presentation" class="col-md-4 col-sm-4 col-xs-4"><a href="javascript:void(0)" onclick="sortFun(2,this)">库存&nbsp;<i></i></a></li>
  <li role="presentation" class="col-md-4 col-sm-4 col-xs-4"><a href="javascript:void(0)" onclick="sortFun(3,this)">价格&nbsp;<i></i></a></li>
</ul>
<div id="goodsContainer">
	
</div>
<script type="text/javascript">
$(function(){
	window.sortFun = function(v,obj){
		container.empty();
		obj = $(obj);
		obj.parent().parent().find('i').removeClass();
		if(v == 1){
			sort = 0;
		}else if(v == 3){
			if(obj.attr("flag") == "true"){
				sort = 2;
				obj.attr("flag","false");
				obj.children('i').replaceWith('<i class="icon-caret-down"></i>');
			}else{
				sort = 1;
				obj.attr("flag","true");
				obj.children('i').replaceWith('<i class="icon-caret-up"></i>');
			}
		}else if(v == 2){
			if(obj.attr("flag") == "true"){
				sort = 4;
				obj.attr("flag",false);
				obj.children('i').replaceWith('<i class="icon-caret-down"></i>');
			}else{
				sort = 3;
				obj.attr("flag",true);
				obj.children('i').replaceWith('<i class="icon-caret-up"></i>');
			}
		}
		page = 1;
		loading = false;
		loadFun();
	}
	var code = $("#code").val();
	var merchantId = $("#merchantId").val();
	var page = 1;
	var rows = 10;
	var sort = 0;
	var loading = false;
	var tabs = $("#tabs");
	var container = $("#goodsContainer");
	tabs.children("li").each(function(){
		var li = $(this);
		li.click(function(){
			tabs.children("li").removeClass("active")
			li.addClass("active");
		});
	});
	function loadFun(){
		if(loading){
			return;
		}
		loading = true;
		var param = {page:page++,rows:rows};
		code&&(param.code = code);
		merchantId&&(param.merchantId = merchantId);
		param.sort = sort;
		$.getJSON(f.dynUrl+"/category/mlist.htm",param,function(d){
			if(d.success){
				var arr = d.result.entry;
				var param = [];
				$.each(arr,function(i,obj){
					param.push(obj.cgid);
					container.goodsContainerBuilder(obj);
				});
				if(param.length == 0){
					return;
				}
				$.getJSON(f.dynUrl+"/goods/dyn/cgids.htm?cgids="+param.join(","),function(d){
					if((page-1)*rows >= d.result.total){
						loading = false;
					}
					if(d.success){
						var arr = d.result;
						var po = {};
						$.each(arr,function(i,obj){
							po[obj.cgid] = obj.price;
						});
						container.find(".gprice span").each(function(){
							var t = $(this);
							t.text(po[t.attr("f-id")]);
						});
					}else{
						f.dialogAlert(d.errMsg);
					}
				});
			}else{
				loading = false;
				f.dialogAlert(d.errMsg);
			}
		});
	};
	loadFun();
	f.backTopBtn();
})
</script>
</body>
</html>