
/**
 * framework.js
 */
(function($){
	$.ajaxSetup({
		cache:false,
		dataFilter:function(data,type){
			try{
				var json = jQuery.parseJSON(data);
				if(!json.success){
					if(json.errCode == 7){
						window.location.href = window.f.staUrl + "/page/login/mlogin.htm";
					}
				}
			}catch(e){
			}
			return data;
		},
		error:function(XmlHttpReq, textStatus, errorThrow){
			f.dialogAlert(textStatus);
		}
	});
	//自定义
	var f = window.f = window.f||{};
	f.tmpl.goodsContainer = '<div class="f-goods-container">' +
								'<div class="f-goods-block" f-id="${cgid}">' + 
									'<div class="f-goods-img">' + 
										'<img alt="" src="${imgUrl}${photo}" />' + 
									'</div>' + 
									'<ul>' + 
										'<li class="gprice"><font style="font-size:80%">￥&nbsp;</font><span f-id="${cgid}"></span></li>' + 
										'<li class="gname">${gname}</li>' + 
										'<li class="goper btn-group">' + 
											'<button class="btn btn-default f-share" f-id="${cgid}">分享</button>' + 
											'<button class="btn btn-default f-collect" f-id="${cgid}">收藏</button>' + 
											'<button class="btn btn-default f-addcart" f-id="${cgid}" f-merchant="${merchantId}">加入购物车</button>' + 
										'</li>' + 
									'</ul>' + 
								'</div>' + 
							'</div>';
	f.tmpl.cart = '<div class="panel panel-default">'+
					    '<div class="panel-heading">商家：${merchantName}</div>'+
					    '<div class="panel-body">'+
							'<table class="table table-bordered">'+
								'<tr>'+
									'<th><input type="checkbox" /></th>'+
									'<th colspan="2">商品</th>'+
									'<th class="text-center">价格</th>'+
									'<th>删除</th>'+
								'</tr>{{each settleCarts}}'+
								'<tr style="">'+
									'<td style="vertical-align: middle;width:30px"><input type="checkbox" {{if $value.checked}} checked="checked" {{/if}}/></td>'+
									'<td style="vertical-align: middle;width:100px">{{each settleGoodsList}}<img src="${imgUrl}${$value.photo}" style="width:80px;height:80px"/>{{/each}}</td>'+
									'<td style="vertical-align: middle;">'+
										'<div style="min-height:50px">{{each settleGoodsList}}${$value.gname}-${$value.cgname}<br/>{{/each}}</div>'+
										'<div>'+
											'<button class="btn btn-default" style="float:left;height:30px;max-height:30px"><i class="icon-minus"></i></button>'+
											'<input class="form-control" value="${$value.number}" style="float:left;width:40px;height:30px;max-height:30px"/>'+
											'<button class="btn btn-default" style="float:left;height:30px;max-height:30px"><i class="icon-plus"></i></button>'+
										'</div>'+
									'</td>'+
									'<td style="vertical-align: middle;width:80px;text-align:center"><b>${$value.orderPrice}</b></td>'+
									'<td style="vertical-align: middle;width:45px"><i class="icon-trash icon-2x" style="cursor:pointer"></i></td>'+
								'</tr>{{/each}}'+
							'</table>'+
						'</div>'+
						'<div class="panel-footer">'+
							'<div class="text-right" style="color:red">总金额：￥${orderPrice}</div>'+
						'</div>'+
					'</div>';
	f.setTitle = function(title){
		$("#f_gloal_mhead_title").html(title);
	};
	f.backTopBtn = function(){
		var btn = $('<i class="icon-circle-arrow-up icon-3x" style="position:fixed;bottom:50px;right:10px;cursor:pointer"><i>').click(function(){
			$(window).scrollTop(0);
		}).appendTo("body").hide();
		var state = false;
		$(window).scroll(function(){
			var top = $(window).scrollTop();
			if(top > 200&&!state){
				state = true;
				btn.show();
			}else if(top < 200&&state){
				state = false;
				btn.hide();
			}
		});
	};
	f.addCart = function(merchantId,cgids,num,fun,errFun){
		console.log(arguments)
		$.post(f.dynUrl+"/cart/add.htm",{merchantId:merchantId,cgids:cgids,number:num},function(d){
			if(d.success){
				fun&&fun.call(null,d.result);
			}else{
				if(errFun){
					errFun.call(null,d.errCode,d.errMsg);
				}else{
					f.dialogAlert(d.errMsg);
				}
			}
		},"json");
	}
	
	f.updCart = function(cartStr,num,fun,errFun){
		$.post(f.dynUrl+"/cart/upd.htm",{cartStr:cartStr,number:num},function(d){
			if(d.success){
				fun&&fun.call(null,d.result);
			}else{
				if(errFun){
					errFun.call(null,d.errCode,d.errMsg);
				}else{
					f.dialogAlert(d.errMsg);
				}
			}
		},"json");
	}
	
	f.sizeCart = function(fun,errFun){
		$.getJSON(f.dynUrl+"/cart/size.htm",function(d){
			if(d.success){
				fun&&fun.call(null,d.result);
			}else{
				if(errFun){
					errFun.call(null,d.errCode,d.errMsg);
				}else{
					f.dialogAlert(d.errMsg);
				}
			}
		});
	}
	
	f.delCart = function(cartStr,fun,errFun){
		$.post(f.dynUrl+"/cart/upd.htm",{cartStr:cartStr},function(d){
			if(d.success){
				fun&&fun.call(null,d.result);
			}else{
				if(errFun){
					errFun.call(null,d.errCode,d.errMsg);
				}else{
					f.dialogAlert(d.errMsg);
				}
			}
		},"json");
	}
	
	f.cartBuilder = function(obj){
		obj.imgUrl = f.imgUrl();
		var cart = $.tmpl(f.tmpl.cart,obj);
		cart.find(".icon-minus").click(function(){
			container.startMask();
			//f.updCart(obj., num, fun, errFun)
		});
		cart.find(".icon-plus").click(function(){
			container.startMask();
			f.post(f.dynUrl,{},function(d){
				container.closeMask();
				if(d.success){
					
				}else{
					f.dialogAlert(d.errMsg);
				}
			},'json');
		})
		var input = cart.find("input").blur(function(){
			var num = parseInt(input.val());
			if(num > 99){
				input.val(99);
			}else if(num < 1){
				input.val(1);
			}
			container.startMask();
			f.post(f.dynUrl,{},function(d){
				container.closeMask();
				if(d.success){
					
				}else{
					f.dialogAlert(d.errMsg);
				}
			},'json');
		}).keypress(function(e){
			if(e.which == 8)return;
			if(e.which<48||e.which>57){
				e.preventDefault();
			}
		});
		return cart;
	}
	
	$.fn.carouselBuilder = function(arr){
		if($.isArray(arr) && arr.length > 0){
			var container = this;
			container.addClass("carousel");
			container.addClass("slide");
			var imgContainer = $('<div class="carousel-inner">');
			var liContainer = $('<ul class="f-carousel-btn">');
			$.each(arr,function(i,obj){
				if(obj instanceof $){
					if(i == 0){
						imgContainer.append($('<div class="active item" f-id="'+i+'">').append(obj));
					}else{
						imgContainer.append($('<div class="item" f-id="'+i+'">').append(obj));
					}
					liContainer.append($('<li f-id="'+i+'">●</li>').click(function(){
						container.find(".item").removeClass("active");
						container.find(".item[f-id="+$(this).attr("f-id")+"]").addClass("active");
					}));
				}
			});
			container.append(imgContainer);
			container.append(liContainer);
		}
	};
	
	$.fn.goodsContainerBuilder = function(obj){
		if($.isPlainObject(obj)){
			obj.imgUrl = f.imgUrl
			var goodsCon = $.tmpl(f.tmpl.goodsContainer,obj);
			goodsCon.appendTo(this);
			goodsCon.find(".f-addcart").click(function(){
				var th = $(this);
				var cgids = $.trim(th.attr("f-id"));
				var merchantId = $.trim(th.attr("f-merchant"));
				f.addCart(merchantId,cgids,1,function(){
					f.transientAlert("成功加入购物车");
				});
				
			});
		}
	};
	
	//自定义页面加载完成事件
	$(function(){
		
		
	});
})(jQuery);