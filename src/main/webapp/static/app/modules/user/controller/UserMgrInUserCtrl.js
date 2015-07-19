//用户管理-编辑用户
app.controller('UserMgrInUserCtrl',function($scope, $routeParams, $http,
		BaseInfoService, $rootScope, $location) {
	
	$rootScope.menu = "user";
	
	//新增or编辑
	if($routeParams.id){
		$scope.id = $routeParams.id;
		//请求用户信息
		$http.post("user/getUserById", {id: $scope.id}).success(function(user){
			user.createTime = null;
			user.updateTime = null;
			$scope.user = user;
		});
	}else{//新增
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
				username: {required: true, username: true},//登录名
				userType: {required: true},//用户类型
				name: {required: true, maxlength: 20},//姓名
				sex: {required: true},//性别
				duty: {required: true,  maxlength: 40},//职务
				entryDate: {required: true, dateISO: true},//入职日期
				state: {required: true},//状态
				idCard: {idCard: true},//身份证号
				npPlace: {maxlength: 200},//籍贯
				phone: {required: true, maxlength: 100},//电话
				eduSchool: {maxlength: 200},//毕业院校
				eduDate: {dateISO: true},//毕业时间
				department: {maxlength: 100},//所属部门
				email: {email: true},//邮箱
				msn: {maxlength: 100},//qq或者微信
				education: {maxlength: 100},//学历
				professional: {maxlength: 100},//专业
				positiveDate: {dateISO: true},//转正日期
				leaveDate: {dateISO: true},//离职日期
				skills: {maxlength: 200}//擅长专业
			}
		});
		if(valid){
			$scope.isrequest = true;
			$http.post("user/editUser", $scope.user).success(function(data){
				$scope.formresult = data;
				if(data.success == "1"){//成功
					$location.path("usermgr/list/");
				}
			});
		}
	};
	
});