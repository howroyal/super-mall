<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/resources/styles/clicki.web.css?V=20120501" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/resources/styles/clicki.loginandreg.css?V=20120501" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/resources/styles/clicki.webkitanimation.css?V=20120501" media="screen" />
<title>用户注册</title>
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
<div class="theCenterBox" style="">
  <div class="theLoginBox" style="width:657px">
    <div class="loginTxt">注册账号</div>
    <form id="leftForm" method="post">
      <div class="theLoginArea" id="loginBox">
        <p style="position: relative;">
          <label for="LoginForm_email">手机号：</label>
          <input placeholder="请输入您的手机号" name="LoginForm[email]" id="userName" type="text" maxlength="255" />
          <span>请输入您的邮箱</span> </p>
        <p style="position: relative;">
          <label for="LoginForm_password">密码：</label>
          <input placeholder="请输入您的密码" name="LoginForm[password]" id="password" type="password" maxlength="16" />
          <span>请输入您的密码</span> </p>
        <p style="position: relative;">
          <label for="LoginForm_checksum">确认密码：</label>
          <input placeholder="请再次输入密码" name="LoginForm[checksum]" id="password2" type="password" value="16" />
          <span>请再次输入密码</span> </p>
        <div class="loginSubmitBnt">
          <div class="reg_submit">
            <input id='userSubmit' name="userSubmit" class="theSubmitButton" value="" type="button" />
          </div>
        </div>
      </div>
      <div class="theRegArea fixRegHeight" id="reg_reg">
        <h2>没有邀请码？</h2>
        <br/>
        <a class="apply_reg" href="#"></a> <br/>
        <h2>已经有账号？</h2>
        <br/>
        <a class="reg_login" href="index.html"></a> </div>
    </form>
  </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/js/zquery.js"></script>
<script src="${pageContext.request.contextPath}/static/js/new/reg.js"></script>

</html>
