<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
			<div class="sideBar_nav">
				<dl>
					<dt>
						交易中心 <a style="" id="nav_msg_reminder"
							class="msg-reminder integralhide" href="" target="_blank"> <span
							class="count"> <span class="cir-l"></span> <span
								class="cir-r"></span> <em class="cir-n">2</em>
						</span>
						</a>
					</dt>
					<dd>
						<i class="icon_square" class=""></i><a id="orderListUrl"
							href="${pageContext.request.contextPath}/my/order.html"
							class="target_no active" target="">我的订单</a>
					</dd>
					<dd>
						<i class="icon_square"></i><a id="commentUrl"
							href="${pageContext.request.contextPath}/my/comment.html"
							class="target_no " target="">我的评价</a>
					</dd>
				</dl>
				<dl>
					<dt>资产中心</dt>
					<dd class="subnav">
						<i class="icon_square"></i><a id="my_couponUrl" href="#"
							class="target_no " target="">我的优惠券</a>
						<ul>
							<li><a id="blistUrl" href="" class="target_no  active"
								target="">抵用券（<span class="num vouchers_bonus_num">0</span>）
							</a></li>
							<li><a id="voucherUrl" href="" class="target_no " target="">购物金（<span
									class="num vouchers_money_num">0</span>）
							</a></li>
							<li><a id="couponUrl" href="" class="target_no " target="">优惠券（<span
									class="num vouchers_coupon_num">1</span>）
							</a></li>
							<li><a id="redEnvelopeUrl" href="" class="target_no "
								target="">商城券（<span class="num noUseCount_num">0</span>）
							</a></li>
						</ul>
					</dd>
					<dd>
						<i class="icon_square"></i><a id="fullblistUrl" href=""
							class="target_no " target="">我的购物卡</a>
					</dd>
					<dd>
						<i class="icon_square"></i><a id="myBalanceUrl" href=""
							class="target_no " target="">账户余额</a>
					</dd>

					<dd class="subnav">
						<i class="icon_square"></i><a id="pointsUrl" href=""
							class="target_no " target="">我的积分</a>
						<ul>
							<li><a id="scoreCardUrl" href="" class="target_no "
								target="">积分换券明细</a></li>
						</ul>
					</dd>

				</dl>
				<dl>
					<dt>关注中心</dt>
					<dd>
						<i class="icon_square"></i><a id="favoriteUrl" href=""
							class="target_no " target="">我的收藏</a>
					</dd>
					<dd>
						<i class="icon_square"></i><a id="everbuyUrl" href=""
							class="target_no " target="">曾经购买</a>
					</dd>
					<dd>
						<i class="icon_square"></i><a id="historyUrl" href=""
							class="target_no " target="">我的足迹</a>
					</dd>
				</dl>
				<dl>
					<dt>个人中心</dt>
					<dd>
						<i class="icon_square"></i><a id="personal_infoUrl" href=""
							class="target_no " target="">个人信息</a>
					</dd>

					<dd>
						<i class="icon_square"></i><a id="growthUrl" href=""
							class="target_no " target="">我的等级</a>
					</dd>

					<dd>
						<i class="icon_square"></i><a id="memlistUrl" href=""
							class="target_no " target="">地址管理</a>
					</dd>
					<dd>
						<i class="icon_square"></i><a id="securityViewUrl" href=""
							class="target_no " target="">安全设置</a>
					</dd>
				</dl>
				<dl class="service_center">
					<dt>服务中心</dt>
					<dd>
						<i class="icon_square"></i><a id="subscribeUrl" href=""
							class="target_no " target="">我的消息</a>
					</dd>
					<dd>
						<i class="icon_square"></i><a id="my_returnsUrl" href=""
							class="target_no " target="">退货管理</a>
					</dd>
				</dl>
			</div>