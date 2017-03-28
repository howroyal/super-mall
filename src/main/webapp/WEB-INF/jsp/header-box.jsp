<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<style type="text/css">
#mall_header .nav li input {
	border: 1px;
	border-radius: 4px;
	display: block;
	height: 30px;
	padding: 0 25px;
	margin: 0 0 0 100px;
	width: 300px;
	background: transparent;
	border: 1px solid #f00;
	line-height: 36px;
	float: left;
}

#mall_header .nav li input+a {
	background: #c7181e none repeat scroll 0 0;
	border: medium none;
	border-radius: 4px;
	color: #fff;
	display: block;
	height: 36px;
	line-height: 36px;
	width: 80px;
	margin: 0 0 0 10px;
	padding: 0px;
	float: left;
}

#mall_header .nav li input+a:hover {
	background: #e6292f none repeat scroll 0 0;
}
</style>
</head>
<input id='contextPath' type='hidden' value='${pageContext.request.contextPath}' />
<div class="header_box">
	<div class="w1180">
		<a href="${pageContext.request.contextPath}" class="logo logo_point"></a>
		<ul class="nav">
			<li><a href="${pageContext.request.contextPath}" class="hot"><i
					class="icon_hot"></i>商城首页</a></li>
			<li>
				<div>
					<input id='seachwords' value='${dto.k }'> <a href="javascript:void(0)" class="searchred">搜索</a>
				</div>
			</li>
		</ul>
		<div class="shopCar">
			<div class="default">
				<i class="icon_shopCar"></i> <i class="icon_shopCount">0</i> 
				<a href="${pageContext.request.contextPath}/cart.html" target="_blank">购物车</a>
			</div>
			<div class="shop_list">
				<div class="spacer"></div>
				<div class="shop_box">
					<div class="empty">购物车肚子很饿啦，赶紧选购吧</div>
					<div class="collect">
						<div class="txt">
							<span class="sum"><i class="num totals">0</i>分</span> 共<span
								class="num totalnum">0</span>件
						</div>
						<a href="${pageContext.request.contextPath}/cart.html" target="_blank" class="btn_submit">去结算</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/static/js/header-box.js"></script>
