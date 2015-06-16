//简历管理-录入简历
app.controller('ResumeMgrInResumeCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				CustomService, $rootScope) {
	$rootScope.menu = "resume";
	
	var resumeId = $routeParams.id;
	$scope.isReady = false;
	
	if(resumeId){//编辑
		//请求简历信息
		$http.post("custom/getCustomById", {id: customId}).success(function(data){
			$scope.resume = data.resume;
			$scope.resumeDatas = data.resumeDatas;
			$scope.resumeEdus = data.resumeEdus;
			$scope.resumeJobs = data.resumeJobs;
			$scope.resumeLanguages = data.resumeLanguages;
			$scope.resumeWorkhistorys = data.resumeWorkhistorys;
			
			$scope.isReady = true;
			
		});
	}else{//新增
		$scope.isReady = true;
	}
	
	//保存简历信息
	$scope.save = function(){
		validFormAndSubmit(function(data){
			if(data.success == "1"){//成功
				$location.path("resumemgr/list");
			}
		});
	};
	
	//验证表单并提交
	function validFormAndSubmit(cb){
		if(validForm()){
			$scope.isrequest = true;
			$http.post("resume/edit", $scope.custom).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(cb) cb(data);
			});
		}

	};
	
	//验证表单
	function validForm(){
		return $("#resumeeditform").isHappy({
			fields: {
				
			}
		});
	}
	
});