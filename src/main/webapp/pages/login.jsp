<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>用户登陆</title>
<style type="text/css">
body, h1 {
	margin: 0;
	padding: 0;
	font-family: "微软雅黑";
	font-size: 14px;
	background: #3d73b1;
}

h1, h2, h3, h4, ul, li, dl, dt, dd, p, img {
	margin: 0;
	padding: 0;
	list-style: none;
	border: 0;
}

html {
	min-width: 1200px;
}

a:link, a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

.login {
	margin: 0 auto;
}

.title {
	width: 749px;
	height: 182px;
	margin: 0 auto;
}

.denglright {
	float: left;
}

.denglleft {
	padding-top: 30px;
	padding-left: 60px;
	float: left;
}

.login_register-con {
	width: 100%;
}

.login_register-inner {
	width: 1000px;
	margin: 0 auto;
	position: relative;
}

.login-page {
	min-height: 540px;
	_height: 540px;
}

.login-inner {
	margin: 0 auto;
	width: 680px;
	height: 289px;
	padding-bottom: 10px;
	background: #fff;
	border-radius: 3px;
	font-family: 'Microsoft Yahei';
}

.login-inner h2 {
	font-size: 20px;
	font-weight: bold;
	color: #b7883e;
	overflow: hidden;
	margin-bottom: 13px
}

.login-form-error {
	height: 18px;
	line-height: 18px;
	font-size: 12px;
	color: #e43737;
	width: 300px;
	margin: 0 auto 5px
}

.login-form-error .error {
	padding-left: 22px
}

.login-form-error .icon-error {
	margin-right: 3px;
}

.login-form {
	width: 300px;
	margin: 0 auto;
}

.login-form li {
	width: 300px;
	height: 42px;
	margin-bottom: 25px;
	position: relative
}

.login-form-username, .login-form-password, .login-form-valicode {
	position: absolute;
	display: block;
	width: 21px;
	height: 21px;
	top: 10px;
	left: 8px
}

.login-form-username {
	background: url(images/icon-ren.png) no-repeat 3px 0;
}

.login-form-password {
	background: url(images/icon-suo.png) no-repeat 4px 1px;
}

.login-form .rcf-valicode .input-text-style-3 {
	float: left;
	width: 150px;
	padding-left: 10px;
}

.login-form .login-form-checkbox {
	height: 16px;
	line-height: 16px;
	margin-bottom: 19px;
}

.login-form-checkbox .input-checkbox-style-3 {
	margin-top: 1px;
	margin-right: 5px;
}

.login-other {
	width: 300px;
	margin: 0 auto;
	padding-top: 20px;
}

.login-other dt {
	color: #333;
	width: 100%;
	margin-bottom: 9px;
}

.login-other dd {
	float: left;
	line-height: 22px;
	margin-right: 20px;
}

.login-other dd a {
	color: #666;
}

.login-other dd a:hover {
	color: #333;
	text-decoration: none;
}

.login-other .icon-login_register {
	margin-right: 5px;
}
/*公共样式*/
.input-text-style-3 {
	width: 260px;
	padding: 11px 0;
	height: 18px;
	line-height: 18px;
	border: 1px solid #bcc6d0;
	background: #eef5fa;
	border-radius: 3px;
	font-size: 14px;
	color: #333;
	padding-left: 43px;
}

.input-text-style-4 {
	width: 80px;
	padding: 11px 0;
	height: 18px;
	line-height: 18px;
	border: 1px solid #dcdcdc;
	border-radius: 3px;
	font-size: 14px;
	color: #333;
	padding-left: 43px;
	float: left;
}

.input-text-style-3:hover {
	border: 1px solid #2081f2;
}

.input-text-style-4:hover {
	border: 1px solid #2081f2;
}

.input-submit-style-3 {
	cursor: pointer;
	background: none;
	background-color: #3e97c9;
	border: none;
	width: 300px;
	height: 40px;
	line-height: 40px;
	text-align: center;
	color: #fff;
	font-size: 16px;
	font-weight: bold;
	border-radius: 3px;
}
/*footer*/
.footer {
	width: 100%;
	height: 39px;
	background: #2762a6;
	position: absolute;
	bottom: 0;
}

.footer p {
	line-height: 39px;
	font-size: 14px;
	text-align: center;
	color: #FFFFFF;
}
</style>
</head>
<body>
	<!--login-->
	<div class="login">
		<div class="title"></div>
		<div class="login-inner">
			<div class="denglright">
				<img src="images/loginleft.jpg" width="275" height="299" />
			</div>
			<div class="denglleft">
				<h2>用户登录</h2>
				<form id="loginForm" action="login" method="post">
					<div id="errorContainer" class="login-form-error clearfix">
						<c:out value="${message_login}" />
					</div>
					<ul class="login-form clearfix">
						<li><span class="login-form-username"></span> <input
							id="username" name="username" value="admin" type="text"
							autocomplete="off" class="input-text-style-3" placeholder="用户名"
							value=""></li>
						<li><span class="login-form-password"></span> <input
							id="password" name="password" value="11111111" type="password"
							autocomplete="off" class="input-text-style-3" placeholder="密码">
						</li>
						<li><input id="loginSubBtn" type="submit"
							class="input-submit-style-3" value="立即登录"></li>
					</ul>
				</form>
			</div>
		</div>
		<div class="footer"></div>
	</div>
</body>
</html>