<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/mcommons.jsp" %>
</head>
<body>
	<div class="navbar navbar-default">
	  <div class="row container-fluid" style="padding: 10px 5px 10px 5px">
	  	<div class="col-md-2 col-xs-2 col-sm-2">
	  		<i class="icon-angle-left icon-2x"></i>
	  	</div>
	  	<div class="col-md-8 col-xs-8 col-sm-8">
	  		<div class="input-group">
				<input type="text" class="form-control">
				<span class="input-group-addon">
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
			    <li><a href="#"><i class="icon-home"></i>&nbsp;首页</a></li>
			    <li><a href="#"><i class="icon-search"></i>&nbsp;分类搜索</a></li>
			    <li><a href="#"><i class="icon-shopping-cart"></i>&nbsp;我的购物车</a></li>
			    <li><a href="#"><i class="icon-user"></i>&nbsp;个人中心</a></li>
			  </ul>
			</div>
	  	</div>
	  </div>
	</div>
	<div class="container">
		<ul class="nav nav-tabs row text-center">
		  <li role="presentation" class="active col-md-4 col-sm-4 col-xs-4"><a href="#">综合</a></li>
		  <li role="presentation" class="col-md-4 col-sm-4 col-xs-4"><a href="#">销量</a></li>
		  <li role="presentation" class="col-md-4 col-sm-4 col-xs-4"><a href="#">价格</a></li>
		</ul>
		<i id="categoryBtn" style="z-index:1000;position:fixed;left:10px;bottom:10px" class="icon-th-large icon-2x"></i>
		<div id="categoryContainer" class="f-menu" style="position:fixed;bottom:0px;left:0px;height:100%;overflow-y:auto;margin-bottom:0px;display:none">
			<ul class="list-group" >
				<li class="list-group-item"><span>果蔬</span></li>
				<li class="list-group-item"><span>肉类</span></li>
				<li class="list-group-item"><span>海鲜</span></li>
				<li class="list-group-item"><span>粮油</span></li>
				<li class="list-group-item"><span>进口水果</span></li>
				<li class="list-group-item"><span>果蔬</span></li>
				<li class="list-group-item"><span>肉类</span></li>
				<li class="list-group-item"><span>海鲜</span></li>
				<li class="list-group-item"><span>粮油</span></li>
				<li class="list-group-item"><span>进口水果</span></li>
				<li class="list-group-item"><span>果蔬</span></li>
				<li class="list-group-item"><span>肉类</span></li>
				<li class="list-group-item"><span>海鲜</span></li>
				<li class="list-group-item"><span>粮油</span></li>
				<li class="list-group-item"><span>进口水果</span></li>
			</ul>
		</div>
		<div id="goodsContainer">
			<div class="f-goods-container">
				<div class="f-goods-block">
					<div class="f-goods-img">
						<img src="http://img11.360buyimg.com/n7/jfs/t1747/94/1121132190/123619/d307e19b/55e00172N89c73848.jpg" />
					</div>
					<ul>
						<li class="gprice"><font>￥</font>1</li>
						<li class="gname">立即预约，19号1579超低价抢购！黑色919，红色乐迷节，点击查看更多优惠</li>
						<li class="goper btn-group">
							<button class="btn btn-default f-share">分享</button>
							<button class="btn btn-default">收藏</button>
							<button class="btn btn-default">加入购物车</button>
						</li>
					</ul>
				</div>
			</div>
			<div class="f-goods-container">
				<div class="f-goods-block">
					<div class="f-goods-img">
						<img alt="" src="http://img11.360buyimg.com/n7/jfs/t1747/94/1121132190/123619/d307e19b/55e00172N89c73848.jpg" />
					</div>
					<ul>
						<li class="gprice"><font>￥</font>1</li>
						<li class="gname">立即预约，19号1579超低价抢购！黑色919，红色乐迷节，点击查看更多优惠</li>
						<li class="goper btn-group">
							<button class="btn btn-default f-share">分享</button>
							<button class="btn btn-default">收藏</button>
							<button class="btn btn-default">加入购物车</button>
						</li>
					</ul>
				</div>
			</div>
			<div class="f-goods-container">
				<div class="f-goods-block">
					<div class="f-goods-img">
						<img alt=""
							src="http://img11.360buyimg.com/n7/jfs/t1747/94/1121132190/123619/d307e19b/55e00172N89c73848.jpg" />
					</div>
					<ul>
						<li class="gprice"><font>￥</font>1</li>
						<li class="gname">立即预约，19号1579超低价抢购！黑色919，红色乐迷节，点击查看更多优惠立即预约，19号1579超低价抢购！黑色919，红色乐迷节，点击查看更多优惠立即预约，19号1579超低价抢购！黑色919，红色乐迷节，点击查看更多优惠立即预约，19号1579超低价抢购！黑色919，红色乐迷节，点击查看更多优惠</li>
						<li class="goper btn-group">
							<button class="btn btn-default f-share">分享</button>
							<button class="btn btn-default">收藏</button>
							<button class="btn btn-default">加入购物车</button>
						</li>
					</ul>
				</div>
			</div>
			<div class="f-goods-container">
				<div class="f-goods-block">
					<div class="f-goods-img">
						<img alt=""
							src="http://img11.360buyimg.com/n7/jfs/t1747/94/1121132190/123619/d307e19b/55e00172N89c73848.jpg" />
					</div>
					<ul>
						<li class="gprice"><font>￥</font>1</li>
						<li class="gname">立即预约，19号1579超低价抢购！黑色919，红色乐迷节，点击查看更多优惠</li>
						<li class="goper btn-group">
							<button class="btn btn-default f-share">分享</button>
							<button class="btn btn-default">收藏</button>
							<button class="btn btn-default">加入购物车</button>
						</li>
					</ul>
				</div>
			</div>
			<div class="f-goods-container">
				<div class="f-goods-block">
					<div class="f-goods-img">
						<img alt=""
							src="http://img11.360buyimg.com/n7/jfs/t1747/94/1121132190/123619/d307e19b/55e00172N89c73848.jpg" />
					</div>
					<ul>
						<li class="gprice"><font>￥</font>1</li>
						<li class="gname">立即预约，19号1579超低价抢购！黑色919，红色乐迷节，点击查看更多优惠</li>
						<li class="goper btn-group">
							<button class="btn btn-default f-share">分享</button>
							<button class="btn btn-default">收藏</button>
							<button class="btn btn-default">加入购物车</button>
						</li>
					</ul>
				</div>
			</div>
			<div class="f-goods-container">
				<div class="f-goods-block">
					<div class="f-goods-img">
						<img alt=""
							src="http://img11.360buyimg.com/n7/jfs/t1747/94/1121132190/123619/d307e19b/55e00172N89c73848.jpg" />
					</div>
					<ul>
						<li class="gprice"><font>￥</font>1</li>
						<li class="gname">立即预约，19号1579超低价抢购！黑色919，红色乐迷节，点击查看更多优惠</li>
						<li class="goper btn-group">
							<button class="btn btn-default f-share">分享</button>
							<button class="btn btn-default">收藏</button>
							<button class="btn btn-default">加入购物车</button>
						</li>
					</ul>
				</div>
			</div>
			<div class="f-goods-container">
				<div class="f-goods-block">
					<div class="f-goods-img">
						<img alt=""
							src="http://img11.360buyimg.com/n7/jfs/t1747/94/1121132190/123619/d307e19b/55e00172N89c73848.jpg" />
					</div>
					<ul>
						<li class="gprice"><font>￥</font>1</li>
						<li class="gname">立即预约，19号1579超低价抢购！黑色919，红色乐迷节，点击查看更多优惠</li>
						<li class="goper btn-group">
							<button class="btn btn-default f-share">分享</button>
							<button class="btn btn-default">收藏</button>
							<button class="btn btn-default">加入购物车</button>
						</li>
					</ul>
				</div>
			</div>
			
		</div>
	</div>
<script type="text/javascript">
$(function(){
	var categoryCon = $("#categoryContainer");
	categoryCon.find("span").click(function(){
		f.dialogAlert($(this).html());
		categoryCon.hide();
	});
	$("#categoryBtn").click(function(event){
		categoryCon.show();
		event.stopPropagation();
	});
	f.backTopBtn();
});
</script>
</body>
</html>