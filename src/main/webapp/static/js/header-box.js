
var HeaderBox = {
	isMouse : false,
	/**
	 * 鼠标移上去时初始化购物车数据
	 */
	initCart : function(){
		$.ajax({
			url : $('#contextPath').val() +"/cart/cartdata.html",
			data : {},
			type : 'POST',
			dataType : 'json',
			success : function(res) {
				HeaderBox.callbackCart(res);
			}
		});
	},
	
	/**
	 * 去结算
	 */
	submitOrder : function(){
		
		//构造form提交表单
		var form = $('<form></form>');
		form.attr('action', $('#contextPath').val() + "/order.html");
		form.attr('method', 'post');
		form.attr('target', '_self');
		$('body').append(form)
		form.submit();
	},
	
	removeProduct : function(id){
		$.ajax({
			url : $('#contextPath').val() +"/cart/update.html",
			data : {productId:id,type:'remove'},
			type : 'POST',
			dataType : 'json',
			success : function(res) {
				HeaderBox.callbackCart(res);
			}
		});
	},
	
	callbackCart : function(res){
		if(res.code == '0'){
			$('.shop_box').empty();
			$('.icon_shopCount').text(res.data.totalCount);
			var html = HeaderBox.createCartHtml(res.data.products,res.data.totalCount,res.data.totalScoreSale);
			$('.shop_box').html(html);
		}
	},
	
	createCartHtml : function(products,totalCount,totalScoreSale){
		if(!totalCount || totalCount <= 0){
			return '<div class="empty">购物车肚子很饿啦,赶紧选购吧</div>';
		}
		
		var html = '<div class="tit">积分兑换</div><div class="mall mall_point"><ul class="listBox">';
		$.each(products,function(){
			var product = this;
			var liHtml = '<li> '
					+ '<div class="name">'
					+ '<img src="' + product.productPicUrl + '" alt=""/>'
					+ product.productName
					+ '</div>'
					+ '<div class="r">'
					+ '<div class="price">' + product.productScoreSale + ' 分x' + product.productCount + '</div>'
					+ '<a href="javascript:void(0)" class="btn_remove" onclick="HeaderBox.removeProduct(' +product.productId + ')"></a>'
					+ '</div>'
					+ '</li>';
			html += liHtml;
		});
				html += '</div></div></ul><div class="collect">'
				+ '<div class="txt">'
				+ '	<span class="sum"><i class="num totals">' + totalScoreSale + '</i>分</span>'
				+ '共<span class="num totalnum">' + totalCount + '</span>件'
				+ '</div>'
				+ '<a href="'+$('#contextPath').val() +'/cart.html" target="_blank" class="btn_submit">去结算</a>';
		
		return html;
	}
};

$(function(){
	// 初始化购物车数据
	HeaderBox.initCart();
	
	//购物车商品显示
	$('.shopCar').mouseenter(function(){
		if(!HeaderBox.isMouse){
			HeaderBox.initCart();
			HeaderBox.isMouse = true;
		}
		
		$(this).addClass('hover');
	}).mouseleave(function(){
		$(this).removeClass('hover');
		HeaderBox.isMouse = false;
	});
	
	$('.searchred').click(function(){
		var url = $('#contextPath').val() + "/items.html";
		var seachwords = $('#seachwords').val();
		if(seachwords){
			url += "?k=" + seachwords;
		}
		window.location.href = url ;
	});
})