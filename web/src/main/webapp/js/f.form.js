(function($){
	$.fn.f_isValid = function(){
		var form = $(this);
		var validSuc = true;
		form.find('input,select').each(function(){
			var obj = $(this);
			if(typeof obj.data('f-name') == 'string'){
				if(!obj[obj.data('f-name')].call(obj,'isValid')){
					validSuc = false;
				}
			}
		});
		return validSuc;
	};
	
	$.fn.f_serialized = function(){
		var form = $(this);
		var param = {};
		form.find('input[name],select[name]').each(function(){
			var obj = $(this);
			if(obj.prop('name')){
				if(obj.get(0).tagName == 'SELECT'){
					if(obj.data('f-name')&&!obj.data('f-change')){
						return;
					}
					param[$.trim(obj.prop('name'))] = obj.val();
				}else{
					if(obj.data('f-name')){
						if(obj.data('f-change')){
							param[$.trim(obj.prop('name'))] = obj[obj.data('f-name')].call(obj,'getValue');
						}
					}else{
						if(obj.prop('type')&&(obj.prop('type').toLowerCase() == 'radio'||obj.prop('type').toLowerCase() == 'checkbox')){
							if(obj.prop('checked')){
								param[$.trim(obj.prop('name'))] = obj.val();
							}
						}else{
							param[$.trim(obj.prop('name'))] = obj.val();
						}
					}
				}
			}
		});
		var param1 = {};
		$.each(form.serializeArray(),function(i,obj){
			param1[obj.name] = obj.value;
		});
		return $.extend(param1,param);
	};
	
	$.fn.f_create = function(){
		this.find('[f-type]').each(function(){
		    var obj = $(this);
			var options = obj.attr('f-options');
			if(options){
				try{
					options = eval('({'+$.trim(options)+'})')
				}catch(e){
					return;
				}
			}else{
				options = {};
			}
			var fun = $.trim(obj.attr('f-type'));
			if(fun == 'combobox'){
				obj.f_combobox(options);
			}else if(fun == 'input_combobox'){
				obj.f_input_combobox(options);
			}else{
				obj['f_input_'+fun].call(obj,options);
			}
		});
		return this;
	}
	
	$.fn.f_formLoad = function(data){
		this.find('input[name],select[name]').each(function(){
		    var obj = $(this);
			var name = $.trim(obj.prop('name'));
			if(name&&data[name] != undefined){
				if(obj.data('f-name')){
					obj[obj.data('f-name')].call(obj,'setValue',data[name]);
				}else{
					if(obj.prop('type')&&(obj.prop('type').toLowerCase() == 'radio'||obj.prop('type').toLowerCase() == 'checkbox')){
						if(obj.val() == data[name]){
							obj.prop('checked','checked');
						}else{
							obj.removeProp('checked');
						}
					}else{
						obj.val(data[name]);
					}
				}
			}			
		});
		return this;
	}
	
	$.fn.f_formReset = function(){
		if(this.get(0).tagName == 'FORM'){
			this.get(0).reset();
		}
		this.find('input[name],select[name]').each(function(){
			var obj = $(this);
			if(obj.data('f-name')){
				obj[obj.data('f-name')].call(obj,'reset');
			}else{
				obj.val('');
			}
		});
	}
	
	$.fn.f_formClear = function(){
		if(this.get(0).tagName == 'FORM'){
			this.get(0).reset();
		}
		this.find('input[name],select[name]').each(function(){
			var obj = $(this);
			if(obj.data('f-name')){
				obj[obj.data('f-name')].call(obj,'clear');
			}
		});
	}
})(jQuery);

