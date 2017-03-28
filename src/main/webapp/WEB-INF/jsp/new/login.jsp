<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/resources/styles/clicki.web.css?V=20120501" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/resources/styles/clicki.loginandreg.css?V=20120501" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/resources/styles/clicki.webkitanimation.css?V=20120501" media="screen" />

<title>用户登录</title>
<!--[if lte IE 9 ]><link rel="stylesheet" href="resources/styles/clicki.iehotfix.css?V=20120501" /><![endif]-->
<!--[if lte IE 9]>
<style>
body {background:#2f7fb2 url(resources/styles/images/topbg_01.png?V=20120501) top center no-repeat;}
html{height:100%;overflow:hidden;background:#2f7fb2 url(resources/styles/images/topbg_01.png?V=20120501) top center no-repeat;}
</style>
<![endif]-->
</head>
<body>
<input id='contextPath' type='hidden' value='${pageContext.request.contextPath}' />
<input id='returl' type='hidden' value='${returl}' />
<div class="theCenterBox" style="">
  <div class="theLoginBox">
    <div class="loginTxt">会员登录</div>
    <div class="theLoginArea" id="loginBox">
      <form id="leftForm" action="dologinForm.html" method="post">
        <p style="position: relative;">
          <label for="LoginForm_userName">账号：</label>
          <input placeholder="请输入您的账号" name="LoginForm[userName]" id="userName" type="text" maxlength="255" />
          <span>请输入您的账号</span> </p>
        <p style="position: relative;">
          <label for="LoginForm_password">密码：</label>
          <input placeholder="请输入您的密码" name="LoginForm[password]" id="password" type="password" maxlength="16" />
          <span>请输入您的密码</span> </p>
        <p style="position: relative;">
          <label for="LoginForm_validatecode">验证码：</label>
          <input placeholder="请输入验证码" name="LoginForm[validatecode]" id="validatecode" type="text" maxlength="6" />
          <span>请输入验证码(点击图案刷新)</span>
          <img id='img' src="${pageContext.request.contextPath}/drawImage.html" onclick='Login.refresh()' title="点击刷新验证码" alt="验证码获取错误,请刷新页面" style="cursor:pointer" />
          </p>
        <div class="loginSubmitBnt fixPadding">
          <div>
            <input id="ytautoLogin" type="hidden" value="0" name="LoginForm[rememberMe]" />
            <input id="autoLogin" class="theRememberMe" name="LoginForm[rememberMe]" value="1" checked="checked" type="checkbox" />
            <label class="theRememberMeLabel" for="autoLogin">记住用户名和密码</label>
            <em class="forgotPasswordEm"><a href="#" title="忘记密码">忘记密码?</a>&nbsp;&nbsp;&nbsp;<a href="reg.html" title="马上注册">马上注册</a></em>
            <div class="login_submit">
              <input id='userSubmit' name="userSubmit" class="theSubmitButton" type="button" />
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/js/zquery.js"></script>
<script src="${pageContext.request.contextPath}/static/js/new/login.js"></script>
</html>

