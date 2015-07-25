//编码管理
app.controller('CodeMgrCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope, BaseInfoService) {
	//菜单
	$rootScope.menu = "codemgr";
	
	$scope.search = {queryParams: {}};
	
	//加载编码类型
	$http.post("code/getCodeTypes", {}).success(function(data){
		$scope.items = data;
		$scope.search.queryParams.codetype = "customIndustry";
	});
	
	//加载数据
	$scope.reload = function(){
		$http.post("code/getCodesByType", $scope.search.queryParams).success(function(data){
			$scope.codes = data;
		});
	}

	//监控查询条件
	$scope.$watch('search', function(newValue, oldValue){
		if(newValue == oldValue) return;
		$scope.reload();
	}, true);
	
	//删除
	$scope.del = function(item){
		$http.post("code/del", {codeId: item.id}).success(function(data){
			$scope.formresult = data;
			$scope.reload();
		});
	};
	
	//新增
	$scope.addCode = function(){
		//验证
		if($scope.addCodeValue == undefined){//值
			$scope.formresult = {
					success: false,
					message: "值不能为空"
			};
			return;
		}
		if($scope.addCodeValue.length > 100){//值
			$scope.formresult = {
					success: 0,
					message: "长度不能大于100"
			};
			return;
		}
		if($scope.addCodeRank != undefined && isNaN($scope.addCodeRank)){//顺序
			$scope.formresult = {
					success: 0,
					message: "顺序必须为数字"
			};
			return;
		}
		
		$http.post("code/edit", {
			codetype: $scope.search.queryParams.codetype,
			codevalue: $scope.addCodeValue,
			rank: $scope.addCodeRank
		}).success(function(data){
			$scope.formresult = data;
			$scope.reload();
		});
	};
	
});