//合同管理-录入合同
app.controller('ContractMgrInContractCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				CustomService ,$rootScope) {
	$rootScope.menu = "contract";
	
	var contractId = $routeParams.id;
	var customId = $routeParams.customId;
	$scope.isReady = false;
	//请求客户信息-直接跳转过来的
	if(customId){
		$http.post("custom/getCustomById", {id: customId}).success(function(data){
			$scope.custom = data;
			$scope.isReady = true;
		});
	}
	if(contractId){//编辑合同
		//请求合同信息
		$http.post("contract/getContractById", {id: customId}).success(function(data){
			$scope.custom = data.custom;
			$scope.contract = data.contract;
			$scope.isReady = true;
		});
	}
	
	//保存合同信息
	$scope.save = function(){
		validFormAndSubmit(function(data){
			if(data.success == "1"){//成功
				$location.path("contractmgr/list");
			}
		});
	};
	
	//验证表单并提交
	function validFormAndSubmit(cb){
		if(validForm()){
			$scope.isrequest = true;
			$http.post("contract/edit", $scope.custom).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(cb) cb(data);
			});
		}

	};
	
	//验证表单
	function validForm(){
		return $("#contracteditform").isHappy({
			fields: {
				
			}
		});
	}
	
});