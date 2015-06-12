//客户管理-录入客户
app.controller('CustomMgrInCustomCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				CustomService, $rootScope, $location) {
	$rootScope.menu = "custom";
	
	var customId = $routeParams.id;
	$scope.isReady = false;
	
	if(customId){//编辑
		//请求客户信息
		$http.post("custom/getCustomById", {id: customId}).success(function(data){
			$scope.custom = data;
			$scope.isReady = true;
			
			//请求团队信息ss
			$http.post("team/getSelectTeams", {ids: $scope.custom.team}).success(function(data){
				$scope.teamselect = data;
			});
		});
	}else{//新增
		$scope.isReady = true;
		$scope.custom = {state: "潜在客户"};
		
		//请求团队信息
		$http.post("team/getSelectTeams", {}).success(function(data){
			$scope.teamselect = data;
		});
	}
	
	//保存客户信息
	$scope.save = function(){
		validFormAndSubmit();
	};
	//保存客户信息并且添加新合同
	$scope.saveAndAddContract = function(){
		validFormAndSubmit(function(data){
			if(data.success == "1"){//成功
				var customId = data.data;
				$location.path("#contractmgr/incontract/" + customId);
			}
		});
	};
	
	//验证表单并提交
	function validFormAndSubmit(cb){
		if($scope.form.$valid){
			$scope.isrequest = true;
			$http.post("custom/edit", $scope.custom).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(cb) cb(data);
			});
		}

	};
	
});