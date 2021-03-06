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
app.factory('redirectInterceptor', ['$location', '$q', function($location, $q) {
    return function(promise) {
        promise.then(
            function(response) {
                if (typeof response.data === 'string') {
                    if (response.data.indexOf instanceof Function &&
                        response.data.indexOf('<html id="ng-app" ng-app="loginApp">') != -1) {
                        window.location = "/";
                    }
                }
                return response;
            },
            function(response) {
                return $q.reject(response);
            }
        );
        return promise;
    };
}]);
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
	$httpProvider.interceptors.push('redirectInterceptor');
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
		window.LOGINUSER = data;
	});
	
});

var fdRouterViewsBasepath = "static/app/modules/";

//路由
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/', {//首页
		templateUrl : fdRouterViewsBasepath + 'index/views/index.html',
		controller : 'IndexCtrl'
	})
	
	.when('/custommgr/list', {//客户管理-列表页
		templateUrl : fdRouterViewsBasepath + 'custom/views/custommgrlist.html',
		controller : 'CustomMgrListCtrl'
	})
	.when('/custommgr/list/:who', {//客户管理-我的
		templateUrl : fdRouterViewsBasepath + 'custom/views/custommgrlist.html',
		controller : 'CustomMgrListCtrl'
	})
	.when('/custommgr/incustom', {//客户管理-录入客户
		templateUrl : fdRouterViewsBasepath + 'custom/views/custommgrincustom.html',
		controller : 'CustomMgrInCustomCtrl'
	})
	.when('/custommgr/editcustom/:id', {//客户管理-编辑客户
		templateUrl : fdRouterViewsBasepath + 'custom/views/custommgrincustom.html',
		controller : 'CustomMgrInCustomCtrl'
	})
	.when('/custommgr/viewcustom/:id', {//客户管理-客户信息
		templateUrl : fdRouterViewsBasepath + 'custom/views/custommgrviewcustom.html',
		controller : 'CustomMgrViewCustomCtrl'
	})
	
	.when('/jobmgr/list', {//职位管理-列表页
		templateUrl : fdRouterViewsBasepath + 'job/views/jobmgrlist.html',
		controller : 'JobMgrListCtrl'
	})
	.when('/jobmgr/list/:who', {//职位管理-列表页-我的
		templateUrl : fdRouterViewsBasepath + 'job/views/jobmgrlist.html',
		controller : 'JobMgrListCtrl'
	})
	.when('/jobmgr/injob', {//职位管理-录入职位
		templateUrl : fdRouterViewsBasepath + 'job/views/jobmgrinjob.html',
		controller : 'JobMgrInJobCtrl'
	})
	.when('/jobmgr/injob/:customId', {//职位管理-为客户录入职位
		templateUrl : fdRouterViewsBasepath + 'job/views/jobmgrinjob.html',
		controller : 'JobMgrInJobCtrl'
	})
	.when('/jobmgr/editjob/:id', {//职位管理-编辑职位
		templateUrl : fdRouterViewsBasepath + 'job/views/jobmgrinjob.html',
		controller : 'JobMgrInJobCtrl'
	})
	.when('/jobmgr/viewjob/:id', {//职位管理-职位信息
		templateUrl : fdRouterViewsBasepath + 'job/views/jobmgrviewjob.html',
		controller : 'JobMgrViewJobCtrl'
	})
	
	.when('/resumemgr/list', {//简历管理-列表页
		templateUrl : fdRouterViewsBasepath + 'resume/views/resumemgrlist.html',
		controller : 'ResumeMgrListCtrl'
	})
	.when('/resumemgr/inresume', {//简历管理-录入简历
		templateUrl : fdRouterViewsBasepath + 'resume/views/resumemgrinresume.html',
		controller : 'ResumeMgrInResumeCtrl'
	})
	.when('/resumemgr/editresume/:id', {//简历管理-编辑简历
		templateUrl : fdRouterViewsBasepath + 'resume/views/resumemgrinresume.html',
		controller : 'ResumeMgrInResumeCtrl'
	})
	.when('/resumemgr/viewresume/:id', {//简历管理-简历信息
		templateUrl : fdRouterViewsBasepath + 'resume/views/resumemgrviewresume.html',
		controller : 'ResumeMgrViewResumeCtrl'
	})
	.when('/resumemgr/pub/:id', {//简历管理-推荐职位
		templateUrl : fdRouterViewsBasepath + 'resume/views/resumepublist.html',
		controller : 'ResumePubListCtrl'
	})
	
	.when('/contractmgr/list', {//合同管理-列表页
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrlist.html',
		controller : 'ContractMgrListCtrl'
	})
	.when('/contractmgr/incontract', {//合同管理-录入合同
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrincontract.html',
		controller : 'ContractMgrInContractCtrl'
	})
	.when('/contractmgr/incontract/:customId', {//合同管理-为指定客户录入合同
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrincontract.html',
		controller : 'ContractMgrInContractCtrl'
	})
	.when('/contractmgr/editcontract/:id', {//合同管理-编辑合同
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrincontract.html',
		controller : 'ContractMgrInContractCtrl'
	})
	.when('/contractmgr/viewcontract/:id', {//合同管理-合同信息
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrviewcontract.html',
		controller : 'ContractMgrViewContractCtrl'
	})
	
	.when('/invoicemgr/list', {//发票管理-列表页
		templateUrl : fdRouterViewsBasepath + 'invoice/views/invoicemgrlist.html',
		controller : 'InvoiceMgrListCtrl'
	})
	.when('/invoicemgr/ininvoice', {//发票管理-录入发票
		templateUrl : fdRouterViewsBasepath + 'invoice/views/invoicemgrininvoice.html',
		controller : 'InvoiceMgrInInvoiceCtrl'
	})
	.when('/invoicemgr/editcustom/:id', {//发票管理-编辑发票
		templateUrl : fdRouterViewsBasepath + 'invoice/views/invoicemgrininvoice.html',
		controller : 'InvoiceMgrInInvoiceCtrl'
	})
	.when('/invoicemgr/viewinvoice/:id', {//发票管理-发票信息
		templateUrl : fdRouterViewsBasepath + 'custom/views/invoicemgrviewinvoice.html',
		controller : 'InvoiceMgrViewInvoiceCtrl'
	})
	
	.when('/performancemgr/list', {//绩效管理-列表页
		templateUrl : fdRouterViewsBasepath + 'performance/views/performancemgrlist.html',
		controller : 'PerformanceMgrListCtrl'
	})
	
	.when('/teammgr/list', {//团队管理-列表页
		templateUrl : fdRouterViewsBasepath + 'team/views/teammgrlist.html',
		controller : 'TeamMgrListCtrl'
	})
	.when('/teammgr/myinfo', {//团队管理-我的信息
		templateUrl : fdRouterViewsBasepath + 'team/views/teammgrmyinfo.html',
		controller : 'TeamMgrMyInfoCtrl'
	})
	
	.when('/usermgr/list', {//用户管理-列表页
		templateUrl : fdRouterViewsBasepath + 'user/views/usermgrlist.html',
		controller : 'UserMgrListCtrl'
	})
	.when('/usermgr/inuser', {//用户管理-录入用户
		templateUrl : fdRouterViewsBasepath + 'user/views/usermgrinuser.html',
		controller : 'UserMgrInUserCtrl'
	})
	.when('/usermgr/editcustom/:id', {//用户管理-编辑用户
		templateUrl : fdRouterViewsBasepath + 'user/views/usermgrinuser.html',
		controller : 'UserMgrInUserCtrl'
	})
	.when('/usermgr/viewuser/:id', {//用户管理-用户信息
		templateUrl : fdRouterViewsBasepath + 'user/views/usermgrviewuser.html',
		controller : 'UserMgrViewUserCtrl'
	})
	
	.when('/usermgr/resetpwd', {//修改密码管理-列表页
		templateUrl : fdRouterViewsBasepath + 'user/views/userresetpwd.html',
		controller : 'UserResetPwdCtrl'
	})
	
	.when('/usermgr/uploadicon', {//上传头像-页面
		templateUrl : fdRouterViewsBasepath + 'user/views/uploadicon.html',
		controller : 'UserUploadIconCtrl'
	})
	
	.when('/pubmgr/list', {//公告管理-列表页
		templateUrl : fdRouterViewsBasepath + 'pub/views/pubmgrlist.html',
		controller : 'PubMgrListCtrl'
	})
	.when('/pubmgr/inpub', {//公告管理-录入公告
		templateUrl : fdRouterViewsBasepath + 'pub/views/pubmgrinpub.html',
		controller : 'PubMgrInPubCtrl'
	})
	.when('/pubmgr/editpub', {//公告管理-编辑公告
		templateUrl : fdRouterViewsBasepath + 'pub/views/pubmgreditpub.html',
		controller : 'PubMgrEditPubCtrl'
	})
	
	.when('/codemgr', {//编码管理
		templateUrl : fdRouterViewsBasepath + 'code/views/codemgr.html',
		controller : 'CodeMgrCtrl'
	})
	
	.otherwise({
		redirectTo : '/'
	});
} ]);
//html过滤
app.filter('trustHtml', function ($sce) {
    return function (input) {
        return $sce.trustAsHtml(input);
    };
});

