<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<div class="navbar navbar-default">
  <div class="row container-fluid" style="padding: 10px 5px 10px 5px">
  	<div class="col-md-2 col-xs-2 col-sm-2">
  		<i class="icon-angle-left icon-2x" style="cursor:pointer" onclick="history.go(-1)"></i>
  	</div>
  	<div class="col-md-8 col-xs-8 col-sm-8 text-center">
  		<font id="f_gloal_mhead_title"></font>
  	</div>
  	<div class="col-md-2 col-xs-2 col-sm-2 text-right">
		<div class="dropdown">
		  <span class="dropdown-toggle" id="f_global_mhead_dropdown" data-toggle="dropdown">
		    <i class="icon-align-justify icon-2x" style="cursor:pointer"></i>
		  </span>
		  <ul class="dropdown-menu pull-right" aria-labelledby="f_global_mhead_dropdown">
		    <li><a href="${staUrl }/page/index/mindex.htm"><i class="icon-home"></i>&nbsp;首页</a></li>
		    <li><a href="${staUrl }/page/list/msearch.htm"><i class="icon-search"></i>&nbsp;分类搜索</a></li>
		    <li><a href="#"><i class="icon-shopping-cart"></i>&nbsp;我的购物车</a></li>
		    <li><a href="#"><i class="icon-user"></i>&nbsp;个人中心</a></li>
		  </ul>
		</div>
  	</div>
  </div>
</div>

