//全局变量
//当前页面大小
var currentPageSize = 10;

var OrderManager = {
		currentPageSize : 10,
		initTable : function(){
			$('table').bootstrapTable({
				method : 'post',
				url : contextPath + '/orderManager/list.html',
				contentType : 'application/x-www-form-urlencoded',
				minimumCountColumns : 3, // 设置最少显示列个数
				queryParams : OrderManager.queryParams,
				responseHandler : responseHandler,
				columns : [ {
					field : 'orderNo',
					title : '订单号'
				},{
					field : 'amountPrice',
					title : '订单金额'
				}, {
					field : 'message',
					title : '客户留言'
				}, {
					field : 'phone',
					title : '收货电话'
				}, {
					field : 'name',
					title : '收货人'
				}, {
					field : 'address',
					title : '收货地址'
				}, {
					field : 'createTime',
					title : '下单时间'
				},{
					field : 'paymentType',
					title : '支付方式'
				}, {
					field : 'isPay',
					title : '是否支付'
				}, {
					field : 'isDelivery',
					title : '是否发货'
				}, {
					field : 'operate',
					title : '操作',
					events: OrderManager.operateEvents,
					formatter : OrderManager.operationFormatter
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
			var orderNo = $('#orderNo').val();
			if(orderNo){
				dto.orderNo = orderNo;
			}
			var paymentType = $('#paymentType').val();
			if(paymentType){
				dto.paymentType = paymentType;
			}
			var isPay = $('#isPay').val();
			if(isPay){
				dto.isPay = isPay;
			}
			var isDelivery = $('#isDelivery').val();
			if(isDelivery){
				dto.isDelivery = isDelivery;
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
			
			var pay = ['<div class="btn-group btn-group-xs">',
						'<button type="button" class="btn btn-primary payBtn">',
						'<i class="glyphicon glyphicon-edit"></i>确认收款</button></div>'];
			var delivery = ['<div class="btn-group btn-group-xs">',
						'<button type="button" class="btn btn-primary showBtn">',
						'<i class="glyphicon glyphicon-edit"></i>查看详情</button></div>'];
			var remove = ['<div class="btn-group btn-group-xs">',
							'<button type="button" class="btn btn-danger deleteBtn">',
							'<i class="glyphicon glyphicon-remove"></i>删除</button></div>' ];

			return pay.join('') + delivery.join('') + remove.join('');
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
					OrderManager.viewPic("#editpic",this,$('.editAlert'));
				});
				
				$('#editModal').modal('show');
			},

			/**
			 * 删除按钮
			 */
			'click .deleteBtn' : function(e, value, row, index){
				OrderManager.deleteB(row.pkid);
			}
		}
}


$(function(){
	OrderManager.initTable();
	
	// 查询按钮点击事件
	$('#searchBtn').click(function() {
		OrderManager.search();
	});
	
	// 绑定添加提交事件
	$('#createBtn').click(function() {
		var clazz = $(this).attr('class');
		if(clazz.indexOf('disabled') > 0){
			return;
		}
		OrderManager.add();
	});

	
	
	//绑定编辑提交事件
	$('#editSubmitBtn').click(function(){
		var clazz = $(this).attr('class');
		if(clazz.indexOf('disabled') > 0){
			return;
		}
		OrderManager.edit();
	});
	
	//绑定删除提交事件
	$('#deleteSubmitBtn').click(function(){
		OrderManager.remove();
	});
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
