//合同管理-合同信息
app.controller('ContractMgrViewContractCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				CustomService ,$rootScope) {
	$rootScope.menu = "contract";
	
	var contractId = $routeParams.id;
	$scope.isReady = false;
	
	//请求合同信息
	$http.post("contract/getContractViewById", {id: contractId}).success(function(data){
		$scope.custom = data.custom;
		$scope.contract = data.contract;
		$scope.jobs = data.jobs;
		$scope.isReady = true;
	});
	
});