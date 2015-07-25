//基础数据加载指令
app.directive('coreBasedata', ["$http", function($http){
	var baseData = {
	};
	//入职时间
	baseData.inyear = [];
	var yearCount = 5;
	var currentYear= new Date().getFullYear();
	for(var i = 0; i < yearCount; i++){
		baseData.inyear.push(["" + (currentYear - i) + "", "" + (currentYear - i) + "年"]);
	}
	
	//获取所有码表
	baseData = {};
	$.ajax({
		url: "getAllCodes",
		async: false,
		success: function(data){
			data = eval(data);
			for(var i in data){
				var item = data[i];
				var type = item["codetype"],
					value = item["codevalue"];
				if(!baseData[type]){
					baseData[type] = [];
				}
				baseData[type].push([value, value]);
			}
		}
	});
	
    return {
        restrict: 'A',
        scope: {
        	value: "@value",//值
        	type: "@type",
        	key: "@key",
        	name: "@dataname",
        	bindModel: "=",
        	allSelectName: "@allSelectName"
        },
        templateUrl:'static/app/components/directives/core/views/basedata.html',
        replace: true,
        require: '?ngModel',
        link: function($scope, $el, attrs, ngModel){
        	$scope.items = baseData[$scope.key];
        	
        	$scope.click = function(val ,target){
        		ngModel.$setViewValue(val);
        		if(target){
        			var $target = $(target);
        			$target.siblings().removeClass("select");
        			$target.addClass("select");
        		}
        	};
        	
        	$scope.select = function(t){
        		$scope.bindModel = t.bindModel;
        	};
        }
    };
}]);