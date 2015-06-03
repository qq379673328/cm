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