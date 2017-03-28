<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>积分商城-商品列表</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" /> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/vip2.css"/>
</head>
<body>
	<input id='categoryId' type='hidden' value='${categoryId}'/>
	<input id='sf' type='hidden' value='${dto.sf}'/>
	<input id='st' type='hidden' value='${dto.st}'/>
	<input id='cp' type='hidden' value='${dto.cp}'/>
	<input id='pn' type='hidden' value='${dto.pn}'/>
	<input id='min' type='hidden' value='${dto.min}'/>
	<input id='max' type='hidden' value='${dto.max}'/>
	<input id='pagenum' type='hidden' value='${pagenum}'/>
	<div id="mall_header">
		<c:import url="header-topbar.jsp"/>
		<c:import url="header-box.jsp"/>
	</div>
	
	<div class="vip">
		<div class="group">
			<div class="crumb">
				<i class="iconsub"></i><a href="${pageContext.request.contextPath}">商城首页</a><b>&gt;</b><span><font color='#c7181e'>商品列表</font></span>
			</div>
			<div class="tab clearfix">
				<a href="${pageContext.request.contextPath}/items.html" class="first <c:if test="${empty categoryId }">active</c:if>"><i class="iconsub icon1"></i>全部</a>
				<a href="${pageContext.request.contextPath}/items/1.html" class="<c:if test="${ categoryId == 1 }">active</c:if>"><i class="iconsub icon2"></i>家居生活</a>
				<a href="${pageContext.request.contextPath}/items/2.html" class="<c:if test="${ categoryId == 2 }">active</c:if>"><i class="iconsub icon3"></i>数码家电</a>
				<a href="${pageContext.request.contextPath}/items/3.html" class="<c:if test="${ categoryId == 3 }">active</c:if>"><i class="iconsub icon4"></i>母婴玩具</a>
				<a href="${pageContext.request.contextPath}/items/4.html" class="<c:if test="${ categoryId == 4}">active</c:if>"><i class="iconsub icon5"></i>食品保健</a>
				<a href="${pageContext.request.contextPath}/items/5.html" class="<c:if test="${categoryId == 5}">active</c:if>"><i class="iconsub icon6"></i>美容配饰</a>
				<a href="${pageContext.request.contextPath}/items/6.html" class="<c:if test="${categoryId == 6}">active</c:if>"><i class="iconsub icon7"></i>汽车用品</a>
			</div>
			<div class="sort">
				<div class="page_simple">
					<span>${dto.cp}/${pagenum}</span>
					<a href="javascript:;">&lt;</a>
					<a href="javascript:;">&gt;</a>
				</div>
				<ul class="sorts">
					<li><a href="javascript:;" class="one <c:if test="${empty dto.sf }">active</c:if>">默认排序</a></li>
					<li><a href="javascript:;" class="one <c:if test="${dto.sf == 'sc'}">active</c:if>">销量<i class="iconsub <c:if test="${dto.sf == 'sc' and dto.st == 'a'}">up</c:if><c:if test="${dto.sf == 'sc' and dto.st == 'd'}">down</c:if>"></i></a></li>
					<li><a href="javascript:;" class="one <c:if test="${dto.sf == 'pc'}">active</c:if>">价格<i class="iconsub <c:if test="${dto.sf == 'pc' and dto.st == 'a'}">up</c:if><c:if test="${dto.sf == 'pc' and dto.st == 'd'}">down</c:if>"></i></a></li>
					<li><a href="javascript:;" class="one <c:if test="${dto.sf == 'st'}">active</c:if>">上架时间<i class="iconsub <c:if test="${dto.sf == 'st' and dto.st == 'a'}">up</c:if><c:if test="${dto.sf == 'st' and dto.st == 'd'}">down</c:if>"></i></a></li>
					<li>
						<div class="range">
							<div class="text">
								价格：<input type="text" id="priceMin" value="${dto.min}" /><span>-</span><input
									type="text" id="priceMax" value="${dto.max}" />
							</div>
							<div class="more">
								<div class="option">
									<a href="javascript:;" class='clear'>清除</a>
									<a href="javascript:;" class="priceArea red">确定</a>
								</div>
								<div class="txt clearfix">
									<a href="javascript:;" title='0-50'>0-500</a>
									<a href="javascript:;" title='50-100'>50-100</a>
									<a href="javascript:;" title='100-200'>100-200</a>
									<a href="javascript:;" title='200-500'>200-500</a>
									<a href="javascript:;" title='500-1000'>500-1000</a>
									<a href="javascript:;" title='1000-2000'>1000-2000</a>
									<a href="javascript:;" title='2000-5000'>2000-5000</a>
									<a href="javascript:;" title='5000'>5000以上</a>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div class="box">
				<div class="list clearfix">
				<c:if test="${empty goodsList }">
					<h2><font color='#e6292f'>找不到符合条件的数据啦,难道是被外星人劫持了吗?</font></h2>
				</c:if>
				<c:forEach var="goods" items="${goodsList }" varStatus="stat">
					<div class="one <c:if test="${stat.index%5==0}">first</c:if> ">
						<a href='${pageContext.request.contextPath}/item/${goods.id }.html' target="_blank">
							<img class="lazy" alt="${goods.name }" title="${goods.name }" width="180px" height="180px" data-original="${goods.mainImg }">
						</a>
						<h4 class="elli"><a href='#' title='${goods.name }'>${goods.name }</a></h4>
						<p><span>市场价: <b class='market'>${goods.marketPrice }</b></span>优惠价: <b class="pricered">${goods.price }</b></p>
						<div class="btn clearfix ${goods.id }">
							<a href="javascript:;" class="red">立即购买</a>
							<a href="javascript:;" class="white">加入购物车</a>
						</div>
					</div>
				</c:forEach>
					</div>
				</div>
				<c:import url="page.jsp"></c:import>
				
				<!-- 
				<div class="pkg_page clearfix">
					<a href="javascript:" class="up up_nocurrent">上一页</a>
					<a href="javascript:" class="current">1</a>
					<a href="javascript:">2</a>
					<a href="javascript:">3</a>
					<a href="javascript:">4</a>
					<span class="pkg_page_ellipsis">...</span>
					<a href="javascript:">99</a>
					<a href="javascript:" class="down">下一页</a>
					<span class="pkg_pagevalue">
						到<input type="text" id="iptPageTxt" class="pkg_page_num">页<a href="javascript:" class="pkg_page_submit">确定</a>
					</span>
				</div>
			 -->
			</div>
		</div>
	
	<c:import url="footer.jsp"/>
<div id="mask_success">
		<div class="mask_success">
			<p><i class="iconsub"></i>已成功加入购物车</p>
			<div class="btn">
				<a href="javascript:;" class="white">继续购物</a>
				<a href="${pageContext.request.contextPath}/cart.html" class="red">查看购物车</a>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/static/js/vip_group.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/vip_index.js"></script>
</body>
</html>