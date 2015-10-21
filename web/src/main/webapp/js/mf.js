
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
											'<button class="btn btn-default f-addcart" f-id="${cgid}">加入购物车</button>' + 
										'</li>' + 
									'</ul>' + 
								'</div>' + 
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
			$.tmpl(f.tmpl.goodsContainer,obj).appendTo(this);
		}
	};
	
	//自定义页面加载完成事件
	$(function(){
		$(".f-label-fluid").each(function(){
			var obj = $(this);
			var length = obj.text().length;
			var width = obj.innerWidth();
			obj.width(width + width/2);
			obj.css("color",f.colors[(length%f.colors.length)]);
		});
	});
})(jQuery);