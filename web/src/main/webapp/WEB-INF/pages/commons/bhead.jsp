<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<div>
	<ul class="nav nav-pills">
      <li class="active"><a>商家管理系统</a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          	商品
          <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
          <li><a href="${staUrl }/page/brand/bbrand.htm">品牌维护</a></li>
          <li><a href="${staUrl }/page/goods/bgoods_add.htm">新增商品</a></li>
          <li><a href="${staUrl }/page/goods/bgoods.htm">商品维护</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          	订单
          <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
          <li><a href="${staUrl }/page/orders/blist.htm">订单查询</a></li>
          <li role="separator" class="divider"></li>
          <li><a href="#">订单统计</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          	活动
          <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
          <li><a href="#">优惠券</a></li>
        </ul>
      </li>
      <li><a href="javascript:f.unlogin()">退出</a></li>
    </ul>
<div style="position:fixed;bottom:0px;left:0px;width:100%;z-index:99999" class="text-center" id="bhead_errorAlert"></div>
</div>
<hr style="margin-top:10px;margin-bottom:10px"/>
