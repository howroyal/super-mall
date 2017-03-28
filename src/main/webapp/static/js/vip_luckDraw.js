var luckDraw = {
	init: function(){
		luckDraw.header();
		luckDraw.scrollList();
		luckDraw.arrowPopupClose();
		luckDraw.recordBtn();
	},

	//公共头部
	header: function(){
		$('.msn').mouseenter(function(){
			$(this).addClass('hover');
		}).mouseleave(function(){
			$(this).removeClass('hover');
		})

		$('.shopCar').mouseenter(function(){
			$(this).addClass('hover');
		}).mouseleave(function(){
			$(this).removeClass('hover');
		});
	},

	//中奖名单滚动
	scrollList: function(){
		$('.award_list').myScroll({
			speed: 50,
			rowHeight: 34
		});
	},

	//抽奖随机数
	rnd: function(n, m){
		return Math.floor(Math.random() * (m - n + 1) + n)
	},

	//触发弹层
	showPopup: function(awards,targetDom){
		$('.mask_popup').show();
		$('.arrow_popup').find('.content').html(targetDom);
		if(awards == 0){
			$('.arrow_popup').find('.content').removeClass('win');
		} else {
			$('.arrow_popup').find('.content').addClass('win');
		}
		$('.arrow_popup').show();
	},

	//弹出层关闭按钮
	arrowPopupClose: function(){
		$('.close').click(function(){
			luckDraw.closePopup();
		})
	},

	//关闭弹层
	closePopup: function(){
		$('.arrow_popup').hide();
		$('.mask_popup').hide();
	},

	//查看中奖纪录
	recordBtn: function(){
		$('.btn_record').click(function(){
			var isWin = false; //是否中奖过
			if(isWin){
				var _temp = '<h3>我的中奖纪录</h3><img src="img/noneWinning.png" alt=""/><p>还没有抽中礼品哦~</p><a href="javascript:void(0)" class="btns btn_next">再抽一次</a>';
			} else {
				var _temp = '<h3>我的中奖纪录</h3><div class="record_list_box"><div class="record_list_table"><table cellspacing="0" cellpadding="0" width="100%"><tr><td>积分+8</td><td>2014/10/14 15:08:56</td></tr><tr><td>积分+8</td><td>2014/10/14 15:08:56</td></tr><tr><td>积分+8</td><td>2014/10/14 15:08:56</td></tr><tr><td>积分+8</td><td>2014/10/14 15:08:56</td></tr><tr><td>积分+8</td><td>2014/10/14 15:08:56</td></tr><tr><td>积分+8</td><td>2014/10/14 15:08:56</td></tr><tr><td>积分+8</td><td>2014/10/14 15:08:56</td></tr><tr><td>积分+8</td><td>2014/10/14 15:08:56</td></tr><tr><td>积分+8</td><td>2014/10/14 15:08:56</td></tr></table></div></div>';
			}
			luckDraw.showPopup('',_temp);
		})
	}
}

