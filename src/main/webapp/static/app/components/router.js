var fdRouterViewsBasepath = "static/app/modules/";

//路由
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/', {//首页
		templateUrl : fdRouterViewsBasepath + 'index/views/index.html',
		controller : 'IndexCtrl'
	}).when('/custommgr', {//客户管理
		templateUrl : fdRouterViewsBasepath + 'custom/views/custommgr.html',
		controller : 'CustomMgrCtrl'
	}).otherwise({
		redirectTo : '/'
	});
} ]);