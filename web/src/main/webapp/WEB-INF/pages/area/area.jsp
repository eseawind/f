<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${staUrl }/res/ztree/css/zTreeStyle/zTreeStyle.css">
<%@include file="../commons/hcommons.jsp" %>
<script type="text/javascript" src="${staUrl }/res/ztree/js/jquery.ztree.all-3.5.min.js"></script>
</head>
<body class="container" style="padding-bottom:50px">
<%@include file="../commons/hhead.jsp" %>
<ul id="tree" class="ztree"></ul>
<ul class="dropdown-menu" id="menu">
	<li><a href="javascript:add()">新增区域</a></li>
	<li><a href="javascript:upd()">修改区域</a></li>
</ul>
<div id="win" style="display:none">
	<input name="id" type="hidden"/>
	<div class="form">
		<div class="form-group">
			<label></label>
			<input class="form-control" name="name" f-type="text" f-options="required:true,maxLength:10"/>
		</div>
		<div class="form-group">
			<label></label>
			<input type="radio" name="isUse" value="1" checked="checked"/>是
			<input type="radio" name="isUse" value="0"/>否
		</div>
		<button id="submit" class="btn btn-block btn-primary">提交</button>
	</div>
</div>
<script type="text/javascript">
$(function(){
	var menu = $("#menu");
	var treeId;
	var curNode;
	var setting = {
		async:{
			enable:true,
			url:f.dynUrl+'/area/hlist.htm',
			dataType:'json',
			dataFilter:function(tid,parentNode,resData){
				var arr = [];
				if(resData.success){
					$.each(resData.result,function(i,d){
						var node = {};
						node.id = d.id;
						node.fid = d.fid;
						node.relName = d.name;
						node.name = d.name+":"+(d.isUse == 0?"隐藏":"启用");
						node.isUse = d.isUse;
						node.isParent = true;
						arr.push(node);
					});
				}
				return arr;
			},
			autoParam:['id=pid']
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
	$.fn.zTree.init($("#tree"),setting,[{id:1,name:"中国",isParent:true}]);
	$(document).click(function(){
		menu.hide();
	});
	var win = $("#win");
	win.f_modal({});
	$("#submit").click(function(){
		if(win.f_isValid()){
			var param = win.f_serialized();
			if(!param.id){
				param.fid = curNode.id;
			}
			win.startMask();
			$.post(f.dynUrl+"/area/addOrUpd.htm",param,function(d){
				win.closeMask();
				win.f_modal("hide");
				if(d.success){
					var treeObj = $.fn.zTree.getZTreeObj(treeId);
					treeObj.reAsyncChildNodes(param.id?curNode.getParentNode():curNode, "refresh");
				}else{
					f.alertError(d.errMsg);
				}
			},"json");
		}
	});
	window.add = function(){
		win.f_formClear();
		win.f_modal("setTitle","新增区域");
		win.f_modal("show");
	}
	window.upd = function(){
		win.f_formClear();
		win.f_formLoad({id:curNode.id,name:curNode.relName,isUse:curNode.isUse});
		win.f_modal("setTitle","维护区域");
		win.f_modal("show");
	}
})	
</script>	
</body>
</html>