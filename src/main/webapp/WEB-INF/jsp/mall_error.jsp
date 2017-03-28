<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>天选商城</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/public.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/vip2.css" />
</head>
<body>
	<div id="mall_header">
		<c:import url="header-topbar.jsp" />
		<c:import url="header-box.jsp" />
	</div>
	<div id="mall_error_div_3" class="col01 clearfix">
		<div id="mall_error_div_4" class="tips404">
			<h1>
				抱歉，您访问的页面不存在了<br>
			</h1>
			<ul>
				<li>你访问的商品已被抢光了！</li>
				<li>页面已被删除</li>
				<li>被外星人劫持啦</li>
			</ul>
			<h4>
				<div id="mall_error_div_5">&nbsp;</div>
				<div id="mall_error_div_6">&nbsp;</div>
				<div id="mall_error_div_7">&nbsp;</div>
				<div id="mall_error_div_8" class="btn">
					<a href="${pageContext.request.contextPath}">返回天选商城</a>
				</div>
			</h4>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
