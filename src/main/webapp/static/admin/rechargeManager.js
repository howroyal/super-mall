//全局变量
//当前页面大小
var currentPageSize = 10;

var RechargeManager = {
		currentPageSize : 10,
		initTable : function(){
			$('table').bootstrapTable({
				method : 'post',
				url : contextPath + '/rechargeManager/list.html',
				contentType : 'application/x-www-form-urlencoded',
				minimumCountColumns : 3, // 设置最少显示列个数
				queryParams : RechargeManager.queryParams,
				responseHandler : responseHandler,
				columns : [ {
					field : 'userId',
					title : '用户名'
				},{
					field : 'payWay',
					title : '支付渠道'
				}, {
					field : 'payAccount',
					title : '支付账户'
				}, {
					field : 'rechargeMoney',
					title : '充值金额'
				}, {
					field : 'payInfo',
					title : '支付详情'
				}, {
					field : 'valid',
					title : '验证状态',
					formatter : RechargeManager.statusFormatter
				}, {
					field : 'createTime',
					title : '创建日期'
				},{
					field : 'updateTime',
					title : '更新日期'
				}, {
					field : 'operate',
					title : '操作',
					events: RechargeManager.operateEvents,
					formatter : RechargeManager.operationFormatter
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
			var userId = $('#userId').val();
			if(userId){
				dto.userId = userId;
			}
			var payWay = $('#payWay').val();
			if(payWay){
				dto.payWay = payWay;
			}
			var valid = $('#valid').val();
			if(valid){
				dto.valid = valid;
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
		 * 状态格式化
		 */
		statusFormatter : function(value, row, index){
			var text = "-";
			var color = "";
			if(value == 0){
				text = "未验证";
				color = "text-danger";
			}else if(value == 1){
				text = "已验证";
				color = "text-success";
			}else if(value == 2){
				text = "已删除";
				color = "text-warning";
			}
			
			return '<strong class="'+color+'">'+text+'</strong>';
		},
		
		// 操作列
		operationFormatter : function(value, row, index){
			var valid = ['<div class="btn-group btn-group-xs">',
							'<button type="button" class="btn btn-success validBtn">',
							'<i class="glyphicon glyphicon-edit"></i>确认</button></div>'];
			var remove = ['<div class="btn-group btn-group-xs">',
								'<button type="button" class="btn btn-danger deleteBtn">',
								'<i class="glyphicon glyphicon-remove"></i>删除</button></div>' ];
			if(row.valid == 0){
				return valid.join('') + remove.join('');
			}
			if(row.valid == 1){
				return remove.join('');
			}
			if(row.valid == 2){
				return valid.join('');
			}
		},
		
		/**
		 * 操作列表
		 */
		operateEvents : {
			
			/**
			 * 编辑按钮
			 */
			'click .validBtn' : function(e, value, row, index){
				
				$('#id').val(row.id);
				$('#userName').val(row.userId);
				$('#rechargeMoney').val(row.rechargeMoney);
				
				$('#validModal').modal('show');
			},

			/**
			 * 删除按钮
			 */
			'click .deleteBtn' : function(e, value, row, index){
				RechargeManager.deleteB(row.id);
			}
		},
		
		/**
		 * 删除
		 */
		deleteB : function(id){
			$('#removeId').val(id);
			$('#deleteModal').modal('show');
		},
		
		valid : function(){
			$.ajax({
				url : contextPath + "/rechargeManager/valid.html",
				type :"POST",
				dataType : "json",
				data: {
					id : $('#id').val(),
					money : $('#rechargeMoney').val()
				},
				success: function(res){
					if(res.success){
						qiao.bs.msg("删除操作已成功!");
						$('#validModal').modal('hide');
						$('table').bootstrapTable('refresh');
					}else {
						qiao.bs.msg("操作失败,请检查参数及网络设置!");
					}
				},
				error : function(){
					qiao.bs.msg("操作失败,请检查参数及网络设置!");
				}
			});
		},
		
		/**
		 * 删除提交事件
		 */
		remove : function(){
			$.ajax({
				url : contextPath + "/rechargeManager/remove.html",
				type :"POST",
				dataType : "json",
				data: {
					id :$('#removeId').val()
				},
				success: function(res){
					qiao.bs.msg("删除操作已成功!");
					$('#deleteModal').modal('hide');
					$('table').bootstrapTable('refresh');
				},
				error : function(){
					qiao.bs.msg("操作失败,请检查参数及网络设置!");
				}
			});
		}
}


$(function(){
	RechargeManager.initTable();
	
	// 查询按钮点击事件
	$('#searchBtn').click(function() {
		RechargeManager.search();
	});

	//绑定编辑提交事件
	$('#validSubmitBtn').click(function(){
		RechargeManager.valid();
	});
	
	//绑定删除提交事件
	$('#deleteSubmitBtn').click(function(){
		RechargeManager.remove();
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