//基础数据加载指令
app.directive('coreBasedata', ["$http", function($http){
	var baseData = {
	};
	//入职时间
	baseData.inyear = [];
	var yearCount = 8;
	var currentYear= new Date().getFullYear();
	for(var i = 0; i < yearCount; i++){
		baseData.inyear.push(["" + (currentYear - i) + "", "" + (currentYear - i) + "年"]);
	}
	
	//获取所有码表
	$.ajax({
		url: "getAllCodes",
		async: false,
		success: function(data){
			data = eval(data);
			for(var i in data){
				var item = data[i];
				var type = item["codetype"],
					value = item["codevalue"];
				if(!baseData[type]){
					baseData[type] = [];
				}
				baseData[type].push([value, value]);
			}
		}
	});
	
    return {
        restrict: 'A',
        scope: {
        	value: "@value",//值
        	type: "@type",
        	key: "@key",
        	name: "@dataname",
        	bindModel: "=",
        	allSelectName: "@allSelectName"
        },
        templateUrl:'static/app/components/directives/core/views/basedata.html',
        replace: true,
        require: '?ngModel',
        link: function($scope, $el, attrs, ngModel){
        	$scope.items = baseData[$scope.key];
        	
        	$scope.click = function(val ,target){
        		ngModel.$setViewValue(val);
        		if(target){
        			var $target = $(target);
        			$target.siblings().removeClass("select");
        			$target.addClass("select");
        		}
        	};
        	
        	$scope.select = function(t){
        		$scope.bindModel = t.bindModel;
        	};
        	
        }
    };
}]);
//入职人选-发票
app.directive('coreContractresumeselect', ["$http", function($http){
    return {
        restrict: 'A',
        replace: true,
        scope: {
        	finalval: "=finalval"
        },
        require: 'ngModel',
        templateUrl: 'static/app/components/directives/core/views/contractresumeselect.html',
        link: function($scope, $element, attrs, ngModel) {
        	var isInit = false;
        	//选择
        	$scope.ok = function(item){
        		var val = item.resume_name + " - " + item.job_name;
        		$scope.finalval = val;
        		ngModel.$setViewValue({
        			resumeId: item.rid,
        			resumeDesc: val
        		});
        	};
        	
        	//监控客户初始化
        	$scope.$watch('finalval', function(newValue, oldValue){
        		if(!newValue){
        			return;
        		}
        		if(isInit){
        			return;
        		}else{
        			isInit = true;
        			$scope.finalval = newValue;
        		}
        	}, true);
        	
        	//监控查询
        	$scope.$watch('customcontractsearch', function(newValue, oldValue){
        		if(newValue == oldValue) return;
        		$http.post("invoice/getContractResume",
        				{
        			no: $scope.customcontractsearch.no,
        			jobName: $scope.customcontractsearch.jobName,
        			resumeName: $scope.customcontractsearch.resumeName,
        			page: 1,
        			rows: 100})
        				.success(function(data){
						$scope.items = data.rows;
        		});
        	}, true);
        	
        	//默认查询一次
        	$http.post("invoice/getContractResume",
    				{
    			page: 1,
    			rows: 100})
    				.success(function(data){
					$scope.items = data.rows;
    		});
        	
        }
    };
}]);
//客户合同选择指令
app.directive('coreCustomcontractselect', ["$http", function($http){
    return {
        restrict: 'A',
        replace: true,
        scope: {
        	finalval: "=finalval"
        },
        require: 'ngModel',
        templateUrl: 'static/app/components/directives/core/views/customcontractselect.html',
        link: function($scope, $element, attrs, ngModel) {
        	var isInit = false;
        	//选择
        	$scope.ok = function(item){
        		var val = item.custom_name + "(合同编号：" + item.no + ")";
        		$scope.finalval = val;
        		ngModel.$setViewValue({
        			customId: item.custom_id,
        			contractNo: item.no,
        			customContract: val
        		});
        	};
        	
        	//监控客户初始化
        	$scope.$watch('finalval', function(newValue, oldValue){
        		if(!newValue){
        			return;
        		}
        		if(isInit){
        			return;
        		}else{
        			isInit = true;
        			$scope.finalval = newValue;
        		}
        	}, true);
        	
        	//监控查询
        	$scope.$watch('customcontractsearch', function(newValue, oldValue){
        		if(newValue == oldValue) return;
        		$http.post("invoice/getCustomContract",
        				{
        			companyName: $scope.customcontractsearch.companyName,
        			no: $scope.customcontractsearch.no,
        			page: 1,
        			rows: 100})
        				.success(function(data){
						$scope.items = data.rows;
        		});
        	}, true);
        	
        	//默认查询一次
        	$http.post("invoice/getCustomContract",
    				{page: 1,rows: 100})
    				.success(function(data){
					$scope.items = data.rows;
    		});
        }
    };
}]);
//客户选择指令
app.directive('coreCustomselect', ["$http", function($http){
    return {
        restrict: 'A',
        replace: true,
        scope: {
        	customid: "=customid"
        },
        require: 'ngModel',
        templateUrl: 'static/app/components/directives/core/views/customselect.html',
        link: function($scope, $element, attrs, ngModel) {
        	var isInit = false;
        	//选择
        	$scope.ok = function(item){
        		$scope.customname = item.custom_name;
        		ngModel.$setViewValue(item);
        	};
        	//监控客户初始化
        	$scope.$watch('customid', function(newValue, oldValue){
        		if(!newValue){
        			return;
        		}
        		if(isInit){
        			return;
        		}else{
        			isInit = true;
        			$http.post("custom/getCustomById",{id: newValue}).success(function(data){
        					if(data.custom){
        						$scope.customname = data.custom.customName;
        					}
                		});
        		}
        	}, true);
        	
        	//监控查询
        	$scope.$watch('customsearch', function(newValue, oldValue){
        		if(newValue == oldValue) return;
        		$http.post("custom/list",
        				{
        				companyName: newValue,
        				page: 1, rows: 100,
        				beyond:"my",
        				customStatus: "签约运作"})
        				.success(function(data){
						$scope.customs = data.rows;
        		});
        	}, true);
        	//默认查询一次
        	$http.post("custom/list",
    				{
        			page: 1, rows: 100, beyond:"my",
        			customStatus: "签约运作"})
    				.success(function(data){
					$scope.customs = data.rows;
    		});
        }
    };
}]);
//文件上传指令
app.directive('coreFileupload', ["$http", function($http){
	return {
		restrict:'A',
		scope: {
			attas: "=attas"
		},
	  	require: '?ngModel',
	  	templateUrl:'static/app/components/directives/core/views/fileupload.html',
	  	link: function(scope, element, attrs, ngModel) {
	  		scope.$watch("attaresult", function(){
	  			var data = scope.attaresult;
	  			scope.attaformresult = data;
	  			
	  			if(data && data.success == "1"){
	  				if(!scope.attas){
	  					scope.attas = [];
	  				}
	  				scope.attas.push(data.data);
  					ngModel.$setViewValue(handleAttasId(scope.attas));
  				}
	  		});
	  		function handleAttasId(attas){
	  			var ret = "";
	  			if(attas && attas.length > 0){
	  				for(var i = 0; i < attas.length; i++){
	  					ret += attas[i]["id"];
	  					if(i != attas.length - 1){
	  						ret += ",";
	  					}
	  				}
	  			}
	  			return ret;
	  		}
	  		
	  		/**
	  		 * 删除附件
	  		 */
	  		scope.delAttach = function(atta){
	  			$http.post("fileDel", {id: atta.id}).success(function(data){
	  				scope.attaformresult = data;
	  				removeAtta(atta.id);
	  				ngModel.$setViewValue(handleAttasId(scope.attas));
	  			});
	  		};
	  		function removeAtta(id){
	  			var newAttas = [];
	  			for(var idx in scope.attas){
	  				var atta = scope.attas[idx];
	  				if(atta.id != id){
	  					newAttas.push(atta);
	  				}
	  			}
	  			scope.attas = newAttas;
	  		}
	  		
	  	}
	  };

}]);
app.directive('coreFormresult',["$timeout", function($timeout){
	var timer = null;
    return {
        restrict: 'A',
        scope: {
        	formresult: "=result"
        },
        templateUrl:'static/app/components/directives/core/views/formresult.html',
        link: function($scope){
        	$scope.$watch("formresult", function(){
        		if($scope.formresult == null) return;
        		if($scope.formresult.success == "1"){//成功的信息自动关闭
        			$timeout.cancel(timer);//先取消
        			timer = $timeout(function(){//新建
            			$scope.formresult = null;
            		}, 4000);
            		$scope.$on(
            				"$destroy",
            				function(event) {
            					$timeout.cancel(timer);
            				}
            		);
        		}
        	});
        }
    };
}]);
//文件上传指令-内部
app.directive('coreFu', ["$http", function($http){
	return {
		restrict:'A',
		require: '?ngModel',
	  	link: function(scope, element, attrs, ngModel) {
	  		var optionsObj = {
	  			dataType: 'json',
	  			url: "fileUpload"
	  		};
	  		//开始上传
	  		optionsObj.started = function(e, data){
	  			scope.$apply(function(){
  					ngModel.$setViewValue({
  						success: "0",
  						message: "上传中..."
  					});
  				});
	  		};
	  		//上传成功
  			optionsObj.done = function(e, data) {
  				scope.$apply(function(){
  					ngModel.$setViewValue(data.result);
  				});
  			};
  			//上传失败
  			optionsObj.fail = function(e, data) {
  				scope.$apply(function(){
  					ngModel.$setViewValue({
  						success: "0",
  						message: "上传失败"
  					});
  				});
  			};
	  		//以上内容可以简单地在一个循环中完成，这里是为了覆盖Fileupload控件所提供的API
	  		element.fileupload(optionsObj);
	  	}
	  };

}]);
app.directive('corePermission', ["BaseInfoService", function(BaseInfoService){
    return {
        restrict: 'A',
        link: function(scope, element, attrs, ctrl) {
        	var oldStyle = element.style.display;
        	//先隐藏节点
        	element.style.display = "none";
        	BaseInfoService.getUserInfo(function(userInfo){
        		//角色-用户类型
        		var rolePass = false;
        		var fundIdPass = false;
        		var userType = userInfo.userType;
        		if(userType != "1"){//超级管理员直接过滤
        			//角色-以","分隔
            		var rolesSp = attrs.roles.split(",");
            		if(rolesSp.length > 0){
            			for(var i in rolesSp){
            				var role = rolesSp[i];
            				if(role == userType){
            					rolePass = true;
            					break;
            				}
            			}
            		}
                	
                	//功能点id
                	var funId = attrs.funid;
                	
                	fundIdPass = true;//TODO 待实现
                	
                	//是否验证通过
                	if(rolePass && fundIdPass){
                		//恢复显示
                		element.style.display = oldStyle;
                	}
        		}else{
        			element.style.display = oldStyle;
        		}
        		
            	
            	
        	});
        	
        }
    };
}]);
//执行团队选择指令
app.directive('coreTeamselect', ["$http", function($http){
    return {
        restrict: 'A',
        replace: true,
        scope: {
        	teamids: "=teamids",
        	single: "=single",
        	name: "@dataname"
        },
        require: 'ngModel',
        templateUrl: 'static/app/components/directives/core/views/teamselect.html',
        link: function($scope, $element, attrs, ngModel) {
        	function resetTe(){
        		var arr = [];
        		var teams = $scope.select;
        		for(var i in teams){
        			arr.push(teams[i]["id"]);
        		}
        		$scope.selectteamsFlag = arr.join(",");
        		ngModel.$setViewValue(arr.join(","));
        	}
        	
        	var isSingle = $scope.single ? $scope.single : false;
        	
        	var isInit = false;
        	//添加
        	$scope.addItem = function(team, index){
        		if(!$scope.select){
        			$scope.select = [];
        		}
        		if(isSingle){//单选
        			$scope.select = [];
        		}
        		$scope.select.push(team);
        		resetTe();
        	};
        	//移除
        	$scope.removeItem = function(team, index){
        		$scope.select.splice(index, 1);
        		resetTe();
        	};
        	//监控团队初始化
        	$scope.$watch('teamids', function(newValue, oldValue){
        		if(!newValue){
        			return;
        		}
        		if(isInit){
        			return;
        		}else{
        			isInit = true;
        			$http.post("team/getSelectTeams",{ids: newValue}).success(function(data){
    						$scope.select = data;
    						var arr = [];
    						for(var i in data){
    		        			arr.push(data[i]["id"]);
    		        		}
    		        		$scope.selectteamsFlag = arr.join(",");
                		});
        		}
        	}, true);
        	
        	//监控查询
        	$scope.$watch('teamsearch', function(newValue, oldValue){
        		if(newValue == oldValue) return;
        		$http.post("team/list",{name: newValue, page: 1, rows: 100}).success(function(data){
        			$scope.teams = data.rows;
        		});
        	}, true);
        	
        	//默认查询一次
        	$http.post("team/list",{page: 1, rows: 100}).success(function(data){
    			$scope.teams = data.rows;
    		});
        	
        }
    };
}]);
angular.module('datepicker', []).directive('datepicker',[function(){
    return {
        restrict: 'A',
        require : 'ngModel',
        link: function(scope, element, attrs, ngModelCtrl){
        	$(function(){
        		var format = "yy-mm-dd";
        		if(attrs.format){
        			format = attrs.format;
        		}
        		
        		var config = {
        				dateFormat: format,
                        changeMonth: true, 
                        changeYear: true, 
                        showButtonPanel: true,
                        onSelect:function (date) {
                            scope.$apply(function () {
                                ngModelCtrl.$setViewValue(date);
                            });
                        }};
        		
        		if(format == "yy-mm"){
        			config.closeText = "确定";
        			config.onClose = function(){
        				if(format == "yy-mm"){
                    		var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                    		var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                    		$(this).datepicker('setDate', new Date(year, month, 1));
                    		scope.$apply(function () {
                    			if(month && month.length == 1){
                    				month = "0" + month;
                    			}
                                ngModelCtrl.$setViewValue(year + '-' + month);
                            });
                    	}
        			};
        		}
        		config.currentText = "今天";
                element.datepicker(config);
                
                element.focus(function () {
                	if(format == "yy-mm"){
                		$(".ui-datepicker-calendar").hide();
                	}else{
                		$(".ui-datepicker-calendar").show();
                	}
                });
                
                //添加清空按钮
                var $clear = $('<i class="fa fa-refresh hover"></i>')
                	.click(function(){
                		element.val("");
                		scope.$apply(function () {
                            ngModelCtrl.$setViewValue("");
                        });
                	});
                element.after($clear);
            });
        }
    };
}]);
app.factory('BaseInfoService', ["$http", "ngTableParams", 
    function($http, ngTableParams) {
	var userInfo = null;
	
	return {
		//获取登录用户信息
		getUserInfo: function(cb){
			if(userInfo){
				if(data) cb(data);
			}else{
				$http.post("getLoginUser").success(function(data){
					if(data) cb(data);
				});
			}
		},
		//进行分页查询
		pageSearch: function(sc){
			var $scope = sc["$scope"],
				p = sc["params"],
				url = sc["url"],
				page = sc["page"];
			//每页默认数
			if(page == undefined){
				page = 10;
			}
			var items = sc["items"];
			if(!p){
				$scope[p] = {};
			}
			
			var ret = new ngTableParams({
				page: 0,
				count: page
			}, {
				total: 0,
				getData: function($defer, params) {
					if(!$scope[p].isreload){
						$scope[p].page = params.page();
						$scope[p].rows = params.count();
						$scope[p].total = params.total();
					}else{
						$scope[p].isreload = false;
					}
					$http.post(url, $scope[p]).success(function(data){
						var total = data.total;
						$scope[p].total = total;
						params.total(total);
						$defer.resolve(data.rows);
					});
				}
			});
			return ret;
		}
		
	};
}]);
//编码管理
app.controller('CodeMgrCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope, BaseInfoService) {
	//菜单
	$rootScope.menu = "codemgr";
	
	$scope.search = {queryParams: {}};
	
	//加载编码类型
	$http.post("code/getCodeTypes", {}).success(function(data){
		$scope.items = data;
		$scope.search.queryParams.codetype = "customIndustry";
	});
	
	//加载数据
	$scope.reload = function(){
		$http.post("code/getCodesByType", $scope.search.queryParams).success(function(data){
			$scope.codes = data;
		});
	}

	//监控查询条件
	$scope.$watch('search', function(newValue, oldValue){
		if(newValue == oldValue) return;
		$scope.reload();
	}, true);
	
	//删除
	$scope.del = function(item){
		$http.post("code/del", {codeId: item.id}).success(function(data){
			$scope.formresult = data;
			$scope.reload();
		});
	};
	
	//新增
	$scope.addCode = function(){
		//验证
		if($scope.addCodeValue == undefined){//值
			$scope.formresult = {
					success: false,
					message: "值不能为空"
			};
			return;
		}
		if($scope.addCodeValue.length > 100){//值
			$scope.formresult = {
					success: 0,
					message: "长度不能大于100"
			};
			return;
		}
		if($scope.addCodeRank != undefined && isNaN($scope.addCodeRank)){//顺序
			$scope.formresult = {
					success: 0,
					message: "顺序必须为数字"
			};
			return;
		}
		
		$http.post("code/edit", {
			codetype: $scope.search.queryParams.codetype,
			codevalue: $scope.addCodeValue,
			rank: $scope.addCodeRank
		}).success(function(data){
			$scope.formresult = data;
			$scope.reload();
		});
	};
	
});
//合同管理-录入合同
app.controller('ContractMgrInContractCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				$rootScope, $location) {
	$rootScope.menu = "contract";
	
	var contractId = $routeParams.id;
	var customId = $routeParams.customId;
	$scope.isReady = false;
	//请求客户信息-直接跳转过来的
	if(customId){
		$http.post("custom/getCustomById", {id: customId}).success(function(data){
			$scope.custom = data.custom;
			$scope.isReady = true;
		});
	}
	if(contractId){//编辑合同
		//请求合同信息
		$http.post("contract/getContractViewById", {id: contractId}).success(function(data){
			$scope.custom = data.custom;
			$scope.contract = data.contract;
			$scope.attachs = data.attachs;
			$scope.isReady = true;
		});
	}
	if(!contractId && !customId){
		$scope.isReady = true;
	}
	
	//保存合同信息
	$scope.save = function(isupdate){
		validFormAndSubmit(isupdate, function(data){
			if(data.success == "1"){//成功
				if(contractId){
					$location.path("contractmgr/viewcontract/" + contractId);
				}else{
					$location.path("contractmgr/list");
				}
			}
		});
	};
	
	//验证表单并提交
	function validFormAndSubmit(isUpdate, cb){
		if(validForm()){
			$scope.isrequest = true;
			$scope.contract.customId = $scope.custom.id;
			$scope.contract.isUpdate = isUpdate;
			$http.post("contract/edit", $scope.contract).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(cb) cb(data);
			});
		}

	};
	
	//验证表单
	function validForm(){
		return $("#contracteditform").isHappy({
			fields: {
				//客户名
				customName:{required: true},
				//合同编号
				no:{required: true, maxlength: 100},
				//合同状态
				state:{required: true, maxlength: 50},
				//签约日期
				inDate:{required: true, date: true},
				//签约比例
				inPercentage:{required: true, maxlength: 100},
				//支付方式
				payway:{required: true, maxlength: 100},
				//首付款
				firstPay:{required: true, number: true},
				//使用期限
				useLimit:{required: true, maxlength: 50},
				//其他要求
				otherRequire:{maxlength: 500}
			}
		});
	}
	
});
//合同管理-列表页
app.controller('ContractMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "contract";
	
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.search = {};
	$scope.queryParams = {rows: initrows};
	$scope.initParams = function(pageNotChange){
		var iPage = initpage;
		if(pageNotChange){
			iPage = $scope.queryParams.page;
		}
		var temp = {
				page: iPage,
				rows: $scope.queryParams.rows,
				isreload: true
			};
		angular.extend(temp, $scope.search);
		angular.extend($scope.queryParams, temp);
	};
	$scope.initParams();
	/*重新加载*/
	$scope.reload = function(pageNotChange){
		$scope.initParams(pageNotChange);
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			$http.post("contract/list", $scope.queryParams).success(function(data){
				var total = data.total;
				$scope.queryParams.total = total;
				params.total(total);
				params.page($scope.queryParams.page);
				$defer.resolve(data.rows);
			});
		}
	});

	//监控查询条件
	$scope.$watch('search', function(newValue, oldValue){
		if(newValue == oldValue) return;
		$scope.reload();
	}, true);
	
	//删除
	$scope.del = function(item){
		$http.post("contract/del", {id: item.id}).success(function(data){
			$scope.formresult = data;
			$scope.reload(true);
		});
	};
	
});
//合同管理-合同信息
app.controller('ContractMgrViewContractCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "contract";
	
	var contractId = $routeParams.id;
	$scope.isReady = false;
	
	//请求合同信息
	$http.post("contract/getContractViewById", {id: contractId}).success(function(data){
		$scope.custom = data.custom;
		$scope.contract = data.contract;
		$scope.jobs = data.jobs;
		$scope.contractAttas = data.attachs;
		$scope.beyond = data.beyond;
		$scope.isReady = true;
	});
	
});
app.factory('ContractService', [ '$http', function($http) {
	return {
		//
		test: function(params, cb){
			$http.post("formdesign/formdatadel", params).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);
//客户管理-录入客户
app.controller('CustomMgrInCustomCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope, $location) {
	$rootScope.menu = "custom";
	
	var customId = $routeParams.id;
	$scope.isReady = false;
	
	if(customId){//编辑
		//请求客户信息
		$http.post("custom/getCustomById", {id: customId}).success(function(data){
			$scope.custom = data.custom;
			$scope.communs = data.communs;
			$scope.attachs = data.attachs;
			$scope.isReady = true;
			
		});
	}else{//新增
		$scope.isReady = true;
		$scope.custom = {state: "潜在客户"};
		
	}
	
	//删除沟通记录
	$scope.delCommun = function(item){
		$http.post("custom/delCommun", {id: item.id}).success(function(data){
			$scope.formresult = data;
			//重新加载沟通记录
			realodCommun();
		});
	};
	function realodCommun(){
		//重新加载沟通记录
		$http.post("custom/getCommuns", {customId: customId}).success(function(data){
			$scope.communs = data;
		});
	}
	//保存客户信息
	$scope.save = function(){
		validFormAndSubmit(function(data){
			if(data.success == "1"){//成功
				$location.path("custommgr/viewcustom/" + customId);
			}
		});
	};
	//保存客户信息并且添加新合同
	$scope.saveAndAddContract = function(){
		validFormAndSubmit(function(data){
			if(data.success == "1"){//成功
				var customId = data.data;
				$location.path("contractmgr/incontract/" + customId);
			}
		});
	};
	
	//验证表单并提交
	function validFormAndSubmit(cb){
		if(validForm()){
			$scope.isrequest = true;
			$http.post("custom/edit", $scope.custom).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(cb) cb(data);
			});
		}

	};
	
	//验证表单
	function validForm(){
		return $("#customeditform").isHappy({
			fields: {
				//客户名
				customName: {required: true, maxlength: 100},
				//所属行业
				industry: {required: true,maxlength: 200},
				//执行团队
				team: {required: true},
				//联系人名
				contactName: {required: true,maxlength: 100},
				//担任职务
				contactDuty: {required: true,maxlength: 100},
				//座机号码
				contactLandline: {required: true,maxlength: 100},
				//手机号码
				contactCellphone: {maxlength: 100},
				//fax传真
				contactFax: {maxlength: 100},
				//电子邮件
				contactEmail: {required: true,email: true, maxlength: 100},
				//qq
				contactQq: {maxlength: 100},
				//微信
				contactWeixin: {maxlength: 100},
				//公司地址
				contactAddress: {maxlength: 200},
				//网址
				contactWebsite: {required: true,maxlength: 100},
				//公司介绍
				companyProfile: {maxlength: 100},
				//沟通记录
				commun: {maxlength: 500}
			}
		});
	}
	
});
//客户管理-列表页
app.controller('CustomMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope, BaseInfoService) {
	//菜单
	$rootScope.menu = "custom";
	$scope.search = {};

	$scope.changeSelect = function(target){
		$(target).siblings().removeClass("select");
		$(target).addClass("select");
	}
	
	//是否显示我的列表
	var who = $routeParams.who;
	if(who){
		$scope.search.beyond = who;
		$scope.changeSelect($("#beyond-my"));
	}
	
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.queryParams = {rows: initrows};
	$scope.initParams = function(pageNotChange){
		var iPage = initpage;
		if(pageNotChange){
			iPage = $scope.queryParams.page;
		}
		var temp = {
				page: iPage,
				rows: $scope.queryParams.rows,
				isreload: true
			};
		angular.extend(temp, $scope.search);
		angular.extend($scope.queryParams, temp);
	};
	$scope.initParams();
	/*重新加载*/
	$scope.reload = function(pageNotChange){
		$scope.initParams(pageNotChange);
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			$http.post("custom/list", $scope.queryParams).success(function(data){
				var total = data.total;
				$scope.queryParams.total = total;
				params.total(total);
				params.page($scope.queryParams.page);
				$defer.resolve(data.rows);
			});
		}
	});

	//监控查询条件
	$scope.$watch('search', function(newValue, oldValue){
		if(newValue == oldValue) return;
		$scope.reload();
	}, true);
	
	//删除
	$scope.del = function(item){
		$http.post("custom/del", {id: item.id}).success(function(data){
			$scope.formresult = data;
			$scope.reload(true);
		});
	};
	
});
//客户管理-客户信息
app.controller('CustomMgrViewCustomCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "custom";
	
	var customId = $routeParams.id;
	$scope.isReady = false;
	
	//重新加载客户信息
	$scope.reload = function(){
		$http.post("custom/getCustomViewById", {id: customId}).success(function(data){
			$scope.custom = data.custom;
			$scope.comms = data.comms ? data.comms : [];
			$scope.attas = data.attas;
			$scope.jobs = data.jobs;
			$scope.teams = data.teams;
			$scope.beyond = data.beyond;
			if(data.contracts && data.contracts.length > 0){
				$scope.contract = data.contracts[0];
			}
			$scope.contractAttas = data.contractAttas;
			$scope.isReady = true;
			$scope.contentInfo = "";
		});
	};
	
	//请求客户信息
	$scope.reload();
	
	//添加沟通记录
	$scope.addComm = function(){
		if($("#addCommForm").isHappy({
			fields: {
				addContent: {
					required: true, maxlength: 500
				}
			}
		})){
			//请求客户信息
			$http.post("custom/addCommun", 
					{customId: $scope.custom.id, content: $scope.contentInfo})
					.success(function(data){
						$scope.reload();
						$scope.formresult = data;
					});
		}
	};
	
});

