<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html ng-app="app" lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>表单设计系统</title>
<base href="<%=basePath%>" />

<link rel="stylesheet" type="text/css" href="css/fonts/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="static/app/bower_components/jquery-ui/themes/base/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/app/bower_components/ng-table/ng-table.css" />

</head>
<body>
	<div class="main" ng-controller="MainCtrl">
		<div class="top">
			<div>
				<span>员工级别</span>
				<span>退出登录</span>
			</div>
			<div>
				<span>员工姓名</span>
				<span>公司主页</span>
			</div>
			<div class="photo">
				<img width="50px" height="50px" alt="员工图片" src="">
			</div>
		</div>
		<div class="right">
			<div class="body">
				<div ng-view></div>
			</div>
		</div>
		<div class="menus">
			<div ng-class="{true: 'select'}[$root.menu=='#']" class="index" ng-click="to('');"><i class="fa fa-user"></i>&nbsp;我的主页</div>
			
			<div class="lv1"><i class="fa fa-list"></i>&nbsp;业务管理</div>
			
			<div ng-class="{true: 'select'}[$root.menu=='custom']" class="lv2" ng-click="to('custommgr/list');"><i class="fa fa-caret-right"></i>&nbsp;客户管理</div>
			<div ng-class="{true: 'select'}[$root.menu=='job']" class="lv2" ng-click="to('jobmgr/list');"><i class="fa fa-caret-right"></i>&nbsp;职位管理</div>
			<div ng-class="{true: 'select'}[$root.menu=='resume']" class="lv2" ng-click="to('resumemgr/list');"><i class="fa fa-caret-right"></i>&nbsp;简历管理</div>
			<div ng-class="{true: 'select'}[$root.menu=='contract']" class="lv2" ng-click="to('contractmgr/list');"><i class="fa fa-caret-right"></i>&nbsp;合同管理</div>
			<div ng-class="{true: 'select'}[$root.menu=='invoice']" class="lv2" ng-click="to('invoicemgr/list');"><i class="fa fa-caret-right"></i>&nbsp;发票管理</div>
			
			<div class="lv1"><i class="fa fa-comments"></i>&nbsp;团队管理</div>
			
			<div ng-class="{true: 'select'}[$root.menu=='performance']" class="lv2" ng-click="to('performancemgr/list');"><i class="fa fa-caret-right"></i>&nbsp;绩效管理	</div>
			<div ng-class="{true: 'select'}[$root.menu=='team']" class="lv2" ng-click="to('teammgr/list');"><i class="fa fa-caret-right"></i>&nbsp;团队管理</div>
			
			<div class="lv1"><i class="fa fa-edit"></i>&nbsp;系统管理</div>
			
			<div ng-class="{true: 'select'}[$root.menu=='user']" class="lv2" ng-click="to('usermgr/list');"><i class="fa fa-caret-right"></i>&nbsp;用户管理</div>
			<div ng-class="{true: 'select'}[$root.menu=='resetpwd']" class="lv2" ng-click="to('usermgr/resetpwd');"><i class="fa fa-caret-right"></i>&nbsp;修改密码</div>
			
			<div class="lv1"><i class="fa fa-calendar"></i>&nbsp;公告管理</div>
			<div ng-class="{true: 'select'}[$root.menu=='pub']" class="lv2" ng-click="to('pubmgr/list');"><i class="fa fa-caret-right"></i>&nbsp;公告管理</div>
			
		</div>
		
		
		<script type="text/ng-template" id="custom/pager">
<div class="ng-cloak"> 
	<div ng-if="params.settings().counts.length" class="btn-group pull-right"> 
		<button ng-repeat="count in params.settings().counts" 
			type="button" 
			ng-class="{'active':params.count()==count}" 
			ng-click="params.count(count)" class="btn btn-default btn-xs"> 
			<span ng-bind="count"></span>
		</button> 
	</div> 
	<ul class="pagination"> 
		<li ng-class="{'disabled': !page.active}" ng-repeat="page in pages" ng-switch="page.type">
		<a ng-switch-when="prev" ng-click="params.page(page.number)" href="">&laquo;</a> 
		<a ng-switch-when="first" ng-click="params.page(page.number)" href=""><span ng-bind="page.number"></span></a>
		<a ng-switch-when="page" ng-click="params.page(page.number)" href=""><span ng-bind="page.number"></span></a> 
		<a ng-switch-when="more" ng-click="params.page(page.number)" href="">…</a> 
		<a ng-switch-when="last" ng-click="params.page(page.number)" href=""><span ng-bind="page.number"></span></a>
		<a ng-switch-when="next" ng-click="params.page(page.number)" href="">&raquo;</a> 
		</li>
		&nbsp;
		总数：<span ng-bind="params.total()"></span>
	</ul> 
</div>
</script>
				
	</div>
	
	<!-- jq -->
	<script type="text/javascript" src="static/app/bower_components/jquery/dist/jquery.min.js"></script>
	<script type="text/javascript" src="static/app/components/3rd/jquery_ext/jquery.ishappy.js"></script>
	<script type="text/javascript" src="static/app/bower_components/jquery-ui/jquery-ui.min.js"></script>
	<script type="text/javascript" src="static/app/bower_components/jquery-ui/ui/i18n/datepicker-zh-CN.js"></script>
	
	<!-- ng -->
	<script type="text/javascript" src="static/app/bower_components/angular/angular.min.js"></script>
	<script type="text/javascript" src="static/app/bower_components/angular-route/angular-route.min.js"></script>
	<script type="text/javascript" src="static/app/bower_components/ng-table/ng-table.js"></script>
		
	<script type="text/javascript" src="static/build/main.js"></script>
	
</body>
</html>