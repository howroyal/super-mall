<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城管理系统</title>
<link href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
<style>
#main-nav {
	margin-left: 1px;
}

#main-nav.nav-tabs.nav-stacked>li>a {
	padding: 10px 8px;
	font-size: 12px;
	font-weight: 600;
	color: #4A515B;
	background: #E9E9E9;
	background: -moz-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #FAFAFA),
		color-stop(100%, #E9E9E9));
	background: -webkit-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
	background: -o-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
	background: -ms-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
	background: linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA',
		endColorstr='#E9E9E9');
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9')";
	border: 1px solid #D5D5D5;
	border-radius: 4px;
}

#main-nav.nav-tabs.nav-stacked>li>a>span {
	color: #4A515B;
}

#main-nav.nav-tabs.nav-stacked>li.active>a, #main-nav.nav-tabs.nav-stacked>li>a:hover
	{
	color: #FFF;
	background: #3C4049;
	background: -moz-linear-gradient(top, #4A515B 0%, #3C4049 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #4A515B),
		color-stop(100%, #3C4049));
	background: -webkit-linear-gradient(top, #4A515B 0%, #3C4049 100%);
	background: -o-linear-gradient(top, #4A515B 0%, #3C4049 100%);
	background: -ms-linear-gradient(top, #4A515B 0%, #3C4049 100%);
	background: linear-gradient(top, #4A515B 0%, #3C4049 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B',
		endColorstr='#3C4049');
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049')";
	border-color: #2B2E33;
}

#main-nav.nav-tabs.nav-stacked>li.active>a, #main-nav.nav-tabs.nav-stacked>li>a:hover>span
	{
	color: #FFF;
}

#main-nav.nav-tabs.nav-stacked>li {
	margin-bottom: 4px;
}

/*定义二级菜单样式*/
.secondmenu a {
	font-size: 12px;
	color: #4A515B;
	text-align: center;
}

.navbar-static-top {
	background-color: #212121;
	margin-bottom: 5px;
}

.navbar-brand {
	background: url('') no-repeat 10px 8px;
	display: inline-block;
	vertical-align: middle;
	padding-left: 40px;
	color: #fff;
}
</style>
<script type="text/javascript">
function iFrameHeight(){
	var ifm= document.getElementById("mainFrame");   
	var subWeb = document.frames ? document.frames["mainFrame"].document : ifm.contentDocument;   
	if(ifm != null && subWeb != null) {
	   ifm.height = subWeb.body.scrollHeight < 600 ? 600 : subWeb.body.scrollHeight;
	   ifm.width = subWeb.body.scrollWidth > 1100 ? 1100 : subWeb.body.scrollWidth;
	   console.log(ifm.width);
	}
}

</script>
</head>

<body>
	<input id='contextPath' type='hidden' value='${pageContext.request.contextPath}' />
	<div class="navbar navbar-duomi navbar-static-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#" id="logo">商城管理系统</a>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
					<li class="active li" value="${pageContext.request.contextPath}">
						<a href="javascript:;"> 
							<i class="glyphicon glyphicon-th-large"></i> 首页
						</a>
					</li>
					<li>
						<a href="#cms" class="nav-header collapsed" data-toggle="collapse"> 
							<i class="glyphicon glyphicon-cog"></i> 商城CMS
							<span class="pull-right glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul id="cms" class="nav nav-list collapse secondmenu" style="height: 0px;">
							<li class="li" value="${pageContext.request.contextPath}/activeManager/"><a href="javascript:;"><i class="glyphicon glyphicon-user"></i>活动管理</a></li>
							<li class="li" value="${pageContext.request.contextPath}/bannerManager/"><a href="javascript:;"><i class="glyphicon glyphicon-th-list"></i>轮播管理</a></li>
						</ul>
					</li>
					<li>
						<a href="#systemSetting" class="nav-header collapsed" data-toggle="collapse"> 
							<i class="glyphicon glyphicon-cog"></i> 用户管理
							<span class="pull-right glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul id="systemSetting" class="nav nav-list collapse secondmenu" style="height: 0px;">
							<li class="li" value="${pageContext.request.contextPath}/userManager/"><a href="javascript:;"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
							<li class="li" value="${pageContext.request.contextPath}/scoreManager/"><a href="javascript:;"><i class="glyphicon glyphicon-th-list"></i>积分管理</a></li>
						</ul>
					</li>
					<li class="li" value="${pageContext.request.contextPath}/goodsManager/">
						<a href="javascript:;"> <i class="glyphicon glyphicon-credit-card"></i> 商品管理</a>
					</li>
					<li>
						<a href="#finance" class="nav-header collapsed" data-toggle="collapse"> 
							<i class="glyphicon glyphicon-cog"></i> 财务管理
							<span class="pull-right glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul id="finance" class="nav nav-list collapse secondmenu" style="height: 0px;">
							<li class="li" value="${pageContext.request.contextPath}/balanceManager/"><a href="javascript:;"><i class="glyphicon glyphicon-user"></i>余额管理</a></li>
							<li class="li" value="${pageContext.request.contextPath}/rechargeManager/"><a href="javascript:;"><i class="glyphicon glyphicon-th-list"></i>充值记录</a></li>
						</ul>
					</li>
					<li class="li" value="${pageContext.request.contextPath}/orderManager/">
						<a href="javascript:;"> 
							<i class="glyphicon glyphicon-globe"></i> 订单管理 
							<span class="label label-warning pull-right">5</span>
						</a>
					</li>

					<li>
						<a href="javascript:;"> 
							<i class="glyphicon glyphicon-calendar"></i> 图表统计
						</a>
					</li>
					<li>
						<a href="javascript:;"> 
							<i class="glyphicon glyphicon-fire"></i>关于系统
						</a>
					</li>

				</ul>
			</div>
			<div class="col-md-10">
				<iframe id='mainFrame' width='1100' height='600' frameborder="0" scrolling="no" marginheight="0" marginwidth="0" onLoad="iFrameHeight()">
				</iframe>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/admin/index.js"></script>
</body>
</html>
