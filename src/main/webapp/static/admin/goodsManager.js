//全局变量
//当前页面大小
var currentPageSize = 10;

/**
 * 验证对象
 */
var Valide = {
	
}

var GoodsManager = {
		currentPageSize : 10,
		initTable : function(){
			$('table').bootstrapTable({
				method : 'post',
				url : contextPath + '/goodsManager/list.html',
				contentType : 'application/x-www-form-urlencoded',
				minimumCountColumns : 3, // 设置最少显示列个数
				queryParams : GoodsManager.queryParams,
				responseHandler : responseHandler,
				onLoadSuccess :GoodsManager.onLoadSuccess,
				columns : [ {
					field : 'name',
					title : '名称'
				},{
					field : 'mainImg',
					title : '图片',
					formatter : GoodsManager.picFormatter
				},{
					field : 'categoryId',
					title : '类别'
				}, {
					field : 'stock',
					title : '库存'
				}, {
					field : 'clicks',
					title : '点击数量'
				}, {
					field : 'marketPrice',
					title : '市场价'
				}, {
					field : 'price',
					title : '价格'
				}, {
					field : 'sales',
					title : '销量'
				}, {
					field : 'operate',
					title : '操作',
					events: GoodsManager.operateEvents,
					formatter : GoodsManager.operationFormatter
				}
				]
			});
		},
		
		/**
		 * 查询按钮
		 */
		search : function(){
				$('table').bootstrapTable('selectPage', 1);
		},
		
		// 查询条件
		queryParams :function(params){
			var dto = {};
			var name = $('#name').val();
			if(name){
				dto.name = name;
			}
			var state = $('#state').val();
			if(state){
				dto.state = state
			}
			//pageSize改变，从第一页开始
			if(currentPageSize != params.limit){
				//设置从起始页开始
				$('table').bootstrapTable('getOptions').pageNumber = 1;
				//必须设置
				params.offset = 0;
			}
			dto.start = params.offset;
			dto.end = params.offset + params.limit;
			currentPageSize = params.limit;
			return  dto;
		},
		
		/**
		 * url格式化
		 */
		urlFormatter : function(value, row, index){
			var url = row.url;
			if(!url){
				return;
			}
			var html = "<a target='_blank' href='" + row.url + "'>" + row.url + "</a>";
			return html;
		},
		
		picFormatter : function(value, row, index){
			var html = ""
			+"<div class='container-fluid'>"
			+"<div class='row-fluid'>"
			+"	<div class='span12'>"
			+"		<img height=40 width=40 alt='-' src='" + row.mainImg + "' />"
			+"	</div>"
			+"</div>"
			+"</div>"
			return html;
		},
		
		/**
		 * 状态格式化
		 */
		statusFormatter : function(value, row, index){
			var text = "-";
			var color = "";
			if(value == 0){
				text = "已上线";
				color = "text-success";
			}else if(value == 1){
				text = "已下线";
				color = "text-warning";
			}else if(value == 2){
				text = "已删除";
				color = "text-danger";
			}else {
				return;
			}
			
			return '<strong class="'+color+'">'+text+'</strong>';
		},
		// 操作列
		operationFormatter : function(value, row, index){
			var edit = ['<div class="btn-group btn-group-xs">',
						'<button type="button" class="btn btn-primary editBtn">',
						'<i class="glyphicon glyphicon-edit"></i>编辑</button></div>'];
			var remove = ['<div class="btn-group btn-group-xs">',
							'<button type="button" class="btn btn-danger deleteBtn">',
							'<i class="glyphicon glyphicon-remove"></i>删除</button></div>' ];
//			var state = row.state;
//			if(state == '0'){
//				return edit.join('')
//			}
//			if(state == '1'){
				return edit.join('') + remove.join('');
//			}else {
//				return;
//			}
		},
		
		/**
		 * 删除
		 */
		deleteB : function(pkid){
			$('#removePkid').val(pkid);
			$('#deleteModal').modal('show');
		},
		
		/**
		 * 删除提交事件
		 */
		remove : function(){
			var pkid = $('#removePkid').val();

			$.ajax({
				url : contextPath + "/banner/remove",
				type :"POST",
				dataType : "json",
				data: {
					pkid : pkid
				},
				success: function(res){
					qiao.bs.msg("banner删除操作已成功!");
					$('#deleteModal').modal('hide');
					$('table').bootstrapTable('refresh');
				},
				error : function(){
					qiao.bs.msg("banner操作失败,请检查参数及网络设置!");
				}
			});
		},
		
		/**
		 * 编辑banner
		 */
		edit: function(){
			var bannerName = $('#editBannerName').val();
			if(!bannerName){
				$('.editAlert').html('banner名称不能为空');
				$('.editAlert').removeClass('hide');
				return;
			}
			
			var url = $('#editUrl').val();
			var urlV = Valide.valideURL(url);
			if(!urlV){
				$('.editAlert').html('请输入正确的URL,如http://www.zhaogang.com,https://vip.zhaogang.com');
				$('.editAlert').removeClass('hide');
				return;
			}
			
			//判断图片
			var filename = $('#editBannerPic').val();
			if(filename !== null && filename !== undefined && filename !== ''){
				var fileNameV = Valide.valideFileName(filename);
				if(!fileNameV){
					$('.editAlert').html('图片格式错误，仅支持png|jpg|jpeg|gif|bmp格式!');
					$('.editAlert').removeClass('hide');
					return;
				}
			}
			
			$('#editSubmitBtn').addClass('disabled');
			var data = new FormData($('#editForm')[0]);
			
			$.ajax({
				url : contextPath + "/banner/edit",
	            type: 'POST',  
	            data: data,  
	            dataType: 'JSON',  
	            cache: false,  
	            processData: false,  
	            contentType: false,
				success: function(res){
					$('#editModal').modal('hide');
					$('#editForm')[0].reset();
					$('#editpic').attr("src","");
					//$('table').bootstrapTable('refresh');
					$('table').bootstrapTable('selectPage', 1);
					qiao.bs.msg("banner编辑操作已成功!");
				},
				error : function(){
					$('#editSubmitBtn').removeClass('disabled');
					qiao.bs.msg("banner操作失败,请检查参数及网络设置!");
				}
			});
		},
		
		/**
		 * 数据加载成功后执行
		 */
		onLoadSuccess : function(){
			
		},
		
		/**
		 * 操作列表
		 */
		operateEvents : {
			/**
			 * 编辑按钮
			 */
			'click .editBtn' : function(e, value, row, index){
				
				$('#editForm')[0].reset();
				$('#pkid').val(row.pkid);
				$('#picUrl').val(row.picUrl);
				$('#editBannerName').val(row.bannerName);
				$('#editUrl').val(row.url);
				$('#editDescription').val(row.description);
				$('#editState').val(row.state);
				$('#editpic').attr('src',row.picUrl);
				$('.editAlert').addClass('hide');
				$('#editSubmitBtn').removeClass('disabled');
				
				var $obj = $('#editBannerPic');
				$obj.after($obj.clone().val(""));
				$obj.remove();
				$('#editBannerPic').change(function(){
					GoodsManager.viewPic("#editpic",this,$('.editAlert'));
				});
				
				$('#editModal').modal('show');
			},

			/**
			 * 删除按钮
			 */
			'click .deleteBtn' : function(e, value, row, index){
				GoodsManager.deleteB(row.pkid);
			}
		}
}


