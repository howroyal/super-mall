
var Index = {
		changeNum : 1,
		changeitemsUrl :  $('#contextPath').val() + "/changeitems.html",
		orderUrl : $('#contextPath').val() + "/order.html",
		cartUrl : $('#contextPath').val() + '/cart.html',
		addCartUrl : $('#contextPath').val() + '/cart/addToCart.html',
		
		/**
		 * 换一换
		 */
		changeItems : function(){
			$.ajax({
				url : Index.changeitemsUrl,
				data : {
					curPage : Index.changeNum
				},
				type : 'POST',
				dataType : 'json',
				success : function(res) {
					CallBack.changeItemsCallBack(res);
				}
			});
		},
		
		doOrder : function(obj){
			//直接跳转到订单页面
			//构造form提交表单
			var goodsId = obj.parent().attr('class').split(' ')[2];
			var form = $('<form></form>');
			form.attr('action', Index.orderUrl);
			form.attr('method', 'post');
			form.attr('target', '_blank');
			var goodsIdinput = $('<input type="text" name="productId" />');
			goodsIdinput.attr('value', goodsId);
			form.append(goodsIdinput)
			$('body').append(form)
			form.submit();
		},
		
		/**
		 * 跳转到订单页面
		 */
		toOrder : function(obj){
			$.ajax({
				url : $('#contextPath').val() + "/new/check.html",
				data : {},
				type : 'POST',
				dataType : 'json',
				success : function(res) {
					if (res.success) {
						Index.doOrder(obj);
					} else {
						//登录后回调订单页面
						$('#btn_login').trigger("click",[function(){Index.doOrder(obj)}]);
						//HeaderTopbar.doLogin();
					}
				}
			});
		},
		
		/**
		 * 加入购物车
		 */
		addToCart : function(obj){
			var goodsId = obj.parent().attr('class').split(' ')[2];
			$.ajax({
				url : Index.addCartUrl,
				type : 'POST',
				dataType : 'json',
				data : {
					id : goodsId,
					count : 1
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
					$('.icon_shopCount').text(res.data.totalCount);
				}
			});
		}
};

var CallBack = {
		/**
		 *	换一换回调函数
		 */
		changeItemsCallBack : function(res){
			if(res && res.data){
				Index.changeNum = res.curPage + 1;
				//清空原有html
				$('.changelist').empty();
				var html = '';
				$.each(res.data,function(i,v,index){
					var even = "";
					if(i %2 == 0){
						even = "even";
					}
					html 	+= '<a href="item/'+ v.id + '.html" target="_blank">'
							+ '<div class="one ' + even + '">'
							+ '<img class="lazy" alt="' + v.name + '" width="72px" height="72px" data-original="' + v.mainImg + '" src="' + v.mainImg + '">'
							+ '<h4 class="elli">' + v.name + '</h4>'
							+ '<p>优惠价<b>' + v.price + '</b></p></div>'
							+ '</a>';
				});
				$('.changelist').html(html);
			}
		}
};


var mallHome = {
    //幻灯片
    picTab: function(){
        if($('.banner .slides_container').find('div').length>1){
            $('.banner .slides_container').slidesjs({
                height: 300,
                navigation: {
                    active:true
                },
                play: {
                    active: true,
                    auto: true,
                    interval: 3000,
                    swap: true,
                    pauseOnHover: true,
                    restartDelay: 3000
                }
            });
        }
    },
    newTab: function(){
		if($('.box .slides_container').find('.list_box').length>1){
			$('.box .slides_container').slidesjs({
				width:1180,
				height: 351,
				navigation: {
					active:true
				},
				pagination: {
					active:false
				},
				play: {
					active: false,
					auto: true,
					interval: 5000,
					swap: true,
					pauseOnHover: true,
					restartDelay: 5000
				}
			});
		}
	}
}



$(function(){
	//图片的延迟加载
	$("img.lazy").lazyload({
		threshold : 200,
	    effect : "fadeIn"
	});
	
	mallHome.picTab();
	mallHome.newTab();
	$("#login").on("click",function(){
		$(this).parent().hide().next().show();
	});
	
	//加入购物车
	$(".list .white").click(function() {
		Index.addToCart($(this));
	});
	
//	//抢购
//	$(".bound .one a").click(function() {
//		var popup = new UIpopup({
//			width:600,
//			height:320,
//			paddingTB: '0',
//			paddingLR: '0',
//			template: $("#mask_nologin").html()
//		});
//	});
	
	//立即购买
	$(".list .red").click(function() {
		//登录后跳转到订单页
		Index.toOrder($(this));
	});
	
	$(".bound .title a").click(function() {
		var popup = new UIpopup({
			width:600,
			height:320,
			paddingTB: '0',
			paddingLR: '0',
			template: $("#mask_rule").html()
		});
	});
	
	/**
	 * 换一换
	 */
	$('.change').click(function(){
		Index.changeItems();
	});
})