$(function(){
	luckDraw.init();

	var rotateTimeOut = function (){
		$('#rotate').rotate({
			angle:0,
			animateTo:2160,
			duration:8000,
			callback:function (){
				var _temp = '<h3>服务器超时</h3><img src="img/noWinning.png" alt=""/>';
				luckDraw.showPopup(0,_temp);
			}
		});
	};
	var rotateFailure = function (){
		var _temp = '<h3>抱歉！您的积分不够~</h3><img src="img/noWinning.png" alt=""/>';
		luckDraw.showPopup(0,_temp);
	};
	var bRotate = false;

	var rotateFn = function (awards, angles, dom){
		bRotate = !bRotate;
		$('#rotate').stopRotate();
		$('#rotate').rotate({
			angle:0,
			animateTo:angles+1800,
			duration:8000,
			callback:function (){
				luckDraw.showPopup(awards,dom);
				bRotate = !bRotate;
			}
		})
	};

	$('.btn_start').click(function (){
		if(bRotate)return;
		var item = luckDraw.rnd(0,9);

		switch (item) {
			case 0:
				var _temp = '<h3>恭喜您~抽中了找钢台历</h3><img src="img/award/award_10.jpg" alt=""/><p>中奖信息已记录，工作人员会在2个工作日内联系您，请耐心等待~</p><a href="javascript:void(0)" class="btns btn_next">再抽一次</a>';
				rotateFn(0, 342, _temp);
				break;
			case 1:
				var _temp = '<h3>恭喜您~抽中了iphone6s</h3><img src="img/award/award_1.jpg" alt=""/><p>中奖信息已记录，工作人员会在2个工作日内联系您，请耐心等待~</p><a href="javascript:void(0)" class="btns btn_next">再抽一次</a>';
				rotateFn(1, 18, _temp);
				break;
			case 2:
				var _temp = '<h3>恭喜您~抽中了28积分</h3><img src="img/award/award_2.jpg" alt=""/><p>中奖信息已记录，工作人员会在2个工作日内联系您，请耐心等待~</p><a href="javascript:void(0)" class="btns btn_next">再抽一次</a>';
				rotateFn(2, 54, _temp);
				break;
			case 3:
				var _temp = '<h3>恭喜您~抽中了找钢金猫</h3><img src="img/award/award_3.jpg" alt=""/><p>中奖信息已记录，工作人员会在2个工作日内联系您，请耐心等待~</p><a href="javascript:void(0)" class="btns btn_next">再抽一次</a>';
				rotateFn(3, 90, _temp);
				break;
			case 4:
				var _temp = '<h3>恭喜您~抽中了5积分</h3><img src="img/award/award_4.jpg" alt=""/><p>中奖信息已记录，工作人员会在2个工作日内联系您，请耐心等待~</p><a href="javascript:void(0)" class="btns btn_next">再抽一次</a>';
				rotateFn(4, 126, _temp);
				break;
			case 5:
				var _temp = '<h3>恭喜您~抽中了胖猫公仔</h3><img src="img/award/award_5.jpg" alt=""/><p>中奖信息已记录，工作人员会在2个工作日内联系您，请耐心等待~</p><a href="javascript:void(0)" class="btns btn_next">再抽一次</a>';
				rotateFn(5, 162, _temp);
				break;
			case 6:
				var _temp = '<h3>恭喜您~抽中了18积分</h3><img src="img/award/award_6.jpg" alt=""/><p>中奖信息已记录，工作人员会在2个工作日内联系您，请耐心等待~</p><a href="javascript:void(0)" class="btns btn_next">再抽一次</a>';
				rotateFn(6, 198, _temp);
				break;
			case 7:
				var _temp = '<h3>恭喜您~抽中了找钢保温杯</h3><img src="img/award/award_7.jpg" alt=""/><p>中奖信息已记录，工作人员会在2个工作日内联系您，请耐心等待~</p><a href="javascript:void(0)" class="btns btn_next">再抽一次</a>';
				rotateFn(7, 234, _temp);
				break;
			case 8:
				var _temp = '<h3>恭喜您~抽中了8积分</h3><img src="img/award/award_8.jpg" alt=""/><p>中奖信息已记录，工作人员会在2个工作日内联系您，请耐心等待~</p><a href="javascript:void(0)" class="btns btn_next">再抽一次</a>';
				rotateFn(8, 270, _temp);
				break;
			case 9:
				var _temp = '<h3>恭喜您~抽中了找钢U盘</h3><img src="img/award/award_9.jpg" alt=""/><p>中奖信息已记录，工作人员会在2个工作日内联系您，请耐心等待~</p><a href="javascript:void(0)" class="btns btn_next">再抽一次</a>';
				rotateFn(9, 306, _temp);
				break;
		}
	});

	$('body').on('click', '.btn_next', function(){
		luckDraw.closePopup();
		$('.btn_start').trigger('click');
	})

	var top = new goTop();
})


