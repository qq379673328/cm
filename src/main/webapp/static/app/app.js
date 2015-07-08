"use strict";
var app = angular.module('app',['ngRoute', 'ngTable', 'datepicker'],
		function($httpProvider) {
			//处理默认angularjs的ajax请求
			$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
			var param = function(obj) {
				var query = '', name, value, fullSubName, subName, subValue, innerObj, i;
				for (name in obj) {
					value = obj[name];
					if (value instanceof Array) {
						for (i = 0; i < value.length; ++i) {
							subValue = value[i];
							fullSubName = name + '[' + i + ']';
							innerObj = {};
							innerObj[fullSubName] = subValue;
							query += param(innerObj) + '&';
						}
					} else if (value instanceof Object) {
						for (subName in value) {
							subValue = value[subName];
							fullSubName = name + '[' + subName + ']';
							innerObj = {};
							innerObj[fullSubName] = subValue;
							query += param(innerObj) + '&';
						}
					} else if (value !== undefined && value !== null)
						query += encodeURIComponent(name) + '='
								+ encodeURIComponent(value) + '&';
				}
				return query.length ? query.substr(0, query.length - 1) : query;
			};
			// 覆盖默认转换请求
			$httpProvider.defaults.transformRequest = [ function(data) {
				return angular.isObject(data)
						&& String(data) !== '[object File]' ? param(data)
						: data;
			} ];
			
});

/**
 * 处理公共ajax请求异常处理
 */
var reloadPageFlag = false;
app.factory('ajaxHandler', ['$q', '$rootScope', function($q, $rootScope) {
    var handler = {
        responseError: function(response) {
        	var status = response.status;
        	var message = "";
        	switch (parseInt(status))
    		{
    		case 400:
    			message = "请求错误";
    			break;
    		case 401:
    			var time = 3;
    			message = "登陆超时," + time + "秒后自动跳转登陆页面...";
    			if(!reloadPageFlag){
    				reloadPageFlag = true;
    				setTimeout(function(){
    					window.location.reload();
    				}, time*1000);
    			}
    			break;
    		case 403:
    			message = "无访问权限";
    			break;
    		case 404:
    			message = "未知请求";
    			break;
    		case 500:
    			message = "操作失败,服务器异常";
    			break;
    		case 302:
    			var time = 3;
    			message = "登陆超时," + time + "秒后自动跳转登陆页面...";
    			if(!reloadPageFlag){
    				reloadPageFlag = true;
    				setTimeout(function(){
    					window.location.reload();
    				}, time*1000);
    			}
    			break;
    		case 0:
    			message = "无法连接服务器";
    			break;
    		default:
    			message = "未知错误";
    		}
        	
        	$rootScope.ajaxerror = {
    			success: 0,
    			message: message,
    			time: new Date()
        	};
        	
            return $q.reject(response);
        }
    };
    return handler;
}]);
app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('ajaxHandler');
}]);



//主框架
app.controller('MainCtrl', function($scope, $location, $http) {
	//跳转链接
	$scope.to = function(url){
		$location.path(url);
	};
	
	//加载用户基本信息
	$http.post("user/getLoginUserInfo", {}).success(function(data){
		$scope.user = data;
	});
	
	
});
