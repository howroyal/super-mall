var Cart = {
	baseUrl : $('#contextPath').val(),
	
	/**
	 * 输入框改变事件
	 */
	inputChange : function(obj){
		var productCount = obj.val();
		var clazz = obj.attr('class');
		var productId = clazz.split(" ")[0];
		$.ajax({
			url:Cart.baseUrl + '/cart/update.html',
			type:'post',
			dataType:'json',
			data:{
				type:'updateCount',
				count : productCount,
				productId :productId
			},
			success: function(data){
				//更新本tr的数据
				//更新公共显示
				CallBack.setTrStyle(productId,data);
				CallBack.setCommonStytle(data);
			}
		});
	},
	
	/**
	 * 减数量
	 */
	doCut : function(obj){
		var clazz = obj.attr('class');
		if(clazz.indexOf('no') > 0){
			return;
		}
		var productId = obj.attr('value');
		var input = $('.' + productId);
		var count = input.val();
		if(count == 1){
			obj.attr('class','cut no iconsub');
			input.val(count);
			return;
		}
		
		count = count -1;
		//请求后台
		if(count <= 1){
			count = 1;
			obj.attr('class','cut no iconsub');
		}
		obj.next().next().attr('class','add iconsub');
		//input.val(count);
		
		$.ajax({
			url:Cart.baseUrl + '/cart/update.html',
			type:'post',
			dataType:'json',
			data:{
				type:'cut',
				productId :productId
			},
			success: function(data){
				//更新本tr的数据
				//更新公共显示
				CallBack.setTrStyle(productId,data);
				CallBack.setCommonStytle(data);
			}
		});
	},
	
	/**
	 * 加数量
	 */
	doAdd :  function(obj){
		var clazz = obj.attr('class');
		if(clazz.indexOf('no') > 0){
			return;
		}
		var productId = obj.attr('value');
		var input = $('.' + productId);
		var count = parseInt(input.val());
		var stock = $('.' + 'stock' + productId).html();
		if(count == stock){
			input.val(count);
			obj.attr('class','add no iconsub');
			return;
		}
		if(count == 1){
			obj.prev().prev().attr('class','cut iconsub');
		}
		count +=1;
		if(count >= stock){
			obj.attr('class','add no iconsub');
		}
		//input.val(count);
		
		$.ajax({
			url:Cart.baseUrl + '/cart/update.html',
			type:'post',
			dataType:'json',
			data:{
				type:'add',
				productId :productId
			},
			success: function(data){
				//更新本tr的数据
				
				//更新公共显示
				CallBack.setTrStyle(productId,data);
				CallBack.setCommonStytle(data);
			}
		});
	},	
	
	/**
	 * 删除商品
	 */
	doDelete : function(obj){
		//请求后台
		var productId = obj.attr('value');
		if(!productId){
			return;
		}
		
		$.ajax({
			url:Cart.baseUrl + '/cart/update.html',
			type:'post',
			dataType:'json',
			data:{
				type:'remove',
				productId :productId
			},
			success: function(data){
				//删除tr节点
				obj.parent().parent().remove();
				//更新公共显示
				CallBack.setCommonStytle(data);
				//关闭弹出框
				$('.mask, .popup').remove();
				
			}
		});
	},
	
	/**
	 * 全选
	 */
	doAllCheck : function(obj){
		var clazz = obj.attr('class');
		var status = true;
		var singleClazz = "single iconsub checkbox check";
		if(clazz.indexOf('allcheck iconsub checkbox check') == 0){
			status = false;
			singleClazz = 'single iconsub checkbox';
		}
		
		//请求后台
		$.ajax({
			url:Cart.baseUrl + '/cart/update.html',
			type:'post',
			dataType:'json',
			data:{
				type:'check',
				status : status
			},
			success: function(data){
				$('.single').attr('class',singleClazz);
				CallBack.setCommonStytle(data);
			}
		});
	},
	
	/**
	 * 单选
	 */
	doSingleCheck : function(obj){
		var clazz = obj.attr('class');
		var productId = obj.attr('value');
		var status = true;
		if(clazz.indexOf('single iconsub checkbox check') == 0){
			status = false;
			clazz = 'single iconsub checkbox';
		}else{
			status = true;
			clazz = 'single iconsub checkbox check';
		}
		
		$.ajax({
			url:Cart.baseUrl + '/cart/update.html',
			type:'post',
			dataType:'json',
			data:{
				type:'check',
				status : status,
				productId : productId
			},
			success: function(data){
				obj.attr('class',clazz);
				CallBack.setCommonStytle(data);
			}
		});
	},
	
	/**
	 * 去订单提交页面
	 */
	toOrder : function(){
		
		//生成form表单并提交
		var url = Cart.baseUrl + "/order.html";
		var form = $('<form></form>');
		form.attr('action', url);
		form.attr('method', 'post');
		form.attr('target', '_self');
		
		$('body').append(form);
		form.submit();
	},
	
	/**
	 * 提交检测
	 */
	doCheck : function(obj){
		if(obj){
			var clazz = obj.attr('class');
			if(clazz.indexOf('no') > 0){
				return;
			}
		}
		$.ajax({
			url:Cart.baseUrl + '/cart/check.html',
			type:'post',
			dataType:'json',
			data:{},
			beforeSend:function(){
				$('#loading-mask').show();
				$('#loading').show();
			},
			error : function(){
				$('#loading-mask').hide();
				$('#loading').hide();
				$('#alertDialogText').html('<i class="iconsub"></i>操作失败,请检查网络设置');
				new UIpopup({
					width:600,
					height:320,
					paddingTB: '0',
					paddingLR: '0',
					template: $("#mask_nologin").html()
				});

				$('.popup #edit_submit').on('click',function(){
					$('.mask, .popup').remove();
				})
			},
			
			success: function(data){
				if(data){
					var code = data.code;
					//检测成功 页面跳转
					if(code == 'iFcs7cO3uUU='){
						Cart.toOrder();
					}
					else {
						$('#loading-mask').hide();
						$('#loading').hide();
						if(code == '-3'){
							//未登录
							$('#btn_login').trigger("click", [function() {Cart.toOrder();}]);
						}else {
							var text = "暂时无法兑换,请修改商品或者联系客服";
							if(code == '-1'){
								//未选择商品
								text = '未选择商品,请选择商品';
							}
							if(code == '-2'){
								//库存不足
								text =  data.data.productName + '库存不足,请修改商品数量或选择其他商品';
							}
							if(code == '-4'){
								//积分不够
								text = '您的可用积分不足,请修改商品数量或选择其他商品';
							}
							if(code == '-5'){
								//积分不够
								text =  data.data.productName + '已下架,请选择其他商品';
							}

							$('#alertDialogText').html('<i class="iconsub"></i>' + text);
							new UIpopup({
								width:600,
								height:320,
								paddingTB: '0',
								paddingLR: '0',
								template: $("#mask_nologin").html()
							});

							$('.popup #edit_submit').on('click',function(){
								$('.mask, .popup').remove();
							})
						}
					}
				}
			}
		});
	}
}

