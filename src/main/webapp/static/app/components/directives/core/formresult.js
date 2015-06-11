app.directive('coreFormresult',[function(){
    return {
        restrict: 'A',
        scope: {
        	formresult: "=result"
        },
        templateUrl:'static/app/components/directives/core/views/formresult.html'
    };
}]);