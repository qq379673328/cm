//客户选择指令
app.directive('coreCustomselect', ["$http", function($http){
    return {
        restrict: 'A',
        replace: true,
        scope: {
        	customid: "=customid"
        },
        require: 'ngModel',
        templateUrl: 'static/app/components/directives/core/views/customselect.html',
        link: function($scope, $element, attrs, ngModel) {
        	var isInit = false;
        	//选择
        	$scope.ok = function(item){
        		$scope.customname = item.custom_name;
        		ngModel.$setViewValue(item);
        	};
        	//监控客户初始化
        	$scope.$watch('customid', function(newValue, oldValue){
        		if(!newValue){
        			return;
        		}
        		if(isInit){
        			return;
        		}else{
        			isInit = true;
        			$http.post("custom/getCustomById",{id: newValue}).success(function(data){
        					if(data.custom){
        						$scope.customname = data.custom.customName;
        					}
                		});
        		}
        	}, true);
        	
        	//监控查询
        	$scope.$watch('customsearch', function(newValue, oldValue){
        		if(newValue == oldValue) return;
        		$http.post("custom/list",
        				{
        				companyName: newValue,
        				page: 1, rows: 100,
        				beyond:"my",
        				customStatus: "签约运作"})
        				.success(function(data){
						$scope.customs = data.rows;
        		});
        	}, true);
        	//默认查询一次
        	$http.post("custom/list",
    				{
        			page: 1, rows: 100, beyond:"my",
        			customStatus: "签约运作"})
    				.success(function(data){
					$scope.customs = data.rows;
    		});
        }
    };
}]);