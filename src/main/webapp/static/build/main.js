"use strict";
var app = angular
		.module(
				'app',
				[ 'ngRoute', 'ngTable'],
				function($httpProvider) {
					$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
					/**
					 * The workhorse; converts an object to
					 * x-www-form-urlencoded serialization.
					 * 
					 * @param {Object}
					 *            obj
					 * @return {String}
					 */
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
						return query.length ? query.substr(0, query.length - 1)
								: query;
					};
					// Override $http service's default transformRequest
					$httpProvider.defaults.transformRequest = [ function(data) {
						return angular.isObject(data)
								&& String(data) !== '[object File]' ? param(data)
								: data;
					} ];

				});

app.controller('MainCtrl', function($scope, $location) {
	$scope.to = function(url){
		$location.path(url);
	};
	
});

app.controller('IndexCtrl', function($scope, $routeParams, $rootScope) {
	$rootScope.menu = "#";
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
	.when('/jobmgr/injob', {//职位管理-录入职位
		templateUrl : fdRouterViewsBasepath + 'job/views/jobmgrinjob.html',
		controller : 'JobMgrInCustomCtrl'
	})
	.when('/jobmgr/editjob/:id', {//职位管理-编辑职位
		templateUrl : fdRouterViewsBasepath + 'job/views/jobmgrinjob.html',
		controller : 'JobMgrInJobCtrl'
	})
	.when('/jobmgr/viewjob/:id', {//职位管理-职位信息
		templateUrl : fdRouterViewsBasepath + 'custom/views/jobmgrviewjob.html',
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
	.when('/resumemgr/editcustom/:id', {//简历管理-编辑简历
		templateUrl : fdRouterViewsBasepath + 'resume/views/resumemgrinresume.html',
		controller : 'ResumeMgrInResumeCtrl'
	})
	.when('/resumemgr/viewresume/:id', {//简历管理-简历信息
		templateUrl : fdRouterViewsBasepath + 'custom/views/resumemgrviewresume.html',
		controller : 'ResumeMgrViewResumeCtrl'
	})
	
	.when('/contractmgr/list', {//合同管理-列表页
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrlist.html',
		controller : 'ContractMgrListCtrl'
	})
	.when('/contractmgr/incontract', {//合同管理-录入合同
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrincontract.html',
		controller : 'ContractMgrInContractCtrl'
	})
	.when('/contractmgr/incontract/:customid', {//合同管理-为指定客户录入合同
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrincontract.html',
		controller : 'ContractMgrInContractCtrl'
	})
	.when('/contractmgr/editcustom/:id', {//合同管理-编辑合同
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrincontract.html',
		controller : 'ContractMgrInContractCtrl'
	})
	.when('/contractmgr/viewcontract/:id', {//合同管理-合同信息
		templateUrl : fdRouterViewsBasepath + 'custom/views/contractmgrviewcontract.html',
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
		templateUrl : fdRouterViewsBasepath + 'custom/views/usermgrviewuser.html',
		controller : 'UserMgrViewUserCtrl'
	})
	
	.when('/usermgr/resetpwd', {//修改密码管理-列表页
		templateUrl : fdRouterViewsBasepath + 'user/views/userresetpwd.html',
		controller : 'UserResetPwdCtrl'
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

app.directive('coreFormresult',[function(){
    return {
        restrict: 'A',
        scope: {
        	formresult: "=result"
        },
        templateUrl:'static/app/components/directives/core/views/formresult.html'
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
        	te: "=bindteam"
        },
        templateUrl: 'static/app/components/directives/core/views/teamselect.html',
        link: function($scope, $element, attrs) {
        	$scope.selectteams = [];
        	$scope.teams = [];
        	$http.post("team/getSelectTeams", {}).success(function(data){
        		$scope.teams = data;
        	});
  
        	//添加
        	$scope.addItem = function(team, index){
        		$scope.teams.splice(index, 1);
        		$scope.selectteams.push(team);
        		resetTe();
        	};
        	//移除
        	$scope.removeItem = function(team, index){
        		$scope.selectteams.splice(index, 1);
        		$scope.teams.push(team);
        		resetTe();
        	};
        	function resetTe(){
        		var arr = [];
        		var teams = $scope.selectteams;
        		for(var i in teams){
        			arr.push(teams[i]["id"]);
        		}
        		$scope.te = arr.join(",");
        	}
        }
    };
}]);
angular.module('datepicker', []).directive('datepicker',[function(){
    return {
        restrict: 'A',
        require : 'ngModel',
        link: function(scope, element, attrs, ngModelCtrl){
        	$(function(){
                element.datepicker({
                    dateFormat:'yy-mm-dd',
                    onSelect:function (date) {
                        scope.$apply(function () {
                            ngModelCtrl.$setViewValue(date);
                        });
                    }
                });
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
						//$defer.resolve(data.rows);
						$scope[items] = data.rows;
					});
				}
			});
			return ret;
		}
		
	};
}]);
//合同管理-录入合同
app.controller('ContractMgrInContractCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				CustomService ,$rootScope) {
	$rootScope.menu = "contract";
	
});
//
app.controller('ContractMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				ContractService ,$rootScope) {
	$rootScope.menu = "contract";
	
});
//合同管理-合同信息
app.controller('ContractMgrViewContractCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				CustomService ,$rootScope) {
	$rootScope.menu = "contract";
	
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
				CustomService, $rootScope, $location) {
	$rootScope.menu = "custom";
	
	var customId = $routeParams.id;
	$scope.isReady = false;
	
	if(customId){//编辑
		//请求客户信息
		$http.post("custom/getCustomById", {id: customId}).success(function(data){
			$scope.custom = data;
			$scope.isReady = true;
		});
	}else{//新增
		$scope.isReady = true;
		$scope.custom = {state: "潜在客户"};
	}
	
	//保存客户信息
	$scope.save = function(){
		validFormAndSubmit();
	};
	//保存客户信息并且添加新合同
	$scope.saveAndAddContract = function(){
		validFormAndSubmit(function(data){
			if(data.success == "1"){//成功
				var customId = data.data;
				$location.path("#contractmgr/incontract/" + customId);
			}
		});
	};
	
	//验证表单并提交
	function validFormAndSubmit(cb){
		if($scope.form.$valid){
			$scope.isrequest = true;
			$http.post("custom/edit", $scope.custom).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(cb) cb(data);
			});
		}

	};
	
});
//客户管理-列表页
app.controller('CustomMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				CustomService, $rootScope, BaseInfoService) {
	$rootScope.menu = "custom";
	
	$scope.qp = {username: "1"};
	
	$scope.tableParams = BaseInfoService.pageSearch({
		$scope: $scope,
		params: "qp",
		items: "customs",
		url: "custom/list"
	});
	
});
//客户管理-客户信息
app.controller('CustomMgrViewCustomCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				CustomService, $rootScope) {
	$rootScope.menu = "custom";
	
});
app.factory('CustomService', [ '$http', function($http) {
	return {
		//
		test: function(params, cb){
			$http.post("formdesign/formdatadel", params).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);

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

//发票管理-录入发票
app.controller('InvoiceMgrInInvoiceCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				CustomService, $rootScope) {
	$rootScope.menu = "invoice";
	
});

//
app.controller('InvoiceMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				InvoiceService, $rootScope) {
	$rootScope.menu = "invoice";
	
});
//发票管理-发票信息
app.controller('InvoiceMgrViewInvoiceCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				CustomService, $rootScope) {
	$rootScope.menu = "invoice";
	
});
app.factory('InvoiceService', [ '$http', function($http) {
	return {
		//
		test: function(params, cb){
			$http.post("formdesign/formdatadel", params).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);
//职位管理-录入职位
app.controller('JobMgrInJobCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				CustomService, $rootScope) {
	$rootScope.menu = "job";
	
});

//
app.controller('JobMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				JobService, $rootScope) {
	$rootScope.menu = "job";
	
});
//职位管理-职位信息
app.controller('JobMgrViewJobCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				CustomService, $rootScope) {
	$rootScope.menu = "job";
	
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
				CustomService, $rootScope) {
	$rootScope.menu = "pub";
	
});

//
app.controller('PubMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				PubService, $rootScope) {
	$rootScope.menu = "pub";
	
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
				CustomService, $rootScope) {
	$rootScope.menu = "resume";
	
});

//
app.controller('ResumeMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				ResumeService, $rootScope) {
	$rootScope.menu = "resume";
	
});
//简历管理-简历信息
app.controller('ResumeMgrViewResumeCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				CustomService, $rootScope) {
	$rootScope.menu = "resume";
	
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

//
app.controller('TeamMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				TeamService, $rootScope) {
	$rootScope.menu = "team";
	
});
//团队管理-我的信息
app.controller('TeamMgrMyInfoCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				CustomService, $rootScope) {
	$rootScope.menu = "team";
	
});
app.factory('TeamService', [ '$http', function($http) {
	return {
		//
		test: function(params, cb){
			$http.post("formdesign/formdatadel", params).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);
//用户管理-录入用户
app.controller('UserMgrInUserCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				CustomService, $rootScope) {
	$rootScope.menu = "user";
	
});

//
app.controller('UserMgrListCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				UserService, $rootScope) {
	$rootScope.menu = "user";
	
});
//用户管理-用户信息
app.controller('UserMgrViewUserCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				CustomService, $rootScope) {
	$rootScope.menu = "user";
	
});

//
app.controller('UserResetPwdCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				UserService, $rootScope) {
	$rootScope.menu = "resetpwd";
	
});
app.factory('UserService', [ '$http', function($http) {
	return {
		//
		test: function(params, cb){
			$http.post("formdesign/formdatadel", params).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);
//用户管理-编辑用户
app.controller('UserMgrEditUserCtrl',function($scope, $routeParams, $http, BaseInfoService) {
	//新增or编辑
	if($routeParams.id){
		$scope.id = $routeParams.id;
		//请求用户信息
		$http.post("usermgr/getUserById", {id: $scope.id}).success(function(user){
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
				loginName: {required: true, maxlength: 20},//登录名
				userName: {required: true, maxlength: 20},//用户名
				birthday: {dateISO: true},//出生日期
				orgCode: {maxlength: 40},//所属机构
				department: {maxlength: 100},//所属科室
				comment: {maxlength: 100}//备注
			}
		});
		if(valid){
			$scope.isrequest = true;
			$http.post("usermgr/editUser", $scope.user).success(function(data){
				$scope.formresult = data;
				if(data.data){
					$scope.user.id = data.data.id;
				}
				$scope.isrequest = false;
			});
		}
	};
	
});
//用户管理-列表
app.controller('UserMgrListCtrl',function($scope, $routeParams, $http, ngTableParams) {
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
			username: $scope.searchUserName,
			loginname: $scope.searchLoginName,
			usertype: $scope.searchUserType,
			isDisabled: $scope.searchIsDisabled,
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
			
			$http.post("usermgr/getUserList", $scope.queryParams).success(function(data){
				var total = data.total;
				$scope.queryParams.total = total;
				params.total(total);
				$defer.resolve(data.rows);
			});
		}
	});
	
	//删除用户
	$scope.delUser = function(item){
		if(confirm("确认删除?") === false) return;
		$http.post("usermgr/delUser", {id: item.id}).success(function(data){
			$scope.reload();
		});
	};
	//锁定用户
	$scope.disabledUser = function(item){
		if(confirm("确认锁定?") === false) return;
		$http.post("usermgr/disabledUser", {id: item.id}).success(function(data){
			$scope.reload();
		});
	};
	//解锁用户
	$scope.enableUser = function(item){
		$http.post("usermgr/enableUser", {id: item.id}).success(function(data){
			$scope.reload();
		});
	};
	
});