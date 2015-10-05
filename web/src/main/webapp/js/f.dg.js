/**
 * 依赖bootstrap 3 css jquery showloading (awesome bootstrap)
 * jquery 1.11
 */
(function($){
	function wrapContent(value,column){
		var content = $('<div style="white-space:nowrap;overflow:hidden;text-overflow:clip"></div>');
		if(column.showTip){
			content.popover({
				placement: "auto top",
				trigger: "hover",
				content: value
			});
		}
		content.html(value);
		return content;
	}
	function pageCount(config){
		var pageCount = Math.ceil(config.datas.count/config.rows);
		return pageCount;
	};
	function headBuilder(thead,config){
		var tr = $('<tr></tr>');
		if(config.rownumber){
			tr.append('<td style="background-color: #e6e6e6;width:14px" f-head="rownumber"></td>');
		}
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
			var td = $('<td></td>');
			var span = $('<span style="cursor:e-resize;float:left">&nbsp;</span>');
			var state = false;
			var x = 0;
			span.mousedown(function(e){
				x = e.pageX;
				state = true;
			});
			thead.mouseup(function(){
				state = false;
			});
			thead.mouseleave(function(){
				state = false;
			});
			thead.mousemove(function(e){
				if(state){
					var width = td.width();
					var range = e.pageX-x;
					x = e.pageX;
					if(range < width - 20){
						td.width(width - range);
					}
				}
			});
			td.append(span);
			config.fitColumn&&column.width&&td.css('width',column.width);
			var content = wrapContent(column.title,column);
			td.append(content);
			if(column.sort){
				content.css('cursor','pointer');
				content.click(function(){
					if(content.attr('sort') == 'asc'){
						content.attr('sort','desc');
						content.children('i').remove();
						content.append('<i class="icon-sort-down" style="padding-left:10px"></i>');
						config.defParam.sort = 'desc';
					}else{
						content.attr('sort','asc');
						content.children('i').remove();
						content.append('<i class="icon-sort-up" style="padding-left:10px"></i>');
						config.defParam.sort = 'asc';
					}
					dataBuilder(config);
				});
			}
			tr.append(td);
		});
		thead.append(tr);
	}
	function rowBuilder(rowIndex,data,tr,config){
		tr.css("cursor","pointer");
		if(config.rownumber){
			tr.append('<td style="background-color: #e6e6e6;font-size:14px">'+(rowIndex+(config.page-1)*config.rows+1)+'</td>');
		}
		if(config.check){
			tr.append('<td><input type="checkbox" f-id="'+rowIndex+'"/></td>');
		}
		$.each(config.columns,function(i,column){
			var value = data[column.field];
			if($.isFunction(column.formatter)){
				value = column.formatter.call(null,value,data);
			}
			var td = $('<td></td>');
			td.append(wrapContent(value,column));
			tr.append(td);
		});
	}
	function bodyBuilder(config){
		if(config.rownumber){
			var width = (""+config.page*config.rows).length*14;
			config.target.find('thead tr td[f-head="rownumber"]').css("width",width);
		}
		config.body.empty();
		$.each(config.datas.rows,function(i,data){
			var tr = $('<tr f-id="'+i+'" f-active="false"></tr>');
			tr.click(function(){
				var trs = tr.parent().children("tr");
				trs.attr('f-active','false');
				trs.removeClass("info");
				tr.attr('f-active','true');
				tr.addClass("info");
				config.click.call(null,i,data);
			});
			tr.dblclick(function(){
				config.dblclick.call(null,i,data);
			});
			rowBuilder(i,data,tr,config);
			config.body.append(tr);
		});
		if(config.pagination){
			var sNum = (config.page-1)*config.rows+1;
			var eNum = undefined;
			if(config.page == pageCount(config)){
				eNum = config.datas.count;
			}else{
				eNum = sNum - 1 + config.rows;
			}
			config.pagiinfo.text(sNum+"-"+eNum+":"+config.datas.count);
		}
	}
	function pagiBuilder(config){
		var sel = $('<select style="display:inline;float:left;padding:3.5px;border:1px solid #ccc;"></select>');
		$.each(config.pages,function(i,num){
			sel.append('<option value="'+num+'">'+num+'</option>');
		});
		var txt = $('<input value="1" style="width:66px;display:inline;float:left;padding:4.5px 5px 2.5px 5px;border:1px solid #ccc;"/>');
		txt.blur(function(){
			try{
				var page = parseInt(txt.val());
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
		var flush = $('<li><span><i class="icon-refresh"></i></span></li>');
		flush.click(function(){
			dataBuilder(config);
		});
		sel.change(function(){
			config.rows = parseInt($(this).val());
			config.page = 1;
			txt.val(config.page);
			flush.click();
		});
		var pre = $('<li><span><i class="icon-caret-left"></i></span></li>');
		pre.click(function(){
			if(config.page > 1){
				config.page--;
				txt.val(config.page);
				flush.click();
			}
		});
		var next = $('<li><span><i class="icon-caret-right"></i></span></li>');
		next.click(function(){
			if(config.page < pageCount(config)){
				config.page++;
				txt.val(config.page);
				flush.click();
			}
		});
		var btnDiv = $('<ul class="pagination  col-md-3 col-sm-5 col-xs-7" style="float:left;margin:0"></ul>');
		var pagiinfo = $('<ul class="pagination col-md-9 col-sm-7 col-xs-5 text-right" style="margin:0;padding:5px"><li><span></span></li></ul>');
		var pagiDiv = $('<nav style="min-width:520px;color:#337AB7"></nav>');
		btnDiv.append($('<span></span>').append(sel));
		btnDiv.append(pre);
		btnDiv.append($('<span></span>').append(txt));
		btnDiv.append(next);
		btnDiv.append(flush);
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
			$.extend(param,config.defParam);
			if(config.pagination){
				param.page = config.page;
				param.rows = config.rows;
			}
			try{
				config.target.showLoading({loadTip:config.loadTip});
			}catch(e){
				console.log(e);
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
				},
				complete:function(){
					try{
						config.target.hideLoading();
					}catch(e){
						console.log(e);
					}
				}
			});
		}else{
			bodyBuilder(config);
		}
	}
	function datagridBuilder(config){
		config.target.empty();
		var table = $('<table class="table table-hover table-bordered table-condensed"></table>');
		if(config.fitColumn){
			table.css('table-layout','fixed');
		}
		if(config.fontSize){
			table.css('font-size',config.fontSize);
		}
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
		if($.isPlainObject(config)){
			config.target = this;
			config = $.extend({
				fitColumn:false,
				check:false,
				columns:[],//title,field,showTip,sort,formatter,width
				page:1,
				rows:10,
				pages:[10,50,100,500,1000],
				defParam:{},
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
				lazy:false,
				loadTip:'处理中...'
			},config);
			if(!config.lazy){
				datagridBuilder(config);
			}
			this.data("config",config);
		}else{
			var fun = config;
			var config = this.data('config');
			switch(fun){
			case 'getDatas':return config.datas;
			case 'getSelected':return getSelected(config);
			case 'getChecked':return getChecked(config);
			case 'reload':reload(config,arguments[1]);break;
			case 'load':load(config,arguments[1]);break;
			case 'addRow':addRow(config,arguments[1],arguments[2]);break;
			case 'delRow':delRow(config,arguments[1]);break;
			default: return undefined;
			}
		}
		return this;
	};
	function getSelected(config){
		var obj = config.target.find('tr[f-active="true"]');
		if(obj.length == 0){
			return {};
		}
		return config.datas.rows[parseInt(obj.eq(0).attr("f-id"))];
	}
	function getChecked(config){
		var datas = [];
		if(config.check){
			config.target.find('input[type="checkbox"][f-id]:checked').each(function(i,cbox){
				datas.push(config.datas.rows[parseInt($(this).attr('f-id'))]);
			});
		}
		return datas;
	}
	function reload(config,obj){
		if($.isPlainObject(obj)){
			$.extend(config,obj);
		}
		dataBuilder(config);
	}
	function load(config,obj){
		config.page = 1;
		if($.isPlainObject(obj)){
			$.extend(config,obj);
		}
		datagridBuilder(config);
	}
	function addRow(config,data,index){
		index = parseInt(index);
		if(index>=0){
			config.datas.splice(index,0,data);
		}else{
			config.datas.push(data);
		}
		dataBuilder(config);
	}
	function delRow(config,index){
		index = parseInt(index);
		if(index>=0){
			config.datas.splice(index,1);
		}
		dataBuilder(config);
	}
})(jQuery);