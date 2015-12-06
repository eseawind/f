<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${staUrl }/res/ztree/css/zTreeStyle/zTreeStyle.css">
<%@include file="../commons/hcommons.jsp" %>
<script type="text/javascript" src="${staUrl }/res/ztree/js/jquery.ztree.all-3.5.min.js"></script>
</head>
<body class="container">
<%@include file="../commons/hhead.jsp" %>
<ul class="ztree" id="ztree"></ul>
<div class="panel panel-default" id="win">
	<input name="id" />
	<div class="panel-body form">
		<div class="form-group">
			<label></label>
			<input class="form-control" name="name" />
		</div>
		<div class="form-group">
			<input name="isDel" value="1" type="radio"/>启用
			<input name="isDel" value="127" type="radio"/>停用
		</div>
		<button class="btn btn-block btn-primary">保存</button>
	</div>
</div>
<ul class="dropdown-menu" id="menu" style="cursor:pointer">
	<li><a href="javascript:add()">新增子字典</a></li>
	<li><a href="javascript:upd()">修改字典</a></li>
</ul>
<script type="text/javascript">
$(function(){
	var win = $("#win");
	win.f_modal({});
	var menu = $("#menu");
	var treeId;
	var curNode;
	var setting = {
			async:{
				enable:true,
				url:f.dynUrl+'/dict/tree.htm',
				dataType:'json',
				dataFilter:function(tid,parentNode,resData){
					var arr = [];
					if(resData.success){
						$.each(resData.result,function(i,d){
							var node = {};
							node.id = d.id;
							node.fid = d.fid;
							node.relName = d.name;
							node.name = d.name+":"+(d.isDel == 127?"隐藏":"启用");
							node.layer = parentNode.layer + 1;
							node.isParent = true;
							arr.push(node);
						});
					}
					return arr;
				},
				autoParam:['id=fid']
			},
			callback:{
				beforeAsync:function(){
				},
				onAsyncSuccess:function(){
				},
				onAsyncError:function(event, tid, treeNode, XMLHttpRequest, textStatus, errorThrown){
					f.alertError(textStatus);
				},
				onRightClick:function(e,tid,treeNode){
					treeId = tid;
					curNode = treeNode;
					menu.css('top',e.pageY);
					menu.css('left',e.pageX);
					menu.show();
				}
			}
	};
	$.fn.zTree.init($("#ztree"),setting,[{id:0,name:"字典",isParent:true,layer:0}]);
	$(document).click(function(){
		menu.hide();
	});
})
</script>
</body>
</html>