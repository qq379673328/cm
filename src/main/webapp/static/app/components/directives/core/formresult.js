app.directive('coreFormresult',["$timeout", function($timeout){
	var timer = null;
    return {
        restrict: 'A',
        scope: {
        	formresult: "=result"
        },
        templateUrl:'static/app/components/directives/core/views/formresult.html',
        link: function($scope){
        	$scope.$watch("formresult", function(){
        		if($scope.formresult == null) return;
        		if($scope.formresult.success == "1"){//成功的信息自动关闭
        			$timeout.cancel(timer);//先取消
        			timer = $timeout(function(){//新建
            			$scope.formresult = null;
            		}, 4000);
            		$scope.$on(
            				"$destroy",
            				function(event) {
            					$timeout.cancel(timer);
            				}
            		);
        		}
        	});
        }
    };
}]);