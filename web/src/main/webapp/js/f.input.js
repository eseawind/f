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
				hint = hint +' '+ config.hint;
			}
			config.target.attr('placeholder',hint);
		}else if(typeof config.hint == 'string'){
			config.target.attr('placeholder', config.hint);
		}
		if(config.errMsg && typeof config.errMsg == 'string'){
			config.target.popover({
				placement: config.errDir||"auto right",
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
		};
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
			config.body.css('min-width',config.target.width());
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
			var re = true;
			if(getValue(config) == ''&&config.required){
				errorTip(config);
				re = false;
			}
			return re;
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
					filter:function(data){return data;},
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
		};
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
			return re;
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
			var re = true;
			if(!getValue(config)&&config.required){
				errorTip(config);
				re = false;
			}
			return re;
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
					filter:function(data){return data;},
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
				this.data('f-config',config);
				return this;
			}else{
				var fun = config;
				config = this.data('f-config');
				switch(fun){
				case 'getValue':return getValue(config);
				case 'getText':return getText(config);
				case 'isValid':return isValid(config);
				case 'loadData':loadData(config,arguments[1]);break;
				case 'setValue':setValue(config,arguments[1]);break;
				default:return undefined;
				}
			}
		};
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
			var re = true;
			if(getValue(config) == ''&&config.required){
				errorTip(config);
				re = false;
			}
			return re;
		}
		function getDate(config){
			if(config.tsep){
				return new Date(config.oper.year.val(),config.oper.month.val()-1,config.oper.value,config.oper.hours.val(),config.oper.minutes.val(),config.oper.seconds.val());
			}
			return new Date(config.oper.year.val(),config.oper.month.val()-1,config.oper.value);
		}
		function getValue(config){
			var year = config.oper.year.val();
			var month = parseInt(config.oper.month.val());
			var day = parseInt(config.oper.value);
			var value = year + config.dsep;
			if(month < 10){
				month = '0' + month;
			}
			value = value + month + config.dsep;
			if(day < 10){
				day = '0' + day;
			}
			value = value + day;
			if(config.tsep){
				value = value + config.sep;
				var h = parseInt(config.oper.hours.val());
				var m = parseInt(config.oper.minutes.val());
				var s = parseInt(config.oper.seconds.val());
				if(h < 10){
					h = '0' + h;
				}
				if(m < 10){
					m = '0' + m;
				}
				if(s < 10){
					s = '0' + s;
				}
				value = value + h + config.tsep;
				value = value + m + config.tsep;
				value = value + s;
			}
			return value;
		}
		function setValue(config,date){
			var year = config.oper.year.val();
			var month = config.oper.month.val();
			config.oper.year.val(date.getFullYear());
			if(config.oper.year.val()){
				config.oper.month.val(date.getMonth()+1);
				if(config.oper.month.val()){
					config.oper.value = date.getDate();
					if(config.tsep){
						config.oper.hours.val(date.getHours());
						config.oper.minutes.val(date.getMinutes());
						config.oper.seconds.val(date.getSeconds());
					}
					bodyBuilder(config);
					config.target.val(getValue(config));
				}else{
					config.oper.year.val(year);
					config.oper.month.val(month);
				}
			}else{
				config.oper.year.val(year);
			}
		}
		function keypress(key){
			if(key == 8)return true;
			if(key>=48&&key<=57){
				return true;
			}
			return false;
		}
		function selectedIndex(arr,v){
			for(var i = 0;i<arr.length;i++){
				if(arr[i] == v){
					return i;
				}
			}
			return -1;
		}
		function pickerBuilder(config){
			var picker = config.picker = 
				$('<div style="background-color:white;border:1px solid #ccc;cursor:pointer;display:none">'
					+'<div class="row text-center" style="margin:0;padding:5px 0">'
					+'	<div class="col-md-3 col-xs-4 col-sm-4" style="padding:0">'
					+'		<button type="button" f-id="leftBtn2" class="btn btn-xs"><i class="icon-double-angle-left"></i></button>'
					+'		<button type="button" f-id="leftBtn" class="btn btn-xs"><i class="icon-angle-left"></i></button>'
					+'	</div>'
					+'	<div class="col-md-3 col-xs-4 col-sm-4" style="padding:0">'
					+'		<select f-id="year_combo" style="font-size:10px;padding:0;height:22px;" class="form-control">'
					+'		</select>'
					+'	</div>'
					+'	<div class="col-md-3 col-xs-4 col-sm-4" style="padding:0">'
					+'		<select f-id="month_combo" style="font-size:10px;padding:0;height:22px;" class="form-control">'
					+'		</select>'
					+'	</div>'
					+'	<div class="col-md-3 col-xs-4 col-sm-4" style="padding:0">'
					+'		<button type="button" f-id="rightBtn" class="btn btn-xs"><i class="icon-angle-right"></i></button>'
					+'		<button type="button" f-id="rightBtn2" class="btn btn-xs"><i class="icon-double-angle-right"></i></button>'
					+'	</div>'
					+'</div>'
					+'<table class="table table-condensed table-bordered text-center" style="font-size:10px;margin:0">'
					+'	<thead>'
					+'		<tr>'
					+'			<td>日</td>'
					+'			<td>一</td>'
					+'			<td>二</td>'
					+'			<td>三</td>'
					+'			<td>四</td>'
					+'			<td>五</td>'
					+'			<td>六</td>'
					+'		</tr>'
					+'	</thead>'
					+'	<tbody f-id="body">'
					+'	</tbody>'
					+'</table>'
					+'<div f-id="time" class="row text-center" style="font-size:10px;margin:0;border-top:1px solid #ccc;padding:5px 0 5px 0">'
					+'	<div class="col-md-4 col-xs-4 col-sm-4" style="padding:0">'
					+'		<span>时</span>'
					+'		<input style="width:20px" f-id="input_hours"/>'
					+'	</div>'
					+'	<div class="col-md-4 col-xs-4 col-sm-4" style="padding:0">'
					+'		<span>分</span>'
					+'		<input style="width:20px" f-id="input_minutes"/>'
					+'	</div>'
					+'	<div class="col-md-4 col-xs-4 col-sm-4" style="padding:0">'
					+'		<span>秒</span>'
					+'		<input style="width:20px" f-id="input_seconds"/>'
					+'	</div>'
					+'</div>'
					+'<div class="row text-center" style="font-size:10px;margin:0;border-top:1px solid #ccc;padding:5px 0 5px 0">'
					+'	<div class="col-md-4 col-xs-4 col-sm-4" style="padding:0">'
					+'		<button class="btn btn-xs" f-id="todayBtn" style="font-size:10px">今天</button>'
					+'	</div>'
					+'	<div class="col-md-4 col-xs-4 col-sm-4" style="padding:0">'
					+'		<button class="btn btn-xs" f-id="celBtn" style="font-size:10px">取消</button>'
					+'	</div>'
					+'	<div class="col-md-4 col-xs-4 col-sm-4" style="padding:0">'
					+'		<button class="btn btn-xs" f-id="okBtn" style="font-size:10px">确定</button>'
					+'	</div>'
					+'</div>'
					+'</div>');
			picker.css('position','absolute');
			picker.css('z-index',99999);
			picker.css('width',config.width||200);
			config.canHide = true;
			picker.mouseenter(function(){
				config.canHide = false;
			});
			picker.mouseleave(function(){
				config.canHide = true;
			});
			config.target.after(picker);
			config.oper = {value:config.defDate.getDate()};
			config.oper.leftBtn2 = picker.find('[f-id="leftBtn2"]').eq(0);
			config.oper.leftBtn = picker.find('[f-id="leftBtn"]').eq(0);
			config.oper.rightBtn = picker.find('[f-id="rightBtn"]').eq(0);
			config.oper.rightBtn2 = picker.find('[f-id="rightBtn2"]').eq(0);
			config.oper.year = picker.find('[f-id="year_combo"]').eq(0);
			config.oper.month = picker.find('[f-id="month_combo"]').eq(0);
			config.oper.body = picker.find('[f-id="body"]').eq(0);
			config.oper.time = picker.find('[f-id="time"]').eq(0);
			config.oper.hours = picker.find('[f-id="input_hours"]').eq(0);
			config.oper.minutes = picker.find('[f-id="input_minutes"]').eq(0);
			config.oper.seconds = picker.find('[f-id="input_seconds"]').eq(0);
			config.oper.todayBtn = picker.find('[f-id="todayBtn"]').eq(0);
			config.oper.celBtn = picker.find('[f-id="celBtn"]').eq(0);
			config.oper.okBtn = picker.find('[f-id="okBtn"]').eq(0);
			if(!$.isArray(config.years)||config.years.length==0){
				config.years = [];
				for(var i = 1970;i<3000;i++){
					config.years.push(i);
				}
			}
			$.each(config.years,function(i,y){
				config.oper.year.append('<option value="'+y+'">'+y+'</option>');
			});
			if(!$.isArray(config.months)||config.months.length==0){
				config.months = [1,2,3,4,5,6,7,8,9,10,11,12];
			}
			$.each(config.months,function(i,m){
				if(m<1||m>12)return;
				config.oper.month.append('<option value="'+m+'">'+m+'</option>');
			});
			config.oper.year.val(config.defDate.getFullYear());
			if(!config.oper.year.val()){
				config.oper.year.val(config.years[0]);
			}
			config.oper.month.val(config.defDate.getMonth()+1);
			if(!config.oper.month.val()){
				config.oper.month.val(config.months[0]);
			}
			config.oper.year.change(function(){
				bodyBuilder(config);
			});
			config.oper.month.change(function(){
				bodyBuilder(config);
			});
			//顶部按钮事件
			config.oper.leftBtn2.click(function(e){
				var i = selectedIndex(config.years,config.oper.year.val());
				if(i > 0){
					config.oper.year.val(config.years[i-1]);
					bodyBuilder(config);
				}
				e.preventDefault();
				e.stopPropagation();
			});
			config.oper.leftBtn.click(function(e){
				var i = selectedIndex(config.months,config.oper.month.val());
				if(i > 0){
					config.oper.month.val(config.months[i-1]);
					bodyBuilder(config);
				}
				e.preventDefault();
				e.stopPropagation();
			});
			config.oper.rightBtn.click(function(e){
				var i = selectedIndex(config.months,config.oper.month.val());
				if(i < config.months.length - 1){
					config.oper.month.val(config.months[i+1]);
					bodyBuilder(config);
				}
				e.preventDefault();
				e.stopPropagation();
			});
			config.oper.rightBtn2.click(function(e){
				var i = selectedIndex(config.years,config.oper.year.val());
				if(i < config.years.length - 1){
					config.oper.year.val(config.years[i+1]);
					bodyBuilder(config);
				}
				e.preventDefault();
				e.stopPropagation();
			});
			//底部按钮事件
			config.oper.todayBtn.click(function(e){
				config.oper.year.val(config.defDate.getFullYear());
				if(config.oper.year.val()){
					config.oper.month.val(config.defDate.getMonth()+1);
					if(config.oper.month.val()){
						config.oper.value = config.defDate.getDate();
					}else{
						config.oper.month.val(config.months[0]);
					}
				}else{
					config.oper.year.val(config.years[0]);
				}
				bodyBuilder(config);
				e.preventDefault();
				e.stopPropagation();
			});
			config.oper.celBtn.click(function(e){
				hide(config);
				e.preventDefault();
				e.stopPropagation();
			});
			config.oper.okBtn.click(function(e){
				var value = getValue(config);
				config.target.val(value);
				hide(config);
				config.select.call(config.target);
				e.preventDefault();
				e.stopPropagation();
			});
			//时间框事件
			if(config.tsep){
				config.oper.hours.val(config.defDate.getHours());
				config.oper.minutes.val(config.defDate.getMinutes());
				config.oper.seconds.val(config.defDate.getSeconds());
				config.oper.hours.keypress(function(e){
					if(!keypress(e.which)){
						e.preventDefault();
					}
				});
				config.oper.minutes.keypress(function(e){
					if(!keypress(e.which)){
						e.preventDefault();
					}
				});
				config.oper.seconds.keypress(function(e){
					if(!keypress(e.which)){
						e.preventDefault();
					}
				});
				config.oper.hours.keyup(function(){
					var value = parseInt(config.oper.hours.val());
					if(!value){
						value = 0;
					}else{
						if(value<0||value>=24){
							value = 0;
						}
					}
					config.oper.hours.val(value);
				});
				config.oper.minutes.keyup(function(){
					var value = parseInt(config.oper.minutes.val());
					if(!value){
						value = 0;
					}else{
						if(value<0||value>=60){
							value = 0;
						}
					}
					config.oper.minutes.val(value);
				});
				config.oper.seconds.keyup(function(){
					var value = parseInt(config.oper.seconds.val());
					if(!value){
						value = 0;
					}else{
						if(value<0||value>=60){
							value = 0;
						}
					}
					config.oper.seconds.val(value);
				});
				config.oper.hours.focus(function(){
					$(this).select();
				});
				config.oper.minutes.focus(function(){
					$(this).select();
				});
				config.oper.seconds.focus(function(){
					$(this).select();
				});
			}else{
				config.oper.time.hide();
			}
			bodyBuilder(config);
		}
		function bodyBuilder(config){
			var year = parseInt(config.oper.year.val());
			var month = parseInt(config.oper.month.val());
			var day = parseInt(config.oper.value);
			var body = config.oper.body;
			var arr = [[]];
			var days = 31;
			//计算每月天数
			if((month<=7&&month%2==0)||(month>=8&&month%2==1)){
				days = 30;
			}
			if(month == 2){
				days = 28;
				if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
					days = 29;
				}
			}
			var j = (new Date(year,month-1,1).getDay());
			for(var i=0;i<j;i++){
				arr[0].push('');
			}
			for(var i=0,x=0;i<days;i++){
				arr[x][j] = i+1;
				if(j == 6&&i<days-1){
					arr.push([]);
					j = 0;
					x++;
				}else{
					j++;
				}
			}
			for(var i=7-arr[arr.length-1].length;i>0;i--){
				arr[arr.length-1].push('');
			}
			body.empty();
			$.each(arr,function(i,arr1){
				var tr = $('<tr></tr>');
				$.each(arr1,function(i,v){
					var td = $('<td f-value="'+v+'">'+v+'</td>');
					//处理换月最后一天天数异常
					if(v >= 28){
						if(month == 2){
							if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
								if(config.oper.value > 29&&v==29){
									config.oper.value = 29;
								}
							}else{
								if(config.oper.value > 28&&v==28){
									config.oper.value = 28;
								}
							}
						}else if((month<=7&&month%2==0)||(month>=8&&month%2==1)){
							if(config.oper.value > 30&&v==30){
								config.oper.value = 30;
							}
						}
					}
					if(config.oper.value == v){
						td.css('background-color','#ccc');
					}else{
						td.css('background-color','#fff');
					}
					td.click(function(){
						if(td.attr('f-value') != ''){
							body.find('td').css('background-color','#fff');
							td.css('background-color','#ccc');
							config.oper.value = v;
						}
					})
					tr.append(td);
				});
				body.append(tr);
			});
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
					months:[],
					defDate:new Date(),
					years:[],
					dsep:'-',
					tsep:':',
					sep:' ',
					select:function(){}
				},config);
				config.target = this;
				input_init(config);
				config.target.prop('readonly','readonly');
				config.target.click(function(){
					show(config);
				});
				config.target.blur(function(){
					if(config.canHide){
						hide(config);
					}
					config.blur.call(config.target);
				});
				pickerBuilder(config);
				this.data('f-config',config);
				return this;
			}else{
				var fun = config;
				config = this.data('f-config');
				switch(fun){
				case 'getValue':return getValue(config);
				case 'getDate':return getDate(config);
				case 'isValid':return isValid(config);
				case 'setValue':setValue(config,arguments[1]);break;
				default:return undefined;
				}
			}
		};
		$(function(){
			$('input[f-type="datepicker"]').each(function(){
				var input = $(this);
				var config = {};
				var options = input.attr('f-options');
				if(options){
					config = configBuilder(options);
				}
				input.f_input_datepicker(config);
			});
		})
	})();
})(jQuery);
