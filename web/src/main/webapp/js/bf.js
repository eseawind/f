
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
						window.location.href = window.f.staUrl + "/page/login/blogin.htm";
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
	var f = window.f = window.f||{};
	f.alertError = function(message){
		$("#bhead_errorAlert").empty();
		$("#bhead_errorAlert").f_alertError(message);
	}
	f.unlogin = function(){
		$.getJSON(f.dynUrl+"/login/unlogin.htm",function(d){
			if(d.success){
				window.location.href = window.f.staUrl + "/page/login/blogin.htm";
			}else{
				f.alertError(d.errMsg);
			}
		})
	}
})(jQuery);