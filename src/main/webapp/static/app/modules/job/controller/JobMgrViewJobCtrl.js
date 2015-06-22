//职位管理-职位信息
app.controller('JobMgrViewJobCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "job";
	
	var jobId = $routeParams.id;
	$scope.isReady = false;
	
	//请求职位信息
	$http.post("job/getJobViewById", {id: jobId}).success(function(data){
		$scope.job = data.job;
		$scope.custom = data.custom;
		$scope.inteamresumes = data.inteamresumes;
		$scope.pubresumes = data.pubresumes;
		$scope.isReady = true;
	});
	
	//向企业推荐人选
	$scope.updateInteamsResumes = function(ids){
		
	};
	
	//审核向企业投递的简历
	$scope.updatePubResumes = function(ids){
		
	};
	
	//添加交流信息
	$scope.addComm = function(){
		
	};
	
});