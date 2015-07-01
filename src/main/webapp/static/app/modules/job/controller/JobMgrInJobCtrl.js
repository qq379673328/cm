//职位管理-录入职位
app.controller('JobMgrInJobCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				$rootScope) {
	$rootScope.menu = "job";
	
	var jobId = $routeParams.id;
	var customId = $routeParams.customId;
	$scope.isReady = false;
	//请求职位信息
	if(jobId){
		$http.post("job/getJobViewById", {id: jobId}).success(function(data){
			$scope.job = data.job;
			$scope.custom = data.custom;
			$scope.isReady = true;
		});
	}else if(customId){
		$http.post("custom/getCustomById", {id: customId}).success(function(data){
			$scope.custom = data.custom;
			$scope.isReady = true;
		});
		$scope.job = {
				state: "运作",
				sexLimit: "不限"
		};
	}else{
		$scope.isReady = true;
		$scope.job = {
				state: "运作",
				sexLimit: "不限"
		};
	}
	
	//保存职位信息
	$scope.save = function(){
		validFormAndSubmit(function(data){
			if(data.success == "1"){//成功
				$location.path("jobmgr/list");
			}
		});
	};
	
	//验证表单并提交
	function validFormAndSubmit(cb){
		if(validForm()){
			$scope.isrequest = true;
			$scope.job.customId = $scope.custom.id;
			$http.post("job/edit", $scope.job).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(cb) cb(data);
			});
		}

	};
	
	//验证表单
	function validForm(){
		return $("#jobeditform").isHappy({
			fields: {
				customName: {required: true, maxlength: 500},
				//职位名称
				name: {required: true, maxlength: 100},
				//薪资待遇-最小
				payMin: {number: true},
				//薪资待遇-最大
				payMax: {number: true},
				//职位状态
				state: {required: true},
				//执行团队
				team: {required: true},
				//工作地点
				workplace: {required: true, maxlength: 200},
				//职位类别
				jobType: {required: true, maxlength: 100},
				//所属行业
				industry: {required: true, maxlength: 200},
				//工作年限
				workYear: {required: true, digits: true},
				//需求人数
				requirePeople: {required: true, digits: true},
				//所属部门
				department: {required: true, maxlength: 100},
				//汇报对象
				reportObj: {required: true, maxlength: 100},
				//年龄要求-最小
				ageMin: {required: true, digits: true},
				//年龄要求-最大
				ageMax: {required: true, digits: true},
				//性别要求
				//学历要求
				//语言要求
				languageLimit: {maxlength: 100},
				//职位描述
				jobDesc: {required: true, maxlength: 500},
				//公司介绍
				companyDesc: {required: true, maxlength: 500}
			}
		});
	}
	
});