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