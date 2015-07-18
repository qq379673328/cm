//简历管理-简历信息
app.controller('ResumeMgrViewResumeCtrl',
		function($scope, $http, $routeParams, ngTableParams, 
				$rootScope) {
	$rootScope.menu = "resume";
	
	var resumeId = $routeParams.id;
	$scope.isReady = false;
	
	function reload(){
		//请求客户信息
		$http.post("resume/getResumeViewById", {id: resumeId}).success(function(data){
			$scope.resume = data.resume;
			var targets = data.target;
			if(targets && targets.length >0){
				var tar = targets[0];
				$scope.target = {
					id: tar.id,
					resumeId: tar.resume_id,
					targetType: tar.target_type,
					targetPlace: tar.target_place,
					targetJob: tar.target_job,
					targetIndustry: tar.target_industry,
					targetPay: tar.target_pay,
					workState: tar.work_state
				};
			}
			$scope.resumeDatas = data.resumeDatas;
			$scope.resumeEdus = data.resumeEdus;
			$scope.resumeJobs = data.resumeJobs;
			$scope.resumeLanguages = data.resumeLanguages;
			$scope.resumeWorkhistorys = data.resumeWorkhistorys;
			$scope.resumeComms = data.resumeComms;
			$scope.beyond = data.beyond;
			
			$scope.isReady = true;
		});
	}
	
	reload();
	
	//添加沟通记录
	$scope.addComm = function(){
		$http.post("resume/addComm", {
			resumeId: $scope.resume.id,
			content: $scope.addcomm
		}).success(function(data){
			$scope.formresult = data;
			$scope.isrequest = false;
			reload();
			/*if(data.success == "1"){//成功
				var item = data.data;
				$scope.resumeComms.push({
					id: item.id,
					resume_id: item.resumeId,
					content: item.content,
					create_time: item.createTime
				});
			}*/
		});
	};
	
	//删除沟通记录
	$scope.delComm = function(id){
		$http.post("resume/delComm", {
			id: id
		}).success(function(data){
			$scope.formresult = data;
			$scope.isrequest = false;
			if(data.success == "1"){//成功
				//delItemFormArray("resumeComms", id);
				reload();
			}
		});
	};
	//从数据中删除对象
	function delItemFormArray(at, id){
		var newAttas = [];
		var target = $scope[at];
		for(var idx in target){
			var atta = target[idx];
			if(atta.id != id){
				newAttas.push(atta);
			}
		}
		$scope[at] = newAttas;
	}
});