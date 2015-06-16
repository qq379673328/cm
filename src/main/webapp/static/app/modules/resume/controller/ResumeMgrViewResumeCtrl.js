//简历管理-简历信息
app.controller('ResumeMgrViewResumeCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				CustomService, $rootScope) {
	$rootScope.menu = "resume";
	
	var resumeId = $routeParams.id;
	$scope.isReady = false;
	
	//请求客户信息
	$http.post("resume/getResumeViewById", {id: resumeId}).success(function(data){
		$scope.resume = data.resume;
		$scope.resumeDatas = data.resumeDatas;
		$scope.resumeEdus = data.resumeEdus;
		$scope.resumeJobs = data.resumeJobs;
		$scope.resumeLanguages = data.resumeLanguages;
		$scope.resumeWorkhistorys = data.resumeWorkhistorys;
		$scope.resumeComms = data.resumeComms;
		
		$scope.isReady = true;
	});
	
	//添加沟通记录
	$scope.addComm = function(){
		
	};
	
});