var Login = {
		//默认路径
		contextPath : $('#contextPath').val(),
		returl :  $('#returl').val(),
		//刷新验证码
		refresh : function(){
			$('#img').attr('src',Login.contextPath +"/drawImage.html?" + Math.random());
		},
		
		//请求登录
		doLogin: function(){
			var userName= $("#userName").val();
			var password = $("#password").val();
			var validatecode = $("#validatecode").val();
			
			if(!validatecode){
				alert('请输入验证码');
				return;
			}
			$.ajax({
				url : Login.contextPath + "/new/dologin.html",
				data : {
					userName: userName,
					password : password,
					validatecode : validatecode,
					returl : Login.returl
				},
				type : 'POST',
				dataType : 'json',
				success : function(res) {
					if(!res.success){
						if(res.code == -1){
							alert('验证码错误,请重新输入');
							Login.refresh();
						}
					}
					
					if (res.success) {
						var returl = res.returl;
						if(!returl){
							returl = Login.contextPath; 
						}
						
						window.location.href = returl;
					} else {
						alert("账号或密码错误,请重试");
					}
				}
			});
		},
};

$(function(){
	
	//绑定提交
	$('#userSubmit').on('click',function(){
		Login.doLogin($(this));
	});
	
});