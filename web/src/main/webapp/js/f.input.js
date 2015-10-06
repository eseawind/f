/**
 * 依赖bootstrap3 jquery1.11
 * 支持验证，格式化，
 */
(function($){
	function formatDate(date,dsep,tsep) {
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		var hours = date.getHours();
		var minutes = date.getMinutes();
		var seconds = date.getSeconds();
		var re = year + dsep;
		if(month<10){
			re = re + '0';
		}
		re = re + month + dsep;
		if(day < 10){
			re = re + '0';
		}
		re = re + day;
		if(tsep){
			re = re + ' ';
			if(hours < 10){
				re = re + '0';
			}
			re = re + hours + tsep;
			if(minutes < 10){
				re = re + '0';
			}
			re = re + minutes + tesp;
			if(seconds < 10){
				re = re + '0';
			}
			re = re + seconds;
		}
		return re;
	};
	function input_init(config){
		config.border = config.target.css('border');
		if(config.required){
			var hint = '必填';
			if(typeof config.hint == 'string'){
				hint = hint +'|'+ config.hint;
			}
			config.target.popover({
				placement: "auto right",
				trigger: "focus",
				content: hint
			});
		}else if(typeof config.hint == 'string'){
			config.target.popover({
				placement: "auto right",
				trigger: "focus",
				content: config.hint
			});
		}
		config.defValue = config.target.val();
		config.target.blur(function(){
			if(config.defValue == $(this).val()){
				$(this).attr('f-change', false);
			}else{
				$(this).attr('f-change', true);
			}
		});
	}
	function getValue(config){
		return config.target.val();
	}
	function setValue(config,value){
		config.defValue = value;
		config.target.val(value);
	}
	function errorTip(config){
		config.target.css('border','1px solid red');
		if(config.errMsg && typeof config.errMsg == 'string'){
			var popover = config.target.popover({
				placement: "auto top",
				trigger: "manual",
				content: config.message
			});
			popover.popover('show');
			config.target.one('focus',function(){
				config.target.css('border',config.border);
				popover.popover('destroy');
			});
		}else{
			config.target.one('focus',function(){
				config.target.css('border',config.border);
			});
		}
	}
	//input number
	(function(){
		function isValid(config){
			var re = true;
			var value = $.trim(config.target.val());
			if(value == ''){
				if(config.required) re = false;
			}else{
				if(isNaN(value)){
					re = false;
				}else{
					value = parseFloat(value);
					if(value < config.min||value>config.max){
						re = false;
					}else{
						int i = value.indexOf('.');
						if(config.precision > 0){
							var step = value.length - i - 1 - config.precision;
							if(step>0){
								value = value.substring(0,value.length-step);
							}else if(step<0){
								for(var j = -step;j>0;j--){
									value = value + '0';
								}
							}
						}else{
							if(i>0){
								value = value.substring(0,i);
							}
						}
						config.target.val(value);
					}
				}
			}
			if(!re){
				errorTip(config);
			}
			return re;
		}
		$.fn.f_input_number = function(config){
			if($.isPlainObject(config)){
				config = $.extend({
					required:false,
					errMsg:undefined,
					hint:undefined,
					precision:0,
					min:-Infinity,
					max:Infinity
				},config);
				config.target = this;
				input_init(config);
				config.target.blur(function(){
					isValid(config);
				});
				this.data('f-config',config);
				return this;
			}else{
				var fun = config;
				config = this.data('f-config');
				switch(fun){
				case 'isValid':return isValid(config);
				case 'getValue':return getValue(config);
				case 'setValue':return setValue(config,arguments[1]);
				default: return undefined;
				}
			}
		}
	})();
	//input date
	(function(){
		function validDateFormat(value,dsep,tsep){
			var arr = value.split(' ');
			if(tsep){
				if(arr.length != 2){
					return false;
				}
			}else{
				if(arr.length != 1){
					return false;
				}
			}
			var arr1 = arr[0].split(dsep);
			if(arr1.length != 3){
				return false;
			}
			if(tsep){
				var arr2 = arr[1].split(tsep);
			}
		}
		function isValid(config){
			var re = true;
			var value = $.trim(config.target.val());
			if(value == ''){
				if(config.required){
					re = false;
				}
			}else{
				re = validDateFormat(value,config.dsep,config.tsep);
			}
			if(!re){
				errorTip(config);
			}
			return re;
		}
		$.fn.f_input_date = function(config){
			if($.isPlainObject(config)){
				config = $.extend({
					required:false,
					errMsg:'日期格式不正确',
					hint:'',
					dsep:'-',
					tsep:':'
				},config);
				config.hint = '格式:'+formatDate(new Date(),config.dsep,config.tsep) + '\n\r' + config.hint; 
				config.target = this;
				input_init(config);
				config.target.blur(function(){
					isValid(config);
				});
				this.data('f-config',config);
				return this;
			}else{
				var fun = config;
				config = this.data('f-config');
				switch(fun){
				case 'isValid':return isValid(config);
				case 'getValue':return getValue(config);
				case 'setValue':return setValue(config,arguments[1]);
				default: return undefined;
				}
			}
		}
	})();
	//input combobox
	(function(){
		$.fn.f_input_combobox = function(config){
			if($.isPlainObject(config)){
				
				config.target = this;
				this.data('f-config',config);
			}else{
				var fun = config;
				config = this.data('f-config');
				switch(fun){
				case '':;
				default: return undefined;
				}
			}
		}
	})();
	//input text
	(function(){
		function isValid(config){
			var re = true;
			var value = $.trim(config.target.val());
			if(value == ''){
				if(config.required)re = false;
			}else{
				if(value.length > config.maxLength||value.length < config.minLength)re = false;
				if(typeof config.regex == 'string'&&!(new RegExp(config.regex)).test(value))re = false;
			}
			if(!re)errorTip(config);
		};
		$.fn.f_input_text = function(config){
			if($.isPlainObject(config)){
				config = $.extend({
					required:false,
					errMsg:undefined,
					hint:undefined,
					regex:undefined,
					maxLength: Infinity,
					minLength: 0
				},config);
				config.target = this;
				input_init(config);
				config.target.blur(function(){
					isValid(config);
				});
				this.data('f-config',config);
				return this;
			}else{
				var fun = config;
				config = this.data('f-config');
				switch(fun){
				case 'isValid':return isValid(config);
				case 'getValue':return getValue(config);
				case 'setValue':return setValue(config,arguments[1]);
				default: return undefined;
				}
			}
		}
	})();
	
	
})(jQuery);