$(function(){
	GoodsManager.initTable();
	//绑定新建商品按钮
	$('#addBtn').click(function(){
		window.open(contextPath + '/goodsManager/new.html');
	});
	
	// 查询按钮点击事件
	$('#searchBtn').click(function() {
		GoodsManager.search();
	});
	
	// 绑定添加提交事件
	$('#createBtn').click(function() {
		var clazz = $(this).attr('class');
		if(clazz.indexOf('disabled') > 0){
			return;
		}
		GoodsManager.add();
	});

	// 绑定预览事件
	$('#viewBtn').click(function() {
		GoodsManager.preview();
	});
	
	//绑定编辑提交事件
	$('#editSubmitBtn').click(function(){
		var clazz = $(this).attr('class');
		if(clazz.indexOf('disabled') > 0){
			return;
		}
		GoodsManager.edit();
	});
	
	//绑定删除提交事件
	$('#deleteSubmitBtn').click(function(){
		GoodsManager.remove();
	});
	
//	$('#bannerPic').change(function(){
//		GoodsManager.viewPic("#pic",this,$('.addAlert'));
//	});
//	
//	$('#editBannerPic').change(function(){
//		GoodsManager.viewPic("#editpic",this,$('.editAlert'));
//	});
});

// 回调函数
function responseHandler(res) {
	if (res.success) {
		return {
			"rows" : res.rows,
			"total" : res.total
		};
	} else {
		return {
			"rows" : [],
			"total" : 0
		};
	}
}
