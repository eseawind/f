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
<script type="text/javascript">
$(function(){
	var setting = {
		async:{
			url:'',
			dataType:'json',
			dataFilter:function(treeId,parentNode,resData){
				if(resData.success){
					
				}
			},
			autoParam:['id=fid']
		},
		callback:{
			beforeExpand:function(treeId,treeNode){
				
			}
		}
	};
	$.fn.zTree.init($("#category"), setting, [{id:0,name:"分类",isParent:true}]);
});
</script>
</body>
</html>