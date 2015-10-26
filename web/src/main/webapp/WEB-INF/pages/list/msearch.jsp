<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/mcommons.jsp" %>
</head>
<body class="container">
	<div class="navbar navbar-default">
	  <div class="row container-fluid" style="padding: 10px 5px 10px 5px">
	  	<div class="col-md-2 col-xs-2 col-sm-2">
	  		<i class="icon-angle-left icon-2x"></i>
	  	</div>
	  	<div class="col-md-8 col-xs-8 col-sm-8">
	  		<div class="input-group">
				<input type="text" class="form-control" id="qtext">
				<span class="input-group-addon" id="qBtn">
					<i class="icon-search" style="cursor:pointer"></i>
				</span>
			</div>
	  	</div>
	  	<div class="col-md-2 col-xs-2 col-sm-2 text-right">
			<div class="dropdown">
			  <span class="dropdown-toggle" id="f_global_msearch_dropdown" data-toggle="dropdown">
			    <i class="icon-align-justify icon-2x"></i>
			  </span>
			  <ul class="dropdown-menu pull-right" aria-labelledby="f_global_msearch_dropdown">
			    <li><a href="${staUrl }/page/index/mindex.htm"><i class="icon-home"></i>&nbsp;首页</a></li>
		   		<li><a href="${staUrl }/page/list/msearch.htm"><i class="icon-search"></i>&nbsp;分类搜索</a></li>
			    <li><a href="${staUrl }/page/cart/msettle.htm"><i class="icon-shopping-cart"></i>&nbsp;我的购物车</a></li>
			    <li><a href="${staUrl }/page/user/mcenter.htm"><i class="icon-user"></i>&nbsp;个人中心</a></li>
			  </ul>
			</div>
	  	</div>
	  </div>
	</div>
	<ul class="nav nav-tabs row text-center" id="tabs">
	  <li role="presentation" class="active col-md-4 col-sm-4 col-xs-4"><a href="javascript:void(0)" onclick="sortFun(1,this)">综合</a></li>
 	  <li role="presentation" class="col-md-4 col-sm-4 col-xs-4"><a href="javascript:void(0)" onclick="sortFun(2,this)">库存&nbsp;<i></i></a></li>
      <li role="presentation" class="col-md-4 col-sm-4 col-xs-4"><a href="javascript:void(0)" onclick="sortFun(3,this)">价格&nbsp;<i></i></a></li>
	</ul>
	<i id="categoryBtn" style="z-index:1000;position:fixed;left:6%;bottom:10px;cursor:pointer" class="icon-th-large icon-2x"><font style="font-size:56%"></font></i>
	<div id="categoryContainer" class="f-menu" style="position:fixed;bottom:0px;left:0px;height:100%;overflow-y:auto;margin-bottom:0px;display:none">
		<ul class="list-group" >
		</ul>
	</div>
	<div id="goodsContainer">
	</div>
	<div id="searchContainer">
	</div>
	<button class="btn btn-default btn-block" id="mostBtn">点击加载更多</button>
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
		if(isSearch){
			qLoadFun();
		}else{
			loadFun();
		}
	}
	var code = undefined;
	var page = 1;
	var rows = 20;
	var sort = 0;
	var loading = false;
	var tabs = $("#tabs");
	var container = $("#goodsContainer");
	var isSearch = false;
	var mostBtn = $("#mostBtn");
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
		if(mostBtn.prop('disabled')){
			mostBtn.text("点击加载更多数据");
			mostBtn.removeProp('disabled');
		}
		loading = true;
		var param = {page:page++,rows:rows};
		code&&(param.code = code);
		param.sort = sort;
		$.getJSON(f.dynUrl+"/category/mlist.htm",param,function(d){
			if(d.success){
				var arr = d.result.entry;
				var total = d.result.total;
				var param = [];
				$.each(arr,function(i,obj){
					param.push(obj.cgid);
					container.goodsContainerBuilder(obj);
				});
				if(param.length == 0){
					mostBtn.text("没有更多数据了");
					mostBtn.prop('disabled','disabled');
					return;
				}
				$.getJSON(f.dynUrl+"/goods/dyn/cgids.htm?cgids="+param.join(","),function(d){
					if((page-1)*rows < total){
						loading = false;
					}else{
						mostBtn.text("没有更多数据了");
						mostBtn.prop('disabled','disabled');
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
	var categoryCon = $("#categoryContainer");
	var searchCon = $("#searchContainer");
	$("#categoryBtn").click(function(event){
		isSearch = false;
		searchCon.hide();
		container.show();
		categoryCon.show();
		event.stopPropagation();
	});
	categoryCon.click(function(){
		categoryCon.hide();
	});
	function childrenCategory(li,fid){
		var div = li.next('div');
		if(div.length == 0){
			div = $('<div></div>');
			$.getJSON(f.dynUrl+"/category/list.htm?fid="+fid,function(d){
				if(d.success){
					var arr = d.result;
					$.each(arr,function(i,c){
						var li2 = $('<li class="list-group-item" style="padding-left:'+((c.layer-1)*20+15)+'px"></li>');
						li2.click(function(e){
							childrenCategory(li2,c.id);
							e.preventDefault();
							e.stopPropagation();
						});
						var cObj = $('<span style="cursor:pointer">'+c.name+'</span>')
						cObj.click(function(e){
							container.empty();
							code = c.code;
							page = 1;
							loading = false;
							loadFun();
							categoryCon.hide();
							e.preventDefault();
							e.stopPropagation();
						});
						div.append(li2.append(cObj));
					});
					li.after(div);
				}else{
					f.dialogAlert(d.errMsg);
				}
			});
		}else{
			if(div.attr('f-state') == 'hide'){
				div.show();
				div.attr('f-state','show');
			}else{
				div.hide();
				div.attr('f-state','hide');
			}
		}
	}
	$.getJSON(f.dynUrl+"/category/list.htm?fid=0",function(d){
		if(d.success){
			var jObj = categoryCon.children('ul').eq(0);
			$.each(d.result,function(i,c){
				var li = $('<li class="list-group-item"></li>');
				li.click(function(e){
					childrenCategory(li,c.id);
					e.preventDefault();
					e.stopPropagation();
				});
				var cObj = $('<span style="cursor:pointer">'+c.name+'</span>')
				cObj.click(function(e){
					container.empty();
					code = c.code;
					page = 1;
					loading = false;
					loadFun();
					categoryCon.hide();
					e.preventDefault();
					e.stopPropagation();
				});
				jObj.append(li.append(cObj));
			});
		}else{
			f.dialogAlert(d.errMsg);
		}
	});
	f.backTopBtn();
	loadFun();
	var qtext = $("#qtext");
	var qBtn = $("#qBtn");
	var qpage = 1;
	qBtn.click(function(){
		var q = $.trim(qtext.val());
		if(q != ''){
			loading = false;
			isSearch = true;
			qpage = 1;
			container.hide();
			searchCon.show();
			qLoadFun();
		}
	});
	mostBtn.click(function(){
		if(isSearch){
			qLoadFun();
		}else{
			loadFun();
		}
	});
	function qLoadFun(){
		if(loading){
			return;
		}
		var q = $.trim(qtext.val());
		if(q == ''){
			return;
		}
		loading = true;
		if(mostBtn.prop('disabled')){
			mostBtn.text("点击加载更多数据");
			mostBtn.removeProp('disabled');
		}
				
	}
});
</script>
</body>
</html>