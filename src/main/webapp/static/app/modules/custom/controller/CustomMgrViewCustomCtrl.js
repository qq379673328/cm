//客户管理-客户信息
app.controller('CustomMgrViewCustomCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				CustomService, $rootScope) {
	$rootScope.menu = "custom";
	
	var customId = $routeParams.id;
	$scope.isReady = false;
	
	//请求客户信息
	$http.post("custom/getCustomViewById", {id: customId}).success(function(data){
		$scope.custom = data.custom;
		$scope.comms = data.comms;
		$scope.attas = data.attas;
		$scope.jobs = data.jobs;
		$scope.teams = data.teams;
		if(data.contracts && data.contracts.length > 0){
			$scope.contract = data.contracts[0];
		}
		$scope.contractAttas = data.contractAttas;
		$scope.isReady = true;
	});
	
	//添加沟通记录
	$scope.addComm = function(){
		
	};
	
});