//表单数据列表
app.controller('FormDataListCtrl',function($scope, $http, $routeParams, 
		ngTableParams, FormDataListService, FormDesignService, FormDataService) {
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.initParams = function(){
		$scope.queryParams = {
				page: initpage,
				rows: initrows,
				formid: $routeParams.formid,
				isreload: true
		};
	};
	$scope.initParams();
	/*重新加载*/
	$scope.reload = function(){
		$scope.initParams();
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			FormDataListService.queryList($scope.queryParams, function(data){
				var total = data.pagingResult.total;
				$scope.queryParams.total = total;
				params.total(total);
				$defer.resolve(data.pagingResult.rows);
			});
		}
	});
	
	 /*
	 * 删除
	 */
	$scope.del = function(formdata){
		if(confirm("确认删除?") === false) return;
		formdata.isdeling = true;
		FormDataService.del({id: formdata.id}, function(data){
			$scope.reload();
		});
	};
	  
	/*
	 * 加载表单信息
	 */
	FormDesignService.queryFormById({id: $routeParams.formid}, function(data){
		 $scope.formtitle = data.formResult.data.title;
		 $scope.formdtcreate = data.formResult.data.dtCreate;
		 $scope.formdtupdate = data.formResult.data.dtUpdate;
		 $scope.formdtpub = data.formResult.data.dtPub;
		 $scope.formFields = data.formFields;
	});
	
});

