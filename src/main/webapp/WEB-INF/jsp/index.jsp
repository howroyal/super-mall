<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>天选商城</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/vip2.css">
</head>
<body>
	<div id="mall_header">
	<c:import url="header-topbar.jsp"/>
	<c:import url="header-box.jsp"/>
	</div>

	<div class="vip">
		<div class="top clearfix">
			<div class="t_left clearfix">
				<div class="menu">
					<a href="${pageContext.request.contextPath}/items/1.html" target='_blank'><i class="icon icon1"></i>家居生活</a>
					<a href="${pageContext.request.contextPath}/items/2.html" target='_blank'><i class="icon icon2"></i>数码电器</a>
					<a href="${pageContext.request.contextPath}/items/3.html" target='_blank'><i class="icon icon3"></i>母婴玩具</a>
					<a href="${pageContext.request.contextPath}/items/4.html" target='_blank'><i class="icon icon4"></i>食品保健</a>
					<a href="${pageContext.request.contextPath}/items/5.html" target='_blank'><i class="icon icon5"></i>美容配饰</a>
					<a href="${pageContext.request.contextPath}/items/6.html" target='_blank'><i class="icon icon6"></i>汽车用品</a>
				</div>
				<div class="banner">
					<div class="slides_container">
						<c:forEach var="bannerInfo" items="${bannerInfos}" varStatus="status">
        					 <div style="<c:if test="${status.index !=0 }">display:none;</c:if>z-index: ${status.index }; background: url(${bannerInfo.picUrl}) no-repeat; height:300px;">
							<a href="${bannerInfo.url}" target="_blank"></a>
						</div> 
						</c:forEach> 
					</div>
				</div>
				<div class="bound">
					<div class="title">
						<h2 class="icon">活动商品</h2>
					</div>
					<div class="list">
						<c:forEach var="goods" end='3' items="${panicBuyGoodsList }" varStatus="stat">
						<div class="one <c:if test="${stat.last}">last</c:if>">
							<img title='${goods.name }'  class="lazy" alt="${goods.name }" width="180px" height="180px" data-original="${goods.mainImg }" style="cursor:pointer" onclick="window.open('item/${goods.id }.html')">
							<h4  title='${goods.name }' class="elli">${goods.name }</h4>
							<p><span>市场价: <b class='market'>${goods.marketPrice }</b> </span>抢购价: <b>${goods.price }</b></p>
							<div class="btn clearfix ${goods.id }">
									<a href="javascript:;" class="red">立即购买</a>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="t_right">
				<div class="luck"><a href="#"><img src="static/img/guanggao.png"></a></div>
				<div class="luck"><a href="#"><img src="static/img/guanggao.png"></a></div>
				<div class="love">
					<div class="title change">
						<a href="javascript:;"><i class="icon"></i>换一换</a>
						<h2><i class="icon"></i>猜你喜欢</h2>
					</div>
					<div class="list changelist">
						<c:forEach var="goods" items="${guesGoods }" varStatus="stat">
						<a href="item/${goods.id}.html" target="_blank">
						<div class="one <c:if test="${stat.index%2==0 }">even</c:if>">
							<img title='${goods.name }' class="lazy" alt="${goods.name }" width="72px" height="72px" data-original="${goods.mainImg }">
							<h4  title='${goods.name }' class="elli">${goods.name }</h4>
							<p>优惠价: <b>${goods.price }</b></p>
						</div>
						</a>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="center">
			<div class="box">
				<div class="title new">
					<h2 class="icon icon1">新品推荐</h2>
					<a href="items.html?sf=st&st=d" target="_blank">更多&nbsp;&gt;</a>
				</div>
				<div class="list clearfix slides_container">
					<c:forEach var="goods" items="${jptjGoods }" varStatus="stat">
						<c:if test="${stat.index%5==0 }">
				    		<div class="list_box">
				    	 		<div class="one first">
						</c:if>
						<c:if test="${stat.index % 5 != 0 }">
				    			<div class="one">
						</c:if>
								<img title='${goods.name }' src="${goods.mainImg}" alt="图片无法显示" style="cursor:pointer" onclick="window.open('item/${goods.id }.html')">
								<h4 title='${goods.name }' class="elli" style="cursor:pointer" onmouseout="this.style.color='';" onMouseOver="this.style.color='#c7181e';" onclick="window.open('item/${goods.id }.html">${goods.name}</h4>
								<p><span>市场价: <b class='market'>${goods.marketPrice }</b></span>优惠价: <b class="pricered">${goods.price }</b></p>
								<div class="btn clearfix ${goods.id }">
									<a href="javascript:;" class="red">立即购买</a>
									<a href="javascript:;" class="white">加入购物车</a>
								</div>
								</div>
						<c:if test="${stat.index%5 == 4 or stat.count == jptjGoods.size()}">
						</div>
						</c:if>
					</c:forEach>
					</div>
			</div>
			
			<div class="box">
				<div class="title title1">
					<h2 class="icon icon1">家居生活</h2>
					<a href="items/1.html" target="_blank">更多&nbsp;&gt;</a>
				</div>
				<div class="list clearfix">
					<c:forEach var="goods" items="${goods1 }" varStatus="stat">
					<div class="one <c:if test="${stat.index==0}">first</c:if> ">
						<a href='item/${goods.id }.html' target="_blank">
							<img class="lazy" alt="${goods.name }" title="${goods.name }" width="180px" height="180px" data-original="${goods.mainImg }">
						</a>
						<h4 class="elli"><a href='item/${goods.id }.html' target="_blank" title='${goods.name }'>${goods.name }</a></h4>
						<p><span>市场价: <b class='market'>${goods.marketPrice }</b></span>优惠价: <b class="pricered">${goods.price }</b></p>
						<div class="btn clearfix ${goods.id }">
							<a href="javascript:;" class="red">立即购买</a>
							<a href="javascript:;" class="white">加入购物车</a>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
			<div class="box">
				<div class="title title2">
					<h2 class="icon icon1">数码电器</h2>
					<a href="items/2.html" target="_blank">更多&nbsp;&gt;</a>
				</div>
				<div class="list clearfix">
					<c:forEach var="goods" items="${goods2 }" varStatus="stat">
					<div class="one <c:if test="${stat.index==0}">first</c:if> ">
						<a href='item/${goods.id }.html' target="_blank">
							<img class="lazy" alt="${goods.name }" title="${goods.name }" width="180px" height="180px" data-original="${goods.mainImg }">
						</a>
						<h4 class="elli"><a href='item/${goods.id }.html' target="_blank" title='${goods.name }'>${goods.name }</a></h4>
						<p><span>市场价: <b class='market'>${goods.marketPrice }</b></span>优惠价: <b class="pricered">${goods.price }</b></p>
						<div class="btn clearfix ${goods.id }">
							<a href="javascript:;" class="red">立即购买</a>
							<a href="javascript:;" class="white">加入购物车</a>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
			<div class="box">
				<div class="title title3">
					<h2 class="icon icon1">母婴玩具</h2>
					<a href="items/3.html" target="_blank">更多&nbsp;&gt;</a>
				</div>
				<div class="list clearfix">
					<c:forEach var="goods" items="${goods3 }" varStatus="stat">
					<div class="one <c:if test="${stat.index==0}">first</c:if> ">
						<a href='item/${goods.id }.html' target="_blank">
							<img class="lazy" alt="${goods.name }" title="${goods.name }" width="180px" height="180px" data-original="${goods.mainImg }">
						</a>
						<h4 class="elli"><a href='item/${goods.id }.html' target="_blank" title='${goods.name }'>${goods.name }</a></h4>
						<p><span>市场价: <b class='market'>${goods.marketPrice }</b></span>优惠价: <b class="pricered">${goods.price }</b></p>
						<div class="btn clearfix ${goods.id }">
							<a href="javascript:;" class="red">立即购买</a>
							<a href="javascript:;" class="white">加入购物车</a>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
			<div class="box">
				<div class="title title4">
					<h2 class="icon icon1">食品保健</h2>
					<a href="items/4.html" target="_blank">更多&nbsp;&gt;</a>
				</div>
				<div class="list clearfix">
					<c:forEach var="goods" items="${goods4 }" varStatus="stat">
					<div class="one <c:if test="${stat.index==0}">first</c:if> ">
						<a href='item/${goods.id }.html' target="_blank">
							<img class="lazy" alt="${goods.name }" title="${goods.name }" width="180px" height="180px" data-original="${goods.mainImg }">
						</a>
						<h4 class="elli"><a href='item/${goods.id }.html' target="_blank" title='${goods.name }'>${goods.name }</a></h4>
						<p><span>市场价: <b class='market'>${goods.marketPrice }</b></span>优惠价: <b class="pricered">${goods.price }</b></p>
						<div class="btn clearfix ${goods.id }">
							<a href="javascript:;" class="red">立即购买</a>
							<a href="javascript:;" class="white">加入购物车</a>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
			<div class="box">
				<div class="title title5">
					<h2 class="icon icon1">美容配饰</h2>
					<a href="items/5.html" target="_blank">更多&nbsp;&gt;</a>
				</div>
				<div class="list clearfix">
					<c:forEach var="goods" items="${goods5 }" varStatus="stat">
					<div class="one <c:if test="${stat.index==0}">first</c:if> ">
						<a href='item/${goods.id }.html' target="_blank">
							<img class="lazy" alt="${goods.name }" title="${goods.name }" width="180px" height="180px" data-original="${goods.mainImg }">
						</a>
						<h4 class="elli"><a href='item/${goods.id }.html' target="_blank" title='${goods.name }'>${goods.name }</a></h4>
						<p><span>市场价: <b class='market'>${goods.marketPrice }</b></span>优惠价: <b class="pricered">${goods.price }</b></p>
						<div class="btn clearfix ${goods.id }">
							<a href="javascript:;" class="red">立即购买</a>
							<a href="javascript:;" class="white">加入购物车</a>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
			<div class="box">
				<div class="title title6">
					<h2 class="icon icon1">汽车用品</h2>
					<a href="items/6.html" target="_blank">更多&nbsp;&gt;</a>
				</div>
				<div class="list clearfix">
					<c:forEach var="goods" items="${goods6 }" varStatus="stat">
					<div class="one <c:if test="${stat.index==0}">first</c:if> ">
						<a href='item/${goods.id }.html' target="_blank">
							<img class="lazy" alt="${goods.name }" title="${goods.name }" width="180px" height="180px" data-original="${goods.mainImg }">
						</a>
						<h4 class="elli"><a href='item/${goods.id }.html' target="_blank" title='${goods.name }'>${goods.name }</a></h4>
						<p><span>市场价: <b class='market'>${goods.marketPrice }</b></span>优惠价: <b class="pricered">${goods.price }</b></p>
						<div class="btn clearfix ${goods.id }">
							<a href="javascript:;" class="red">立即购买</a>
							<a href="javascript:;" class="white">加入购物车</a>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	
	<div id="mask_success">
		<div class="mask_success">
			<p><i class="iconsub"></i>已成功加入购物车</p>
			<div class="btn">
				<a href="javascript:;" class="white">继续购物</a>
				<a href="${pageContext.request.contextPath}/cart.html" class="red">查看购物车</a>
			</div>
		</div>
	</div>
	<div id="mask_nologin">
		<div class="mask_nologin">
			<p><i class="iconsub"></i>您还未登录</p>
			<div class="btn">
				<a href="javascript:;" class="red">立即登录</a>
			</div>
		</div>
	</div>
	<div id="mask_nointegral">
		<div class="mask_nointegral">
			<p><i class="iconsub"></i>积分不足</p>
		</div>
	</div>
	<div id="mask_rule">
		<div class="mask_rule">
			<p>1.每天16:00准时开启本活动。</p>
			<p>2.每个账号每天限兑换1次；每次可兑换1张购物卡。</p>
			<p>3.本活动最终解释权归找钢网所有。</p>
		</div>
	</div>
	<c:import url="footer.jsp"/>
	<script src="${pageContext.request.contextPath}/static/js/vip_index.js"></script>
</body>
</html>