/**
 * 
 */
(function($){
	$.fn.f_isValid = function(){
		var validSuc = true;
		this.find('input select').each(function(){
			var obj = $(this);
			var fun = obj.data('f-name');
			if(fun){
				if(!obj[fun].call(obj,'isValid')){
					validSuc = false
				}
			}
		});
	}
})(jQuery);