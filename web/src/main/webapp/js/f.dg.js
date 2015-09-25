/**
 * 依赖bootstrap 3
 * jquery 1.11
 */
(function($){
	function pageCount(config){
		var pageCount = Math.ceil(config.datas.count/config.rows);
		return pageCount;
	};
	function headBuilder(thead,config){
		var tr = $('<tr></tr>');
		if(config.check){
			var td = $('<td style="width:23px"></td>');
			var cbox = $('<input type="checkbox"/>');
			cbox.change(function(){
				var cbox = $(this);
				if(cbox.is(':checked')){
					config.body.find('input[type="checkbox"][f-id]').prop('checked',true);
				}else{
					config.body.find('input[type="checkbox"][f-id]').removeProp('checked',false);
				}
			});
			td.append(cbox);
			tr.append(td);
		}
		$.each(config.columns,function(i,column){
			var td = $('<td>'+column.title+'</td>');
			tr.append(td);
		});
		thead.append(tr);
	}
	function rowBuilder(rowIndex,tr,columns,data,check){
		tr.css("cursor","pointer");
		if(check){
			tr.append('<td><input type="checkbox" f-id="'+rowIndex+'"/></td>');
		}
		$.each(columns,function(i,column){
			var value = data[column.field];
			if($.isFunction(column.formatter)){
				value = column.formatter.call(null,value,data);
			}
			var td = $('<td></td>');
			if(column.showTip){
				//增加cell提示
			}
			td.html(value);
			tr.append(td);
		});
	}
	function bodyBuilder(config){
		config.body.empty();
		$.each(config.datas.rows,function(i,data){
			var tr = $('<tr f-id="'+i+'"></tr>');
			tr.click(function(){
				tr.parent().children("tr").removeClass("info");
				tr.addClass("info");
				config.click.call(null,i,data);
				config.dblclick.call(null,i,data);
			});
			rowBuilder(i,tr,config.columns,data,config.check);
			config.body.append(tr);
		});
		if(config.pagination){
			var sNum = (config.page-1)*config.rows+1;
			var eNum = undefined;
			if(config.page == pageCount(config)){
				eNum = config.datas.count;
			}else{
				eNum = sNum+config.rows-1;
			}
			config.pagiinfo.text(sNum+"-"+eNum+":"+config.datas.count);
		}
	}
	function pagiBuilder(config){
		var sel = $('<select class="form-control" style="min-width:66px;width:66px;display:inline"></select>');
		$.each(config.pages,function(i,num){
			sel.append('<option value="'+num+'">'+num+'</option>');
		});
		sel.change(function(){
			config.rows = $(this).val();
		});
		var txt = $('<input class="form-control" style="min-width:66px;width:66px;display:inline" value="1"/>');
		txt.blur(function(){
			try{
				var page = parseInt(txt.val());
				console.log(page);
				if(page>0&&page<pageCount(config)){
					config.page = page;
					txt.val(page);
				}else{
					txt.val(config.page);
				}
			}catch(e){
				errorTip(config,e);
				txt.val(config.page);
			}
		});
		var flush = $('<button class="btn btn-default btn-lg" style="padding-top:7px;padding-bottom:7px"><i class="icon-refresh"></i></button>');
		flush.click(function(){
			dataBuilder(config);
		});
		var pre = $('<button class="btn btn-default btn-lg" style="padding-top:7px;padding-bottom:7px"><i class="icon-caret-left"></i></button>');
		pre.click(function(){
			if(config.page > 1){
				config.page--;
				txt.val(config.page);
				flush.click();
			}
		});
		var next = $('<button class="btn btn-default btn-lg" style="padding-top:7px;padding-bottom:7px"><i class="icon-caret-right"></i></button>');
		next.click(function(){
			if(config.page < pageCount(config)){
				config.page++;
				txt.val(config.page);
				flush.click();
			}
		});
		var btnDiv = $('<div class="text-left col-md-6 col-sm-6" style="min-width:320px"></div>');
		var pagiinfo = $('<div class="text-right col-md-6 col-sm-6" style="font-size:24px"></div>');
		var pagiDiv = $('<div class="row"></div>');
		btnDiv.append(sel).append(pre).append(txt).append(next).append(flush);
		pagiDiv.append(btnDiv);
		pagiDiv.append(pagiinfo);
		config.target.append(pagiDiv);
		config.pagiinfo = pagiinfo;
	}
	function errorTip(config,message){
		var error = $('<div class="alert alert-danger alert-dismissible"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+message+'</div>');
		config.target.prepend(error);
	}
	function dataBuilder(config){
		if(config.url){
			var param = {};
			config.before.call(null,param);
			if(config.pagination){
				param.page = config.page;
				param.rows = config.rows;
			}
			$.ajax({
				url:config.url,
				type:config.method||'GET',
				cache:false,
				dataType:"json",
				data:param,
				success:function(datas,ts){
					datas = config.filter.call(null,datas);
					config.datas = datas;
					bodyBuilder(config);
				},
				error:function(xhq,error,ethrown){
					errorTip(config,error);
				}
			});
		}else{
			bodyBuilder(config);
		}
	}
	function datagridBuilder(config){
		var table = $('<table class="table table-hover table-bordered table-condensed"></table>');
		var thead = $('<thead></thead>');
		var tbody = $('<tbody></tbody>');
		headBuilder(thead,config);
		table.append(thead);
		table.append(tbody);
		config.target.addClass('table-responsive');
		config.target.append(table);
		config.body = tbody;
		if(config.pagination){
			pagiBuilder(config);
		}
		if(!config.lazy){
			dataBuilder(config);
		}
	}
	$.fn.f_dg = function(config){
		config.target = this;
		config = $.extend({
			check:false,
			columns:[],
			page:1,
			rows:10,
			pages:[10,50,100,500,1000],
			click:function(index,data){
			},
			dblclick:function(index,data){
			},
			before:function(param){
			},
			filter:function(data){
				return data;
			},
			pagination:false,
			datas:{rows:[],count:0},
			rownumber:false,
			lazy:false
		},config);
		if(!config.lazy){
			datagridBuilder(config);
		}
		this.data("config",config);
	}
})(jQuery);