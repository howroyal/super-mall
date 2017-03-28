$(function(){
	var top = new goTop();
	alert('ss');
	$(".intro").on("click",".introtab a",function(){
		var _num = $(this).index();
		$(this).addClass("active").siblings().removeClass("active");
		$(".intro").find(".introbox").eq(_num).show().siblings(".introbox").hide();
	});
})
