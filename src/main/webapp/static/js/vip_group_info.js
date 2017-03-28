$(function(){

	//图片的延迟加载
	$("img.lazy").lazyload({
		threshold : 200,
	    effect : "fadeIn"
	});
	
	$(".intro").on("click",".introtab a",function(){
		var _num = $(this).index();
		//载入评论
		if(_num == 1){
			$('#commentsFrame').attr("src",$('#contextPath').val() + "/mallCommentary/index.html");
		}
		$(this).addClass("active").siblings().removeClass("active");
		$(".intro").find(".introbox").eq(_num).show().siblings(".introbox").hide();
	});
	
	//购物车商品显示
	$('.shopCar').mouseenter(function(){
		$(this).addClass('hover');
	}).mouseleave(function(){
		$(this).removeClass('hover');
	});
	
	//加数量
	$('.add').click(function(){
		var buyCount  = parseInt($('#buyCount').val());
		$('#buyCount').val(buyCount + 1);
		$('.cut').removeClass('no');
	});
	
	//减数量
	$('.cut').click(function(){
		if($(this)[0].className.contains('no')){
			return;
		}
		var buyCount  = parseInt($('#buyCount').val());
		buyCount = buyCount - 1;
		if(buyCount <= 1){
			$('.cut').addClass('no');
		}
		$('#buyCount').val(buyCount);
	});
	
	//立即兑换
	$('.order').click(function(){
		//直接跳转到订单页面
		//构造form提交表单
		var goodsId = $(this).parent().attr('class').split(' ')[2];
		var form = $('<form></form>');
		form.attr('action', $('#contextPath').val() + "/order.html");
		form.attr('method', 'post');
		form.attr('target', '_blank');
		var goodsIdinput = $('<input type="text" name="productId" />');
		goodsIdinput.attr('value', goodsId);
		var countinput = $('<input type="text" name="count" />');
		countinput.attr('value', $('#buyCount').val());
		form.append(goodsIdinput).append(countinput)
		$('body').append(form)
		form.submit();
	});
	
	//加入购物车
	$('.addCart').click(function(){
		var goodsId = $(this).parent().attr('class').split(' ')[2];
		$.ajax({
			url : $('#contextPath').val() + '/cart/addToCart.html',
			type : 'POST',
			dataType : 'json',
			data : {
				id : goodsId,
				count : $('#buyCount').val()
			},
			success : function(res) {
				var popup = new UIpopup({
					width:600,
					height:320,
					paddingTB: '0',
					paddingLR: '0',
					template: $("#mask_success").html()
				});
				$('.popup .white').click(function(){
					$('.mask, .popup').remove();
				});
			}
		});
	});
	
})