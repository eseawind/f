
/**
 * framework.js
 */
(function($){
	
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
	var f = window.f = window.f||{};
	f.staUrl = "http://app.365020.com";
	f.imgUrl = function(){
		return f.staUrl;
	};
	f.dynUrl = f.staUrl;
	f.tmpl = {};
	f.tmpl.modal =    '<div class="modal">' 
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
	f.tmpl.modalWin = '<div class="modal fade">' 
					  + '<div class="modal-dialog">'
					  + '  <div class="modal-content">'
					  + '	 <div class="modal-header">'
					  + '      	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
					  + '      	<h4 class="modal-title" f-div="title"></h4>'
					  + '    </div>'
					  + '    <div class="modal-body" f-div="body">'
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
	}
	f.transientAlert = function(message,timeout){
		var alert = $('<div class="text-center" style="z-index:999999999;position:fixed;bottom:50px;left:0;width:100%"><button class="btn btn-default">'+message+'</button></div>')
		$(document.body).append(alert);
		setTimeout(function(){alert.remove()},timeout||1000);
	}
	f.length = function(o){
		if($.isArray(o)){
			return o.length;
		}else if($.isPlainObject(o)){
			var n = 0;
			for(var k in o){
				n++;
			}
			return n;
		}
	}
	
	f.filterEmpty = function(o){
		var arr = [];
		if($.isPlainObject(o)){
			for(var k in o){
				if($.trim(o[k]) == ''){
					arr.push(k);
				}
			}
		}
		$.each(arr,function(i,k){
			delete o[k];
		});
		return o;
	}
	
	f.parseUrlParam = function(url){
		var param = {};
		var arr = url.split("?");
		if(arr.length == 1){
			return param;
		}
		arr = arr[1];
		arr = arr.split("&");
		$.each(arr,function(i,kv){
			kv = kv.split("=");
			if(kv.length == 2){
				param[kv[0]] = kv[1];
			}
		});
		return param;
	}
	
	$.fn.f_showTip = function(message){
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
	
	$.fn.f_alertError = function(message,timeout){
		var alertError = $('<div class="alert alert-danger" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+message+'</div>');
		timeout&&$(this).empty();
		$(this).append(alertError);
		if(timeout){
			setTimeout(function(){alertError.remove();},timeout);
		}
	};
	
	$.fn.f_modal = function(config){
		if($.isPlainObject(config)){
			if(this.data('f-modal')){
				return;
			}
			var modal = $(f.tmpl.modalWin);
			this.parent().append(modal);
			modal.modal({show:false});
			config.width&&modal.find('.modal-dialog').eq(0).width(config.width);
			config.title&&modal.find('[f-div="title"]').eq(0).text(config.title);
			modal.find('.modal-body').eq(0).append(this);
			modal.data('f-config',config);
			this.data('f-modal',modal);
			this.show();
		}else{
			var modal = this.data('f-modal');
			if(modal instanceof jQuery){
				var fun = config;
				config = modal.data('f-config');
				switch(fun){
				case 'setTitle':modal.find('[f-div="title"]').eq(0).text(arguments[1]);break;
				case 'show':modal.modal('show');break;
				case 'hide':modal.modal('hide');break;
				}
			}
		}
		
	};
})(jQuery);