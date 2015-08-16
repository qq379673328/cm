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