$(function(){
	var top = new goTop();
	
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
	
	$(".sorts").on("click","label",function(){
		$(this).children(".iconsub").toggleClass("active");
	});
})

