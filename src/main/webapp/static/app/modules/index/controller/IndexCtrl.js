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