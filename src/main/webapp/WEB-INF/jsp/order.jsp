<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>天选商城-结算</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/Validform_v5.3.2/css/style.css?v=e487e2bf4c">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/common.css?v=1fb12f2e6c">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/vip2.css?v=09bd484578">
<style type="text/css">
#loading-mask {
	top: 0;
	left: 0;
	z-index: 999;
	width: 100%;
	height: 100%;
	opacity: 0.5;
	filter: alpha(opacity = 50);
	background: #000;
	position: fixed;
}

#loading {
	border-radius: 5px;
	left: 50%;
	overflow: hidden;
	top: 50%;
	z-index: 1000;
	position: fixed;
	background: #fff;
}
</style>
</head>
<body>

	<input id='contextPath' type='hidden' value='${pageContext.request.contextPath}' />
	<input type='hidden' id='productstr' value='${productstr}' />
	<input type='hidden' id='isFromCart' value='${isFromCart}' />

	<div id='loading-mask' style='display: none;'></div>
	<div id="loading" style='display: none;'>
		<img src="${pageContext.request.contextPath}/static/img/loading.gif"
			style="margin-right: 8px; float: left; vertical-align: top;" />
		<div id="cart_sure_div_1" class="loading-indicator"></div>
	</div>
	<div id="cart_sure_div_2" class="vip nob">
		<div id="cart_sure_div_3" class="vip_head">
			<div id="cart_sure_div_4" class="head_wrap clearfix">
				<a href="${pageContext.request.contextPath}">
				<h1 class="logo iconsub"></h1></a>
				<div id="cart_sure_div_5" class="process">
					<ul>
						<li><i class="iconsub icon1"></i><span>查看购物车</span></li>
						<li class="line"></li>
						<li class="active"><i class="iconsub icon2"></i><span>确定订单信息</span></li>
						<li class="line"></li>
						<li><i class="iconsub icon3"></i><span>成功提交订单</span></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="cart_sure_div_6" class="cart">
			<h2>
				<a href="${pageContext.request.contextPath}">返回商城首页</a>订单信息
			</h2>
			<div id="cart_sure_div_7" class="order_info">
				<h3>
					选择付款方式： 
					<label class="way" value="1">
						<i class="iconsub radio"></i>货到付款
					</label> 
					<label class="way active" value="0">
						<i class="iconsub radio"></i>提交订单后付款
					</label>
				</h3>
				<h4>收货信息</h4>
				<div id="cart_sure_div_8" class="adsbox">
					<c:if test="${!empty addrs and fn:length(addrs)>= 1}">
					<div id="cart_sure_div_9" class="ads clearfix">
					<c:forEach  var="addr" items="${addrs }" varStatus="stat">
						<div class="one <c:if test="${addr.isDefault == '1' }">active</c:if>" id='${addr.id}'>
							<div id="cart_sure_div_10" class="option">
								<a href="javascript:void(0);" class='ads_edit'
									value='${addr.id}'>编辑</a> <a href="javascript:void(0);"
									class='ads_isDefault' value='${addr.id}'
									<c:if test="${addr.isDefault == '1' }">style='display:none;'</c:if>>设为常用地址</a> <a
									href="javascript:void(0);" class="del iconsub"
									value='${addr.id}'></a>
							</div>
							<i class="iconsub sure"></i>
							<h5 class='isDefault'<c:if test="${addr.isDefault != '1' }">style='display:none;'</c:if>>常用地址</h5>
							<div id="cart_sure_div_11" class="text">
								<p>
									<b class='contactName'>${addr.contactName}</b> 收
								</p>
								<p class='areaName'>${addr.provName}
									${addr.cityName} ${addr.areaName}</p>
								<p class='addressDetail'>${addr.addressDetail}</p>
								<p class='contactPhone'>${addr.contactPhone}</p>
							</div>
						</div>
					</c:forEach>
					</div>
					</c:if>
					<c:if test="${empty addrs or fn:length(addrs)<= 0}">
					
					<div id="cart_sure_div_12" class="ads clearfix"></div>
					<form id='noadsValideForm'>
						<div id="cart_sure_div_13" class="noads clearfix">
							<dl class="clearfix">
								<dt>
									<span>*</span>收&ensp;货&ensp;人：
								</dt>
								<dd>
									<input type="text" id='noadsContactName' datatype='*2-10'
										nullmsg="请输入收货人姓名！" />
								</dd>
							</dl>
							<dl class="clearfix">
								<dt>
									<span>*</span>收货地址：
								</dt>
								<dd>
									<select id='noadsProvId' datatype='*' nullmsg="请选择省/市！"
										errormsg="请选择省/市！">
										<option value=''>请选择省/市</option>
									</select> <select id='noadsCityId' datatype='*' nullmsg="请选择市/区！"
										errormsg="请选择市/区！">
										<option value=''>请选择市/区</option>
									</select> <select id='noadsAreaId' datatype='*' nullmsg="请选择区/县！"
										errormsg="请选择区/县！">
										<option value=''>请选择区/县</option>
									</select>
								</dd>
							</dl>
							<dl class="clearfix">
								<dt>
									<span>*</span>详细地址：
								</dt>
								<dd>
									<input type="text" placeholder="" id='noadsAddressDetail'
										datatype='*1-36' nullmsg="请填写详细地址！" />
								</dd>
							</dl>
							<dl class="clearfix">
								<dt>
									<span>*</span>手机号码：
								</dt>
								<dd>
									<input type="text" placeholder="" id='noadsContactPhone'
										style="width: 308px;" datatype='mobile' nullmsg="请填写常用手机号码！"
										errormsg="请填写正确的手机号码！" />
								</dd>
							</dl>
							<dl class="clearfix">
								<dd class="dt">固定电话：</dd>
								<dd>
									<input type="text" id='noadscontactTel'
										style="width: 308px;" datatype='phone' ignore="ignore"
										errormsg="请填写正确的电话号码！" />
								</dd>
							</dl>
							<dl class="clearfix">
								<dt>&nbsp;</dt>
								<dd>
									<label><i
										class="no_addr_defalut_check iconsub checkbox check"
										id='noadsIsDefault'></i>设为常用地址</label>
								</dd>
							</dl>
							<dl class="clearfix">
								<dt>&nbsp;</dt>
								<dd>
									<a href="javascript:void(0);" class="redbtn"
										id='no_addr_add_submit'>确定</a>
								</dd>
							</dl>
						</div>
					</form>
					</c:if>
					<div id="cart_sure_div_14" class="addads"
						<c:if test="${empty addrs or fn:length(addrs)<=0}">style='display:none;'</c:if>> <a href="javascript:;" id="open">使用新地址</a>
					</div>
				</div>

				<div id="cart_sure_div_17" class="line"></div>
				<h3 class="nob">商品信息</h3>
				<div id="cart_sure_div_18" class="table">
					<table id="cart_sure_table_1" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th width="120">序号</th>
							<th width="120">商品图片</th>
							<th width="630">商品名称</th>
							<th width="180">数量</th>
							<th>所需金额</th>
						</tr>
						
						<c:forEach  var="product" items="${cartModel.products }" varStatus="stat">
							<c:if test="${product.checked }">
								<tr>
									<td rowspan="">${stat.index+1}</td>
									<td rowspan=""><img width=50 width=50 src='${product.productPicUrl }' alt='${product.productName}'></td>
									<td><a target='_blank' href="${pageContext.request.contextPath}/item/${product.productId}.html">${product.productName}</a></td>
									<td>${product.productCount}</td>
									<td>${product.productCount * product.productScoreSale}</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>

				<div id="cart_sure_div_19" class="remarks clearfix">
					<label>订单备注:</label> <input type="text" id="remark" maxlength="60" />
				</div>
				<!--  
				<div id="cart_sure_div_20" class="code clearfix">
					<a href="javascript:;" id="code">获取验证码</a> <input type="text"
						id="codetext" />
					<p>
						&nbsp;<span style="display: none;">验证码已发送到您注册时的手机上</span>
					</p>
				</div>
				-->
				<div id="cart_sure_div_21" class="btn clearfix">
					<a href="javascript:;" class="" id='order_submit'>提交订单</a>
					<p>
						合计： <span><b>${cartModel.totalScoreSale}</b>元</span>
					</p>
					<p>
						件数： <b>${cartModel.totalCount}</b>件
					</p>
				</div>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp" />

	<div id="mask_ads">
		<form id='newValideForm'>
			<div id="cart_sure_div_22" class="mask_ads">
				<h2>收货信息</h2>
				<dl class="clearfix">
					<dt>
						<span>*</span>收&ensp;货&ensp;人：
					</dt>
					<dd>
						<input type="text" value='' id='new_contactName' maxlength="10"
							datatype='*2-10' nullmsg="请输入收货人姓名！" />
					</dd>
				</dl>
				<dl class="clearfix">
					<dt>
						<span>*</span>收货地址：
					</dt>
					<dd>
						<select id='new_provId' datatype='*' nullmsg="请选择省/市！"><option
								value=''>请选择省/市</option></select> <select id='new_cityId' datatype='*'
							nullmsg="请选择市/区！"><option value=''>请选择市/区</option></select> <select
							id='new_areaId' datatype='*' nullmsg="请选择区/县！"><option
								value=''>请选择区/县</option></select>
					</dd>
				</dl>
				<dl class="clearfix">
					<dt>
						<span>*</span>详细地址：
					</dt>
					<dd>
						<input type="text" id='new_addressDetail' placeholder=""
							maxlength="36" datatype='*1-36' nullmsg="请填写详细地址！" />
					</dd>
				</dl>
				<dl class="clearfix">
					<dt>
						<span>*</span>手机号码：
					</dt>
					<dd>
						<input type="text" id='new_contactPhone' placeholder=""
							style="width: 308px;" maxlength="13" datatype='mobile'
							nullmsg="请填写常用手机号码！" errormsg="请填写正确的手机号码！" />
					</dd>
				</dl>
				<dl class="clearfix">
					<dd class="dt">固定电话：</dd>
					<dd>
						<input type="text" id='new_contactTel' style="width: 308px;"
							maxlength="13" datatype='phone,*6-13' ignore="ignore"
							errormsg="请填写正确的电话号码！" />
					</dd>
				</dl>
				<dl class="clearfix">
					<dt>&nbsp;</dt>
					<dd>
						<label><i class="iconsub checkbox check"
							id='new_isDefault'></i>设为常用地址</label>
					</dd>
				</dl>
				<dl class="clearfix">
					<dt>&nbsp;</dt>
					<dd>
						<a href="javascript:void(0);" class="redbtn" id='add_submit'>确定</a>
						<a href="javascript:void(0);" class="white" id='add_cancel'>取消</a>
					</dd>
				</dl>
			</div>
		</form>
	</div>

	<!-- 编辑-->
	<div id="mask_ads_edit" style='display: none;'>
		<form id='editValideForm'>
			<div id="cart_sure_div_23" class="mask_ads">
				<h2>收货信息</h2>
				<dl class="clearfix">
					<dt>
						<span>*</span>收&ensp;货&ensp;人：
					</dt>
					<dd>
						<input type="text" id='edit_contactName' maxlength="10"
							datatype='*2-10' nullmsg="请输入收货人姓名！" />
					</dd>
				</dl>
				<dl class="clearfix">
					<dt>
						<span>*</span>收货地址：
					</dt>
					<dd>
						<select id='edit_provId' datatype='*' nullmsg="请选择省/市！">
							<!--<option value=''>请选择省/市</option>-->
						</select> <select id='edit_cityId' datatype='*' nullmsg="请选择市/区！">
							<!--<option value=''>请选择市/区</option>-->
						</select> <select id='edit_areaId' datatype='*' nullmsg="请选择区/县！">
							<!--<option value=''>请选择区/县</option>-->
						</select>
					</dd>
				</dl>
				<dl class="clearfix">
					<dt>
						<span>*</span>详细地址：
					</dt>
					<dd>
						<input type="text" placeholder="" id='edit_addressDetail'
							maxlength="36" datatype='*1-36' nullmsg="请填写详细地址！" />
					</dd>
				</dl>
				<dl class="clearfix">
					<dt>
						<span>*</span>手机号码：
					</dt>
					<dd>
						<input type="text" placeholder="" id='edit_contactPhone'
							style="width: 308px;" maxlength="13" datatype='mobile'
							nullmsg="请填写常用手机号码！" errormsg="请填写正确的手机号码！" />
					</dd>
				</dl>
				<dl class="clearfix">
					<dd class="dt">固定电话：</dd>
					<dd>
						<input type="text" id='edit_contactTel' style="width: 308px;"
							maxlength="13" datatype='phone,*6-13' ignore="ignore"
							errormsg="请填写正确的电话号码！" />
					</dd>
					</dd>
				</dl>
				<dl class="clearfix">
					<dt>&nbsp;</dt>
					<dd>
						<label><i class="iconsub checkbox" id='edit_isDefault'></i>设为常用地址</label>
					</dd>
				</dl>
				<dl class="clearfix">
					<dt>&nbsp;</dt>
					<dd>
						<a href="javascript:void(0);" class="redbtn" id='edit_submit'>确定</a>
						<a href="javascript:void(0);" class="white" id='edit_cancel'>取消</a>
					</dd>
				</dl>
			</div>
		</form>
	</div>

	<!-- 确认框
	<div id="confirm" style='display:none;'>
		<div id="cart_sure_div_24" class="mask_ads">
			<h2 id='confirmText'>删除地址数据暂不可恢复,确认删除吗?</h2>
			<dl class="clearfix">
				<dt>&nbsp;</dt>
				<dd>
					<a href="javascript:void(0);" class="redbtn" id='remove_submit'>确定</a>
					<a href="javascript:void(0);" class="white" id='remove_cancel'>取消</a>
				</dd>
			</dl>
		</div>
	</div>
	-->
	<!-- 提示框
	<div id="alertDialog" style='display:none;'>
		<div id="cart_sure_div_25" class="mask_ads">
			<h2 id='alertDialogText'></h2>
			<dl class="clearfix">
				<dt>&nbsp;</dt>
				<dd>
					<a href="javascript:void(0);" class="redbtn" id='close'>确定</a>
				</dd>
			</dl>
		</div>
	</div>
	-->

	<div id="confirm">
		<div id="cart_sure_div_26" class="mask_warning">
			<p>
				<i class="iconsub"></i>删除地址后暂不可恢复,确认删除吗?
			</p>
			<div id="cart_sure_div_27" class="btn">
				<a href="javascript:void(0);" class="redbtn" id='remove_submit'>确定</a>
				<a href="javascript:void(0);" class="white" id='remove_cancel'>取消</a>
			</div>
		</div>
	</div>

	<div id="mask_nologin">
		<div id="cart_sure_div_28" class="mask_nologin">
			<p id='alertDialogText'></p>
			<div id="cart_sure_div_29" class="btn">
				<a href="javascript:void(0);" class="redbtn" id='close'>确定</a>
			</div>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/static/js/zquery.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/public.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/Validform_v5.3.2/js/Validform_v5.3.2_min.js?v=b83a39d41e"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/vip_cart_sure.js?v=0f1df95ec9"></script>
</body>
</html>
