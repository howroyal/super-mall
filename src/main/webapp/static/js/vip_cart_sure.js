var mSecond = 60, 
	oClass = "send",
	cSecond = mSecond,
	timerID;

var CartSure = {
		baseURL : $('#contextPath').val(),
		
		/**
		 * 发送验证码
		 */
		sendCode : function (btn) {
			if(!btn.hasClass(oClass) ){
				btn.addClass(oClass);
				//ajax获取验证码
				$.ajax({
					url:CartSure.baseURL + '/cart/sendSms',
					type:'post',
					dataType:'json',
					data:{},
					success: function(data){
						if(data && data.returnCD == '0'){
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
							$(".code span").show();
						}else {
							btn.removeClass(oClass);
							alert('验证码获取失败,请重新获取');
						}
					},
					error : function(){
						btn.removeClass(oClass);
						alert('验证码获取失败,请重新获取');
					}
				});
			}
		},
		
		/**
		 * 验证验证码
		 */
		checkCode : function(codeInput){
			var code = codeInput.val();
			if(!code || code.length < 6){
				return;
			}
			//请求后台
			$.ajax({
				url:CartSure.baseURL + '/cart/checkSmsCode',
				type:'post',
				dataType:'json',
				data:{
					code : code
				},
				success: function(data){
					if(data && data.returnCD == '0'){
						$(".btn a").removeClass("no");
					}
				},
				error : function(){
					alert('验证超时,请重试');
				}
			});
		},
		
		
		/**
		 * 有地址时新增收货地址弹框
		 */
		openPopup : function(){
			var popup = new UIpopup({
				width:600,
				height:420,
				paddingTB: '0',
				paddingLR: '20',
				template: $("#mask_ads").html()
			});
			
			//绑定设为默认地址的checkbox
			$('.popup #new_isDefault').unbind('click');
			$('.popup #new_isDefault').click(function(){
				var clazz = $(this).attr('class');
				if(clazz.indexOf('iconsub checkbox check') == 0){
					$(this).attr('class','iconsub checkbox');
				}else {
					$(this).attr('class','iconsub checkbox check');
				}
			});
			var newValideForm = $(".popup #newValideForm").Validform({
				tiptype:3,
				showAllError:true,
				datatype:{
					"mobile" : function(gets,obj,curform,regxp){
						var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
						return reg.test(gets);
						//if(reg.test(gets)){return true;}
					},
					"phone":function(gets,obj,curform,regxp){
						reg = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
						return reg.test(gets);
					}	
				}
			});

			//绑定提交按钮
			$('.popup #add_submit').unbind('click');
			$('.popup #add_submit').click(function(){
				if(newValideForm.check()){
					CartSure.doAddSubmit();
				}
				
			});
			//绑定取消按钮
			$('.popup #add_cancel').unbind('click');
			$('.popup #add_cancel').click(function(){
				 $('.mask, .popup').remove();
			});
			//绑定下拉框 下拉联动
			$.ajax({
				url:CartSure.baseURL + '/addr/provinces.html',
				type:'post',
				dataType:'json',
				data:{},
				success: function(data){
					$('.popup #new_provId').unbind('click');
					$.each(data,function(i,item){
						$('.popup #new_provId').append("<option value='" + item.id + "'code='" + item.code + "'>" + item.name + "</option>");
					});
				}
			});
			$('.popup #new_provId').unbind('change');
			$('.popup #new_provId').change(function(){
				var provCode = $(this).find("option:selected").attr('code'); 
				if(!provCode){
					$('.popup #new_cityId').empty();
					$('.popup #new_cityId').append("<option value=''>请选择市/区</option>");
					$('.popup #new_areaId').empty();
					$('.popup #new_areaId').append("<option value=''>请选择区/县</option>");
					return;
				}
				//请求收货地址新增接口
				$.ajax({
					url:CartSure.baseURL + '/addr/cities.html',
					type:'post',
					dataType:'json',
					data:{
						provCode : provCode
					},
					success: function(data){
						$('.popup #new_cityId').empty();
						$('.popup #new_cityId').append("<option value=''>请选择市/区</option>");
						$('.popup #new_areaId').empty();
						$('.popup #new_areaId').append("<option value=''>请选择区/县</option>");
						$.each(data,function(i,item){
							$('.popup #new_cityId').append("<option value='" + item.id + "'code='" + item.code + "'>" + item.name + "</option>");
						});
					}
				});
			});
			$('.popup #new_cityId').unbind('change');
			$('.popup #new_cityId').change(function(){
				var cityCode = $(this).find("option:selected").attr('code'); 
				if(!cityCode){
					$('.popup #new_areaId').empty();
					$('.popup #new_areaId').append("<option value=''>请选择区/县</option>");
					return;
				}
				//请求收货地址新增接口
				$.ajax({
					url:CartSure.baseURL + '/addr/areas.html',
					type:'post',
					dataType:'json',
					data:{
						cityCode : cityCode
					},
					success: function(data){
						$('.popup #new_areaId').empty();
						$.each(data,function(i,item){
							$('.popup #new_areaId').append("<option value='" + item.id + "'code='" + item.code + "'>" + item.name + "</option>");
						});
					}
				});
			});
		},
		
		/**
		 * 修改按钮点击弹窗
		 */
		openEditPopup : function(adsId){

			//请求后台获取地址信息
			$.ajax({
				url:CartSure.baseURL + '/addr.html',
				type:'post',
				dataType:'json',
				data:{
					id :adsId
				},
				success: function(data){
					if(data && data.code == '0'){
						CallBack.initEditCompentsValue(data);
					}else {
						$('#alertDialogText').html('<i class="iconsub"></i>操作失败,无此地址信息');
						new UIpopup({
							width:600,
							height:320,
							paddingTB: '0',
							paddingLR: '0',
							template: $("#mask_nologin").html()
						});

						$('.popup .redbtn').on('click',function(){
							$('.mask, .popup').remove();
						})
					}
				},
				error: function(){
					$('#alertDialogText').html('<i class="iconsub"></i>操作失败,无此地址信息');
					new UIpopup({
						width:600,
						height:320,
						paddingTB: '0',
						paddingLR: '0',
						template: $("#mask_nologin").html()
					});

					$('.popup .redbtn').on('click',function(){
						$('.mask, .popup').remove();
					})
				}
			});
		},
		
		/**
		 * 收货地址编辑确认事件
		 */
		doEditSubmit : function(id){
			var contactName = $('.popup #edit_contactName').val();
			var provId = $(".popup #edit_provId").val();
			var provName = $(".popup #edit_provId").find("option:selected").text(); 
			var cityId = $(".popup #edit_cityId").val();
			var cityName = $(".popup #edit_cityId").find("option:selected").text(); 
			var areaId = $(".popup #edit_areaId").val();
			var areaName = $(".popup #edit_areaId").find("option:selected").text(); 
			var addressDetail = $(".popup #edit_addressDetail").val();
			var contactPhone = $(".popup #edit_contactPhone").val();
			var contactTel = $(".popup #edit_contactTel").val();
			//设为常用地址
			var isDefault = '0';
			var clazz = $(".popup #edit_isDefault").attr('class');
			if(clazz.indexOf('iconsub checkbox check') == 0){
				isDefault = '1';
			}
			
			var reqData = {
					id : id,
					contactName: contactName,
					provId: provId,
					provName : provName,
					cityId: cityId,
					cityName : cityName,
					areaId: areaId,
					areaName : areaName,
					addressDetail: addressDetail,
					contactPhone: contactPhone,
					contactTel: contactTel,
					isDefault: isDefault
			};
			
			//ajax提交到后台修改
			$.ajax({
				url:CartSure.baseURL + '/addr/updateAddr.html',
				type:'post',
				dataType:'json',
				data:reqData,
				success: function(resq){
					//执行回调
					CallBack.doEditSuccess(reqData);
					$('.mask, .popup').remove();
				}
			});
		
		},
		
		/**
		 * 无收货地址时新增收货地址
		 */
		doNoAddrAddSubmit : function(){
			var contactName = $('#noadsContactName').val();
			var provId = $("#noadsProvId").val();
			var provName = $("#noadsProvId").find("option:selected").text(); 
			var cityId = $("#noadsCityId").val();
			var cityName = $("#noadsCityId").find("option:selected").text(); 
			var areaId = $("#noadsAreaId").val();
			var areaName = $("#noadsAreaId").find("option:selected").text(); 
			var addressDetail = $("#noadsAddressDetail").val();
			var contactPhone = $("#noadsContactPhone").val();
			var contactTel = $("#noadscontactTel").val();
			//设为常用地址
			var isDefault = '0';
			var clazz = $("#noadsIsDefault").attr('class');
			if(clazz.indexOf('no_addr_defalut_check iconsub checkbox check') == 0){
				isDefault = '1';
			}
			
			if(provId == ''){
				provId = null;
				provName = null;
			}
			if(cityId == ''){
				cityId = null;
				cityName = null;
			}
			if(areaId == ''){
				areaId = null;
				areaName = null;
			}
			var reqData = {
					contactName: contactName,
					provId: provId,
					provName : provName,
					cityId: cityId,
					cityName: cityName,
					areaId: areaId,
					areaName: areaName,
					addressDetail: addressDetail,
					contactPhone: contactPhone,
					contactTel: contactTel,
					isDefault: isDefault
			};
			
			console.log(reqData);
			
			//请求收货地址新增接口
			$.ajax({
				url:CartSure.baseURL + '/addr/newAddr.html',
				type:'post',
				dataType:'json',
				data:reqData,
				success: function(resp){
					var data = resp.data;
					CallBack.toAddAds(data);
					$('.mask, .popup').remove();
				}
			});
			
		},

		/**
		 * 有收货地址时新增收货地址
		 */
		doAddSubmit : function(){
			var contactName = $('.popup #new_contactName').val();
			var provId = $(".popup #new_provId").val();
			var provName = $(".popup #new_provId").find("option:selected").text(); 
			var cityId = $(".popup #new_cityId").val();
			var cityName = $(".popup #new_cityId").find("option:selected").text(); 
			var areaId = $(".popup #new_areaId").val();
			var areaName = $(".popup #new_areaId").find("option:selected").text(); 
			var addressDetail = $(".popup #new_addressDetail").val();
			var contactPhone = $(".popup #new_contactPhone").val();
			var contactTel = $(".popup #new_contactTel").val();
			//设为常用地址
			var isDefault = '0';
			var clazz = $(".popup #new_isDefault").attr('class');
			if(clazz.indexOf('iconsub checkbox check') == 0){
				isDefault = '1';
			}
			
			if(provId == ''){
				provId = null;
				provName = null;
			}
			if(cityId == ''){
				cityId = null;
				cityName = null;
			}
			if(areaId == ''){
				areaId = null;
				areaName = null;
			}
			var reqData = {
					contactName: contactName,
					provId: provId,
					provName : provName,
					cityId: cityId,
					cityName: cityName,
					areaId: areaId,
					areaName: areaName,
					addressDetail: addressDetail,
					contactPhone: contactPhone,
					contactTel: contactTel,
					isDefault: isDefault
			};
			
			//请求收货地址新增接口
			$.ajax({
				url:CartSure.baseURL + '/addr/newAddr.html',
				type:'post',
				dataType:'json',
				data:reqData,
				success: function(resp){
					var data = resp.data;
					CallBack.toAddAds(data);
					$('.mask, .popup').remove();
				}
			});
			
		},
		
		createAdsHtml : function(data){
			var isDefaultStyle = 'style="display:none"';
			if(data.isDefault == '1'){
				isDefaultStyle = 'style=""';
				$('.ads_isDefault').show();
				//常用地址隐藏
				$('.isDefault').hide();
			}
			
			var html = '<div class="one active" id="'+ data.id +'">'
				+ '<div class="option">'
				+ '<a href="javascript:void(0);" class="ads_edit" value="' + data.id + '">编辑</a>'
				+ '<a href="javascript:void(0);" class="ads_isDefault" value="' + data.id + '" ' + isDefaultStyle + '>设为常用地址</a>'
				+ '<a href="javascript:void(0);" value="' + data.id + '" class="del iconsub"></a>'
				+ '</div>'
				+ '<i class="iconsub sure"></i>';
			
			html += '<h5 class= "isDefault" ' + isDefaultStyle +'>常用地址</h5>'
				+ '<div class="text">'
				+ '<p>'
				+ '	<b class="contactName">'+ data.contactName +'</b> 收'
				+ '</p>'
				+ '<p class="areaName">' + data.provName + " " + data.cityName + " " +  data.areaName + '</p>'
				+ '<p class="addressDetail">' + data.addressDetail  +'</p>'
				+ '<p class="contactPhone">' + data.contactPhone + '</p>'
				+ '</div>'
				+ '</div>';
			return html;
		},
		
		/**
		 * 
		 */
		initEditCompentsValue : function(id){},
		
		/**
		 * 设为常用地址
		 */
		setIsDefault : function(obj){
			var id = obj.attr('value');
			//ajax后台
			$.ajax({
				url:CartSure.baseURL + '/addr/setAddrIsDefault.html',
				type:'post',
				dataType:'json',
				data:{
					id :id
				},
				success: function(data){
					if(data && data.code == '0'){
						//成功后回调函数
						CallBack.doIsDefault(obj);
					}else {
						$('#alertDialogText').html('<i class="iconsub"></i>操作失败,无此地址信息');
						
						new UIpopup({
							width:600,
							height:320,
							paddingTB: '0',
							paddingLR: '0',
							template: $("#mask_nologin").html()
						});

						$('.popup .redbtn').on('click',function(){
							$('.mask, .popup').remove();
						})
					}
				},
				error :function(){
					$('#alertDialogText').html('<i class="iconsub"></i>操作失败,请检查参数或网络设置');
					new UIpopup({
						width:600,
						height:320,
						paddingTB: '0',
						paddingLR: '0',
						template: $("#mask_nologin").html()
					});

					$('.popup .redbtn').on('click',function(){
						$('.mask, .popup').remove();
					})
				}
			});
		},
		
		/**
		 * 删除用户收货地址
		 */
		remove : function(obj){
			//确认框哦
			var popup = new UIpopup({
				width:600,
				height:320,
				paddingTB: '0',
				paddingLR: '0',
				template: $("#confirm").html()
			});
			var id = obj.attr('value');
			//确认
			$('.popup #remove_submit').on('click',function(){
				//ajax请求后台
				$.ajax({
					url:CartSure.baseURL + '/addr/removeAddr.html',
					type:'post',
					dataType:'json',
					data:{
						id : id	
					},
					success: function(data){
						if(data){
							var code = data.code;
							if(code == '0'){
								$('#' + id).remove();
								$('.mask, .popup').remove();
							}else {
								$('#alertDialogText').html('<i class="iconsub"></i>删除失败,无此地址信息');
								new UIpopup({
									width:600,
									height:320,
									paddingTB: '0',
									paddingLR: '0',
									template: $("#mask_nologin").html()
								});

								$('.popup .redbtn').on('click',function(){
									$('.mask, .popup').remove();
								})
							}
						}
					},
					error : function(){
						$('#alertDialogText').html('<i class="iconsub"></i>删除失败,请检查参数及网络设置');
						new UIpopup({
							width:600,
							height:320,
							paddingTB: '0',
							paddingLR: '0',
							template: $("#mask_nologin").html()
						});
						$('.popup .redbtn').on('click',function(){
							$('.mask, .popup').remove();
						})
					}
				});
			})
			//取消
			$('.popup #remove_cancel').on('click',function(){
				$('.mask, .popup').remove();
			})
		},
		
		/**
		 * 提交订单
		 */
		orderSubmit : function(obj){
			var clazz = obj.attr('class');
			if('no' == clazz){
				return;
			}
			var wayValue = $('.way.active').attr('value');
			if(!wayValue){
				$('#alertDialogText').html('<i class="iconsub"></i>' + '请选择领取方式');
				new UIpopup({
					width:600,
					height:320,
					paddingTB: '0',
					paddingLR: '0',
					template: $("#mask_nologin").html()
				});

				$('.popup #close').on('click',function(){
					$('.mask, .popup').remove();
				})
				return;
			}
			//
			var addrId = $('.one.active').attr('id');
			if(!addrId && wayValue=='1'){
				$('#alertDialogText').html('<i class="iconsub"></i>' + '请选择送货地址');
				new UIpopup({
					width:600,
					height:320,
					paddingTB: '0',
					paddingLR: '0',
					template: $("#mask_nologin").html()
				});

				$('.popup #close').on('click',function(){
					$('.mask, .popup').remove();
				})
				return;
			}
			
			var productstr = $('#productstr').val();
			if(!productstr || productstr == ''){
				$('#alertDialogText').html('<i class="iconsub"></i>' + '未选择任何商品请返回商城选购商品');
				new UIpopup({
					width:600,
					height:320,
					paddingTB: '0',
					paddingLR: '0',
					template: $("#mask_nologin").html()
				});

				$('.popup #close').on('click',function(){
					$('.mask, .popup').remove();
				})
				return;
			}
			
//			var code = $('#codetext').val();
//			if(!code){
//				$('#alertDialogText').html('<i class="iconsub"></i>' + '请输入验证码');
//				new UIpopup({
//					width:600,
//					height:320,
//					paddingTB: '0',
//					paddingLR: '0',
//					template: $("#mask_nologin").html()
//				});
//
//				$('.popup #close').on('click',function(){
//					$('.mask, .popup').remove();
//				})
//				return;
//			}
//			var type = $('#type').val();
//			if(!type){
//				type = '2';
//			}
//			obj.addClass('no');
			//ajax发送请求
			$.ajax({
				url:CartSure.baseURL + '/order/checkOrderSubmit.html',
				type:'post',
				dataType:'json',
				data:{
					way : wayValue,
					addrId :addrId,
					products : productstr
					//code: code,
					//type: type
				},
				beforeSend:function(){
					$('#loading-mask').show();
					$('#loading').show();
				},
				error : function(){
					alert('下单失败,请返回商城');
					obj.removeClass('no');
					$('#loading-mask').hide();
					$('#loading').hide();
				},
				success: function(data){
					if(data && data.code != '0'){

						$('#loading-mask').hide();
						$('#loading').hide();
						obj.removeClass('no');
						var cd = data.returnCD;
						if(cd == '-4'){
							loginPopup(function(){
								Cart.doCheck();
							});
							return;
						}else {
							var text = "下单失败,请重试";
							if(cd == '-1'){
								text = "请选择领取方式";
							}else if(cd == '-2'){
								text = "请选择收货地址";
							}else if(cd == '-3'){
								text = "请选购商品";
							}else if(cd == '-5'){
								text =  data.data.productName + "库存不足";
							}else if(cd == '-6'){
								text = "您的积分不足";
							}else if(cd == '-7'){
								text = "验证码输入错误";
							}else if(cd == '-8'){
								text =  data.data.productName + "已下架";
							}
							
							$('#alertDialogText').html('<i class="iconsub"></i>' + text);
							new UIpopup({
								width:600,
								height:320,
								paddingTB: '0',
								paddingLR: '0',
								template: $("#mask_nologin").html()
							});

							$('.popup #close').on('click',function(){
								$('.mask, .popup').remove();
							})
							return;
						}
					}
					if(data && data.code == '0'){
						//生成form表单并提交
						var url = CartSure.baseURL + "/order/orderSubmit.html";
						
						var form = $('<form></form>');
						form.attr('action', url);
						form.attr('method', 'post');
						form.attr('target', '_self');
						var wayinput = $('<input type="text" name="way" />');
						wayinput.attr('value', wayValue);
						var ainput = $('<input type="text" name="addrId" />');
						ainput.attr('value', addrId);
						var pinput = $('<input type="text" name="products" />');
						pinput.attr('value', productstr);
//						var tinput = $('<input type="text" name="type" />');
//						tinput.attr('value', type);
//						var soucreInput = $('<input type="text" name="source" />');
//						soucreInput.attr('value', '1');

						var remark = $('#remark').val();
						var remarkinput = $('<input type="text" name="remark" />');
						remarkinput.attr('value', remark);
//						var codeinput = $('<input type="text" name="code" />');
//						codeinput.attr('value', code);
						var isFromCart = $('#isFromCart').val();
						if(!isFromCart){
							isFromCart = false;
						}
						var cartinput = $('<input type="text" name="fromCart" />');
						cartinput.attr('value', isFromCart);
						
						form.append(wayinput)
							.append(ainput)
							.append(pinput)
							//.append(tinput)
							//.append(soucreInput)
							.append(remarkinput)
							//.append(codeinput)
							.append(cartinput);
						$('body').append(form)
						form.submit();
					}
				}
			});
		}
};

