//执行团队选择指令
app.directive('coreTeamselect', ["$http", function($http){
    return {
        restrict: 'A',
        replace: true,
        scope: {
        	te: "=bindteam"
        },
        templateUrl: 'static/app/components/directives/core/views/teamselect.html',
        link: function($scope, $element, attrs) {
        	$scope.selectteams = [];
        	$scope.teams = [];
        	$http.post("team/getSelectTeams", {}).success(function(data){
        		$scope.teams = data;
        	});
  
        	//添加
        	$scope.addItem = function(team, index){
        		$scope.teams.splice(index, 1);
        		$scope.selectteams.push(team);
        		resetTe();
        	};
        	//移除
        	$scope.removeItem = function(team, index){
        		$scope.selectteams.splice(index, 1);
        		$scope.teams.push(team);
        		resetTe();
        	};
        	function resetTe(){
        		var arr = [];
        		var teams = $scope.selectteams;
        		for(var i in teams){
        			arr.push(teams[i]["id"]);
        		}
        		$scope.te = arr.join(",");
        	}
        }
    };
}]);