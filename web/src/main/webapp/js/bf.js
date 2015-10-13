
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
		},
		error:function(XmlHttpReq, textStatus, errorThrow){
			f.alertError(textStatus);
		}
	});
})(jQuery);