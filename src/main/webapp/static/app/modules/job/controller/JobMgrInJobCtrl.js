//职位管理-录入职位
app.controller('JobMgrInJobCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				$rootScope) {
	$rootScope.menu = "job";
	
	var jobId = $routeParams.id;
	$scope.isReady = false;
	//请求职位信息
	if(jobId){
		$http.post("job/getJobViewById", {id: jobId}).success(function(data){
			$scope.job = data.job;
			$scope.custom = data.custom;
			$scope.isReady = true;
			
			//请求团队信息
			$http.post("team/getSelectTeams", {ids: $scope.job.team}).success(function(data){
				$scope.teamselect = data;
			});		
		});
	}else{
		$scope.isReady = true;
		
		$scope.job = {
				state: "运作",
				sexLimit: "不限"
		};
		
		//请求团队信息
		$http.post("team/getSelectTeams", {ids: ""}).success(function(data){
			$scope.teamselect = data;
		});
	}
	
	//保存职位信息
	$scope.save = function(){
		validFormAndSubmit(function(data){
			if(data.success == "1"){//成功
				$location.path("jobmgr/list");
			}
		});
	};
	
	//验证表单并提交
	function validFormAndSubmit(cb){
		if(validForm()){
			$scope.isrequest = true;
			$scope.job.customId = $scope.custom.id;
			$http.post("job/edit", $scope.job).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(cb) cb(data);
			});
		}

	};
	
	//验证表单
	function validForm(){
		return $("#jobeditform").isHappy({
			fields: {
				
			}
		});
	}
	
});