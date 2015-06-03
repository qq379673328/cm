"use strict";
var app = angular
		.module(
				'app',
				[ 'ngRoute', 'datepicker', 'ngTable', 'core'],
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

app.controller('MainCtrl', function($scope, $routeParams, BaseInfoService) {
	BaseInfoService.getUserInfo(function(userInfo){
		$scope.user = userInfo;
	});
});


var fdRouterViewsBasepath = "static/app/modules/";

//路由
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/', {//首页
		templateUrl : fdRouterViewsBasepath + 'formdesign/views/list.html',
		controller : 'FormDesignListCtrl'
	}).when('/formdesign', {//表单管理-列表页面
		templateUrl : fdRouterViewsBasepath + 'formdesign/views/list.html',
		controller : 'FormDesignListCtrl'
	}).when('/formdesignedit/:id', {//表单设计-编辑页面
		templateUrl : fdRouterViewsBasepath + 'formdesign/views/design.html',
		controller : 'FormDesignCtrl'
	}).when('/formdesignedit', {//表单设计-添加页面
		templateUrl : fdRouterViewsBasepath + 'formdesign/views/design.html',
		controller : 'FormDesignCtrl'
	}).when('/formdatalist/:formid', {//表单数据管理-列表页面
		templateUrl : fdRouterViewsBasepath + 'formdesign/views/formdatalist.html',
		controller : 'FormDataListCtrl'
	}).when('/usermgr/list', {//用户管理-列表页面
		templateUrl : fdRouterViewsBasepath + 'usermgr/views/usermgr_list.html',
		controller : 'UserMgrListCtrl'
	}).when('/usermgr/editUser/:id', {//用户管理-编辑用户
		templateUrl : fdRouterViewsBasepath + 'usermgr/views/usermgr_edituser.html',
		controller : 'UserMgrEditUserCtrl'
	}).when('/usermgr/addUser', {//用户管理-新增用户
		templateUrl : fdRouterViewsBasepath + 'usermgr/views/usermgr_edituser.html',
		controller : 'UserMgrEditUserCtrl'
	}).when('/formreport/list', {//表单上报-列表页面
		templateUrl : fdRouterViewsBasepath + 'formreport/views/formreport_list.html',
		controller : 'FormReportListCtrl'
	}).otherwise({
		redirectTo : '/'
	});
} ]);

//html过滤
app.filter('trustHtml', function ($sce) {
    return function (input) {
        return $sce.trustAsHtml(input);
    };
});

