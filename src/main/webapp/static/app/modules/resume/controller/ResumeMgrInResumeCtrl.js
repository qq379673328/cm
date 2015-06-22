//简历管理-录入简历
app.controller('ResumeMgrInResumeCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "resume";
	
	var resumeId = $routeParams.id;
	$scope.isReady = false;
	
	if(resumeId){//编辑
		//请求简历信息
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
			
			$scope.isReady = true;
			
		});
	}else{//新增
		$scope.isReady = true;
	}
	
	//提交基本信息
	$scope.saveBaseInfo = function(next){
		if($("#resumeeditform-baseinfo").isHappy({
			fields: {
				
			}
		})){
			$scope.isrequest = true;
			$http.post("resume/saveBaseinfo", $scope.resume).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1" && next){//成功
					$scope.show = "target";
					$scope.resume = data.data;
				}
			});
		}
	};
	
	//保存求职意向
	$scope.saveTarget = function(next){
		if($("#resumeeditform-target").isHappy({
			fields: {
				
			}
		})){
			$scope.isrequest = true;
			$scope.target["resumeId"] = $scope.resume.id;
			$http.post("resume/saveTarget", $scope.target).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1" && next){//成功
					$scope.show = "workhistory";
				}
			});
		}
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
	//保存工作经历
	$scope.saveWorkHistory = function(next){
		if($("#resumeeditform-workhistory").isHappy({
			fields: {
				
			}
		})){
			$scope.isrequest = true;
			$scope.workhistory["resumeId"] = $scope.resume.id;
			$http.post("resume/saveWorkHistory", $scope.workhistory).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1"){//成功
					var item = data.data;
					$scope.resumeWorkhistorys.push({
						id: item.id,
						resume_id: item.resumeId,
						time_begin: item.timeBegin,
						time_end: item.timeEnd,
						content: item.content
					});
					$scope.workhistory = {};
					if(next){
						$scope.show = "eduhistory";
					}
				}
			});
		}
	};
	//删除工作经历
	$scope.delWorkHistory = function(id){
		$http.post("resume/delWorkHistory", {id: id}).success(function(data){
			$scope.formresult = data;
			$scope.isrequest = false;
			if(data.success == "1"){//成功
				delItemFormArray("resumeWorkhistorys", id);
			}
		});
	};
	
	//保存教育经历
	$scope.saveEduHistory = function(next){
		if($("#resumeeditform-eduhistory").isHappy({
			fields: {
				
			}
		})){
			$scope.isrequest = true;
			$scope.eduhistory["resumeId"] = $scope.resume.id;
			$http.post("resume/saveEduHistory", $scope.eduhistory).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1"){//成功
					var item = data.data;
					$scope.resumeEdus.push({
						id: item.id,
						resume_id: item.resumeId,
						time_begin: item.timeBegin,
						time_end: item.timeEnd,
						org: item.org,
						course: item.course,
						content: item.content
					});
					$scope.eduhistory = {};
					if(next){
						$scope.show = "language";
					}
					
				}
			});
		}
	};
	//删除教育经历
	$scope.delEduHistory = function(id){
		$http.post("resume/delEduHistory", {id: id}).success(function(data){
			$scope.formresult = data;
			$scope.isrequest = false;
			if(data.success == "1"){//成功
				delItemFormArray("resumeEdus", id);
			}
		});
	};
	

	//保存语言
	$scope.saveLanguage = function(next){
		if($("#resumeeditform-language").isHappy({
			fields: {
				
			}
		})){
			$scope.isrequest = true;
			$scope.language["resumeId"] = $scope.resume.id;
			$http.post("resume/saveLanguage", $scope.language).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1"){//成功
					var item = data.data;
					$scope.resumeLanguages.push({
						id: item.id,
						resume_id: item.resumeId,
						lan_type: item.lanType,
						read_ab: item.readAb,
						listen_ab: item.listenAb,
						content: item.content
					});
					$scope.language = {};
					if(next){
						$scope.show = "icon";
					}
				}
			});
		}
	};
	//删除语言
	$scope.delLanguage = function(id){
		$http.post("resume/delLanguage", {id: id}).success(function(data){
			$scope.formresult = data;
			$scope.isrequest = false;
			if(data.success == "1"){//成功
				delItemFormArray("resumeLanguages", id);
			}
		});
	};
	
	//上传头像
	$scope.saveIcon = function(next){
		if($("#resumeeditform-icon").isHappy({
			fields: {
				
			}
		})){
			$scope.isrequest = true;
			$http.post("resume/saveIcon", {
				id: $scope.resume.id,
				iconPath: $scope.iconPath
				}).success(function(data){
					$scope.formresult = data;
					$scope.isrequest = false;
					if(data.success == "1" && next){//成功
						$scope.show = "atta";
					}
				});
		}
	};
	
	//上传附件
	$scope.uploadAttas = function(){
		if($("#resumeeditform-atta").isHappy({
			fields: {
				
			}
		})){
			$scope.isrequest = true;
			$http.post("resume/uploadAttas", {
				id: $scope.resume.id,
				attas: $scope.attas
				}).success(function(data){
					$scope.formresult = data;
					$scope.isrequest = false;
				});
		}
	};
});