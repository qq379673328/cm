//简历管理-录入简历
app.controller('ResumeMgrInResumeCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "resume";
	
	var resumeId = $routeParams.id;
	$scope.isReady = false;
	
	$scope.target = {
			targetTypeA:{
				all: false,
				part: false,
				learn: false
			}
	};
	
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
				if(tar.target_type){
					$scope.target .targetTypeA = {
						all: false,
						part: false,
						learn: false
					};
					if(tar.target_type.indexOf("全职") != -1){
						$scope.target.targetTypeA.all = true;
					}
					if(tar.target_type.indexOf("兼职") != -1){
						$scope.target.targetTypeA.part = true;
					}
					if(tar.target_type.indexOf("实习") != -1){
						$scope.target.targetTypeA.learn = true;
					}
				}
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
		
		$scope.target = {};
		$scope.resumeDatas = [];
		$scope.resumeEdus = [];
		$scope.resumeJobs = [];
		$scope.resumeLanguages = [];
		$scope.resumeWorkhistorys = [];
	}
	
	//提交基本信息
	$scope.saveBaseInfo = function(next){
		if($("#resumeeditform-baseinfo").isHappy({
			fields: {
				//姓名
				name: {required: true, maxlength: 100},
				//居住地址
				address: {required: true, maxlength: 200},
				//性别
				sex: {required: true, maxlength: 10},
				//最高学历
				education: {required: true, maxlength: 100},
				//出生日期
				birthcay: {required: true, date: true},
				//婚姻状况
				marrage: {required: true, maxlength: 20},
				//手机号码
				phone: {required: true, telephone:true, maxlength: 100},
				//电子邮箱
				email: {required: true, email:true, maxlength: 100},
				//目前年薪
				yearPay: {required: true, number: true},
				//工作状态
				workState: {required: true, maxlength: 50},
				//行业
				industry: {maxlength: 100},
				//职位
				duty: {maxlength: 50},
				//个人简介
				desc: {required: true, maxlength: 500}
			}
		})){
			$scope.isrequest = true;
			$http.post("resume/saveBaseinfo", $scope.resume).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1"){//成功
					$scope.resume.id = data.data.id;
					if(next){
						$scope.show = "language";
					}
				}
			});
		}
	};
	
	//保存求职意向
	$scope.saveTarget = function(next){
		if($("#resumeeditform-target").isHappy({
			fields: {
				//期望工作性质
				worktype: {maxlength: 100},
				//期望工作地点
				targetPlace: {maxlength: 100},
				//期望从事职业
				targetJob: {maxlength: 100},
				//期望从事行业
				targetIndustry: {maxlength: 100},
				//期望月薪
				targetPay: {maxlength: 100},
				//工作状态
				workState: {maxlength: 100}
			}
		})){
			$scope.isrequest = true;
			$scope.target["resumeId"] = $scope.resume.id;
			
			//工作性质
			var targetType = "";
			if($scope.target.targetTypeA.all){
				targetType += "全职,";
			}
			if($scope.target.targetTypeA.part){
				targetType += "兼职,";
			}
			if($scope.target.targetTypeA.learn){
				targetType += "实习,";
			}
			$scope.target.targetType = targetType;
			
			$http.post("resume/saveTarget", $scope.target).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(data.success == "1"){//成功
					$scope.target.id = data.data.id;
					if(next){
						$scope.show = "workhistory";
					}
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
				//工作时间-从
				timeBegin: {required: true, date: true},
				//工作时间-到
				timeEnd: {date: true},
				//公司名
				company: {required: true, maxlength: 100},
				//内容
				content: {required: true, maxlength: 500},
				//职位
				zhiwei: {required: true, maxlength: 100},
				//行业
				hangye: {required: true, maxlength: 500}
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
						content: item.content,
						company: item.company,
						zhiwei: item.zhiwei,
						hangye: item.hangye
					});
					$("#hangye-tag").find("[name=hangye]").val("");
					$scope.workhistory = {};
					if(next){
						$scope.show = "icon";
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
				//培训时间-从
				timeBegin: {required: true, date: true},
				//培训时间-到
				timeEnd: {date: true},
				//培训机构
				org: {required: true, maxlength: 100},
				//课程
				course: {required: true, maxlength: 500}
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
						$scope.show = "target";
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
				//语种
				lanType: {required: true, maxlength: 100},
				//读写能力
				readAb: {required: true, maxlength: 100},
				//听说能力
				listenAb: {required: true, maxlength: 100},
				//内容
				content: {required: true, maxlength: 500}
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
						$scope.show = "target";
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
	
	//头像部分处理
	// Create variables (in this scope) to hold the API and image size
	  var jcrop_api,
	  boundx,
	  boundy,
	  
	  // Grab some information about the preview pane
	  $preview = $('#preview-pane'),
	  $pcnt = $('#preview-pane .preview-container'),
	  $pimg = $('#preview-pane .preview-container img');
	  
	  function updatePreview(c){
	    if (parseInt(c.w) > 0) {
	      var rx = 100 / c.w;
	      var ry = 100 / c.h;
	      
	      $scope.icon = c;
	      
	      $pimg.css({
	        width: Math.round(rx * boundx) + 'px',
	        height: Math.round(ry * boundy) + 'px',
	        marginLeft: '-' + Math.round(rx * c.x) + 'px',
	        marginTop: '-' + Math.round(ry * c.y) + 'px'
	      });
	    }
	  };


	//地址变化
	$scope.$watch('upicondata.data.id', function(newValue, oldValue){
		if(jcrop_api) jcrop_api.destroy();
		setTimeout(function(){
			$scope.icon = {};
			$('#icon_head').Jcrop({
				onChange: updatePreview,
			    onSelect: updatePreview,
			    width: 200,
			    height: 200,
			    boxWidth: 700,
			    aspectRatio: 1
			}, function(){
				jcrop_api = this;
				var bounds = this.getBounds();
			    boundx = bounds[0];
			    boundy = bounds[1];
			});
		}, 200);
	}, true);
	
	//上传头像
	$scope.saveIcon = function(next){
		
		if($scope.icon.x == undefined){//为选择
			$scope.formresult = {success: 0, message: "请截取图片"};
			return;
		}
		
		if($("#resumeeditform-icon").isHappy({
			fields: {
				
			}
		})){
			$scope.isrequest = true;
			$http.post("resume/saveIcon", {
				resumeId: $scope.resume.id,
				iconId: $scope.upicondata.data.id,
				x: parseInt($scope.icon.x),
				y: parseInt($scope.icon.y),
				w: parseInt($scope.icon.w),
				h: parseInt($scope.icon.h)
				}).success(function(data){
					$scope.formresult = data;
					$scope.isrequest = false;
					if(data.success == "1"){//成功
						var item = data.data;
						$scope.resume.headImage = item + "?_=" + Math.random();
						$scope.upicondata.data.id = null;
						if(next){
							$scope.show = "atta";
						}
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