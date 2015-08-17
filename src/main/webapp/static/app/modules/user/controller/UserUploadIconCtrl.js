//用户-更新信息
app.controller('UserUploadIconCtrl',function($scope, $http,
		BaseInfoService, $rootScope) {
	
	$rootScope.menu = "uploadicon";
	
	//加载用户基本信息
	$http.post("user/getLoginUserInfo", {}).success(function(data){
		$scope.headImage = data.icon;
	});
	
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
	$scope.saveIcon = function(){
		
		if($scope.icon.x == undefined){//为选择
			$scope.formresult = {success: 0, message: "请截取图片"};
			return;
		}
		
		$scope.isrequest = true;
		$http.post("user/uploadicon", {
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
					$scope.headImage = item + "?_=" + Math.random();
					$scope.upicondata.data.id = null;
				}
			});
	};
	
});