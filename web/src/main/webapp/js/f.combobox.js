/**
 * 依赖jquery1.11 
 * 搜索，ajax，组合框
 */
(function($){
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
	$.fn.f_combobox = function(config){
		if(this.context.nodeName != 'SELECT'){
			return undefined;
		}
		if($.isPlainObject(config)){
			config = $.extend({
				datas:[],
				before:function(param){},
				filter:function(data){return data},
				valueField:'v',
				textField:'k'
			},config);
			config.target = $(this);
			config.defValue = config.target.val();
			config.target.blur(function(){
				if(config.defValue == config.target.val()){
					$(this).attr('f-change',false);
				}else{
					$(this).attr('f-change',true);
				}
			});
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
			case 'loadData':loadData(config,arguments[1]);break;
			case 'setValue':setValue(config,arguments[1]);break;
			default:return undefined;
			}
		}
	}
	$(function(){
		$('select[f-type="combobox"]').each(function(){
			var obj = $(this);
			var config = $.parseJSON('{'+obj.attr('f-options')+'}');
			obj.f_combobox(config);
		});
	});
})(jQuery);