//执行团队选择指令
app.directive('coreTeamselect', ["$http", function($http){
    return {
        restrict: 'A',
        replace: true,
        scope: {
        	te: "=bindteam",
        	teamselect: "=teamselect",
        },
        templateUrl: 'static/app/components/directives/core/views/teamselect.html',
        link: function($scope, $element, attrs) {
        	/*$scope.selectteams = [];
        	$scope.teams = [];*/
        	/*$http.post("team/getSelectTeams", {ids: $scope.te}).success(function(data){
        		$scope.teams = data.unSelect;
        		$scope.selectteams = data.select;
        	});*/
        	//添加
        	$scope.addItem = function(team, index){
        		$scope.teamselect.unSelect.splice(index, 1);
        		$scope.teamselect.select.push(team);
        		resetTe();
        	};
        	//移除
        	$scope.removeItem = function(team, index){
        		$scope.teamselect.select.splice(index, 1);
        		$scope.teamselect.unSelect.push(team);
        		resetTe();
        	};
        	function resetTe(){
        		var arr = [];
        		var teams = $scope.teamselect.select;
        		for(var i in teams){
        			arr.push(teams[i]["id"]);
        		}
        		$scope.te = arr.join(",");
        	}
        }
    };
}]);