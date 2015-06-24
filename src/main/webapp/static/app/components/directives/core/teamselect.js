//执行团队选择指令
app.directive('coreTeamselect', ["$http", function($http){
    return {
        restrict: 'A',
        replace: true,
        scope: {
        	teamids: "=teamids",
        	single: "=single"
        },
        require: 'ngModel',
        templateUrl: 'static/app/components/directives/core/views/teamselect.html',
        link: function($scope, $element, attrs, ngModel) {
        	function resetTe(){
        		var arr = [];
        		var teams = $scope.select;
        		for(var i in teams){
        			arr.push(teams[i]["id"]);
        		}
        		ngModel.$setViewValue(arr.join(","));
        	}
        	
        	var isSingle = $scope.single ? $scope.single : false;
        	
        	var isInit = false;
        	//添加
        	$scope.addItem = function(team, index){
        		if(!$scope.select){
        			$scope.select = [];
        		}
        		if(isSingle){//单选
        			$scope.select = [];
        		}
        		$scope.select.push(team);
        		resetTe();
        	};
        	//移除
        	$scope.removeItem = function(team, index){
        		$scope.select.splice(index, 1);
        		resetTe();
        	};
        	//监控团队初始化
        	$scope.$watch('teamids', function(newValue, oldValue){
        		if(!newValue){
        			return;
        		}
        		if(isInit){
        			return;
        		}else{
        			isInit = true;
        			$http.post("team/getSelectTeams",{ids: newValue}).success(function(data){
    						$scope.select = data;
                		});
        		}
        	}, true);
        	
        	//监控查询
        	$scope.$watch('teamsearch', function(newValue, oldValue){
        		if(newValue == oldValue) return;
        		$http.post("team/list",{name: newValue, page: 1, rows: 100}).success(function(data){
        			$scope.teams = data.rows;
        		});
        	}, true);
        	
        }
    };
}]);