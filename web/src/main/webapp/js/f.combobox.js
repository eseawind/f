/**
 * 搜索，ajax，组合框
 */
(function($){
	function comboboxBuilder(config){
		$.each(config.datas,function(i,d){
			config.target.append('<option value="'+d[config.valueField]+'">'+d[config.textField]+'</option>');
		});
	}
	$.fn.f_combobox = function(config){
		if($.isPlainObject(config)){
			config = $.extend({
				datas:[],
				before:function(param){},
				filter:function(data){return data},
				valueField:'v',
				textField:'k'
			},config);
			config.target = $(this);
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
			this.data('config',config);
			return this;
		}else{
			var fun = config;
			config = this.data('config');
			switch(fun){
			case '':;break;
			case '':;break;
			case '':;break;
			case '':;break;
			case '':;break;
			case '':;break;
			default:return undefined;
			}
		}
	}
})(jQuery);