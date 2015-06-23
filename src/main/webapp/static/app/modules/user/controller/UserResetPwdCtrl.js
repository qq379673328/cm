//用户-修改密码
app.controller('UserResetPwdCtrl',function($scope, $http,
		BaseInfoService, $rootScope) {
	
	$rootScope.menu = "resetpwd";
	
	//重置密码
	$scope.resetPwd = function(){
		$scope.formresult = null;
		var valid = $("#user-pwdreset-form").isHappy({//验证
			fields: {
				oldPwd: {required: true, pwd: true},
				newPwd: {required: true, pwd: true},
				newPwdRepeat: {required: true, pwd: true}
			}
		});
		if(valid){
			$scope.isrequest = true;
			$http.post("user/pwdChange", $scope.pwd).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
			});
		}
	};
	
});