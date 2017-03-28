<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>天选商城-购物车</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" /> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/vip2.css">
	<style type="text/css">
			#loading-mask{
		    top: 0;
		    left: 0;
		    z-index: 999;
		    width: 100%;
		    height: 100%;
		    opacity: 0.5;
		    filter:alpha(opacity=50);  
		    background: #000;
			position:fixed;
	    }
	    #loading{
	     	border-radius: 5px;
	   	 	left: 50%;
	    	overflow: hidden;
	    	top: 50%;
	    	z-index: 1000;
	        position:fixed; 
	        background:#fff;
		 }
	</style>
	
</head>
<body>
<div id='loading-mask' style='display: none;'></div>
<div id="loading" style='display: none;'>
 <img src="${pageContext.request.contextPath}/static/img/loading.gif?v=8d4de8b51b" style="margin-right:8px;float:left;vertical-align:top;"/> 
    <div id="cart_div_1" class="loading-indicator">
    </div>
</div>
	<div id="mall_header">
	<c:import url="header-topbar.jsp"/>
	<input id='contextPath' type='hidden' value='${pageContext.request.contextPath}'/>
	</div>
	<div id="cart_div_2" class="vip nob">
		<div id="cart_div_3" class="vip_head">
			<div id="cart_div_4" class="head_wrap clearfix">
				<a href="${pageContext.request.contextPath}"><h1 class="logo iconsub"></h1></a>
				<div id="cart_div_5" class="process">
					<ul>
						<li class="active"><i class="iconsub icon1"></i><span>查看购物车</span></li>
						<li class="line"></li>
						<li><i class="iconsub icon2"></i><span>确定订单信息</span></li>
						<li class="line"></li>
						<li><i class="iconsub icon3"></i><span>成功提交订单</span></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="cart_div_6" class="cart">
			<h2><a href="${pageContext.request.contextPath}">返回商城首页</a>我的购物车</h2>
			<div id="cart_div_7" class="list">
				<table id="cart_table_1" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th width="60" class="first"><label><i class="allcheck iconsub checkbox <c:if test="${dto.allChecked }">check</c:if>"></i>全选</label></th>
						<th width="110">&nbsp;</th>
						<th width="340">商品信息</th>
						<th width="120">可供量</th>
						<th width="240">数量</th>
						<th width="160">价格</th>
						<th>操作</th>
					</tr>
				<c:forEach var="product" items="${dto.products}" varStatus="stat">
					<tr>
						<td class="first"><i class="single iconsub checkbox <c:if test="${product.checked}">check</c:if>" value="${product.productId}"></i></td>
						<td class="tl"><img src="${product.productPicUrl}"></td>
						<td ><a target='_blank' href="${pageContext.request.contextPath}/item/${product.productId}.html">${product.productName}</a></td>
						<td><span class='stock${product.productId}'>${product.productStock}</span></td>
						<td>
							<div id="cart_div_8" class="change clearfix">
								<span class="cut <c:if test="${product.productCount <= 1}">no</c:if> iconsub" value="${product.productId}"></span>
								<input id="cart_input_1" type="text" value="${product.productCount}" class="${product.productId} countInput" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="5" size="14">
								<span class="add <c:if test="${product.productCount >= product.productStock }">no</c:if> iconsub" value="${product.productId}"></span>
							</div>
						</td>
						<td><span class="red"><b class='score${product.productId}'>${product.productCount * product.productScoreSale}</b>分</span></td>
						<td><a href="javascript:void(0);" class='delete' value="${product.productId}">删除</a></td>
					</tr>
				</c:forEach>
				
				</table>
				
				<div id="cart_div_9" class="total">
					<label><i class="allcheck iconsub checkbox <c:if test="${dto.allChecked }">check</c:if>"></i>全选</label>
					<div id="cart_div_10" class="num">您的可用余额: 0 元</div>
					<a href="javascript:void(0);" class="btn <c:if test="${dto.totalCount <= 0 }">no</c:if>">立即购买</a>
					<div id="cart_div_11" class="money">合计<br><span><b class='totalScoreSale'>${dto.totalScoreSale}</b>元</span></div>
					<div id="cart_div_12" class="text">件数<br><b class='totalCount'>${dto.totalCount}</b>件</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="confirm">
		<div id="cart_div_14" class="mask_warning">
			<p><i class="iconsub"></i>删除购物车数据暂不可恢复,确认删除吗?</p>
			<div id="cart_div_15" class="btn">
					<a href="javascript:void(0);" class="red" id='edit_submit'>确定</a>
					<a href="javascript:void(0);" class="white" id='edit_cancel'>取消</a>
			</div>
		</div>
	</div>
	
	<div id="mask_nologin">
		<div id="cart_div_16" class="mask_nologin">
			<p id='alertDialogText'></p>
			<div id="cart_div_17" class="btn">
				<a href="javascript:void(0);" class="red" id='edit_submit'>确定</a>
			</div>
		</div>
	</div>

	<c:import url="footer.jsp"/>
	<script src="${pageContext.request.contextPath}/static/js/zquery.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/public.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/vip_cart_cart.js"></script>
</body>
</html>
