//客户管理-录入客户
app.controller('CustomMgrInCustomCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope, $location) {
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
			
		});
	}else{//新增
		$scope.isReady = true;
		$scope.custom = {state: "潜在客户"};
		
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
				$location.path("custommgr/viewcustom/" + customId);
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
				industry: {required: true,maxlength: 200},
				//执行团队
				team: {required: true},
				//联系人名
				contactName: {required: true,maxlength: 100},
				//担任职务
				contactDuty: {required: true,maxlength: 100},
				//座机号码
				contactLandline: {maxlength: 100},
				//手机号码
				contactCellphone: {required: true,maxlength: 100},
				//fax传真
				contactFax: {maxlength: 100},
				//电子邮件
				contactEmail: {required: true,maxlength: 100},
				//qq
				contactQq: {maxlength: 100},
				//微信
				contactWeixin: {maxlength: 100},
				//公司地址
				contactAddress: {maxlength: 200},
				//网址
				contactWebsite: {maxlength: 100},
				//公司介绍
				companyProfile: {maxlength: 100},
				//沟通记录
				commun: {maxlength: 500}
			}
		});
	}
	
});