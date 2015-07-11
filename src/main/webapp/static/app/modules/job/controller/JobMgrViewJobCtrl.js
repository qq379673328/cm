//职位管理-职位信息
app.controller('JobMgrViewJobCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "job";
	
	var jobId = $routeParams.id;
	$scope.isReady = false;
	
	//请求职位信息
	var loadViewInfo = function(){
		$http.post("job/getJobViewById", {id: jobId}).success(function(data){
			$scope.job = data.job;
			$scope.custom = data.custom;
			$scope.inteams = data.inteamresumes;
			$scope.pubresumes = data.pubresumes;
			$scope.jobcomms = data.jobcomms;
			$scope.isReady = true;
		});
	};
	loadViewInfo();
	
	//向企业投递简历
	$scope.pubResume = function(id){
		$http.post("job/pubResume", {rjId: id}).success(function(data){
			$scope.formresult = data;
			if(data.success == 1){
				loadViewInfo();
			}
		});
	};
	//取消投递简历
	$scope.cancleResume = function(id){
		$http.post("job/cancleResume", {rjId: id}).success(function(data){
			$scope.formresult = data;
			if(data.success == 1){
				loadViewInfo();
			}
		});
	};
	
	//审核向企业投递的简历
	$scope.verify = function(id, status){
		$http.post("job/verifyResume", {rjId: id, status: status})
			.success(function(data){
				$scope.formresult = data;
				if(data.success == 1){
					loadViewInfo();
				}
		});
	};
	
	//添加交流信息
	$scope.addComm = function(){
		$http.post("job/editJobComm", {jobId: jobId, content: $scope.addcomm})
			.success(function(data){
				$scope.formresult = data;
				if(data.success == 1){
					loadViewInfo();
				}
		});
	};
	//删除交流信息
	$scope.delComm = function(id){
		$http.post("job/delJobComm", {id: id})
		.success(function(data){
			$scope.formresult = data;
			if(data.success == 1){
				loadViewInfo();
			}
		});
	};
	
});