angular.module('core', []).directive('formresult',[function(){
    return {
        restrict: 'A',
        templateUrl:'static/app/components/directives/core/formresult.html'
    };
}]);
angular.module('core', []).directive('permission', ["BaseInfoService", function(BaseInfoService){
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
angular.module('page', []).directive('fpage',[function(){
    return {
        restrict: 'EA',
        templateUrl: "static/app/components/directives/fdpage/page.html",
        replace: true,
        scope: {
            conf: '='
        },
        link: function(scope, element, attrs){
            // 变更当前页
            scope.changeCurrentPage = function(item){
                if(item == '...'){
                    return;
                }else{
                    scope.conf.currentPage = item;
                }
            };
            // 定义分页的长度必须为奇数 (default:9)
            scope.conf.pagesLength = parseInt(scope.conf.pagesLength) ? parseInt(scope.conf.pagesLength) : 9 ;
            if(scope.conf.pagesLength % 2 === 0){
                // 如果不是奇数的时候处理一下
                scope.conf.pagesLength = scope.conf.pagesLength -1;
            }
            // conf.erPageOptions
            if(!scope.conf.perPageOptions){
                scope.conf.perPageOptions = [10, 15, 20, 30, 50];
            }
            // pageList数组
            function getPagination(){
                // conf.currentPage
                scope.conf.currentPage = parseInt(scope.conf.currentPage) ? parseInt(scope.conf.currentPage) : 1;
                // conf.totalItems
                if(!scope.conf.totalItems){
                	scope.conf.totalItems = 0;
                }
                scope.conf.totalItems = parseInt(scope.conf.totalItems);
                // conf.itemsPerPage (default:15)
                // 先判断一下本地存储中有没有这个值
                if(scope.conf.rememberPerPage){
                    if(!parseInt(localStorage[scope.conf.rememberPerPage])){
                        localStorage[scope.conf.rememberPerPage] = parseInt(scope.conf.itemsPerPage) ? parseInt(scope.conf.itemsPerPage) : 15;
                    }
                    scope.conf.itemsPerPage = parseInt(localStorage[scope.conf.rememberPerPage]);
                }else{
                    scope.conf.itemsPerPage = parseInt(scope.conf.itemsPerPage) ? parseInt(scope.conf.itemsPerPage) : 15;
                }
                // numberOfPages
                scope.conf.numberOfPages = Math.ceil(scope.conf.totalItems/scope.conf.itemsPerPage);
                // judge currentPage > scope.numberOfPages
                if(scope.conf.currentPage < 1){
                    scope.conf.currentPage = 1;
                }
                if(scope.conf.currentPage > scope.conf.numberOfPages){
                    scope.conf.currentPage = scope.conf.numberOfPages;
                }
                // jumpPageNum
                scope.jumpPageNum = scope.conf.currentPage;
                // 如果itemsPerPage在不在perPageOptions数组中，就把itemsPerPage加入这个数组中
                var perPageOptionsLength = scope.conf.perPageOptions.length;
                // 定义状态
                var perPageOptionsStatus;
                for(var i = 0; i < perPageOptionsLength; i++){
                    if(scope.conf.perPageOptions[i] == scope.conf.itemsPerPage){
                        perPageOptionsStatus = true;
                    }
                }
                // 如果itemsPerPage在不在perPageOptions数组中，就把itemsPerPage加入这个数组中
                if(!perPageOptionsStatus){
                    scope.conf.perPageOptions.push(scope.conf.itemsPerPage);
                }
                // 对选项进行sort
                scope.conf.perPageOptions.sort(function(a, b){return a-b});
                scope.pageList = [];
                if(scope.conf.numberOfPages <= scope.conf.pagesLength){
                    // 判断总页数如果小于等于分页的长度，若小于则直接显示
                    for(i =1; i <= scope.conf.numberOfPages; i++){
                        scope.pageList.push(i);
                    }
                }else{
                    // 总页数大于分页长度（此时分为三种情况：1.左边没有...2.右边没有...3.左右都有...）
                    // 计算中心偏移量
                    var offset = (scope.conf.pagesLength - 1)/2;
                    if(scope.conf.currentPage <= offset){
                        // 左边没有...
                        for(i =1; i <= offset +1; i++){
                            scope.pageList.push(i);
                        }
                        scope.pageList.push('...');
                        scope.pageList.push(scope.conf.numberOfPages);
                    }else if(scope.conf.currentPage > scope.conf.numberOfPages - offset){
                        scope.pageList.push(1);
                        scope.pageList.push('...');
                        for(i = offset + 1; i >= 1; i--){
                            scope.pageList.push(scope.conf.numberOfPages - i);
                        }
                        scope.pageList.push(scope.conf.numberOfPages);
                    }else{
                        // 最后一种情况，两边都有...
                        scope.pageList.push(1);
                        scope.pageList.push('...');

                        for(i = Math.ceil(offset/2) ; i >= 1; i--){
                            scope.pageList.push(scope.conf.currentPage - i);
                        }
                        scope.pageList.push(scope.conf.currentPage);
                        for(i = 1; i <= offset/2; i++){
                            scope.pageList.push(scope.conf.currentPage + i);
                        }
                        scope.pageList.push('...');
                        scope.pageList.push(scope.conf.numberOfPages);
                    }
                }
                if(scope.conf.onChange){
                    scope.conf.onChange();
                }
                scope.$parent.conf = scope.conf;
            }
            // prevPage
            scope.prevPage = function(){
                if(scope.conf.currentPage > 1){
                    scope.conf.currentPage -= 1;
                }
            };
            // nextPage
            scope.nextPage = function(){
                if(scope.conf.currentPage < scope.conf.numberOfPages){
                    scope.conf.currentPage += 1;
                }
            };
            // 跳转页
            scope.jumpToPage = function(){
                scope.jumpPageNum = scope.jumpPageNum.replace(/[^0-9]/g,'');
                if(scope.jumpPageNum !== ''){
                    scope.conf.currentPage = scope.jumpPageNum;
                }
            };
            // 修改每页显示的条数
            scope.changeItemsPerPage = function(){
                // 清除本地存储的值方便重新设置
                if(scope.conf.rememberPerPage){
                    localStorage.removeItem(scope.conf.rememberPerPage);
                }
            };
            
            scope.$watch(function(){
                var newValue = scope.conf.currentPage + ' ' + scope.conf.totalItems + ' ';
                // 如果直接watch perPage变化的时候，因为记住功能的原因，所以一开始可能调用两次。
                //所以用了如下方式处理
                if(scope.conf.rememberPerPage){
                    // 由于记住的时候需要特别处理一下，不然可能造成反复请求
                    // 之所以不监控localStorage[scope.conf.rememberPerPage]是因为在删除的时候会undefind
                    // 然后又一次请求
                    if(localStorage[scope.conf.rememberPerPage]){
                        newValue += localStorage[scope.conf.rememberPerPage];
                    }else{
                        newValue += scope.conf.itemsPerPage;
                    }
                }else{
                    newValue += scope.conf.itemsPerPage;
                }
                return newValue;

            }, getPagination);
            
        }
    };
}]);
angular.module('core', []).factory('BaseInfoService', [ '$http', function($http) {
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
		}
	};
}]);
//表单上报-列表
app.controller('FormReportListCtrl',function($scope, $routeParams, $http, ngTableParams) {
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.initParams = function(){
		$scope.queryParams = {
				page: initpage,
				rows: initrows,
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
			
			$http.post("formdesign/getMyforms", $scope.queryParams).success(function(data){
				var total = data.pagingResult.total;
				$scope.queryParams.total = total;
				params.total(total);
				$defer.resolve(data.pagingResult.rows);
			});
		}
	});
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