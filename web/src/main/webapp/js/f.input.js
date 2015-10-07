/**
 * 依赖bootstrap3 jquery1.11
 * 支持验证，格式化，
 */
(function($){
	function configBuilder(type){
		return eval('({'+type+'})');
	}
	function input_init_hint(config){
		if(config.required){
			var hint = '必填';
			if(typeof config.hint == 'string'){
				hint = hint +'|'+ config.hint;
			}
			config.target.attr('placeholder',hint);
		}else if(typeof config.hint == 'string'){
			config.target.attr('placeholder',config.hint);
		}
		if(config.errMsg && typeof config.errMsg == 'string'){
			config.target.popover({
				placement: "auto right",
				trigger: "manual",
				content: config.errMsg
			});
		}
	}
	function input_init(config){
		config.border = config.target.css('border');
		input_init_hint(config);
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
			config.target.popover('show');
			config.target.one('focus',function(){
				config.target.css('border',config.border);
				config.target.popover('hide');
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
						value = String(value);
						var i = value.indexOf('.');
						if(config.precision > 0){
							if(i > 0){
								var step = value.length - i - 1 - config.precision;
								if(step>0){
									value = value.substring(0,value.length-step);
								}else if(step<0){
									for(var j = -step;j>0;j--){
										value = value + '0';
									}
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
			if(this.get(0).tagName != 'INPUT'){
				return undefined;
			}
			if($.isPlainObject(config)){
				config = $.extend({
					required:false,
					errMsg:undefined,
					hint:undefined,
					precision:0,
					min:-Infinity,
					max:Infinity,
					blur:function(){}
				},config);
				config.target = this;
				input_init(config);
				config.target.blur(function(){
					config.blur.call(config.target);
				});
				config.target.keypress(function(e){
					if(e.which == 8) return;
					if(e.which<45||e.which>57){
						e.preventDefault();
					}
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
		$(function(){
			$('input[f-type="number"]').each(function(){
				var input = $(this);
				var options = input.attr('f-options');
				var config = {};
				if(options){
					config = configBuilder(options);
				}
				input.f_input_number(config);
			});
		});
	})();
	//input combobox
	(function(){
		function comboboxBuilder(config){
			config.body = $('<ul class="list-group" style="display:none;z-index:99999"></ul>');
			config.body.css('position','absolute');
			config.canHide = true;
			config.body.mouseenter(function(){
				config.canHide = false;
			});
			config.body.mouseleave(function(){
				config.canHide = true;
			});
			config.target.after(config.body);
			dataBuilder(config);
		}
		function dataBuilder(config){
			if(config.url){
				var param = {};
				config.before.call(null,param);
				param[config.queryField] = config.target.val();
				$.ajax({
					url:config.url,
					cache:false,
					data:param,
					dataType:'json',
					type:config.method||'GET',
					success:function(data){
						config.datas = config.filter.call(null,data);
						config.initDatas = config.datas;
						optionBuilder(config);
					},
					error:function(){
						console.log(arguments);
					}
				});
			}else{
				optionBuilder(config);
			}
		}
		function optionBuilder(config){
			config.body.empty();
			$.each(config.datas,function(i,d){
				var option = $('<li class="list-group-item">'+d[config.textField]+'</li>');
				config.body.append(option);
				option.css('cursor','pointer');
				option.click(function(e){
					config.target.data('f-value',d[config.valueField]);
					config.target.val(d[config.textField]);
					hide(config);
					config.select.call(null,d);
					e.stopPropagation();
					e.preventDefault();
				});
			});
		}
		function isValid(config){
			if(getValue(config) == ''&&config.required){
				errorTip(config);
			}
		}
		function show(config){
			var position = config.target.position();
			config.body.css('top',position.top + config.target.outerHeight());
			config.body.css('left',position.left);
			config.body.show();
		}
		function hide(config){
			config.body.hide();
		}
		function loadData(config,arr){
			if($.isArray(arr)){
				config.datas = arr;
				optionBuilder(config);
			}
		}
		function setValue(config,value){
			$.each(config.datas,function(i,d){
				if(d[config.valueField] == value){
					config.defValue = d[config.textField];
					config.target.data('f-value',value);
					config.target.val(d[config.textField]);
				}
			});
		}
		function getValue(config){
			var value = config.target.data('f-value');
			if(value){
				return value;
			}
			return '';
		}
		$.fn.f_input_combobox = function(config){
			if(this.get(0).tagName != 'INPUT'){
				return undefined;
			}
			if($.isPlainObject(config)){
				config = $.extend({
					required:false,
					errMsg:undefined,
					blur:function(){},
					hint:undefined,
					before:function(param){},
					filter:function(data){return data},
					url:'',
					datas:[],
					textField:'k',
					valueField:'v',
					queryField:'q',
					select:function(option){}
				},config);
				config.target = this;
				config.initDatas = config.datas;
				input_init(config);
				config.target.focus(function(){
					show(config);
				});
				config.target.blur(function(){
					if(config.canHide){
						hide(config);
					}
					config.blur.call(config.target);
				});
				config.target.keyup(function(e){
					config.target.removeData('f-value');
					if(e.which == 32&&config.url){
						var query = $.trim(config.target.val());
						config.target.val(query);
					}else{
						var key = config.target.val();
						if(key == ''){
							config.datas = config.initDatas;
						}else{
							var arr = [];
							$.each(config.initDatas,function(i,d){
								if(d[config.textField].match(key)){
									arr.push(d);
								}
							});
							config.datas = arr;
						}
					}
					dataBuilder(config);
				});
				comboboxBuilder(config);
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
		$(function(){
			$('input[f-type="input_combobox"]').each(function(){
				var input = $(this);
				var options = input.attr('f-options');
				var config = {};
				if(options){
					config = configBuilder(options);
				}
				input.f_input_combobox(config);
			});
		});
		
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
			if(this.get(0).tagName != 'INPUT'){
				return undefined;
			}
			if($.isPlainObject(config)){
				config = $.extend({
					required:false,
					errMsg:undefined,
					hint:undefined,
					regex:undefined,
					maxLength: Infinity,
					minLength: 0,
					blur:function(){}
				},config);
				config.target = this;
				input_init(config);
				config.target.blur(function(){
					config.blur.call(config.target);
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
		};
		$(function(){
			$('input[f-type="text"]').each(function(){
				var input = $(this);
				var options = input.attr('f-options');
				var config = {};
				if(options){
					config = configBuilder(options);
				}
				input.f_input_text(config);
			});
		});
	})();
	//select combobox
	(function(){
		function comboboxBuilder(config){
			config.target.empty();
			$.each(config.datas,function(i,d){
				config.target.append('<option value="'+d[config.valueField]+'">'+d[config.textField]+'</option>');
			});
		}
		function getValue(config){
			return config.target.children('option:selected').val();
		}
		function getText(config){
			return config.target.children('option:selected').text();
		}
		function loadData(config,data){
			config.datas = data;
			comboboxBuilder(config);
		}
		function setValue(config,value){
			config.defValue = value;
			config.target.children('option:selected').removeProp('selected');
			config.target.children('option[value="'+value+'"]').prop('selected','selected');
		}
		function isValid(config){
			if(!getValue(config)&&config.required){
				errorTip(config);
			}
		}
		$.fn.f_combobox = function(config){
			if(this.get(0).tagName != 'SELECT'){
				return undefined;
			}
			if($.isPlainObject(config)){
				config = $.extend({
					required:false,
					datas:[],
					errMsg:undefined,
					hint:undefined,
					before:function(param){},
					filter:function(data){return data},
					valueField:'v',
					textField:'k'
				},config);
				config.target = this;
				input_init(config);
				if(config.url){
					var param = {};
					config.before.call(null,param);
					$.ajax({
						url:config.url,
						type:config.method||'GET',
						dataType:'json',
						cache:false,
						data:param,
						success:function(data){
							config.datas = config.filter.call(null,data);
							comboboxBuilder(config);
						},
						error:function(XMLHttpRequest, textStatus, errorThrown){
							console.log(textStatus);
						}
					});
				}else{
					comboboxBuilder(config);
				}
				this.data('f_config',config);
				return this;
			}else{
				var fun = config;
				config = this.data('f_config');
				switch(fun){
				case 'getValue':return getValue(config);
				case 'getText':return getText(config);
				case 'isValid':return isValid(config);
				case 'loadData':loadData(config,arguments[1]);break;
				case 'setValue':setValue(config,arguments[1]);break;
				default:return undefined;
				}
			}
		}
		$(function(){
			$('select[f-type="combobox"]').each(function(){
				var select = $(this);
				var config = {};
				var options = select.attr('f-options');
				if(options){
					config = configBuilder(options);
				}
				select.f_combobox(config);
			});
		});
	})();
	
	//input datetimepicker
	(function(){
		function isValid(config){
			if(getValue(config) == ''&&config.required){
				errorTip(config);
			}
		}
		function pickerBuilder(config){
			var picker = config.picker = $('<div></div>');
			picker.css('position','absolute');
			picker.css('z-index',99999);
		}
		function show(config){
			var position = config.target.position();
			config.picker.css('top',position.top + config.target.outerHeight());
			config.picker.css('left',position.left);
			config.picker.show();
		}
		function hide(config){
			config.picker.hide();
		}
		$.fn.f_input_datepicker = function(config){
			if(this.get(0).tagName != 'INPUT'){
				return undefined;
			}
			if($.isPlainObject(config)){
				config = $.extend({
					required:false,
					errMsg:undefined,
					hint:undefined,
					blur:function(){},
					dsep:'-',
					tsep:':',
					select:function(){}
				},config);
				config.target = this;
				input_init(config);
				config.target.prop('readonly','readonly');
				config.target.blur(function(){
					config.blur.call(config.target);
				});
				pickerBuilder(config);
				this.data('f_config',config);
				return this;
			}else{
				var fun = config;
				config = this.data('f_config');
				switch(fun){
				case 'getValue':return getValue(config);
				case 'isValid':return isValid(config);
				case 'setValue':setValue(config,arguments[1]);break;
				default:return undefined;
				}
			}
		}
	})();
})(jQuery);
