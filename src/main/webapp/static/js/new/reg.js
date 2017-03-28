var Reg = {
		//默认路径
		contextPath : $('#contextPath').val(),
		//刷新验证码
		refresh : function(){
			$('#img').attr('src',Reg.contextPath +"/drawImage.html?" + Math.random());
		},
		
		//请求登录
		doReg: function(){
			var userName= $("#userName").val();
			var password = $("#password").val();
			var password2 = $("#password2").val();
			if(password != password2){
				alert('2次输入密码不一致');
				return;
			}
			$.ajax({
				url : Reg.contextPath + "/new/doreg.html",
				data : {
					userName: userName,
					password : password
				},
				type : 'POST',
				dataType : 'json',
				success : function(res) {
					if (res.success) {
						alert('恭喜您,注册成功,三秒后自动跳往首页');
						setTimeout("window.location.href = Reg.contextPath",3000);
						return;
					} 
					if(!res.success){
						if(res.code == -1){
							alert('用户名或者密码不能为空');
						}
						if(res.code == -2){
							alert('该用户已注册,请前往登录页面');
						}
					}
				}
			});
		},
};

$(function(){
	
	//绑定提交
	$('#userSubmit').on('click',function(){
		Reg.doReg();
	});
	
});