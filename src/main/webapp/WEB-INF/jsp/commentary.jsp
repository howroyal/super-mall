<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="content-language" content="zh-cn" />
<meta name="renderer" content="webkit" />
<meta http-equiv="Cache-control" content="public" />
<title>商品评论</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/commentary/css/page.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/commentary/css/commentary.css" />
</head>

<body>
	<input id='goodsId' type="hidden" value='${goodsId}' />
	<input id='goodsCommentCount' type="hidden" value='${commentVo.commentUserNum}' />
	<input id='staticHost' type="hidden" value='${pageContext.request.contextPath}' />
	<input id='loginPathURL' type="hidden" value='${loginPathURL}' />
	<input id='myFeiniuURL' type="hidden" value='${myFeiniuURL}' />
	<div class="pro-tabs-box proAppraise">
		<div class="pa-top">
			<p class="pa-top-title">
				商品评价<font>（共<b>${commentVoDefault.commentUserNum}位</b>参加本商品评论）
				</font><font class="fr">所有商品评价均来自已购买本商品的会员</font>
			</p>
			<div class="pa-top-content">
				<div class="hp-box">
					<span><font><fmt:formatNumber
								value="${commentVoDefault.goodCommentRate}" pattern="###" /><b>%</b></font>
					<p>好评率</p> </span>
				</div>
				<ul class="three-Appraise">
					<li><span>好评<font>(<fmt:formatNumber
									value="${commentVoDefault.goodCommentRate}" pattern="###" />%)
						</font></span>
						<div>
							<em class="hp"
								style="width: <fmt:formatNumber value="${commentVoDefault.goodCommentRate}" pattern="###"/>%;"></em>
						</div></li>
					<li><span>中评<font>(<fmt:formatNumber
									value="${commentVoDefault.normalCommentRate}" pattern="###" />%)
						</font>
					</span>
						<div>
							<em class="zp"
								style="width: <fmt:formatNumber value="${commentVoDefault.normalCommentRate}" pattern="###"/>%;"></em>
						</div></li>
					<li><span>差评<font>(<fmt:formatNumber
									value="${commentVoDefault.badCommentRate}" pattern="###" />%)
						</font>
					</span>
						<div>
							<em class="cp"
								style="width: <fmt:formatNumber value="${commentVoDefault.badCommentRate}" pattern="###"/>%;"></em>
						</div></li>
				</ul>
				<div class="nr-pj">
					<p>牛人评价：</p>
					<span> <c:forEach var="impress"
							items="${commentVo.impression }">
							<font><c:out value="${impress}" /> </font>
						</c:forEach>
					</span>
				</div>
				<div class="fb-pj">
					<p>我已购买过此商品</p>
					<input type="button" class="fbpjBtn" />
					<!-- 
					<p>发表评价可获得飞牛积分哦~</p>
					<p>加精置顶还可赢双倍积分</p>
					<p>详见 <a target="_blank" href="http://sale.feiniu.com/help_center/hc-6.html" class="jflink">[积分规则]</a> </p>
					 -->
				</div>
			</div>
		</div>
		<ul class="pj-content">
			<li><a class="selected">全部评论(${commentVo.commentUserNum })</a></li>
			<li><a>好评(${commentVo.goodCommentCount })</a></li>
			<li><a>中评(${commentVo.normalCommentCount })</a></li>
			<li><a>差评(${commentVo.badCommentCount })</a></li>
			<li><a>追评(${commentVo.addCommentCount })</a></li>
			<li><a>晒单(${commentVo.commentPicCount })</a></li>
			<li><a>回复(${commentVo.replyCount })</a></li>
		</ul>
		<!-- 好评开始 -->
		<div class="pjContents clearfix" style="display: block;" id='goodDiv'>
			<c:forEach var="data" items="${pageDataVo.dataList }">
				<div class="pj-box">
					<div class="author-info">
						<div class="author-portrait">
							<a class="author-portrait-container"> <!-- <div class="portrait-cover"> -->
								<img src="${data.userPhoto }" width="70px" height="70px" />
							</a>
						</div>
						<p>${data.userName }</p>
					</div>
					<div class="pj-box-warp">
						<em class="pj-box-bg"></em>
						<div class="pj-box-content">
							<div class="pj-title">
								<span
									class="start-<fmt:formatNumber value="${data.commentStar }" pattern="#"/>"></span><span
									class="pl-date"><fmt:formatDate
										value="${data.commentDate }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
							</div>
							<ul class="pl-ul">
								<!-- 规格开始 -->
								<li class="clearfix">
									<div class="dd">规 格:</div>
									<div class="dt">
										<c:if test="${!empty data.color }">
											<span>颜色：${data.color } </span>
										</c:if>
										<c:if test="${!empty data.size }">
											<span>尺寸：${data.size } </span>
										</c:if>
									</div>
								</li>
								<!-- 印象开始 -->
								<li class="clearfix">
									<div class="dd">印 象:</div>
									<div class="dt">
										<c:forEach var="mark" items="${data.impression }">
											<p>
												<c:out value="${mark}" />
											</p>
										</c:forEach>
									</div>
								</li>
								<li class="clearfix">
									<div class="dd">体 会:</div>
									<div class="dt">
										<span>${data.commentText }</span>
									</div>
								</li>
								<!-- 晒图开始 -->
								<c:if test='${!empty data.commentPicUrls }'>
									<li class="clearfix">
										<div class="dd">晒 图:</div>
										<div class="dt">
											<ul class="fn-comment-photos">
												<c:forEach var="picUrl" items="${data.commentPicUrls }">
													<li class=""><img src="<c:out value="${picUrl}" />"
														alt=""> <b></b></li>
												</c:forEach>
											</ul>
											<div class="fn-photos-view">
												<span class="css-hook"></span><img src="" alt=""> <a
													class="view-navleft" href="javascript:;"><i></i></a> <a
													class="view-navright" href="javascript:;"><i></i></a>
											</div>
										</div>
									</li>
								</c:if>
								<li class="clearfix">
									<div class="dd">购买日期:</div>
									<div class="dt">
										<span>${data.buyDate }</span>
									</div>
								</li>
								<!-- 商家回复开始 -->
								<c:if test="${!empty data.replyText }">
									<li class="clearfix">
										<div class="dd replyByservice">商家回复:</div>
										<div class="dt">
											<span class="replyByservice">${data.replyText}</span>
										</div>
									<li class="clearfix">
										<div class="dd"></div>
										<div class="dt">
											<font class="replyTime"><fmt:formatDate
													value="${data.replyDate }" pattern="yyyy-MM-dd HH:mm:ss" /></font>
										</div>
									</li>
								</c:if>
								<!-- 追评开始 -->
								<c:if test="${!empty data.addCommentText }">
									<li class="comments-line clearfix">
										<div class="dd">追 评:</div>
										<div class="dt">
											<span>${data.addCommentText }</span>
										</div>
									</li>
									<!-- 追评日期 -->
									<li class="clearfix">
										<div class="dd"></div>
										<div class="dt">
											<font class="replyTime"><fmt:formatDate
													value="${data.addCommentDate }"
													pattern="yyyy-MM-dd HH:mm:ss" /></font>
										</div>
									</li>
									<!-- 追评晒图开始 -->
									<c:if test='${!empty data.addCommentPicUrls }'>
										<li class="clearfix">
											<div class="dd">晒 图:</div>
											<div class="dt">
												<ul class="fn-comment-photos">
													<c:forEach var="addpicUrl"
														items="${data.addCommentPicUrls }">
														<li class=""><img
															src="<c:out value="${addpicUrl}" />" alt=""> <b></b>
														</li>
													</c:forEach>
												</ul>
												<div class="fn-photos-view">
													<span class="css-hook"></span> <img src="" alt=""> <a
														class="view-navleft" href="javascript:;"><i></i></a> <a
														class="view-navright" href="javascript:;"><i></i></a>
												</div>
											</div>
										</li>
									</c:if>
									<!-- 客服回复 -->
									<c:if test="${!empty data.addCommentReplyText}">
										<li class="clearfix">
											<div class="dd replyByservice">商家回复:</div>
											<div class="dt">
												<span class="replyByservice">${data.addCommentReplyText}</span>
											</div>
										</li>
										<li class="clearfix">
											<div class="dd"></div>
											<div class="dt">
												<font class="replyTime"><fmt:formatDate
														value="${data.addCommentReplyDate }"
														pattern="yyyy-MM-dd HH:mm:ss" /></font>
											</div>
										</li>
									</c:if>
								</c:if>
								<li class="clearfix">
									<div class="dd"></div>
									<div class="dt">
										<a class="zhan" commentId='${data.id }'>赞(${data.niceCount})</a>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</c:forEach>
			<c:if test="${pageDataVo.totalPage > 1 }">
				<!-- 全部评论分页 -->
				<div class="fn_page clearfix" id='allCommentPage'>
					<ul>
						<li class="prve <c:if test="${pageDataVo.start <= 1}">off</c:if>"><a
							href="javascript:void(0);"> <i class="arrow_prev"></i> <span>上一页</span>
						</a></li>
						<c:out escapeXml="false" value="${pageHTML }"></c:out>
						<li
							class="next <c:if test="${pageDataVo.start == pageDataVo.totalPage }">off</c:if>"><a
							href="javascript:void(0);"> <span>下一页</span> <i
								class="arrow_next"></i>
						</a></li>
						<!-- 
					<li class="next off"><a href="javascript:void(0);"> <span>下一页</span>
							<i class="arrow_next"></i> </a></li>
							 -->
						<li><span>到第</span> <input id="amount"
							style="IME-MODE: disabled; WIDTH: 60px; HEIGHT: 28px"
							onkeyup="return MallCommentary.ValidateNumber(this,value)"
							maxlength="6" size="14" name="amount" type="text"
							value='${pageDataVo.start}' maxValue=${pageDataVo.totalPage } />
							<span>页</span></li>
						<li class="goto"><a href="javascript:void(0);">跳转</a></li>
					</ul>
				</div>
			</c:if>
		</div>
		<!-- 好评开始 -->
		<div class="pjContents clearfix" style="display: block;" id='goodDiv'></div>
		<!-- 中评开始 -->
		<div class="pjContents clearfix" style="display: block;"
			id='normalDiv'></div>
		<!-- 差评开始 -->
		<div class="pjContents clearfix" style="display: block;" id='badDiv'></div>
		<!-- 追评开始 -->
		<div class="pjContents clearfix" style="display: block;" id='addDiv'></div>
		<!-- 晒图开始 -->
		<div class="pjContents clearfix" style="display: block;" id='picDiv'></div>
		<!-- 回复开始 -->
		<div class="pjContents clearfix" style="display: block;" id='replyDiv'></div>
	</div>
</body>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/static/commentary/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/static/commentary/js/commentary.js"></script>
</html>
