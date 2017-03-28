<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>商城-我的订单</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/vip2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/icommon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/public.min.css">
</head>
<body>
	<input id='contextPath' type='hidden'
		value='${pageContext.request.contextPath}' />
	<div id="mall_header">
		<c:import url="../header-topbar.jsp" />
		<c:import url="../header-box.jsp" />
	</div>
	<div class="vip">
		<div class="wrap">
			<div class="bread_crumbs">
				<a class="crumbs_font target_no"
					href="${pageContext.request.contextPath}" target="">我的商城</a>&gt;<a
					class="crumbs_font target_no"
					href="${pageContext.request.contextPath}/my/order.html" target="">我的订单</a>&gt;<span
					class="color">全部订单</span>
			</div>

			<c:import url="menu.jsp"></c:import>
			<div class="col_main">
				<div class="main coupon">
					<!-- themes star -->
					<div class="themes_title">
						<h3>我的订单</h3>
					</div>
					<!-- themes end -->
					<div class="ui_tab">
						<ul class="ui_tab_nav">
							<li <c:if test="${empty s }">class="active"</c:if>><a
								class="target_no"
								href="${pageContext.request.contextPath}/my/order.html"
								target="">全部订单（<span class="num vouchers_bonus_num">0</span>）
							</a></li>
							<li <c:if test="${s =='1' }">class="active"</c:if>><a
								class="target_no"
								href="${pageContext.request.contextPath}/my/order.html?s=1"
								target="">待付款（<span class="num vouchers_money_num">0</span>）
							</a></li>
							<li <c:if test="${s =='2' }">class="active last"</c:if>
								<c:if test="${s !='2' }">class="last"</c:if>><a
								class="target_no"
								href="${pageContext.request.contextPath}/my/order.html?s=2"
								target="">待评价（<span class="num vouchers_coupon_num">1</span>）
							</a></li>
						</ul>
						<div class="ui_tab_content">
							<div style="display: block;" class="ui_panel"></div>
						</div>
					</div>
					<!-- coupon voucher list star -->
					<div class="voucher_select clearfix">
						<div class="fr">
							<a title="获取更多抵用卷" alt="获取更多抵用卷" id="redeemCoupon"
								class="fn-s1 target_no" href="javascript:void (0);" target="">抵用券充值</a>
							<a title="如何获取抵用券" alt="如何获取抵用券" class="fn-s2 target_no" href=""
								target="">? 如何获取抵用券</a>
						</div>
					</div>

					<div class="details_list details_list_first">
						<div class="details_list_tit">
							<ul class="clearfix">
								<li class="col_w15">订单编号</li>
								<li class="col_w15">总金额</li>
								<li class="col_w10">支付方式</li>
								<li class="col_w15">收货人</li>
								<li class="col_w15">下单时间</li>
								<li class="col_w10">状态</li>
								<li class="col_w10">操作</li>
							</ul>
						</div>

						<div class="details_list_cont">

							<c:forEach var="order" items="${orders}">
								<ul class="clearfix">
									<li class="col_w15">${order.orderNo }</li>
									<li class="num col_w15">${order.amountPrice }</li>
									<li class="col_w10"><c:if test="${order.paymentType == 0}">在线支付</c:if>
										<c:if test="${order.paymentType == 1}">货到付款</c:if></li>
									<li class="col_w15">${order.name }</li>
									<li class="col_w15"><fmt:formatDate
											pattern="yyyy-MM-dd HH:mm:ss" value="${order.createTime }" /></li>
									<li class="col_w10"><c:choose>
											<c:when test="${order.isPay == 0}">
											待支付
										</c:when>
											<c:when test="${order.isDelivery == 0}">
											待发货
										</c:when>
											<c:when test="${order.isDelivery == 1}">
											已发货
										</c:when>
										</c:choose></li>
									<li class="col_w10"><p class="okdate">
											<c:choose>
												<c:when test="${order.isPay == 0}">
													<div class='btn'>
														<a class="btn_batch payBtn" hidden="${order.id }"
															href="javascript:;" title="支持支付宝和微信支付">支付</a>
													</div>
												</c:when>
												<c:when test="${order.isDelivery == 0}">
											确认收货
										</c:when>
												<c:when test="${order.isDelivery == 1}">
											评价
										</c:when>
											</c:choose>

										</p></li>
								</ul>
							</c:forEach>
						</div>
						<!-- page next star -->
						<div class="fn_page clearfix">
							<input type="hidden" pgcount="${pgcount }" current="${i }"
								id="prod_page_num">
							<ul>
								<li class="fn_prve <c:if test="${i==1 }">off</c:if>"><a
									id="pre" href="javascript:;" class="target_no" target=""> <i
										class="arrow_prev"></i><span>上一页</span>
								</a></li>
								<li><span class="cur">${i }</span>/<span class="all">${pgcount }</span>
								</li>
								<li class="fn_next <c:if test="${i==pgcount }">off</c:if>">
									<a id="next" href="javascript:;" class="target_no" target="">
										<span>下一页</span><i class="arrow_next"></i>
								</a>
								</li>
								<li><span>到第</span> <input type="text"
									style="width: 30; height: 22" maxlength="100" name="pagenum"
									id="pagenum"> <span>页</span></li>
								<li class="goto"><a class="target_no" href="javascript:;"
									target="">跳转</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- col main end -->
			</div>
		</div>
	</div>
	<c:import url="../footer.jsp" />
</body>
<!-- 支付-->
<div id="mask_ads_edit" style='display: none;'>
	<div id="cart_sure_div_23" class="mask_ads">
		<h2>
			<font color='red'>支付信息填写</font>
		</h2>
		<dl class="clearfix">
			<dt>&nbsp;</dt>
			<dd>
				<font color='red'>提示:</font>支付完成后,请您填写支付信息。
			</dd>
		</dl>

		<dl class="clearfix">
			<dt>
				<span>*</span>支付渠道：
			</dt>
			<dd>
				<select id='edit_provId'>
					<option value="支付宝">支付宝</option>
					<option value="微信">微信</option>
					<option value="财付通">财付通</option>
					<option value="其他">其他</option>
				</select>
			</dd>
		</dl>
		<dl class="clearfix">
			<dt>
				<span>*</span>付款人账号：
			</dt>
			<dd>
				<input type="text" placeholder="只需填写关键信息如:sh***@qq.ccom"
					id='edit_addressDetail' maxlength="36" />
			</dd>
		</dl>
		<dl class="clearfix">
			<dt>
				<span>*</span>支付信息：
			</dt>
			<dd>
				<textarea rows="5" cols="50" style="border: 1px solid #ccc;"
					placeholder="请填写支付详情" id='edit_addressDetail'></textarea>
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
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/my/my_order.js"></script>

</html>