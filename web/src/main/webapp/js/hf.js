
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
						window.location.href = window.f.staUrl + "/page/login/login.htm";
					}
				}
			}catch(e){
			}
			return data;
		},
		error:function(XmlHttpReq, textStatus, errorThrow){
			f.alertError(textStatus);
		}
	});
	
	Date.prototype.formatDate=function(fmt) {           
	    var o = {           
	    "M+" : this.getMonth()+1, //月份           
	    "d+" : this.getDate(), //日           
	    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
	    "H+" : this.getHours(), //小时           
	    "m+" : this.getMinutes(), //分           
	    "s+" : this.getSeconds(), //秒           
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度           
	    "S" : this.getMilliseconds() //毫秒           
	    };           
	    var week = {           
	    "0" : "/u65e5",           
	    "1" : "/u4e00",           
	    "2" : "/u4e8c",           
	    "3" : "/u4e09",           
	    "4" : "/u56db",           
	    "5" : "/u4e94",           
	    "6" : "/u516d"          
	    };           
	    if(/(y+)/.test(fmt)){           
	        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
	    }           
	    if(/(E+)/.test(fmt)){           
	        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);           
	    }           
	    for(var k in o){           
	        if(new RegExp("("+ k +")").test(fmt)){           
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
	        }           
	    }           
	    return fmt;           
	};
	
	$.fn.serializeObj = function(){
		  var param = {};
		  jQuery.each(this.serializeArray(),function(i,obj){
			  param[obj.name] = obj.value;
		  });
		  return param;
	  };
	
	//自定义
	var f = window.f = {};
	f.staUrl = "http://app.365020.com";
	f.imgUrl = function(){
		return f.staUrl;
	};
	f.dynUrl = f.staUrl;
	f.tmpl = {};
	f.tmpl.goodsContainer = '<div class="f-goods-container">' +
								'<div class="f-goods-block" f-id="${cgid}">' + 
									'<div class="f-goods-img">' + 
										'<img alt="" src="http://img11.360buyimg.com/n7/jfs/t1747/94/1121132190/123619/d307e19b/55e00172N89c73848.jpg" />' + 
									'</div>' + 
									'<ul>' + 
										'<li class="gprice"><font>￥</font>${price}</li>' + 
										'<li class="gname">${gname}</li>' + 
										'<li class="goper btn-group">' + 
											'<button class="btn btn-default f-share" f-id="${cgid}">分享</button>' + 
											'<button class="btn btn-default f-collect" f-id="${cgid}">收藏</button>' + 
											'<button class="btn btn-default f-addcart" f-id="${cgid}">加入购物车</button>' + 
										'</li>' + 
									'</ul>' + 
								'</div>' + 
							'</div>';
	f.tmpl.modal = '<div class="modal">' 
					  + '<div class="modal-dialog">'
					  + '  <div class="modal-content">'
					  + '    <div class="modal-body">'
					  + '      <div class="alert alert-warning" role="alert">${content}</div>'
					  + '    </div>'
					  + '    <div class="modal-footer">'
					  + '      <button type="button" class="btn btn-default" data-dismiss="modal" f-oper="close">取消</button>'
					  + '      <button type="button" class="btn btn-primary" f-oper="ok">确认</button>'
					  + '    </div>'
					  + '  </div>'
					  + ' </div>'
					  + '</div>';
	f.dialogAlert = function(message){
		var modal = $.tmpl(f.tmpl.modal,{content:message});
		modal.find("[f-oper=close]").click(function(){
			modal.modal("hide");
			modal.remove();
		});
		modal.find("[f-oper=ok]").hide();
		modal.modal("show");
	};
	f.dialogConfirm = function(message,fun){
		var modal = $.tmpl(f.tmpl.modal,{content:message});
		modal.find("[f-oper=close]").click(function(){
			modal.modal("hide");
			modal.remove();
			fun&&fun.call(null,false);
		});
		modal.find("[f-oper=ok]").click(function(){
			modal.modal("hide");
			modal.remove();
			fun&&fun.call(null,true);
		}).show();
		modal.modal("show");
	};
	f.backTopBtn = function(){
		var btn = $('<i class="icon-circle-arrow-up icon-3x" style="position:fixed;bottom:50px;right:10px"><i>').click(function(){
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
			$.tmpl(f.tmpl.goodsContainer,obj).appendTo(this);
		}
	};
	
	$.fn.showTip = function(message){
		var obj = this;
		obj.popover({
			placement: arguments[2] || "auto top",
			trigger: "manual",
			content: message
		});
		obj.popover("show");
		if(!arguments[1]){
			setTimeout(function(){
				obj.popover("destroy");
			},2000);
		}
	};
	$.fn.alertError = function(message){
		var alertError = $('<div class="alert alert-danger" role="alert">'+message+'</div>');
		$(this).append(alertError);
		setTimeout(function(){alertError.remove();},2000);
	};
})(jQuery);