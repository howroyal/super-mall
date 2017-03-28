var mSecond = 60, 
	oClass = "send",
	cSecond = mSecond,
	timerID;

function sendCode(btn) {
	if( !btn.hasClass(oClass) ){
		btn.addClass(oClass).text(cSecond +"秒后重新获取");
		timerID = setInterval(function() {
			if (--cSecond > 0) {
				btn.text(cSecond +"秒后重新获取");
			}
			else {
				clearInterval(timerID);
				cSecond = mSecond;
				btn.removeClass(oClass).text("获取验证码");
			}
		}, 1000);
	}
};

$(function(){
	var top = new goTop({
		showPhone: false,      //是否显示联系方式
		showQQ: false          //是否显示在线客服
	});
	$("#open").click(function() {
		var popup = new UIpopup({
			width:860,
			height:320,
			paddingTB: '50',
			paddingLR: '70',
			template: $("#mask_ads").html()
		});
	});	


	$("#code").click(function() {
		sendCode($(this));
		$(".code span").show();
	});
	$("#codetext").on("keyup",function(){
		$(this).val() == "" ? $(".btn a").addClass("no") : $(".btn a").removeClass("no") ;
	});
	
	
	$(".order_info").on("click",".way",function(){
		var num = $(this).index();
		$(this).addClass("active").siblings(".way").removeClass("active");
		$(".adsbox").eq(num).show().siblings(".adsbox").hide();
	});
	$(".ads").on("click",".one .text",function(){
		$(this).parent().addClass("active").siblings().removeClass("active");
	});
})

