//用户管理-编辑用户
app.controller('UserMgrEditUserCtrl',function($scope, $routeParams, $http,
		BaseInfoService, $rootScope) {
	//新增or编辑
	if($routeParams.id){
		$rootScope.nav = BaseInfoService.geNav(
				[["用户管理", "/usermgr/list"], ["用户编辑"]]
		);
		$scope.id = $routeParams.id;
		//请求用户信息
		$http.post("usermgr/getUserById", {id: $scope.id}).success(function(user){
			user.createTime = null;
			user.updateTime = null;
			$scope.user = user;
		});
	}else{//新增
		$rootScope.nav = BaseInfoService.geNav(
				[["用户管理", "/usermgr/list"], ["用户新增"]]
		);
		BaseInfoService.getUserInfo(function(userInfo){
			$scope.user = {
				userType: (userInfo.userType == "1") ? "2" : "3"
			};
		});
	}
	
	//保存用户信息
	$scope.saveUser = function(){
		$scope.formresult = null;
		var valid = $("#usermgr-list-form").isHappy({//验证
			fields: {
				loginName: {required: true, 
					maxlength: 20,
					minlength: 6,
					username: true},//登录名
				userName: {required: true, maxlength: 20},//用户名
				birthday: {dateISO: true},//出生日期
				orgCode: {required: true, maxlength: 40},//所属机构
				department: {required: true, maxlength: 100},//所属科室
				comment: {maxlength: 100}//备注
			}
		});
		if(valid){
			$scope.isrequest = true;
			$http.post("usermgr/editUser", $scope.user).success(function(data){
				$scope.formresult = data;
				if(data.data){
					$scope.user.id = data.data.id;
				}
				$scope.isrequest = false;
			});
		}
	};
	
});