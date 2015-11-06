<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../commons/mcommons.jsp" %>
</head>
<body class="container">
<%@include file="../commons/mhead.jsp" %>
<div id="container">
</div>
<button class="btn btn-block btn-default" id="mostBtn" style="display:none">加载更多数据</button>
<script type="text/javascript">
$(function(){
	f.setTitle("我的收藏");
	var mostBtn = $("#mostBtn");
	var container = $("#container");
	var page = 1;
	var rows = 20;
	mostBtn.click(function(){
		loadData();
	});
	function loadData(){
		$.getJSON(f.dynUrl+"/goods/collect/list.htm",{page:page++,rows:rows},function(d){
			if(d.success){
				if(d.result.entry.length > 0){
					var cgids = [];
					$.each(d.result.entry,function(i,obj){
						cgids.push(obj.cgid);
						container.simpleGoodsContainerBuilder(obj);
					});
					$.getJSON(f.dynUrl+"/goods/dyn/cgids.htm",{cgids:cgids.join(",")},function(d){
						if(d.success){
							$.each(d.result,function(i,d){
								container.find('.gprice span[f-id="'+d.cgid+'"]').eq(0).text(d.price);
							});
						}
					});
				}else if(d.result.entry.length == 0&&page - 1 == 1){
					container.append('<div class="text-center">还没有收藏的商品&nbsp;&nbsp;<a class="btn btn-success" href="'+f.staUrl+"/page/index/mindex.htm"+'">去购物</a></div>');
				}
				if((page-1)*rows >= d.result.total){
					mostBtn.hide();
				}else{
					mostBtn.show();
				}
			}else{
				f.dialogAlert(d.errMsg);
			}
		});
	};
	loadData();
})
</script>
</body>
</html>