/**
 * 基于jquery的datagrid
 * 需要 bootstrap3以上版本 inputmask
 * 只支持json数据
 */
(function($){
	$.fn.f_dg = function(conf){
		if($.isPlainObject(conf)){
			var config = $.extend({
				field:undefined,
				data:[],
				type:"post",
				before:function(param){},
				filter:function(data){return data},
				click:function(index,rowData){},
				dblclick:function(index,rowData){},
				beginEdit:function(index,rowData){},
				afterEdit:function(index,rowData,change){},
				page:1,
				rows:50,
				rowList:[50,100,150,200],
				pagination:false,
				columns:[],
				headBuilder:function(target,config){
					var tr = $('<tr></tr>')
					$.each(config.columns,function(i,column){
						tr.append('<th>'+column.name+'</th>');
					});
					target.append(tr);
				},
				pagiBuilder:function(target,config){
					if(config.pagination){
						var left = $('<div class="col-md-8 col-sm-8 col-xs-8 text-left"></div>');
						var right = $('<div class="col-md-4 col-sm-4 col-xs-4 text-right"></div>');
						var flush = $('<button class="btn btn-default btn-lg"><i class="icon-refresh"></i></button>');
						var list = $('<select class="form-control" style="width:85px;max-width:85px;display:inline"></select>');
						$.each(config.rowList,function(i,row){
							list.append('<option value="'+row+'">'+row+'</option>');
						});
						var pre = $('<button class="btn btn-default btn-lg"><i class="icon-caret-left"></i></button>');
						var next = $('<button class="btn btn-default btn-lg"><i class="icon-caret-right"></i></button>');
						var skipText = $('<input type="text" class="form-control" style="width:65px;max-width:65px;display:inline"/>');
						left.append(list).append(pre).append(skipText).append(next).append(flush);
						target.append(left).append(right);
						config.message = right;
					}
				},
				tableBuilder:function(target,config){
					if(config.url){
						var param = {};
						config.before(param);
						$.ajax({
							url:config.url,
							async:true,
							cache:false,
							dataType:"json",
							data:param,
							success:function(data,ts){
								if(parseInt(ts) == 200){
									config.data = config.filter.call(data);
								}
							},
							error:function(XMLHttpRequest, textStatus, errorThrown){
								var error = $('<div class="alert alert-danger" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+textStatus+'</div>');
								config.target.prepend(error);
							}
						});
					}
					$.each(config.data,function(i,d){
						var tr = $('<tr></tr>');
						$.each(config.columns,function(i,column){
							tr.append('<td>'+d[column.field]+'</td>');
						});
					});
				}
			},conf);
			var target = this;
			target.addClass("table-responsive");
			target.addClass("container-fluid");
			target.css("min-width",768);
			var table = $('<table class="table table-bordered"></table>')
			var head = $('<thead></thead>');
			var pagi = $('<div class="row"></div>');
			var body = $('<tbody></tbody>');
			config.headBuilder(head,config);
			config.pagiBuilder(pagi,config);
			config.tableBuilder(body,config);
			table.append(head).append(body);
			target.append(table);
			target.append(pagi);
			config.target = target;
			target.data("config",config);
		}else if(conf instanceof string){
			var config = this.data("config");
			config[conf].call(config,arguments);
		}
	}
})(jQuery);