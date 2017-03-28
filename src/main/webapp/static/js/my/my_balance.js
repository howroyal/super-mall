var MyBalance = {
	baseUrl : $('#contextPath').val(),
	paging : function(type){
		var i =  $('#prod_page_num').attr('current');
		var s =  $('#prod_page_num').attr('s');
		var pgcount = $('#prod_page_num').attr('pgcount');
		if(type == 'pre'){
			if(i == '1'	){
				return;
			}
			i = parseInt(i) - 1;
		}
		
		if(type == 'next'){
			if(i == pgcount	){
				return;
			}
			i = parseInt(i) + 1;
		}
		if(type == 'goto'){
			var pagenum = $('#pagenum').val();
			if(!pagenum || isNaN(parseInt(pagenum))){
				return;
			}
			if(i == pagenum	){
				return;
			}
			
			if(parseInt(pagenum) > parseInt(pgcount) || parseInt(pagenum) < 1){
				return;
			}
			
			i = parseInt(pagenum);
		}
		
		var params = "i=" + i;
		if(s){
			params += "&s=" +s;
		}
		window.location.href = MyBalance.baseUrl + "/my/balance.html?" + params;
	},
	
	recharge : function(){
		var payWay = $('.popup #payWay').val();
		var rechargeMoney = $('.popup #rechargeMoney').val();
		var payAccount = $('.popup #payAccount').val();
		var payInfo = $('.popup #payInfo').val();
		
		$.ajax({
			url:MyBalance.baseUrl + '/recharge/add.html',
			type:'post',
			dataType:'json',
			data:{
				payWay : payWay,
				rechargeMoney : rechargeMoney,
				payAccount : payAccount,
				payInfo : payInfo
			},
			success: function(resp){
				var success = resp.success;
				if(success){
					$('.mask, .popup').remove();
					alert('成功');
				}else {
					alert('失败');
				}
			}
		});
	}
};

$(function(){
	var top = new goTop({
		showPhone: true,      //是否显示联系方式
		showQQ: true          //是否显示在线客服
	});
	
	//支付信息填写
	$('#recharge').on('click',function(){
		//支付信息填写框
		var popup = new UIpopup({
			width:500,
			height:380,
			paddingTB: '0',
			paddingLR: '0',
			template: $("#mask_ads_edit").html()
		});
		var $this = $(this);
		
		//确认 充值
		$('.popup #recharge_submit').on('click',function(){
			MyBalance.recharge();
		})
		//取消
		$('.popup #recharge_cancel').on('click',function(){
			$('.mask, .popup').remove();
		})
		
		//Cart.doDelete($(this));
	});
	
	//上一页
	$('#pre').on('click',function(){
		MyBalance.paging('pre');
	});
	//下一页
	$('#next').on('click',function(){
		MyBalance.paging('next');
	});
	//跳转
	$('.goto').on('click',function(){
		MyBalance.paging('goto');
	});
	
});