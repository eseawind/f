<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<div>
	<ul class="nav nav-pills">
      <li class="active"><a>后台管理系统</a></li>
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
          	区域和分类
          <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
          <li><a href="${staUrl }/page/category/hcategory.htm">分类维护</a></li>
          <li><a href="${staUrl }/page/area/area.htm">区域维护</a></li>
          <li><a href="${staUrl }/page/dict/dict.htm">字典维护</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          	订单
          <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
          <li><a href="${staUrl }/page/orders/hlist.htm">订单查询</a></li>
          <li role="separator" class="divider"></li>
          <li><a href="#">订单统计</a></li>
        </ul>
      </li>
      <li><a href="javascript:f.unlogin()">退出</a></li>
    </ul>
<div style="position:fixed;bottom:0px;left:0px;width:100%;z-index:99999" class="text-center" id="hhead_errorAlert"></div>
</div>

<hr style="margin-top:10px;margin-bottom:10px"/>

