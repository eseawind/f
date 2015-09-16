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
	f.staUrl = "http://localhost";
	f.imgUrl = function(){
		return f.staUrl;
	}
	f.dynUrl = f.staUrl;
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