//表单设计页面
app.controller('FormDesignCtrl',function($scope, $routeParams, $http) {
	if($routeParams.id){
		$scope.id = $routeParams.id;
	}
	
	UE.delEditor('formdesigncontainer');
	var ue = null;
	ue = UE.getEditor('formdesigncontainer',{
		toolleipi: true,
		initialFrameWidth: 1024,
        initialFrameHeight: 200,
		enableAutoSave: false,
		enableContextMenu: true,
		toolbars:[['fullscreen',
		           'undo', 'redo', '|',
		           'bold', 'italic', 'underline', 'fontborder', 'strikethrough',  'removeformat', '|',
		           'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist','|',
		           'fontfamily', 'fontsize', '|',
		           'indent', '|',
		           'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
		           'link', 'unlink',  '|',
		           'horizontal',  'spechars',  'wordimage', '|',
		           'inserttable', 'deletetable',  'mergecells',  'splittocells', '|']],
       wordCount:false,//关闭字数统计
       elementPathEnabled:false//关闭elementPath
	});
	ue.addListener( 'ready', function( editor ) {
		if($routeParams.id){
			//字段数
			$http.post("formdesign/getformbyid", {id: $routeParams.id}).success(function(data){
				$scope.title = data.formResult.data.title;
				ue.execCommand('focus');
				ue.setContent(data.formResult.data.contentSrc);
			});
		}
	});
	//加载控件
	$scope.exec = function(method){
		ue.execCommand(method);
	};
	//保存
	$scope.save = function(type){
		if(confirm("确认保存?注意：保存之后原始表单以及对应表单已上报数据会清空。") == false) return;
		
		if(ue.queryCommandState( 'source' ))
			ue.execCommand('source');//切换到编辑模式才提交，否则有bug
		if($scope.title === undefined) {
			alert("标题不能为空");
			return;
		}else if($scope.title.length > 50){
			alert("标题长度不能大于50个字符");
			return;
		}
        if(ue.hasContents()){
        	ue.sync();/*同步内容*/
            //获取表单设计器里的内容
            var contentSrc = ue.getContent();
            //解析表单设计器控件
            var result = JSON.parse(parseForm(contentSrc, 0));
            
            $scope.issaving = true;
            
            $http.post("formdesign/add", {
            		'title': $scope.title,
            		'content': result.parse,
            		'contentSrc': contentSrc,
            		'fields':  JSON.stringify(result['add_fields']),
            		'id': $scope.id
            	}).success(function(data){
            		if(data.formResult.success == "0"){
            			alert(data.formResult.message);
            		}else{
            			$scope.id = data.formResult.data;
            			alert('保存成功');
            		}
            		$scope.issaving = false;
	            }).error(function(){
	            	alert("保存失败");
	            	$scope.issaving = false;
	            });
        } else {
            alert('内容不能为空');
        }
	};
	//预览
	$scope.preview = function(method){
		if(ue.queryCommandState( 'source' ))
            ue.execCommand('source');/*切换到编辑模式才提交，否则部分浏览器有bug*/
        if(ue.hasContents()){
	        ue.sync();       /*同步内容*/
	        //获取表单设计器里的内容
	        var content = ue.getContent();
	        //解析表单设计器控件
	        var formContent = parseForm(content, 0);
	        $scope.designZone = JSON.parse(formContent).parse;
	        $scope.isDesignZoneActive = true;
        } else {
            alert('内容不能为空！');
            return false;
        }
	};
	//解析表单
	function parseForm(template, fields){
		var preg =  /(\|-<span(((?!<span).)*plugins=\"(radios|checkboxs|select)\".*?)>(.*?)<\/span>-\||<(img|input|textarea|select).*?(<\/select>|<\/textarea>|\/>))/gi,
			preg_attr =/(\w+)=\"(.?|.+?)\"/gi,
			preg_group =/<input.*?\/>/gi;
        if(!fields) fields = 0;
        var template_parse = template,
        	template_data = new Array(),
        	add_fields = new Object(),
        	checkboxs = 0;

        var pno = 0;
        template.replace(preg, function(plugin,p1,p2,p3,p4,p5,p6){
            var parse_attr = new Array(),
            	attr_arr_all = new Object(),
            	name = '', 
            	select_dot = '' , 
            	is_new=false;
            var p0 = plugin;
            var tag = p6 ? p6 : p4;
            if(tag == 'radios' || tag == 'checkboxs'){
                plugin = p2;
            }else if(tag == 'select'){
                plugin = plugin.replace('|-','');
                plugin = plugin.replace('-|','');
            }
            plugin.replace(preg_attr, function(str0,attr,val) {
                if(attr=='name'){
                    if(val=='NEWFIELD'){
                        is_new=true;
                        fields++;
                        val = 'data_'+fields;
                    }
                    name = val;
                }
                if(tag=='select' && attr=='value'){
                    if(!attr_arr_all[attr]) attr_arr_all[attr] = '';
                    attr_arr_all[attr] += select_dot + val;
                    select_dot = ',';
                }else{
                    attr_arr_all[attr] = val;
                }
                var oField = new Object();
                oField[attr] = val;
                parse_attr.push(oField);
            });
            if(tag =='checkboxs') /*复选组  多个字段 */{
                plugin = p0;
                plugin = plugin.replace('|-','');
                plugin = plugin.replace('-|','');
                var name = 'checkboxs_'+checkboxs;
                attr_arr_all['parse_name'] = name;
                attr_arr_all['name'] = '';
                attr_arr_all['value'] = '';
                attr_arr_all['content'] = 
                	'<span plugins="checkboxs" valids="'+attr_arr_all['valids']+'"'
                	+'>';
                var dot_name ='', dot_value = '';
                p5.replace(preg_group, function(parse_group) {
                    var is_new=false,option = new Object();
                    parse_group.replace(preg_attr, function(str0,k,val) {
                        if(k=='name'){
                        	if(val=='NEWFIELD'){
                                is_new=true;
                                fields++;
                                val = 'data_'+fields;
                            }
                            attr_arr_all['name'] += dot_name + val;
                            dot_name = ',';
                        }else if(k=='value'){
                            attr_arr_all['value'] += dot_value + val;
                            dot_value = ',';
                        }
                        option[k] = val;
                    });
                    if(!attr_arr_all['options']) attr_arr_all['options'] = new Array();
                    attr_arr_all['options'].push(option);
                    if(!option['checked']) option['checked'] = '';
                    var checked = option['checked'] ? 'checked="checked"' : '';
                    attr_arr_all['content'] +=
                    		'<input type="checkbox" name="'
                    		+option['name']
                    		+'" value="'+option['value']
                    		+'" fieldname="' + attr_arr_all['fieldname'] 
                    		+ option['fieldname'] 
                    		+ '"' + checked+'/>'+option['value']+'&nbsp;';
                    if(is_new){
                        var arr = new Object();
                        arr['name'] = option['name'];
                        arr['plugins'] = attr_arr_all['plugins'];
                        arr['fieldname'] = attr_arr_all['fieldname'] + option['fieldname'];
                        arr['fielddesc'] = attr_arr_all['orgdesc'];
                        
                        arr['valids'] = attr_arr_all['valids'];
                        
                        add_fields[option['name']] = arr;
                    }
                });
                attr_arr_all['content'] += '</span>';
                //parse
                template = template.replace(plugin,attr_arr_all['content']);
                template_parse = template_parse.replace(plugin,'{'+name+'}');
                template_parse = template_parse.replace('{|-','');
                template_parse = template_parse.replace('-|}','');
                template_data[pno] = attr_arr_all;
                checkboxs++;
            }else if(name){
                if(tag =='radios'){ /*单选组  一个字段*/
                    plugin = p0;
                    plugin = plugin.replace('|-','');
                    plugin = plugin.replace('-|','');
                    attr_arr_all['value'] = '';
                    attr_arr_all['content'] = '<span plugins="radios" name="'
                    	+ attr_arr_all['name']
                    	+'" valids="'+attr_arr_all['valids']+'">'
                    	;
                    
                    var dot='';
                    p5.replace(preg_group, function(parse_group) {
                        var option = new Object();
                        parse_group.replace(preg_attr, function(str0,k,val) {
                            if(k=='value'){
                                attr_arr_all['value'] += dot + val;
                                dot = ',';
                            }
                            option[k] = val;
                        });
                        option['name'] = attr_arr_all['name'];
                        if(!attr_arr_all['options']) attr_arr_all['options'] = new Array();
                        attr_arr_all['options'].push(option);
                        if(!option['checked']) option['checked'] = '';
                        var checked = option['checked'] ? 'checked="checked"' : '';
                        attr_arr_all['content'] +='<input type="radio" name="'+attr_arr_all['name']+'" value="'+option['value']+'"  '+checked+'/>'+option['value']+'&nbsp;';
                    });
                    attr_arr_all['content'] += '</span>';
                }else{
                    attr_arr_all['content'] = is_new ? plugin.replace(/NEWFIELD/,name) : plugin;
                }
                template = template.replace(plugin,attr_arr_all['content']);
                template_parse = template_parse.replace(plugin,'{'+name+'}');
                template_parse = template_parse.replace('{|-','');
                template_parse = template_parse.replace('-|}','');
                if(is_new){
                    var arr = new Object();
                    arr['name'] = name;
                    arr['plugins'] = attr_arr_all['plugins'];
                    arr['orgtype'] = attr_arr_all['orgtype'];
                    arr['fieldname'] = attr_arr_all['fieldname'];
                    arr['fielddesc'] = attr_arr_all['orgdesc'];
                    arr['valids'] = attr_arr_all['valids'];
                    add_fields[arr['name']] = arr;
                }
                template_data[pno] = attr_arr_all;
            }
            pno++;
        });
        var view = template.replace(/{\|-/g,'');
        view = view.replace(/-\|}/g,'');
        var parse_form = new Object({
            'fields':fields,//总字段数
            'template':template,//完整html
            'parse':view,
            'data':template_data,//控件属性
            'add_fields':add_fields//新增控件
        });
        return JSON.stringify(parse_form);
	}
	
});
//表单列表
app.controller('FormDesignListCtrl',function($scope, $http, ngTableParams,
		FormDesignListService, FormDesignService) {
	//重置
	$scope.reset = function(){
		$scope.searchTitle = null;
		$scope.dtCreateBegin = null;
		$scope.dtCreateEnd = null;
		$scope.dtPubBegin = null;
		$scope.dtPubEnd = null;
		$scope.ispub = null;
		$scope.pubtype = null;
	};
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.queryParams = {
			page: initpage,
			rows: initrows,
			isreload: true
	};
	/*重新加载*/
	$scope.reload = function(){
		$scope.queryParams = {
				page: $scope.queryParams.page,
				rows: $scope.queryParams.rows,
				'dtcreate_begin': $scope.dtCreateBegin,
				'dtcreate_end': $scope.dtCreateEnd,
				'dtpub_begin': $scope.dtPubBegin,
				'dtpub_end': $scope.dtPubEnd,
				ispub: $scope.ispub,
				title: $scope.searchTitle,
				pubtype: $scope.pubtype,
				isreload: true
		};
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			FormDesignListService.queryList($scope.queryParams, function(data){
				var total = data.pagingResult.total;
				$scope.queryParams.total = total;
				params.total(total);
				$defer.resolve(data.pagingResult.rows);
			});
		}
	});
	
	/*
	 * 发布
	 */
	$scope.pub = function(id, type){
		FormDesignService.pub({id: id, type: type}, function(){
			$scope.reload();
		});
	};
	/*
	 * 撤销发布
	 */
	$scope.canclepub = function(id){
		FormDesignService.canclepub({id: id}, function(){
			$scope.reload();
		});
	};
	/*
	 * 删除
	 */
	$scope.del = function(form){
		if(confirm("确认删除?") === false) return;
		form.isdeling = true;
		FormDesignService.del({id: form.id}, function(){
			$scope.reload();
		});
	};
	
});
app.factory('FormDataListService', [ '$http', function($http) {
	return {
		//查询表单列表
		queryList: function(queryParams, cb){
			$http.post("formdesign/getformdatas", queryParams).success(function(data){
				 var newItems = [],
				 	rows = data.pagingResult.rows;
				 if(rows && rows.length > 0){
					 for(var i in rows){
						 var row = rows[i];
						 newItems.push({
							 id: row.id,
							 dt_create: row.dt_create,
							 form_data: JSON.parse(row.form_data)
						 });
					 }
				 }
				data.pagingResult.rows = newItems;
				if(cb) cb(data);
			});
		}
	};
}]);
app.factory('FormDataService', [ '$http', function($http) {
	return {
		//查询表单列表
		del: function(params, cb){
			$http.post("formdesign/formdatadel", params).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);
app.factory('FormDesignListService', [ '$http', function($http) {
	return {
		//查询表单列表
		queryList: function(queryParams, cb){
			$http.post("formdesign/getforms", queryParams).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);
app.factory('FormDesignService', [
		'$http',
		function($http) {
			return {
				// 查询单条表单信息并处理
				queryFormById : function(queryParams, cb) {
					$http.post("formdesign/getformbyid", queryParams).success(function(data) {
						var fields = JSON.parse(data.formResult.data.fields);
						var newFields = [];
						var maxField = 5;
						var flag = 0;
						$.each(fields, function(key, val) {
							if (flag >= maxField) {
								return false;
							}
							flag++;
							if (val && val.fielddesc) {
								newFields.push({
									name : key,
									desc : val.fielddesc
								});
							}
						});
						data.formFields = newFields;
						if(cb) cb(data);
					});
				},
				//删除表单
				del: function(params, cb){
					$http.post("formdesign/del", params).success(function(data){
						if(cb) cb(data);
					});
				},
				//发布表单
				pub: function(params, cb){
					$http.post("formdesign/pub", params).success(function(data){
						if(cb) cb(data);
					});
				},
				//撤销发布表单
				canclepub: function(params, cb){
					$http.post("formdesign/canclepub", params).success(function(data){
						if(cb) cb(data);
					});
				}
			};
		} ]);

//首页
app.controller('IndexCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "#";
	
	$scope.isRequest = true;
	
	$http.post("index/getIndex", {}).success(function(data){
		$scope.data = data;
		$scope.isRequest = false;
	});
	
});
//发票管理-录入发票
app.controller('InvoiceMgrInInvoiceCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope, $location) {
	$rootScope.menu = "invoice";
	
	var invoiceId = $routeParams.id;
	$scope.isReady = false;
	
	$scope.USER = LOGINUSER;
	
	if(invoiceId){//编辑
		//请求发票信息
		$http.post("invoice/getInvoiceViewById", {id: invoiceId}).success(function(data){
			$scope.invoice = data.invoice;
			$scope.applyUserDesc = data.applyUserDesc;
			
			$scope.invoice.cc = {
				customId: data.invoice.customId,
				contractNo: data.invoice.contractNo
			};
			
			$scope.invoice.rd = {
				resumeId: data.invoice.resumeId,
				resumeDesc: data.invoice.resumeDesc
			};
			
			$scope.isReady = true;
		});
	}else{//新增
		$scope.isReady = true;
	}
	
	//删除发票
	$scope.delCommun = function(item){
		$http.post("invoice/del", {id: item.id}).success(function(data){
			$scope.formresult = data;
		});
	};
	//保存发票信息
	$scope.save = function(){
		validFormAndSubmit(function(data){
			$scope.formresult = data;
			if(data.success == "1"){//成功
				$location.path("/invoicemgr/list");
			}
		});
	};
	
	//验证表单并提交
	function validFormAndSubmit(cb){
		if(validForm()){
			$scope.isrequest = true;
			
			$scope.invoice.customId = $scope.invoice.cc.customId;
			$scope.invoice.contractNo = $scope.invoice.cc.contractNo;
			
			$scope.invoice.resumeId = $scope.invoice.rd.resumeId;
			$scope.invoice.resumeDesc = $scope.invoice.rd.resumeDesc;
			
			$http.post("invoice/edit", $scope.invoice).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(cb) cb(data);
			});
		}

	};
	
	//验证表单
	function validForm(){
		return $("#invoiceeditform").isHappy({
			fields: {
				//申请人
				team: {required: true},
				//开票人
				checkUser: {required: true},
				//申请时间
				applyTime: {required: true, date: true},
				//客户+合同编号
				customContract: {required: true, maxlength: 100},
				//发票类型
				type: {required: true, maxlength: 50},
				//发票属性
				property: {required: true, maxlength: 100},
				//发票金额
				total: {required: true, number: true},
				//发票状态
				state: {required: true, maxlength: 100},
				//回款状态
				incomeState: {required: true, maxlength: 50},
				//备注
				comment: {maxlength: 500}
			}
		});
	}
	
});
//发票管理-列表
app.controller('InvoiceMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "invoice";
	
	//用户是否能够开发票
	$http.post("invoice/isCanInvoice", {}).success(function(data){
		$scope.isCanInvoice = data.isCanInvoice;
	});
	
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.search = {};
	$scope.queryParams = {rows: initrows};
	$scope.initParams = function(pageNotChange){
		var iPage = initpage;
		if(pageNotChange){
			iPage = $scope.queryParams.page;
		}
		var temp = {
				page: iPage,
				rows: $scope.queryParams.rows,
				isreload: true
			};
		angular.extend(temp, $scope.search);
		angular.extend($scope.queryParams, temp);
	};
	$scope.initParams();
	/*重新加载*/
	$scope.reload = function(pageNotChange){
		$scope.initParams(pageNotChange);
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			$http.post("invoice/list", $scope.queryParams).success(function(data){
				var total = data.total;
				$scope.queryParams.total = total;
				params.total(total);
				params.page($scope.queryParams.page);
				$defer.resolve(data.rows);
			});
		}
	});

	//监控查询条件
	$scope.$watch('search', function(newValue, oldValue){
		if(newValue == oldValue) return;
		$scope.reload();
	}, true);
	
	//删除
	$scope.del = function(item){
		$http.post("invoice/del", {id: item.id}).success(function(data){
			$scope.formresult = data;
			$scope.reload(true);
		});
	};
	
});
//发票管理-发票信息查看
app.controller('InvoiceMgrViewInvoiceCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "invoice";
	
	var invoiceId = $routeParams.id;
	$scope.isReady = false;
	
	//请求客户信息
	$http.post("invoice/getInvoiceViewById", {id: invoiceId}).success(function(data){
		$scope.invoice = data.invoice;
		$scope.isReady = true;
	});
	
});
//职位管理-录入职位
app.controller('JobMgrInJobCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				$rootScope, $location) {
	$rootScope.menu = "job";
	
	var jobId = $routeParams.id;
	var customId = $routeParams.customId;
	$scope.isReady = false;
	//请求职位信息
	if(jobId){
		$http.post("job/getJobViewById", {id: jobId}).success(function(data){
			$scope.job = data.job;
			$scope.custom = data.custom;
			$scope.isReady = true;
		});
	}else if(customId){
		$http.post("custom/getCustomById", {id: customId}).success(function(data){
			$scope.custom = data.custom;
			$scope.isReady = true;
		});
		$scope.job = {
				state: "运作",
				sexLimit: "不限"
		};
	}else{
		$scope.isReady = true;
		$scope.job = {
				state: "运作",
				sexLimit: "不限"
		};
	}
	
	//保存职位信息
	$scope.save = function(){
		validFormAndSubmit(function(data){
			if(data.success == "1"){//成功
				if(customId){
					$location.path("custommgr/viewcustom/" + customId);
				}else{
					$location.path("jobmgr/viewjob/" + jobId);
				}
			}
		});
	};
	
	//验证表单并提交
	function validFormAndSubmit(cb){
		if(validForm()){
			$scope.isrequest = true;
			$scope.job.customId = $scope.custom.id;
			$http.post("job/edit", $scope.job).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(cb) cb(data);
			});
		}

	};
	
	//验证表单
	function validForm(){
		return $("#jobeditform").isHappy({
			fields: {
				customName: {required: true, maxlength: 500},
				//职位名称
				name: {required: true, maxlength: 100},
				//薪资待遇-最小
				payMin: {number: true},
				//薪资待遇-最大
				payMax: {number: true},
				//职位状态
				state: {required: true},
				//执行团队
				team: {required: true},
				//工作地点
				workplace: {required: true, maxlength: 200},
				//职位类别
				jobType: {required: true, maxlength: 100},
				//所属行业
				industry: {required: true, maxlength: 200},
				//工作年限
				workYear: {required: true, digits: true},
				//需求人数
				requirePeople: {required: true, digits: true},
				//所属部门
				department: {required: true, maxlength: 100},
				//汇报对象
				reportObj: {required: true, maxlength: 100},
				//年龄要求-最小
				ageMin: {required: true, digits: true},
				//年龄要求-最大
				ageMax: {required: true, digits: true},
				//性别要求
				//学历要求
				//语言要求
				languageLimit: {maxlength: 100},
				//职位描述
				jobDesc: {required: true, maxlength: 500},
				//公司介绍
				companyDesc: {required: true, maxlength: 500}
			}
		});
	}
	
});
//职位管理列表页面
app.controller('JobMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "job";
	$scope.search = {};
	
	$scope.changeSelect = function(target){
		$(target).siblings().removeClass("select");
		$(target).addClass("select");
	}
	
	//是否显示我的列表
	var who = $routeParams.who;
	if(who){
		$scope.search.beyond = who;
		$scope.changeSelect($("#beyond-my"));
	}
	
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.queryParams = {rows: initrows};
	$scope.initParams = function(pageNotChange){
		var iPage = initpage;
		if(pageNotChange){
			iPage = $scope.queryParams.page;
		}
		var temp = {
				page: iPage,
				rows: $scope.queryParams.rows,
				isreload: true
			};
		angular.extend(temp, $scope.search);
		angular.extend($scope.queryParams, temp);
	};
	$scope.initParams();
	/*重新加载*/
	$scope.reload = function(pageNotChange){
		$scope.initParams(pageNotChange);
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			$http.post("job/list", $scope.queryParams).success(function(data){
				var total = data.total;
				$scope.queryParams.total = total;
				params.total(total);
				params.page($scope.queryParams.page);
				$defer.resolve(data.rows);
			});
		}
	});

	//监控查询条件
	$scope.$watch('search', function(newValue, oldValue){
		if(newValue == oldValue) return;
		$scope.reload();
	}, true);
	
	//删除
	$scope.del = function(item){
		$http.post("job/del", {id: item.id}).success(function(data){
			$scope.formresult = data;
			$scope.reload(true);
		});
	};
	
});
//职位管理-职位信息
app.controller('JobMgrViewJobCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "job";
	
	var jobId = $routeParams.id;
	$scope.isReady = false;
	
	//请求职位信息
	var loadViewInfo = function(){
		$scope.isReady = false;
		$http.post("job/getJobViewById", {id: jobId}).success(function(data){
			$scope.job = data.job;
			$scope.custom = data.custom;
			$scope.inteams = data.inteamresumes;
			$scope.pubresumes = data.pubresumes;
			$scope.jobcomms = data.jobcomms;
			$scope.jobcommsFile = data.jobcommsFile;
			$scope.beyond = data.beyond;
			$scope.jobTeamDesc = data.jobTeamDesc;
			$scope.jobrecstate = handleJobrecstate(data.jobrecstate);//简历推荐状态
			$scope.isReady = true;
		});
	};
	loadViewInfo();
	//将简历推荐状态数据转换为map格式
	function handleJobrecstate(items){
		var ret = {};
		var all = 0;
		var notHandle = 0;
		if(items && items.length > 0){
			$.each(items, function(idx, val){
				ret[val.verify_state] = val.cou;
				all += val.cou;
				if(!val.verify_state){
					notHandle += val.cou;
				}
			});
		}
		ret.all = all;
		ret.notHandle = notHandle;
		//企业面试人
		ret.mianshi = safeN(ret['初试']) + safeN(ret['复试']) + safeN(ret['终试']);
		//企业否决
		ret.pass = safeN(ret['面试不通过']) + safeN(ret['审核不通过']) + safeN(ret['候选人放弃']);
		//企业确定录用
		ret.ok = safeN(ret['offer']) + safeN(ret['入职']);
		
		return ret;
	}
	function safeN(val){
		if(val === null || val === undefined || val === ''){
			return 0
		}else{
			return val;
		}
	}
	
	//向企业投递简历
	$scope.pubResume = function(id){
		$http.post("job/pubResume", {rjId: id}).success(function(data){
			$scope.formresult = data;
			if(data.success == 1){
				loadViewInfo();
			}
		});
	};
	//取消投递简历
	$scope.cancleResume = function(id){
		$http.post("job/cancleResume", {rjId: id}).success(function(data){
			$scope.formresult = data;
			if(data.success == 1){
				loadViewInfo();
			}
		});
	};
	
	//审核向企业投递的简历
	$scope.verify = function(id, status){
		$http.post("job/verifyResume", {rjId: id, status: status})
			.success(function(data){
				$scope.formresult = data;
				if(data.success == 1){
					loadViewInfo();
				}
		});
	};
	
	//添加交流信息
	$scope.addComm = function(){
		$http.post("job/editJobComm", {jobId: jobId, content: $scope.addcomm})
			.success(function(data){
				$scope.formresult = data;
				if(data.success == 1){
					loadViewInfo();
				}
		});
	};
	//删除交流信息
	$scope.delComm = function(id){
		$http.post("job/delJobComm", {id: id})
		.success(function(data){
			$scope.formresult = data;
			if(data.success == 1){
				loadViewInfo();
			}
		});
	};
	
	//加载推荐简历的沟通记录
	$scope.loadRJComm = function(id){
		$scope.rjResumeId = id;
		$scope.addRJCommContent = null;
		$scope.comms = null;
		$http.post("job/loadRJComm", {id: id})
		.success(function(data){
			$scope.comms = data;
			$scope.showcommdialog=true;
			$scope.editRJCommID = id;
		});
	};
	//新增推荐简历的沟通记录
	$scope.addRJComm = function(){
		$http.post("job/editRJComm", 
				{resumejobId: $scope.rjResumeId, content: $scope.addRJCommContent}
		)
		.success(function(data){
			$scope.loadRJComm($scope.rjResumeId);
			$scope.formresult = data;
		});
	};
	//删除推荐简历的沟通记录
	$scope.delRJComm = function(id){
		$http.post("job/delRJComm", {id: id})
		.success(function(data){
			$scope.formresult = data;
			$scope.loadRJComm($scope.rjResumeId);
		});
	};
	
	//添加交流信息表-附件格式
	$scope.addCommFile = function(){
		$http.post("job/addCommFile", {ids: $scope.addcommFile, jobId: jobId})
			.success(function(data){
				$scope.formresult = data;
				if(data.success == 1){
					loadViewInfo();
					$scope.addcommFile = null;
					$scope.attachs = null;
				}
		});
	};
	//删除交流信息表-附件格式
	$scope.delCommFile = function(id){
		$http.post("fileDel", {id: id})
		.success(function(data){
			$scope.formresult = data;
			if(data.success == 1){
				loadViewInfo();
			}
		});
	};
	
});
app.factory('JobService', [ '$http', function($http) {
	return {
		//
		test: function(params, cb){
			$http.post("formdesign/formdatadel", params).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);
//绩效管理
app.controller('PerformanceMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				PerformanceService, $rootScope) {
	$rootScope.menu = "performance";
	
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.search = {};
	$scope.queryParams = {rows: initrows};
	$scope.initParams = function(pageNotChange){
		var iPage = initpage;
		if(pageNotChange){
			iPage = $scope.queryParams.page;
		}
		var temp = {
				page: iPage,
				rows: $scope.queryParams.rows,
				isreload: true
			};
		angular.extend(temp, $scope.search);
		angular.extend($scope.queryParams, temp);
	};
	$scope.initParams();
	/*重新加载*/
	$scope.reload = function(pageNotChange){
		$scope.initParams(pageNotChange);
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			$http.post("performance/list", $scope.queryParams).success(function(data){
				var total = data.total;
				$scope.queryParams.total = total;
				params.total(total);
				params.page($scope.queryParams.page);
				$defer.resolve(data.rows);
			});
		}
	});

	//监控查询条件
	$scope.$watch('search', function(newValue, oldValue){
		if(newValue == oldValue) return;
		$scope.reload();
	}, true);
	
});
app.factory('PerformanceService', [ '$http', function($http) {
	return {
		//
		test: function(params, cb){
			$http.post("formdesign/formdatadel", params).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);
//公告管理-录入公告
app.controller('PubMgrInPubCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				$rootScope) {
	$rootScope.menu = "pub";
	
});
//公告管理
app.controller('PubMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
			$rootScope) {
	$rootScope.menu = "pub";
	
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.search = {};
	$scope.queryParams = {rows: initrows};
	$scope.initParams = function(pageNotChange){
		var iPage = initpage;
		if(pageNotChange){
			iPage = $scope.queryParams.page;
		}
		var temp = {
				page: iPage,
				rows: $scope.queryParams.rows,
				isreload: true
			};
		angular.extend(temp, $scope.search);
		angular.extend($scope.queryParams, temp);
	};
	$scope.initParams();
	/*重新加载*/
	$scope.reload = function(pageNotChange){
		$scope.initParams(pageNotChange);
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			$http.post("pub/list", $scope.queryParams).success(function(data){
				var total = data.total;
				$scope.queryParams.total = total;
				params.total(total);
				params.page($scope.queryParams.page);
				$defer.resolve(data.rows);
			});
		}
	});

	//监控查询条件
	$scope.$watch('search', function(newValue, oldValue){
		if(newValue == oldValue) return;
		$scope.reload();
	}, true);
	
	//删除
	$scope.del = function(item){
		$http.post("pub/del", {id: item.id}).success(function(data){
			$scope.formresult = data;
			$scope.reload(true);
		});
	};
	
	//添加公告
	$scope.add = function(){
		if($("#form-pub").isHappy({
			fields: {
				addcontent: {required: true, maxlength: 500}
			}
		})){
			$http.post("pub/edit", {content: $scope.addcontent}).success(function(data){
				$scope.addcontent = null;
				$scope.formresult = data;
				$scope.reload(true);
			});
		}
	};
	
	//发布公告
	$scope.pub = function(item){
		$http.post("pub/pubp", {id: item.id}).success(function(data){
			$scope.formresult = data;
			$scope.reload(true);
		});
	};
	
	//取消发布公告
	$scope.canclepub = function(item){
		$http.post("pub/canclepub", {id: item.id}).success(function(data){
			$scope.formresult = data;
			$scope.reload(true);
		});
	};
	
});
app.factory('PubService', [ '$http', function($http) {
	return {
		//
		test: function(params, cb){
			$http.post("formdesign/formdatadel", params).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);
//简历管理-录入简历
app.controller('ResumeMgrInResumeCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "resume";
	
	var resumeId = $routeParams.id;
	$scope.isReady = false;
	
	$scope.target = {
			targetTypeA:{
				all: false,
				part: false,
				learn: false
			}
	};
	
	if(resumeId){//编辑
		//请求简历信息
		$http.post("resume/getResumeViewById", {id: resumeId}).success(function(data){
			$scope.resume = data.resume;
			var targets = data.target;
			if(targets && targets.length >0){
				var tar = targets[0];
				$scope.target = {
					id: tar.id,
					resumeId: tar.resume_id,
					targetType: tar.target_type,
					targetPlace: tar.target_place,
					targetJob: tar.target_job,
					targetIndustry: tar.target_industry,
					targetPay: tar.target_pay,
					workState: tar.work_state
				};
				if(tar.target_type){
					$scope.target .targetTypeA = {
						all: false,
						part: false,
						learn: false
					};
					if(tar.target_type.indexOf("全职") != -1){
						$scope.target.targetTypeA.all = true;
					}
					if(tar.target_type.indexOf("兼职") != -1){
						$scope.target.targetTypeA.part = true;
					}
					if(tar.target_type.indexOf("实习") != -1){
						$scope.target.targetTypeA.learn = true;
					}
				}
			}
			$scope.resumeDatas = data.resumeDatas;
			$scope.resumeEdus = data.resumeEdus;
			$scope.resumeJobs = data.resumeJobs;
			$scope.resumeLanguages = data.resumeLanguages;
			$scope.resumeWorkhistorys = data.resumeWorkhistorys;
			
			$scope.isReady = true;
			
		});
	}else{//新增
		$scope.isReady = true;
		
		$scope.target = {};
		$scope.resumeDatas = [];
		$scope.resumeEdus = [];
		$scope.resumeJobs = [];
		$scope.resumeLanguages = [];
		$scope.resumeWorkhistorys = [];
	}
	
	//提交基本信息
	$scope.saveBaseInfo = function(next){
		if($("#resumeeditform-baseinfo").isHappy({
			fields: {
				//姓名
				name: {required: true, maxlength: 100},
				//居住地址
				address: {required: true, maxlength: 200},
				//性别
				sex: {required: true, maxlength: 10},
				//最高学历
				education: {required: true, maxlength: 100},
				//出生日期
				birthcay: {required: true, date: true},
				//婚姻状况
				marrage: {required: true, maxlength: 20},
				//手机号码
				phone: {required: true, telephone:true, maxlength: 100},
				//电子邮箱
				email: {required: true, email:true, maxlength: 100},
				//目前年薪
				yearPay: {required: true, number: true},
				//工作状态
				workState: {required: true, maxlength: 50},
				//行业
				industry: {maxlength: 100},
				//职位
				duty: {maxlength: 50},
				//个人简介
				desc: {required: true, maxlength: 500}
			}
		})){
			$scope.isrequest = true;
			$http.post("resume/saveBaseinfo", $scope.resume).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1"){//成功
					$scope.resume.id = data.data.id;
					if(next){
						$scope.show = "language";
					}
				}
			});
		}
	};
	
	//保存求职意向
	$scope.saveTarget = function(next){
		if($("#resumeeditform-target").isHappy({
			fields: {
				//期望工作性质
				worktype: {maxlength: 100},
				//期望工作地点
				targetPlace: {maxlength: 100},
				//期望从事职业
				targetJob: {maxlength: 100},
				//期望从事行业
				targetIndustry: {maxlength: 100},
				//期望月薪
				targetPay: {maxlength: 100},
				//工作状态
				workState: {maxlength: 100}
			}
		})){
			$scope.isrequest = true;
			$scope.target["resumeId"] = $scope.resume.id;
			
			//工作性质
			var targetType = "";
			if($scope.target.targetTypeA.all){
				targetType += "全职,";
			}
			if($scope.target.targetTypeA.part){
				targetType += "兼职,";
			}
			if($scope.target.targetTypeA.learn){
				targetType += "实习,";
			}
			$scope.target.targetType = targetType;
			
			$http.post("resume/saveTarget", $scope.target).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1"){//成功
					$scope.target.id = data.data.id;
					if(next){
						$scope.show = "workhistory";
					}
				}
			});
		}
	};
	//从数据中删除对象
	function delItemFormArray(at, id){
		var newAttas = [];
		var target = $scope[at];
		for(var idx in target){
			var atta = target[idx];
			if(atta.id != id){
				newAttas.push(atta);
			}
		}
		$scope[at] = newAttas;
	}
	//保存工作经历
	$scope.saveWorkHistory = function(next){
		if($("#resumeeditform-workhistory").isHappy({
			fields: {
				//工作时间-从
				timeBegin: {required: true, date: true},
				//工作时间-到
				timeEnd: {date: true},
				//公司名
				company: {required: true, maxlength: 100},
				//内容
				content: {required: true, maxlength: 500},
				//职位
				zhiwei: {required: true, maxlength: 100},
				//行业
				hangye: {required: true, maxlength: 500}
			}
		})){
			$scope.isrequest = true;
			$scope.workhistory["resumeId"] = $scope.resume.id;
			$http.post("resume/saveWorkHistory", $scope.workhistory).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1"){//成功
					var item = data.data;
					$scope.resumeWorkhistorys.push({
						id: item.id,
						resume_id: item.resumeId,
						time_begin: item.timeBegin,
						time_end: item.timeEnd,
						content: item.content,
						company: item.company,
						zhiwei: item.zhiwei,
						hangye: item.hangye
					});
					$("#hangye-tag").find("[name=hangye]").val("");
					$scope.workhistory = {};
					if(next){
						$scope.show = "icon";
					}
				}
			});
		}
	};
	//删除工作经历
	$scope.delWorkHistory = function(id){
		$http.post("resume/delWorkHistory", {id: id}).success(function(data){
			$scope.formresult = data;
			$scope.isrequest = false;
			if(data.success == "1"){//成功
				delItemFormArray("resumeWorkhistorys", id);
			}
		});
	};
	
	//保存教育经历
	$scope.saveEduHistory = function(next){
		if($("#resumeeditform-eduhistory").isHappy({
			fields: {
				//培训时间-从
				timeBegin: {required: true, date: true},
				//培训时间-到
				timeEnd: {date: true},
				//培训机构
				org: {required: true, maxlength: 100},
				//课程
				course: {required: true, maxlength: 500}
			}
		})){
			$scope.isrequest = true;
			$scope.eduhistory["resumeId"] = $scope.resume.id;
			$http.post("resume/saveEduHistory", $scope.eduhistory).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1"){//成功
					var item = data.data;
					$scope.resumeEdus.push({
						id: item.id,
						resume_id: item.resumeId,
						time_begin: item.timeBegin,
						time_end: item.timeEnd,
						org: item.org,
						course: item.course,
						content: item.content
					});
					$scope.eduhistory = {};
					if(next){
						$scope.show = "target";
					}
					
				}
			});
		}
	};
	//删除教育经历
	$scope.delEduHistory = function(id){
		$http.post("resume/delEduHistory", {id: id}).success(function(data){
			$scope.formresult = data;
			$scope.isrequest = false;
			if(data.success == "1"){//成功
				delItemFormArray("resumeEdus", id);
			}
		});
	};
	

	//保存语言
	$scope.saveLanguage = function(next){
		if($("#resumeeditform-language").isHappy({
			fields: {
				//语种
				lanType: {required: true, maxlength: 100},
				//读写能力
				readAb: {required: true, maxlength: 100},
				//听说能力
				listenAb: {required: true, maxlength: 100},
				//内容
				content: {required: true, maxlength: 500}
			}
		})){
			$scope.isrequest = true;
			$scope.language["resumeId"] = $scope.resume.id;
			$http.post("resume/saveLanguage", $scope.language).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1"){//成功
					var item = data.data;
					$scope.resumeLanguages.push({
						id: item.id,
						resume_id: item.resumeId,
						lan_type: item.lanType,
						read_ab: item.readAb,
						listen_ab: item.listenAb,
						content: item.content
					});
					$scope.language = {};
					if(next){
						$scope.show = "target";
					}
				}
			});
		}
	};
	//删除语言
	$scope.delLanguage = function(id){
		$http.post("resume/delLanguage", {id: id}).success(function(data){
			$scope.formresult = data;
			$scope.isrequest = false;
			if(data.success == "1"){//成功
				delItemFormArray("resumeLanguages", id);
			}
		});
	};
	
	//头像部分处理
	// Create variables (in this scope) to hold the API and image size
	  var jcrop_api,
	  boundx,
	  boundy,
	  
	  // Grab some information about the preview pane
	  $preview = $('#preview-pane'),
	  $pcnt = $('#preview-pane .preview-container'),
	  $pimg = $('#preview-pane .preview-container img');
	  
	  function updatePreview(c){
	    if (parseInt(c.w) > 0) {
	      var rx = 100 / c.w;
	      var ry = 100 / c.h;
	      
	      $scope.icon = c;
	      
	      $pimg.css({
	        width: Math.round(rx * boundx) + 'px',
	        height: Math.round(ry * boundy) + 'px',
	        marginLeft: '-' + Math.round(rx * c.x) + 'px',
	        marginTop: '-' + Math.round(ry * c.y) + 'px'
	      });
	    }
	  };


	//地址变化
	$scope.$watch('upicondata.data.id', function(newValue, oldValue){
		if(jcrop_api) jcrop_api.destroy();
		setTimeout(function(){
			$scope.icon = {};
			$('#icon_head').Jcrop({
				onChange: updatePreview,
			    onSelect: updatePreview,
			    width: 200,
			    height: 200,
			    boxWidth: 700,
			    aspectRatio: 1
			}, function(){
				jcrop_api = this;
				var bounds = this.getBounds();
			    boundx = bounds[0];
			    boundy = bounds[1];
			});
		}, 200);
	}, true);
	
	//上传头像
	$scope.saveIcon = function(next){
		
		if($scope.icon.x == undefined){//为选择
			$scope.formresult = {success: 0, message: "请截取图片"};
			return;
		}
		
		if($("#resumeeditform-icon").isHappy({
			fields: {
				
			}
		})){
			$scope.isrequest = true;
			$http.post("resume/saveIcon", {
				resumeId: $scope.resume.id,
				iconId: $scope.upicondata.data.id,
				x: parseInt($scope.icon.x),
				y: parseInt($scope.icon.y),
				w: parseInt($scope.icon.w),
				h: parseInt($scope.icon.h)
				}).success(function(data){
					$scope.formresult = data;
					$scope.isrequest = false;
					if(data.success == "1"){//成功
						var item = data.data;
						$scope.resume.headImage = item + "?_=" + Math.random();
						$scope.upicondata.data.id = null;
						if(next){
							$scope.show = "atta";
						}
					}
				});
		}
	};
	
	//上传附件
	$scope.uploadAttas = function(){
		if($("#resumeeditform-atta").isHappy({
			fields: {
				
			}
		})){
			$scope.isrequest = true;
			$http.post("resume/uploadAttas", {
				id: $scope.resume.id,
				attas: $scope.attas
				}).success(function(data){
					$scope.formresult = data;
					$scope.isrequest = false;
				});
		}
	};
});
//简历管理
app.controller('ResumeMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "resume";
	
	$scope.changeSelect = function(target){
		$(target).siblings().removeClass("select");
		$(target).addClass("select");
	}
	
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.search = {};
	$scope.queryParams = {rows: initrows};
	$scope.initParams = function(pageNotChange){
		var iPage = initpage;
		if(pageNotChange){
			iPage = $scope.queryParams.page;
		}
		var temp = {
				page: iPage,
				rows: $scope.queryParams.rows,
				isreload: true
			};
		angular.extend(temp, $scope.search);
		angular.extend($scope.queryParams, temp);
	};
	$scope.initParams();
	/*重新加载*/
	$scope.reload = function(pageNotChange){
		$scope.initParams(pageNotChange);
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			$http.post("resume/list", $scope.queryParams).success(function(data){
				var total = data.total;
				$scope.queryParams.total = total;
				params.total(total);
				params.page($scope.queryParams.page);
				$defer.resolve(data.rows);
			});
		}
	});

	//监控查询条件
	$scope.$watch('search', function(newValue, oldValue){
		if(newValue == oldValue) return;
		$scope.reload();
	}, true);
	
	//删除
	$scope.del = function(item){
		$http.post("resume/del", {id: item.id}).success(function(data){
			$scope.formresult = data;
			$scope.reload(true);
		});
	};
	
});
//简历管理-简历信息
app.controller('ResumeMgrViewResumeCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				$rootScope) {
	$rootScope.menu = "resume";
	
	var resumeId = $routeParams.id;
	$scope.isReady = false;
	
	function reload(){
		//请求客户信息
		$http.post("resume/getResumeViewById", {id: resumeId}).success(function(data){
			$scope.resume = data.resume;
			var targets = data.target;
			if(targets && targets.length >0){
				var tar = targets[0];
				$scope.target = {
					id: tar.id,
					resumeId: tar.resume_id,
					targetType: tar.target_type,
					targetPlace: tar.target_place,
					targetJob: tar.target_job,
					targetIndustry: tar.target_industry,
					targetPay: tar.target_pay,
					workState: tar.work_state
				};
			}
			$scope.resumeDatas = data.resumeDatas;
			$scope.resumeEdus = data.resumeEdus;
			$scope.resumeJobs = data.resumeJobs;
			$scope.resumeLanguages = data.resumeLanguages;
			$scope.resumeWorkhistorys = data.resumeWorkhistorys;
			$scope.resumeComms = data.resumeComms;
			$scope.beyond = data.beyond;
			
			$scope.isReady = true;
		});
	}
	
	reload();
	
	//添加沟通记录
	$scope.addComm = function(){
		$http.post("resume/addComm", {
			resumeId: $scope.resume.id,
			content: $scope.addcomm
		}).success(function(data){
			$scope.formresult = data;
			$scope.isrequest = false;
			reload();
			/*if(data.success == "1"){//成功
				var item = data.data;
				$scope.resumeComms.push({
					id: item.id,
					resume_id: item.resumeId,
					content: item.content,
					create_time: item.createTime
				});
			}*/
		});
	};
	
	//删除沟通记录
	$scope.delComm = function(id){
		$http.post("resume/delComm", {
			id: id
		}).success(function(data){
			$scope.formresult = data;
			$scope.isrequest = false;
			if(data.success == "1"){//成功
				//delItemFormArray("resumeComms", id);
				reload();
			}
		});
	};
	//从数据中删除对象
	function delItemFormArray(at, id){
		var newAttas = [];
		var target = $scope[at];
		for(var idx in target){
			var atta = target[idx];
			if(atta.id != id){
				newAttas.push(atta);
			}
		}
		$scope[at] = newAttas;
	}
});
//为简历推荐客户
app.controller('ResumePubListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "resume";
	
	var resumeId = $routeParams.id;
	$scope.resumeId = resumeId;
	
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.search = {};
	$scope.queryParams = {rows: initrows};
	$scope.initParams = function(pageNotChange){
		var iPage = initpage;
		if(pageNotChange){
			iPage = $scope.queryParams.page;
		}
		var temp = {
				page: iPage,
				rows: $scope.queryParams.rows,
				isreload: true
			};
		angular.extend(temp, $scope.search);
		angular.extend($scope.queryParams, temp);
	};
	$scope.initParams();
	/*重新加载*/
	$scope.reload = function(pageNotChange){
		$scope.initParams(pageNotChange);
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			$http.post("resume/getCustomList",
					$.extend({}, $scope.queryParams, {resumeId: resumeId}))
					.success(function(data){
				var total = data.total;
				$scope.queryParams.total = total;
				params.total(total);
				params.page($scope.queryParams.page);
				$defer.resolve(data.rows);
			});
		}
	});

	//监控查询条件
	$scope.$watch('search', function(newValue, oldValue){
		if(newValue == oldValue) return;
		$scope.reload();
	}, true);
	
	//推荐
	$scope.pub = function(item){
		$http.post("resume/pub", {resumeId: resumeId, jobId: item.id}).success(function(data){
			$scope.formresult = data;
			$scope.reload(true);
		});
	};
	//取消推荐
	$scope.canclePub = function(item){
		$http.post("resume/canclePub", {resumeId: resumeId, jobId: item.id}).success(function(data){
			$scope.formresult = data;
			$scope.reload(true);
		});
	};
	
});
app.factory('ResumeService', [ '$http', function($http) {
	return {
		//
		test: function(params, cb){
			$http.post("formdesign/formdatadel", params).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);
//团队管理
app.controller('TeamMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				$rootScope) {
	$rootScope.menu = "team";
	
	//分页查询
	var initpage = 1,
		initrows = 12;
	$scope.search = {};
	$scope.queryParams = {rows: initrows};
	$scope.initParams = function(pageNotChange){
		var iPage = initpage;
		if(pageNotChange){
			iPage = $scope.queryParams.page;
		}
		var temp = {
				page: iPage,
				rows: $scope.queryParams.rows,
				isreload: true
			};
		angular.extend(temp, $scope.search);
		angular.extend($scope.queryParams, temp);
	};
	$scope.initParams();
	/*重新加载*/
	$scope.reload = function(pageNotChange){
		$scope.initParams(pageNotChange);
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			$http.post("team/list", $scope.queryParams).success(function(data){
				var total = data.total;
				$scope.queryParams.total = total;
				params.total(total);
				params.page($scope.queryParams.page);
				$defer.resolve(data.rows);
			});
		}
	});

	//监控查询条件
	$scope.$watch('search', function(newValue, oldValue){
		if(newValue == oldValue) return;
		$scope.reload();
	}, true);
	
});
//团队管理-我的信息
app.controller('TeamMgrMyInfoCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				$rootScope) {
	$rootScope.menu = "team";
	
});
//用户管理-编辑用户
app.controller('UserMgrInUserCtrl',function($scope, $routeParams, $http,
		BaseInfoService, $rootScope, $location) {
	
	$rootScope.menu = "user";
	
	//新增or编辑
	if($routeParams.id){
		$scope.id = $routeParams.id;
		//请求用户信息
		$http.post("user/getUserById", {id: $scope.id}).success(function(user){
			user.createTime = null;
			user.updateTime = null;
			$scope.user = user;
		});
	}else{//新增
		BaseInfoService.getUserInfo(function(userInfo){
			$scope.user = {
				userType: (userInfo.userType == "1") ? "2" : "3"
			};
		});
	}
	
	//保存用户信息
	$scope.saveUser = function(){
		$scope.formresult = null;
		var valid = $("#usermgr-list-form").isHappy({//验证
			fields: {
				username: {required: true, username: true},//登录名
				userType: {required: true},//用户类型
				name: {required: true, maxlength: 20},//姓名
				sex: {required: true},//性别
				duty: {required: true,  maxlength: 40},//职务
				entryDate: {required: true, dateISO: true},//入职日期
				state: {required: true},//状态
				idCard: {idCard: true},//身份证号
				npPlace: {maxlength: 200},//籍贯
				phone: {required: true, maxlength: 100},//电话
				eduSchool: {maxlength: 200},//毕业院校
				eduDate: {dateISO: true},//毕业时间
				department: {maxlength: 100},//所属部门
				email: {email: true},//邮箱
				msn: {maxlength: 100},//qq或者微信
				education: {maxlength: 100},//学历
				professional: {maxlength: 100},//专业
				positiveDate: {dateISO: true},//转正日期
				leaveDate: {dateISO: true},//离职日期
				skills: {maxlength: 200}//擅长专业
			}
		});
		if(valid){
			$scope.isrequest = true;
			$http.post("user/editUser", $scope.user).success(function(data){
				$scope.formresult = data;
				if(data.success == "1"){//成功
					$location.path("usermgr/list/");
				}
			});
		}
	};
	
});
//用户管理-列表
app.controller('UserMgrListCtrl',function($scope, $routeParams,
		$http, ngTableParams, $rootScope, BaseInfoService) {
	
	$rootScope.menu = "user";
	
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.search = {};
	$scope.queryParams = {rows: initrows};
	$scope.initParams = function(pageNotChange){
		var iPage = initpage;
		if(pageNotChange){
			iPage = $scope.queryParams.page;
		}
		var temp = {
				page: iPage,
				rows: $scope.queryParams.rows,
				isreload: true
			};
		angular.extend(temp, $scope.search);
		angular.extend($scope.queryParams, temp);
	};
	$scope.initParams();
	/*重新加载*/
	$scope.reload = function(pageNotChange){
		$scope.initParams(pageNotChange);
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			$http.post("user/getUserList", $scope.queryParams).success(function(data){
				var total = data.total;
				$scope.queryParams.total = total;
				params.total(total);
				params.page($scope.queryParams.page);
				$defer.resolve(data.rows);
			});
		}
	});

	//监控查询条件
	$scope.$watch('search', function(newValue, oldValue){
		if(newValue == oldValue) return;
		$scope.reload();
	}, true);
	
	
	//删除用户
	$scope.delUser = function(item){
		$http.post("user/delUser", {id: item.id}).success(function(data){
			$scope.reload();
		});
	};
	//锁定用户
	$scope.disabledUser = function(item){
		$http.post("user/disabledUser", {id: item.id}).success(function(data){
			$scope.reload();
		});
	};
	//解锁用户
	$scope.enableUser = function(item){
		$http.post("user/enableUser", {id: item.id}).success(function(data){
			$scope.reload();
		});
	};
	//重置密码
	$scope.resetPwd = function(item){
		if(confirm("确认重置用户密码?") === false) return;
		$http.post("user/pwdReset", {id: item.id}).success(function(data){
			$scope.reload();
			$scope.formresult = data;
		});
	};
	
});
//用户管理-用户信息
app.controller('UserMgrViewUserCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				$rootScope) {
	$rootScope.menu = "user";
	
});
//用户-修改密码
app.controller('UserResetPwdCtrl',function($scope, $http,
		BaseInfoService, $rootScope) {
	
	$rootScope.menu = "resetpwd";
	
	//重置密码
	$scope.resetPwd = function(){
		$scope.formresult = null;
		var valid = $("#user-pwdreset-form").isHappy({//验证
			fields: {
				oldPwd: {required: true, pwd: true},
				newPwd: {required: true, pwd: true},
				newPwdRepeat: {required: true, pwd: true}
			}
		});
		if(valid){
			$scope.isrequest = true;
			$http.post("user/pwdChange", $scope.pwd).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
			});
		}
	};
	
});
//用户-更新信息
app.controller('UserUploadIconCtrl',function($scope, $http,
		BaseInfoService, $rootScope) {
	
	$rootScope.menu = "uploadicon";
	
	//加载用户基本信息
	$http.post("user/getLoginUserInfo", {}).success(function(data){
		$scope.headImage = data.icon;
	});
	
	//头像部分处理
	// Create variables (in this scope) to hold the API and image size
	  var jcrop_api,
	  boundx,
	  boundy,
	  
	  // Grab some information about the preview pane
	  $preview = $('#preview-pane'),
	  $pcnt = $('#preview-pane .preview-container'),
	  $pimg = $('#preview-pane .preview-container img');
	  
	  function updatePreview(c){
	    if (parseInt(c.w) > 0) {
	      var rx = 100 / c.w;
	      var ry = 100 / c.h;
	      
	      $scope.icon = c;
	      
	      $pimg.css({
	        width: Math.round(rx * boundx) + 'px',
	        height: Math.round(ry * boundy) + 'px',
	        marginLeft: '-' + Math.round(rx * c.x) + 'px',
	        marginTop: '-' + Math.round(ry * c.y) + 'px'
	      });
	    }
	  };


	//地址变化
	$scope.$watch('upicondata.data.id', function(newValue, oldValue){
		if(jcrop_api) jcrop_api.destroy();
		setTimeout(function(){
			$scope.icon = {};
			$('#icon_head').Jcrop({
				onChange: updatePreview,
			    onSelect: updatePreview,
			    width: 200,
			    height: 200,
			    boxWidth: 700,
			    aspectRatio: 1
			}, function(){
				jcrop_api = this;
				var bounds = this.getBounds();
			    boundx = bounds[0];
			    boundy = bounds[1];
			});
		}, 200);
	}, true);
	
	//上传头像
	$scope.saveIcon = function(){
		
		if($scope.icon.x == undefined){//为选择
			$scope.formresult = {success: 0, message: "请截取图片"};
			return;
		}
		
		$scope.isrequest = true;
		$http.post("user/uploadicon", {
			iconId: $scope.upicondata.data.id,
			x: parseInt($scope.icon.x),
			y: parseInt($scope.icon.y),
			w: parseInt($scope.icon.w),
			h: parseInt($scope.icon.h)
			}).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1"){//成功
					var item = data.data;
					$scope.headImage = item + "?_=" + Math.random();
					$scope.upicondata.data.id = null;
				}
			});
	};
	
});