/**
 * 回调函数
 */
var CallBack = {
		
		/**
		 * 设置样式
		 */
		setTrStyle : function(productId,data){
			if(data && data.data && data.data.products){
				var products = data.data.products;
				$.each(products,function(i,product){
					if(productId ==  product.productId){
						var stock = product.productStock;
						var productCount = product.productCount;
						var productScoreSale = product.productScoreSale;
						
						//单选选择框
						$('.single').each(function(){
							var $value = $(this).attr('value');
							if($value == productId){
								$(this).attr('class','single iconsub checkbox check');
							}
						});
						
						//可卖量设置
						$('.stock' + productId).html(stock);
						//数量设置
						$('.' + productId).val(productCount);
						//积分的设置
						$('.score' + productId).html(productCount * productScoreSale);
						
						//加减的设置
						$('.cut').each(function(){
							var $value = $(this).attr('value');
							if($value == productId){
								if(productCount <= 1){
									$(this).attr('class','cut no iconsub')
								}else {
									$(this).attr('class','cut iconsub')
								}
							}
						});
						
						$('.add').each(function(){
							var $value = $(this).attr('value');
							if($value == productId){
								if(productCount >= stock){
									$(this).attr('class','add no iconsub')
								}else {
									$(this).attr('class','add iconsub')
								}
							}
						});
					}
				});
			}
		},
		
		/**
		 * 设置公共样式
		 */
		setCommonStytle: function(data){
			if(data && data.code == '0'){
				var isAllChecked = data.data.allChecked;
				var totalCount = data.data.totalCount;
				var totalScoreSale = data.data.totalScoreSale
				this.setTotalCount(totalCount);
				this.setTotalScoreSale(totalScoreSale);
				this.setAllcheck(isAllChecked);
			}
		},
		
		/**
		 * 设置总数
		 */
		setTotalCount : function(totalCount){
			$('.totalCount').html(totalCount);
			if(totalCount <= 0){
				$('.btn').attr('class','btn no');
			}else {
				$('.btn').attr('class','btn');
			}
		},
		
		/**
		 * 设置总积分
		 */
		setTotalScoreSale : function(totalScoreSale){
			$('.totalScoreSale').html(totalScoreSale);
		},
		
		/***
		 * 设置全选样式
		 */
		setAllcheck : function(isAllChecked){
			var clazz = 'allcheck iconsub checkbox check';
			if(!isAllChecked){
				clazz = 'allcheck iconsub checkbox';
			}
			$('.allcheck').attr('class',clazz);
		}
}

$(function(){
	var top = new goTop({
		showPhone: false,      //是否显示联系方式
		showQQ: false          //是否显示在线客服
	});
	
	//绑定加减数量按钮
	$('.cut').on('click',function(){
		Cart.doCut($(this));
	});
	
	$('.add').on('click',function(){
		Cart.doAdd($(this));
	});
	
	//绑定删除
	$('.delete').on('click',function(){
		
		//确认框哦
		var popup = new UIpopup({
			width:600,
			height:320,
			paddingTB: '0',
			paddingLR: '0',
			template: $("#confirm").html()
		});
		var $this = $(this);
		//确认
		$('.popup #edit_submit').on('click',function(){
			Cart.doDelete($this);
		})
		//取消
		$('.popup #edit_cancel').on('click',function(){
			$('.mask, .popup').remove();
		})
		
		//Cart.doDelete($(this));
	});
	
	//绑定全选
	$('.allcheck').on('click',function(){
		Cart.doAllCheck($(this));
	});
	
	//绑定单个选择
	$('.single').on('click',function(){
		Cart.doSingleCheck($(this));
	});
	
	//绑定提交
	$('.btn').on('click',function(){
		Cart.doCheck($(this));
	});
	
	//绑定输入框改变事件
	$('.countInput').on('change',function(){
		Cart.inputChange($(this));
	});
	
	//更新可用积分
	$.ajax({
		url:Cart.baseUrl + '/cart/userscore.html',
		type:'post',
		dataType:'json',
		data:{},
		success: function(data){
			if(data && data.code == '0'){
				$('.num').html('您的可用余额: ' + data.data + ' 元');
			}
		}
	});
	
})

