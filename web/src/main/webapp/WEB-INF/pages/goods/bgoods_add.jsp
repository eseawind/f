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
<div class="container" id="body">
<%@ include file="../commons/bhead.jsp" %>
<div style="padding-bottom:100px;">
	<div id="goodsDiv">
		<input name="id" type="hidden" id="gId"/>
		<div class="panel panel-default">
			<div class="panel-heading">
				商品信息（必填）
			</div>
			<div class="panel-body">
				<div class="form">
					<div class="form-group">
						<label>商品名称：</label>
						<input class="form-control" name="gname" f-type="text" f-options="required:true,maxLength:254"/>
					</div>
				</div>
				<table class="table">
					<tr>
						<td style="width:20%">
							<label>sku:</label>
							<input class="form-control" name="sku" f-type="text" f-options="maxLength:32"/>
						</td>
						<td>
							<label>描述:</label>
							<input class="form-control" name="remark" f-type="text" f-options="required:true,maxLength:254"/>
						</td>
						<td style="width:15%">
							<label>品牌:</label>
							<select class="form-control" name="brandId" f-type="combobox" f-options="required:false"></select>
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
				<button id="goodsBtn" class="btn btn-primary btn-block" style="display:none">保存</button>
			</div>	
		</div>
	</div>
	<div id="gcDiv">
		<input name="id" type="hidden" id="gcId"/>
		<div class="panel panel-default">
			<div class="panel-heading">
				品类（必填）
			</div>
			<div class="panel-body">
				<table class="table">
					<tr>
						<td>
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
						<td>
							<label>三级品类:</label>
							<select class="form-control" id="category3" f-type="combobox" f-options=""></select>
						</td>
					</tr>
				</table>
				<button id="gcBtn" class="btn btn-primary btn-block" style="display:none">保存</button>
			</div>
		</div>
	</div>
	<div id="cgDiv">
		<input name="id" type="hidden" id="gsId"/>
		<input name="cgid" type="hidden" id="cgId"/>
		<div class="panel panel-default">
		  <div class="panel-heading">
		  	规格（必填）
		  </div>
		  <div class="panel-body">
		  	<table class="table table-bordered">
		  		<tr>
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
			<button class="btn btn-primary btn-block" id="cgBtn" style="display:none">保存</button>
		  </div>
		</div>
	</div>
	<button class="btn btn-primary btn-block" id="saveGoodsBtn">保存</button>
	<hr/>
	<div id="add_standard">
		<script type="text/tmpl" id="standard_tmpl">
			<div class="panel panel-default">
			  <div>
		        <span id="standard_del_{{= index}}" style="cursor:pointer;padding:5px"><i class="icon-remove-sign"></i>&nbsp;取消<span>
	          </div>
			  <div class="panel-heading">
			  	规格（必填）
			  </div>
			  <form id="standard_form_{{= index}}">
			  <input type="hidden" name="id" value=""/>
              <input type="hidden" name="cgid" value=""/>
			  <div class="panel-body">
			  	<table class="table table-bordered">
			  		<tr>
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
			  </div>
              <button class="btn btn-primary btn-block" id="standard_submit_{{= index }}">保存</button>
			  </form>
			</div>
		</script>
	</div>
	<div>
		<button class="btn btn-default" id="add_standard_btn"><i class="icon-plus-sign"></i>添加新规格</button>
	</div>
</div>
</div>
<script type="text/javascript">
$(function(){
	var gid = undefined;
	var formIndex = 0;
	var goodsDiv = $("#goodsDiv");
	var gcDiv = $("#gcDiv");
	var cgDiv = $("#cgDiv");
	$("#saveGoodsBtn").click(function(){
		if(goodsDiv.f_isValid()&&gcDiv.f_isValid()&&cgDiv.f_isValid()){
			var param = {};
			$.extend(param,goodsDiv.f_serialized());
			$.extend(param,gcDiv.f_serialized());
			$.extend(param,cgDiv.f_serialized());
			param.code = $("#category3").f_combobox("getValue")||$("#category2").f_combobox("getValue")||$("#category1").f_combobox("getValue");
			$("#body").startMask();
			$.post(f.dynUrl+'/goods/add.htm',param,function(d){
				$("#body").closeMask();
				if(d.success){
					gid = d.result[0];
					$("#gId").val(d.result[0]);
					$("#gcId").val(d.result[1]);
					$("#cgId").val(d.result[2]);
					$("#gsId").val(d.result[3]);
					$("#saveGoodsBtn").hide();
					$("#goodsBtn").show();
					$("#gcBtn").show();
					$("#cgBtn").show();
				}else{
					f.alertError(d.errMsg);
				}
			},'json');
		}
	});
	$("#goodsBtn").click(function(){
		if(goodsDiv.f_isValid()){
			var param = goodsDiv.f_serialized();
			goodsDiv.startMask();
			$.post(f.dynUrl+"/goods/updGoods.htm",param,function(d){
				goodsDiv.closeMask();
				if(!d.success){
					f.alertError(d.errMsg);
				}
			},'json');
		}
	});
	$("#gcBtn").click(function(){
		if(gcDiv.f_isValid()){
			var param = gcDiv.f_serialized();
			param.code = $("#category3").f_combobox("getValue")||$("#category2").f_combobox("getValue")||$("#category1").f_combobox("getValue");
			gcDiv.startMask();
			$.post(f.dynUrl+"/goods/updGC.htm",param,function(d){
				gcDiv.closeMask();
				if(!d.success){
					f.alertError(d.errMsg);
				}
			},'json');
		}	
	});
	$("#cgBtn").click(function(){
		if(cgDiv.f_isValid()){
			var param = cgDiv.f_serialized();
			cgDiv.startMask();
			$.post(f.dynUrl+"/goods/updCGandGS.htm",param,function(d){
				cgDiv.closeMask();
				if(!d.success){
					f.alertError(d.errMsg);
				}
			},'json');
		}
	});
	$("#add_standard_btn").click(function(){
		if(!gid){
			f.dialogAlert("保存成功商品信息后才能添加新规格");
			return;
		}
		formIndex++;
		$("#add_standard").append($("#standard_tmpl").tmpl({gid:gid,index:formIndex})).f_create();
		$("#standard_submit_"+formIndex).click(function(e){
			var form = $("#standard_form_"+formIndex);
			if(form.f_isValid()){
				var param = form.f_serialized();
				console.log(param);
				if(!param.id){
					param.gid = gid;
					form.startMask();
					$.post(f.dynUrl+"/goods/addCG.htm",param,function(d){
						form.closeMask();
						if(d.success){
							$("#standard_del_"+formIndex).hide();
							form.find('input[name="id"]').val(d.result[1]);
							form.find('input[name="cgid"]').val(d.result[0]);
						}else{
							f.alertError(d.errMsg);
						}
					},'json');
				}else{
					form.startMask();
					$.post(f.dynUrl+"/goods/updCGandGS.htm",param,function(d){
						form.closeMask();
						if(!d.success){
							f.alertError(d.errMsg);
						}
					},'json');
				}
			}
			e.preventDefault();
			e.stopPropagation();
		});
		$("#standard_del_"+formIndex).click(function(){
			$(this).parent().parent().remove();
		});
		$("#standard_form_"+formIndex).find('img.imgupload').each(function(){
			imgupload($(this));
		});
		$("#standard_form_"+formIndex).find('.del_imgupload').each(function(){
			$(this).click(function(e){
				del_imgupload($(this));
				e.preventDefault();
				e.stopPropagation();
			});
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
});
</script>
</body>
</html>