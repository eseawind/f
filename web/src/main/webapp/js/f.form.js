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
		form.find('input,select').each(function(){
			var obj = $(this);
			if(obj.prop('name')){
				if(obj.get(0).tagName == 'SELECT'){
					param[$.trim(obj.prop('name'))] = obj.val();
				}else{
					if(obj.data('f-name')){
						param[$.trim(obj.prop('name'))] = obj[obj.data('f-name')].call(obj,'getValue');
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
})(jQuery);

