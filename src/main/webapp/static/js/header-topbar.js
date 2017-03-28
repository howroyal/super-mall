
var HeaderTopbar = {

	//刷新验证码
	refresh : function(){
		$('.popup #img').attr('src',$('#contextPath').val() +"/drawImage.html?" + Math.random());
	},
	/**
	 * 检查是否登录
	 */
	checkLogin : function() {
		$.ajax({
			url : $('#contextPath').val() + "/new/check.html",
			data : {},
			type : 'POST',
			dataType : 'json',
			success : function(res) {
				if (res.success) {
					HeaderTopbar.loginCallback(res.info);
				} else {
					console.log('no login');
				}
			}
		});
	},
	
	doLogin: function(callback){
		var userName= $(".popup #txt_loginmobile").val();
		var password = $(".popup #txt_loginpwd").val();
		var validatecode = $(".popup #txt_validatecode").val();
		if(!validatecode){
			alert('请输入验证码');
			return false;
		}
		$.ajax({
			url : $('#contextPath').val() + "/new/dologin.html",
			data : {
				userName: userName,
				password : password,
				validatecode : validatecode
			},
			type : 'POST',
			dataType : 'json',
			success : function(res) {
				if(!res.success){
					if(res.code == -1){
						alert('验证码错误,请重新输入');
						HeaderTopbar.refresh();
					}else if(res.code == -2){
						alert('用户密码错误,请重新输入');
						HeaderTopbar.refresh();
					}
				}
				
				if (res.success) {
					if(callback){
						callback.call();
					}else {
						var returl = res.returl;
						returl = $('#contextPath').val(); 
						window.location.href = returl;
					}
				}
			}
		});
	},
	
	/**
	 * 注册
	 */
	doReg: function(){
		var userName= $(".popup #txt_regmobile").val();
		var password = $(".popup #txt_regpwd").val();
		var validatecode = $(".popup #txt_regcode").val();
		if(!validatecode){
			alert('请输入验证码');
			return false;
		}
		$.ajax({
			url : $('#contextPath').val() + "/new/doreg.html",
			data : {
				userName: userName,
				password : password,
				validatecode :validatecode
			},
			type : 'POST',
			dataType : 'json',
			success : function(res) {
				if(res.success){
					window.location.reload();
				}else {
					alert("注册失败,请重试");
				}
			}
		});
	},
	
	loginCallback: function(userName){
		$('#btn_login').hide();
		$('#btn_register').hide();
		$('#userName').show().text("您好," + userName);
		$('#btn_quit').show();
	},
	
	logout: function(){
		$.ajax({
			url : $('#contextPath').val() + "/new/logout.html",
			data : {
			},
			type : 'POST',
			dataType : 'json',
			success : function(res) {
				if (res.success) {
					window.location.reload();
				}
			}
		});
	}
};

$(function(){
	//检查登录状态
	HeaderTopbar.checkLogin();
	//绑定登录按钮
	$('#btn_login').bind("click",function(event,callBack){
		var popup = new UIpopup({
			width:480,
			height:280,
			paddingTB: '0',
			paddingLR: '0',
			template: $("#login_box").html()
		});
		
		$('.popup #btn_do_login').click(function(){
			HeaderTopbar.doLogin(callBack);
		});
		HeaderTopbar.refresh();

	});

	$('body').on("click", '.register', function () {
		$('.popup .close').trigger('click');
		$('#btn_register').trigger('click');
	});
	
	//绑定注册按钮
	$('#btn_register').click(function(){
		var popup = new UIpopup({
			width:480,
			height:320,
			paddingTB: '0',
			paddingLR: '0',
			template: $("#reg_box").html()
		});
		
		$('.popup #btn_do_reg').click(function(){
			HeaderTopbar.doReg();
		});
		HeaderTopbar.refresh();
	})

	$('body').on("click", '.login_small', function () {
		$('.popup .close').trigger('click');
		$('#btn_login').trigger('click');
	});
	
	//绑定退出按钮
	$('#btn_quit').click(function(){
		HeaderTopbar.logout();
	})
})