var CallBack = {
		
		/**
		 * 初始化编辑组件的值
		 */
		initEditCompentsValue: function(data){
			
			if(data && data.data){
				var popup = new UIpopup({
					width:600,
					height:420,
					paddingTB: '0',
					paddingLR: '20',
					template: $("#mask_ads_edit").html()
				});
				
				//初始化mask_ads_edit组件的值
				//CartSure.initEditCompentsValue(adsId);
				//绑定设为默认地址的checkbox
				$('.popup #edit_isDefault').unbind('click');
				$('.popup #edit_isDefault').click(function(){
					var clazz = $(this).attr('class');
					if(clazz.indexOf('iconsub checkbox check') == 0){
						$(this).attr('class','iconsub checkbox');
					}else {
						$(this).attr('class','iconsub checkbox check');
					}
				});
				
				var editValideForm = $(".popup #editValideForm").Validform({
					tiptype:3,
					showAllError:true,
					datatype:{
						"mobile" : function(gets,obj,curform,regxp){
							var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
							return reg.test(gets);
							//if(reg.test(gets)){return true;}
						
						},
						"phone":function(gets,obj,curform,regxp){
							reg = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
							return reg.test(gets);
						}	
					}
				});
				//绑定确认按钮
				$('.popup #edit_submit').unbind('click');
				$('.popup #edit_submit').click(function(){
					if(editValideForm.check()){
						CartSure.doEditSubmit(data.data.id);
					}
				});
				
				//绑定取消按钮
				$('.popup #edit_cancel').unbind('click');
				$('.popup #edit_cancel').click(function(){
					 $('.mask, .popup').remove();
				});
				
				//绑定下拉框 下拉联动
				$('.popup #edit_provId').unbind('click');
//				$('.popup #edit_provId').click(function(){
//					//请求收货地址新增接口
//					$.ajax({
//						url:CartSure.baseURL + '/common/getProvList',
//						type:'post',
//						dataType:'json',
//						data:{},
//						success: function(data){
//							$('.popup #edit_provId').unbind('click');
//							$('.popup #edit_provId').empty();
//							$('.popup #edit_provId').append("<option value=''>请选择省/市</option>");
//							
//							$.each(data,function(i,item){
//								$('.popup #edit_provId').append("<option value='" + item.id + "'code='" + item.code + "'>" + item.name + "</option>");
//							});
//						}
//					});
//				});
				$('.popup #edit_provId').unbind('change');
				$('.popup #edit_provId').change(function(){
					var provCode = $(this).find("option:selected").attr('code');
					if(!provCode){
						$('.popup #edit_cityId').empty();
						$('.popup #edit_cityId').append("<option value=''>请选择市/区</option>");
						$('.popup #edit_areaId').empty();
						$('.popup #edit_areaId').append("<option value=''>请选择区/县</option>");
						return;
					}
					//请求收货地址新增接口
					$.ajax({
						url:CartSure.baseURL + '/addr/cities.html',
						type:'post',
						dataType:'json',
						data:{
							provCode : provCode
						},
						success: function(data){
							$('.popup #edit_cityId').empty();
							$('.popup #edit_cityId').append("<option value=''>请选择市/区</option>");
							$('.popup #edit_areaId').empty();
							$('.popup #edit_areaId').append("<option value=''>请选择区/县</option>");
							
							$.each(data,function(i,item){
								$('.popup #edit_cityId').append("<option value='" + item.id + "'code='" + item.code + "'>" + item.name + "</option>");
							});
						}
					});
				});
				$('.popup #edit_cityId').unbind('change');
				$('.popup #edit_cityId').change(function(){
					var value = $(this).val();
					var cityCode = $(this).find("option:selected").attr('code');
					if(!cityCode){
						$('.popup #edit_areaId').empty();
						$('.popup #edit_areaId').append("<option value=''>请选择区/县</option>");
						return;
					}
					//请求收货地址新增接口
					$.ajax({
						url:CartSure.baseURL + '/addr/areas.html',
						type:'post',
						dataType:'json',
						data:{
							cityCode : cityCode
						},
						success: function(data){
							$('.popup #edit_areaId').empty();
							$.each(data,function(i,item){
								$('.popup #edit_areaId').append("<option value='" + item.id + "'code='" + item.code + "'>" + item.name + "</option>");
							});
						}
					});
				});
				
				var addrBean = data.data;
				//设置字段的值
				$('.popup #edit_contactName').val(addrBean.contactName);
				$('.popup #edit_addressDetail').val(addrBean.addressDetail);
				$('.popup #edit_contactPhone').val(addrBean.contactPhone);
				$('.popup #edit_contactTel').val(addrBean.contactTel);
				//设置下拉框的值
				//$('.popup #edit_provId').append("<option value='" + addrBean.provId + "'>" + addrBean.provName  +"</option>");
				//$('.popup #edit_cityId').append("<option value='" + addrBean.cityId + "'>" + addrBean.cityName  +"</option>");
			//	$('.popup #edit_areaId').append("<option value='" + addrBean.areaId + "'>" + addrBean.areaName  +"</option>");
				
				//设为常用checkbox
				var isDefault = addrBean.isDefault;
				if(isDefault == '1'){
					var clazz = $('.popup #edit_isDefault').attr('class');
					$('.popup #edit_isDefault').attr('class',clazz + " check")
				}
				
				//获取省市区数据
				var provId = addrBean.provId;
				var provCode = "";
				//请求收货地址新增接口
				$.ajax({
					url:CartSure.baseURL + '/addr/provinces.html',
					type:'post',
					dataType:'json',
					data:{},
					success: function(data){
						$('.popup #edit_provId').append("<option value=''>请选择省/市</option>");
						$.each(data,function(i,item){
							$('.popup #edit_provId').append("<option value='" + item.id + "'code='" + item.code + "'>" + item.name + "</option>");
							if(provId == item.id){
								$('.popup #edit_provId').val(item.id);
								provCode = item.code;
							}
						});

						var cityId = addrBean.cityId;
						var cityCode = "";
						$.ajax({
							url:CartSure.baseURL + '/addr/cities.html',
							type:'post',
							dataType:'json',
							data:{
								provCode : provCode
							},
							success: function(data){
								$('.popup #edit_cityId').append("<option value=''>请选择市/区</option>");
								$.each(data,function(i,item){
									$('.popup #edit_cityId').append("<option value='" + item.id + "'code='" + item.code + "'>" + item.name + "</option>");
									if(cityId == item.id){
										$('.popup #edit_cityId').val(cityId);
										cityCode = item.code;
									}
								});
								
								var areaId = addrBean.areaId;

								$.ajax({
									url:CartSure.baseURL + '/addr/areas.html',
									type:'post',
									dataType:'json',
									data:{
										cityCode : cityCode
									},
									success: function(data){
										$.each(data,function(i,item){
											$('.popup #edit_areaId').append("<option value='" + item.id + "'code='" + item.code + "'>" + item.name + "</option>");
											if(areaId == item.id){
												$('.popup #edit_areaId').val(areaId);
											}
										});
									}
								});
							}
						});
					}
				});
				
			}
		},
		
		/**
		 * 新增完成后回调动作
		 */
		toAddAds : function(data){
			$('.addads').attr('style',"display:block;");
			$('.noads').remove();
			$('.one').removeClass('active');
			var isDefault = data.isDefault;
			//if(isDefault == '1'){
				
			//}
			
			var html = CartSure.createAdsHtml(data);
			$('.ads').prepend(html);
			var $id =  "#" + data.id;
			if(isDefault == '1'){
				//将设为常用地址显示
				$('.ads_isDefault').show();
				$($id + ' .ads_isDefault').hide();
				//常用地址隐藏
				$('.isDefault').hide();
				//本节点的设为常用隐藏
				$($id + ' .isDefault').show();
			}else {
				//将设为常用地址显示
				//$('.ads_isDefault').show();
				$($id + ' .ads_isDefault').show();
				//常用地址隐藏
				//$('.isDefault').hide();
			}
			
			//重新绑定事件
			//绑定编辑事件
			$($id + ' .ads_edit').on('click',function(){
				CartSure.openEditPopup($(this).attr('value'));
			});
			
			//绑定设为常用地址事件
			$($id + ' .ads_isDefault').on('click',function(){
				CartSure.setIsDefault($(this));
			});
			//绑定删除地址事件
			$($id + ' .del').on('click',function(){
				CartSure.remove($(this));
			});
		},
		
		/**
		 * 编辑成功后执行回调的方法 
		 */
		doEditSuccess : function(data){
			var id = "#" + data.id;
			$(id +" .contactName").html(data.contactName);
			$(id +" .areaName").html(data.provName + " " + data.cityName + " " + data.areaName);
			$(id +" .addressDetail").html(data.addressDetail);
			$(id +" .contactPhone").html(data.contactPhone);
			
			//设为常用的按钮 状态 显示与隐藏
			var isDefault = data.isDefault;
			if(isDefault == '1'){
				//将设为常用地址显示
				$('.ads_isDefault').show();
				$(id + ' .ads_isDefault').hide();
				//常用地址隐藏
				$('.isDefault').hide();
				//本节点的设为常用隐藏
				$(id + ' .isDefault').show();
			}else {
				//将设为常用地址显示
				$('.ads_isDefault').show();
				//常用地址隐藏
				$('.isDefault').hide();
			}
		},
		
		/**
		 * 设置为常用地址
		 */
		doIsDefault : function(obj){
			//将设为常用地址显示
			$('.ads_isDefault').show();
			//按钮隐藏
			obj.hide();
			//常用地址显示去掉
			$('.isDefault').hide();
			//添加常用地址显示
			obj.parent().parent().find('.isDefault').show();
		}
};


