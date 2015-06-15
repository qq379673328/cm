//用户管理-列表
app.controller('UserMgrListCtrl',function($scope, $routeParams,
		$http, ngTableParams, $rootScope, BaseInfoService) {
	
	$rootScope.nav = BaseInfoService.geNav(
			[["用户管理"]]
	);
	
	//分页查询
	var initpage = 1,
		initrows = 10;
	
	$scope.queryParams = {
		page: initpage,
		rows: initrows,
		isreload: true
	};
	
	/*重新加载*/
	$scope.reload = function(){
		$scope.queryParams = {
			page: $scope.queryParams.page,
			rows: $scope.queryParams.rows,
			username: $scope.searchUserName,
			loginname: $scope.searchLoginName,
			usertype: $scope.searchUserType,
			isDisabled: $scope.searchIsDisabled,
			isreload: true
		};
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			
			$http.post("usermgr/getUserList", $scope.queryParams).success(function(data){
				var total = data.total;
				$scope.queryParams.total = total;
				params.total(total);
				$defer.resolve(data.rows);
			});
		}
	});
	
	//删除用户
	$scope.delUser = function(item){
		if(confirm("确认删除?") === false) return;
		$http.post("usermgr/delUser", {id: item.id}).success(function(data){
			$scope.reload();
		});
	};
	//锁定用户
	$scope.disabledUser = function(item){
		if(confirm("确认锁定?") === false) return;
		$http.post("usermgr/disabledUser", {id: item.id}).success(function(data){
			$scope.reload();
		});
	};
	//解锁用户
	$scope.enableUser = function(item){
		$http.post("usermgr/enableUser", {id: item.id}).success(function(data){
			$scope.reload();
		});
	};
	//重置密码
	$scope.resetPwd = function(item){
		if(confirm("确认重置用户密码?") === false) return;
		$http.post("usermgr/pwdReset", {id: item.id}).success(function(data){
			$scope.reload();
			$scope.formresult = data;
		});
	};
	
});