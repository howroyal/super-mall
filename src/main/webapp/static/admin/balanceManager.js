//全局变量
//当前页面大小
var currentPageSize = 10;

var BalanceManager = {
		currentPageSize : 10,
		initTable : function(){
			$('table').bootstrapTable({
				method : 'post',
				url : contextPath + '/balanceManager/list.html',
				contentType : 'application/x-www-form-urlencoded',
				minimumCountColumns : 3, // 设置最少显示列个数
				queryParams : BalanceManager.queryParams,
				responseHandler : responseHandler,
				columns : [ {
					field : 'userId',
					title : '用户名'
				},{
					field : 'money',
					title : '金额'
				},{
					field : 'type',
					title : '类型',
					formatter : BalanceManager.typeFormatter
				}, {
					field : 'isValid',
					title : '验证状态',
					formatter : BalanceManager.statusFormatter
				}, {
					field : 'createTime',
					title : '创建日期'
				},{
					field : 'updateTime',
					title : '更新日期'
				}, {
					field : 'operate',
					title : '操作',
					events: BalanceManager.operateEvents,
					formatter : BalanceManager.operationFormatter
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
			var type = $('#type').val();
			if(type){
				dto.type = type;
			}
			var isValid = $('#isValid').val();
			if(isValid){
				dto.isValid = isValid;
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
		
		typeFormatter : function(value, row, index){
			var text = "-";
			var color = "";
			if(value == 0){
				text = "充值";
				color = "text-success";
			}else if(value == 1){
				text = "支付";
				color = "text-warning";
			}
			
			return '<strong class="'+color+'">'+text+'</strong>';
		},
		
		/**
		 * 状态格式化
		 */
		statusFormatter : function(value, row, index){
			var text = "-";
			var color = "";
			if(value == 0){
				text = "有效";
				color = "text-success";
			}else if(value == 1){
				text = "已删除";
				color = "text-warning";
			}
			
			return '<strong class="'+color+'">'+text+'</strong>';
		},
		
		// 操作列
		operationFormatter : function(value, row, index){
			var remove = ['<div class="btn-group btn-group-xs">',
								'<button type="button" class="btn btn-danger deleteBtn">',
								'<i class="glyphicon glyphicon-remove"></i>删除</button></div>' ];
			if(row.isValid == 0){
				return remove.join('');
			}
		},
		
		/**
		 * 操作列表
		 */
		operateEvents : {

			/**
			 * 删除按钮
			 */
			'click .deleteBtn' : function(e, value, row, index){
				BalanceManager.deleteB(row.id);
			}
		},
		
		/**
		 * 删除
		 */
		deleteB : function(id){
			$('#removeId').val(id);
			$('#deleteModal').modal('show');
		},
		
		/**
		 * 删除提交事件
		 */
		remove : function(){
			$.ajax({
				url : contextPath + "/balanceManager/remove.html",
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
	BalanceManager.initTable();
	
	// 查询按钮点击事件
	$('#searchBtn').click(function() {
		BalanceManager.search();
	});
	
	//绑定删除提交事件
	$('#deleteSubmitBtn').click(function(){
		BalanceManager.remove();
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
