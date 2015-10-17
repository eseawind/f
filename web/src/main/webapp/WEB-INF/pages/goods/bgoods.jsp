<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/bcommons.jsp" %>
</head>
<body>
<div class="container">
	<%@ include file="../commons/bhead.jsp" %>
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="form-inline" id="queryDiv">
				<div class="form-group">
					<label>商品ID：</label>
					<input class="form-control" f-type="number" f-options="min:0"/>
				</div>
				<div class="form-group">
					<label>商品名称</label>
					<input class="form-control" name="gname" f-type="text" f-options=""/>
				</div>
				<div class="form-group">
					<label>sku</label>
					<input class="form-control" name="sku" />
				</div>
				<div class="form-group">
					<label>品牌</label>
					<select class="form-control" name="brandId" style="width:150px"></select>
				</div>
				<button class="btn btn-primary" id="queryBtn">查询</button>
			</div>
		</div>
		<div class="panel-body">
			<div id="dg"></div>
 		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	var dg = $("#dg").f_dg({
		lazy:true,
		method:"post",
		url:f.dynUrl+"/goods/blist.htm",
		pagination:true,
		filter:function(data){
			if(data.success){
				return {rows:data.result.entry,count:data.result.total};
			}
			return {rows:[],count:0}
		},
		columns:[{
			field:"id",title:"商品ID"
		},{
			field:"gname",title:"商品名称"
		},{
			field:"categoryName",title:"品类"
		},{
			field:"cgname",title:"规格"
		},{
			field:"sku",title:"sku"
		},{
			field:"price",title:"价格"
		},{
			field:"mprice",title:"市场价格"
		},{
			field:"number",title:"库存"
		},{
			field:"remark",title:"描述",showTip:true
		},{
			field:"brandName",title:"品牌"
		},{
			field:"state",title:"状态",formatter:function(v){
				switch(parseInt(v)){
				case 1:return "上架";
				case 2:return "下架";
				case 127:return "不显示";
				}
				return ""
			}
		},{
			field:"createtime",title:"创建时间"
		}],
		before:function(param){
			$.extend(param,$("#queryDiv").f_serialized());
		}
	});
	$("#queryBtn").click(function(){
		dg.f_dg("load");
	})
})
</script>
</body>
</html>