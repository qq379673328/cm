//客户选择指令
app.directive('coreCustomselect', ["$http", function($http){
    return {
        restrict: 'A',
        replace: true,
        require: 'ngModel',
        templateUrl: 'static/app/components/directives/core/views/customselect.html',
        link: function($scope, $element, attrs, ngModel) {
        	
        	//选择
        	$scope.ok = function(id){
        		ngModel.$setViewValue(id);
        	};
        	
        	//监控查询
        	$scope.$watch('customsearch', function(newValue, oldValue){
        		if(newValue == oldValue) return;
        		$http.post("custom/list",{companyName: newValue, page: 1, rows: 100}).success(
					function(data){
						$scope.customs = data.rows;
        		});
        	}, true);
        	
        }
    };
}]);