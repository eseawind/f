
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
	}
	
	$.fn.serializeObj = function(){
		  var param = {};
		  jQuery.each(this.serializeArray(),function(i,obj){
			  param[obj.name] = obj.value;
		  });
		  return param;
	  }
	
	//自定义
	var f = window.f = {};
	f.staUrl = "http://app.365020.com";
	f.imgUrl = function(){
		return f.staUrl;
	}
	f.dynUrl = f.staUrl;
	f.goodsContainerTmpl = '<div class="f-goods-container">' +
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
	f.setTitle = function(title){
		$("#f_gloal_mhead_title").html(title);
	}
	$.formUtils.LANG = {
	      errorTitle: '表单提交失败!',
	      requiredFields: '你没有回答所必须的字段',
	      badTime: '你没有提供一个正确的时间',
	      badEmail: '你没有提供一个正确的邮件地址',
	      badTelephone: '你没有提供',
	      badSecurityAnswer: '你没有提供一个正确的答案',
	      badDate: '你没有提供一个正确的日期',
	      lengthBadStart: '输入值范围必须是',
	      lengthBadEnd: ' 字符',
	      lengthTooLongStart: '输入值超过 ',
	      lengthTooShortStart: '输入值小于 ',
	      notConfirmed: '输入值不正确',
	      badDomain: '不正确的域名',
	      badUrl: '输入值不是一个正确的url',
	      badCustomVal: '输入值是不正确的',
	      andSpaces: ' 和空格 ',
	      badInt: '输入值不是一个正确的号码',
	      badSecurityNumber: '身份号码不正确',
	      badUKVatAnswer: '',
	      badStrength: '',
	      badNumberOfSelectedOptionsStart: '你必须至少选择 ',
	      badNumberOfSelectedOptionsEnd: ' 回答',
	      badAlphaNumeric: '输入值只能包含字符数字字符 ',
	      badAlphaNumericExtra: ' 和 ',
	      wrongFileSize: '上传文件太大超过 (max %s)',
	      wrongFileType: '只允许的文件格式  %s',
	      groupCheckedRangeStart: '请选择 ',
	      groupCheckedTooFewStart: '请选择至少 ',
	      groupCheckedTooManyStart: '请选择一个最大的 ',
	      groupCheckedEnd: ' item(s)',
	      badCreditCard: '信用卡号码不正确',
	      badCVV: '',
	      wrongFileDim : '不正确的图片尺寸,',
	      imageTooTall : '图像不能比',
	      imageTooWide : '图像不能超出',
	      imageTooSmall : '图片太小',
	      min : '最小',
	      max : '最大',
	      imageRatioNotAccepted : '图像比不能被接受',
	      badBrazilTelephoneAnswer: '输入的电话号码不正确',
	      badBrazilCEPAnswer: '',
	      badBrazilCPFAnswer: ''
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
	}
	
	$.fn.goodsContainerBuilder = function(obj){
		if($.isPlainObject(obj)){
			$.tmpl(f.goodsContainerTmpl,obj).appendTo(this);
		}
	}
	
	//自定义页面加载完成事件
	$(function(){
		$.fn.alertError = function(message){
			var alertError = $('<div class="alert alert-danger" role="alert">'+message+'</div>');
			$(this).append(alertError);
			setTimeout(function(){alertError.remove()},2000);
		}
	})
})(jQuery);