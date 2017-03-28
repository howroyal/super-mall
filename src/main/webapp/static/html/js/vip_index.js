var mallHome = {
    //幻灯片
    picTab: function(){
        if($('.banner .slides_container').find('div').length>1){
            $('.banner .slides_container').slidesjs({
                height: 300,
                navigation: {
                    active:false
                },
                play: {
                    active: false,
                    auto: true,
                    interval: 3000,
                    swap: true,
                    pauseOnHover: true,
                    restartDelay: 3000
                }
            });
        }
    },
	countDown: function (time,day_elem,hour_elem,minute_elem,second_elem){
		var end_time = new Date(time).getTime(),//月份是实际月份-1
			sys_second = (end_time-new Date().getTime())/1000;
		var timer = setInterval(function(){
			if (sys_second > 0) {
				sys_second -= 1;
				var day = Math.floor((sys_second / 3600) / 24);
				var hour = Math.floor((sys_second / 3600) % 24);
				var minute = Math.floor((sys_second / 60) % 60);
				var second = Math.floor(sys_second % 60);
				$(day_elem).text(day<10?"0"+day:day);//计算天
				$(hour_elem).text(hour<10?"0"+hour:hour);//计算小时
				$(minute_elem).text(minute<10?"0"+minute:minute);//计算分
				$(second_elem).text(second<10?"0"+second:second);// 计算秒
			} else { 
				clearInterval(timer);
			}
		}, 1000);
	}
}

$(function(){
	var top = new goTop();
	
	mallHome.picTab();
	mallHome.countDown("2015/11/13 11:00:00",".times",".time .hour",".time .minute",".time .second");
	$("#login").on("click",function(){
		$(this).parent().hide().next().show();
	});
	
	
	$('.shopCar').mouseenter(function(){
		$(this).addClass('hover');
	}).mouseleave(function(){
		$(this).removeClass('hover');
	});
	
	$(".list .white").click(function() {
		var popup = new UIpopup({
			width:600,
			height:320,
			paddingTB: '0',
			paddingLR: '0',
			template: $("#mask_success").html()
		});
	});
	$(".bound .one a").click(function() {
		var popup = new UIpopup({
			width:600,
			height:320,
			paddingTB: '0',
			paddingLR: '0',
			template: $("#mask_nologin").html()
		});
	});
	$(".list .red").click(function() {
		var popup = new UIpopup({
			width:600,
			height:320,
			paddingTB: '0',
			paddingLR: '0',
			template: $("#mask_nointegral").html()
		});
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
})

