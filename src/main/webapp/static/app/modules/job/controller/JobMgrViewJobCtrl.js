//职位管理-职位信息
app.controller('JobMgrViewJobCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "job";
	
	var jobId = $routeParams.id;
	$scope.isReady = false;
	
	//请求职位信息
	var loadViewInfo = function(){
		$scope.isReady = false;
		$http.post("job/getJobViewById", {id: jobId}).success(function(data){
			$scope.job = data.job;
			$scope.custom = data.custom;
			$scope.inteams = data.inteamresumes;
			$scope.pubresumes = data.pubresumes;
			$scope.jobcomms = data.jobcomms;
			$scope.beyond = data.beyond;
			$scope.jobTeamDesc = data.jobTeamDesc;
			$scope.jobrecstate = handleJobrecstate(data.jobrecstate);//简历推荐状态
			$scope.isReady = true;
		});
	};
	loadViewInfo();
	//将简历推荐状态数据转换为map格式
	function handleJobrecstate(items){
		var ret = {};
		var all = 0;
		var notHandle = 0;
		if(items && items.length > 0){
			$.each(items, function(idx, val){
				ret[val.verify_state] = val.cou;
				all += val.cou;
				if(!val.verify_state){
					notHandle += val.cou;
				}
			});
		}
		ret.all = all;
		ret.notHandle = notHandle;
		//企业面试人
		ret.mianshi = safeN(ret['初试']) + safeN(ret['复试']) + safeN(ret['终试']);
		//企业否决
		ret.pass = safeN(ret['面试不通过']) + safeN(ret['审核不通过']) + safeN(ret['候选人放弃']);
		//企业确定录用
		ret.ok = safeN(ret['offer']) + safeN(ret['入职']);
		
		return ret;
	}
	function safeN(val){
		if(val === null || val === undefined || val === ''){
			return 0
		}else{
			return val;
		}
	}
	
	//向企业投递简历
	$scope.pubResume = function(id){
		$http.post("job/pubResume", {rjId: id}).success(function(data){
			$scope.formresult = data;
			if(data.success == 1){
				loadViewInfo();
			}
		});
	};
	//取消投递简历
	$scope.cancleResume = function(id){
		$http.post("job/cancleResume", {rjId: id}).success(function(data){
			$scope.formresult = data;
			if(data.success == 1){
				loadViewInfo();
			}
		});
	};
	
	//审核向企业投递的简历
	$scope.verify = function(id, status){
		$http.post("job/verifyResume", {rjId: id, status: status})
			.success(function(data){
				$scope.formresult = data;
				if(data.success == 1){
					loadViewInfo();
				}
		});
	};
	
	//添加交流信息
	$scope.addComm = function(){
		$http.post("job/editJobComm", {jobId: jobId, content: $scope.addcomm})
			.success(function(data){
				$scope.formresult = data;
				if(data.success == 1){
					loadViewInfo();
				}
		});
	};
	//删除交流信息
	$scope.delComm = function(id){
		$http.post("job/delJobComm", {id: id})
		.success(function(data){
			$scope.formresult = data;
			if(data.success == 1){
				loadViewInfo();
			}
		});
	};
	
	//加载推荐简历的沟通记录
	$scope.loadComm = function(id){
		$http.post("job/loadRJComm", {id: id})
		.success(function(data){
			$scope.comms = data;
			
			$scope.editRJCommID = id;
		});
	};
	
	//删除简历的沟通记录
	$scope.delComm = function(id){
		$http.post("job/delRJComm", {id: id})
		.success(function(data){
			$scope.formresult = data;
			$scope.loadComm(id);
		});
	};
	
	//新增简历的沟通记录
	$scope.addComm = function(){
		$http.post("job/editRJComm", 
				{
			content: $scope.addRJComm,
			resumejobId: $scope.editRJCommID})
		.success(function(data){
			$scope.formresult = data;
			$scope.loadComm(id);
		});
	};
	
});