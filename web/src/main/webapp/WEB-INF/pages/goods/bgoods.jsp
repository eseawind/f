<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${staUrl }/res/kindeditor/themes/default/default.css">
<%@ include file="../commons/bcommons.jsp" %>
<script type="text/javascript" src="${staUrl }/res/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${staUrl }/res/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${staUrl }/res/ajaxupload/jquery.ajaxupload.js"></script>
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
					<select class="form-control" name="brandId" style="width:150px" f-type="combobox" f-options="url:'${dynUrl }/brand/combobox.htm',filter:function(d){return d.result}"></select>
				</div>
				<button class="btn btn-primary" id="queryBtn">查询</button>
			</div>
		</div>
		<div class="panel-body">
			<div id="dg"></div>
 		</div>
	</div>
</div>
<div id="goodsDiv" style="display:none">
	<input name="id" type="hidden"/><!-- 商品id -->
	<div class="panel panel-default">
		<div class="panel-heading">
			商品信息（必填）
		</div>
		<div class="panel-body">
			<table class="table" style="border-width:0">
				<tr>
					<td style="width:33%">
						<label>一级品类:</label>
						<select class="form-control" id="category1" f-type="combobox" f-options="url:'${dynUrl }/category/combobox.htm?fid=0',errMsg:'必填',errDir:'bottom',required:true,
						filter:function(d){
							if(d.success){
								return d.result;
							}else{
								return [];
							}
						}
						,select:function(d){
							if(d.id){
								$.getJSON(f.dynUrl+'/category/combobox.htm?fid='+d.id,function(d){
									if(d.success){
										$('#category2').f_combobox('loadData',d.result);
									}
								});
							}else{
								$('#category2').f_combobox('loadData',[]);
								$('#category3').f_combobox('loadData',[]);
							}
						}"></select>
					</td>
					<td>
						<label>二级品类:</label>
						<select class="form-control" id="category2" f-type="combobox" f-options="select:function(d){
							if(d.id){
								$.getJSON(f.dynUrl+'/category/combobox.htm?fid='+d.id,function(d){
									if(d.success){
										$('#category3').f_combobox('loadData',d.result);
									}
								});
							}else{
								$('#category3').f_combobox('loadData',[]);
							}
						}"></select>
					</td>
					<td style="width:33%">
						<label>三级品类:</label>
						<select class="form-control" id="category3" f-type="combobox" f-options=""></select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label>商品名称：</label>
						<input class="form-control" name="gname" f-type="text" f-options="required:true,maxLength:254"/>
					</td>
					<td>
						<label>品牌:</label>
						<select class="form-control" name="brandId" f-type="combobox" f-options="required:false,url:'${dynUrl }/brand/combobox.htm',filter:function(d){return d.result}"></select>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<label>描述:</label>
						<input class="form-control" name="remark" f-type="text" f-options="required:false,maxLength:254"/>
					</td>
				</tr>
			</table>
			<div class="panel panel-default">
				<div class="panel-heading">
					商品详情（必填）
				</div>
				<div class="panel-body">
					<textarea id="descript" style="width:100%"></textarea>
				</div>
			</div>
			<button id="goodsBtn" class="btn btn-primary btn-block">保存</button>
		</div>	
	</div>
