<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${staUrl }/res/ztree/css/zTreeStyle/zTreeStyle.css">
<%@ include file="../commons/hcommons.jsp" %>
<script type="text/javascript" src="${staUrl }/res/ztree/js/jquery.ztree.all-3.5.min.js"></script>
</head>
<body>
<div class="container">
<%@ include file="../commons/hhead.jsp" %>
<ul id="category" class="ztree"></ul>
</div>
<ul class="dropdown-menu" id="menu">
	<li><a href="javascript:void(0)" onclick="addCategory()">添加子分类</a></li>
	<li class="divider"></li>
    <li><a href="javascript:void(0)" onclick="updCategory()">修改分类信息</a></li>
</ul>
<div id="win" style="display:none">
	<form class="form" id="form">
		<input name="id" type="hidden"/>
		<div class="form-group">
			<label>分类code：</label>
			<input class="form-control" f-type="text" name="code" f-options="required:true,maxLength:9" />
		</div>
		<div class="form-group">
			<label>分类名称：</label>
			<input class="form-control" f-type="text" id="name" name="name" f-options="required:true,maxLength:20" val="123"/>
		</div>
		<div class="form-group">
			<label>是否启用：</label>
			<input type="radio" name="isUse" value="1" checked="checked"/>启用
			<input type="radio" name="isUse" value="0"/>隐藏
		</div>
	</form>
	<button id="submit" class="btn btn-block btn-primary">保存</button>
</div>
<script type="text/javascript">
$(function(){
	var curNode;
	var treeId;
	var menu = $("#menu");
	var form = $("#form");
	var win = $("#win");
	var tree = $("#category");
	win.f_modal({});
	addCategory = function(){
		if(curNode.open && curNode.layer < 3){
			form.f_formClear();
			win.f_modal('setTitle','新增分类');
			win.f_modal('show');
		}
	}
	updCategory = function(){
		if(curNode.layer>0 && curNode.layer < 4){
			form.f_formClear();
			win.f_modal('setTitle','维护分类');
			win.f_modal('show');
			form.f_formLoad({id:curNode.id,code:curNode.code,name:curNode.relName,isUse:curNode.isUse});
		}
	}
	$("#submit").click(function(e){
		if(form.f_isValid()){
			var param = form.f_serialized();
			if(!param.id){
				param.layer = curNode.layer + 1;
				param.fid = curNode.id;
			}
			win.startMask();
			$.post(f.dynUrl+'/category/addOrUpd.htm',param,function(d){
				if(!d.success){
					f.alertError(d.errMsg);
				}else{
					var treeObj = $.fn.zTree.getZTreeObj(treeId);
					treeObj.reAsyncChildNodes(param.id?curNode.getParentNode():curNode, "refresh");
				}
				win.closeMask();
				win.f_modal('hide');
			},'json');
		}
		e.preventDefault();
		e.stopPropagation();
	});
	$(document).click(function(){
		menu.hide();
	});
	
	var setting = {
		async:{
			enable:true,
			url:f.dynUrl+'/category/list.htm',
			dataType:'json',
			dataFilter:function(tid,parentNode,resData){
				var arr = [];
				if(resData.success){
					$.each(resData.result,function(i,d){
						var node = {};
						node.id = d.id;
						node.pId = d.fid;
						node.code = d.code;
						node.name = d.name+":"+d.code+":"+(d.isUse == 0?"隐藏":"启用");
						node.relName = d.name;
						node.layer = d.layer;
						node.isParent = d.layer == 3?false:true;
						node.isUse = d.isUse;
						arr.push(node);
					});
				}
				return arr;
			},
			autoParam:['id=fid']
		},
		callback:{
			beforeAsync:function(){
				tree.startMask();
			},
			onAsyncSuccess:function(){
				tree.closeMask();
			},
			onAsyncError:function(event, tid, treeNode, XMLHttpRequest, textStatus, errorThrown){
				tree.closeMask();
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
	$.fn.zTree.init(tree, setting, [{id:0,name:"分类",isParent:true,layer:0}]);
});
</script>
</body>
</html>