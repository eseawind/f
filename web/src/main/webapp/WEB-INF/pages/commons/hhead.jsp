<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<div>
	<ul class="nav nav-pills">
      <li class="active"><a href="#">后台管理系统</a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          	商家
          <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
          <li><a href="${staUrl }/page/merchant/hmerchant.htm">商家维护</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          	商品
          <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
          <li><a href="${staUrl }/page/category/hcategory.htm">分类</a></li>
          <li><a href="#">商品维护</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          	订单
          <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
          <li><a href="#">订单查询</a></li>
          <li role="separator" class="divider"></li>
          <li><a href="#">订单统计</a></li>
        </ul>
      </li>
    </ul>
<div style="position:absolute;bottom:0px;left:0px;width:100%;z-index:99999" class="text-center" id="hhead_errorAlert"></div>
</div>

<hr style="margin-top:10px;margin-bottom:10px"/>