</div>
<div id="cgDiv" style="display:none">
	<input name="id" type="hidden"/>
	<input name="gid" type="hidden"/>
	<div class="panel panel-default">
	  <div class="panel-heading">
	  	规格（必填）
	  </div>
	  <div class="panel-body">
	  	<table class="table table-bordered">
	  		<tr>
	  			<td>
		  			<label>sku：</label>
		  			<input class="form-control" name="sku" f-type="text" f-options="maxLength:32"/>
	  			</td>
	  			<td>
	  				<label>规格：</label>
	  				<input class="form-control" name="cgname" f-type="text" f-options="required:true,maxLength:254"/>
	  			</td>
	  			<td>
	  				<label>销售价格：</label>
	  				<input class="form-control" name="price" f-type="number" f-options="required:true,precision:2,min:0" value="0.00"/>
	  			</td>
	  			<td>
	  				<label>市场价格：</label>
	  				<input class="form-control" name="mprice" f-type="number" f-options="required:true,precision:2,min:0" value="0.00"/>
	  			</td>
	  			<td>
	  				<label>上线状态：</label>
	  				<select class="form-control" name="state" f-type="combobox" f-options="required:true,datas:[{k:'不可见',v:127},{k:'上架',v:1},{k:'下架',v:2}]">
	  				</select>
	  			</td>
	  			<td>
	  				<label>默认规格：</label>
	  				<select style="width:80px" class="form-control" name="isDef" f-type="combobox" f-options="required:true,datas:[{k:'否',v:0},{k:'是',v:1}]">
	  				</select>
	  			</td>
	  			<td>
	  				<label>库存：</label>
	  				<input class="form-control" name="number" f-type="number" f-options="required:true,precision:0,min:0" value="0"/>
	  			</td>
	  		</tr>
	  	</table>
	    <table class="table table-bordered text-center">
			<tr>
				<td>
					<div>
						<img style="width:200px;height:200px" class="img-thumbnail imgupload"/>
						<input class="form-control" name="photo"/>
						<button class="btn btn-default btn-block del_imgupload">删除</button>
					</div>
				</td>
				<td>
					<div>
						<img style="width:200px;height:200px" class="img-thumbnail imgupload"/>
						<input class="form-control" name="photo1"/>
						<button class="btn btn-default btn-block del_imgupload">删除</button>
					</div>
				</td>
				<td>
					<div>
						<img style="width:200px;height:200px" class="img-thumbnail imgupload"/>
						<input class="form-control" name="photo2"/>
						<button class="btn btn-default btn-block del_imgupload">删除</button>
					</div>
				</td>
				<td>
					<div>
						<img style="width:200px;height:200px" class="img-thumbnail imgupload"/>
						<input class="form-control" name="photo3"/>
						<button class="btn btn-default btn-block del_imgupload">删除</button>
					</div>
				</td>
				<td>
					<div>
						<img style="width:200px;height:200px" class="img-thumbnail imgupload"/>
						<input class="form-control" name="photo4"/>
						<button class="btn btn-default btn-block del_imgupload">删除</button>
					</div>
				</td>
			</tr>
		</table>
		<button class="btn btn-primary btn-block" id="cgBtn">保存</button>
	  </div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	var goodsDiv = $("#goodsDiv");
	goodsDiv.f_modal({title:"商品信息维护"});
	var cgDiv = $("#cgDiv");
	cgDiv.f_modal({title:"商品规格信息维护",width:1200});
	$("#goodsBtn").click(function(){
		if(goodsDiv.f_isValid()){
			var param = goodsDiv.f_serialized();
			param.descript = editor.html();
			param.code = $("#category3").f_combobox("getValue")||$("#category2").f_combobox("getValue")||$("#category1").f_combobox("getValue");
			goodsDiv.startMask();
			$.post(f.dynUrl+'/goods/updGoods.htm',param,function(d){
				goodsDiv.closeMask();
				if(d.success){
					goodsDiv.f_modal('hide');
					dg.f_dg("reload");
				}else{
					f.alertError(d.errMsg);
				}
			},'json');
		}
	});
	$("#cgBtn").click(function(){
		if(cgDiv.f_isValid()){
			var param = cgDiv.f_serialized();
			if(f.length(param) > 0){
				cgDiv.startMask();
				$.post(f.dynUrl+'/goods/updCGandGS.htm',f.filterEmpty(param),function(d){
					cgDiv.closeMask();
					if(d.success){
						cgDiv.f_modal('hide');
						dg.f_dg("reload");
					}else{
						f.alertError(d.errMsg);
					}
				},'json');
			}
		}
	});
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
			field:"isDef",title:"是否默认规格",formatter(v){
				switch(parseInt(v)){
				case 1:return "是";
				case 0:return "否";
				}
			}
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
		},
		rightclick:[{text:"维护商品规格信息",handler:function(i,data){
			var map = {};
			map.id = data.id;
			map.gid = data.gid;
			map.cgname = data.cgname;
			map.sku = data.sku;
			map.price = data.price;
			map.mprice = data.mprice;
			map.state = data.state;
			map.number = data.number;
			map.isDef = data.isDef;
			cgDiv.f_formReset();
			cgDiv.f_formLoad(map);
			cgDiv.f_modal("show");
		}},{text:"维护商品信息",handler:function(i,data){
			var map = {};
			map.id = data.gid;
			map.gname = data.gname;
			map.brandId = data.brandId;
			map.remark = data.remark;
			editor.html(data.descript);
			categoryBuilder(data.code);
			goodsDiv.f_formReset();
			goodsDiv.f_formLoad(map);
			goodsDiv.f_modal("show");
		}}]
	});
	$("#queryBtn").click(function(){
		dg.f_dg("load");
	})
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('#descript', {
			allowFileManager : true,
			autoHeightMode : true,
			afterCreate : function() {
				this.loadPlugin('autoheight');
			},
			items:[
			        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
			        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
			        'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
			        'anchor', 'link', 'unlink'
			],
			uploadJson:'',
			afterUpload : function(url,data,name) {
				
	    	},
	        afterChange : function() {
	            
	        }
		});
	});
	function imgupload(obj){
		obj.ajaxUploadPrompt({
			url : '',
			beforeSend : function () {
				obj.parent().startMask();
			},
			onprogress : function (e) {
				
			},
			error : function (e) {
				f.dialogAlert(e);
			},
			success : function (data) {
				obj.parent().closeMask();
				
			}
		});
	}
	function del_imgupload(obj){
		
	}
	$("img.imgupload").each(function(){
		imgupload($(this));
	});
	$('.del_imgupload').each(function(){
		$(this).click(function(e){
			del_imgupload($(this));
			e.preventDefault();
			e.stopPropagation();
		});
	});
	function categoryBuilder(code){
		var c1 = code.substring(0,3);
		$("#category1").f_combobox("setValue",c1);
		if(code.length >= 6){
			var c2 = code.substring(0,6);
			var id = $("#category1").f_combobox('getSelectedData').id;
			$.getJSON(f.dynUrl+'/category/combobox.htm?fid='+id,function(d){
				if(d.success){
					$('#category2').f_combobox('loadData',d.result);
					$('#category2').f_combobox('setValue',c2);
					if(code.length >= 9){
						var id = $("#category2").f_combobox('getSelectedData').id;
						$.getJSON(f.dynUrl+'/category/combobox.htm?fid='+id,function(d){
							if(d.success){
								$('#category3').f_combobox('loadData',d.result);
								$('#category3').f_combobox('setValue',code);
							}
						});
					}else{
						$('#category3').f_combobox('loadData',[]);
					}
				}
			});
		}else{
			$('#category2').f_combobox('loadData',[]);
			$('#category3').f_combobox('loadData',[]);
		}
	}
})
</script>
</body>
</html>