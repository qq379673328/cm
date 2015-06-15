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
			$scope.custom = data.custom;
			$scope.communs = data.communs;
			$scope.attachs = data.attachs;
			$scope.isReady = true;
			
			//请求团队信息
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
	
	//删除沟通记录
	$scope.delCommun = function(item){
		$http.post("custom/delCommun", {id: item.id}).success(function(data){
			$scope.formresult = data;
			//重新加载沟通记录
			realodCommun();
		});
	};
	function realodCommun(){
		//重新加载沟通记录
		$http.post("custom/getCommuns", {customId: customId}).success(function(data){
			$scope.communs = data;
		});
	}
	//保存客户信息
	$scope.save = function(){
		validFormAndSubmit(function(data){
			if(data.success == "1"){//成功
				$location.path("custommgr/list");
			}
		});
	};
	//保存客户信息并且添加新合同
	$scope.saveAndAddContract = function(){
		validFormAndSubmit(function(data){
			if(data.success == "1"){//成功
				var customId = data.data;
				$location.path("contractmgr/incontract/" + customId);
			}
		});
	};
	
	//验证表单并提交
	function validFormAndSubmit(cb){
		if(validForm()){
			$scope.isrequest = true;
			$http.post("custom/edit", $scope.custom).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(cb) cb(data);
			});
		}

	};
	
	//验证表单
	function validForm(){
		return $("#customeditform").isHappy({
			fields: {
				//客户名
				customName: {required: true, maxlength: 100},
				//所属行业
				industry: {required: true,maxlength: 200}
				
			}
		});
	}
	
});