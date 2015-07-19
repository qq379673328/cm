//客户合同选择指令
app.directive('coreCustomcontractselect', ["$http", function($http){
    return {
        restrict: 'A',
        replace: true,
        scope: {
        	finalval: "=finalval"
        },
        require: 'ngModel',
        templateUrl: 'static/app/components/directives/core/views/customcontractselect.html',
        link: function($scope, $element, attrs, ngModel) {
        	var isInit = false;
        	//选择
        	$scope.ok = function(item){
        		var val = item.custom_name + " - " + item.no;
        		$scope.finalval = val;
        		ngModel.$setViewValue({
        			customId: item.custom_id,
        			contractNo: item.no,
        			customContract: val
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
        		$http.post("invoice/getCustomContract",
        				{
        			companyName: $scope.customcontractsearch.companyName,
        			no: $scope.customcontractsearch.no,
        			page: 1,
        			rows: 100})
        				.success(function(data){
						$scope.items = data.rows;
        		});
        	}, true);
        	
        }
    };
}]);