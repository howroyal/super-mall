<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>商城-我的余额</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/vip2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/icommon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/btn.css">
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
					href="${pageContext.request.contextPath}" target="">我的商城</a>&gt;
					<span class="color">我的余额</span>
			</div>
			<c:import url="menu.jsp"></c:import>

			<div class="col_main">

				<div class="main shopping_card">
					<!-- themes star -->
					<div class="themes_title">
						<h3>我的账户余额</h3>
						<a class="blue"
							href="http://sale.feiniu.com/help_center/hc-5.html"
							target="_blank">帮助</a>
					</div>
					<!-- themes end -->
					<!-- message star -->
					<div class="message">
						<ul class="cleardix">
							<li class="mes_01"><i class="icon_purse"></i> <!-- <div class="inf_purse"> -->
								<p>可用余额</p>
								<p class="num">
									<em class="rmb">￥</em>${balance }
								</p> <!-- </div> --></li>
							<li class="mes_02"><a class="btn_recharge target_no"
								id="recharge" title="立即充值" alt="立即充值" href="javascript:void(0);"
								target="">立即充值</a></li>
						</ul>
					</div>
					
			<div class="ui_tab">
				<div class="ui_tab_content">
					<div style="display: block;" class="ui_panel"></div>
				</div>
				<ul class="ui_tab_nav">
							<li <c:if test="${empty s }">class="active"</c:if>><a class="target_no" href="${pageContext.request.contextPath}/my/balance.html" target="">全部记录（<span
									class="num vouchers_bonus_num">0</span>）
							</a></li>
							<li <c:if test="${s =='1' }">class="active"</c:if>><a class="target_no" href="${pageContext.request.contextPath}/my/balance.html?s=1" target="">充值记录（<span
									class="num vouchers_money_num">0</span>）
							</a></li>
							<li <c:if test="${s =='2' }">class="active last"</c:if><c:if test="${s !='2' }">class="last"</c:if>><a class="target_no" href="${pageContext.request.contextPath}/my/balance.html?s=2" target="">消费记录（<span
									class="num vouchers_coupon_num">1</span>）
							</a></li>
						</ul>
			</div>
					<!-- message end -->
					<!-- Details List star -->
					<div class="details_list">
						<div class="details_list_tit clearfix">
							<ul>
								<li class="col_w15">适用购买渠道</li>
								<li class="col_w15">类别</li>
								<li class="col_w10">金额</li>
								<li class="col_w20">有效期限</li>
								<li class="col_w15">充值时间</li>
							</ul>
						</div>
						<div class="details_list_cont">
						
							<c:forEach var="balance" items="${balances}">
							<ul class="clearfix">
								<li class="col_w15">全网通用</li>
								<li class="col_w15"><c:if test="${balance.type == 0}">充值</c:if><c:if test="${balance.type == 1}">消费</c:if></li>
								<li class="num col_w10">${balance.money }</li>
								<li class="col_w20">永久有效</li>
								<li class="col_w15"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${balance.createTime }" /></li>
							</ul>
							</c:forEach>
						</div>
					</div>
					<!-- Details List end -->
					<!-- page next star -->
							<div class="fn_page clearfix">
							<input type="hidden" s="${s }" pgcount="${pgcount }" current="${i }" id="prod_page_num">
								<ul>
									<li class="fn_prve <c:if test="${i==1 }">off</c:if>">
										<a id="pre" href="javascript:;" class="<c:if test="${i==1 }">target_no</c:if>" target="">
										<i class="arrow_prev"></i><span>上一页</span>
										</a>
									</li>
									<li>
										<span class="cur">${i }</span>/<span class="all">${pgcount }</span>
									</li>
									<li class="fn_next <c:if test="${i==pgcount }">off</c:if>">
										<a id="next" href="javascript:;" class="target_no" target="">
										<span>下一页</span><i class="arrow_next"></i></a>
									</li>
									<li>
										<span>到第</span>
										<input type="text" style="width: 30; height: 22" maxlength="100" name="pagenum" id="pagenum" value='${pgcount }'>
										<span>页</span>
									</li>
									<li class="goto">
										<a class="target_no" href="javascript:;" target="">跳转</a>
									</li>
								</ul>
							</div>

					<!-- page next end -->
				</div>
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
				<span>*</span>充值方式：
			</dt>
			<dd>
				<select id='payWay'>
					<option value="支付宝">支付宝</option>
					<option value="微信">微信</option>
					<option value="财付通">财付通</option>
					<option value="其他">其他</option>
				</select>
			</dd>
		</dl>
		<dl class="clearfix">
			<dt>
				<span>*</span>充值金额：
			</dt>
			<dd>
				<input type="text" placeholder="请填写充值金额"
					id='rechargeMoney' maxlength="36" />
			</dd>
		</dl>
		<dl class="clearfix">
			<dt>
				<span>*</span>付款人账号：
			</dt>
			<dd>
				<input type="text" placeholder="只需填写关键信息如:sh***@qq.ccom"
					id='payAccount' maxlength="36" />
			</dd>
		</dl>
		<dl class="clearfix">
			<dt>
				<span>*</span>支付信息：
			</dt>
			<dd>
				<textarea rows="3" cols="50" style="border: 1px solid #ccc;"
					placeholder="请填写支付详情" id='payInfo'></textarea>
			</dd>
		</dl>
		<dl class="clearfix">
			<dt>&nbsp;</dt>
			<dd>
				<a href="javascript:void(0);" class="redbtn" id='recharge_submit'>确定</a>
				<a href="javascript:void(0);" class="white" id='recharge_cancel'>取消</a>
			</dd>
		</dl>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/my/my_balance.js"></script>

</html>