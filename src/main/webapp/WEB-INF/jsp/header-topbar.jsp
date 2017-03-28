<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input id='contextPath' type='hidden' value='${pageContext.request.contextPath}' />
<div class="topbar">
	<div class="w1180">
		<div class="toplink">
			<a href=""><i class="home"></i>商城首页</a> <a href="javascript:"
				id="userName" style="display: none;">您好,</a> <span>喵，欢迎来商城！</span> <a
				href="javascript:" id="btn_login">请登录</a> <a href="javascript:"
				id="btn_register">免费注册</a> <a href="javascript:" id="btn_quit"
				style="display: none;">退出</a>
		</div>
		<div class="topmenu">
			<a href="${pageContext.request.contextPath}/my/order.html" target="_blank">我的订单</a>|
			<!-- 
					<div class="msn">
						<a href="#" class="msn_center">消息中心<span>5</span></a>
						<div class="msn_box">
							尚未认证企业<a href="#">去验证</a><br/>
							积分规则调整公告<a href="#">查看详情</a>
						</div>
					</div>|
					-->
			<div class="msn">
				<a href="${pageContext.request.contextPath}">论坛</a>|<a href="">帮助中心</a>|<a
					href="">在线客服</a>|<span><i class="tel"></i>021-60100300
					(8:30-17:30)</span>
			</div>
		</div>
	</div>
</div>

<!-- 登录-->
	<div id="login_box">
	<div class="login_box">
        <!--<h2>会员登录</h2>-->
        <div class="login_tab">
            <div class="login">
                <h2><a class="qq"></a>会员登录</h2>
                <div class="user clearfix">
                    <label>手机号：</label>
                    <input type="text" placeholder="输入11位数字的手机号码" maxlength="11" class="phone_icon sign_icon" tabindex="1" id="txt_loginmobile">
                </div>
                <div class="password clearfix">
                    <label>密码：</label>
                    <input type="password" placeholder="请输入密码" class="password_icon sign_icon" tabindex="2" id="txt_loginpwd">
                    <div class="txt"><a target="_top" href="#">找回密码</a></div>
                </div>
                <div class="password clearfix">
                    <label>验证码：</label>
                    <input type="text" placeholder="请输入验证码" class="phone_icon sign_icon" tabindex="2" id="txt_validatecode">
                    <div class="txt"><img id='img' src="${pageContext.request.contextPath}/drawImage.html" onclick='HeaderTopbar.refresh()' title="点击刷新验证码" alt="验证码获取错误,请刷新页面" style="cursor:pointer;width:70px;height:23px;" /></div>
                </div>
                <div class="link">
                    <label><input type="checkbox" checked="checked" id="chk_rememberme"><span>下次自动登录</span></label>
                </div>
                <div class="btn clearfix">
                        <div style="display:none;" class="fail"><i class="icon_tip"></i></div>
                    <input type="button" value="登   录" id="btn_do_login">
                    <div class="txt"><a target="_top" href="javascript:" class='register'>免费注册</a></div>
                </div>
            </div>
        </div>
    </div>
    </div>
    
    <!-- 注册-->
	<div id="reg_box">
	<div class="login_box">
        <!--<h2>会员登录</h2>-->
        <div class="login_tab">
            <div class="login">
                <h2><a class="qq"></a>会员登录</h2>
                <div class="user clearfix">
                    <label>手 机 号：</label>
                    <input type="text" placeholder="输入11位数字的手机号码" maxlength="11" class="phone_icon sign_icon" tabindex="1" id="txt_regmobile">
                </div>
                    <div class="password clearfix">
                        <label>设置密码：</label>
                        <input type="password" placeholder="请输入密码" class="password_icon sign_icon" tabindex="2" id="txt_regpwd">
                    </div>
                    <div class="password clearfix">
                        <label>确认密码：</label>
                        <input type="password" placeholder="请再次输入密码" class="password_icon sign_icon" tabindex="2" id="txt_reg2pwd">
                    </div>
                    <div class="password clearfix">
                        <label>验证码：</label>
                        <input type="text" placeholder="请输入验证码" class="password_icon sign_icon" tabindex="2" id="txt_regcode">
                         <div class="txt"><img id='img' src="${pageContext.request.contextPath}/drawImage.html" onclick='HeaderTopbar.refresh()' title="点击刷新验证码" alt="验证码获取错误,请刷新页面" style="cursor:pointer;width:70px;height:23px;" /></div>
                    </div>
                <div class="btn clearfix">
                        <div style="display:none;" class="fail"><i class="icon_tip"></i></div>
                    <input type="button" value="注   册" id="btn_do_reg">
                    <div class="txt"><a target="_top" href="javascript:" class='login_small'>登录</a></div>
                </div>
            </div>
        </div>
    </div>
    </div>
    
<script src="${pageContext.request.contextPath}/static/js/zquery.js"></script>
<script src="${pageContext.request.contextPath}/static/js/public.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.lazyload.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/header-topbar.js"></script>