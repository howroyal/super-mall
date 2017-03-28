<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>天选商城-订单提交结果</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" /> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css?v=1fb12f2e6c">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/vip2.css?v=09bd484578">
</head>
<body>
	<div id="mall_header">
		<c:import url="header-topbar.jsp"/>
	</div>

	<div id="cart_success_div_1" class="vip nob">
		<div id="cart_success_div_2" class="vip_head">
			<div id="cart_success_div_3" class="head_wrap clearfix">
				<a href="${pageContext.request.contextPath}"><h1 class="logo iconsub"></h1></a>
				<div id="cart_success_div_4" class="process">
					<ul>
						<li><i class="iconsub icon1"></i><span>查看购物车</span></li>
						<li class="line"></li>
						<li><i class="iconsub icon2"></i><span>确定订单信息</span></li>
						<li class="line"></li>
						<li class="active"><i class="iconsub icon3"></i><span>成功提交订单</span></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="cart_success_div_5" class="cart">
			<div id="cart_success_div_6" class="success">
				<c:if test="${!empty orderCode}">
				<i class="iconsub"></i>
				<h2>订单已成功提交，等待收货吧!</h2>
				<p><b class="red">注意：</b>您的订单我们尽快为您发货，请保持手机通畅注意查收！</p>
				<div id="cart_success_div_7" class="line"></div>
				<p>订单号：<a title='查看我的订单' href="${pageContext.request.contextPath}/my/order.html">${orderCode}</a></p>
				</c:if>
				
				<c:if test="${!empty errors }">
				<p><b class="red">以下商品未成功下单：</b></p>
				<c:forEach var="product" items="${errors }">
				<p>商品名称：<a target='_blank' href="${pageContext.request.contextPath}/item/${product.productId}.html">${product.productName}</a> 购买数量:${product.productcount}</p>
				
				</c:forEach>
				<p>可能原因：<b class="red">1.商品库存不足  2.您的余额不足</b></p>
				</c:if>
				<p>您的可用余额：<b class="red">${score}</b>元</p>
				<div id="cart_success_div_8" class="btn"><a href="${pageContext.request.contextPath}">返回天选商城</a></div>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp"/>
</body>
</html>