$(function(){
	var top = new goTop({
		showPhone: false,      //是否显示联系方式
		showQQ: false          //是否显示在线客服
	});
	
	//新增收货地址
	$("#open").click(function() {
		CartSure.openPopup();
	});	
	
	$(".order_info").on("click",".way",function(){
		var num = $(this).index();
		//第三方供应商不容许自提
		if(num == 1 && $('#isContainOther').val() == 'true'){

			$('#alertDialogText').html('<i class="iconsub"></i>' + "含有第三方供应商商品,请选择邮寄");
			new UIpopup({
				width:600,
				height:320,
				paddingTB: '0',
				paddingLR: '0',
				template: $("#mask_nologin").html()
			});

			$('.popup #close').on('click',function(){
				$('.mask, .popup').remove();
			})
			return;
		}
		$(this).addClass("active").siblings(".way").removeClass("active");
		$(".adsbox").eq(num).show().siblings(".adsbox").hide();
	});
	
	$(".ads").on("click",".one .text",function(){
		$(this).parent().addClass("active").siblings().removeClass("active");
	});
	
	//绑定无收货地址时新增的按钮中的设为常用按钮
	if($("#noadsValideForm").length > 0 ){
		$('.no_addr_defalut_check').on('click', function(){
			var clazz = $(this).attr('class');
			if(clazz.indexOf('no_addr_defalut_check iconsub checkbox check') == 0){
				$(this).attr('class',"no_addr_defalut_check iconsub checkbox");
			}else {
				$(this).attr('class',"no_addr_defalut_check iconsub checkbox check");
			}
		});

		var noadsValideForm = $("#noadsValideForm").Validform({
			tiptype:3,
			showAllError:true,
			datatype:{
				"mobile" : function(gets,obj,curform,regxp){
					var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
					return reg.test(gets);
					//if(reg.test(gets)){return true;}
				
				},
				"phone":function(gets,obj,curform,regxp){
					reg = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
					return reg.test(gets);
				}	
			}
		});
	
		//绑定无收货地址时新增收货地址的确认按钮
		$('#no_addr_add_submit').on('click',function(){
			if(noadsValideForm.check()){
				CartSure.doNoAddrAddSubmit();
			}
		});

		//绑定下拉框 下拉联动
		$.ajax({
			url:CartSure.baseURL + '/addr/provinces.html',
			type:'post',
			dataType:'json',
			data:{},
			success: function(data){
				$('#noadsProvId').unbind('click');
				$.each(data,function(i,item){
					$('#noadsProvId').append("<option value='" + item.id + "'code='" + item.code + "'>" + item.name + "</option>");
				});
			}
		});
		$('#noadsProvId').unbind('change');
		$('#noadsProvId').change(function(){
			var provCode = $(this).find("option:selected").attr('code');
			if(!provCode){
				$('#noadsCityId').empty();
				$('#noadsCityId').append("<option value=''>请选择市/区</option>");
				$('#noadsAreaId').empty();
				$('#noadsAreaId').append("<option value=''>请选择区/县</option>");
				return;
			}
			//请求收货地址新增接口
			$.ajax({
				url:CartSure.baseURL + '/addr/cities.html',
				type:'post',
				dataType:'json',
				data:{
					provCode : provCode
				},
				success: function(data){
					$('#noadsCityId').empty();
					$('#noadsCityId').append("<option value=''>请选择市/区</option>");
					$('#noadsAreaId').empty();
					$('#noadsAreaId').append("<option value=''>请选择区/县</option>");
					$.each(data,function(i,item){
						$('#noadsCityId').append("<option value='" + item.id + "'code='" + item.code + "'>" + item.name + "</option>");
					});
				}
			});
		});
		$('#noadsCityId').unbind('change');
		$('#noadsCityId').change(function(){
			var cityCode = $(this).find("option:selected").attr('code');
			if(!cityCode){
				$('#noadsAreaId').empty();
				$('#noadsAreaId').append("<option value=''>请选择区/县</option>");
				return;
			}
			//请求收货地址新增接口
			$.ajax({
				url:CartSure.baseURL + '/addr/areas.html',
				type:'post',
				dataType:'json',
				data:{
					cityCode : cityCode
				},
				success: function(data){
					$('#noadsAreaId').empty();
					$.each(data,function(i,item){
						$('#noadsAreaId').append("<option value='" + item.id + "'code='" + item.code + "'>" + item.name + "</option>");
					});
				}
			});
		});
	}
	
	//绑定编辑事件
	$('.ads_edit').on('click',function(){
		CartSure.openEditPopup($(this).attr('value'));
	});
	
	//绑定设为常用地址事件
	$('.ads_isDefault').on('click',function(){
		CartSure.setIsDefault($(this));
	});
	
	//绑定删除地址事件
	$('.del').on('click',function(){
		CartSure.remove($(this));
	});
	
	//绑定获取验证码事件
	$('#code').on('click',function(){
		CartSure.sendCode($(this));
	});

	//绑定验证码检测事件
	$("#codetext").on("keyup",function(){
		CartSure.checkCode($(this));
	});
	//绑定订单提交页面
	$('#order_submit').on('click',function(){
		CartSure.orderSubmit($(this));
	});
})

