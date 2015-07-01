//合同管理-录入合同
app.controller('ContractMgrInContractCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				$rootScope) {
	$rootScope.menu = "contract";
	
	var contractId = $routeParams.id;
	var customId = $routeParams.customId;
	$scope.isReady = false;
	//请求客户信息-直接跳转过来的
	if(customId){
		$http.post("custom/getCustomById", {id: customId}).success(function(data){
			$scope.custom = data.custom;
			$scope.isReady = true;
		});
	}
	if(contractId){//编辑合同
		//请求合同信息
		$http.post("contract/getContractViewById", {id: contractId}).success(function(data){
			$scope.custom = data.custom;
			$scope.contract = data.contract;
			$scope.attachs = data.attachs;
			$scope.isReady = true;
		});
	}
	if(!contractId && !customId){
		$scope.isReady = true;
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
			$scope.contract.customId = $scope.custom.id;
			$http.post("contract/edit", $scope.contract).success(function(data){
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
				//客户名
				customName:{required: true},
				//合同编号
				no:{required: true, maxlength: 100},
				//合同状态
				state:{required: true, maxlength: 50},
				//签约日期
				inDate:{required: true, date: true},
				//签约比例
				inPercentage:{required: true, maxlength: 100},
				//支付方式
				payway:{required: true, maxlength: 100},
				//首付款
				firstPay:{required: true, number: true},
				//使用期限
				useLimit:{required: true, maxlength: 50},
				//其他要求
				otherRequire:{maxlength: 500}
			}
		});
	}
	
});