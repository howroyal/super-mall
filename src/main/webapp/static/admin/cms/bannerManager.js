//全局变量
//当前页面大小
var currentPageSize = 10;

/**
 * 验证对象
 */
var Valide = {
	
	/**
	 * 验证输入的时候URL是否合法
	 */
	valideURL : function(url){
		if(!url){
			return true;
		}
		if(url){
			return!!url.match(/^(http|https)\:\/\/+[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/);  
		}
	},
}

var BannerManager = {
		currentPageSize : 10,
		initTable : function(){
			$('table').bootstrapTable({
				method : 'post',
				url : contextPath + '/bannerManager/infos.html',
				contentType : 'application/x-www-form-urlencoded',
				minimumCountColumns : 3, // 设置最少显示列个数
				queryParams : BannerManager.queryParams,
				responseHandler : responseHandler,
				onLoadSuccess :BannerManager.onLoadSuccess,
				columns : [ {
					field : 'bannerName',
					title : '名称'
				},{
					field : 'pic_url',
					title : '图片',
					formatter : BannerManager.picFormatter
				},{
					field : 'url',
					title : '页面地址',
					formatter : BannerManager.urlFormatter
				}, {
					field : 'description',
					title : '描述'
				}, {
					field : 'state',
					title : '状态',
					formatter : BannerManager.statusFormatter
				}, {
					field : 'createTime',
					title : '创建时间'
				}, {
					field : 'updateTime',
					title : '更新时间'
				}, {
					field : 'operate',
					title : '操作',
					events: BannerManager.operateEvents,
					formatter : BannerManager.operationFormatter
				}
				]
			});
		},
		
		/**
		 * 选择文件后预览图片
		 */
		viewPic : function(picUrl){
			$(idName).attr('src', url).attr('title', picUrl);
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
			var bannerName = $('#bannerName').val();
			if(bannerName){
				dto.bannerName = bannerName;
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
			+"		<img height=40 width=100 alt='-' src='" + row.picUrl + "' />"
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
			var state = row.state;
			if(state == '0'){
				return edit.join('')
			}
			if(state == '1'){
				return edit.join('') + remove.join('');
			}else {
				return;
			}
		},
	
		/**
		 * 新增banner
		 */
		add : function(){
			//检测参数
			//检测页面url
			
			var bannerName = $('#inputBannerName').val();
			if(!bannerName){
				$('.addAlert').html('banner名称不能为空');
				$('.addAlert').removeClass('hide');
				return;
			}
			
			var url = $('#inputUrl').val();
			var urlV = Valide.valideURL(url);
			if(!urlV){
				$('.addAlert').html('请输入正确的URL,如http://www.zhaogang.com,https://vip.zhaogang.com');
				$('.addAlert').removeClass('hide');
				return;
			}
			
			//判断图片
			var filename = $('#picUrl').val();
			if(!filename){
				$('.addAlert').html('请输入图片地址');
				$('.addAlert').removeClass('hide');
				return;
			}
			
			$('#createBtn').addClass('disabled');
			var data = {
				bannerName : $('#inputBannerName').val(),
				url : $('#inputUrl').val(),
				state : $('#inputState').val(),
				picUrl : $('#picUrl').val(),
				description : $('#inputDescription').val()
			}; 
			
			$.ajax({
				url : contextPath + "/bannerManager/add.html",
	            type: 'POST',  
	            data: data,  
	            dataType: 'JSON',  
	            cache: false,  
				success: function(){
					$('#myModal').modal('hide');
					$('#addForm')[0].reset();
					$('#pic').attr("src","");
					$('.addAlert').addClass('hide');
					$('table').bootstrapTable('refresh');
					qiao.bs.msg("banner添加操作已成功!");
				},
				error: function(){
					$('#createBtn').removeClass('disabled');
					qiao.bs.msg("banner操作失败,请检查参数及网络设置!");
				}
			});
		},
		
		/**
		 * 预览效果
		 */
		preview : function(){
			$.ajax({
				url : contextPath + "/bannerManager/preview.html",
				type :"POST",
				dataType : "json",
				data: {},
				success: function(res){
					BannerManager.initPreivewModal(res);
				}
			});
		},

		/**
		 * 初始化banner预览窗口
		 */
		initPreivewModal : function(res){
			var total = res.total;
			if(total <= 0){
				$('#viewModalNon').modal('show');
				return;
			}
			
			var rows = res.rows;
			var innerHtml = '';
			var indicatorsHtml = '';

			$(rows).each(function(i,value){
				var active = "";
				if(i == 0){
					active = "active";
				}
				var temp = '<div class="item ' + active + '">'
					+ '<img alt="" src="' + value.picUrl + '"/>'
					+ '<div class="carousel-caption" contenteditable="true"></div>'
					+ '</div>';
				innerHtml += temp;
				
				var indTmp = '<li data-slide-to="' + i + '" data-target="#carousel-268003">&nbsp;</li>';
				indicatorsHtml += indTmp;
			})
			
			$('#data-slide-to').attr('data-slide-to', "" + total - 1 + "");
			$('.carousel-inner').html(innerHtml);
			$('.carousel-indicators').html(indicatorsHtml);
			$('#viewModal').modal('show');
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
			var id = $('#removeId').val();

			$.ajax({
				url : contextPath + "/bannerManager/remove.html",
				type :"POST",
				dataType : "json",
				data: {
					id : id
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
			
			
			
			$('#editSubmitBtn').addClass('disabled');
			var data = {
					id : $('#id').val(),
					bannerName : $('#editBannerName').val(),
					url : $('#editUrl').val(),
					state : $('#editState').val(),
					picUrl : $('#editPicUrl').val(),
					description : $('#editDescription').val()
				}; 
			
			$.ajax({
				url : contextPath + "/bannerManager/edit.html",
	            type: 'POST',  
	            data: data,  
	            dataType: 'JSON',  
	            cache: false,  
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
				$('#id').val(row.id);
				$('#editBannerName').val(row.bannerName);
				$('#editUrl').val(row.url);
				$('#editDescription').val(row.description);
				$('#editState').val(row.state);
				$('#editPicUrl').val(row.picUrl);
				$('#editpic').attr('src',row.picUrl);
				$('.editAlert').addClass('hide');
				$('#editSubmitBtn').removeClass('disabled');
				$('#editModal').modal('show');
			},

			/**
			 * 删除按钮
			 */
			'click .deleteBtn' : function(e, value, row, index){
				BannerManager.deleteB(row.id);
			}
		}
}


$(function(){
	BannerManager.initTable();

	// 查询按钮点击事件
	$('#searchBtn').click(function() {
		BannerManager.search();
	});
	
	// 绑定添加显示界面事件
	$('#addBtn').click(function() {
		
		$('#pic').attr("src","");
		$('#addForm')[0].reset();
		$('.addAlert').addClass('hide');
		$('#createBtn').removeClass('disabled');
		$('#myModal').modal('show');
	});
	// 绑定添加提交事件
	$('#createBtn').click(function() {
		var clazz = $(this).attr('class');
		if(clazz.indexOf('disabled') > 0){
			return;
		}
		BannerManager.add();
	});

	// 绑定预览事件
	$('#viewBtn').click(function() {
		BannerManager.preview();
	});
	
	//绑定编辑提交事件
	$('#editSubmitBtn').click(function(){
		var clazz = $(this).attr('class');
		if(clazz.indexOf('disabled') > 0){
			return;
		}
		BannerManager.edit();
	});
	
	//绑定删除提交事件
	$('#deleteSubmitBtn').click(function(){
		BannerManager.remove();
	});
	
	//绑定图片选择事件
	$('#picUrl').change(function(){
		$('#pic').attr("src",$(this).val());
	});
	//绑定编辑图片选择事件
	$('#editPicUrl').change(function(){
		$('#editpic').attr("src",$(this).val());
	});
	
	
//	$('#bannerPic').change(function(){
//		BannerManager.viewPic("#pic",this,$('.addAlert'));
//	});
//	
//	$('#editBannerPic').change(function(){
//		BannerManager.viewPic("#editpic",this,$('.editAlert'));
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
