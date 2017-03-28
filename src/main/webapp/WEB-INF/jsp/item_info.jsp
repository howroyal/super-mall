<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${goods.name }</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/vip2.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/magicZoom/css/MagicZoom.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/magicZoom/css/ShopShow.css" type="text/css" />
</head>
<script type="text/javascript">
function iFrameHeight(){
	var ifm= document.getElementById("commentsFrame");   
	var subWeb = document.frames ? document.frames["commentsFrame"].document : ifm.contentDocument;   
	if(ifm != null && subWeb != null) {
	   ifm.height = subWeb.body.scrollHeight;
	   ifm.width = subWeb.body.scrollWidth;
	}
}
</script>
<body>
	<div id="mall_header">
		<c:import url="header-topbar.jsp"/>
		<c:import url="header-box.jsp"/>
	</div>
	<div class="vip">
		<div class="group clearfix">
			<div class="crumb">
				<i class="iconsub"></i>
				<a href="${pageContext.request.contextPath}/">商城首页</a><b>&gt;</b>
				<a href="${pageContext.request.contextPath}/items/${goods.category.id }.html">${goods.category.name }</a><b>&gt;</b><span><font color='#c7181e'>${goods.name }</font></span>
			</div>
			<div class="info">
					<div id="tsShopContainer">
					<div id="tsImgS">
						<a href="${goods.imgUrl[0] }" title="Images" class="MagicZoom" id="MagicZoom">
							<img width="100" height="100" src="${goods.imgUrl[0] }" />
						</a>
					</div>
					<div id="tsPicContainer">
							<div id="tsImgSArrL" onclick="tsScrollArrLeft()"></div>
							<div id="tsImgSCon">
								<ul>
									<c:forEach var="imgurl" items="${goods.imgUrl }" varStatus="stat">
										<li onclick="showPic(${stat.index})" rel="MagicZoom" <c:if test="${stat.index == 0}">class="tsSelectImg"</c:if>><img height="42" width="42" src="${imgurl }" tsImgS="${imgurl }" /></li>
									</c:forEach>
								</ul>
							</div>
							<div id="tsImgSArrR" onclick="tsScrollArrRight()"></div>
						</div>
						<img class="MagicZoomLoading" width="16" height="16" src="${pageContext.request.contextPath}/static/magicZoom/images/loading.gif" alt="Loading..." />
					</div>
				
				<div class="text">
					<h3>${goods.name }</h3>
					<p>
						<span>商品编号：${goods.code }</span>
						<!-- <span>我的账户余额:0</span> -->
					</p>
					<dl class="clearfix big">
						<dt>商城价:</dt>
						<dd><b>${goods.price }</b> 元</dd>
					</dl>
					<dl class="clearfix">
						<dt>市场价:</dt>
						<dd><b>${goods.marketPrice }</b> 元</dd>
					</dl>
					<dl class="clearfix">
						<dt>每件节省:</dt>
						<dd><b>${goods.marketPrice - goods.price }</b> 元</dd>
					</dl>
					<dl class="clearfix">
						<dt>库&ensp;&ensp;存:</dt>
						<dd><span>${goods.stock }</span> 件</dd>
					</dl>
					<dl class="clearfix">
						<dt>服&ensp;&ensp;务:</dt>
						<dd>由 天选商城 发货，并提供售后服务，请放心购买。</dd>
					</dl>
					<dl class="clearfix">
						<dt>购买数量：</dt>
						<dd>
							<div class="change clearfix">
								<span class="cut no iconsub"></span>
								<input id='buyCount' type="text" value="1">
								<span class="add iconsub"></span>
							</div>
						</dd>
					</dl>
					<div class="btn clearfix ${goods.id }">
						<a href="javascript:;" class="red order">立即购买</a>
						<a href="javascript:;" class="white addCart">加入购物车</a>
					</div>
					<dl class="clearfix">
						<dt>温馨提示:</dt>
						<dd>此商品支持7天无理由退款。</dd>
					</dl>
				</div>
				<div class="intro">
					<div class="introtab">
						<a href="javascript:;" class="active">商品介绍</a>
						<a href="javascript:;">商品评论</a>
						<a href="javascript:;">退换货政策</a>
					</div>
					<div class="introbox">
						${goods.description }
					</div>
					<div class="introbox comments" style="display:none;">
						<iframe id='commentsFrame' width='1100' height='100' frameborder="0" scrolling="no" marginheight="0" marginwidth="0" onLoad="iFrameHeight()">
						</iframe>					
					</div>
					<div class="introbox" style="display:none;">
						<div class="texts">
							<p>若出现以下情况，商城允许退换货：</p>
							<p class="em">1）商品本身有质量瑕疵，影响使用</p>
							<p class="em">2）所兑换的商品在运送过程中出现损毁</p>
							<p>用户可在签收日起7天内拨打找钢网客服热线，申请退换货，退回时，请务必保留原包装、内附说明书、赠品及相关文件。</p>
							<p>若出现以下情况，商城有权利不予进行商品退换货：</p>
							<p class="em">1)非商城购买的礼品</p>
							<p class="em">2)超过商城承诺的7天退换货有效时间</p>
							<p class="em">3)因非正常使用商品出现的质量问题</p>
							<p class="em">4)将商品存储、暴露在超出物品适宜的环境中造成损坏</p>
							<p class="em">5)因未经授权的修理、改动、不正确的安装造成损坏</p>
							<p class="em">6)不可抗力导致商品损坏</p>
							<p class="em">7)商品的正常磨损</p>
							<p class="em">8)在退换货之前未与商城客服取得联系，进行过退换货登记</p>
							<p class="em">9)退回礼品外包装或其他商品附属物不完整或有毁损</p>
							<p>附注：商品图片及文字仅供参考，因拍摄灯光及不同显示器色差等问题可能造成图片与实物有色差，不属质量问题，具体以实物为准。</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="ui-box" id="promises">
		<div class="serve-agree-bd">
			<dl>
				<dt>
					<i class="goods"></i><strong>正品行货</strong>
				</dt>
				<dd>天选商城向您保证所售商品均为正品行货。</dd>
				<dt>
					<i class="unprofor"></i><strong>全国联保</strong>
				</dt>
				<dd>
					凭质保证书及天选商城发票，可享受全国联保服务（奢侈品、钟表除外；奢侈品、钟表由天选联系保修，享受法定三包售后服务），与您亲临商场选购的商品享受相同的质量保证。天选商城还为您提供具有竞争力的商品价格和<a
						target="_blank" href="javascript:;">运费政策</a>，请您放心购买！ <br>
					<br>注：因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本司不能确保客户收到的货物与商城图片、产地、附件说明完全一致。只能确保为原厂正货！并且保证与当时市场上同样主流新品一致。若本商城没有及时更新，请大家谅解！
				</dd>
			</dl>
		</div>
		<br>
		<br>
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
	<script src="${pageContext.request.contextPath}/static/magicZoom/js/MagicZoom.js"></script>
	<script src="${pageContext.request.contextPath}/static/magicZoom/js/ShopShow.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/vip_group_info.js"></script>
</body>
</html>