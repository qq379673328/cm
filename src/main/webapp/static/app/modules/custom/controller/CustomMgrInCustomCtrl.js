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
		});
	}else{//新增
		$scope.isReady = true;
	}
	
	//保存客户信息
	$scope.save = function(){
		validFormAndSubmit();
	};
	//保存客户信息并且添加新合同
	$scope.saveAndAddContract = function(){
		validFormAndSubmit(function(data){
			if(data.success == "1"){//成功
				var customId = null;
				if($scope.custom.id){
					
				}
				$location.path("#contractmgr/incontract/" + $scope.custom.id);
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