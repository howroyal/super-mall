var MyOrder = {
	baseUrl : $('#contextPath').val()
};

$(function(){
	var top = new goTop({
		showPhone: false,      //是否显示联系方式
		showQQ: false          //是否显示在线客服
	});
	
	//支付信息填写
	$('.payBtn').on('click',function(){
		
		//确认框哦
		var popup = new UIpopup({
			width:480,
			height:330,
			paddingTB: '0',
			paddingLR: '0',
			template: $("#mask_ads_edit").html()
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
	
});