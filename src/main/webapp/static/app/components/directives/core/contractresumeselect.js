//入职人选-发票
app.directive('coreContractresumeselect', ["$http", function($http){
    return {
        restrict: 'A',
        replace: true,
        scope: {
        	finalval: "=finalval"
        },
        require: 'ngModel',
        templateUrl: 'static/app/components/directives/core/views/contractresumeselect.html',
        link: function($scope, $element, attrs, ngModel) {
        	var isInit = false;
        	//选择
        	$scope.ok = function(item){
        		var val = item.resume_name + " - " + item.job_name;
        		$scope.finalval = val;
        		ngModel.$setViewValue({
        			resumeId: item.rid,
        			resumeDesc: val
        		});
        	};
        	
        	//监控客户初始化
        	$scope.$watch('finalval', function(newValue, oldValue){
        		if(!newValue){
        			return;
        		}
        		if(isInit){
        			return;
        		}else{
        			isInit = true;
        			$scope.finalval = newValue;
        		}
        	}, true);
        	
        	//监控查询
        	$scope.$watch('customcontractsearch', function(newValue, oldValue){
        		if(newValue == oldValue) return;
        		$http.post("invoice/getContractResume",
        				{
        			no: $scope.customcontractsearch.no,
        			jobName: $scope.customcontractsearch.jobName,
        			resumeName: $scope.customcontractsearch.resumeName,
        			page: 1,
        			rows: 100})
        				.success(function(data){
						$scope.items = data.rows;
        		});
        	}, true);
        	
        	//默认查询一次
        	$http.post("invoice/getContractResume",
    				{
    			page: 1,
    			rows: 100})
    				.success(function(data){
					$scope.items = data.rows;
    		});
        	
        }
    };
}]);