var Group = {
		/**
		 * 上下文环境
		 */
		contextPath : $('#contextPath').val(),
		/**
		 * 取得本page内容
		 */
		pageUrl : function(){
			var url = this.contextPath + "/items";
			if($('#categoryId').val()){
				url += "/"+ $('#categoryId').val();
			}
			url += ".html";
			return url;
		},
		
		/**
		 * 执行分页操作
		 */
		dopage : function(){
			var url = Group.pageUrl();
			var cp = $('#cp').val();
			var sf = $('#sf').val();
			var st = $('#st').val();
			var pn = $('#pn').val();
			var min = $('#min').val();
			var max = $('#max').val();
			url += '?cp=' + cp;
			
			if(sf){
				url += '&sf=' + sf;
			}
			if(st){
				url += '&st=' + st;
			}
			if(pn){
				url += '&pn=' + pn;
			}
			if(min){
				url += '&min=' + min;
			}
			if(max){
				url += '&max=' + max;
			}
			
			window.location.href = url;
		}
};

$(function(){
	//图片的延迟加载
	$("img.lazy").lazyload({
		threshold : 200,
	    effect : "fadeIn"
	});
	
	$(".range").hover(
		function(){
			$(this).addClass("active");
			$(this).children(".more").show();
		},
		function(){
			$(this).removeClass("active");
			$(this).children(".more").hide();
		}
	);
	
	/**
	 * 点击排序
	 */
	$(".sorts > li > a").on("click",function(){
		
		var text  = $(this).text();
		var sort = $(this).find('.iconsub').attr('class');
		
		var sf = "";
		var st = "d";
		if(sort){
			if(text == '销量'){
				sf = "sc";
			}
			if(text == '价格'){
				sf = "pc";
			}
			if(text == '上架时间'){
				sf = "st";
			}
			
			var sortType = sort.split(' ')[1];
			if(sortType){
				if(sortType == 'down'){
					st = 'a';
				}else {
					st = 'd';
				}
			}
		}
		
		window.location.href = Group.pageUrl()+"?sf="+sf+"&st="+st;
	});
	
	//购物车商品显示
	$('.shopCar').mouseenter(function(){
		$(this).addClass('hover');
	}).mouseleave(function(){
		$(this).removeClass('hover');
	});
	
	//价格区间确定按钮
	$('.priceArea').click(function(){
		var priceMin = $('#priceMin').val();
		var priceMax = $('#priceMax').val();
		if(priceMin == $('#min').val()  && priceMax == $('#max').val()){
			$(".more").hide();
			return;
		}
		
		$('#min').val(priceMin);
		$('#max').val(priceMax);
		Group.dopage();
	});
	
	//价格区间选择
	$('.txt > a').click(function(){
		var priceArea = $(this).attr('title').split('-');
		$('#priceMin').val(priceArea[0]);
		if(priceArea[1]){
			$('#priceMax').val(priceArea[1]);
		}else {
			$('#priceMax').val('');
		}
	});
	
	//清除价格区间
	$('.clear').click(function(){
		$('#priceMin').val('');
		$('#priceMax').val('');
	});
	
	/**
	 * 简单分页
	 * 
	 */
	$('.page_simple > a').click(function(){

		var cp = parseInt($('#cp').val());
		var curpage = 1;
		
		if($(this)[0].text == '<'){
			if(cp == 1){
				return;
			}
			curpage = cp - 1;
		}else {
			curpage = cp + 1;
			if(curpage > $('#pagenum').val()){
				return;
			}
		}
		$('#cp').val(curpage);
		Group.dopage();
	});
	
	/**
	 * 分页
	 */
	$('.pkg_page > a').click(function(){
		var clazz = $(this)[0].className;
		if(clazz.contains('nocurrent') || clazz.contains('current')){
			return;
		}
		
		var curpage = $(this)[0].text;
		var cp = parseInt($('#cp').val());
		if(curpage == '上一页'){
			curpage = cp - 1;
		}else if(curpage == '下一页'){
			curpage = cp + 1;
		}
		
		$('#cp').val(curpage);
		Group.dopage();
	});
})

