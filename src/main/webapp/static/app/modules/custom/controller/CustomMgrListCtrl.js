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